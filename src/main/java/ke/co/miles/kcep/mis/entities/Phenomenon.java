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
    @NamedQuery(name = "Phenomenon.findByPhenomenonTypeIdAndRelative", query = "SELECT p FROM Phenomenon p WHERE p.phenomenonType.id = :phenomenonTypeId AND p.category.relative = :relative"),
    @NamedQuery(name = "Phenomenon.findByPhenomenonTypeId", query = "SELECT p FROM Phenomenon p WHERE p.phenomenonType.id = :phenomenonTypeId"),
    @NamedQuery(name = "Phenomenon.findByRelativeId", query = "SELECT p FROM Phenomenon p WHERE p.category.relative.id = :relativeId"),
    @NamedQuery(name = "Phenomenon.findAll", query = "SELECT p FROM Phenomenon p"),
    @NamedQuery(name = "Phenomenon.findById", query = "SELECT p FROM Phenomenon p WHERE p.id = :id")})
public class Phenomenon implements Serializable {

    @OneToMany(mappedBy = "trainer")
    private List<Topic> topicList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "gfssCode")
    private List<SubActivity> subActivityList;
    @OneToMany(mappedBy = "performanceIndicatorType")
    private List<PerformanceIndicator> performanceIndicatorList;
    @OneToMany(mappedBy = "warehouseOperator")
    private List<Warehouse> warehouseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseType")
    private List<Warehouse> warehouseList1;
    @OneToMany(mappedBy = "gfssCode")
    private List<Procurement> procurementList;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phenomenon")
    private List<Trainer> trainerList;

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

    @XmlTransient
    public List<SubActivity> getSubActivityList() {
        return subActivityList;
    }

    public void setSubActivityList(List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
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
    public List<Trainer> getTrainerList() {
        return trainerList;
    }

    public void setTrainerList(List<Trainer> trainerList) {
        this.trainerList = trainerList;
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
    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

}
