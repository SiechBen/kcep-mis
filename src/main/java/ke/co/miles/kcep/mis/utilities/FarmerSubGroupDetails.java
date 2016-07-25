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

    private static final long serialVersionUID = 1L;

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
        if (!(object instanceof FarmerSubGroupDetails)) {
            return false;
        }
        FarmerSubGroupDetails other = (FarmerSubGroupDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
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
