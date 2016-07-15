/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "budget", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Budget.findAll", query = "SELECT b FROM Budget b"),
    @NamedQuery(name = "Budget.findById", query = "SELECT b FROM Budget b WHERE b.id = :id"),
    @NamedQuery(name = "Budget.findByGokValue", query = "SELECT b FROM Budget b WHERE b.gokValue = :gokValue"),
    @NamedQuery(name = "Budget.findByIfadLoanValue", query = "SELECT b FROM Budget b WHERE b.ifadLoanValue = :ifadLoanValue"),
    @NamedQuery(name = "Budget.findByIfadGrantValue", query = "SELECT b FROM Budget b WHERE b.ifadGrantValue = :ifadGrantValue"),
    @NamedQuery(name = "Budget.findByBeneficiariesValue", query = "SELECT b FROM Budget b WHERE b.beneficiariesValue = :beneficiariesValue"),
    @NamedQuery(name = "Budget.findByEuValue", query = "SELECT b FROM Budget b WHERE b.euValue = :euValue"),
    @NamedQuery(name = "Budget.findByFinancialInstitutionValue", query = "SELECT b FROM Budget b WHERE b.financialInstitutionValue = :financialInstitutionValue"),
    @NamedQuery(name = "Budget.findByGokPercentage", query = "SELECT b FROM Budget b WHERE b.gokPercentage = :gokPercentage"),
    @NamedQuery(name = "Budget.findByIfadLoanPercentage", query = "SELECT b FROM Budget b WHERE b.ifadLoanPercentage = :ifadLoanPercentage"),
    @NamedQuery(name = "Budget.findByIfadGrantPercentage", query = "SELECT b FROM Budget b WHERE b.ifadGrantPercentage = :ifadGrantPercentage"),
    @NamedQuery(name = "Budget.findByBeneficiariesPercentage", query = "SELECT b FROM Budget b WHERE b.beneficiariesPercentage = :beneficiariesPercentage"),
    @NamedQuery(name = "Budget.findByEuPercentage", query = "SELECT b FROM Budget b WHERE b.euPercentage = :euPercentage"),
    @NamedQuery(name = "Budget.findByFinancialInstitutionPercentage", query = "SELECT b FROM Budget b WHERE b.financialInstitutionPercentage = :financialInstitutionPercentage")})
public class Budget implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gok_value")
    private BigDecimal gokValue;
    @Size(max = 45)
    @Column(name = "ifad_loan_value")
    private String ifadLoanValue;
    @Size(max = 45)
    @Column(name = "ifad_grant_value")
    private String ifadGrantValue;
    @Size(max = 45)
    @Column(name = "beneficiaries_value")
    private String beneficiariesValue;
    @Size(max = 45)
    @Column(name = "eu_value")
    private String euValue;
    @Size(max = 45)
    @Column(name = "financial_institution_value")
    private String financialInstitutionValue;
    @Size(max = 45)
    @Column(name = "gok_percentage")
    private String gokPercentage;
    @Size(max = 45)
    @Column(name = "ifad_loan_percentage")
    private String ifadLoanPercentage;
    @Size(max = 45)
    @Column(name = "ifad_grant_percentage")
    private String ifadGrantPercentage;
    @Size(max = 45)
    @Column(name = "beneficiaries_percentage")
    private String beneficiariesPercentage;
    @Size(max = 45)
    @Column(name = "eu_percentage")
    private String euPercentage;
    @Size(max = 45)
    @Column(name = "financial_institution_percentage")
    private String financialInstitutionPercentage;

    public Budget() {
    }

    public Budget(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getGokValue() {
        return gokValue;
    }

    public void setGokValue(BigDecimal gokValue) {
        this.gokValue = gokValue;
    }

    public String getIfadLoanValue() {
        return ifadLoanValue;
    }

    public void setIfadLoanValue(String ifadLoanValue) {
        this.ifadLoanValue = ifadLoanValue;
    }

    public String getIfadGrantValue() {
        return ifadGrantValue;
    }

    public void setIfadGrantValue(String ifadGrantValue) {
        this.ifadGrantValue = ifadGrantValue;
    }

    public String getBeneficiariesValue() {
        return beneficiariesValue;
    }

    public void setBeneficiariesValue(String beneficiariesValue) {
        this.beneficiariesValue = beneficiariesValue;
    }

    public String getEuValue() {
        return euValue;
    }

    public void setEuValue(String euValue) {
        this.euValue = euValue;
    }

    public String getFinancialInstitutionValue() {
        return financialInstitutionValue;
    }

    public void setFinancialInstitutionValue(String financialInstitutionValue) {
        this.financialInstitutionValue = financialInstitutionValue;
    }

    public String getGokPercentage() {
        return gokPercentage;
    }

    public void setGokPercentage(String gokPercentage) {
        this.gokPercentage = gokPercentage;
    }

    public String getIfadLoanPercentage() {
        return ifadLoanPercentage;
    }

    public void setIfadLoanPercentage(String ifadLoanPercentage) {
        this.ifadLoanPercentage = ifadLoanPercentage;
    }

    public String getIfadGrantPercentage() {
        return ifadGrantPercentage;
    }

    public void setIfadGrantPercentage(String ifadGrantPercentage) {
        this.ifadGrantPercentage = ifadGrantPercentage;
    }

    public String getBeneficiariesPercentage() {
        return beneficiariesPercentage;
    }

    public void setBeneficiariesPercentage(String beneficiariesPercentage) {
        this.beneficiariesPercentage = beneficiariesPercentage;
    }

    public String getEuPercentage() {
        return euPercentage;
    }

    public void setEuPercentage(String euPercentage) {
        this.euPercentage = euPercentage;
    }

    public String getFinancialInstitutionPercentage() {
        return financialInstitutionPercentage;
    }

    public void setFinancialInstitutionPercentage(String financialInstitutionPercentage) {
        this.financialInstitutionPercentage = financialInstitutionPercentage;
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
        if (!(object instanceof Budget)) {
            return false;
        }
        Budget other = (Budget) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Budget[ id=" + id + " ]";
    }
    
}
