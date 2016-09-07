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
@Table(name = "e_voucher", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EVoucher.findAll", query = "SELECT e FROM EVoucher e"),
    @NamedQuery(name = "EVoucher.findById", query = "SELECT e FROM EVoucher e WHERE e.id = :id"),
    @NamedQuery(name = "EVoucher.findByAmount", query = "SELECT e FROM EVoucher e WHERE e.amount = :amount"),
    @NamedQuery(name = "EVoucher.findByDateRedeemed", query = "SELECT e FROM EVoucher e WHERE e.dateRedeemed = :dateRedeemed"),
    @NamedQuery(name = "EVoucher.findByInputsLogbookPage", query = "SELECT e FROM EVoucher e WHERE e.inputsLogbookPage = :inputsLogbookPage")})
public class EVoucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "amount")
    private String amount;
    @Column(name = "date_redeemed")
    @Temporal(TemporalType.DATE)
    private Date dateRedeemed;
    @Size(max = 200)
    @Column(name = "inputs_logbook_page")
    private String inputsLogbookPage;
    @JoinColumn(name = "input_type", referencedColumnName = "id")
    @ManyToOne
    private InputType inputType;
    @JoinColumn(name = "person", referencedColumnName = "id")
    @ManyToOne
    private Person person;

    public EVoucher() {
    }

    public EVoucher(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDateRedeemed() {
        return dateRedeemed;
    }

    public void setDateRedeemed(Date dateRedeemed) {
        this.dateRedeemed = dateRedeemed;
    }

    public String getInputsLogbookPage() {
        return inputsLogbookPage;
    }

    public void setInputsLogbookPage(String inputsLogbookPage) {
        this.inputsLogbookPage = inputsLogbookPage;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        if (!(object instanceof EVoucher)) {
            return false;
        }
        EVoucher other = (EVoucher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.EVoucher[ id=" + id + " ]";
    }
    
}
