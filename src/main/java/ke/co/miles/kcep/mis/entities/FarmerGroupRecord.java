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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "farmer_group_record", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FarmerGroupRecord.findAll", query = "SELECT f FROM FarmerGroupRecord f"),
    @NamedQuery(name = "FarmerGroupRecord.findById", query = "SELECT f FROM FarmerGroupRecord f WHERE f.id = :id"),
    @NamedQuery(name = "FarmerGroupRecord.findByDateOfInputsCollection", query = "SELECT f FROM FarmerGroupRecord f WHERE f.dateOfInputsCollection = :dateOfInputsCollection"),
    @NamedQuery(name = "FarmerGroupRecord.findByFirstWeedingDone", query = "SELECT f FROM FarmerGroupRecord f WHERE f.firstWeedingDone = :firstWeedingDone"),
    @NamedQuery(name = "FarmerGroupRecord.findBySecondWeedingDone", query = "SELECT f FROM FarmerGroupRecord f WHERE f.secondWeedingDone = :secondWeedingDone"),
    @NamedQuery(name = "FarmerGroupRecord.findByQuantityHarvestedFromKcepFarm", query = "SELECT f FROM FarmerGroupRecord f WHERE f.quantityHarvestedFromKcepFarm = :quantityHarvestedFromKcepFarm"),
    @NamedQuery(name = "FarmerGroupRecord.findByQuantitySoldFromKcepFarm", query = "SELECT f FROM FarmerGroupRecord f WHERE f.quantitySoldFromKcepFarm = :quantitySoldFromKcepFarm"),
    @NamedQuery(name = "FarmerGroupRecord.findByAverageSellingPricePerBag", query = "SELECT f FROM FarmerGroupRecord f WHERE f.averageSellingPricePerBag = :averageSellingPricePerBag")})
public class FarmerGroupRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_of_inputs_collection")
    @Temporal(TemporalType.DATE)
    private Date dateOfInputsCollection;
    @Column(name = "first_weeding_done")
    private Boolean firstWeedingDone;
    @Column(name = "second_weeding_done")
    private Boolean secondWeedingDone;
    @Column(name = "quantity_harvested_from_kcep_farm")
    private Integer quantityHarvestedFromKcepFarm;
    @Column(name = "quantity_sold_from_kcep_farm")
    private Integer quantitySoldFromKcepFarm;
    @Column(name = "average_selling_price_per_bag")
    private Long averageSellingPricePerBag;
    @JoinColumn(name = "farmer_group", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FarmerGroup farmerGroup;

    public FarmerGroupRecord() {
    }

    public FarmerGroupRecord(Integer id) {
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
        if (!(object instanceof FarmerGroupRecord)) {
            return false;
        }
        FarmerGroupRecord other = (FarmerGroupRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.FarmerGroupRecord[ id=" + id + " ]";
    }

}
