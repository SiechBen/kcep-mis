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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
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

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public PersonDetails getTrainer() {
        return trainer;
    }

    public void setTrainer(PersonDetails trainer) {
        this.trainer = trainer;
    }

    public PersonRoleDetails getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRoleDetails personRole) {
        this.personRole = personRole;
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Training[ id=" + id + " ]";
    }

    @Override
    public int compareTo(TrainingDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private Date startDate;
    private Date endDate;
    private String topic;
    private String venue;
    private Integer numberOfTrainees;
    private String attendance;
    private PersonDetails trainer;
    private PersonRoleDetails personRole;

}
