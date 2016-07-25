/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "sub_activity", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubActivity.findByActivityPlanningId", query = "SELECT s FROM SubActivity s WHERE s.activityPlanning.id = :activityPlanningId"),
    @NamedQuery(name = "SubActivity.findAll", query = "SELECT s FROM SubActivity s"),
    @NamedQuery(name = "SubActivity.findById", query = "SELECT s FROM SubActivity s WHERE s.id = :id"),
    @NamedQuery(name = "SubActivity.findByAnnualWorkplanReferenceCode", query = "SELECT s FROM SubActivity s WHERE s.annualWorkplanReferenceCode = :annualWorkplanReferenceCode"),
    @NamedQuery(name = "SubActivity.findByStartDate", query = "SELECT s FROM SubActivity s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "SubActivity.findByEndDate", query = "SELECT s FROM SubActivity s WHERE s.endDate = :endDate"),
    @NamedQuery(name = "SubActivity.findByUnitCost", query = "SELECT s FROM SubActivity s WHERE s.unitCost = :unitCost"),
    @NamedQuery(name = "SubActivity.findByAwpbTarget", query = "SELECT s FROM SubActivity s WHERE s.awpbTarget = :awpbTarget"),
    @NamedQuery(name = "SubActivity.findByProgrammeTarget", query = "SELECT s FROM SubActivity s WHERE s.programmeTarget = :programmeTarget"),
    @NamedQuery(name = "SubActivity.findByTotals", query = "SELECT s FROM SubActivity s WHERE s.totals = :totals"),
    @NamedQuery(name = "SubActivity.findByProcurementPlan", query = "SELECT s FROM SubActivity s WHERE s.procurementPlan = :procurementPlan"),
    @NamedQuery(name = "SubActivity.findByDescription", query = "SELECT s FROM SubActivity s WHERE s.description = :description"),
    @NamedQuery(name = "SubActivity.findByValueAchieved", query = "SELECT s FROM SubActivity s WHERE s.valueAchieved = :valueAchieved"),
    @NamedQuery(name = "SubActivity.findByAllocatedBudget", query = "SELECT s FROM SubActivity s WHERE s.allocatedBudget = :allocatedBudget"),
    @NamedQuery(name = "SubActivity.findByGokPercentage", query = "SELECT s FROM SubActivity s WHERE s.gokPercentage = :gokPercentage"),
    @NamedQuery(name = "SubActivity.findByIfadLoanPercentage", query = "SELECT s FROM SubActivity s WHERE s.ifadLoanPercentage = :ifadLoanPercentage"),
    @NamedQuery(name = "SubActivity.findByIfadGrantPercentage", query = "SELECT s FROM SubActivity s WHERE s.ifadGrantPercentage = :ifadGrantPercentage"),
    @NamedQuery(name = "SubActivity.findByBeneficiariesPercentage", query = "SELECT s FROM SubActivity s WHERE s.beneficiariesPercentage = :beneficiariesPercentage"),
    @NamedQuery(name = "SubActivity.findByEuPercentage", query = "SELECT s FROM SubActivity s WHERE s.euPercentage = :euPercentage"),
    @NamedQuery(name = "SubActivity.findByFinancialInstitutionPercentage", query = "SELECT s FROM SubActivity s WHERE s.financialInstitutionPercentage = :financialInstitutionPercentage")})
public class SubActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "annual_workplan_reference_code")
    private String annualWorkplanReferenceCode;
    @Size(max = 45)
    @Column(name = "start_date")
    private Date startDate;
    @Size(max = 45)
    @Column(name = "end_date")
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
    @JoinColumn(name = "activity", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Activity activity;
    @JoinColumn(name = "expenditure_category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ExpenditureCategory expenditureCategory;
    @JoinColumn(name = "performance_indicator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PerformanceIndicator performanceIndicator;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Component component;
    @JoinColumn(name = "sub_component", referencedColumnName = "id")
    @ManyToOne
    private SubComponent subComponent;
    @JoinColumn(name = "implementing_partner", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ImplementingPartner implementingPartner;
    @JoinColumn(name = "measurement_unit", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MeasurementUnit measurementUnit;
    @JoinColumn(name = "response_pcu", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ResponsePcu responsePcu;
    @JoinColumn(name = "sub_activity_description", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubActivityDescription subActivityDescription;

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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ExpenditureCategory getExpenditureCategory() {
        return expenditureCategory;
    }

    public void setExpenditureCategory(ExpenditureCategory expenditureCategory) {
        this.expenditureCategory = expenditureCategory;
    }

    public PerformanceIndicator getPerformanceIndicator() {
        return performanceIndicator;
    }

    public void setPerformanceIndicator(PerformanceIndicator performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
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

    public ImplementingPartner getImplementingPartner() {
        return implementingPartner;
    }

    public void setImplementingPartner(ImplementingPartner implementingPartner) {
        this.implementingPartner = implementingPartner;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public ResponsePcu getResponsePcu() {
        return responsePcu;
    }

    public void setResponsePcu(ResponsePcu responsePcu) {
        this.responsePcu = responsePcu;
    }

    public SubActivityDescription getSubActivityDescription() {
        return subActivityDescription;
    }

    public void setSubActivityDescription(SubActivityDescription subActivityDescription) {
        this.subActivityDescription = subActivityDescription;
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
    
}
