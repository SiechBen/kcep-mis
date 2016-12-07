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
import ke.co.miles.debugger.MilesDebugger;
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
import ke.co.miles.kcep.mis.utilities.ComponentDetails;
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
    public List<SubActivityDetails> retrieveHeadSubActivities() throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        setQ(em.createNamedQuery("SubActivity.findHeadSubActivities"));
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
    private List<SubActivityDetails> retrieveSubActivities(PhenomenonDetails expenditureCategoryDetails, short financialYearId, Short countyId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        if (countyId == null) {
            setQ(em.createNamedQuery("SubActivity.findByExpenditureCategoryIdAndFinancialYearId"));
            q.setParameter("expenditureCategoryId", expenditureCategoryDetails.getId());
            q.setParameter("financialYearId", financialYearId);
        } else {
            setQ(em.createNamedQuery("SubActivity.findOfCountyByExpenditureCategoryIdAndFinancialYearId"));
            q.setParameter("expenditureCategoryId", expenditureCategoryDetails.getId());
            q.setParameter("financialYearId", financialYearId);
            q.setParameter("countyId", countyId);
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
    private List<SubActivityDetails> retrieveSubActivities(ComponentDetails component, short financialYearId, Short countyId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        if (countyId == null) {
            setQ(em.createNamedQuery("SubActivity.findByComponentIdAndFinancialYearId"));
            q.setParameter("componentId", component.getId());
            q.setParameter("financialYearId", financialYearId);
        } else {
            setQ(em.createNamedQuery("SubActivity.findOfCountyByComponentIdAndFinancialYearId"));
            q.setParameter("componentId", component.getId());
            q.setParameter("financialYearId", financialYearId);
            q.setParameter("countyId", countyId);
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
    private Map<PhenomenonDetails, List<SubActivityDetails>> retrieveExpenditureCategoriesMap(short financialYearId, Short countyId) throws MilesException {
        Map<PhenomenonDetails, List<SubActivityDetails>> expenditureCategoriesMap = new HashMap<>();
        List<PhenomenonDetails> expenditureCategoryDetailsList;

        expenditureCategoryDetailsList = phenomenonService.retrieveExpenditureCategories();
        for (PhenomenonDetails expenditureCategoryDetails : expenditureCategoryDetailsList) {
            expenditureCategoriesMap.put(expenditureCategoryDetails,
                    retrieveSubActivities(expenditureCategoryDetails, financialYearId, countyId));
        }

        return expenditureCategoriesMap;
    }

    @SuppressWarnings("unchecked")
    private Map<PhenomenonDetails, List<SubActivityDetails>> retrieveComponentsMap(short financialYearId, Short countyId) throws MilesException {

        Map<PhenomenonDetails, List<SubActivityDetails>> componentsMap = new HashMap<>();
        List<PhenomenonDetails> componentDetailsList;
        componentDetailsList = phenomenonService.retrieveComponents();
        for (PhenomenonDetails componentDetails : componentDetailsList) {
            componentsMap.put(componentDetails,
                    retrieveSubActivities(componentDetails, financialYearId, countyId));
        }

        return componentsMap;
    }

    @Override
    public Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>> summarizeFinancialPlanByCategories(short financialYearId, Short countyId) throws MilesException {

        Map<PhenomenonDetails, List<SubActivityDetails>> expenditureCategoriesMap = retrieveExpenditureCategoriesMap(financialYearId, countyId);
        Map<PhenomenonDetails, FinancialPlanDetails> categoryToFinancialPlansMap = new HashMap<>();
        Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>> totalsToCategoryToFinancialPlansMap = new HashMap<>();
        FinancialPlanDetails financialPlanDetails;
        FinancialPlanDetails financialPlanTotals = new FinancialPlanDetails();

        for (PhenomenonDetails expenditureCategory : expenditureCategoriesMap.keySet()) {
            financialPlanDetails = new FinancialPlanDetails();

            for (SubActivityDetails subActivity : expenditureCategoriesMap.get(expenditureCategory)) {
                financialPlanDetails.setTotalInitialAllocationValue(subActivity.getAllocatedBudget());
                MilesDebugger.debug(subActivity.getAllocatedBudget());

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
            }

            try {
                MilesDebugger.debug();
                if (financialPlanTotals.getTotalInitialAllocationValue() != null && financialPlanDetails.getTotalInitialAllocationValue() != null) {
                    financialPlanTotals.setTotalInitialAllocationValue(financialPlanTotals.getTotalInitialAllocationValue().add(financialPlanDetails.getTotalInitialAllocationValue()));
                } else if (financialPlanTotals.getTotalInitialAllocationValue() == null && financialPlanDetails.getTotalInitialAllocationValue() != null) {
                    MilesDebugger.debug();
                    financialPlanTotals.setTotalInitialAllocationValue(financialPlanDetails.getTotalInitialAllocationValue());
                } else {

                }
            } catch (Exception e) {
                MilesDebugger.debug(e);
            }

            try {
                if (financialPlanTotals.getGokValue() != null) {
                    financialPlanTotals.setGokValue(financialPlanTotals.getGokValue().add(financialPlanDetails.getGokValue()));
                } else {
                    financialPlanTotals.setGokValue(financialPlanDetails.getGokValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getBeneficiariesValue() != null) {
                    financialPlanTotals.setBeneficiariesValue(financialPlanTotals.getBeneficiariesValue().add(financialPlanDetails.getBeneficiariesValue()));
                } else {
                    financialPlanTotals.setBeneficiariesValue(financialPlanDetails.getBeneficiariesValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getIfadLoanValue() != null) {
                    financialPlanTotals.setIfadLoanValue(financialPlanTotals.getIfadLoanValue().add(financialPlanDetails.getIfadLoanValue()));
                } else {
                    financialPlanTotals.setIfadLoanValue(financialPlanDetails.getIfadLoanValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getEuValue() != null) {
                    financialPlanTotals.setEuValue(financialPlanTotals.getEuValue().add(financialPlanDetails.getEuValue()));
                } else {
                    financialPlanTotals.setEuValue(financialPlanDetails.getEuValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getIfadGrantValue() != null) {
                    financialPlanTotals.setIfadGrantValue(financialPlanTotals.getIfadGrantValue().add(financialPlanDetails.getIfadGrantValue()));
                } else {
                    financialPlanTotals.setIfadGrantValue(financialPlanDetails.getIfadGrantValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getFinancialInstitutionValue() != null) {
                    financialPlanTotals.setFinancialInstitutionValue(financialPlanTotals.getFinancialInstitutionValue().add(financialPlanDetails.getFinancialInstitutionValue()));
                } else {
                    financialPlanTotals.setFinancialInstitutionValue(financialPlanDetails.getFinancialInstitutionValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getTotalsValue() != null) {
                    financialPlanTotals.setTotalsValue(financialPlanTotals.getTotalsValue().add(financialPlanDetails.getTotalsValue()));
                } else {
                    financialPlanTotals.setTotalsValue(financialPlanDetails.getTotalsValue());
                }
            } catch (Exception e) {
            }

            try {
                financialPlanDetails.setBalanceValue(financialPlanDetails.getTotalInitialAllocationValue().subtract(financialPlanDetails.getTotalsValue()));
                if (financialPlanTotals.getBalanceValue() != null) {
                    financialPlanTotals.setBalanceValue(financialPlanTotals.getBalanceValue().add(financialPlanDetails.getBalanceValue()));
                } else {
                    financialPlanTotals.setBalanceValue(financialPlanDetails.getBalanceValue());
                }
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
                financialPlanDetails.setTotalInitialAllocationPercentage(financialPlanDetails.getTotalInitialAllocationValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setTotalInitialAllocationPercentage(Math.round(financialPlanDetails.getTotalsPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setTotalsPercentage(financialPlanDetails.getTotalsValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setTotalsPercentage(Math.round(financialPlanDetails.getTotalsPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setBalancePercentage(financialPlanDetails.getBalanceValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setBalancePercentage(Math.round(financialPlanDetails.getBalancePercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setGokPercentage(financialPlanDetails.getGokValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setGokPercentage(Math.round(financialPlanDetails.getGokPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setBeneficiariesPercentage(financialPlanDetails.getBeneficiariesValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setBeneficiariesPercentage(Math.round(financialPlanDetails.getBeneficiariesPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setIfadLoanPercentage(financialPlanDetails.getIfadLoanValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setIfadLoanPercentage(Math.round(financialPlanDetails.getIfadLoanPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setEuPercentage(financialPlanDetails.getEuValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setEuPercentage(Math.round(financialPlanDetails.getEuPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setIfadGrantPercentage(financialPlanDetails.getIfadGrantValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setIfadGrantPercentage(Math.round(financialPlanDetails.getIfadGrantPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setFinancialInstitutionPercentage(financialPlanDetails.getFinancialInstitutionValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setFinancialInstitutionPercentage(Math.round(financialPlanDetails.getFinancialInstitutionPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }

            categoryToFinancialPlansMap.put(expenditureCategory, financialPlanDetails);
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
            financialPlanTotals.setTotalInitialAllocationPercentage(financialPlanTotals.getTotalInitialAllocationValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setTotalInitialAllocationPercentage(Math.round(financialPlanTotals.getTotalsPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setTotalsPercentage(financialPlanTotals.getTotalsValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setTotalsPercentage(Math.round(financialPlanTotals.getTotalsPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setBalancePercentage(financialPlanTotals.getBalanceValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setBalancePercentage(Math.round(financialPlanTotals.getBalancePercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setGokPercentage(financialPlanTotals.getGokValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setGokPercentage(Math.round(financialPlanTotals.getGokPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setBeneficiariesPercentage(financialPlanTotals.getBeneficiariesValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setBeneficiariesPercentage(Math.round(financialPlanTotals.getBeneficiariesPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setIfadLoanPercentage(financialPlanTotals.getIfadLoanValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setIfadLoanPercentage(Math.round(financialPlanTotals.getIfadLoanPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setEuPercentage(financialPlanTotals.getEuValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setEuPercentage(Math.round(financialPlanTotals.getEuPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setIfadGrantPercentage(financialPlanTotals.getIfadGrantValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setIfadGrantPercentage(Math.round(financialPlanTotals.getIfadGrantPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setFinancialInstitutionPercentage(financialPlanTotals.getFinancialInstitutionValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setFinancialInstitutionPercentage(Math.round(financialPlanTotals.getFinancialInstitutionPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }

        totalsToCategoryToFinancialPlansMap.put(financialPlanTotals, categoryToFinancialPlansMap);

        return totalsToCategoryToFinancialPlansMap;
    }

    @Override
    public Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>> summarizeFinancialPlanByComponents(short financialYearId, Short countyId) throws MilesException {

        Map<PhenomenonDetails, List<SubActivityDetails>> componentsMap = retrieveComponentsMap(financialYearId, countyId);
        Map<PhenomenonDetails, FinancialPlanDetails> componentToFinancialPlansMap = new HashMap<>();
        Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>> totalsToComponentToFinancialPlansMap = new HashMap<>();
        FinancialPlanDetails financialPlanDetails;
        FinancialPlanDetails financialPlanTotals = new FinancialPlanDetails();

        for (PhenomenonDetails components : componentsMap.keySet()) {
            financialPlanDetails = new FinancialPlanDetails();

            for (SubActivityDetails subActivity : componentsMap.get(components)) {
                financialPlanDetails.setTotalInitialAllocationValue(subActivity.getAllocatedBudget());

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
            }

            try {
                if (financialPlanTotals.getTotalInitialAllocationValue() != null) {
                    financialPlanTotals.setTotalInitialAllocationValue(financialPlanTotals.getTotalInitialAllocationValue().add(financialPlanDetails.getTotalInitialAllocationValue()));
                } else {
                    financialPlanTotals.setTotalInitialAllocationValue(financialPlanDetails.getTotalInitialAllocationValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getGokValue() != null) {
                    financialPlanTotals.setGokValue(financialPlanTotals.getGokValue().add(financialPlanDetails.getGokValue()));
                } else {
                    financialPlanTotals.setGokValue(financialPlanDetails.getGokValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getBeneficiariesValue() != null) {
                    financialPlanTotals.setBeneficiariesValue(financialPlanTotals.getBeneficiariesValue().add(financialPlanDetails.getBeneficiariesValue()));
                } else {
                    financialPlanTotals.setBeneficiariesValue(financialPlanDetails.getBeneficiariesValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getIfadLoanValue() != null) {
                    financialPlanTotals.setIfadLoanValue(financialPlanTotals.getIfadLoanValue().add(financialPlanDetails.getIfadLoanValue()));
                } else {
                    financialPlanTotals.setIfadLoanValue(financialPlanDetails.getIfadLoanValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getEuValue() != null) {
                    financialPlanTotals.setEuValue(financialPlanTotals.getEuValue().add(financialPlanDetails.getEuValue()));
                } else {
                    financialPlanTotals.setEuValue(financialPlanDetails.getEuValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getIfadGrantValue() != null) {
                    financialPlanTotals.setIfadGrantValue(financialPlanTotals.getIfadGrantValue().add(financialPlanDetails.getIfadGrantValue()));
                } else {
                    financialPlanTotals.setIfadGrantValue(financialPlanDetails.getIfadGrantValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getFinancialInstitutionValue() != null) {
                    financialPlanTotals.setFinancialInstitutionValue(financialPlanTotals.getFinancialInstitutionValue().add(financialPlanDetails.getFinancialInstitutionValue()));
                } else {
                    financialPlanTotals.setFinancialInstitutionValue(financialPlanDetails.getFinancialInstitutionValue());
                }
            } catch (Exception e) {
            }

            try {
                if (financialPlanTotals.getTotalsValue() != null) {
                    financialPlanTotals.setTotalsValue(financialPlanTotals.getTotalsValue().add(financialPlanDetails.getTotalsValue()));
                } else {
                    financialPlanTotals.setTotalsValue(financialPlanDetails.getTotalsValue());
                }
            } catch (Exception e) {
            }

            try {
                financialPlanDetails.setBalanceValue(financialPlanDetails.getTotalInitialAllocationValue().subtract(financialPlanDetails.getTotalsValue()));
                if (financialPlanTotals.getBalanceValue() != null) {
                    financialPlanTotals.setBalanceValue(financialPlanTotals.getBalanceValue().add(financialPlanDetails.getBalanceValue()));
                } else {
                    financialPlanTotals.setBalanceValue(financialPlanDetails.getBalanceValue());
                }
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
                financialPlanDetails.setTotalInitialAllocationPercentage(financialPlanDetails.getTotalInitialAllocationValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setTotalInitialAllocationPercentage(Math.round(financialPlanDetails.getTotalsPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setTotalsPercentage(financialPlanDetails.getTotalsValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setTotalsPercentage(Math.round(financialPlanDetails.getTotalsPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setBalancePercentage(financialPlanDetails.getBalanceValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setBalancePercentage(Math.round(financialPlanDetails.getBalancePercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setGokPercentage(financialPlanDetails.getGokValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setGokPercentage(Math.round(financialPlanDetails.getGokPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setBeneficiariesPercentage(financialPlanDetails.getBeneficiariesValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setBeneficiariesPercentage(Math.round(financialPlanDetails.getBeneficiariesPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setIfadLoanPercentage(financialPlanDetails.getIfadLoanValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setIfadLoanPercentage(Math.round(financialPlanDetails.getIfadLoanPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setEuPercentage(financialPlanDetails.getEuValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setEuPercentage(Math.round(financialPlanDetails.getEuPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setIfadGrantPercentage(financialPlanDetails.getIfadGrantValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setIfadGrantPercentage(Math.round(financialPlanDetails.getIfadGrantPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }
            try {
                financialPlanDetails.setFinancialInstitutionPercentage(financialPlanDetails.getFinancialInstitutionValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setFinancialInstitutionPercentage(Math.round(financialPlanDetails.getFinancialInstitutionPercentage() * 100.0) / 100.0);
            } catch (Exception e) {
            }

            componentToFinancialPlansMap.put(components, financialPlanDetails);
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
            financialPlanTotals.setTotalInitialAllocationPercentage(financialPlanTotals.getTotalInitialAllocationValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setTotalInitialAllocationPercentage(Math.round(financialPlanTotals.getTotalsPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setTotalsPercentage(financialPlanTotals.getTotalsValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setTotalsPercentage(Math.round(financialPlanTotals.getTotalsPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setBalancePercentage(financialPlanTotals.getBalanceValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setBalancePercentage(Math.round(financialPlanTotals.getBalancePercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setGokPercentage(financialPlanTotals.getGokValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setGokPercentage(Math.round(financialPlanTotals.getGokPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setBeneficiariesPercentage(financialPlanTotals.getBeneficiariesValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setBeneficiariesPercentage(Math.round(financialPlanTotals.getBeneficiariesPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setIfadLoanPercentage(financialPlanTotals.getIfadLoanValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setIfadLoanPercentage(Math.round(financialPlanTotals.getIfadLoanPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setEuPercentage(financialPlanTotals.getEuValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setEuPercentage(Math.round(financialPlanTotals.getEuPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setIfadGrantPercentage(financialPlanTotals.getIfadGrantValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setIfadGrantPercentage(Math.round(financialPlanTotals.getIfadGrantPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }
        try {
            financialPlanTotals.setFinancialInstitutionPercentage(financialPlanTotals.getFinancialInstitutionValue().doubleValue() / financialPlanTotals.getTotalInitialAllocationValue().doubleValue() * 100);
            financialPlanTotals.setFinancialInstitutionPercentage(Math.round(financialPlanTotals.getFinancialInstitutionPercentage() * 100.0) / 100.0);
        } catch (Exception e) {
        }

        totalsToComponentToFinancialPlansMap.put(financialPlanTotals, componentToFinancialPlansMap);

        return totalsToComponentToFinancialPlansMap;
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
