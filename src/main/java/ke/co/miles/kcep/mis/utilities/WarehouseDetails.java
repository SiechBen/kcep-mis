/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;

/**
 *
 * @author siech
 */
public class WarehouseDetails implements Serializable, Comparable<WarehouseDetails> {

    private static final long serialVersionUID = 1L;

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

    public Boolean getOffersWrs() {
        return offersWrs;
    }

    public void setOffersWrs(Boolean offersWrs) {
        this.offersWrs = offersWrs;
    }

    public Boolean getCertified() {
        return certified;
    }

    public void setCertified(Boolean certified) {
        this.certified = certified;
    }

    public LocationDetails getLocation() {
        return location;
    }

    public void setLocation(LocationDetails location) {
        this.location = location;
    }

    public PhenomenonDetails getWarehouseOperator() {
        return warehouseOperator;
    }

    public void setWarehouseOperator(PhenomenonDetails warehouseOperator) {
        this.warehouseOperator = warehouseOperator;
    }

    public MeasurementUnitDetails getUnits() {
        return units;
    }

    public void setUnits(MeasurementUnitDetails units) {
        this.units = units;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof WarehouseDetails)) {
            return false;
        }
        WarehouseDetails other = (WarehouseDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.WarehouseDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(WarehouseDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the warehouseType
     */
    public PhenomenonDetails getWarehouseType() {
        return warehouseType;
    }

    /**
     * @param warehouseType the warehouseType to set
     */
    public void setWarehouseType(PhenomenonDetails warehouseType) {
        this.warehouseType = warehouseType;
    }

    private Integer id;
    private String name;
    private Integer capacity;
    private Boolean certified;
    private Boolean offersWrs;
    private LocationDetails location;
    private MeasurementUnitDetails units;
    private PhenomenonDetails warehouseType;
    private PhenomenonDetails warehouseOperator;

}
