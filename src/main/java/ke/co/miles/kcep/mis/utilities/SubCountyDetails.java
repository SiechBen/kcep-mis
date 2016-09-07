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
public class SubCountyDetails implements Serializable, Comparable<SubCountyDetails> {

    private static final long serialVersionUID = 1L;

    public SubCountyDetails() {
    }

    public SubCountyDetails(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountyDetails getCounty() {
        return county;
    }

    public void setCounty(CountyDetails county) {
        this.county = county;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SubCountyDetails)) {
            return false;
        }
        SubCountyDetails other = (SubCountyDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubCounty[ sub-county=" + getName() + " ]";
    }

    @Override
    public int compareTo(SubCountyDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Short id;
    private String name;
    private CountyDetails county;

}
