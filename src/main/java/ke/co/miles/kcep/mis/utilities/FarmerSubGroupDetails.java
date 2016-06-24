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
public class FarmerSubGroupDetails implements Serializable, Comparable<FarmerSubGroupDetails> {

    public FarmerSubGroupDetails() {
    }

    public FarmerSubGroupDetails(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FarmerSubGroupDetails)) {
            return false;
        }
        FarmerSubGroupDetails other = (FarmerSubGroupDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.FarmerSubGroup[ name=" + name + " ]";
    }

    @Override
    public int compareTo(FarmerSubGroupDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the farmerGroup
     */
    public FarmerGroupDetails getFarmerGroup() {
        return farmerGroup;
    }

    /**
     * @param farmerGroup the farmerGroup to set
     */
    public void setFarmerGroup(FarmerGroupDetails farmerGroup) {
        this.farmerGroup = farmerGroup;
    }

    private Integer id;
    private String name;
    private FarmerGroupDetails farmerGroup;

}
