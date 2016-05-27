/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(catalog = "kcep_mis", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"national_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByNationalId", query = "SELECT p FROM Person p WHERE p.nationalId = :nationalId"),
    @NamedQuery(name = "Person.findByBusinessName", query = "SELECT p FROM Person p WHERE p.businessName = :businessName")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @Size(max = 45)
    @Column(name = "business_name", length = 45)
    private String businessName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wardExtensionOfficer")
    private List<ExtensionAndFieldVisitData> extensionAndFieldVisitDataList;
    @OneToMany(mappedBy = "trainer")
    private List<Training> trainingList;
    @OneToMany(mappedBy = "person")
    private List<EVoucher> eVoucherList;
    @OneToOne(mappedBy = "warehouseOperator")
    private Warehouse warehouse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wardExtensionOfficer")
    private List<SampledFarmerData> sampledFarmerDataList;
    @JoinColumn(name = "contact", referencedColumnName = "id")
    @ManyToOne
    private Contact contact;
    @JoinColumn(name = "farmer_group", referencedColumnName = "id")
    @ManyToOne
    private FarmerGroup farmerGroup;
    @JoinColumn(name = "location", referencedColumnName = "id")
    @ManyToOne
    private Location location;
    @JoinColumn(name = "person_role", referencedColumnName = "id")
    @ManyToOne
    private PersonRole personRole;
    @JoinColumn(name = "sex", referencedColumnName = "id")
    @ManyToOne
    private Sex sex;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wardExtensionOfficer")
    private List<CollectionCentre> collectionCentreList;

    public Person() {
    }

    public Person(Integer id) {
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @XmlTransient
    public List<ExtensionAndFieldVisitData> getExtensionAndFieldVisitDataList() {
        return extensionAndFieldVisitDataList;
    }

    public void setExtensionAndFieldVisitDataList(List<ExtensionAndFieldVisitData> extensionAndFieldVisitDataList) {
        this.extensionAndFieldVisitDataList = extensionAndFieldVisitDataList;
    }

    @XmlTransient
    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    @XmlTransient
    public List<EVoucher> getEVoucherList() {
        return eVoucherList;
    }

    public void setEVoucherList(List<EVoucher> eVoucherList) {
        this.eVoucherList = eVoucherList;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @XmlTransient
    public List<SampledFarmerData> getSampledFarmerDataList() {
        return sampledFarmerDataList;
    }

    public void setSampledFarmerDataList(List<SampledFarmerData> sampledFarmerDataList) {
        this.sampledFarmerDataList = sampledFarmerDataList;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public FarmerGroup getFarmerGroup() {
        return farmerGroup;
    }

    public void setFarmerGroup(FarmerGroup farmerGroup) {
        this.farmerGroup = farmerGroup;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @XmlTransient
    public List<CollectionCentre> getCollectionCentreList() {
        return collectionCentreList;
    }

    public void setCollectionCentreList(List<CollectionCentre> collectionCentreList) {
        this.collectionCentreList = collectionCentreList;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Person[ id=" + id + " ]";
    }
    
}
