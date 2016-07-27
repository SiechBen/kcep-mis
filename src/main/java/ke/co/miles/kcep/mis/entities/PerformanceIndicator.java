/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "performance_indicator", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerformanceIndicator.findAll", query = "SELECT p FROM PerformanceIndicator p"),
    @NamedQuery(name = "PerformanceIndicator.findById", query = "SELECT p FROM PerformanceIndicator p WHERE p.id = :id"),
    @NamedQuery(name = "PerformanceIndicator.findByDescription", query = "SELECT p FROM PerformanceIndicator p WHERE p.description = :description"),
    @NamedQuery(name = "PerformanceIndicator.findByBaselineDate", query = "SELECT p FROM PerformanceIndicator p WHERE p.baselineDate = :baselineDate"),
    @NamedQuery(name = "PerformanceIndicator.findByBaselineValue", query = "SELECT p FROM PerformanceIndicator p WHERE p.baselineValue = :baselineValue"),
    @NamedQuery(name = "PerformanceIndicator.findByYearOfUse", query = "SELECT p FROM PerformanceIndicator p WHERE p.yearOfUse = :yearOfUse"),
    @NamedQuery(name = "PerformanceIndicator.findByActualValue", query = "SELECT p FROM PerformanceIndicator p WHERE p.actualValue = :actualValue"),
    @NamedQuery(name = "PerformanceIndicator.findByExpectedValue", query = "SELECT p FROM PerformanceIndicator p WHERE p.expectedValue = :expectedValue"),
    @NamedQuery(name = "PerformanceIndicator.findByRatio", query = "SELECT p FROM PerformanceIndicator p WHERE p.ratio = :ratio")})
public class PerformanceIndicator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "description")
    private String description;
    @Column(name = "baseline_date")
    @Temporal(TemporalType.DATE)
    private Date baselineDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "baseline_value")
    private Double baselineValue;
    @Column(name = "year_of_use")
    private Short yearOfUse;
    @Column(name = "actual_value")
    private Double actualValue;
    @Column(name = "expected_value")
    private Double expectedValue;
    @Column(name = "ratio")
    private Double ratio;
    @JoinColumn(name = "performance_indicator_type", referencedColumnName = "id")
    @ManyToOne
    private PerformanceIndicatorType performanceIndicatorType;
    @JoinColumn(name = "result_hierarchy", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ResultHierarchy resultHierarchy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "performanceIndicator")
    private List<AnnualIndicator> annualIndicatorList;

    public PerformanceIndicator() {
    }

    public PerformanceIndicator(Short id) {
        this.id = id;
    }

    public PerformanceIndicator(Short id, String description) {
        this.id = id;
        this.description = description;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBaselineDate() {
        return baselineDate;
    }

    public void setBaselineDate(Date baselineDate) {
        this.baselineDate = baselineDate;
    }

    public Double getBaselineValue() {
        return baselineValue;
    }

    public void setBaselineValue(Double baselineValue) {
        this.baselineValue = baselineValue;
    }

    public Short getYearOfUse() {
        return yearOfUse;
    }

    public void setYearOfUse(Short yearOfUse) {
        this.yearOfUse = yearOfUse;
    }

    public Double getActualValue() {
        return actualValue;
    }

    public void setActualValue(Double actualValue) {
        this.actualValue = actualValue;
    }

    public Double getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Double expectedValue) {
        this.expectedValue = expectedValue;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public PerformanceIndicatorType getPerformanceIndicatorType() {
        return performanceIndicatorType;
    }

    public void setPerformanceIndicatorType(PerformanceIndicatorType performanceIndicatorType) {
        this.performanceIndicatorType = performanceIndicatorType;
    }

    public ResultHierarchy getResultHierarchy() {
        return resultHierarchy;
    }

    public void setResultHierarchy(ResultHierarchy resultHierarchy) {
        this.resultHierarchy = resultHierarchy;
    }

    @XmlTransient
    public List<AnnualIndicator> getAnnualIndicatorList() {
        return annualIndicatorList;
    }

    public void setAnnualIndicatorList(List<AnnualIndicator> annualIndicatorList) {
        this.annualIndicatorList = annualIndicatorList;
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
        if (!(object instanceof PerformanceIndicator)) {
            return false;
        }
        PerformanceIndicator other = (PerformanceIndicator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PerformanceIndicator[ id=" + id + " ]";
    }
    
}
