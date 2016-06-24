/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.Date;
import ke.co.miles.kcep.mis.entities.*;

/**
 *
 * @author siech
 */
public class FarmerGroupRecordDetails implements Serializable, Comparable<FarmerGroupRecordDetails> {

    private static final long serialVersionUID = 1L;

    public FarmerGroupRecordDetails() {
    }

    public FarmerGroupRecordDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfInputsCollection() {
        return dateOfInputsCollection;
    }

    public void setDateOfInputsCollection(Date dateOfInputsCollection) {
        this.dateOfInputsCollection = dateOfInputsCollection;
    }

    public Boolean getFirstWeedingDone() {
        return firstWeedingDone;
    }

    public void setFirstWeedingDone(Boolean firstWeedingDone) {
        this.firstWeedingDone = firstWeedingDone;
    }

    public Boolean getSecondWeedingDone() {
        return secondWeedingDone;
    }

    public void setSecondWeedingDone(Boolean secondWeedingDone) {
        this.secondWeedingDone = secondWeedingDone;
    }

    public Integer getQuantityHarvestedFromKcepFarm() {
        return quantityHarvestedFromKcepFarm;
    }

    public void setQuantityHarvestedFromKcepFarm(Integer quantityHarvestedFromKcepFarm) {
        this.quantityHarvestedFromKcepFarm = quantityHarvestedFromKcepFarm;
    }

    public Integer getQuantitySoldFromKcepFarm() {
        return quantitySoldFromKcepFarm;
    }

    public void setQuantitySoldFromKcepFarm(Integer quantitySoldFromKcepFarm) {
        this.quantitySoldFromKcepFarm = quantitySoldFromKcepFarm;
    }

    public Long getAverageSellingPricePerBag() {
        return averageSellingPricePerBag;
    }

    public void setAverageSellingPricePerBag(Long averageSellingPricePerBag) {
        this.averageSellingPricePerBag = averageSellingPricePerBag;
    }

    public FarmerGroup getFarmerGroup() {
        return farmerGroup;
    }

    public void setFarmerGroup(FarmerGroup farmerGroup) {
        this.farmerGroup = farmerGroup;
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
        if (!(object instanceof FarmerGroupRecordDetails)) {
            return false;
        }
        FarmerGroupRecordDetails other = (FarmerGroupRecordDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.FarmerGroupRecord[ id=" + id + " ]";
    }

    @Override
    public int compareTo(FarmerGroupRecordDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private Boolean firstWeedingDone;
    private FarmerGroup farmerGroup;
    private Date dateOfInputsCollection;
    private Boolean secondWeedingDone;
    private Long averageSellingPricePerBag;
    private Integer quantitySoldFromKcepFarm;
    private Integer quantityHarvestedFromKcepFarm;

}
