/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author siech
 */
public class ProcurementDetails implements Serializable, Comparable<ProcurementDetails> {

    private static final long serialVersionUID = 1L;

    public ProcurementDetails() {
    }

    public ProcurementDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhenomenonDetails getGfssCode() {
        return gfssCode;
    }

    public void setGfssCode(PhenomenonDetails gfssCode) {
        this.gfssCode = gfssCode;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
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

    public CountyDetails getCounty() {
        return county;
    }

    public void setCounty(CountyDetails county) {
        this.county = county;
    }

    public SubCountyDetails getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(SubCountyDetails subCounty) {
        this.subCounty = subCounty;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProcurementDetails)) {
            return false;
        }
        ProcurementDetails other = (ProcurementDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ProcurementDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ProcurementDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    private Integer id;
    private String item;
    private BigDecimal cost;
    private String fileName;
    private String lpoNumber;
    private String description;
    private String targetOffice;
    private String serialNumber;
    private Date datePurchased;
    private CountyDetails county;
    private String invoiceOrReceipt;
    private SubCountyDetails subCounty;
    private PhenomenonDetails gfssCode;

}
