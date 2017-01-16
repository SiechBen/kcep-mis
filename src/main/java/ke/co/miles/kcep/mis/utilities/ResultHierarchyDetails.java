/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author siech
 */
public class ResultHierarchyDetails implements Serializable, Comparable<ResultHierarchyDetails> {

    public ResultHierarchyDetails() {
    }

    public ResultHierarchyDetails(Short id) {
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

    public PhenomenonDetails getComponent() {
        return component;
    }

    public void setComponent(PhenomenonDetails component) {
        this.component = component;
    }

    public PhenomenonDetails getSubComponent() {
        return subComponentDetails;
    }

    public void setSubComponent(PhenomenonDetails subComponentDetails) {
        this.subComponentDetails = subComponentDetails;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ResultHierarchyDetails)) {
            return false;
        }
        ResultHierarchyDetails other = (ResultHierarchyDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ResultHierarchyDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ResultHierarchyDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the subComponentDetails
     */
    public PhenomenonDetails getSubComponentDetails() {
        return subComponentDetails;
    }

    /**
     * @param subComponentDetails the subComponentDetails to set
     */
    public void setSubComponentDetails(PhenomenonDetails subComponentDetails) {
        this.subComponentDetails = subComponentDetails;
    }

    /**
     * @return the performanceIndicatorList
     */
    public List<PerformanceIndicatorDetails> getPerformanceIndicatorList() {
        return performanceIndicatorList;
    }

    /**
     * @param performanceIndicatorList the performanceIndicatorList to set
     */
    public void setPerformanceIndicatorList(List<PerformanceIndicatorDetails> performanceIndicatorList) {
        this.performanceIndicatorList = performanceIndicatorList;
    }

    private static final long serialVersionUID = 1L;
    private Short id;
    private String description;
    private PhenomenonDetails component;
    private PhenomenonDetails subComponentDetails;
    private List<PerformanceIndicatorDetails> performanceIndicatorList;

}
