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
public class EquipmentDetails implements Serializable, Comparable<EquipmentDetails> {

    public EquipmentDetails() {
    }

    public EquipmentDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof EquipmentDetails)) {
            return false;
        }
        EquipmentDetails other = (EquipmentDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Equipment[ id=" + id + " ]";
    }

    @Override
    public int compareTo(EquipmentDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the warehouse
     */
    public WarehouseDetails getWarehouse() {
        return warehouse;
    }

    /**
     * @param warehouse the warehouse to set
     */
    public void setWarehouse(WarehouseDetails warehouse) {
        this.warehouse = warehouse;
    }

    private Integer id;
    private String type;
    private String status;
    private Integer totalCount;
    private WarehouseDetails warehouse;

}
