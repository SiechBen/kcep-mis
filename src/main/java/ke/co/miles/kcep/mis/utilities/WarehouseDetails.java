/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

/**
 *
 * @author siech
 */
public class WarehouseDetails implements Serializable, Comparable<WarehouseDetails> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 200)
    @Column(length = 200)
    private String name;
    private Integer capacity;
    private Boolean wrs;
    private Boolean certified;
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LocationDetails locationId;
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonDetails personId;
    @JoinColumn(name = "units_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private MeasurementUnitDetails unitsId;

    public WarehouseDetails() {
    }

    public WarehouseDetails(Integer id) {
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getWrs() {
        return wrs;
    }

    public void setWrs(Boolean wrs) {
        this.wrs = wrs;
    }

    public Boolean getCertified() {
        return certified;
    }

    public void setCertified(Boolean certified) {
        this.certified = certified;
    }

    public LocationDetails getLocationId() {
        return locationId;
    }

    public void setLocationId(LocationDetails locationId) {
        this.locationId = locationId;
    }

    public PersonDetails getPersonId() {
        return personId;
    }

    public void setPersonId(PersonDetails personId) {
        this.personId = personId;
    }

    public MeasurementUnitDetails getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(MeasurementUnitDetails unitsId) {
        this.unitsId = unitsId;
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
        if (!(object instanceof WarehouseDetails)) {
            return false;
        }
        WarehouseDetails other = (WarehouseDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Warehouse[ id=" + id + " ]";
    }

    @Override
    public int compareTo(WarehouseDetails o) {
        return this.id.compareTo(o.getId());
    }

}
