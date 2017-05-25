package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "phenomenon", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phenomenon.findByPhenomenonTypeIdAndRelativeId", query = "SELECT p FROM Phenomenon p WHERE p.phenomenonType.id = :phenomenonTypeId AND p.category.relative.id = :relativeId"),
    @NamedQuery(name = "Phenomenon.findByPhenomenonTypeIdAndRelative", query = "SELECT p FROM Phenomenon p WHERE p.phenomenonType.id = :phenomenonTypeId AND p.category.relative = :relative"),
    @NamedQuery(name = "Phenomenon.findByPhenomenonTypeId", query = "SELECT p FROM Phenomenon p WHERE p.phenomenonType.id = :phenomenonTypeId"),
    @NamedQuery(name = "Phenomenon.findByRelativeId", query = "SELECT p FROM Phenomenon p WHERE p.category.relative.id = :relativeId"),
    @NamedQuery(name = "Phenomenon.findAll", query = "SELECT p FROM Phenomenon p"),
    @NamedQuery(name = "Phenomenon.findByTiedValue", query = "SELECT p FROM Phenomenon p WHERE p.tiedValue = :tiedValue"),
    @NamedQuery(name = "Phenomenon.findById", query = "SELECT p FROM Phenomenon p WHERE p.id = :id")})
public class Phenomenon implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "progressType")
    private List<ActivityProgress> activityProgressList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tied_value")
    private BigDecimal tiedValue;
    @OneToMany(mappedBy = "gfssCode")
    private List<SubActivity> subActivityList;
    @OneToMany(mappedBy = "expectedOutcome")
    private List<SubActivity> subActivityList1;
    @OneToMany(mappedBy = "implementingPartner")
    private List<SubActivity> subActivityList2;
    @OneToMany(mappedBy = "responsePcu")
    private List<SubActivity> subActivityList3;
    @OneToMany(mappedBy = "awpbOwner")
    private List<SubActivity> subActivityList4;
    @OneToMany(mappedBy = "annualIndicator")
    private List<SubActivity> subActivityList5;
    @OneToMany(mappedBy = "expenditureCategory")
    private List<SubActivity> subActivityList6;
    @OneToMany(mappedBy = "component")
    private List<SubActivity> subActivityList7;
    @OneToMany(mappedBy = "subComponent")
    private List<SubActivity> subActivityList8;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feedbackType")
    private List<Feedback> feedbackList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "performanceIndicatorType")
    private List<PerformanceIndicator> performanceIndicatorList;
    @OneToMany(mappedBy = "warehouseOperator")
    private List<Warehouse> warehouseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseType")
    private List<Warehouse> warehouseList1;
    @OneToMany(mappedBy = "gfssCode")
    private List<Procurement> procurementList;
    @OneToMany(mappedBy = "trainer")
    private List<Topic> topicList;
    @OneToMany(mappedBy = "issuingBank")
    private List<Loan> loanList;
    @OneToMany(mappedBy = "categoryOfTrainees")
    private List<Training> trainingList;
    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "phenomenon_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PhenomenonType phenomenonType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purpose")
    private List<UploadedFile> uploadedFileList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phenomenon")
    private List<Trainer> trainerList;
    @OneToMany(mappedBy = "component")
    private List<ResultHierarchy> resultHierarchyList;
    @OneToMany(mappedBy = "subComponent")
    private List<ResultHierarchy> resultHierarchyList1;

    public Phenomenon() {
    }

    public Phenomenon(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTiedValue() {
        return tiedValue;
    }

    public void setTiedValue(BigDecimal tiedValue) {
        this.tiedValue = tiedValue;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList() {
        return subActivityList;
    }

    public void setSubActivityList(List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList1() {
        return subActivityList1;
    }

    public void setSubActivityList1(List<SubActivity> subActivityList1) {
        this.subActivityList1 = subActivityList1;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList2() {
        return subActivityList2;
    }

    public void setSubActivityList2(List<SubActivity> subActivityList2) {
        this.subActivityList2 = subActivityList2;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList3() {
        return subActivityList3;
    }

    public void setSubActivityList3(List<SubActivity> subActivityList3) {
        this.subActivityList3 = subActivityList3;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList4() {
        return subActivityList4;
    }

    public void setSubActivityList4(List<SubActivity> subActivityList4) {
        this.subActivityList4 = subActivityList4;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList5() {
        return subActivityList5;
    }

    public void setSubActivityList5(List<SubActivity> subActivityList5) {
        this.subActivityList5 = subActivityList5;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList6() {
        return subActivityList6;
    }

    public void setSubActivityList6(List<SubActivity> subActivityList6) {
        this.subActivityList6 = subActivityList6;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList7() {
        return subActivityList7;
    }

    public void setSubActivityList7(List<SubActivity> subActivityList7) {
        this.subActivityList7 = subActivityList7;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList8() {
        return subActivityList8;
    }

    public void setSubActivityList8(List<SubActivity> subActivityList8) {
        this.subActivityList8 = subActivityList8;
    }

    @XmlTransient
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @XmlTransient
    public List<PerformanceIndicator> getPerformanceIndicatorList() {
        return performanceIndicatorList;
    }

    public void setPerformanceIndicatorList(List<PerformanceIndicator> performanceIndicatorList) {
        this.performanceIndicatorList = performanceIndicatorList;
    }

    @XmlTransient
    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }

    @XmlTransient
    public List<Warehouse> getWarehouseList1() {
        return warehouseList1;
    }

    public void setWarehouseList1(List<Warehouse> warehouseList1) {
        this.warehouseList1 = warehouseList1;
    }

    @XmlTransient
    public List<Procurement> getProcurementList() {
        return procurementList;
    }

    public void setProcurementList(List<Procurement> procurementList) {
        this.procurementList = procurementList;
    }

    @XmlTransient
    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @XmlTransient
    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    @XmlTransient
    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PhenomenonType getPhenomenonType() {
        return phenomenonType;
    }

    public void setPhenomenonType(PhenomenonType phenomenonType) {
        this.phenomenonType = phenomenonType;
    }

    @XmlTransient
    public List<UploadedFile> getUploadedFileList() {
        return uploadedFileList;
    }

    public void setUploadedFileList(List<UploadedFile> uploadedFileList) {
        this.uploadedFileList = uploadedFileList;
    }

    @XmlTransient
    public List<Trainer> getTrainerList() {
        return trainerList;
    }

    public void setTrainerList(List<Trainer> trainerList) {
        this.trainerList = trainerList;
    }

    @XmlTransient
    public List<ResultHierarchy> getResultHierarchyList() {
        return resultHierarchyList;
    }

    public void setResultHierarchyList(List<ResultHierarchy> resultHierarchyList) {
        this.resultHierarchyList = resultHierarchyList;
    }

    @XmlTransient
    public List<ResultHierarchy> getResultHierarchyList1() {
        return resultHierarchyList1;
    }

    public void setResultHierarchyList1(List<ResultHierarchy> resultHierarchyList1) {
        this.resultHierarchyList1 = resultHierarchyList1;
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
        if (!(object instanceof Phenomenon)) {
            return false;
        }
        Phenomenon other = (Phenomenon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Phenomenon[ id=" + id + " ]";
    }

    @XmlTransient
    public List<ActivityProgress> getActivityProgressList() {
        return activityProgressList;
    }

    public void setActivityProgressList(List<ActivityProgress> activityProgressList) {
        this.activityProgressList = activityProgressList;
    }

}
