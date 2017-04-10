/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.sub;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ActivityName;
import ke.co.miles.kcep.mis.entities.County;
import ke.co.miles.kcep.mis.entities.FinancialYear;
import ke.co.miles.kcep.mis.entities.MeasurementUnit;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.Region;
import ke.co.miles.kcep.mis.entities.SubActivity;
import ke.co.miles.kcep.mis.entities.SubActivityName;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.ActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.sub.SubActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.financialyear.FinancialYearRequestsLocal;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.AwpbOwnerDetail;
import ke.co.miles.kcep.mis.utilities.FinancialPlanDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.RegionDetail;
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
        }

        SubActivity subActivity = new SubActivity();
        subActivity.setAnnualWorkplanReferenceCode(subActivityDetails.getAnnualWorkplanReferenceCode());
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
        if (subActivityDetails.getAwpbOwner() != null) {
            subActivity.setAwpbOwner(em.getReference(Phenomenon.class, subActivityDetails.getAwpbOwner().getId()));
        }
        if (subActivityDetails.getComponent() != null) {
            subActivity.setComponent(em.getReference(Phenomenon.class, subActivityDetails.getComponent().getId()));
        }
        if (subActivityDetails.getImplementingPartner() != null) {
            subActivity.setImplementingPartner(em.getReference(Phenomenon.class, subActivityDetails.getImplementingPartner().getId()));
        }
        if (subActivityDetails.getActivityName() != null) {
            subActivity.setActivityName(em.getReference(ActivityName.class, subActivityDetails.getActivityName().getId()));
        }
        if (subActivityDetails.getExpectedOutcome() != null) {
            subActivity.setExpectedOutcome(em.getReference(Phenomenon.class, subActivityDetails.getExpectedOutcome().getId()));
        }
        if (subActivityDetails.getSubComponent() != null) {
            subActivity.setSubComponent(em.getReference(Phenomenon.class, subActivityDetails.getSubComponent().getId()));
        }
        if (subActivityDetails.getFinancialYear() != null) {
            subActivity.setFinancialYear(em.getReference(FinancialYear.class, subActivityDetails.getFinancialYear().getId()));
        } else {
            try {
                setQ(em.createNamedQuery("FinancialYear.findByCurrentYear"));
                q.setParameter("currentYear", Boolean.TRUE);
                subActivity.setFinancialYear(em.getReference(FinancialYear.class, ((FinancialYear) q.getSingleResult()).getId()));
            } catch (Exception e) {
            }
        }
        try {
            subActivity.setSubActivityName(em.getReference(SubActivityName.class, subActivityDetails.getSubActivityName().getId()));
        } catch (Exception e) {
        }
        if (subActivityDetails.getExpenditureCategory() != null) {
            subActivity.setExpenditureCategory(em.getReference(Phenomenon.class, subActivityDetails.getExpenditureCategory().getId()));
        }
        if (subActivityDetails.getGfssCode() != null) {
            subActivity.setGfssCode(em.getReference(Phenomenon.class, subActivityDetails.getGfssCode().getId()));
        }
        if (subActivityDetails.getMeasurementUnit() != null) {
            subActivity.setMeasurementUnit(em.getReference(MeasurementUnit.class, subActivityDetails.getMeasurementUnit().getId()));
        }
        if (subActivityDetails.getResponsePcu() != null) {
            subActivity.setResponsePcu(em.getReference(Phenomenon.class, subActivityDetails.getResponsePcu().getId()));
        }
        if (subActivityDetails.getCounty() != null) {
            subActivity.setCounty(em.getReference(County.class, subActivityDetails.getCounty().getId()));
        }
        if (subActivityDetails.getRegion() != null) {
            subActivity.setRegion(em.getReference(Region.class, subActivityDetails.getRegion().getId()));
        }

        try {
            em.persist(subActivity);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return subActivity.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<String> retrieveReferenceCodes() throws MilesException {
        List<String> referenceCodes = new ArrayList<>();
        setQ(em.createNamedQuery("SubActivity.findReferenceCodes"));
        try {
            referenceCodes = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return referenceCodes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SubActivityDetails> retrieveHeadOrPartnerSubActivities(AwpbOwnerDetail awpbOwnerId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        setQ(em.createNamedQuery("SubActivity.findByAwpbOwnerId"));
        q.setParameter("awpbOwnerId", awpbOwnerId.getId());
        try {
            subActivities = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivities);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SubActivityDetails> retrieveCountySubActivities(short countyId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        setQ(em.createNamedQuery("SubActivity.findCountySubActivities"));
        q.setParameter("countyId", countyId);
        try {
            subActivities = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivities);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SubActivityDetails> retrieveRegionSubActivities(short regionId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        setQ(em.createNamedQuery("SubActivity.findRegionSubActivities"));
        q.setParameter("regionId", regionId);
        try {
            subActivities = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivities);
    }

    @SuppressWarnings("unchecked")
    private List<SubActivityDetails> retrieveSubActivitiesOfCategory(PhenomenonDetails expenditureCategoryDetails, short financialYearId, Short regionId, Short countyId, AwpbOwnerDetail awpbOwner) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        if (countyId == null && regionId == null) {
            setQ(em.createNamedQuery("SubActivity.findByExpenditureCategoryIdAndFinancialYearId"));
            q.setParameter("expenditureCategoryId", expenditureCategoryDetails.getId());
            q.setParameter("financialYearId", financialYearId);
            q.setParameter("awpbOwnerId", awpbOwner.getId());
        } else if (countyId != null) {
            setQ(em.createNamedQuery("SubActivity.findOfCountyByExpenditureCategoryIdAndFinancialYearId"));
            q.setParameter("expenditureCategoryId", expenditureCategoryDetails.getId());
            q.setParameter("financialYearId", financialYearId);
            q.setParameter("countyId", countyId);
        } else if (regionId != null) {
            setQ(em.createNamedQuery("SubActivity.findOfRegionByExpenditureCategoryIdAndFinancialYearId"));
            q.setParameter("expenditureCategoryId", expenditureCategoryDetails.getId());
            q.setParameter("financialYearId", financialYearId);
            q.setParameter("regionId", regionId);
        }
        try {
            subActivities = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivities);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SubActivityDetails> retrieveSubActivities(short financialYearId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        setQ(em.createNamedQuery("SubActivity.findByFinancialYearId"));
        q.setParameter("financialYearId", financialYearId);
        try {
            subActivities = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivities);
    }

    @SuppressWarnings("unchecked")
    private List<SubActivityDetails> retrieveSubActivitiesOfComponent(PhenomenonDetails componentDetails, short financialYearId, Short regionId, Short countyId, AwpbOwnerDetail awpbOwner) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        if (countyId == null && regionId == null) {
            setQ(em.createNamedQuery("SubActivity.findByComponentIdAndFinancialYearId"));
            q.setParameter("componentId", componentDetails.getId());
            q.setParameter("financialYearId", financialYearId);
            q.setParameter("awpbOwnerId", awpbOwner.getId());
        } else if (countyId != null) {
            setQ(em.createNamedQuery("SubActivity.findOfCountyByComponentIdAndFinancialYearId"));
            q.setParameter("componentId", componentDetails.getId());
            q.setParameter("financialYearId", financialYearId);
            q.setParameter("countyId", countyId);
        } else if (regionId != null) {
            setQ(em.createNamedQuery("SubActivity.findOfRegionByComponentIdAndFinancialYearId"));
            q.setParameter("componentId", componentDetails.getId());
            q.setParameter("financialYearId", financialYearId);
            q.setParameter("regionId", regionId);
        }
        try {
            subActivities = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivities);
    }

    @Override
    public SubActivityDetails retrieveSubActivity(int id) throws MilesException {
        SubActivity subActivity;
        setQ(em.createNamedQuery("SubActivity.findById"));
        q.setParameter("id", id);
        try {
            subActivity = (SubActivity) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivityToSubActivityDetails(subActivity);
    }

    @SuppressWarnings("unchecked")
    private Map<PhenomenonDetails, List<SubActivityDetails>>
            retrieveExpenditureCategoriesMap(short financialYearId, Short regionId, Short countyId, AwpbOwnerDetail awpbOwner) throws MilesException {
        Map<PhenomenonDetails, List<SubActivityDetails>> expenditureCategoriesMap = new HashMap<>();
        List<PhenomenonDetails> expenditureCategoryDetailsList;

        expenditureCategoryDetailsList = phenomenonService.retrieveExpenditureCategories();
        for (PhenomenonDetails expenditureCategoryDetails : expenditureCategoryDetailsList) {
            expenditureCategoriesMap.put(expenditureCategoryDetails,
                    retrieveSubActivitiesOfCategory(expenditureCategoryDetails, financialYearId, regionId, countyId, awpbOwner));
        }

        return expenditureCategoriesMap;
    }

    @SuppressWarnings("unchecked")
    private Map<PhenomenonDetails, List<SubActivityDetails>> retrieveComponentsMap(short financialYearId, Short regionId, Short countyId, AwpbOwnerDetail awpbOwner) throws MilesException {

        Map<PhenomenonDetails, List<SubActivityDetails>> componentsMap = new HashMap<>();
        List<PhenomenonDetails> componentDetailsList;
        componentDetailsList = phenomenonService.retrieveComponents();
        for (PhenomenonDetails componentDetails : componentDetailsList) {
            componentsMap.put(componentDetails,
                    retrieveSubActivitiesOfComponent(componentDetails, financialYearId, regionId, countyId, awpbOwner));
        }

        return componentsMap;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editSubActivity(SubActivityDetails subActivityDetails) throws MilesException {

        if (subActivityDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        } else if (subActivityDetails.getId() == null) {
            throw new InvalidArgumentException("error_017_06");
        }

        SubActivity subActivity = em.find(SubActivity.class, subActivityDetails.getId());
        subActivity.setId(subActivityDetails.getId());
        subActivity.setAnnualWorkplanReferenceCode(subActivityDetails.getAnnualWorkplanReferenceCode());
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
        if (subActivityDetails.getAwpbOwner() != null) {
            subActivity.setAwpbOwner(em.getReference(Phenomenon.class, subActivityDetails.getAwpbOwner().getId()));
        }
        if (subActivityDetails.getComponent() != null) {
            subActivity.setComponent(em.getReference(Phenomenon.class, subActivityDetails.getComponent().getId()));
        }
        if (subActivityDetails.getImplementingPartner() != null) {
            subActivity.setImplementingPartner(em.getReference(Phenomenon.class, subActivityDetails.getImplementingPartner().getId()));
        }
        if (subActivityDetails.getActivityName() != null) {
            subActivity.setActivityName(em.getReference(ActivityName.class, subActivityDetails.getActivityName().getId()));
        }
        if (subActivityDetails.getExpectedOutcome() != null) {
            subActivity.setExpectedOutcome(em.getReference(Phenomenon.class, subActivityDetails.getExpectedOutcome().getId()));
        }
        if (subActivityDetails.getSubComponent() != null) {
            subActivity.setSubComponent(em.getReference(Phenomenon.class, subActivityDetails.getSubComponent().getId()));
        }
        if (subActivityDetails.getFinancialYear() != null) {
            subActivity.setFinancialYear(em.getReference(FinancialYear.class, subActivityDetails.getFinancialYear().getId()));
        } else {
            try {
                setQ(em.createNamedQuery("FinancialYear.findByCurrentYear"));
                q.setParameter("currentYear", Boolean.TRUE);
                subActivity.setFinancialYear(em.getReference(FinancialYear.class, ((FinancialYear) q.getSingleResult()).getId()));
            } catch (Exception e) {
            }
        }
        try {
            subActivity.setSubActivityName(em.getReference(SubActivityName.class, subActivityDetails.getSubActivityName().getId()));
        } catch (Exception e) {
        }
        if (subActivityDetails.getExpenditureCategory() != null) {
            subActivity.setExpenditureCategory(em.getReference(Phenomenon.class, subActivityDetails.getExpenditureCategory().getId()));
        }
        if (subActivityDetails.getGfssCode() != null) {
            subActivity.setGfssCode(em.getReference(Phenomenon.class, subActivityDetails.getGfssCode().getId()));
        }
        if (subActivityDetails.getMeasurementUnit() != null) {
            subActivity.setMeasurementUnit(em.getReference(MeasurementUnit.class, subActivityDetails.getMeasurementUnit().getId()));
        }
        if (subActivityDetails.getResponsePcu() != null) {
            subActivity.setResponsePcu(em.getReference(Phenomenon.class, subActivityDetails.getResponsePcu().getId()));
        }
        if (subActivityDetails.getCounty() != null) {
            subActivity.setCounty(em.getReference(County.class, subActivityDetails.getCounty().getId()));
        }
        if (subActivityDetails.getRegion() != null) {
            subActivity.setRegion(em.getReference(Region.class, subActivityDetails.getRegion().getId()));
        }

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
        subActivityDetails.setStartDate(subActivity.getStartDate());
        subActivityDetails.setEndDate(subActivity.getEndDate());
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
        subActivityDetails.setAnnualWorkplanReferenceCode(subActivity.getAnnualWorkplanReferenceCode());
        if (subActivity.getActivityName() != null) {
            subActivityDetails.setActivityName(activityService.
                    convertActivityNameToActivityNameDetails(subActivity.getActivityName()));
        }
        if (subActivity.getExpectedOutcome() != null) {
            subActivityDetails.setExpectedOutcome(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(subActivity.getExpectedOutcome()));
        }
        if (subActivity.getMeasurementUnit() != null) {
            subActivityDetails.setMeasurementUnit(measurementUnitService.
                    convertMeasurementUnitToMeasurementUnitDetails(subActivity.getMeasurementUnit()));
        }
        if (subActivity.getResponsePcu() != null) {
            subActivityDetails.setResponsePcu(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(subActivity.getResponsePcu()));
        }
        if (subActivity.getAnnualIndicator() != null) {
            subActivityDetails.setAnnualIndicator(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(subActivity.getAnnualIndicator()));
        }
        if (subActivity.getSubActivityName() != null) {
            subActivityDetails.setSubActivityName(subActivityDecriptionService.
                    convertSubActivityNameToSubActivityNameDetails(subActivity.getSubActivityName()));
        }
        if (subActivity.getExpenditureCategory() != null) {
            subActivityDetails.setExpenditureCategory(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(subActivity.getExpenditureCategory()));
        }
        if (subActivity.getComponent() != null) {
            subActivityDetails.setComponent(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(subActivity.getComponent()));
        }
        if (subActivity.getImplementingPartner() != null) {
            subActivityDetails.setImplementingPartner(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(subActivity.getImplementingPartner()));
        }
        if (subActivity.getAwpbOwner() != null) {
            subActivityDetails.setAwpbOwner(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(subActivity.getAwpbOwner()));
        }
        if (subActivity.getSubComponent() != null) {
            subActivityDetails.setSubComponent(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(subActivity.getSubComponent()));
        }
        if (subActivity.getFinancialYear() != null) {
            subActivityDetails.setFinancialYear(financialYearService.
                    convertFinancialYearToFinancialYearDetails(subActivity.getFinancialYear()));
        }
        if (subActivity.getGfssCode() != null) {
            subActivityDetails.setGfssCode(phenomenonService.convertPhenomenonToPhenomenonDetails(subActivity.getGfssCode()));
        }
        if (subActivity.getCounty() != null) {
            subActivityDetails.setCounty(countyService.convertCountyToCountyDetails(subActivity.getCounty()));
        }
        if (subActivity.getRegion() != null) {
            subActivityDetails.setRegion(RegionDetail.getRegionDetail(subActivity.getRegion().getId()));
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
//<editor-fold defaultstate="collapsed" desc="Summarize">
    @Override
    public Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>> summarizeFinancialPlanByCategories(short financialYearId, Short regionId, Short countyId, AwpbOwnerDetail awpbOwner) throws MilesException {

        Map<PhenomenonDetails, List<SubActivityDetails>> expenditureCategoriesMap = retrieveExpenditureCategoriesMap(financialYearId, regionId, countyId, awpbOwner);
        Map<PhenomenonDetails, FinancialPlanDetails> categoryToFinancialPlansMap = new HashMap<>();
        Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>> totalsToCategoryToFinancialPlansMap = new HashMap<>();
        FinancialPlanDetails financialPlanDetails;
        FinancialPlanDetails financialPlanTotals = new FinancialPlanDetails();

        for (PhenomenonDetails expenditureCategory : expenditureCategoriesMap.keySet()) {
            financialPlanDetails = new FinancialPlanDetails();

            for (SubActivityDetails subActivity : expenditureCategoriesMap.get(expenditureCategory)) {

                try {
                    if (financialPlanDetails.getGokValue() == null) {
                        financialPlanDetails.setGokValue(new BigDecimal(((subActivity.getGokPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setGokValue(financialPlanDetails.getGokValue().add(
                                new BigDecimal(((subActivity.getGokPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getBeneficiariesValue() == null) {
                        financialPlanDetails.setBeneficiariesValue(new BigDecimal(((subActivity.getBeneficiariesPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setBeneficiariesValue(financialPlanDetails.getBeneficiariesValue().add(
                                new BigDecimal(((subActivity.getBeneficiariesPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getIfadLoanValue() == null) {
                        financialPlanDetails.setIfadLoanValue(new BigDecimal(((subActivity.getIfadLoanPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setIfadLoanValue(financialPlanDetails.getIfadLoanValue().add(
                                new BigDecimal(((subActivity.getIfadLoanPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getEuValue() == null) {
                        financialPlanDetails.setEuValue(new BigDecimal(((subActivity.getEuPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setEuValue(financialPlanDetails.getEuValue().add(
                                new BigDecimal(((subActivity.getEuPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getIfadGrantValue() == null) {
                        financialPlanDetails.setIfadGrantValue(new BigDecimal(((subActivity.getIfadGrantPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setIfadGrantValue(financialPlanDetails.getIfadGrantValue().add(
                                new BigDecimal(((subActivity.getIfadGrantPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getFinancialInstitutionValue() == null) {
                        financialPlanDetails.setFinancialInstitutionValue(new BigDecimal(((subActivity.getFinancialInstitutionPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setFinancialInstitutionValue(financialPlanDetails.getFinancialInstitutionValue().add(
                                new BigDecimal(((subActivity.getFinancialInstitutionPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getTotalsValue() == null) {
                        financialPlanDetails.setTotalsValue(subActivity.getTotals());
                    } else {
                        financialPlanDetails.setTotalsValue(financialPlanDetails.getTotalsValue().add(subActivity.getTotals()));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getTotalInitialAllocationValue() == null) {
                        financialPlanDetails.setTotalInitialAllocationValue(subActivity.getAllocatedBudget());
                    } else {
                        financialPlanDetails.setTotalInitialAllocationValue(financialPlanDetails.getTotalInitialAllocationValue().add(subActivity.getAllocatedBudget()));
                    }
                } catch (Exception e) {
                }
            }

            try {
                if (financialPlanTotals.getTotalInitialAllocationValue() != null && financialPlanDetails.getTotalInitialAllocationValue() != null) {
                    financialPlanTotals.setTotalInitialAllocationValue(financialPlanTotals.getTotalInitialAllocationValue().add(financialPlanDetails.getTotalInitialAllocationValue()));
                } else if (financialPlanTotals.getTotalInitialAllocationValue() == null && financialPlanDetails.getTotalInitialAllocationValue() != null) {
                    financialPlanTotals.setTotalInitialAllocationValue(financialPlanDetails.getTotalInitialAllocationValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getGokValue() != null && financialPlanDetails.getGokValue() != null) {
                    financialPlanTotals.setGokValue(financialPlanTotals.getGokValue().add(financialPlanDetails.getGokValue()));
                } else if (financialPlanTotals.getGokValue() == null && financialPlanDetails.getGokValue() != null) {
                    financialPlanTotals.setGokValue(financialPlanDetails.getGokValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getBeneficiariesValue() != null && financialPlanDetails.getBeneficiariesValue() != null) {
                    financialPlanTotals.setBeneficiariesValue(financialPlanTotals.getBeneficiariesValue().add(financialPlanDetails.getBeneficiariesValue()));
                } else if (financialPlanTotals.getBeneficiariesValue() == null && financialPlanDetails.getBeneficiariesValue() != null) {
                    financialPlanTotals.setBeneficiariesValue(financialPlanDetails.getBeneficiariesValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getIfadLoanValue() != null && financialPlanDetails.getIfadLoanValue() != null) {
                    financialPlanTotals.setIfadLoanValue(financialPlanTotals.getIfadLoanValue().add(financialPlanDetails.getIfadLoanValue()));
                } else if (financialPlanTotals.getIfadLoanValue() == null && financialPlanDetails.getIfadLoanValue() != null) {
                    financialPlanTotals.setIfadLoanValue(financialPlanDetails.getIfadLoanValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getEuValue() != null && financialPlanDetails.getEuValue() != null) {
                    financialPlanTotals.setEuValue(financialPlanTotals.getEuValue().add(financialPlanDetails.getEuValue()));
                } else if (financialPlanTotals.getEuValue() == null && financialPlanDetails.getEuValue() != null) {
                    financialPlanTotals.setEuValue(financialPlanDetails.getEuValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getIfadGrantValue() != null && financialPlanDetails.getIfadGrantValue() != null) {
                    financialPlanTotals.setIfadGrantValue(financialPlanTotals.getIfadGrantValue().add(financialPlanDetails.getIfadGrantValue()));
                } else if (financialPlanTotals.getIfadGrantValue() == null && financialPlanDetails.getIfadGrantValue() != null) {
                    financialPlanTotals.setIfadGrantValue(financialPlanDetails.getIfadGrantValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getFinancialInstitutionValue() != null && financialPlanDetails.getFinancialInstitutionValue() != null) {
                    financialPlanTotals.setFinancialInstitutionValue(financialPlanTotals.getFinancialInstitutionValue().add(financialPlanDetails.getFinancialInstitutionValue()));
                } else if (financialPlanTotals.getFinancialInstitutionValue() == null && financialPlanDetails.getFinancialInstitutionValue() != null) {
                    financialPlanTotals.setFinancialInstitutionValue(financialPlanDetails.getFinancialInstitutionValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getTotalsValue() != null && financialPlanDetails.getTotalsValue() != null) {
                    financialPlanTotals.setTotalsValue(financialPlanTotals.getTotalsValue().add(financialPlanDetails.getTotalsValue()));
                } else if (financialPlanTotals.getTotalsValue() == null && financialPlanDetails.getTotalsValue() != null) {
                    financialPlanTotals.setTotalsValue(financialPlanDetails.getTotalsValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getBalanceValue() != null && financialPlanDetails.getBalanceValue() != null) {
                    financialPlanTotals.setBalanceValue(financialPlanTotals.getBalanceValue().add(financialPlanDetails.getBalanceValue()));
                } else if (financialPlanTotals.getBalanceValue() == null && financialPlanDetails.getBalanceValue() != null) {
                    financialPlanTotals.setBalanceValue(financialPlanDetails.getBalanceValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                financialPlanDetails.setBalanceValue(financialPlanDetails.getTotalInitialAllocationValue().subtract(financialPlanDetails.getTotalsValue()));
            } catch (Exception e) {
            }

            //Rounding off
            try {
                financialPlanDetails.setBalanceValue(financialPlanDetails.getBalanceValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setGokValue(financialPlanDetails.getGokValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setBeneficiariesValue(financialPlanDetails.getBeneficiariesValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setIfadLoanValue(financialPlanDetails.getIfadLoanValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setEuValue(financialPlanDetails.getEuValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setIfadGrantValue(financialPlanDetails.getIfadGrantValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setFinancialInstitutionValue(financialPlanDetails.getFinancialInstitutionValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }

            //Calaculating percentages
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setTotalInitialAllocationPercentage(financialPlanDetails.getTotalInitialAllocationValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setTotalsPercentage(financialPlanDetails.getTotalsValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setBalancePercentage(financialPlanDetails.getBalanceValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setGokPercentage(financialPlanDetails.getGokValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setBeneficiariesPercentage(financialPlanDetails.getBeneficiariesValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setIfadLoanPercentage(financialPlanDetails.getIfadLoanValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setEuPercentage(financialPlanDetails.getEuValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setIfadGrantPercentage(financialPlanDetails.getIfadGrantValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setFinancialInstitutionPercentage(financialPlanDetails.getFinancialInstitutionValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }

            categoryToFinancialPlansMap.put(expenditureCategory, financialPlanDetails);
        }

        try {
            financialPlanTotals.setBalanceValue(financialPlanTotals.getTotalInitialAllocationValue().subtract(financialPlanTotals.getTotalsValue()));
        } catch (Exception e) {
        }

        try {
            financialPlanTotals.setBalanceValue(financialPlanTotals.getBalanceValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setGokValue(financialPlanTotals.getGokValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setBeneficiariesValue(financialPlanTotals.getBeneficiariesValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setIfadLoanValue(financialPlanTotals.getIfadLoanValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setEuValue(financialPlanTotals.getEuValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setIfadGrantValue(financialPlanTotals.getIfadGrantValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setFinancialInstitutionValue(financialPlanTotals.getFinancialInstitutionValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }

        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setTotalInitialAllocationPercentage(financialPlanTotals.getTotalInitialAllocationValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setTotalsPercentage(financialPlanTotals.getTotalsValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setBalancePercentage(financialPlanTotals.getBalanceValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setGokPercentage(financialPlanTotals.getGokValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setBeneficiariesPercentage(financialPlanTotals.getBeneficiariesValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setIfadLoanPercentage(financialPlanTotals.getIfadLoanValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setEuPercentage(financialPlanTotals.getEuValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setIfadGrantPercentage(financialPlanTotals.getIfadGrantValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setFinancialInstitutionPercentage(financialPlanTotals.getFinancialInstitutionValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }

        totalsToCategoryToFinancialPlansMap.put(financialPlanTotals, categoryToFinancialPlansMap);

        return totalsToCategoryToFinancialPlansMap;
    }

    @Override
    public Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>>
            summarizeFinancialPlanByComponents(short financialYearId, Short regionId, Short countyId, AwpbOwnerDetail awpbOwner) throws MilesException {

        Map<PhenomenonDetails, List<SubActivityDetails>> componentsMap = retrieveComponentsMap(financialYearId, regionId, countyId, awpbOwner);
        Map<PhenomenonDetails, FinancialPlanDetails> componentToFinancialPlansMap = new HashMap<>();
        Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>> totalsToComponentToFinancialPlansMap = new HashMap<>();
        FinancialPlanDetails financialPlanDetails;
        FinancialPlanDetails financialPlanTotals = new FinancialPlanDetails();

        for (PhenomenonDetails component : componentsMap.keySet()) {
            financialPlanDetails = new FinancialPlanDetails();

            for (SubActivityDetails subActivity : componentsMap.get(component)) {

                try {
                    if (financialPlanDetails.getGokValue() == null) {
                        financialPlanDetails.setGokValue(new BigDecimal(((subActivity.getGokPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setGokValue(financialPlanDetails.getGokValue().add(
                                new BigDecimal(((subActivity.getGokPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getBeneficiariesValue() == null) {
                        financialPlanDetails.setBeneficiariesValue(new BigDecimal(((subActivity.getBeneficiariesPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setBeneficiariesValue(financialPlanDetails.getBeneficiariesValue().add(
                                new BigDecimal(((subActivity.getBeneficiariesPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getIfadLoanValue() == null) {
                        financialPlanDetails.setIfadLoanValue(new BigDecimal(((subActivity.getIfadLoanPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setIfadLoanValue(financialPlanDetails.getIfadLoanValue().add(
                                new BigDecimal(((subActivity.getIfadLoanPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getEuValue() == null) {
                        financialPlanDetails.setEuValue(new BigDecimal(((subActivity.getEuPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setEuValue(financialPlanDetails.getEuValue().add(
                                new BigDecimal(((subActivity.getEuPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getIfadGrantValue() == null) {
                        financialPlanDetails.setIfadGrantValue(new BigDecimal(((subActivity.getIfadGrantPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setIfadGrantValue(financialPlanDetails.getIfadGrantValue().add(
                                new BigDecimal(((subActivity.getIfadGrantPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getFinancialInstitutionValue() == null) {
                        financialPlanDetails.setFinancialInstitutionValue(new BigDecimal(((subActivity.getFinancialInstitutionPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget()));
                    } else {
                        financialPlanDetails.setFinancialInstitutionValue(financialPlanDetails.getFinancialInstitutionValue().add(
                                new BigDecimal(((subActivity.getFinancialInstitutionPercentage()) / 100.0)).multiply(subActivity.getAllocatedBudget())));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getTotalsValue() == null) {
                        financialPlanDetails.setTotalsValue(subActivity.getTotals());
                    } else {
                        financialPlanDetails.setTotalsValue(financialPlanDetails.getTotalsValue().add(subActivity.getTotals()));
                    }
                } catch (Exception e) {
                }

                try {
                    if (financialPlanDetails.getTotalInitialAllocationValue() == null) {
                        financialPlanDetails.setTotalInitialAllocationValue(subActivity.getAllocatedBudget());
                    } else {
                        financialPlanDetails.setTotalInitialAllocationValue(financialPlanDetails.getTotalInitialAllocationValue().add(subActivity.getAllocatedBudget()));
                    }
                } catch (Exception e) {
                }
            }

            try {
                if (financialPlanTotals.getTotalInitialAllocationValue() != null && financialPlanDetails.getTotalInitialAllocationValue() != null) {
                    financialPlanTotals.setTotalInitialAllocationValue(financialPlanTotals.getTotalInitialAllocationValue().add(financialPlanDetails.getTotalInitialAllocationValue()));
                } else if (financialPlanTotals.getTotalInitialAllocationValue() == null && financialPlanDetails.getTotalInitialAllocationValue() != null) {
                    financialPlanTotals.setTotalInitialAllocationValue(financialPlanDetails.getTotalInitialAllocationValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getGokValue() != null && financialPlanDetails.getGokValue() != null) {
                    financialPlanTotals.setGokValue(financialPlanTotals.getGokValue().add(financialPlanDetails.getGokValue()));
                } else if (financialPlanTotals.getGokValue() == null && financialPlanDetails.getGokValue() != null) {
                    financialPlanTotals.setGokValue(financialPlanDetails.getGokValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getBeneficiariesValue() != null && financialPlanDetails.getBeneficiariesValue() != null) {
                    financialPlanTotals.setBeneficiariesValue(financialPlanTotals.getBeneficiariesValue().add(financialPlanDetails.getBeneficiariesValue()));
                } else if (financialPlanTotals.getBeneficiariesValue() == null && financialPlanDetails.getBeneficiariesValue() != null) {
                    financialPlanTotals.setBeneficiariesValue(financialPlanDetails.getBeneficiariesValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getIfadLoanValue() != null && financialPlanDetails.getIfadLoanValue() != null) {
                    financialPlanTotals.setIfadLoanValue(financialPlanTotals.getIfadLoanValue().add(financialPlanDetails.getIfadLoanValue()));
                } else if (financialPlanTotals.getIfadLoanValue() == null && financialPlanDetails.getIfadLoanValue() != null) {
                    financialPlanTotals.setIfadLoanValue(financialPlanDetails.getIfadLoanValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getEuValue() != null && financialPlanDetails.getEuValue() != null) {
                    financialPlanTotals.setEuValue(financialPlanTotals.getEuValue().add(financialPlanDetails.getEuValue()));
                } else if (financialPlanTotals.getEuValue() == null && financialPlanDetails.getEuValue() != null) {
                    financialPlanTotals.setEuValue(financialPlanDetails.getEuValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getIfadGrantValue() != null && financialPlanDetails.getIfadGrantValue() != null) {
                    financialPlanTotals.setIfadGrantValue(financialPlanTotals.getIfadGrantValue().add(financialPlanDetails.getIfadGrantValue()));
                } else if (financialPlanTotals.getIfadGrantValue() == null && financialPlanDetails.getIfadGrantValue() != null) {
                    financialPlanTotals.setIfadGrantValue(financialPlanDetails.getIfadGrantValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getFinancialInstitutionValue() != null && financialPlanDetails.getFinancialInstitutionValue() != null) {
                    financialPlanTotals.setFinancialInstitutionValue(financialPlanTotals.getFinancialInstitutionValue().add(financialPlanDetails.getFinancialInstitutionValue()));
                } else if (financialPlanTotals.getFinancialInstitutionValue() == null && financialPlanDetails.getFinancialInstitutionValue() != null) {
                    financialPlanTotals.setFinancialInstitutionValue(financialPlanDetails.getFinancialInstitutionValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getTotalsValue() != null && financialPlanDetails.getTotalsValue() != null) {
                    financialPlanTotals.setTotalsValue(financialPlanTotals.getTotalsValue().add(financialPlanDetails.getTotalsValue()));
                } else if (financialPlanTotals.getTotalsValue() == null && financialPlanDetails.getTotalsValue() != null) {
                    financialPlanTotals.setTotalsValue(financialPlanDetails.getTotalsValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getBalanceValue() != null && financialPlanDetails.getBalanceValue() != null) {
                    financialPlanTotals.setBalanceValue(financialPlanTotals.getBalanceValue().add(financialPlanDetails.getBalanceValue()));
                } else if (financialPlanTotals.getBalanceValue() == null && financialPlanDetails.getBalanceValue() != null) {
                    financialPlanTotals.setBalanceValue(financialPlanDetails.getBalanceValue());
                } else {
                }
            } catch (Exception e) {
            }

            try {
                financialPlanDetails.setBalanceValue(financialPlanDetails.getTotalInitialAllocationValue().subtract(financialPlanDetails.getTotalsValue()));
            } catch (Exception e) {
            }

            //Rounding off
            try {
                financialPlanDetails.setBalanceValue(financialPlanDetails.getBalanceValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setGokValue(financialPlanDetails.getGokValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setBeneficiariesValue(financialPlanDetails.getBeneficiariesValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setIfadLoanValue(financialPlanDetails.getIfadLoanValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setEuValue(financialPlanDetails.getEuValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setIfadGrantValue(financialPlanDetails.getIfadGrantValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setFinancialInstitutionValue(financialPlanDetails.getFinancialInstitutionValue().setScale(2, RoundingMode.HALF_EVEN));
            } catch (Exception e) {
            }

            //Calaculating percentages
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setTotalInitialAllocationPercentage(financialPlanDetails.getTotalInitialAllocationValue().divide(financialPlanDetails.getTotalInitialAllocationValue()).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setTotalsPercentage(financialPlanDetails.getTotalsValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setBalancePercentage(financialPlanDetails.getBalanceValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setGokPercentage(financialPlanDetails.getGokValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setBeneficiariesPercentage(financialPlanDetails.getBeneficiariesValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setIfadLoanPercentage(financialPlanDetails.getIfadLoanValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setEuPercentage(financialPlanDetails.getEuValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setIfadGrantPercentage(financialPlanDetails.getIfadGrantValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }
            try {
                if (financialPlanDetails.getTotalInitialAllocationValue() != null && !financialPlanDetails.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                    financialPlanDetails.setFinancialInstitutionPercentage(financialPlanDetails.getFinancialInstitutionValue().divide(financialPlanDetails.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
                }
            } catch (Exception e) {
            }

            componentToFinancialPlansMap.put(component, financialPlanDetails);
        }

        try {
            financialPlanTotals.setBalanceValue(financialPlanTotals.getTotalInitialAllocationValue().subtract(financialPlanTotals.getTotalsValue()));
        } catch (Exception e) {
        }

        try {
            financialPlanTotals.setBalanceValue(financialPlanTotals.getBalanceValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setGokValue(financialPlanTotals.getGokValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setBeneficiariesValue(financialPlanTotals.getBeneficiariesValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setIfadLoanValue(financialPlanTotals.getIfadLoanValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setEuValue(financialPlanTotals.getEuValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setIfadGrantValue(financialPlanTotals.getIfadGrantValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setFinancialInstitutionValue(financialPlanTotals.getFinancialInstitutionValue().setScale(2, RoundingMode.HALF_EVEN));
        } catch (Exception e) {
        }

        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setTotalInitialAllocationPercentage(financialPlanTotals.getTotalInitialAllocationValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setTotalsPercentage(financialPlanTotals.getTotalsValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setBalancePercentage(financialPlanTotals.getBalanceValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setGokPercentage(financialPlanTotals.getGokValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setBeneficiariesPercentage(financialPlanTotals.getBeneficiariesValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setIfadLoanPercentage(financialPlanTotals.getIfadLoanValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setEuPercentage(financialPlanTotals.getEuValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            if (financialPlanTotals.getTotalInitialAllocationValue() != null && !financialPlanTotals.getTotalInitialAllocationValue().equals(new BigDecimal("0.00"))) {
                financialPlanTotals.setIfadGrantPercentage(financialPlanTotals.getIfadGrantValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
            }
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setFinancialInstitutionPercentage(financialPlanTotals.getFinancialInstitutionValue().divide(financialPlanTotals.getTotalInitialAllocationValue(), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")));
        } catch (Exception e) {
        }

        totalsToComponentToFinancialPlansMap.put(financialPlanTotals, componentToFinancialPlansMap);

        return totalsToComponentToFinancialPlansMap;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="EJB injections">
    @EJB
    private CountyRequestsLocal countyService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;
    @EJB
    private ActivityNameRequestsLocal activityService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private SubActivityNameRequestsLocal subActivityDecriptionService;
    @EJB
    private FinancialYearRequestsLocal financialYearService;
//</editor-fold>

}
