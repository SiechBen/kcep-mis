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
public class ValidationWorkshopsDetails implements Serializable, Comparable<ValidationWorkshopsDetails> {

    private static final long serialVersionUID = 1L;

    public ValidationWorkshopsDetails() {
    }

    public ValidationWorkshopsDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoInWesternRegion() {
        return noInWesternRegion;
    }

    public void setNoInWesternRegion(Integer noInWesternRegion) {
        this.noInWesternRegion = noInWesternRegion;
    }

    public Integer getNoInEasternRegion() {
        return noInEasternRegion;
    }

    public void setNoInEasternRegion(Integer noInEasternRegion) {
        this.noInEasternRegion = noInEasternRegion;
    }

    public String getAttendanceSheet() {
        return attendanceSheet;
    }

    public void setAttendanceSheet(String attendanceSheet) {
        this.attendanceSheet = attendanceSheet;
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
        if (!(object instanceof ValidationWorkshopsDetails)) {
            return false;
        }
        ValidationWorkshopsDetails other = (ValidationWorkshopsDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ValidationWorkshops[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ValidationWorkshopsDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String attendanceSheet;
    private PersonDetails kalroOfficer;
    private Integer noInEasternRegion;
    private Integer noInWesternRegion;

}
