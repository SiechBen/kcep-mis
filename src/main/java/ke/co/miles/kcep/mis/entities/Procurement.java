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
@Table(name = "procurement", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procurement.findByGfssCodeId", query = "SELECT p FROM Procurement p WHERE p.gfssCode.id = :gfssCodeId"),
    @NamedQuery(name = "Procurement.findAll", query = "SELECT p FROM Procurement p"),
    @NamedQuery(name = "Procurement.findById", query = "SELECT p FROM Procurement p WHERE p.id = :id"),
    @NamedQuery(name = "Procurement.findByItem", query = "SELECT p FROM Procurement p WHERE p.item = :item"),
    @NamedQuery(name = "Procurement.findByDatePurchased", query = "SELECT p FROM Procurement p WHERE p.datePurchased = :datePurchased"),
    @NamedQuery(name = "Procurement.findBySerialNumber", query = "SELECT p FROM Procurement p WHERE p.serialNumber = :serialNumber"),
    @NamedQuery(name = "Procurement.findByDescription", query = "SELECT p FROM Procurement p WHERE p.description = :description"),
    @NamedQuery(name = "Procurement.findByTargetOffice", query = "SELECT p FROM Procurement p WHERE p.targetOffice = :targetOffice"),
    @NamedQuery(name = "Procurement.findByCost", query = "SELECT p FROM Procurement p WHERE p.cost = :cost"),
    @NamedQuery(name = "Procurement.findByLpoNumber", query = "SELECT p FROM Procurement p WHERE p.lpoNumber = :lpoNumber"),
    @NamedQuery(name = "Procurement.findByInvoiceOrReceipt", query = "SELECT p FROM Procurement p WHERE p.invoiceOrReceipt = :invoiceOrReceipt")})
public class Procurement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "item")
    private String item;
    @Size(max = 45)
    @Column(name = "date_purchased")
    private String datePurchased;
    @Size(max = 45)
    @Column(name = "serial_number")
    private String serialNumber;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "target_office")
    private String targetOffice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private BigDecimal cost;
    @Size(max = 45)
    @Column(name = "lpo_number")
    private String lpoNumber;
    @Size(max = 300)
    @Column(name = "invoice_or_receipt")
    private String invoiceOrReceipt;
    @JoinColumn(name = "gfss_code", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon gfssCode;
    @JoinColumn(name = "sub_county", referencedColumnName = "id")
    @ManyToOne
    private SubCounty subCounty;
    @JoinColumn(name = "county", referencedColumnName = "id")
    @ManyToOne
    private County county;

    public Procurement() {
    }

    public Procurement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetOffice() {
        return targetOffice;
    }

    public void setTargetOffice(String targetOffice) {
        this.targetOffice = targetOffice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getLpoNumber() {
        return lpoNumber;
    }

    public void setLpoNumber(String lpoNumber) {
        this.lpoNumber = lpoNumber;
    }

    public String getInvoiceOrReceipt() {
        return invoiceOrReceipt;
    }

    public void setInvoiceOrReceipt(String invoiceOrReceipt) {
        this.invoiceOrReceipt = invoiceOrReceipt;
    }

    public Phenomenon getGfssCode() {
        return gfssCode;
    }

    public void setGfssCode(Phenomenon gfssCode) {
        this.gfssCode = gfssCode;
    }

    public SubCounty getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(SubCounty subCounty) {
        this.subCounty = subCounty;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
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
        if (!(object instanceof Procurement)) {
            return false;
        }
        Procurement other = (Procurement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Procurement[ id=" + id + " ]";
    }
    
}
