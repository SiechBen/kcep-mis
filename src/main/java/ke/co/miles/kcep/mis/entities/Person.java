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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "person", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findBySexId", query = "SELECT p FROM Person p WHERE p.sex.id = :sexId"),
    @NamedQuery(name = "Person.findByWardId", query = "SELECT p FROM Person p WHERE p.location.ward.id = :wardId"),
    @NamedQuery(name = "Person.findByCountyId", query = "SELECT p FROM Person p WHERE p.location.county.id = :countyId"),
    @NamedQuery(name = "Person.findBySubCountyId", query = "SELECT p FROM Person p WHERE p.location.subCounty.id = :subCountyId"),
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByNationalId", query = "SELECT p FROM Person p WHERE p.nationalId = :nationalId"),
    @NamedQuery(name = "Person.findByYearOfBirth", query = "SELECT p FROM Person p WHERE p.yearOfBirth = :yearOfBirth"),
    @NamedQuery(name = "Person.findByAge", query = "SELECT p FROM Person p WHERE p.age = :age"),
    @NamedQuery(name = "Person.findByBusinessName", query = "SELECT p FROM Person p WHERE p.businessName = :businessName"),
    @NamedQuery(name = "Person.findByPlotSize", query = "SELECT p FROM Person p WHERE p.plotSize = :plotSize"),
    @NamedQuery(name = "Person.findByApproved", query = "SELECT p FROM Person p WHERE p.approved = :approved")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "national_id")
    private String nationalId;
    @Column(name = "year_of_birth")
    private Short yearOfBirth;
    @Column(name = "age")
    private Short age;
    @Size(max = 45)
    @Column(name = "business_name")
    private String businessName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "plot_size")
    private Double plotSize;
    @Column(name = "approved")
    private Boolean approved;
    @OneToMany(mappedBy = "submitter")
    private List<Feedback> feedbackList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<SoilFertilityPackage> soilFertilityPackageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wardExtensionOfficer")
    private List<ExtensionAndFieldVisitData> extensionAndFieldVisitDataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer")
    private List<FarmActivity> farmActivityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<OnFarmTrialsAndDemonstrations> onFarmTrialsAndDemonstrationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<ExtensionMaterialAndGuideline> extensionMaterialAndGuidelineList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<DisseminationOfResults> disseminationOfResultsList;
    @JoinColumn(name = "contact", referencedColumnName = "id")
    @ManyToOne
    private Contact contact;
    @JoinColumn(name = "designation_in_group", referencedColumnName = "id")
    @ManyToOne
    private DesignationInGroup designationInGroup;
    @JoinColumn(name = "farmer_group", referencedColumnName = "id")
    @ManyToOne
    private FarmerGroup farmerGroup;
    @JoinColumn(name = "farmer_sub_group", referencedColumnName = "id")
    @ManyToOne
    private FarmerSubGroup farmerSubGroup;
    @JoinColumn(name = "location", referencedColumnName = "id")
    @ManyToOne
    private Location location;
    @JoinColumn(name = "sex", referencedColumnName = "id")
    @ManyToOne
    private Sex sex;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agroDealer")
    private List<InputsCollection> inputsCollectionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer")
    private List<InputsCollection> inputsCollectionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Trainee> traineeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<ValidationWorkshops> validationWorkshopsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<Technology> technologyList;
    @OneToMany(mappedBy = "person")
    private List<EVoucher> eVoucherList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wardExtensionOfficer")
    private List<SampledFarmerData> sampledFarmerDataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<TechnologyTargetCounty> technologyTargetCountyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<UserAccount> userAccountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer")
    private List<Account> accountList;

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

    public Short getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Short yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Double getPlotSize() {
        return plotSize;
    }

    public void setPlotSize(Double plotSize) {
        this.plotSize = plotSize;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @XmlTransient
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @XmlTransient
    public List<SoilFertilityPackage> getSoilFertilityPackageList() {
        return soilFertilityPackageList;
    }

    public void setSoilFertilityPackageList(List<SoilFertilityPackage> soilFertilityPackageList) {
        this.soilFertilityPackageList = soilFertilityPackageList;
    }

    @XmlTransient
    public List<ExtensionAndFieldVisitData> getExtensionAndFieldVisitDataList() {
        return extensionAndFieldVisitDataList;
    }

    public void setExtensionAndFieldVisitDataList(List<ExtensionAndFieldVisitData> extensionAndFieldVisitDataList) {
        this.extensionAndFieldVisitDataList = extensionAndFieldVisitDataList;
    }

    @XmlTransient
    public List<FarmActivity> getFarmActivityList() {
        return farmActivityList;
    }

    public void setFarmActivityList(List<FarmActivity> farmActivityList) {
        this.farmActivityList = farmActivityList;
    }

    @XmlTransient
    public List<OnFarmTrialsAndDemonstrations> getOnFarmTrialsAndDemonstrationsList() {
        return onFarmTrialsAndDemonstrationsList;
    }

    public void setOnFarmTrialsAndDemonstrationsList(List<OnFarmTrialsAndDemonstrations> onFarmTrialsAndDemonstrationsList) {
        this.onFarmTrialsAndDemonstrationsList = onFarmTrialsAndDemonstrationsList;
    }

    @XmlTransient
    public List<ExtensionMaterialAndGuideline> getExtensionMaterialAndGuidelineList() {
        return extensionMaterialAndGuidelineList;
    }

    public void setExtensionMaterialAndGuidelineList(List<ExtensionMaterialAndGuideline> extensionMaterialAndGuidelineList) {
        this.extensionMaterialAndGuidelineList = extensionMaterialAndGuidelineList;
    }

    @XmlTransient
    public List<DisseminationOfResults> getDisseminationOfResultsList() {
        return disseminationOfResultsList;
    }

    public void setDisseminationOfResultsList(List<DisseminationOfResults> disseminationOfResultsList) {
        this.disseminationOfResultsList = disseminationOfResultsList;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public DesignationInGroup getDesignationInGroup() {
        return designationInGroup;
    }

    public void setDesignationInGroup(DesignationInGroup designationInGroup) {
        this.designationInGroup = designationInGroup;
    }

    public FarmerGroup getFarmerGroup() {
        return farmerGroup;
    }

    public void setFarmerGroup(FarmerGroup farmerGroup) {
        this.farmerGroup = farmerGroup;
    }

    public FarmerSubGroup getFarmerSubGroup() {
        return farmerSubGroup;
    }

    public void setFarmerSubGroup(FarmerSubGroup farmerSubGroup) {
        this.farmerSubGroup = farmerSubGroup;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @XmlTransient
    public List<InputsCollection> getInputsCollectionList() {
        return inputsCollectionList;
    }

    public void setInputsCollectionList(List<InputsCollection> inputsCollectionList) {
        this.inputsCollectionList = inputsCollectionList;
    }

    @XmlTransient
    public List<InputsCollection> getInputsCollectionList1() {
        return inputsCollectionList1;
    }

    public void setInputsCollectionList1(List<InputsCollection> inputsCollectionList1) {
        this.inputsCollectionList1 = inputsCollectionList1;
    }

    @XmlTransient
    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public void setTraineeList(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    @XmlTransient
    public List<ValidationWorkshops> getValidationWorkshopsList() {
        return validationWorkshopsList;
    }

    public void setValidationWorkshopsList(List<ValidationWorkshops> validationWorkshopsList) {
        this.validationWorkshopsList = validationWorkshopsList;
    }

    @XmlTransient
    public List<Technology> getTechnologyList() {
        return technologyList;
    }

    public void setTechnologyList(List<Technology> technologyList) {
        this.technologyList = technologyList;
    }

    @XmlTransient
    public List<EVoucher> getEVoucherList() {
        return eVoucherList;
    }

    public void setEVoucherList(List<EVoucher> eVoucherList) {
        this.eVoucherList = eVoucherList;
    }

    @XmlTransient
    public List<SampledFarmerData> getSampledFarmerDataList() {
        return sampledFarmerDataList;
    }

    public void setSampledFarmerDataList(List<SampledFarmerData> sampledFarmerDataList) {
        this.sampledFarmerDataList = sampledFarmerDataList;
    }

    @XmlTransient
    public List<TechnologyTargetCounty> getTechnologyTargetCountyList() {
        return technologyTargetCountyList;
    }

    public void setTechnologyTargetCountyList(List<TechnologyTargetCounty> technologyTargetCountyList) {
        this.technologyTargetCountyList = technologyTargetCountyList;
    }

    @XmlTransient
    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
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
