package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "account", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findByFarmerId", query = "SELECT a FROM Account a WHERE a.farmer.id = :farmerId"),
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
    @NamedQuery(name = "Account.findByAccountNumber", query = "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber"),
    @NamedQuery(name = "Account.findBySavings", query = "SELECT a FROM Account a WHERE a.savings = :savings"),
    @NamedQuery(name = "Account.findBySolId", query = "SELECT a FROM Account a WHERE a.solId = :solId")})
public class Account implements Serializable {

    @Column(name = "sol_id")
    private Short solId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "account_number")
    private String accountNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "savings")
    private BigDecimal savings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Loan> loanList;
    @JoinColumn(name = "farmer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person farmer;
    @JoinColumn(name = "ebl_branch", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EblBranch eblBranch;

    public Account() {
    }

    public Account(Integer id) {
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

    public BigDecimal getSavings() {
        return savings;
    }

    public void setSavings(BigDecimal savings) {
        this.savings = savings;
    }

    public Short getSolId() {
        return solId;
    }

    public void setSolId(Short solId) {
        this.solId = solId;
    }

    @XmlTransient
    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public Person getFarmer() {
        return farmer;
    }

    public void setFarmer(Person farmer) {
        this.farmer = farmer;
    }

    public EblBranch getEblBranch() {
        return eblBranch;
    }

    public void setEblBranch(EblBranch eblBranch) {
        this.eblBranch = eblBranch;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Account[ id=" + id + " ]";
    }

}
