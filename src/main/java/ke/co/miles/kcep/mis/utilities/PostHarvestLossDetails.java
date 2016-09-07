/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import ke.co.miles.kcep.mis.defaults.EntityRequests;

/**
 *
 * @author siech
 */
public class PostHarvestLossDetails extends EntityRequests implements Serializable {

    public PostHarvestLossDetails() {
    }

    public PostHarvestLossDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantityHarvested() {
        return quantityHarvested;
    }

    public void setQuantityHarvested(Double quantityHarvested) {
        this.quantityHarvested = quantityHarvested;
    }

    public Double getFamilyConsumption() {
        return familyConsumption;
    }

    public void setFamilyConsumption(Double familyConsumption) {
        this.familyConsumption = familyConsumption;
    }

    public Double getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Double quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Double getPostHarvestLosses() {
        return postHarvestLosses;
    }

    public void setPostHarvestLosses(Double postHarvestLosses) {
        this.postHarvestLosses = postHarvestLosses;
    }

    public PersonDetails getFarmer() {
        return farmer;
    }

    public void setFarmer(PersonDetails farmer) {
        this.farmer = farmer;
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
        if (!(object instanceof PostHarvestLossDetails)) {
            return false;
        }
        PostHarvestLossDetails other = (PostHarvestLossDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PostHarvestLoss[ id=" + id + " ]";
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Double quantityHarvested;
    private Double postHarvestLosses;
    private Double familyConsumption;
    private Double quantitySold;
    private PersonDetails farmer;

}
