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
@Table(name = "farm", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Farm.findAll", query = "SELECT f FROM Farm f"),
    @NamedQuery(name = "Farm.findById", query = "SELECT f FROM Farm f WHERE f.id = :id"),
    @NamedQuery(name = "Farm.findByPlotSize", query = "SELECT f FROM Farm f WHERE f.plotSize = :plotSize")})
public class Farm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "plot_size")
    private String plotSize;
    @JoinColumn(name = "location", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Location location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farm")
    private List<FarmActivity> farmActivityList;

    public Farm() {
    }

    public Farm(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlotSize() {
        return plotSize;
    }

    public void setPlotSize(String plotSize) {
        this.plotSize = plotSize;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @XmlTransient
    public List<FarmActivity> getFarmActivityList() {
        return farmActivityList;
    }

    public void setFarmActivityList(List<FarmActivity> farmActivityList) {
        this.farmActivityList = farmActivityList;
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
        if (!(object instanceof Farm)) {
            return false;
        }
        Farm other = (Farm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Farm[ id=" + id + " ]";
    }
    
}
