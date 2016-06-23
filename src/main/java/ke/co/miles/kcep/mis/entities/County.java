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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "county", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "County.findByReqionId", query = "SELECT c FROM County c WHERE c.region.id = :regionId"),
    @NamedQuery(name = "County.findAll", query = "SELECT c FROM County c"),
    @NamedQuery(name = "County.findById", query = "SELECT c FROM County c WHERE c.id = :id"),
    @NamedQuery(name = "County.findByName", query = "SELECT c FROM County c WHERE c.name = :name")})
public class County implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "county")
    private List<SubCounty> subCountyList;
    @JoinColumn(name = "region", referencedColumnName = "id")
    @ManyToOne
    private Region region;
    @OneToMany(mappedBy = "county")
    private List<Procurement> procurementList;
    @OneToMany(mappedBy = "county")
    private List<Location> locationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "county")
    private List<TechnologyTargetCounty> technologyTargetCountyList;

    public County() {
    }

    public County(Integer id) {
        this.id = id;
    }

    public County(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @XmlTransient
    public List<SubCounty> getSubCountyList() {
        return subCountyList;
    }

    public void setSubCountyList(List<SubCounty> subCountyList) {
        this.subCountyList = subCountyList;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @XmlTransient
    public List<Procurement> getProcurementList() {
        return procurementList;
    }

    public void setProcurementList(List<Procurement> procurementList) {
        this.procurementList = procurementList;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @XmlTransient
    public List<TechnologyTargetCounty> getTechnologyTargetCountyList() {
        return technologyTargetCountyList;
    }

    public void setTechnologyTargetCountyList(List<TechnologyTargetCounty> technologyTargetCountyList) {
        this.technologyTargetCountyList = technologyTargetCountyList;
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
        if (!(object instanceof County)) {
            return false;
        }
        County other = (County) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.County[ id=" + id + " ]";
    }

}
