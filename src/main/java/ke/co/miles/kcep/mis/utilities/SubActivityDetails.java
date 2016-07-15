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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public MeasurementUnitDetails getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnitDetails measurementUnit) {
        this.measurementUnit = measurementUnit;
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
        if (!(object instanceof SubActivityDetails)) {
            return false;
        }
        SubActivityDetails other = (SubActivityDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubActivityDetails[ id=" + getId() + " ]";
    }

    @Override
    public int compareTo(SubActivityDetails o) {
        return this.getId().compareTo(o.getId());
    }

    /**
     * @return the actualExpenditure
     */
    public BigDecimal getActualExpenditure() {
        return actualExpenditure;
    }

    /**
     * @param actualExpenditure the actualExpenditure to set
     */
    public void setActualExpenditure(BigDecimal actualExpenditure) {
        this.actualExpenditure = actualExpenditure;
    }

    /**
     * @return the activityPlanning
     */
    public ActivityPlanningDetails getActivityPlanning() {
        return activityPlanning;
    }

    /**
     * @param activityPlanning the activityPlanning to set
     */
    public void setActivityPlanning(ActivityPlanningDetails activityPlanning) {
        this.activityPlanning = activityPlanning;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;
    private Date startDate;
    private Date endDate;
    private BigDecimal actualExpenditure;
    private ActivityPlanningDetails activityPlanning;
    private MeasurementUnitDetails measurementUnit;

}
