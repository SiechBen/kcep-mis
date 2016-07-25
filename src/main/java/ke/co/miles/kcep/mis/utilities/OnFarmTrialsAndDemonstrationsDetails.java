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
public class OnFarmTrialsAndDemonstrationsDetails implements Serializable, Comparable<OnFarmTrialsAndDemonstrationsDetails> {

    private static final long serialVersionUID = 1L;

    public OnFarmTrialsAndDemonstrationsDetails() {
    }

    public OnFarmTrialsAndDemonstrationsDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNoOfOnFarmTrials() {
        return noOfOnFarmTrials;
    }

    public void setNoOfOnFarmTrials(Short noOfOnFarmTrials) {
        this.noOfOnFarmTrials = noOfOnFarmTrials;
    }

    public Short getNoOfDemonstrations() {
        return noOfDemonstrations;
    }

    public void setNoOfDemonstrations(Short noOfDemonstrations) {
        this.noOfDemonstrations = noOfDemonstrations;
    }

    public String getTargetLocations() {
        return targetLocations;
    }

    public void setTargetLocations(String targetLocations) {
        this.targetLocations = targetLocations;
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
        if (!(object instanceof OnFarmTrialsAndDemonstrationsDetails)) {
            return false;
        }
        OnFarmTrialsAndDemonstrationsDetails other = (OnFarmTrialsAndDemonstrationsDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.OnFarmTrialsAndDemonstrations[ id=" + id + " ]";
    }

    @Override
    public int compareTo(OnFarmTrialsAndDemonstrationsDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String targetLocations;
    private String attendanceSheet;
    private Short noOfOnFarmTrials;
    private PersonDetails kalroOfficer;
    private Short noOfDemonstrations;

}
