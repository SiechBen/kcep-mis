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
public class IndicatorHierarchyDetails implements Serializable, Comparable<IndicatorHierarchyDetails> {

    public IndicatorHierarchyDetails() {
    }

    public IndicatorHierarchyDetails(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicatorHierarchyDetails)) {
            return false;
        }
        IndicatorHierarchyDetails other = (IndicatorHierarchyDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.IndicatorHierarchy[ id=" + id + " ]";
    }

    @Override
    public int compareTo(IndicatorHierarchyDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;
    private ComponentDetails component;
    private SubComponentDetails subComponentDetails;

}
