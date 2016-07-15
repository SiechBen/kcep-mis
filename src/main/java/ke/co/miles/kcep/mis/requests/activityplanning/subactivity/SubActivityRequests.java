/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.subactivity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ActivityPlanning;
import ke.co.miles.kcep.mis.entities.MeasurementUnit;
import ke.co.miles.kcep.mis.entities.SubActivity;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.LoginException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.ActivityPlanningRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;

/**
 *
 * @author siech
 */
@Stateless
public class SubActivityRequests extends EntityRequests implements SubActivityRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public Integer addSubActivity(SubActivityDetails subActivityDetails) throws MilesException {
        if (subActivityDetails == null) {
            throw new InvalidArgumentException("error_045_01");
        } else if (subActivityDetails.getDescription() != null && subActivityDetails.getDescription().trim().length() > 45) {
            throw new InvalidArgumentException("error_045_02");
        } else if (subActivityDetails.getActivityPlanning() == null) {
            throw new InvalidArgumentException("error_045_03");
        }

        SubActivity subActivity = new SubActivity();
        subActivity.setDescription(subActivityDetails.getDescription());
        subActivity.setEndDate(subActivityDetails.getEndDate());
        subActivity.setStartDate(subActivityDetails.getStartDate());
        subActivity.setActualExpenditure(subActivityDetails.getActualExpenditure());
        subActivity.setActivityPlanning(em.find(ActivityPlanning.class, subActivityDetails.getActivityPlanning().getId()));
        subActivity.setMeasurementUnit(em.find(MeasurementUnit.class, subActivityDetails.getMeasurementUnit().getId()));

        try {
            em.persist(subActivity);
            em.flush();
        } catch (Exception e) {
            throw new MilesException("error_000_01");
        }

        return subActivity.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public SubActivityDetails retrieveSubActivity(Integer id) throws MilesException {

        q = em.createNamedQuery("SubActivity.findById");
        q.setParameter("id", id);
        SubActivity subActivity;
        try {
            subActivity = (SubActivity) q.getSingleResult();
        } catch (NoResultException e) {
            throw new LoginException("error_045_10");
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivityToSubActivityDetails(subActivity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SubActivityDetails> retrieveSubActivities(Integer activityPlanningId) throws MilesException {
        q = em.createNamedQuery("SubActivity.findByActivityPlanningId");
        q.setParameter("activityPlanningId", activityPlanningId);
        List<SubActivity> subActivitys = new ArrayList<>();
        try {
            subActivitys = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivitys);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editSubActivity(SubActivityDetails subActivityDetails) throws MilesException {

        if (subActivityDetails == null) {
            throw new InvalidArgumentException("error_045_01");
        } else if (subActivityDetails.getId() == null) {
            throw new InvalidArgumentException("error_045_04");
        } else if (subActivityDetails.getDescription() != null && subActivityDetails.getDescription().trim().length() > 45) {
            throw new InvalidArgumentException("error_045_02");
        } else if (subActivityDetails.getActivityPlanning() == null) {
            throw new InvalidArgumentException("error_045_03");
        }

        SubActivity subActivity = em.find(SubActivity.class, subActivityDetails.getId());
        subActivity.setId(subActivityDetails.getId());
        subActivity.setDescription(subActivityDetails.getDescription());
        subActivity.setEndDate(subActivityDetails.getEndDate());
        subActivity.setStartDate(subActivityDetails.getStartDate());
        subActivity.setActualExpenditure(subActivityDetails.getActualExpenditure());
        subActivity.setActivityPlanning(em.find(ActivityPlanning.class, subActivityDetails.getActivityPlanning().getId()));
        subActivity.setMeasurementUnit(em.find(MeasurementUnit.class, subActivityDetails.getMeasurementUnit().getId()));

        try {
            em.merge(subActivity);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeSubActivity(Integer personId) throws MilesException {
        if (personId == null) {
            throw new InvalidArgumentException("error_045_09");
        }

        q = em.createNamedQuery("SubActivity.findByPersonId");
        q.setParameter("personId", personId);
        SubActivity subActivity;
        try {
            subActivity = (SubActivity) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        try {
            em.merge(subActivity);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private List<SubActivityDetails> convertSubActivitiesToSubActivityDetailsList(List<SubActivity> subActivitys) {

        List<SubActivityDetails> details = new ArrayList<>();
        for (SubActivity subActivity : subActivitys) {
            details.add(convertSubActivityToSubActivityDetails(subActivity));
        }

        return details;
    }

    @Override
    public SubActivityDetails convertSubActivityToSubActivityDetails(SubActivity subActivity) {

        SubActivityDetails subActivityDetails = new SubActivityDetails(subActivity.getId());
        subActivityDetails.setDescription(subActivity.getDescription());
        subActivityDetails.setActualExpenditure(subActivity.getActualExpenditure());
        subActivityDetails.setEndDate(subActivity.getEndDate());
        subActivityDetails.setStartDate(subActivity.getStartDate());
        subActivityDetails.setActivityPlanning(activityPlanningService.
                convertActivityPlanningToActivityPlanningDetails(subActivity.getActivityPlanning()));
        try {
            subActivityDetails.setMeasurementUnit(measurementUnitService.
                    convertMeasurementUnitToMeasurementUnitDetails(subActivity.getMeasurementUnit()));
        } catch (Exception e) {
        }

        return subActivityDetails;
    }
//</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(SubActivityRequests.class.getSimpleName());
    @EJB
    private ActivityPlanningRequestsLocal activityPlanningService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;

}
