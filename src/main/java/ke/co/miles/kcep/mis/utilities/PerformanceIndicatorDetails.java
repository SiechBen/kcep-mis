/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author siech
 */
public class PerformanceIndicatorDetails implements Serializable, Comparable<PerformanceIndicatorDetails> {

    public PerformanceIndicatorDetails() {
    }

    public PerformanceIndicatorDetails(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBaselineDate() {
        return baselineDate;
    }

    public void setBaselineDate(Date baselineDate) {
        this.baselineDate = baselineDate;
    }

    public Double getBaselineValue() {
        return baselineValue;
    }

    public void setBaselineValue(Double baselineValue) {
        this.baselineValue = baselineValue;
    }

    public ResultHierarchyDetails getResultHierarchy() {
        return resultHierarchy;
    }

    public void setResultHierarchy(ResultHierarchyDetails resultHierarchy) {
        this.resultHierarchy = resultHierarchy;
    }

    public PhenomenonDetails getPerformanceIndicatorType() {
        return performanceIndicatorType;
    }

    public void setPerformanceIndicatorType(PhenomenonDetails performanceIndicatorType) {
        this.performanceIndicatorType = performanceIndicatorType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerformanceIndicatorDetails)) {
            return false;
        }
        PerformanceIndicatorDetails other = (PerformanceIndicatorDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PerformanceIndicatorDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the appraisalTarget
     */
    public Double getAppraisalTarget() {
        return appraisalTarget;
    }

    /**
     * @param appraisalTarget the appraisalTarget to set
     */
    public void setAppraisalTarget(Double appraisalTarget) {
        this.appraisalTarget = appraisalTarget;
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
     * @return the performanceIndicatorValuesList
     */
    public List<PerformanceIndicatorValuesDetails> getPerformanceIndicatorValuesList() {
        return performanceIndicatorValuesList;
    }

    /**
     * @param performanceIndicatorValuesList the performanceIndicatorValuesList
     * to set
     */
    public void setPerformanceIndicatorValuesList(List<PerformanceIndicatorValuesDetails> performanceIndicatorValuesList) {
        this.performanceIndicatorValuesList = performanceIndicatorValuesList;
    }

    /**
     * @return the accumulatedActual
     */
    public Double getAccumulatedActual() {
        return accumulatedActual;
    }

    /**
     * @param accumulatedActual the accumulatedActual to set
     */
    public void setAccumulatedActual(Double accumulatedActual) {
        this.accumulatedActual = accumulatedActual;
    }

    private static final long serialVersionUID = 1L;
    private Short id;
    private String description;
    private Date baselineDate;
    private Double baselineValue;
    private Double appraisalTarget;
    private Double accumulatedActual;
    private ResultHierarchyDetails resultHierarchy;
    private MeasurementUnitDetails measurementUnit;
    private PhenomenonDetails performanceIndicatorType;
    private List<PerformanceIndicatorValuesDetails> performanceIndicatorValuesList;

}
