/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.sub;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ActivityName;
import ke.co.miles.kcep.mis.entities.Component;
import ke.co.miles.kcep.mis.entities.ExpenditureCategory;
import ke.co.miles.kcep.mis.entities.FinancialYear;
import ke.co.miles.kcep.mis.entities.ImplementingPartner;
import ke.co.miles.kcep.mis.entities.MeasurementUnit;
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
import ke.co.miles.kcep.mis.requests.activityplanning.financialyear.FinancialYearRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.responsepcu.ResponsePcuRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;
import ke.co.miles.kcep.mis.utilities.ExpenditureCategoryDetails;
import ke.co.miles.kcep.mis.utilities.FinancialPlanDetails;
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
        } else if (subActivityDetails.getActivityName() == null) {
            throw new InvalidArgumentException("error_017_05");
        }

        SubActivity subActivity = new SubActivity();
        subActivity.setAnnualWorkplanReferenceCode(subActivityDetails.getAnnualWorkplanReferenceCode());
        subActivity.setExpectedOutcome(subActivityDetails.getExpectedOutcome());
        subActivity.setStartDate(subActivityDetails.getStartDate());
        subActivity.setEndDate(subActivityDetails.getEndDate());
        subActivity.setEndDate(new Date());
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
        subActivity.setActivityName(em.getReference(ActivityName.class, subActivityDetails.getActivityName().getId()));
        subActivity.setExpenditureCategory(em.getReference(ExpenditureCategory.class, subActivityDetails.getExpenditureCategory().getId()));
        subActivity.setComponent(em.getReference(Component.class, subActivityDetails.getComponent().getId()));
        if (subActivityDetails.getSubComponent() != null) {
            subActivity.setSubComponent(em.getReference(SubComponent.class, subActivityDetails.getSubComponent().getId()));
        }
        if (subActivityDetails.getFinancialYear() != null) {
            subActivity.setFinancialYear(em.getReference(FinancialYear.class, subActivityDetails.getFinancialYear().getId()));
        } else {
            q = em.createNamedQuery("FinancialYear.findByCurrentYear");
            q.setParameter("currentYear", Boolean.TRUE);
            subActivity.setFinancialYear(em.getReference(FinancialYear.class, ((FinancialYear) q.getSingleResult()).getId()));
        }
        subActivity.setImplementingPartner(em.getReference(ImplementingPartner.class, subActivityDetails.getImplementingPartner().getId()));
        subActivity.setMeasurementUnit(em.getReference(MeasurementUnit.class, subActivityDetails.getMeasurementUnit().getId()));
        subActivity.setResponsePcu(em.getReference(ResponsePcu.class, subActivityDetails.getResponsePcu().getId()));
        subActivity.setSubActivityName(em.getReference(SubActivityName.class, subActivityDetails.getSubActivityName().getId()));

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
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivity);
    }

    @SuppressWarnings("unchecked")
    private List<SubActivityDetails> retrieveSubActivities(ExpenditureCategoryDetails expenditureCategoryDetails, short financialYearId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        q = em.createNamedQuery("SubActivity.findByExpenditureCategoryIdAndFinancialYearId");
        q.setParameter("expenditureCategoryId", expenditureCategoryDetails.getId());
        q.setParameter("financialYearId", financialYearId);
        try {
            subActivities = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivitiesToSubActivityDetailsList(subActivities);
    }

    @SuppressWarnings("unchecked")
    private List<SubActivityDetails> retrieveSubActivities(ComponentDetails component, short financialYearId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        q = em.createNamedQuery("SubActivity.findByComponentIdAndFinancialYearId");
        q.setParameter("componentId", component.getId());
        q.setParameter("financialYearId", financialYearId);
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
        q = em.createNamedQuery("SubActivity.findById");
        q.setParameter("id", id);
        try {
            subActivity = (SubActivity) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivityToSubActivityDetails(subActivity);
    }

    @SuppressWarnings("unchecked")
    private Map<ExpenditureCategoryDetails, List<SubActivityDetails>> retrieveExpenditureCategoriesMap(short financialYearId) throws MilesException {
        Map<ExpenditureCategoryDetails, List<SubActivityDetails>> expenditureCategoriesMap = new HashMap<>();
        List<ExpenditureCategoryDetails> expenditureCategoryDetailsList;

        expenditureCategoryDetailsList = expenditureCategoryService.retrieveExpenditureCategories();
        for (ExpenditureCategoryDetails expenditureCategoryDetails : expenditureCategoryDetailsList) {
            expenditureCategoriesMap.put(expenditureCategoryDetails,
                    retrieveSubActivities(expenditureCategoryDetails, financialYearId));
        }
        
        return expenditureCategoriesMap;
    }

    @SuppressWarnings("unchecked")
    private Map<ComponentDetails, List<SubActivityDetails>> retrieveComponentsMap(short financialYearId) throws MilesException {

        Map<ComponentDetails, List<SubActivityDetails>> componentsMap = new HashMap<>();
        List<ComponentDetails> componentDetailsList;
        componentDetailsList = componentService.retrieveComponents();
        for (ComponentDetails componentDetails : componentDetailsList) {
            componentsMap.put(componentDetails,
                    retrieveSubActivities(componentDetails, financialYearId));
        }

        return componentsMap;
    }

    @Override
    public Map<ExpenditureCategoryDetails, FinancialPlanDetails> summarizeFinancialPlanByCategories(short financialYearId) throws MilesException {

        Map<ExpenditureCategoryDetails, List<SubActivityDetails>> expenditureCategoriesMap = retrieveExpenditureCategoriesMap(financialYearId);
        Map<ExpenditureCategoryDetails, FinancialPlanDetails> financialPlansMap = new HashMap<>();
        FinancialPlanDetails financialPlanDetails;

        for (ExpenditureCategoryDetails expenditureCategory : expenditureCategoriesMap.keySet()) {
            financialPlanDetails = new FinancialPlanDetails();

            for (SubActivityDetails subActivity : expenditureCategoriesMap.get(expenditureCategory)) {
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
                financialPlanDetails.setTotalsPercentage(financialPlanDetails.getTotalsValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setTotalsPercentage(Math.round(financialPlanDetails.getTotalsPercentage() * 100.0) / 100.0);
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

            financialPlansMap.put(expenditureCategory, financialPlanDetails);
        }

        return financialPlansMap;
    }

    @Override
    public Map<ComponentDetails, FinancialPlanDetails> summarizeFinancialPlanByComponents(short financialYearId) throws MilesException {

        Map<ComponentDetails, List<SubActivityDetails>> componentsMap = retrieveComponentsMap(financialYearId);
        Map<ComponentDetails, FinancialPlanDetails> financialPlansMap = new HashMap<>();
        FinancialPlanDetails financialPlanDetails;

        for (ComponentDetails component : componentsMap.keySet()) {
            financialPlanDetails = new FinancialPlanDetails();
            for (SubActivityDetails subActivity : componentsMap.get(component)) {
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
                financialPlanDetails.setTotalsPercentage(financialPlanDetails.getTotalsValue().doubleValue() / financialPlanDetails.getTotalInitialAllocationValue().doubleValue() * 100);
                financialPlanDetails.setTotalsPercentage(Math.round(financialPlanDetails.getTotalsPercentage() * 100.0) / 100.0);
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

            financialPlansMap.put(component, financialPlanDetails);
        }

        return financialPlansMap;
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
        } else if (subActivityDetails.getActivityName() == null) {
            throw new InvalidArgumentException("error_017_05");
        }

        SubActivity subActivity = em.find(SubActivity.class, subActivityDetails.getId());
        subActivity.setId(subActivityDetails.getId());
        subActivity.setAnnualWorkplanReferenceCode(subActivityDetails.getAnnualWorkplanReferenceCode());
        subActivity.setExpectedOutcome(subActivityDetails.getExpectedOutcome());
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
        subActivity.setActivityName(em.getReference(ActivityName.class, subActivityDetails.getActivityName().getId()));
        subActivity.setExpenditureCategory(em.getReference(ExpenditureCategory.class, subActivityDetails.getExpenditureCategory().getId()));
        subActivity.setComponent(em.getReference(Component.class, subActivityDetails.getComponent().getId()));
        if (subActivityDetails.getSubComponent() != null) {
            subActivity.setSubComponent(em.getReference(SubComponent.class, subActivityDetails.getSubComponent().getId()));
        }
        if (subActivityDetails.getFinancialYear() != null) {
            subActivity.setFinancialYear(em.getReference(FinancialYear.class, subActivityDetails.getFinancialYear().getId()));
        } else {
            q = em.createNamedQuery("FinancialYear.findByCurrentYear");
            q.setParameter("currentYear", Boolean.TRUE);
            subActivity.setFinancialYear(em.getReference(FinancialYear.class, ((FinancialYear) q.getSingleResult()).getId()));
        }
        subActivity.setImplementingPartner(em.getReference(ImplementingPartner.class, subActivityDetails.getImplementingPartner().getId()));
        subActivity.setMeasurementUnit(em.getReference(MeasurementUnit.class, subActivityDetails.getMeasurementUnit().getId()));
        subActivity.setResponsePcu(em.getReference(ResponsePcu.class, subActivityDetails.getResponsePcu().getId()));
        subActivity.setSubActivityName(em.getReference(SubActivityName.class, subActivityDetails.getSubActivityName().getId()));

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
        subActivityDetails.setExpectedOutcome(subActivity.getExpectedOutcome());
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
        subActivityDetails.setComponent(componentService.
                convertComponentToComponentDetails(subActivity.getComponent()));
        subActivityDetails.setImplementingPartner(implementingPartnerService.
                convertImplementingPartnerToImplementingPartnerDetails(subActivity.getImplementingPartner()));
        if (subActivity.getSubComponent() != null) {
            subActivityDetails.setSubComponent(subComponentService.
                    convertSubComponentToSubComponentDetails(subActivity.getSubComponent()));
        }
        if (subActivity.getFinancialYear() != null) {
            subActivityDetails.setFinancialYear(financialYearService.
                    convertFinancialYearToFinancialYearDetails(subActivity.getFinancialYear()));
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
    private FinancialYearRequestsLocal financialYearService;
}
