/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author siech
 */
public class ActivityPlanningDetails implements Serializable, Comparable<ActivityPlanningDetails> {

    public ActivityPlanningDetails() {
    }

    public ActivityPlanningDetails(Integer id) {
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

    public void setProgrammeTarget(BigDecimal planningTarget) {
        this.programmeTarget = planningTarget;
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

    public ImplementingPartnerDetails getImplementingPartner() {
        return implementingPartner;
    }

    public void setImplementingPartner(ImplementingPartnerDetails implementingPartner) {
        this.implementingPartner = implementingPartner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivityPlanningDetails)) {
            return false;
        }
        ActivityPlanningDetails other = (ActivityPlanningDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Planning[ id=" + getId() + " ]";
    }

    @Override
    public int compareTo(ActivityPlanningDetails o) {
        return this.getId().compareTo(o.getId());
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the procurementPlan
     */
    public String getProcurementPlan() {
        return procurementPlan;
    }

    /**
     * @param procurementPlan the procurementPlan to set
     */
    public void setProcurementPlan(String procurementPlan) {
        this.procurementPlan = procurementPlan;
    }

    /**
     * @return the activityDescription
     */
    public Integer getActivityDescription() {
        return activityDescription;
    }

    /**
     * @param activityDescription the activityDescription to set
     */
    public void setActivityDescription(Integer activityDescription) {
        this.activityDescription = activityDescription;
    }

    /**
     * @return the performanceIndicator
     */
    public PerformanceIndicatorDetails getPerformanceIndicator() {
        return performanceIndicator;
    }

    /**
     * @param performanceIndicator the performanceIndicator to set
     */
    public void setPerformanceIndicator(PerformanceIndicatorDetails performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String category;
    private BigDecimal total;
    private BigDecimal awpbTarget;
    private String procurementPlan;
    private BigDecimal valueAchieved;
    private Integer activityDescription;
    private BigDecimal allocatedBudget;
    private BigDecimal programmeTarget;
    private ComponentDetails component;
    private String annualWorkplanReferenceCode;
    private SubComponentDetails subComponent;
    private PerformanceIndicatorDetails performanceIndicator;
    private ImplementingPartnerDetails implementingPartner;

}
