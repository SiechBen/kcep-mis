/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author siech
 */
public class EVoucherDetails implements Serializable, Comparable<EVoucherDetails> {

    private static final long serialVersionUID = 1L;

    public EVoucherDetails() {
    }

    public EVoucherDetails(Integer id) {
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

    public InputTypeDetails getInputType() {
        return inputType;
    }

    public void setInputType(InputTypeDetails inputType) {
        this.inputType = inputType;
    }

    public PersonDetails getPerson() {
        return person;
    }

    public void setPerson(PersonDetails person) {
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
        if (!(object instanceof EVoucherDetails)) {
            return false;
        }
        EVoucherDetails other = (EVoucherDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.EVoucher[ id=" + id + " ]";
    }

    @Override
    public int compareTo(EVoucherDetails o) {
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

    private Integer id;
    private String amount;
    private String fileName;
    private Date dateRedeemed;
    private PersonDetails person;
    private String inputsLogbookPage;
    private InputTypeDetails inputType;

}
