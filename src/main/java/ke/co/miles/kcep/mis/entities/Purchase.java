/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "purchase", catalog = "kcep_mis", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
    @NamedQuery(name = "Purchase.findById", query = "SELECT p FROM Purchase p WHERE p.id = :id"),
    @NamedQuery(name = "Purchase.findByItem", query = "SELECT p FROM Purchase p WHERE p.item = :item"),
    @NamedQuery(name = "Purchase.findByDatePurchased", query = "SELECT p FROM Purchase p WHERE p.datePurchased = :datePurchased"),
    @NamedQuery(name = "Purchase.findBySerialNumber", query = "SELECT p FROM Purchase p WHERE p.serialNumber = :serialNumber"),
    @NamedQuery(name = "Purchase.findByDescription", query = "SELECT p FROM Purchase p WHERE p.description = :description"),
    @NamedQuery(name = "Purchase.findByTargetOffice", query = "SELECT p FROM Purchase p WHERE p.targetOffice = :targetOffice"),
    @NamedQuery(name = "Purchase.findByCounty", query = "SELECT p FROM Purchase p WHERE p.county = :county"),
    @NamedQuery(name = "Purchase.findBySubCounty", query = "SELECT p FROM Purchase p WHERE p.subCounty = :subCounty"),
    @NamedQuery(name = "Purchase.findByCost", query = "SELECT p FROM Purchase p WHERE p.cost = :cost"),
    @NamedQuery(name = "Purchase.findByLpoNumber", query = "SELECT p FROM Purchase p WHERE p.lpoNumber = :lpoNumber"),
    @NamedQuery(name = "Purchase.findByInvoice", query = "SELECT p FROM Purchase p WHERE p.invoice = :invoice")})
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(name = "item", length = 45)
    private String item;
    @Size(max = 45)
    @Column(name = "date_purchased", length = 45)
    private String datePurchased;
    @Size(max = 45)
    @Column(name = "serial_number", length = 45)
    private String serialNumber;
    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String description;
    @Size(max = 45)
    @Column(name = "target_office", length = 45)
    private String targetOffice;
    @Size(max = 45)
    @Column(name = "county", length = 45)
    private String county;
    @Size(max = 45)
    @Column(name = "sub_county", length = 45)
    private String subCounty;
    @Size(max = 45)
    @Column(name = "cost", length = 45)
    private String cost;
    @Size(max = 45)
    @Column(name = "lpo_number", length = 45)
    private String lpoNumber;
    @Size(max = 300)
    @Column(name = "invoice", length = 300)
    private String invoice;

    public Purchase() {
    }

    public Purchase(Integer id) {
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLpoNumber() {
        return lpoNumber;
    }

    public void setLpoNumber(String lpoNumber) {
        this.lpoNumber = lpoNumber;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
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
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Purchase[ id=" + id + " ]";
    }
    
}
