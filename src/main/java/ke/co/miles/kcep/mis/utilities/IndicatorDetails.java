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
public class IndicatorDetails implements Serializable, Comparable<IndicatorDetails> {

    public IndicatorDetails() {
    }

    public IndicatorDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaselineDate() {
        return baselineDate;
    }

    public void setBaselineDate(String baselineDate) {
        this.baselineDate = baselineDate;
    }

    public String getBaselineValue() {
        return baselineValue;
    }

    public void setBaselineValue(String baselineValue) {
        this.baselineValue = baselineValue;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
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

    public IndicatorHierarchyDetails getIndicatorHierarchy() {
        return indicatorHierarchy;
    }

    public void setIndicatorHierarchy(IndicatorHierarchyDetails indicatorHierarchy) {
        this.indicatorHierarchy = indicatorHierarchy;
    }

    public IndicatorTypeDetails getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(IndicatorTypeDetails indicatorType) {
        this.indicatorType = indicatorType;
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
        if (!(object instanceof IndicatorDetails)) {
            return false;
        }
        IndicatorDetails other = (IndicatorDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Indicator[ id=" + id + " ]";
    }

    @Override
    public int compareTo(IndicatorDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String type;
    private String description;
    private String baselineDate;
    private String baselineValue;
    private Date year;
    private Double actualValue;
    private Double expectedValue;
    private Double ratio;
    private IndicatorHierarchyDetails indicatorHierarchy;
    private IndicatorTypeDetails indicatorType;

}
