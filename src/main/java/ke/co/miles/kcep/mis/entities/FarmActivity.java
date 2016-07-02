/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "farm_activity", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FarmActivity.findAll", query = "SELECT f FROM FarmActivity f"),
    @NamedQuery(name = "FarmActivity.findById", query = "SELECT f FROM FarmActivity f WHERE f.id = :id"),
    @NamedQuery(name = "FarmActivity.findByName", query = "SELECT f FROM FarmActivity f WHERE f.name = :name"),
    @NamedQuery(name = "FarmActivity.findByYield", query = "SELECT f FROM FarmActivity f WHERE f.yield = :yield"),
    @NamedQuery(name = "FarmActivity.findByDate", query = "SELECT f FROM FarmActivity f WHERE f.date = :date"),
    @NamedQuery(name = "FarmActivity.findByQuantitySold", query = "SELECT f FROM FarmActivity f WHERE f.quantitySold = :quantitySold"),
    @NamedQuery(name = "FarmActivity.findByQuantityHarvested", query = "SELECT f FROM FarmActivity f WHERE f.quantityHarvested = :quantityHarvested"),
    @NamedQuery(name = "FarmActivity.findByAverageSellingPricePer", query = "SELECT f FROM FarmActivity f WHERE f.averageSellingPricePer = :averageSellingPricePer")})
public class FarmActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "yield")
    private Double yield;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "quantity_sold")
    private Double quantitySold;
    @Column(name = "quantity_harvested")
    private Double quantityHarvested;
    @Column(name = "average_selling_price_per")
    private Long averageSellingPricePer;
    @JoinColumn(name = "farm", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Farm farm;

    public FarmActivity() {
    }

    public FarmActivity(Integer id) {
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

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Long getAverageSellingPricePer() {
        return averageSellingPricePer;
    }

    public void setAverageSellingPricePer(Long averageSellingPricePer) {
        this.averageSellingPricePer = averageSellingPricePer;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
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
        if (!(object instanceof FarmActivity)) {
            return false;
        }
        FarmActivity other = (FarmActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.FarmActivity[ id=" + id + " ]";
    }
    
}
