/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 *
 * @author siech
 */
public class PurchaseDetails implements Serializable, Comparable<PurchaseDetails> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(length = 45)
    private String item;
    @Size(max = 45)
    @Column(name = "date_purchased", length = 45)
    private String datePurchased;
    @Size(max = 45)
    @Column(name = "serial_number", length = 45)
    private String serialNumber;
    @Size(max = 45)
    @Column(length = 45)
    private String description;
    @Size(max = 45)
    @Column(name = "target_office", length = 45)
    private String targetOffice;
    @Size(max = 45)
    @Column(length = 45)
    private String county;
    @Size(max = 45)
    @Column(name = "sub_county", length = 45)
    private String subCounty;
    @Size(max = 45)
    @Column(length = 45)
    private String cost;
    @Size(max = 45)
    @Column(name = "lpo_number", length = 45)
    private String lpoNumber;
    @Size(max = 300)
    @Column(length = 300)
    private String invoice;

    public PurchaseDetails() {
    }

    public PurchaseDetails(Integer id) {
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
        if (!(object instanceof PurchaseDetails)) {
            return false;
        }
        PurchaseDetails other = (PurchaseDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Purchase[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PurchaseDetails o) {
        return this.id.compareTo(o.getId());
    }
}
