/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "indicator", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicator.findAll", query = "SELECT i FROM Indicator i"),
    @NamedQuery(name = "Indicator.findById", query = "SELECT i FROM Indicator i WHERE i.id = :id"),
    @NamedQuery(name = "Indicator.findByDescription", query = "SELECT i FROM Indicator i WHERE i.description = :description"),
    @NamedQuery(name = "Indicator.findByBaselineDate", query = "SELECT i FROM Indicator i WHERE i.baselineDate = :baselineDate"),
    @NamedQuery(name = "Indicator.findByBaselineValue", query = "SELECT i FROM Indicator i WHERE i.baselineValue = :baselineValue"),
    @NamedQuery(name = "Indicator.findByYear", query = "SELECT i FROM Indicator i WHERE i.year = :year"),
    @NamedQuery(name = "Indicator.findByActualValue", query = "SELECT i FROM Indicator i WHERE i.actualValue = :actualValue"),
    @NamedQuery(name = "Indicator.findByExpectedValue", query = "SELECT i FROM Indicator i WHERE i.expectedValue = :expectedValue"),
    @NamedQuery(name = "Indicator.findByRatio", query = "SELECT i FROM Indicator i WHERE i.ratio = :ratio")})
public class Indicator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "baseline_date")
    private String baselineDate;
    @Size(max = 45)
    @Column(name = "baseline_value")
    private String baselineValue;
    @Column(name = "year")
    @Temporal(TemporalType.DATE)
    private Date year;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "actual_value")
    private Double actualValue;
    @Column(name = "expected_value")
    private Double expectedValue;
    @Column(name = "ratio")
    private Double ratio;
    @JoinColumn(name = "indicator_hierarchy", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IndicatorHierarchy indicatorHierarchy;
    @JoinColumn(name = "indicator_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IndicatorType indicatorType;

    public Indicator() {
    }

    public Indicator(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaselineDate() {
        return baselineDate;
    }

    public void setBaselineDate(String baselineDate) {
        this.baselineDate = baselineDate;
    }

    public String getBaselineValue() {
        return baselineValue;
    }

    public void setBaselineValue(String baselineValue) {
        this.baselineValue = baselineValue;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
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

    public IndicatorHierarchy getIndicatorHierarchy() {
        return indicatorHierarchy;
    }

    public void setIndicatorHierarchy(IndicatorHierarchy indicatorHierarchy) {
        this.indicatorHierarchy = indicatorHierarchy;
    }

    public IndicatorType getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(IndicatorType indicatorType) {
        this.indicatorType = indicatorType;
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
        if (!(object instanceof Indicator)) {
            return false;
        }
        Indicator other = (Indicator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Indicator[ id=" + id + " ]";
    }
    
}
