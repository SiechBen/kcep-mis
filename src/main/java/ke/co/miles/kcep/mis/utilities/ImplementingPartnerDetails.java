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
public class ImplementingPartnerDetails implements Serializable, Comparable<ImplementingPartnerDetails> {

    private static final long serialVersionUID = 1L;

    public ImplementingPartnerDetails() {
    }

    public ImplementingPartnerDetails(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public PersonRoleDetail getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRoleDetail personRole) {
        this.personRole = personRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ImplementingPartnerDetails)) {
            return false;
        }
        ImplementingPartnerDetails other = (ImplementingPartnerDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ImplementingPartner[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ImplementingPartnerDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Short id;
    private PersonRoleDetail personRole;

}
