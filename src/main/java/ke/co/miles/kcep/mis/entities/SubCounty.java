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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "sub_county", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCounty.findByCountyId", query = "SELECT s FROM SubCounty s WHERE s.county.id =:countyId"),
    @NamedQuery(name = "SubCounty.findByNameAndCountyId", query = "SELECT s FROM SubCounty s WHERE s.name =:name AND s.county.id =:countyId"),
    @NamedQuery(name = "SubCounty.findAll", query = "SELECT s FROM SubCounty s"),
    @NamedQuery(name = "SubCounty.findById", query = "SELECT s FROM SubCounty s WHERE s.id = :id"),
    @NamedQuery(name = "SubCounty.findByName", query = "SELECT s FROM SubCounty s WHERE s.name = :name")})
public class SubCounty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "county", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private County county;
    @OneToMany(mappedBy = "subCounty")
    private List<Procurement> procurementList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subCounty")
    private List<Ward> wardList;
    @OneToMany(mappedBy = "subCounty")
    private List<Location> locationList;

    public SubCounty() {
    }

    public SubCounty(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    @XmlTransient
    public List<Procurement> getProcurementList() {
        return procurementList;
    }

    public void setProcurementList(List<Procurement> procurementList) {
        this.procurementList = procurementList;
    }

    @XmlTransient
    public List<Ward> getWardList() {
        return wardList;
    }

    public void setWardList(List<Ward> wardList) {
        this.wardList = wardList;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
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
        if (!(object instanceof SubCounty)) {
            return false;
        }
        SubCounty other = (SubCounty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubCounty[ id=" + id + " ]";
    }
    
}
