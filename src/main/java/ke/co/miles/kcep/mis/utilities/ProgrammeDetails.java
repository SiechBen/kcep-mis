
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;

/**
 *
 * @author siech
 */
public class ProgrammeDetails implements Serializable, Comparable<ProgrammeDetails> {

    private static final long serialVersionUID = 1L;

    public ProgrammeDetails() {
    }

    public ProgrammeDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getAwpTarget() {
        return awpTarget;
    }

    public void setAwpTarget(String awpTarget) {
        this.awpTarget = awpTarget;
    }

    public String getProgrammeTarget() {
        return programmeTarget;
    }

    public void setProgrammeTarget(String programmeTarget) {
        this.programmeTarget = programmeTarget;
    }

    public String getValueAchieved() {
        return valueAchieved;
    }

    public void setValueAchieved(String valueAchieved) {
        this.valueAchieved = valueAchieved;
    }

    public String getRequestedBudget() {
        return requestedBudget;
    }

    public void setRequestedBudget(String requestedBudget) {
        this.requestedBudget = requestedBudget;
    }

    public String getActualExpenditure() {
        return actualExpenditure;
    }

    public void setActualExpenditure(String actualExpenditure) {
        this.actualExpenditure = actualExpenditure;
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
        if (!(object instanceof ProgrammeDetails)) {
            return false;
        }
        ProgrammeDetails other = (ProgrammeDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.Programme[ id=" + getId() + " ]";
    }

    @Override
    public int compareTo(ProgrammeDetails o) {
        return this.getId().compareTo(o.getId());
    }

    /**
     * @return the component
     */
    public ComponentDetails getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(ComponentDetails component) {
        this.component = component;
    }

    /**
     * @return the measurementUnit
     */
    public MeasurementUnitDetails getMeasurementUnit() {
        return measurementUnit;
    }

    /**
     * @param measurementUnit the measurementUnit to set
     */
    public void setMeasurementUnit(MeasurementUnitDetails measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    /**
     * @return the implementingPartner
     */
    public ImplementingPartnerDetails getImplementingPartner() {
        return implementingPartner;
    }

    /**
     * @param implementingPartner the implementingPartner to set
     */
    public void setImplementingPartner(ImplementingPartnerDetails implementingPartner) {
        this.implementingPartner = implementingPartner;
    }

    /**
     * @return the sub-component details
     */
    public SubComponentDetails getSubComponent() {
        return subComponent;
    }

    /**
     * @param subComponent the sub-component details to set
     */
    public void setSubComponent(SubComponentDetails subComponent) {
        this.subComponent = subComponent;
    }

    private Integer id;
    private String activity;
    private String endPeriod;
    private String awpTarget;
    private String startPeriod;
    private String valueAchieved;
    private String requestedBudget;
    private String actualExpenditure;
    private String programmeTarget;
    private ComponentDetails component;
    private SubComponentDetails subComponent;
    private MeasurementUnitDetails measurementUnit;
    private ImplementingPartnerDetails implementingPartner;

}
