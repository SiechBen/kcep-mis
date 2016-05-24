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
    private String nationalId;
    private Boolean gender;
    @Size(max = 45)
    @Column(name = "business_name", length = 45)
    private String businessName;
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ContactDetails contactId;
    @JoinColumn(name = "farmer_group_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private FarmerGroupDetails farmerGroupId;
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LocationDetails locationId;
    @JoinColumn(name = "person_role_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonRoleDetails personRoleId;

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
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public ContactDetails getContactId() {
        return contactId;
    }

    public void setContactId(ContactDetails contactId) {
        this.contactId = contactId;
    }

    public FarmerGroupDetails getFarmerGroupId() {
        return farmerGroupId;
    }

    public void setFarmerGroupId(FarmerGroupDetails farmerGroupId) {
        this.farmerGroupId = farmerGroupId;
    }

    public LocationDetails getLocationId() {
        return locationId;
    }

    public void setLocationId(LocationDetails locationId) {
        this.locationId = locationId;
    }

    public PersonRoleDetails getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(PersonRoleDetails personRoleId) {
        this.personRoleId = personRoleId;
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
