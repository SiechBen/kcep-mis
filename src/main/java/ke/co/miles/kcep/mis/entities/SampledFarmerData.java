/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "sampled_farmer_data", catalog = "kcep_mis", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SampledFarmerData.findAll", query = "SELECT s FROM SampledFarmerData s"),
    @NamedQuery(name = "SampledFarmerData.findById", query = "SELECT s FROM SampledFarmerData s WHERE s.id = :id"),
    @NamedQuery(name = "SampledFarmerData.findBySeason", query = "SELECT s FROM SampledFarmerData s WHERE s.season = :season"),
    @NamedQuery(name = "SampledFarmerData.findByProductivityPerCropPerFarmer", query = "SELECT s FROM SampledFarmerData s WHERE s.productivityPerCropPerFarmer = :productivityPerCropPerFarmer"),
    @NamedQuery(name = "SampledFarmerData.findByPostHarvestLosses", query = "SELECT s FROM SampledFarmerData s WHERE s.postHarvestLosses = :postHarvestLosses")})
public class SampledFarmerData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Short season;
    @Size(max = 100)
    @Column(name = "productivity_per_crop_per_farmer", length = 100)
    private String productivityPerCropPerFarmer;
    @Size(max = 100)
    @Column(name = "post_harvest_losses", length = 100)
    private String postHarvestLosses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sampledFarmerData")
    private List<NumberOfFarmers> numberOfFarmersList;
    @JoinColumn(name = "ward_extension_officer", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person wardExtensionOfficer;

    public SampledFarmerData() {
    }

    public SampledFarmerData(Integer id) {
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

    @XmlTransient
    public List<NumberOfFarmers> getNumberOfFarmersList() {
        return numberOfFarmersList;
    }

    public void setNumberOfFarmersList(List<NumberOfFarmers> numberOfFarmersList) {
        this.numberOfFarmersList = numberOfFarmersList;
    }

    public Person getWardExtensionOfficer() {
        return wardExtensionOfficer;
    }

    public void setWardExtensionOfficer(Person wardExtensionOfficer) {
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
        if (!(object instanceof SampledFarmerData)) {
            return false;
        }
        SampledFarmerData other = (SampledFarmerData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SampledFarmerData[ id=" + id + " ]";
    }
    
}
