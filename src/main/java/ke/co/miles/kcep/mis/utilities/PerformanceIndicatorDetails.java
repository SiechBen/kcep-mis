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

    private static final long serialVersionUID = 1L;
    private Short id;
    private String description;
    private Date baselineDate;
    private Double baselineValue;
    private ResultHierarchyDetails resultHierarchy;
    private PhenomenonDetails performanceIndicatorType;

}
