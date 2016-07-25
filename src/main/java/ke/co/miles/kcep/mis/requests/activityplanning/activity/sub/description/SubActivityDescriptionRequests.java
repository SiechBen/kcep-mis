/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.description;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.Debugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Activity;
import ke.co.miles.kcep.mis.entities.SubActivityDescription;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.ActivityRequestsLocal;
import ke.co.miles.kcep.mis.utilities.SubActivityDescriptionDetails;

/**
 *
 * @author siech
 */
@Stateless
public class SubActivityDescriptionRequests extends EntityRequests implements SubActivityDescriptionRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addSubActivityDescription(SubActivityDescriptionDetails subActivityDescriptionDetails) throws MilesException {

        if (subActivityDescriptionDetails == null) {
            throw new InvalidArgumentException("error_032_01");
        } else if (subActivityDescriptionDetails.getDescription() == null) {
            throw new InvalidArgumentException("error_032_02");
        } else if (subActivityDescriptionDetails.getDescription().length() > 200) {
            throw new InvalidArgumentException("error_032_03");
        } else if (subActivityDescriptionDetails.getActivity() == null) {
            throw new InvalidArgumentException("error_032_04");
        }

        SubActivityDescription subActivityDescription;
        q = em.createNamedQuery("SubActivityDescription.findByDescription");
        q.setParameter("description", subActivityDescriptionDetails.getDescription());
        try {
            subActivityDescription = (SubActivityDescription) q.getSingleResult();
        } catch (Exception e) {
            subActivityDescription = null;
        }
        if (subActivityDescription != null) {
            throw new InvalidArgumentException("error_032_05");
        }

        subActivityDescription = new SubActivityDescription();
        subActivityDescription.setDescription(subActivityDescriptionDetails.getDescription());
        subActivityDescription.setActivity(em.find(Activity.class, subActivityDescriptionDetails.getActivity().getId()));

        try {
            em.persist(subActivityDescription);
            em.flush();
        } catch (Exception e) {
            Debugger.debug(e);
            throw new InvalidStateException("error_000_01");
        }

        return subActivityDescription.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<SubActivityDescriptionDetails> retrieveSubActivityDescriptions() throws MilesException {
        List<SubActivityDescription> activities = new ArrayList<>();
        q = em.createNamedQuery("SubActivityDescription.findAll");
        try {
            activities = q.getResultList();
        } catch (Exception e) {
        }

        return convertActivitiesToSubActivityDescriptionDetailsList(activities);
    }

    @Override
    public SubActivityDescriptionDetails retrieveSubActivityDescription(int id) throws MilesException {
        SubActivityDescription subActivityDescription;
        q = em.createNamedQuery("SubActivityDescription.findById");
        q.setParameter("id", id);
        try {
            subActivityDescription = (SubActivityDescription) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivityDescriptionToSubActivityDescriptionDetails(subActivityDescription);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editSubActivityDescription(SubActivityDescriptionDetails subActivityDescriptionDetails) throws MilesException {

        if (subActivityDescriptionDetails == null) {
            throw new InvalidArgumentException("error_032_01");
        } else if (subActivityDescriptionDetails.getId() == null) {
            throw new InvalidArgumentException("error_032_06");
        } else if (subActivityDescriptionDetails.getDescription() == null) {
            throw new InvalidArgumentException("error_032_02");
        } else if (subActivityDescriptionDetails.getDescription().length() > 200) {
            throw new InvalidArgumentException("error_032_03");
        } else if (subActivityDescriptionDetails.getActivity() == null) {
            throw new InvalidArgumentException("error_032_04");
        }

        SubActivityDescription subActivityDescription;
        q = em.createNamedQuery("SubActivityDescription.findByDescription");
        q.setParameter("description", subActivityDescriptionDetails.getDescription());
        try {
            subActivityDescription = (SubActivityDescription) q.getSingleResult();
        } catch (Exception e) {
            subActivityDescription = null;
        }
        if (subActivityDescription != null) {
            if (subActivityDescription.getId().equals(subActivityDescriptionDetails.getId())) {
                throw new InvalidArgumentException("error_032_05");
            }
        }

        subActivityDescription = em.find(SubActivityDescription.class, subActivityDescriptionDetails.getId());
        subActivityDescription.setId(subActivityDescriptionDetails.getId());
        subActivityDescription.setDescription(subActivityDescriptionDetails.getDescription());
        subActivityDescription.setActivity(em.find(Activity.class, subActivityDescriptionDetails.getActivity().getId()));

        try {
            em.merge(subActivityDescription);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeSubActivityDescription(int id) throws MilesException {
        SubActivityDescription subActivityDescription = em.find(SubActivityDescription.class, id);
        try {
            em.remove(subActivityDescription);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public SubActivityDescriptionDetails convertSubActivityDescriptionToSubActivityDescriptionDetails(SubActivityDescription subActivityDescription) {

        SubActivityDescriptionDetails subActivityDescriptionDetails = new SubActivityDescriptionDetails(subActivityDescription.getId());
        subActivityDescriptionDetails.setDescription(subActivityDescription.getDescription());
        subActivityDescriptionDetails.setActivity(activityService.convertActivityToActivityDetails(subActivityDescription.getActivity()));
        return subActivityDescriptionDetails;

    }

    private List<SubActivityDescriptionDetails> convertActivitiesToSubActivityDescriptionDetailsList(List<SubActivityDescription> activities) {

        List<SubActivityDescriptionDetails> subActivityDescriptionDetailsList = new ArrayList<>();
        for (SubActivityDescription subActivityDescription : activities) {
            subActivityDescriptionDetailsList.add(convertSubActivityDescriptionToSubActivityDescriptionDetails(subActivityDescription));
        }

        return subActivityDescriptionDetailsList;

    }

//</editor-fold>
     @EJB 
    private ActivityRequestsLocal activityService;
}
