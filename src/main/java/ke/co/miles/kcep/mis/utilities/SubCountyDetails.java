/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import ke.co.miles.kcep.mis.entities.*;
import java.io.Serializable;

/**
 *
 * @author siech
 */
public class SubCountyDetails implements Serializable, Comparable<SubCountyDetails> {

    public SubCountyDetails() {
    }

    public SubCountyDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubCountyDetails)) {
            return false;
        }
        SubCountyDetails other = (SubCountyDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubCounty[ id=" + id + " ]";
    }

    @Override
    public int compareTo(SubCountyDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String name;
    private CountyDetails county;

}
