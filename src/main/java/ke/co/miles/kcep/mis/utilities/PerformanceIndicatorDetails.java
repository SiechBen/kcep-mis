/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.Date;

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

    public Short getYearOfUse() {
        return yearOfUse;
    }

    public void setYearOfUse(Short yearOfUse) {
        this.yearOfUse = yearOfUse;
    }

    public Double getActualValue() {
        return actualValue;
    }

    public void setActualValue(Double actualValue) {
        this.actualValue = actualValue;
    }

    public Double getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Double expectedValue) {
        this.expectedValue = expectedValue;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public ResultHierarchyDetails getResultHierarchy() {
        return resultHierarchy;
    }

    public void setResultHierarchy(ResultHierarchyDetails resultHierarchy) {
        this.resultHierarchy = resultHierarchy;
    }

    public PerformanceIndicatorTypeDetails getPerformanceIndicatorType() {
        return performanceIndicatorType;
    }

    public void setPerformanceIndicatorType(PerformanceIndicatorTypeDetails performanceIndicatorType) {
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
        return "ke.co.miles.kcep.mis.entities.PerformanceIndicator[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PerformanceIndicatorDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Short id;
    private String description;
    private Date baselineDate;
    private Double baselineValue;
    private Short yearOfUse;
    private Double actualValue;
    private Double expectedValue;
    private Double ratio;
    private ResultHierarchyDetails resultHierarchy;
    private PerformanceIndicatorTypeDetails performanceIndicatorType;

}
