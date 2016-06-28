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
public class FeedbackDetails implements Serializable, Comparable<FeedbackDetails> {

    public FeedbackDetails() {
    }

    public FeedbackDetails(Integer id) {
        this.id = id;
    }

    public FeedbackDetails(Integer id, String message, Date timePosted) {
        this.id = id;
        this.message = message;
        this.timePosted = timePosted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Date timePosted) {
        this.timePosted = timePosted;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackDetails)) {
            return false;
        }
        FeedbackDetails other = (FeedbackDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Feedback[ id=" + id + " ]";
    }

    @Override
    public int compareTo(FeedbackDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;

    /**
     * @return the shortMessage
     */
    public String getShortMessage() {
        return shortMessage;
    }

    /**
     * @param shortMessage the shortMessage to set
     */
    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    private String message;
    private Date timePosted;
    private String shortMessage;
    private PersonDetails farmer;
    private static final long serialVersionUID = 1L;

}
