package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "sub_activity", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubActivity.findReferenceCodes", query = "SELECT s.annualWorkplanReferenceCode FROM SubActivity s"),
    @NamedQuery(name = "SubActivity.findByFinancialYearId", query = "SELECT s FROM SubActivity s WHERE s.financialYear.id =:financialYearId"),
    @NamedQuery(name = "SubActivity.findHeadSubActivities", query = "SELECT s FROM SubActivity s WHERE s.county IS NULL AND s.region IS NULL"),
    @NamedQuery(name = "SubActivity.findByExpectedOutcomeId", query = "SELECT s FROM SubActivity s WHERE s.expectedOutcome.id = :expectedOutcomeId"),
    @NamedQuery(name = "SubActivity.findCountySubActivities", query = "SELECT s FROM SubActivity s WHERE s.county.id = :countyId AND s.region IS NULL"),
    @NamedQuery(name = "SubActivity.findRegionSubActivities", query = "SELECT s FROM SubActivity s WHERE s.region.id = :regionId AND s.county IS NULL"),
    @NamedQuery(name = "SubActivity.findByAnnualWorkplanReferenceCode", query = "SELECT s FROM SubActivity s WHERE s.annualWorkplanReferenceCode = :annualWorkplanReferenceCode"),
    @NamedQuery(name = "SubActivity.findByComponentIdAndFinancialYearId", query = "SELECT s FROM SubActivity s WHERE s.component.id = :componentId AND s.financialYear.id =:financialYearId AND s.county IS NULL"),
    @NamedQuery(name = "SubActivity.findOfCountyByComponentIdAndFinancialYearId", query = "SELECT s FROM SubActivity s WHERE s.component.id = :componentId AND s.financialYear.id =:financialYearId AND s.county.id = :countyId"),
    @NamedQuery(name = "SubActivity.findByExpenditureCategoryIdAndFinancialYearId", query = "SELECT s FROM SubActivity s WHERE s.expenditureCategory.id = :expenditureCategoryId AND s.financialYear.id =:financialYearId AND s.county IS NULL"),
    @NamedQuery(name = "SubActivity.findOfCountyByExpenditureCategoryIdAndFinancialYearId", query = "SELECT s FROM SubActivity s WHERE s.expenditureCategory.id = :expenditureCategoryId AND s.financialYear.id =:financialYearId AND s.county.id = :countyId"),
    @NamedQuery(name = "SubActivity.findAll", query = "SELECT s FROM SubActivity s"),
    @NamedQuery(name = "SubActivity.findById", query = "SELECT s FROM SubActivity s WHERE s.id = :id"),
    @NamedQuery(name = "SubActivity.findByTotals", query = "SELECT s FROM SubActivity s WHERE s.totals = :totals"),
    @NamedQuery(name = "SubActivity.findByEndDate", query = "SELECT s FROM SubActivity s WHERE s.endDate = :endDate"),
    @NamedQuery(name = "SubActivity.findByUnitCost", query = "SELECT s FROM SubActivity s WHERE s.unitCost = :unitCost"),
    @NamedQuery(name = "SubActivity.findByStartDate", query = "SELECT s FROM SubActivity s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "SubActivity.findByAwpbTarget", query = "SELECT s FROM SubActivity s WHERE s.awpbTarget = :awpbTarget"),
    @NamedQuery(name = "SubActivity.findByDescription", query = "SELECT s FROM SubActivity s WHERE s.description = :description"),
    @NamedQuery(name = "SubActivity.findByEuPercentage", query = "SELECT s FROM SubActivity s WHERE s.euPercentage = :euPercentage"),
    @NamedQuery(name = "SubActivity.findByValueAchieved", query = "SELECT s FROM SubActivity s WHERE s.valueAchieved = :valueAchieved"),
    @NamedQuery(name = "SubActivity.findByGokPercentage", query = "SELECT s FROM SubActivity s WHERE s.gokPercentage = :gokPercentage"),
    @NamedQuery(name = "SubActivity.findByProgrammeTarget", query = "SELECT s FROM SubActivity s WHERE s.programmeTarget = :programmeTarget"),
    @NamedQuery(name = "SubActivity.findByProcurementPlan", query = "SELECT s FROM SubActivity s WHERE s.procurementPlan = :procurementPlan"),
    @NamedQuery(name = "SubActivity.findByAllocatedBudget", query = "SELECT s FROM SubActivity s WHERE s.allocatedBudget = :allocatedBudget"),
    @NamedQuery(name = "SubActivity.findByIfadLoanPercentage", query = "SELECT s FROM SubActivity s WHERE s.ifadLoanPercentage = :ifadLoanPercentage"),
    @NamedQuery(name = "SubActivity.findByIfadGrantPercentage", query = "SELECT s FROM SubActivity s WHERE s.ifadGrantPercentage = :ifadGrantPercentage"),
    @NamedQuery(name = "SubActivity.findByBeneficiariesPercentage", query = "SELECT s FROM SubActivity s WHERE s.beneficiariesPercentage = :beneficiariesPercentage"),
    @NamedQuery(name = "SubActivity.findByFinancialInstitutionPercentage", query = "SELECT s FROM SubActivity s WHERE s.financialInstitutionPercentage = :financialInstitutionPercentage")})
