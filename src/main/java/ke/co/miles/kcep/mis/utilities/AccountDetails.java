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
public class AccountDetails implements Serializable, Comparable<AccountDetails> {

    public AccountDetails() {
    }

    public AccountDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public EblBranchDetails getEblBranch() {
        return eblBranch;
    }

    public void setEblBranch(EblBranchDetails eblBranch) {
        this.eblBranch = eblBranch;
    }

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public PersonDetails getFarmer() {
        return farmer;
    }

    public void setFarmer(PersonDetails farmer) {
        this.farmer = farmer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AccountDetails)) {
            return false;
        }
        AccountDetails other = (AccountDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.AccountDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(AccountDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the savings
     */
    public BigDecimal getSavings() {
        return savings;
    }

    /**
     * @param savings the savings to set
     */
    public void setSavings(BigDecimal savings) {
        this.savings = savings;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String accountNumber;
    private EblBranchDetails eblBranch;
    private String solId;
    private PersonDetails farmer;
    private BigDecimal savings;

}
