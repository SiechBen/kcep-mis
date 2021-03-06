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
public class PhenomenonDetails implements Serializable, Comparable<PhenomenonDetails> {

    public PhenomenonDetails() {
    }

    public PhenomenonDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryDetails getCategory() {
        return category;
    }

    public void setCategory(CategoryDetails category) {
        this.category = category;
    }

    public PhenomenonTypeDetails getPhenomenonType() {
        return phenomenonType;
    }

    public void setPhenomenonType(PhenomenonTypeDetails phenomenonType) {
        this.phenomenonType = phenomenonType;
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
        if (!(object instanceof PhenomenonDetails)) {
            return false;
        }
        PhenomenonDetails other = (PhenomenonDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.PhenomenonDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PhenomenonDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private CategoryDetails category;
    private PhenomenonTypeDetails phenomenonType;

}
