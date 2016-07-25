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
public class PerformanceIndicatorTypeDetails implements Serializable, Comparable<PerformanceIndicatorTypeDetails> {

    public PerformanceIndicatorTypeDetails() {
    }

    public PerformanceIndicatorTypeDetails(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerformanceIndicatorTypeDetails)) {
            return false;
        }
        PerformanceIndicatorTypeDetails other = (PerformanceIndicatorTypeDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PerformanceIndicatorType[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PerformanceIndicatorTypeDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Short id;
    private String type;

}
