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
public class DisseminationOfResultsDetails implements Serializable, Comparable<DisseminationOfResultsDetails> {

    public DisseminationOfResultsDetails() {
    }

    public DisseminationOfResultsDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoOfResultSetInWesternRegion() {
        return noOfResultSetInWesternRegion;
    }

    public void setNoOfResultSetInWesternRegion(Integer noOfResultSetInWesternRegion) {
        this.noOfResultSetInWesternRegion = noOfResultSetInWesternRegion;
    }

    public Integer getNoOfResultSetInEasternRegion() {
        return noOfResultSetInEasternRegion;
    }

    public void setNoOfResultSetInEasternRegion(Integer noOfResultSetInEasternRegion) {
        this.noOfResultSetInEasternRegion = noOfResultSetInEasternRegion;
    }

    public PersonDetails getKalroOfficer() {
        return kalroOfficer;
    }

    public void setKalroOfficer(PersonDetails kALROofficer) {
        this.kalroOfficer = kALROofficer;
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
        if (!(object instanceof DisseminationOfResultsDetails)) {
            return false;
        }
        DisseminationOfResultsDetails other = (DisseminationOfResultsDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.DisseminationOfResults[ id=" + id + " ]";
    }

    @Override
    public int compareTo(DisseminationOfResultsDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private Integer noOfResultSetInWesternRegion;
    private Integer noOfResultSetInEasternRegion;
    private PersonDetails kalroOfficer;

}
