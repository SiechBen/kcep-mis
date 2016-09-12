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
public class WardDetails implements Serializable, Comparable<WardDetails> {

    private static final long serialVersionUID = 1L;

    public WardDetails() {
    }

    public WardDetails(Short id) {
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

    public SubCountyDetails getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(SubCountyDetails subCounty) {
        this.subCounty = subCounty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof WardDetails)) {
            return false;
        }
        WardDetails other = (WardDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.WardDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(WardDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Short id;
    private String name;
    private SubCountyDetails subCounty;
}
