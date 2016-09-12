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
public class AnnualIndicatorDetails implements Serializable, Comparable<AnnualIndicatorDetails> {

    public AnnualIndicatorDetails() {
    }

    public AnnualIndicatorDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PerformanceIndicatorDetails getPerformanceIndicator() {
        return performanceIndicator;
    }

    public void setPerformanceIndicator(PerformanceIndicatorDetails performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
    }

    public SubActivityDetails getSubActivity() {
        return subActivity;
    }

    public void setSubActivity(SubActivityDetails subActivity) {
        this.subActivity = subActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AnnualIndicatorDetails)) {
            return false;
        }
        AnnualIndicatorDetails other = (AnnualIndicatorDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.AnnualIndicatorDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(AnnualIndicatorDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private PerformanceIndicatorDetails performanceIndicator;
    private SubActivityDetails subActivity;

}
