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
public class SampledFarmerDataDetails implements Serializable, Comparable<SampledFarmerDataDetails> {

    private static final long serialVersionUID = 1L;

    public SampledFarmerDataDetails() {
    }

    public SampledFarmerDataDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getSeason() {
        return season;
    }

    public void setSeason(Short season) {
        this.season = season;
    }

    public String getProductivityPerCropPerFarmer() {
        return productivityPerCropPerFarmer;
    }

    public void setProductivityPerCropPerFarmer(String productivityPerCropPerFarmer) {
        this.productivityPerCropPerFarmer = productivityPerCropPerFarmer;
    }

    public String getPostHarvestLosses() {
        return postHarvestLosses;
    }

    public void setPostHarvestLosses(String postHarvestLosses) {
        this.postHarvestLosses = postHarvestLosses;
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
        if (!(object instanceof SampledFarmerDataDetails)) {
            return false;
        }
        SampledFarmerDataDetails other = (SampledFarmerDataDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.SampledFarmerData[ id=" + id + " ]";
    }

    @Override
    public int compareTo(SampledFarmerDataDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private Short season;
    private String postHarvestLosses;
    private PersonDetails wardExtensionOfficer;
    private String productivityPerCropPerFarmer;

}
