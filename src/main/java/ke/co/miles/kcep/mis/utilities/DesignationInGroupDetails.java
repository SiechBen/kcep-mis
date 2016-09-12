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
public class DesignationInGroupDetails implements Serializable, Comparable<DesignationInGroupDetails> {

    private static final long serialVersionUID = 1L;

    public DesignationInGroupDetails() {
    }

    public DesignationInGroupDetails(Integer id) {
        this.id = id;
    }

    public DesignationInGroupDetails(Integer id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DesignationInGroupDetails)) {
            return false;
        }
        DesignationInGroupDetails other = (DesignationInGroupDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.DesignationInGroupDetails[ designation=" + designation + " ]";
    }

    @Override
    public int compareTo(DesignationInGroupDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String designation;

}
