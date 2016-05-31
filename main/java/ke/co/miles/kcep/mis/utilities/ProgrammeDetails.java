
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgrammeDetails)) {
            return false;
        }
        ProgrammeDetails other = (ProgrammeDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Programme[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ProgrammeDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String activity;
    private String startPeriod;
    private String endPeriod;
    private String unit;
    private String awpTarget;
    private String programmeTarget;
    private String valueAchieved;
    private String requestedBudget;
    private String actualExpenditure;

}
