/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.responsepcu;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ResponsePcu;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ResponsePcuDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ResponsePcuRequests extends EntityRequests implements ResponsePcuRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addResponsePcu(ResponsePcuDetails responsePcuDetails) throws MilesException {

        if (responsePcuDetails == null) {
            throw new InvalidArgumentException("error_029_01");
        } else if (responsePcuDetails.getName() == null) {
            throw new InvalidArgumentException("error_029_02");
        } else if (responsePcuDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_029_03");
        }

        ResponsePcu responsePcu;
        q = em.createNamedQuery("ResponsePcu.findByName");
        q.setParameter("name", responsePcuDetails.getName());
        try {
            responsePcu = (ResponsePcu) q.getSingleResult();
        } catch (Exception e) {
            responsePcu = null;
        }
        if (responsePcu != null) {
            throw new InvalidArgumentException("error_029_04");
        }

        responsePcu = new ResponsePcu();
        responsePcu.setName(responsePcuDetails.getName());

        try {
            em.persist(responsePcu);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return responsePcu.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<ResponsePcuDetails> retrieveResponsePcuList() throws MilesException {
        List<ResponsePcu> responsePcuList = new ArrayList<>();
        q = em.createNamedQuery("ResponsePcu.findAll");
        try {
            responsePcuList = q.getResultList();
        } catch (Exception e) {
        }

        return convertResponsePcuListToResponsePcuDetailsList(responsePcuList);
    }

    @Override
    public ResponsePcuDetails retrieveResponsePcu(int id) throws MilesException {
        ResponsePcu responsePcu;
        q = em.createNamedQuery("ResponsePcu.findById");
        q.setParameter("id", id);
        try {
            responsePcu = (ResponsePcu) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertResponsePcuToResponsePcuDetails(responsePcu);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editResponsePcu(ResponsePcuDetails responsePcuDetails) throws MilesException {

        if (responsePcuDetails == null) {
            throw new InvalidArgumentException("error_029_01");
        } else if (responsePcuDetails.getId() == null) {
            throw new InvalidArgumentException("error_029_05");
        } else if (responsePcuDetails.getName() == null) {
            throw new InvalidArgumentException("error_029_02");
        } else if (responsePcuDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_029_03");
        }

        ResponsePcu responsePcu;
        q = em.createNamedQuery("ResponsePcu.findByName");
        q.setParameter("name", responsePcuDetails.getName());
        try {
            responsePcu = (ResponsePcu) q.getSingleResult();
        } catch (Exception e) {
            responsePcu = null;
        }
        if (responsePcu != null) {
            if (responsePcu.getId().equals(responsePcuDetails.getId())) {
                throw new InvalidArgumentException("error_029_04");
            }
        }

        responsePcu = em.find(ResponsePcu.class, responsePcuDetails.getId());
        responsePcu.setId(responsePcuDetails.getId());
        responsePcu.setName(responsePcuDetails.getName());

        try {
            em.merge(responsePcu);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeResponsePcu(int id) throws MilesException {
        ResponsePcu responsePcu = em.find(ResponsePcu.class, id);
        try {
            em.remove(responsePcu);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ResponsePcuDetails convertResponsePcuToResponsePcuDetails(ResponsePcu responsePcu) {

        ResponsePcuDetails responsePcuDetails = new ResponsePcuDetails();
        try {
            responsePcuDetails.setId(responsePcu.getId());
        } catch (Exception e) {
        }
        try {
            responsePcuDetails.setName(responsePcu.getName());
        } catch (Exception e) {
        }
        return responsePcuDetails;

    }

    private List<ResponsePcuDetails> convertResponsePcuListToResponsePcuDetailsList(List<ResponsePcu> responsePcuList) {

        List<ResponsePcuDetails> responsePcuDetailsList = new ArrayList<>();
        for (ResponsePcu responsePcu : responsePcuList) {
            responsePcuDetailsList.add(convertResponsePcuToResponsePcuDetails(responsePcu));
        }

        return responsePcuDetailsList;

    }

//</editor-fold>
    
}
