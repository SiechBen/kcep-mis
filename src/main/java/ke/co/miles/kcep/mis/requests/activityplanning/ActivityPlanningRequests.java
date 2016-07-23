/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Activity;
import ke.co.miles.kcep.mis.entities.ActivityPlanning;
import ke.co.miles.kcep.mis.entities.Component;
import ke.co.miles.kcep.mis.entities.ImplementingPartner;
import ke.co.miles.kcep.mis.entities.PerformanceIndicator;
import ke.co.miles.kcep.mis.entities.SubComponent;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activity.ActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityPlanningDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ActivityPlanningRequests extends EntityRequests implements ActivityPlanningRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addActivityPlanning(ActivityPlanningDetails activityPlanningDetails) throws MilesException {

        if (activityPlanningDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        } else if (activityPlanningDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_017_02");
        } else if (activityPlanningDetails.getImplementingPartner() == null) {
            throw new InvalidArgumentException("error_017_03");
        } else if (activityPlanningDetails.getPerformanceIndicator() == null) {
            throw new InvalidArgumentException("error_017_04");
        } else if (activityPlanningDetails.getActivity() == null) {
            throw new InvalidArgumentException("error_017_05");
        }

        ActivityPlanning activityPlanning = new ActivityPlanning();
        activityPlanning.setTotal(activityPlanningDetails.getTotal());
        activityPlanning.setCategory(activityPlanningDetails.getCategory());
        activityPlanning.setAwpbTarget(activityPlanningDetails.getAwpbTarget());
        activityPlanning.setValueAchieved(activityPlanningDetails.getValueAchieved());
        activityPlanning.setAllocatedBudget(activityPlanningDetails.getAllocatedBudget());
        activityPlanning.setProcurementPlan(activityPlanningDetails.getProcurementPlan());
        activityPlanning.setProgrammeTarget(activityPlanningDetails.getProgrammeTarget());
        activityPlanning.setActivity(em.find(Activity.class, activityPlanningDetails.getActivity().getId()));
        activityPlanning.setComponent(em.find(Component.class, activityPlanningDetails.getComponent().getId()));
        activityPlanning.setAnnualWorkplanReferenceCode(activityPlanningDetails.getAnnualWorkplanReferenceCode());
        activityPlanning.setImplementingPartner(em.find(ImplementingPartner.class, activityPlanningDetails.getImplementingPartner().getId()));
        activityPlanning.setPerformanceIndicator(em.find(PerformanceIndicator.class, activityPlanningDetails.getPerformanceIndicator().getId()));
        if (activityPlanningDetails.getSubComponent() != null) {
            activityPlanning.setSubComponent(em.find(SubComponent.class, activityPlanningDetails.getSubComponent().getId()));
        }

        try {
            em.persist(activityPlanning);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return activityPlanning.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ActivityPlanningDetails> retrieveActivityPlannings() throws MilesException {
        List<ActivityPlanning> activityPlanning = new ArrayList<>();
        q = em.createNamedQuery("ActivityPlanning.findAll");
        try {
            activityPlanning = q.getResultList();
        } catch (Exception e) {
        }

        return convertActivityPlanningsToActivityPlanningDetailsList(activityPlanning);
    }

    @Override
    public ActivityPlanningDetails retrieveActivityPlanning(int id) throws MilesException {
        ActivityPlanning activityPlanning;
        q = em.createNamedQuery("ActivityPlanning.findById");
        q.setParameter("id", id);
        try {
            activityPlanning = (ActivityPlanning) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertActivityPlanningToActivityPlanningDetails(activityPlanning);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editActivityPlanning(ActivityPlanningDetails activityPlanningDetails) throws MilesException {

        if (activityPlanningDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        } else if (activityPlanningDetails.getId() == null) {
            throw new InvalidArgumentException("error_017_06");
        } else if (activityPlanningDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_017_02");
        } else if (activityPlanningDetails.getImplementingPartner() == null) {
            throw new InvalidArgumentException("error_017_03");
        } else if (activityPlanningDetails.getPerformanceIndicator() == null) {
            throw new InvalidArgumentException("error_017_04");
        } else if (activityPlanningDetails.getActivity() == null) {
            throw new InvalidArgumentException("error_017_05");
        }

        ActivityPlanning activityPlanning = em.find(ActivityPlanning.class, activityPlanningDetails.getId());
        activityPlanning.setId(activityPlanningDetails.getId());
        activityPlanning.setTotal(activityPlanningDetails.getTotal());
        activityPlanning.setCategory(activityPlanningDetails.getCategory());
        activityPlanning.setAwpbTarget(activityPlanningDetails.getAwpbTarget());
        activityPlanning.setValueAchieved(activityPlanningDetails.getValueAchieved());
        activityPlanning.setAllocatedBudget(activityPlanningDetails.getAllocatedBudget());
        activityPlanning.setProcurementPlan(activityPlanningDetails.getProcurementPlan());
        activityPlanning.setProgrammeTarget(activityPlanningDetails.getProgrammeTarget());
        activityPlanning.setActivity(em.find(Activity.class, activityPlanningDetails.getActivity().getId()));
        activityPlanning.setComponent(em.find(Component.class, activityPlanningDetails.getComponent().getId()));
        activityPlanning.setAnnualWorkplanReferenceCode(activityPlanningDetails.getAnnualWorkplanReferenceCode());
        activityPlanning.setImplementingPartner(em.find(ImplementingPartner.class, activityPlanningDetails.getImplementingPartner().getId()));
        activityPlanning.setPerformanceIndicator(em.find(PerformanceIndicator.class, activityPlanningDetails.getPerformanceIndicator().getId()));
        if (activityPlanningDetails.getSubComponent() != null) {
            activityPlanning.setSubComponent(em.find(SubComponent.class, activityPlanningDetails.getSubComponent().getId()));
        }

        try {
            em.merge(activityPlanning);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeActivityPlanning(int id) throws MilesException {
        ActivityPlanning activityPlanning = em.find(ActivityPlanning.class, id);
        try {
            em.remove(activityPlanning);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ActivityPlanningDetails convertActivityPlanningToActivityPlanningDetails(ActivityPlanning activityPlanning) {

        ActivityPlanningDetails activityPlanningDetails = new ActivityPlanningDetails(activityPlanning.getId());
        activityPlanningDetails.setTotal(activityPlanning.getTotal());
        activityPlanningDetails.setCategory(activityPlanning.getCategory());
        activityPlanningDetails.setAwpbTarget(activityPlanning.getAwpbTarget());
        activityPlanningDetails.setValueAchieved(activityPlanning.getValueAchieved());
        activityPlanningDetails.setAllocatedBudget(activityPlanning.getAllocatedBudget());
        activityPlanningDetails.setProcurementPlan(activityPlanning.getProcurementPlan());
        activityPlanningDetails.setProgrammeTarget(activityPlanning.getProgrammeTarget());
        activityPlanningDetails.setActivity(activityService.convertActivityToActivityDetails(activityPlanning.getActivity()));
        activityPlanningDetails.setAnnualWorkplanReferenceCode(activityPlanning.getAnnualWorkplanReferenceCode());
        activityPlanningDetails.setPerformanceIndicator(performanceIndicatortService.
                convertPerformanceIndicatorToPerformanceIndicatorDetails(activityPlanning.getPerformanceIndicator()));
        activityPlanningDetails.setComponent(componentService.
                convertComponentToComponentDetails(activityPlanning.getComponent()));
        activityPlanningDetails.setImplementingPartner(implementingPartnerService.
                convertImplementingPartnerToImplementingPartnerDetails(activityPlanning.getImplementingPartner()));
        if (activityPlanning.getSubComponent() != null) {
            activityPlanningDetails.setSubComponent(subComponentService.
                    convertSubComponentToSubComponentDetails(activityPlanning.getSubComponent()));
        }

        return activityPlanningDetails;

    }

    private List<ActivityPlanningDetails> convertActivityPlanningsToActivityPlanningDetailsList(List<ActivityPlanning> activityPlanningList) {

        List<ActivityPlanningDetails> activityPlanningDetailsList = new ArrayList<>();
        for (ActivityPlanning activityPlanning : activityPlanningList) {
            activityPlanningDetailsList.add(convertActivityPlanningToActivityPlanningDetails(activityPlanning));
        }

        return activityPlanningDetailsList;

    }

//</editor-fold>
    @EJB
    private ActivityRequestsLocal activityService;
    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private SubComponentRequestsLocal subComponentService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatortService;
    @EJB
    private ImplementingPartnerRequestsLocal implementingPartnerService;
}
