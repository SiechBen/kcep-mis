/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author siech
 */
public class FinancialPlanDetails implements Serializable, Comparable<FinancialPlanDetails> {

    public FinancialPlanDetails() {
    }

    public FinancialPlanDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalsValue() {
        return totalsValue;
    }

    public void setTotalsValue(BigDecimal totalsValue) {
        this.totalsValue = totalsValue;
    }

    public Double getGokPercentage() {
        return gokPercentage;
    }

    public void setGokPercentage(Double gokPercentage) {
        this.gokPercentage = gokPercentage;
    }

    public Double getIfadLoanPercentage() {
        return ifadLoanPercentage;
    }

    public void setIfadLoanPercentage(Double ifadLoanPercentage) {
        this.ifadLoanPercentage = ifadLoanPercentage;
    }

    public Double getIfadGrantPercentage() {
        return ifadGrantPercentage;
    }

    public void setIfadGrantPercentage(Double ifadGrantPercentage) {
        this.ifadGrantPercentage = ifadGrantPercentage;
    }

    public Double getBeneficiariesPercentage() {
        return beneficiariesPercentage;
    }

    public void setBeneficiariesPercentage(Double beneficiariesPercentage) {
        this.beneficiariesPercentage = beneficiariesPercentage;
    }

    public Double getEuPercentage() {
        return euPercentage;
    }

    public void setEuPercentage(Double euPercentage) {
        this.euPercentage = euPercentage;
    }

    public Double getFinancialInstitutionPercentage() {
        return financialInstitutionPercentage;
    }

    public void setFinancialInstitutionPercentage(Double financialInstitutionPercentage) {
        this.financialInstitutionPercentage = financialInstitutionPercentage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FinancialPlanDetails)) {
            return false;
        }
        FinancialPlanDetails other = (FinancialPlanDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.FinancialPlanDetails[ id=" + getId() + " ]";
    }

    @Override
    public int compareTo(FinancialPlanDetails o) {
        return this.getId().compareTo(o.getId());
    }

    /**
     * @return the gokValue
     */
    public BigDecimal getGokValue() {
        return gokValue;
    }

    /**
     * @param gokValue the gokValue to set
     */
    public void setGokValue(BigDecimal gokValue) {
        this.gokValue = gokValue;
    }

    /**
     * @return the ifadLoanValue
     */
    public BigDecimal getIfadLoanValue() {
        return ifadLoanValue;
    }

    /**
     * @param ifadLoanValue the ifadLoanValue to set
     */
    public void setIfadLoanValue(BigDecimal ifadLoanValue) {
        this.ifadLoanValue = ifadLoanValue;
    }

    /**
     * @return the ifadGrantValue
     */
    public BigDecimal getIfadGrantValue() {
        return ifadGrantValue;
    }

    /**
     * @param ifadGrantValue the ifadGrantValue to set
     */
    public void setIfadGrantValue(BigDecimal ifadGrantValue) {
        this.ifadGrantValue = ifadGrantValue;
    }

    /**
     * @return the beneficiariesValue
     */
    public BigDecimal getBeneficiariesValue() {
        return beneficiariesValue;
    }

    /**
     * @param beneficiariesValue the beneficiariesValue to set
     */
    public void setBeneficiariesValue(BigDecimal beneficiariesValue) {
        this.beneficiariesValue = beneficiariesValue;
    }

    /**
     * @return the euValue
     */
    public BigDecimal getEuValue() {
        return euValue;
    }

    /**
     * @param euValue the euValue to set
     */
    public void setEuValue(BigDecimal euValue) {
        this.euValue = euValue;
    }

    /**
     * @return the financialInstitutionValue
     */
    public BigDecimal getFinancialInstitutionValue() {
        return financialInstitutionValue;
    }

    /**
     * @param financialInstitutionValue the financialInstitutionValue to set
     */
    public void setFinancialInstitutionValue(BigDecimal financialInstitutionValue) {
        this.financialInstitutionValue = financialInstitutionValue;
    }

    /**
     * @return the totalsPercentage
     */
    public Double getTotalsPercentage() {
        return totalsPercentage;
    }

    /**
     * @param totalsPercentage the totalsPercentage to set
     */
    public void setTotalsPercentage(Double totalsPercentage) {
        this.totalsPercentage = totalsPercentage;
    }

    /**
     * @return the totalInitialAllocationValue
     */
    public BigDecimal getTotalInitialAllocationValue() {
        return totalInitialAllocationValue;
    }

    /**
     * @param totalInitialAllocationValue the totalInitialAllocationValue to set
     */
    public void setTotalInitialAllocationValue(BigDecimal totalInitialAllocationValue) {
        this.totalInitialAllocationValue = totalInitialAllocationValue;
    }

    /**
     * @return the balanceValue
     */
    public BigDecimal getBalanceValue() {
        return balanceValue;
    }

    /**
     * @param balanceValue the balanceValue to set
     */
    public void setBalanceValue(BigDecimal balanceValue) {
        this.balanceValue = balanceValue;
    }

    /**
     * @return the totalInitialAllocationPercentage
     */
    public Double getTotalInitialAllocationPercentage() {
        return totalInitialAllocationPercentage;
    }

    /**
     * @param totalInitialAllocationPercentage the
     * totalInitialAllocationPercentage to set
     */
    public void setTotalInitialAllocationPercentage(Double totalInitialAllocationPercentage) {
        this.totalInitialAllocationPercentage = totalInitialAllocationPercentage;
    }

    /**
     * @return the balancePercentage
     */
    public Double getBalancePercentage() {
        return balancePercentage;
    }

    /**
     * @param balancePercentage the balancePercentage to set
     */
    public void setBalancePercentage(Double balancePercentage) {
        this.balancePercentage = balancePercentage;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private BigDecimal totalsValue;
    private Double totalsPercentage;
    private Double gokPercentage;
    private Double ifadLoanPercentage;
    private Double ifadGrantPercentage;
    private Double beneficiariesPercentage;
    private Double euPercentage;
    private Double financialInstitutionPercentage;
    private BigDecimal gokValue;
    private BigDecimal ifadLoanValue;
    private BigDecimal ifadGrantValue;
    private BigDecimal beneficiariesValue;
    private BigDecimal euValue;
    private BigDecimal financialInstitutionValue;
    private BigDecimal totalInitialAllocationValue;
    private BigDecimal balanceValue;
    private Double totalInitialAllocationPercentage;
    private Double balancePercentage;

}
