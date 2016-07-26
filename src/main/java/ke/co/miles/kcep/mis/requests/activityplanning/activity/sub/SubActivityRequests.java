/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.sub;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ActivityName;
import ke.co.miles.kcep.mis.entities.Component;
import ke.co.miles.kcep.mis.entities.ExpenditureCategory;
import ke.co.miles.kcep.mis.entities.ImplementingPartner;
import ke.co.miles.kcep.mis.entities.MeasurementUnit;
import ke.co.miles.kcep.mis.entities.PerformanceIndicator;
import ke.co.miles.kcep.mis.entities.ResponsePcu;
import ke.co.miles.kcep.mis.entities.SubActivity;
import ke.co.miles.kcep.mis.entities.SubActivityName;
import ke.co.miles.kcep.mis.entities.SubComponent;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.ActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.sub.SubActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.expenditurecategory.ExpenditureCategoryRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.responsepcu.ResponsePcuRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
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
    public int addSubActivity(SubActivityDetails subActivityDetails) throws MilesException {

        if (subActivityDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        } else if (subActivityDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_017_02");
        } else if (subActivityDetails.getImplementingPartner() == null) {
            throw new InvalidArgumentException("error_017_03");
        } else if (subActivityDetails.getPerformanceIndicator() == null) {
            throw new InvalidArgumentException("error_017_04");
        } else if (subActivityDetails.getActivityName() == null) {
            throw new InvalidArgumentException("error_017_05");
        }

        SubActivity subActivity = new SubActivity();
        subActivity.setAnnualWorkplanReferenceCode(subActivityDetails.getAnnualWorkplanReferenceCode());
        MilesDebugger.debug("Start date", subActivityDetails.getStartDate(), "End date", subActivityDetails.getEndDate());
        subActivity.setStartDate(subActivityDetails.getStartDate());
        subActivity.setEndDate(subActivityDetails.getEndDate());
        subActivity.setUnitCost(subActivityDetails.getUnitCost());
        subActivity.setAwpbTarget(subActivityDetails.getAwpbTarget());
        subActivity.setProgrammeTarget(subActivityDetails.getProgrammeTarget());
        subActivity.setTotals(subActivityDetails.getTotals());
        subActivity.setProcurementPlan(subActivityDetails.getProcurementPlan());
        subActivity.setDescription(subActivityDetails.getDescription());
        subActivity.setValueAchieved(subActivityDetails.getValueAchieved());
        subActivity.setAllocatedBudget(subActivityDetails.getAllocatedBudget());
        subActivity.setGokPercentage(subActivityDetails.getGokPercentage());
        subActivity.setIfadLoanPercentage(subActivityDetails.getIfadLoanPercentage());
        subActivity.setIfadGrantPercentage(subActivityDetails.getIfadGrantPercentage());
        subActivity.setBeneficiariesPercentage(subActivityDetails.getBeneficiariesPercentage());
        subActivity.setEuPercentage(subActivityDetails.getEuPercentage());
        subActivity.setFinancialInstitutionPercentage(subActivityDetails.getFinancialInstitutionPercentage());
        subActivity.setActivityName(em.find(ActivityName.class, subActivityDetails.getActivityName().getId()));
        subActivity.setExpenditureCategory(em.find(ExpenditureCategory.class, subActivityDetails.getExpenditureCategory().getId()));
        subActivity.setPerformanceIndicator(em.find(PerformanceIndicator.class, subActivityDetails.getPerformanceIndicator().getId()));
        subActivity.setComponent(em.find(Component.class, subActivityDetails.getComponent().getId()));
        if (subActivityDetails.getSubComponent() != null) {
            subActivity.setSubComponent(em.find(SubComponent.class, subActivityDetails.getSubComponent().getId()));
        }
        subActivity.setImplementingPartner(em.find(ImplementingPartner.class, subActivityDetails.getImplementingPartner().getId()));
        subActivity.setMeasurementUnit(em.find(MeasurementUnit.class, subActivityDetails.getMeasurementUnit().getId()));
        subActivity.setResponsePcu(em.find(ResponsePcu.class, subActivityDetails.getResponsePcu().getId()));
        subActivity.setSubActivityName(em.find(SubActivityName.class, subActivityDetails.getSubActivityName().getId()));

        try {
            em.persist(subActivity);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return subActivity.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<SubActivityDetails> retrieveSubActivities() throws MilesException {
        List<SubActivity> subActivity = new ArrayList<>();
        q = em.createNamedQuery("SubActivity.findAll");
        try {
            subActivity = q.getResultList();
        } catch (Exception e) {
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivity);
    }

    @Override
    public SubActivityDetails retrieveSubActivity(int id) throws MilesException {
        SubActivity subActivity;
        q = em.createNamedQuery("SubActivity.findById");
        q.setParameter("id", id);
        try {
            subActivity = (SubActivity) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivityToSubActivityDetails(subActivity);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editSubActivity(SubActivityDetails subActivityDetails) throws MilesException {

        if (subActivityDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        } else if (subActivityDetails.getId() == null) {
            throw new InvalidArgumentException("error_017_06");
        } else if (subActivityDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_017_02");
        } else if (subActivityDetails.getImplementingPartner() == null) {
            throw new InvalidArgumentException("error_017_03");
        } else if (subActivityDetails.getPerformanceIndicator() == null) {
            throw new InvalidArgumentException("error_017_04");
        } else if (subActivityDetails.getActivityName() == null) {
            throw new InvalidArgumentException("error_017_05");
        }

        SubActivity subActivity = em.find(SubActivity.class, subActivityDetails.getId());
        subActivity.setId(subActivityDetails.getId());
        subActivity.setAnnualWorkplanReferenceCode(subActivityDetails.getAnnualWorkplanReferenceCode());
        subActivity.setStartDate(subActivityDetails.getEndDate());
        subActivity.setUnitCost(subActivityDetails.getUnitCost());
        subActivity.setAwpbTarget(subActivityDetails.getAwpbTarget());
        subActivity.setProgrammeTarget(subActivityDetails.getProgrammeTarget());
        subActivity.setTotals(subActivityDetails.getTotals());
        subActivity.setProcurementPlan(subActivityDetails.getProcurementPlan());
        subActivity.setDescription(subActivityDetails.getDescription());
        subActivity.setValueAchieved(subActivityDetails.getValueAchieved());
        subActivity.setAllocatedBudget(subActivityDetails.getAllocatedBudget());
        subActivity.setGokPercentage(subActivityDetails.getGokPercentage());
        subActivity.setIfadLoanPercentage(subActivityDetails.getIfadLoanPercentage());
        subActivity.setIfadGrantPercentage(subActivityDetails.getIfadGrantPercentage());
        subActivity.setBeneficiariesPercentage(subActivityDetails.getBeneficiariesPercentage());
        subActivity.setEuPercentage(subActivityDetails.getEuPercentage());
        subActivity.setFinancialInstitutionPercentage(subActivityDetails.getFinancialInstitutionPercentage());
        subActivity.setActivityName(em.find(ActivityName.class, subActivityDetails.getActivityName().getId()));
        subActivity.setExpenditureCategory(em.find(ExpenditureCategory.class, subActivityDetails.getExpenditureCategory().getId()));
        subActivity.setPerformanceIndicator(em.find(PerformanceIndicator.class, subActivityDetails.getPerformanceIndicator().getId()));
        subActivity.setComponent(em.find(Component.class, subActivityDetails.getComponent().getId()));
        if (subActivityDetails.getSubComponent() != null) {
            subActivity.setSubComponent(em.find(SubComponent.class, subActivityDetails.getSubComponent().getId()));
        }
        subActivity.setImplementingPartner(em.find(ImplementingPartner.class, subActivityDetails.getImplementingPartner().getId()));
        subActivity.setMeasurementUnit(em.find(MeasurementUnit.class, subActivityDetails.getMeasurementUnit().getId()));
        subActivity.setResponsePcu(em.find(ResponsePcu.class, subActivityDetails.getResponsePcu().getId()));
        subActivity.setSubActivityName(em.find(SubActivityName.class, subActivityDetails.getSubActivityName().getId()));

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
    public void removeSubActivity(int id) throws MilesException {
        SubActivity subActivity = em.find(SubActivity.class, id);
        try {
            em.remove(subActivity);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public SubActivityDetails convertSubActivityToSubActivityDetails(SubActivity subActivity) {

        SubActivityDetails subActivityDetails = new SubActivityDetails(subActivity.getId());
        subActivityDetails.setAnnualWorkplanReferenceCode(subActivity.getAnnualWorkplanReferenceCode());
        subActivity.setStartDate(subActivityDetails.getStartDate());
        subActivity.setEndDate(subActivityDetails.getEndDate());
        subActivityDetails.setUnitCost(subActivity.getUnitCost());
        subActivityDetails.setAwpbTarget(subActivity.getAwpbTarget());
        subActivityDetails.setProgrammeTarget(subActivity.getProgrammeTarget());
        subActivityDetails.setTotals(subActivity.getTotals());
        subActivityDetails.setProcurementPlan(subActivity.getProcurementPlan());
        subActivityDetails.setDescription(subActivity.getDescription());
        subActivityDetails.setValueAchieved(subActivity.getValueAchieved());
        subActivityDetails.setAllocatedBudget(subActivity.getAllocatedBudget());
        subActivityDetails.setGokPercentage(subActivity.getGokPercentage());
        subActivityDetails.setIfadLoanPercentage(subActivity.getIfadLoanPercentage());
        subActivityDetails.setIfadGrantPercentage(subActivity.getIfadGrantPercentage());
        subActivityDetails.setBeneficiariesPercentage(subActivity.getBeneficiariesPercentage());
        subActivityDetails.setEuPercentage(subActivity.getEuPercentage());
        subActivityDetails.setFinancialInstitutionPercentage(subActivity.getFinancialInstitutionPercentage());
        subActivityDetails.setActivityName(activityService.
                convertActivityNameToActivityNameDetails(subActivity.getActivityName()));
        subActivityDetails.setMeasurementUnit(measurementUnitService.
                convertMeasurementUnitToMeasurementUnitDetails(subActivity.getMeasurementUnit()));
        subActivityDetails.setResponsePcu(responsePcuService.
                convertResponsePcuToResponsePcuDetails(subActivity.getResponsePcu()));
        subActivityDetails.setSubActivityName(subActivityDecriptionService.
                convertSubActivityNameToSubActivityNameDetails(subActivity.getSubActivityName()));
        subActivityDetails.setExpenditureCategory(expenditureCategoryService.
                convertExpenditureCategoryToExpenditureCategoryDetails(subActivity.getExpenditureCategory()));
        subActivityDetails.setAnnualWorkplanReferenceCode(subActivity.getAnnualWorkplanReferenceCode());
        subActivityDetails.setPerformanceIndicator(performanceIndicatortService.
                convertPerformanceIndicatorToPerformanceIndicatorDetails(subActivity.getPerformanceIndicator()));
        subActivityDetails.setComponent(componentService.
                convertComponentToComponentDetails(subActivity.getComponent()));
        subActivityDetails.setImplementingPartner(implementingPartnerService.
                convertImplementingPartnerToImplementingPartnerDetails(subActivity.getImplementingPartner()));
        if (subActivity.getSubComponent() != null) {
            subActivityDetails.setSubComponent(subComponentService.
                    convertSubComponentToSubComponentDetails(subActivity.getSubComponent()));
        }

        return subActivityDetails;

    }

    private List<SubActivityDetails> convertSubActivitiesToSubActivityDetailsList(List<SubActivity> subActivityList) {

        List<SubActivityDetails> subActivityDetailsList = new ArrayList<>();
        for (SubActivity subActivity : subActivityList) {
            subActivityDetailsList.add(convertSubActivityToSubActivityDetails(subActivity));
        }

        return subActivityDetailsList;

    }

//</editor-fold>
    @EJB
    private ActivityNameRequestsLocal activityService;
    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private ResponsePcuRequestsLocal responsePcuService;
    @EJB
    private SubComponentRequestsLocal subComponentService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private ImplementingPartnerRequestsLocal implementingPartnerService;
    @EJB
    private ExpenditureCategoryRequestsLocal expenditureCategoryService;
    @EJB
    private SubActivityNameRequestsLocal subActivityDecriptionService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatortService;
}
