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
public class SoilFertilityPackageDetails implements Serializable, Comparable<SoilFertilityPackageDetails> {

    private static final long serialVersionUID = 1L;

    public SoilFertilityPackageDetails() {
    }

    public SoilFertilityPackageDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNoOfSamplingAndAnalysisActivities() {
        return noOfSamplingAndAnalysisActivities;
    }

    public void setNoOfSamplingAndAnalysisActivities(Short noOfSamplingAndAnalysisActivities) {
        this.noOfSamplingAndAnalysisActivities = noOfSamplingAndAnalysisActivities;
    }

    public Short getNoOfPackagesDeveloped() {
        return noOfPackagesDeveloped;
    }

    public void setNoOfPackagesDeveloped(Short noOfPackagesDeveloped) {
        this.noOfPackagesDeveloped = noOfPackagesDeveloped;
    }

    public String getTargetLocations() {
        return targetLocations;
    }

    public void setTargetLocations(String targetLocations) {
        this.targetLocations = targetLocations;
    }

    public Short getNoOfMeetingsForEVoucherDefinition() {
        return noOfMeetingsForEVoucherDefinition;
    }

    public void setNoOfMeetingsForEVoucherDefinition(Short noOfMeetingsForEVoucherDefinition) {
        this.noOfMeetingsForEVoucherDefinition = noOfMeetingsForEVoucherDefinition;
    }

    public PersonDetails getKalroOfficer() {
        return kalroOfficer;
    }

    public void setKalroOfficer(PersonDetails kalroOfficer) {
        this.kalroOfficer = kalroOfficer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SoilFertilityPackageDetails)) {
            return false;
        }
        SoilFertilityPackageDetails other = (SoilFertilityPackageDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SoilFertilityPackage[ id=" + id + " ]";
    }

    @Override
    public int compareTo(SoilFertilityPackageDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String targetLocations;
    private PersonDetails kalroOfficer;
    private Short noOfPackagesDeveloped;
    private Short noOfSamplingAndAnalysisActivities;
    private Short noOfMeetingsForEVoucherDefinition;

}
