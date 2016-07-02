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
public class PlanningDetails implements Serializable, Comparable<PlanningDetails> {

    public PlanningDetails() {
    }

    public PlanningDetails(Integer id) {
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

    public ActivityDetails getActivity() {
        return activity;
    }

    public void setActivity(ActivityDetails activity) {
        this.activity = activity;
    }

    public ComponentDetails getComponent() {
        return component;
    }

    public void setComponent(ComponentDetails component) {
        this.component = component;
    }

    public SubComponentDetails getSubComponent() {
        return subComponentDetails;
    }

    public void setSubComponent(SubComponentDetails subComponentDetails) {
        this.subComponentDetails = subComponentDetails;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanningDetails)) {
            return false;
        }
        PlanningDetails other = (PlanningDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Planning[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PlanningDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String annualWorkplanReferenceCode;
    private BigDecimal awpbTarget;
    private BigDecimal programmeTarget;
    private BigDecimal valueAchieved;
    private BigDecimal allocatedBudget;
    private ActivityDetails activity;
    private ComponentDetails component;
    private SubComponentDetails subComponentDetails;
    private ImplementingPartnerDetails implementingPartner;

}
