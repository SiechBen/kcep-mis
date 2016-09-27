package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;

/**
 *
 * @author siech
 */
public class PerformanceIndicatorValuesDetails implements Serializable,
        Comparable<PerformanceIndicatorValuesDetails> {

    public PerformanceIndicatorValuesDetails() {
    }

    public PerformanceIndicatorValuesDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public PerformanceIndicatorDetails getPerformanceIndicator() {
        return performanceIndicator;
    }

    public void setPerformanceIndicator(PerformanceIndicatorDetails performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
    }

    /**
     * @return the ratio
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     * @param ratio the ratio to set
     */
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerformanceIndicatorValuesDetails)) {
            return false;
        }
        PerformanceIndicatorValuesDetails other = (PerformanceIndicatorValuesDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PerformanceIndicatorValues[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PerformanceIndicatorValuesDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Double ratio;
    private Short yearOfUse;
    private Double actualValue;
    private Double expectedValue;
    private PerformanceIndicatorDetails performanceIndicator;

}
