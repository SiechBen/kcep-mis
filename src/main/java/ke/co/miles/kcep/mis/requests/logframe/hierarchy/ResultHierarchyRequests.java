/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.hierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Component;
import ke.co.miles.kcep.mis.entities.ResultHierarchy;
import ke.co.miles.kcep.mis.entities.SubComponent;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.planning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ResultHierarchyDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ResultHierarchyRequests extends EntityRequests implements ResultHierarchyRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addResultHierarchy(ResultHierarchyDetails resultHierarchyDetails) throws MilesException {

        if (resultHierarchyDetails == null) {
            throw new InvalidArgumentException("error_038_01");
        } else if (resultHierarchyDetails.getDescription() == null) {
            throw new InvalidArgumentException("error_038_02");
        } else if (resultHierarchyDetails.getDescription().length() > 45) {
            throw new InvalidArgumentException("error_038_03");
        }

        ResultHierarchy resultHierarchy;
        q = em.createNamedQuery("ResultHierarchy.findByDescription");
        q.setParameter("description", resultHierarchyDetails.getDescription());
        try {
            resultHierarchy = (ResultHierarchy) q.getSingleResult();
        } catch (Exception e) {
            resultHierarchy = null;
        }
        if (resultHierarchy != null) {
            throw new InvalidArgumentException("error_038_05");
        }

        resultHierarchy = new ResultHierarchy();
        resultHierarchy.setDescription(resultHierarchyDetails.getDescription());
        if (resultHierarchyDetails.getComponent() != null) {
            resultHierarchy.setComponent(em.find(Component.class, resultHierarchyDetails.getComponent().getId()));
        }
        if (resultHierarchyDetails.getSubComponent() != null) {
            resultHierarchy.setSubComponent(em.find(SubComponent.class, resultHierarchyDetails.getSubComponent().getId()));
        }

        try {
            em.persist(resultHierarchy);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return resultHierarchy.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ResultHierarchyDetails> retrieveResultHierarchies() throws MilesException {
        List<ResultHierarchy> resultHierarchies = new ArrayList<>();
        q = em.createNamedQuery("ResultHierarchy.findAll");
        try {
            resultHierarchies = q.getResultList();
        } catch (Exception e) {
        }

        return convertResultHierarchiesToResultHierarchyDetailsList(resultHierarchies);
    }

    @Override
    public ResultHierarchyDetails retrieveResultHierarchy(int id) throws MilesException {
        ResultHierarchy resultHierarchy;
        q = em.createNamedQuery("ResultHierarchy.findById");
        q.setParameter("id", id);
        try {
            resultHierarchy = (ResultHierarchy) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertResultHierarchyToResultHierarchyDetails(resultHierarchy);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editResultHierarchy(ResultHierarchyDetails resultHierarchyDetails) throws MilesException {

        if (resultHierarchyDetails == null) {
            throw new InvalidArgumentException("error_038_01");
        } else if (resultHierarchyDetails.getId() == null) {
            throw new InvalidArgumentException("error_038_06");
        } else if (resultHierarchyDetails.getDescription() == null) {
            throw new InvalidArgumentException("error_038_02");
        } else if (resultHierarchyDetails.getDescription().length() > 45) {
            throw new InvalidArgumentException("error_038_03");
        } 

        ResultHierarchy resultHierarchy;
        q = em.createNamedQuery("ResultHierarchy.findByDescription");
        q.setParameter("description", resultHierarchyDetails.getDescription());
        try {
            resultHierarchy = (ResultHierarchy) q.getSingleResult();
        } catch (Exception e) {
            resultHierarchy = null;
        }
        if (resultHierarchy != null) {
            if (!Objects.equals(resultHierarchy.getId(), resultHierarchyDetails.getId())) {
                throw new InvalidArgumentException("error_038_05");
            }
        }

        resultHierarchy = em.find(ResultHierarchy.class, resultHierarchyDetails.getId());
        resultHierarchy.setId(resultHierarchyDetails.getId());
        resultHierarchy.setDescription(resultHierarchyDetails.getDescription());
        resultHierarchy.setComponent(em.find(Component.class, resultHierarchyDetails.getComponent().getId()));
            if (resultHierarchyDetails.getComponent() != null) {
            resultHierarchy.setComponent(em.find(Component.class, resultHierarchyDetails.getComponent().getId()));
        }   if (resultHierarchyDetails.getSubComponent() != null) {
            resultHierarchy.setSubComponent(em.find(SubComponent.class, resultHierarchyDetails.getSubComponent().getId()));
        }
        try {
            em.merge(resultHierarchy);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeResultHierarchy(int id) throws MilesException {
        ResultHierarchy resultHierarchy = em.find(ResultHierarchy.class, id);
        try {
            em.remove(resultHierarchy);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ResultHierarchyDetails convertResultHierarchyToResultHierarchyDetails(ResultHierarchy resultHierarchy) {

        ResultHierarchyDetails resultHierarchyDetails = new ResultHierarchyDetails(resultHierarchy.getId());
        resultHierarchyDetails.setDescription(resultHierarchy.getDescription());
        if (resultHierarchy.getComponent() != null) {
            resultHierarchyDetails.setComponent(componentService.
                    convertComponentToComponentDetails(resultHierarchy.getComponent()));
        }
        if (resultHierarchy.getSubComponent() != null) {
            resultHierarchyDetails.setSubComponent(subComponentService.
                    convertSubComponentToSubComponentDetails(resultHierarchy.getSubComponent()));
        }
        
        return resultHierarchyDetails;

    }

    private List<ResultHierarchyDetails> convertResultHierarchiesToResultHierarchyDetailsList(List<ResultHierarchy> resultHierarchies) {

        List<ResultHierarchyDetails> resultHierarchyDetailsList = new ArrayList<>();
        for (ResultHierarchy resultHierarchy : resultHierarchies) {
            resultHierarchyDetailsList.add(convertResultHierarchyToResultHierarchyDetails(resultHierarchy));
        }

        return resultHierarchyDetailsList;

    }

//</editor-fold>
    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private SubComponentRequestsLocal subComponentService;

}
