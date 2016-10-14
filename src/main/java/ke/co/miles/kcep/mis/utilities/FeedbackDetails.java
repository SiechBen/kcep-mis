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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FeedbackDetails)) {
            return false;
        }
        FeedbackDetails other = (FeedbackDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.FeedbackDetails[ id=" + id + " ]";
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public PersonDetails getSubmitter() {
        return submitter;
    }

    public void setSubmitter(PersonDetails submitter) {
        this.submitter = submitter;
    }

    public PhenomenonDetails getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(PhenomenonDetails feedbackType) {
        this.feedbackType = feedbackType;
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

    private String message;
    private Date timePosted;
    private String fileName;
    private String attachment;
    private String shortMessage;
    private PersonDetails submitter;
    private PhenomenonDetails feedbackType;
    private static final long serialVersionUID = 1L;

}
