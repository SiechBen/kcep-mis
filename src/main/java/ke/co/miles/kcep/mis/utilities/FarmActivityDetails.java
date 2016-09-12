/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author siech
 */
public class FarmActivityDetails implements Serializable, Comparable<FarmActivityDetails> {

    public FarmActivityDetails() {
    }

    public FarmActivityDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public Date getDateDone() {
        return dateDone;
    }

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }

    public Double getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Double quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Double getQuantityHarvested() {
        return quantityHarvested;
    }

    public void setQuantityHarvested(Double quantityHarvested) {
        this.quantityHarvested = quantityHarvested;
    }

    public BigDecimal getAverageSellingPrice() {
        return averageSellingPrice;
    }

    public void setAverageSellingPrice(BigDecimal averageSellingPrice) {
        this.averageSellingPrice = averageSellingPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FarmActivityDetails)) {
            return false;
        }
        FarmActivityDetails other = (FarmActivityDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.FarmActivityDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(FarmActivityDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the farmer
     */
    public PersonDetails getFarmer() {
        return farmer;
    }

    /**
     * @param farmer the farmer to set
     */
    public void setFarmer(PersonDetails farmer) {
        this.farmer = farmer;
    }

    /**
     * @return the postHarvestLoss
     */
    public Double getPostHarvestLoss() {
        return postHarvestLoss;
    }

    /**
     * @param postHarvestLoss the postHarvestLoss to set
     */
    public void setPostHarvestLoss(Double postHarvestLoss) {
        this.postHarvestLoss = postHarvestLoss;
    }

    /**
     * @return the familyConsumption
     */
    public Double getFamilyConsumption() {
        return familyConsumption;
    }

    /**
     * @param familyConsumption the familyConsumption to set
     */
    public void setFamilyConsumption(Double familyConsumption) {
        this.familyConsumption = familyConsumption;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String yield;
    private Date dateDone;
    private Double quantitySold;
    private PersonDetails farmer;
    private Double postHarvestLoss;
    private Double quantityHarvested;
    private Double familyConsumption;
    private BigDecimal averageSellingPrice;

}
