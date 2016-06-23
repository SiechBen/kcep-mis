/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.Date;

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

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the nationalId
     */
    public String getNationalId() {
        return nationalId;
    }

    /**
     * @param nationalId the nationalId to set
     */
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
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
        return "ke.co.miles.kcep.mis.utilities.Person[ id=" + getId() + " ]";
    }

    @Override
    public int compareTo(PersonDetails o) {
        return this.getId().compareTo(o.getId());
    }

    /**
     * @return the farmerSubGroup
     */
    public FarmerSubGroupDetails getFarmerSubGroup() {
        return farmerSubGroup;
    }

    /**
     * @param farmerSubGroup the farmerSubGroup to set
     */
    public void setFarmerSubGroup(FarmerSubGroupDetails farmerSubGroup) {
        this.farmerSubGroup = farmerSubGroup;
    }

    private Integer id;
    private String name;
    private SexDetail sex;
    private Date dateOfBirth;
    private String nationalId;
    private String businessName;
    private ContactDetails contact;
    private LocationDetails location;
    private FarmerGroupDetails farmerGroup;
    private FarmerSubGroupDetails farmerSubGroup;

}