public class SubActivity implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subActivity")
    private List<ActivityProgress> activityProgressList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "annual_workplan_reference_code")
    private String annualWorkplanReferenceCode;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unit_cost")
    private BigDecimal unitCost;
    @Column(name = "awpb_target")
    private BigDecimal awpbTarget;
    @Column(name = "programme_target")
    private BigDecimal programmeTarget;
    @Column(name = "totals")
    private BigDecimal totals;
    @Size(max = 45)
    @Column(name = "procurement_plan")
    private String procurementPlan;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Column(name = "value_achieved")
    private BigDecimal valueAchieved;
    @Column(name = "allocated_budget")
    private BigDecimal allocatedBudget;
    @Column(name = "gok_percentage")
    private Double gokPercentage;
    @Column(name = "ifad_loan_percentage")
    private Double ifadLoanPercentage;
    @Column(name = "ifad_grant_percentage")
    private Double ifadGrantPercentage;
    @Column(name = "beneficiaries_percentage")
    private Double beneficiariesPercentage;
    @Column(name = "eu_percentage")
    private Double euPercentage;
    @Column(name = "financial_institution_percentage")
    private Double financialInstitutionPercentage;
    @JoinColumn(name = "measurement_unit", referencedColumnName = "id")
    @ManyToOne
    private MeasurementUnit measurementUnit;
    @JoinColumn(name = "activity_name", referencedColumnName = "id")
    @ManyToOne
    private ActivityName activityName;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne
    private Component component;
    @JoinColumn(name = "sub_component", referencedColumnName = "id")
    @ManyToOne
    private SubComponent subComponent;
    @JoinColumn(name = "gfss_code", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon gfssCode;
    @JoinColumn(name = "expected_outcome", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon expectedOutcome;
    @JoinColumn(name = "implementing_partner", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon implementingPartner;
    @JoinColumn(name = "response_pcu", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon responsePcu;
    @JoinColumn(name = "annual_indicator", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon annualIndicator;
    @JoinColumn(name = "expenditure_category", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon expenditureCategory;
    @JoinColumn(name = "sub_activity_name", referencedColumnName = "id")
    @ManyToOne
    private SubActivityName subActivityName;
    @JoinColumn(name = "financial_year", referencedColumnName = "id")
    @ManyToOne
    private FinancialYear financialYear;
    @JoinColumn(name = "county", referencedColumnName = "id")
    @ManyToOne
    private County county;
    @JoinColumn(name = "region", referencedColumnName = "id")
    @ManyToOne
    private Region region;

    public SubActivity() {
    }

    public SubActivity(Integer id) {
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

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public ActivityName getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityName activityName) {
        this.activityName = activityName;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public SubComponent getSubComponent() {
        return subComponent;
    }

    public void setSubComponent(SubComponent subComponent) {
        this.subComponent = subComponent;
    }

    public Phenomenon getGfssCode() {
        return gfssCode;
    }

    public void setGfssCode(Phenomenon gfssCode) {
        this.gfssCode = gfssCode;
    }

    public Phenomenon getExpectedOutcome() {
        return expectedOutcome;
    }

    public void setExpectedOutcome(Phenomenon expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }

    public Phenomenon getImplementingPartner() {
        return implementingPartner;
    }

    public void setImplementingPartner(Phenomenon implementingPartner) {
        this.implementingPartner = implementingPartner;
    }

    public Phenomenon getResponsePcu() {
        return responsePcu;
    }

    public void setResponsePcu(Phenomenon responsePcu) {
        this.responsePcu = responsePcu;
    }

    public Phenomenon getAnnualIndicator() {
        return annualIndicator;
    }

    public void setAnnualIndicator(Phenomenon annualIndicator) {
        this.annualIndicator = annualIndicator;
    }

    public Phenomenon getExpenditureCategory() {
        return expenditureCategory;
    }

    public void setExpenditureCategory(Phenomenon expenditureCategory) {
        this.expenditureCategory = expenditureCategory;
    }

    public SubActivityName getSubActivityName() {
        return subActivityName;
    }

    public void setSubActivityName(SubActivityName subActivityName) {
        this.subActivityName = subActivityName;
    }

    public FinancialYear getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(FinancialYear financialYear) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubActivity)) {
            return false;
        }
        SubActivity other = (SubActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubActivity[ id=" + id + " ]";
    }

    @XmlTransient
    public List<ActivityProgress> getActivityProgressList() {
        return activityProgressList;
    }

    public void setActivityProgressList(List<ActivityProgress> activityProgressList) {
        this.activityProgressList = activityProgressList;
    }

    /**
     * @return the county
     */
    public County getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(County county) {
        this.county = county;
    }

    /**
     * @return the region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(Region region) {
        this.region = region;
    }

}
