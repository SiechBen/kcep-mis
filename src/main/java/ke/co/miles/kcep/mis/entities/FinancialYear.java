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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "financial_year", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FinancialYear.findAll", query = "SELECT f FROM FinancialYear f"),
    @NamedQuery(name = "FinancialYear.findById", query = "SELECT f FROM FinancialYear f WHERE f.id = :id"),
    @NamedQuery(name = "FinancialYear.findByFinancialYear", query = "SELECT f FROM FinancialYear f WHERE f.financialYear = :financialYear"),
    @NamedQuery(name = "FinancialYear.findByCurrentYear", query = "SELECT f FROM FinancialYear f WHERE f.currentYear = :currentYear")})
public class FinancialYear implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "financial_year")
    private String financialYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "current_year")
    private boolean currentYear;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financialYear")
    private List<SubActivity> subActivityList;

    public FinancialYear() {
    }

    public FinancialYear(Short id) {
        this.id = id;
    }

    public FinancialYear(Short id, String financialYear, boolean currentYear) {
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

    @XmlTransient
    public List<SubActivity> getSubActivityList() {
        return subActivityList;
    }

    public void setSubActivityList(List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
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
        if (!(object instanceof FinancialYear)) {
            return false;
        }
        FinancialYear other = (FinancialYear) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.FinancialYear[ id=" + id + " ]";
    }
    
}
