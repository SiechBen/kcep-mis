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
public class CountyDetails implements Serializable, Comparable<CountyDetails> {

    private static final long serialVersionUID = 1L;

    public CountyDetails() {
    }

    public CountyDetails(Short id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CountyDetails)) {
            return false;
        }
        CountyDetails other = (CountyDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.County[ name=" + name + " ]";
    }

    @Override
    public int compareTo(CountyDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the region
     */
    public RegionDetail getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(RegionDetail region) {
        this.region = region;
    }

    private Short id;
    private String name;
    private RegionDetail region;

}
