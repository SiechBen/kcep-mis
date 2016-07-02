/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.planning;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Activity;
import ke.co.miles.kcep.mis.entities.Component;
import ke.co.miles.kcep.mis.entities.ImplementingPartner;
import ke.co.miles.kcep.mis.entities.Planning;
import ke.co.miles.kcep.mis.entities.SubComponent;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.planning.activity.ActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PlanningDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PlanningRequests extends EntityRequests implements PlanningRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addPlanning(PlanningDetails planningDetails) throws MilesException {

        if (planningDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        } else if (planningDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_017_02");
        } else if (planningDetails.getImplementingPartner() == null) {
            throw new InvalidArgumentException("error_017_03");
        } else if (planningDetails.getActivity() == null) {
            throw new InvalidArgumentException("error_017_04");
        }

        Planning planning = new Planning();
        planning.setAllocatedBudget(planningDetails.getAllocatedBudget());
        planning.setAnnualWorkplanReferenceCode(planningDetails.getAnnualWorkplanReferenceCode());
        planning.setAwpbTarget(planningDetails.getAwpbTarget());
        planning.setValueAchieved(planningDetails.getValueAchieved());
        planning.setProgrammeTarget(planningDetails.getProgrammeTarget());
        planning.setActivity(em.find(Activity.class, planningDetails.getActivity().getId()));
        planning.setComponent(em.find(Component.class, planningDetails.getComponent().getId()));
        planning.setImplementingPartner(em.find(ImplementingPartner.class, planningDetails.getImplementingPartner().getId()));
        if (planningDetails.getSubComponent() != null) {
            planning.setSubComponent(em.find(SubComponent.class, planningDetails.getSubComponent().getId()));
        }
        if (planningDetails.getActivity() != null) {
            planning.setActivity(em.find(Activity.class, planningDetails.getActivity().getId()));
        }

        try {
            em.persist(planning);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return planning.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<PlanningDetails> retrievePlannings() throws MilesException {
        List<Planning> plannings = new ArrayList<>();
        q = em.createNamedQuery("Planning.findAll");
        try {
            plannings = q.getResultList();
        } catch (Exception e) {
        }

        return convertPlanningsToPlanningDetailsList(plannings);
    }

    @Override
    public PlanningDetails retrievePlanning(int id) throws MilesException {
        Planning planning;
        q = em.createNamedQuery("Planning.findById");
        q.setParameter("id", id);
        try {
            planning = (Planning) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPlanningToPlanningDetails(planning);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPlanning(PlanningDetails planningDetails) throws MilesException {

        if (planningDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        } else if (planningDetails.getId() == null) {
            throw new InvalidArgumentException("error_017_05");
        } else if (planningDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_017_02");
        } else if (planningDetails.getImplementingPartner() == null) {
            throw new InvalidArgumentException("error_017_03");
        } else if (planningDetails.getActivity() == null) {
            throw new InvalidArgumentException("error_017_04");
        }
        Planning planning = em.find(Planning.class, planningDetails.getId());
        planning.setId(planningDetails.getId());
        planning.setAllocatedBudget(planningDetails.getAllocatedBudget());
        planning.setAnnualWorkplanReferenceCode(planningDetails.getAnnualWorkplanReferenceCode());
        planning.setAwpbTarget(planningDetails.getAwpbTarget());
        planning.setValueAchieved(planningDetails.getValueAchieved());
        planning.setProgrammeTarget(planningDetails.getProgrammeTarget());
        planning.setActivity(em.find(Activity.class, planningDetails.getActivity().getId()));
        planning.setComponent(em.find(Component.class, planningDetails.getComponent().getId()));
        planning.setImplementingPartner(em.find(ImplementingPartner.class, planningDetails.getImplementingPartner().getId()));
        if (planningDetails.getSubComponent() != null) {
            planning.setSubComponent(em.find(SubComponent.class, planningDetails.getSubComponent().getId()));
        }
        if (planningDetails.getActivity() != null) {
            planning.setActivity(em.find(Activity.class, planningDetails.getActivity().getId()));
        }

        try {
            em.merge(planning);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removePlanning(int id) throws MilesException {
        Planning planning = em.find(Planning.class, id);
        try {
            em.remove(planning);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private PlanningDetails convertPlanningToPlanningDetails(Planning planning) {

        PlanningDetails planningDetails = new PlanningDetails(planning.getId());
        planningDetails.setAllocatedBudget(planning.getAllocatedBudget());
        planningDetails.setAnnualWorkplanReferenceCode(planning.getAnnualWorkplanReferenceCode());
        planningDetails.setAwpbTarget(planning.getAwpbTarget());
        planningDetails.setValueAchieved(planning.getValueAchieved());
        planningDetails.setProgrammeTarget(planning.getProgrammeTarget());
        planningDetails.setActivity(activityService.
                convertActivityToActivityDetails(planning.getActivity()));
        planningDetails.setComponent(componentService.
                convertComponentToComponentDetails(planning.getComponent()));
        planningDetails.setImplementingPartner(implementingPartnerService.
                convertImplementingPartnerToImplementingPartnerDetails(planning.getImplementingPartner()));
        if (planning.getSubComponent() != null) {
            planningDetails.setSubComponent(subComponentService.
                    convertSubComponentToSubComponentDetails(planning.getSubComponent()));
        }
        if (planning.getActivity() != null) {
            planningDetails.setActivity(measurementUnitService.
                    convertActivityToActivityDetails(planning.getActivity()));
        }

        return planningDetails;

    }

    private List<PlanningDetails> convertPlanningsToPlanningDetailsList(List<Planning> plannings) {

        List<PlanningDetails> planningDetailsList = new ArrayList<>();
        for (Planning planning : plannings) {
            planningDetailsList.add(convertPlanningToPlanningDetails(planning));
        }

        return planningDetailsList;

    }

//</editor-fold>
    @EJB
    private ActivityRequestsLocal activityService;
    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private SubComponentRequestsLocal subComponentService;
    @EJB
    private ActivityRequestsLocal measurementUnitService;
    @EJB
    private ImplementingPartnerRequestsLocal implementingPartnerService;
}
