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
public class CollectionCentreDetails implements Serializable, Comparable<CollectionCentreDetails> {

    public CollectionCentreDetails() {
    }

    public CollectionCentreDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getOperational() {
        return operational;
    }

    public void setOperational(Boolean operational) {
        this.operational = operational;
    }

    public PersonDetails getWardExtensionOfficer() {
        return wardExtensionOfficer;
    }

    public void setWardExtensionOfficer(PersonDetails wardExtensionOfficer) {
        this.wardExtensionOfficer = wardExtensionOfficer;
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
        if (!(object instanceof CollectionCentreDetails)) {
            return false;
        }
        CollectionCentreDetails other = (CollectionCentreDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.CollectionCentre[ id=" + id + " ]";
    }

    @Override
    public int compareTo(CollectionCentreDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private Boolean operational;
    private PersonDetails wardExtensionOfficer;
}
