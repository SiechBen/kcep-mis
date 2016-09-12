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

    private static final long serialVersionUID = 1L;

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
        if (!(object instanceof DisseminationOfResultsDetails)) {
            return false;
        }
        DisseminationOfResultsDetails other = (DisseminationOfResultsDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.DisseminationOfResultsDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(DisseminationOfResultsDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private PersonDetails kalroOfficer;
    private Integer noOfResultSetInEasternRegion;
    private Integer noOfResultSetInWesternRegion;

}
