package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "performance_indicator_values", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerformanceIndicatorValues.findByPerformanceIndicatorId", query = "SELECT p FROM PerformanceIndicatorValues p WHERE p.performanceIndicator.id = :performanceIndicatorId"),
    @NamedQuery(name = "PerformanceIndicatorValues.findByPerformanceIndicatorIdAndProjectYearAndPurpose", query = "SELECT p FROM PerformanceIndicatorValues p WHERE p.performanceIndicator.id = :performanceIndicatorId AND p.projectYear = :projectYear AND p.purpose = :purpose"),
    @NamedQuery(name = "PerformanceIndicatorValues.findProjectYears", query = "SELECT DISTINCT p.projectYear FROM PerformanceIndicatorValues p ORDER BY p.projectYear"),
    @NamedQuery(name = "PerformanceIndicatorValues.findAll", query = "SELECT p FROM PerformanceIndicatorValues p"),
    @NamedQuery(name = "PerformanceIndicatorValues.findById", query = "SELECT p FROM PerformanceIndicatorValues p WHERE p.id = :id"),
    @NamedQuery(name = "PerformanceIndicatorValues.findByProjectYear", query = "SELECT p FROM PerformanceIndicatorValues p WHERE p.projectYear = :projectYear"),
    @NamedQuery(name = "PerformanceIndicatorValues.findByActualValue", query = "SELECT p FROM PerformanceIndicatorValues p WHERE p.actualValue = :actualValue"),
    @NamedQuery(name = "PerformanceIndicatorValues.findByExpectedValue", query = "SELECT p FROM PerformanceIndicatorValues p WHERE p.expectedValue = :expectedValue")})
public class PerformanceIndicatorValues implements Serializable {

    @Size(max = 45)
    @Column(name = "purpose")
    private String purpose;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "project_year")
    private Short projectYear;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "actual_value")
    private Double actualValue;
    @Column(name = "expected_value")
    private Double expectedValue;
    @JoinColumn(name = "performance_indicator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PerformanceIndicator performanceIndicator;

    public PerformanceIndicatorValues() {
    }

    public PerformanceIndicatorValues(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(Short projectYear) {
        this.projectYear = projectYear;
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

    public PerformanceIndicator getPerformanceIndicator() {
        return performanceIndicator;
    }

    public void setPerformanceIndicator(PerformanceIndicator performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
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
        if (!(object instanceof PerformanceIndicatorValues)) {
            return false;
        }
        PerformanceIndicatorValues other = (PerformanceIndicatorValues) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PerformanceIndicatorValues[ id=" + id + " ]";
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

}
