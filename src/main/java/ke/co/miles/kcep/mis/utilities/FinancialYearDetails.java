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
public class FinancialYearDetails implements Serializable, Comparable<FinancialYearDetails> {

    public FinancialYearDetails() {
    }

    public FinancialYearDetails(Short id) {
        this.id = id;
    }

    public FinancialYearDetails(Short id, String financialYear, boolean currentYear) {
        this.id = id;
        this.financialYear = financialYear;
        this.currentYear = currentYear;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public boolean getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(boolean currentYear) {
        this.currentYear = currentYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FinancialYearDetails)) {
            return false;
        }
        FinancialYearDetails other = (FinancialYearDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.FinancialYear[ id=" + id + " ]";
    }

    @Override
    public int compareTo(FinancialYearDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Short id;
    private String financialYear;
    private boolean currentYear;

}
