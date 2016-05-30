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
public class PersonDetails implements Serializable, Comparable<PersonDetails> {

    public PersonDetails() {
    }

    public PersonDetails(Integer id) {
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

    public String getNationalId() {
        return national;
    }

    public void setNationalId(String national) {
        this.national = national;
    }

    public void setSex(SexDetail sex) {
        this.sex = sex;
    }

    public SexDetail getSex() {
        return sex;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public ContactDetails getContact() {
        return contact;
    }

    public void setContact(ContactDetails contact) {
        this.contact = contact;
    }

    public FarmerGroupDetails getFarmerGroup() {
        return farmerGroup;
    }

    public void setFarmerGroup(FarmerGroupDetails farmerGroup) {
        this.farmerGroup = farmerGroup;
    }

    public LocationDetails getLocation() {
        return location;
    }

    public void setLocation(LocationDetails location) {
        this.location = location;
    }

    public PersonRoleDetails getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRoleDetails personRole) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonDetails)) {
            return false;
        }
        PersonDetails other = (PersonDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Person[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PersonDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String name;
    private String national;
    private String businessName;
    private ContactDetails contact;
    private FarmerGroupDetails farmerGroup;
    private LocationDetails location;
    private PersonRoleDetails personRole;
    private SexDetail sex;

}
