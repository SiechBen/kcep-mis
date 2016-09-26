/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.descriptors.phenomenon.type;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.PhenomenonType;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PhenomenonTypeDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PhenomenonTypeRequests extends EntityRequests implements PhenomenonTypeRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addPhenomenonType(PhenomenonTypeDetails phenomenonTypeDetails) throws MilesException {

        if (phenomenonTypeDetails == null) {
            throw new InvalidArgumentException("error_052_01");
        } else if (phenomenonTypeDetails.getName() == null) {
            throw new InvalidArgumentException("error_052_02");
        } else if (phenomenonTypeDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_052_03");
        }

        PhenomenonType phenomenonType;
        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", phenomenonTypeDetails.getName());
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            phenomenonType = null;
        }
        if (phenomenonType != null) {
            throw new InvalidArgumentException("error_052_04");
        }

        phenomenonType = new PhenomenonType();
        phenomenonType.setName(phenomenonTypeDetails.getName());

        try {
            em.persist(phenomenonType);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return phenomenonType.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonTypeDetails> retrievePhenomenonTypes() throws MilesException {
        List<PhenomenonType> phenomenonTypes = new ArrayList<>();
        setQ(em.createNamedQuery("PhenomenonType.findAll"));
        try {
            phenomenonTypes = q.getResultList();
        } catch (Exception e) {
        }

        return convertPhenomenonTypesToPhenomenonTypeDetailsList(phenomenonTypes);
    }

    @Override
    public PhenomenonTypeDetails retrievePhenomenonType(int id) throws MilesException {
        PhenomenonType phenomenonType;
        setQ(em.createNamedQuery("PhenomenonType.findById"));
        q.setParameter("id", id);
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPhenomenonTypeToPhenomenonTypeDetails(phenomenonType);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPhenomenonType(PhenomenonTypeDetails phenomenonTypeDetails) throws MilesException {

        if (phenomenonTypeDetails == null) {
            throw new InvalidArgumentException("error_052_01");
        } else if (phenomenonTypeDetails.getId() == null) {
            throw new InvalidArgumentException("error_052_05");
        } else if (phenomenonTypeDetails.getName() == null) {
            throw new InvalidArgumentException("error_052_02");
        } else if (phenomenonTypeDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_052_03");
        }

        PhenomenonType phenomenonType;
        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", phenomenonTypeDetails.getName());
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            phenomenonType = null;
        }
        if (phenomenonType != null) {
            if (phenomenonType.getId().equals(phenomenonTypeDetails.getId())) {
                throw new InvalidArgumentException("error_052_04");
            }
        }

        phenomenonType = em.find(PhenomenonType.class, phenomenonTypeDetails.getId());
        phenomenonType.setId(phenomenonTypeDetails.getId());
        phenomenonType.setName(phenomenonTypeDetails.getName());

        try {
            em.merge(phenomenonType);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removePhenomenonType(int id) throws MilesException {
        PhenomenonType phenomenonType = em.find(PhenomenonType.class, id);
        try {
            em.remove(phenomenonType);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public PhenomenonTypeDetails convertPhenomenonTypeToPhenomenonTypeDetails(PhenomenonType phenomenonType) {

        PhenomenonTypeDetails phenomenonTypeDetails = new PhenomenonTypeDetails();
        try {
            phenomenonTypeDetails.setId(phenomenonType.getId());
        } catch (Exception e) {
        }
        try {
            phenomenonTypeDetails.setName(phenomenonType.getName());
        } catch (Exception e) {
        }
        return phenomenonTypeDetails;

    }

    private List<PhenomenonTypeDetails> convertPhenomenonTypesToPhenomenonTypeDetailsList(List<PhenomenonType> phenomenonTypes) {

        List<PhenomenonTypeDetails> phenomenonTypeDetailsList = new ArrayList<>();
        for (PhenomenonType phenomenonType : phenomenonTypes) {
            phenomenonTypeDetailsList.add(convertPhenomenonTypeToPhenomenonTypeDetails(phenomenonType));
        }

        return phenomenonTypeDetailsList;

    }

//</editor-fold>
}
