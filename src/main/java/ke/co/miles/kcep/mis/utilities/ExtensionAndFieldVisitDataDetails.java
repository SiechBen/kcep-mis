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
public class ExtensionAndFieldVisitDataDetails implements Serializable, Comparable<ExtensionAndFieldVisitDataDetails> {

    private static final long serialVersionUID = 1L;

    public ExtensionAndFieldVisitDataDetails() {
    }

    public ExtensionAndFieldVisitDataDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfPeopleSeekingOrOfferedAdvisoryServices() {
        return numberOfPeopleSeekingOrOfferedAdvisoryServices;
    }

    public void setNumberOfPeopleSeekingOrOfferedAdvisoryServices(Integer numberOfPeopleSeekingOrOfferedAdvisoryServices) {
        this.numberOfPeopleSeekingOrOfferedAdvisoryServices = numberOfPeopleSeekingOrOfferedAdvisoryServices;
    }

    public String getNatureOfAdvisoryServices() {
        return natureOfAdvisoryServices;
    }

    public void setNatureOfAdvisoryServices(String natureOfAdvisoryServices) {
        this.natureOfAdvisoryServices = natureOfAdvisoryServices;
    }

    public Integer getNumberOfFieldVisitsConducted() {
        return numberOfFieldVisitsConducted;
    }

    public void setNumberOfFieldVisitsConducted(Integer numberOfFieldVisitsConducted) {
        this.numberOfFieldVisitsConducted = numberOfFieldVisitsConducted;
    }

    public Integer getNumberOfFarmersVisited() {
        return numberOfFarmersVisited;
    }

    public void setNumberOfFarmersVisited(Integer numberOfFarmersVisited) {
        this.numberOfFarmersVisited = numberOfFarmersVisited;
    }

    public String getFieldVisitsWardLocations() {
        return fieldVisitsWardLocations;
    }

    public void setFieldVisitsWardLocations(String fieldVisitsWardLocations) {
        this.fieldVisitsWardLocations = fieldVisitsWardLocations;
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
        if (!(object instanceof ExtensionAndFieldVisitDataDetails)) {
            return false;
        }
        ExtensionAndFieldVisitDataDetails other = (ExtensionAndFieldVisitDataDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ExtensionAndFieldVisitDataDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ExtensionAndFieldVisitDataDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String fieldVisitsWardLocations;
    private String natureOfAdvisoryServices;
    private Integer numberOfFarmersVisited;
    private PersonDetails wardExtensionOfficer;
    private Integer numberOfFieldVisitsConducted;
    private Integer numberOfPeopleSeekingOrOfferedAdvisoryServices;

}
