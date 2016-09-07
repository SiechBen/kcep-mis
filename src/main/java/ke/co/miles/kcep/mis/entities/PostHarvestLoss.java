/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "post_harvest_loss", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostHarvestLoss.findByFarmerId", query = "SELECT p FROM PostHarvestLoss p WHERE p.farmer.id = :farmerId"),
    @NamedQuery(name = "PostHarvestLoss.findAll", query = "SELECT p FROM PostHarvestLoss p"),
    @NamedQuery(name = "PostHarvestLoss.findById", query = "SELECT p FROM PostHarvestLoss p WHERE p.id = :id"),
    @NamedQuery(name = "PostHarvestLoss.findByQuantityHarvested", query = "SELECT p FROM PostHarvestLoss p WHERE p.quantityHarvested = :quantityHarvested"),
    @NamedQuery(name = "PostHarvestLoss.findByFamilyConsumption", query = "SELECT p FROM PostHarvestLoss p WHERE p.familyConsumption = :familyConsumption"),
    @NamedQuery(name = "PostHarvestLoss.findByQuantitySold", query = "SELECT p FROM PostHarvestLoss p WHERE p.quantitySold = :quantitySold"),
    @NamedQuery(name = "PostHarvestLoss.findByPostHarvestLosses", query = "SELECT p FROM PostHarvestLoss p WHERE p.postHarvestLosses = :postHarvestLosses")})
public class PostHarvestLoss implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity_harvested")
    private Double quantityHarvested;
    @Column(name = "family_consumption")
    private Double familyConsumption;
    @Column(name = "quantity_sold")
    private Double quantitySold;
    @Column(name = "post_harvest_losses")
    private Double postHarvestLosses;
    @JoinColumn(name = "farmer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person farmer;

    public PostHarvestLoss() {
    }

    public PostHarvestLoss(Integer id) {
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

    public Person getFarmer() {
        return farmer;
    }

    public void setFarmer(Person farmer) {
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
        if (!(object instanceof PostHarvestLoss)) {
            return false;
        }
        PostHarvestLoss other = (PostHarvestLoss) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PostHarvestLoss[ id=" + id + " ]";
    }

}
