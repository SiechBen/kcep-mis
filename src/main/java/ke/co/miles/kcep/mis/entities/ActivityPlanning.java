/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "activity_planning", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityPlanning.findAll", query = "SELECT a FROM ActivityPlanning a"),
    @NamedQuery(name = "ActivityPlanning.findById", query = "SELECT a FROM ActivityPlanning a WHERE a.id = :id"),
    @NamedQuery(name = "ActivityPlanning.findByAnnualWorkplanReferenceCode", query = "SELECT a FROM ActivityPlanning a WHERE a.annualWorkplanReferenceCode = :annualWorkplanReferenceCode"),
    @NamedQuery(name = "ActivityPlanning.findByAwpbTarget", query = "SELECT a FROM ActivityPlanning a WHERE a.awpbTarget = :awpbTarget"),
    @NamedQuery(name = "ActivityPlanning.findByProgrammeTarget", query = "SELECT a FROM ActivityPlanning a WHERE a.programmeTarget = :programmeTarget"),
    @NamedQuery(name = "ActivityPlanning.findByValueAchieved", query = "SELECT a FROM ActivityPlanning a WHERE a.valueAchieved = :valueAchieved"),
    @NamedQuery(name = "ActivityPlanning.findByAllocatedBudget", query = "SELECT a FROM ActivityPlanning a WHERE a.allocatedBudget = :allocatedBudget"),
    @NamedQuery(name = "ActivityPlanning.findByProcurementPlan", query = "SELECT a FROM ActivityPlanning a WHERE a.procurementPlan = :procurementPlan"),
    @NamedQuery(name = "ActivityPlanning.findByTotal", query = "SELECT a FROM ActivityPlanning a WHERE a.total = :total"),
    @NamedQuery(name = "ActivityPlanning.findByCategory", query = "SELECT a FROM ActivityPlanning a WHERE a.category = :category")})
public class ActivityPlanning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "annual_workplan_reference_code")
    private String annualWorkplanReferenceCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "awpb_target")
    private BigDecimal awpbTarget;
    @Column(name = "programme_target")
    private BigDecimal programmeTarget;
    @Column(name = "value_achieved")
    private BigDecimal valueAchieved;
    @Column(name = "allocated_budget")
    private BigDecimal allocatedBudget;
    @Size(max = 45)
    @Column(name = "procurement_plan")
    private String procurementPlan;
    @Column(name = "total")
    private BigDecimal total;
    @Size(max = 45)
    @Column(name = "category")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityPlanning")
    private List<SubActivity> subActivityList;
    @JoinColumn(name = "activity", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Activity activity;
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

    public ActivityPlanning() {
    }

    public ActivityPlanning(Integer id) {
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

    public String getProcurementPlan() {
        return procurementPlan;
    }

    public void setProcurementPlan(String procurementPlan) {
        this.procurementPlan = procurementPlan;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList() {
        return subActivityList;
    }

    public void setSubActivityList(List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivityPlanning)) {
            return false;
        }
        ActivityPlanning other = (ActivityPlanning) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ActivityPlanning[ id=" + id + " ]";
    }
    
}
