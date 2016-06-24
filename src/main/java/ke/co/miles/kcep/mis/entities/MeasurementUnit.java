/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "measurement_unit", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeasurementUnit.findAll", query = "SELECT m FROM MeasurementUnit m"),
    @NamedQuery(name = "MeasurementUnit.findById", query = "SELECT m FROM MeasurementUnit m WHERE m.id = :id"),
    @NamedQuery(name = "MeasurementUnit.findByUnit", query = "SELECT m FROM MeasurementUnit m WHERE m.unit = :unit"),
    @NamedQuery(name = "MeasurementUnit.findBySymbol", query = "SELECT m FROM MeasurementUnit m WHERE m.symbol = :symbol"),
    @NamedQuery(name = "MeasurementUnit.findByUse", query = "SELECT m FROM MeasurementUnit m WHERE m.use = :use")})
public class MeasurementUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "unit")
    private String unit;
    @Size(max = 20)
    @Column(name = "symbol")
    private String symbol;
    @Size(max = 45)
    @Column(name = "use")
    private String use;
    @OneToMany(mappedBy = "measurementUnit")
    private List<Programme> programmeList;
    @OneToMany(mappedBy = "units")
    private List<Warehouse> warehouseList;

    public MeasurementUnit() {
    }

    public MeasurementUnit(Short id) {
        this.id = id;
    }

    public MeasurementUnit(Short id, String unit) {
        this.id = id;
        this.unit = unit;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    @XmlTransient
    public List<Programme> getProgrammeList() {
        return programmeList;
    }

    public void setProgrammeList(List<Programme> programmeList) {
        this.programmeList = programmeList;
    }

    @XmlTransient
    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
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
        if (!(object instanceof MeasurementUnit)) {
            return false;
        }
        MeasurementUnit other = (MeasurementUnit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.MeasurementUnit[ id=" + id + " ]";
    }
    
}
