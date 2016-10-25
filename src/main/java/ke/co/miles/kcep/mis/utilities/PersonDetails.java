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

    private static final long serialVersionUID = 1L;

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
     * @return the yearOfBirth
     */
    public Short getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * @param yearOfBirth the yearOfBirth to set
     */
    public void setYearOfBirth(Short yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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
        if (!(object instanceof PersonDetails)) {
            return false;
        }
        PersonDetails other = (PersonDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return " " + getNationalId();
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

    /**
     * @return the age
     */
    public Short getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Short age) {
        this.age = age;
    }

    /**
     * @return the designationInGroup
     */
    public DesignationInGroupDetails getDesignationInGroup() {
        return designationInGroup;
    }

    /**
     * @param designationInGroup the designationInGroup to set
     */
    public void setDesignationInGroup(DesignationInGroupDetails designationInGroup) {
        this.designationInGroup = designationInGroup;
    }

    /**
     * @return the personRoleId
     */
    public Short getPersonRoleId() {
        return personRoleId;
    }

    /**
     * @param personRoleId the personRoleId to set
     */
    public void setPersonRoleId(Short personRoleId) {
        this.personRoleId = personRoleId;
    }

    /**
     * @return the plotSize
     */
    public Double getPlotSize() {
        return plotSize;
    }

    /**
     * @param plotSize the plotSize to set
     */
    public void setPlotSize(Double plotSize) {
        this.plotSize = plotSize;
    }

    /**
     * @return the personRole
     */
    public String getPersonRole() {
        return personRole;
    }

    /**
     * @param personRole the personRole to set
     */
    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

    private Integer id;
    private Short age;
    private String name;
    private SexDetail sex;
    private Short yearOfBirth;
    private Double plotSize;
    private String nationalId;
    private String personRole;
    private Short personRoleId;
    private String businessName;
    private ContactDetails contact;
    private LocationDetails location;
    private FarmerGroupDetails farmerGroup;
    private FarmerSubGroupDetails farmerSubGroup;
    private DesignationInGroupDetails designationInGroup;

}
