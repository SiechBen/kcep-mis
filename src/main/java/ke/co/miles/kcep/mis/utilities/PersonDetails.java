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
import ke.co.miles.kcep.mis.entities.Sex;

/**
 *
 * @author siech
 */
public class PersonDetails implements Serializable, Comparable<PersonDetails> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 200)
    @Column(length = 200)
    private String name;
    @Size(max = 20)
    @Column(name = "national_id", length = 20)
    private String national;
    @Size(max = 45)
    @Column(name = "business_name", length = 45)
    private String businessName;
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ContactDetails contact;
    @JoinColumn(name = "farmer_group_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private FarmerGroupDetails farmerGroup;
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LocationDetails location;
    @JoinColumn(name = "person_role_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonRoleDetails personRole;
    @JoinColumn(name = "sex", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Sex sex;

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

    public void setNational(String national) {
        this.national = national;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Sex getSex() {
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

}
