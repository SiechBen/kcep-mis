/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author siech
 */
public class SubActivityDetails implements Serializable, Comparable<SubActivityDetails> {

    public SubActivityDetails() {
    }

    public SubActivityDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnnualWorkplanReferenceCode() {
        return annualWorkplanReferenceCode;
    }

    public void setAnnualWorkplanReferenceCode(String annualWorkplanReferenceCode) {
        this.annualWorkplanReferenceCode = annualWorkplanReferenceCode;
    }

    public PhenomenonDetails getExpectedOutcome() {
        return this.expectedOutcome;
    }

    public void setExpectedOutcome(PhenomenonDetails expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getAwpbTarget() {
        return awpbTarget;
    }

    public void setAwpbTarget(BigDecimal awpbTarget) {
        this.awpbTarget = awpbTarget;
    }

    public BigDecimal getProgrammeTarget() {
        return programmeTarget;
    }

    public void setProgrammeTarget(BigDecimal programmeTarget) {
        this.programmeTarget = programmeTarget;
    }

    public BigDecimal getTotals() {
        return totals;
    }

    public void setTotals(BigDecimal totals) {
        this.totals = totals;
    }

    public String getProcurementPlan() {
        return procurementPlan;
    }

    public void setProcurementPlan(String procurementPlan) {
        this.procurementPlan = procurementPlan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValueAchieved() {
        return valueAchieved;
    }

    public void setValueAchieved(BigDecimal valueAchieved) {
        this.valueAchieved = valueAchieved;
    }

    public BigDecimal getAllocatedBudget() {
        return allocatedBudget;
    }

    public void setAllocatedBudget(BigDecimal allocatedBudget) {
        this.allocatedBudget = allocatedBudget;
    }

    public Double getGokPercentage() {
        return gokPercentage;
    }

    public void setGokPercentage(Double gokPercentage) {
        this.gokPercentage = gokPercentage;
    }

    public Double getIfadLoanPercentage() {
        return ifadLoanPercentage;
    }

    public void setIfadLoanPercentage(Double ifadLoanPercentage) {
        this.ifadLoanPercentage = ifadLoanPercentage;
    }

    public Double getIfadGrantPercentage() {
        return ifadGrantPercentage;
    }

    public void setIfadGrantPercentage(Double ifadGrantPercentage) {
        this.ifadGrantPercentage = ifadGrantPercentage;
    }

    public Double getBeneficiariesPercentage() {
        return beneficiariesPercentage;
    }

    public void setBeneficiariesPercentage(Double beneficiariesPercentage) {
        this.beneficiariesPercentage = beneficiariesPercentage;
    }

    public Double getEuPercentage() {
        return euPercentage;
    }

    public void setEuPercentage(Double euPercentage) {
        this.euPercentage = euPercentage;
    }

    public Double getFinancialInstitutionPercentage() {
        return financialInstitutionPercentage;
    }

    public void setFinancialInstitutionPercentage(Double financialInstitutionPercentage) {
        this.financialInstitutionPercentage = financialInstitutionPercentage;
    }

    public ActivityNameDetails getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityNameDetails activityName) {
        this.activityName = activityName;
    }

    public PhenomenonDetails getExpenditureCategory() {
        return expenditureCategory;
    }

    public void setExpenditureCategory(PhenomenonDetails expenditureCategory) {
        this.expenditureCategory = expenditureCategory;
    }

    public PerformanceIndicatorDetails getPerformanceIndicator() {
        return performanceIndicator;
    }

    public void setPerformanceIndicator(PerformanceIndicatorDetails performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
    }

    public ComponentDetails getComponent() {
        return component;
    }

    public void setComponent(ComponentDetails component) {
        this.component = component;
    }

    public SubComponentDetails getSubComponent() {
        return subComponent;
    }

    public void setSubComponent(SubComponentDetails subComponent) {
        this.subComponent = subComponent;
    }

    public PhenomenonDetails getImplementingPartner() {
        return implementingPartner;
    }

    public void setImplementingPartner(PhenomenonDetails implementingPartner) {
        this.implementingPartner = implementingPartner;
    }

    public MeasurementUnitDetails getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnitDetails measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public PhenomenonDetails getResponsePcu() {
        return responsePcu;
    }

    public void setResponsePcu(PhenomenonDetails responsePcu) {
        this.responsePcu = responsePcu;
    }

    public SubActivityNameDetails getSubActivityName() {
        return subActivityName;
    }

    public void setSubActivityName(SubActivityNameDetails subActivityName) {
        this.subActivityName = subActivityName;
    }

    /**
     * @return the financialYear
     */
    public FinancialYearDetails getFinancialYear() {
        return financialYear;
    }

    /**
     * @param financialYear the financialYear to set
     */
    public void setFinancialYear(FinancialYearDetails financialYear) {
        this.financialYear = financialYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SubActivityDetails)) {
            return false;
        }
        SubActivityDetails other = (SubActivityDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.SubActivityDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(SubActivityDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the gfssCode
     */
    public PhenomenonDetails getGfssCode() {
        return gfssCode;
    }

    /**
     * @param gfssCode the gfssCode to set
     */
    public void setGfssCode(PhenomenonDetails gfssCode) {
        this.gfssCode = gfssCode;
    }

    /**
     * @return the annualIndicator
     */
    public PhenomenonDetails getAnnualIndicator() {
        return annualIndicator;
    }

    /**
     * @param annualIndicator the annualIndicator to set
     */
    public void setAnnualIndicator(PhenomenonDetails annualIndicator) {
        this.annualIndicator = annualIndicator;
    }

    /**
     * @return the county
     */
    public CountyDetails getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(CountyDetails county) {
        this.county = county;
    }

    /**
     * @return the region
     */
    public RegionDetail getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(RegionDetail region) {
        this.region = region;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String annualWorkplanReferenceCode;
    private PhenomenonDetails gfssCode;
    private PhenomenonDetails expectedOutcome;
    private PhenomenonDetails annualIndicator;
    private Date startDate;
    private Date endDate;
    private BigDecimal unitCost;
    private BigDecimal awpbTarget;
    private BigDecimal programmeTarget;
    private BigDecimal totals;
    private String procurementPlan;
    private String description;
    private BigDecimal valueAchieved;
    private BigDecimal allocatedBudget;
    private Double gokPercentage;
    private Double ifadLoanPercentage;
    private Double ifadGrantPercentage;
    private Double beneficiariesPercentage;
    private Double euPercentage;
    private Double financialInstitutionPercentage;
    private ActivityNameDetails activityName;
    private PhenomenonDetails expenditureCategory;
    private PerformanceIndicatorDetails performanceIndicator;
    private ComponentDetails component;
    private SubComponentDetails subComponent;
    private PhenomenonDetails implementingPartner;
    private MeasurementUnitDetails measurementUnit;
    private PhenomenonDetails responsePcu;
    private SubActivityNameDetails subActivityName;
    private FinancialYearDetails financialYear;
    private CountyDetails county;
    private RegionDetail region;

}
