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
public class WarehouseTypeDetails implements Serializable, Comparable<WarehouseTypeDetails> {

    private static final long serialVersionUID = 1L;

    public WarehouseTypeDetails() {
    }

    public WarehouseTypeDetails(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WarehouseTypeDetails)) {
            return false;
        }
        WarehouseTypeDetails other = (WarehouseTypeDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.WarehouseType[ type=" + getType() + " ]";
    }

    @Override
    public int compareTo(WarehouseTypeDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String type;

}
