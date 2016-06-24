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
public class TrainingDetails implements Serializable, Comparable<TrainingDetails> {

    private static final long serialVersionUID = 1L;

    public TrainingDetails() {
    }

    public TrainingDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocationDetails getVenue() {
        return venue;
    }

    public void setVenue(LocationDetails venue) {
        this.venue = venue;
    }

    public Integer getNumberOfTrainees() {
        return numberOfTrainees;
    }

    /**
     * @param numberOfTrainees the numberOfTrainees to set
     */
    public void setNumberOfTrainees(Integer numberOfTrainees) {
        this.numberOfTrainees = numberOfTrainees;
    }

    public String getAttendanceSheet() {
        return attendanceSheet;
    }

    public void setAttendanceSheet(String attendance) {
        this.attendanceSheet = attendance;
    }

    public PersonRoleDetail getCategoryOfTrainees() {
        return categoryOfTrainees;
    }

    public void setCategoryOfTrainees(PersonRoleDetail categoryOfTrainees) {
        this.categoryOfTrainees = categoryOfTrainees;
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
        if (!(object instanceof TrainingDetails)) {
            return false;
        }
        TrainingDetails other = (TrainingDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.Training[ id=" + id + " ]";
    }

    @Override
    public int compareTo(TrainingDetails o) {
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
    private String topic;
    private Date startDate;
    private Date endDate;
    private String fileName;
    private LocationDetails venue;
    private String attendanceSheet;
    private Integer numberOfTrainees;
    private PersonRoleDetail categoryOfTrainees;

}
