/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author siech
 */
public class TrainingDetails implements Serializable, Comparable<TrainingDetails> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Size(max = 45)
    @Column(name = "end_date", length = 45)
    private String endDate;
    @Size(max = 45)
    @Column(length = 45)
    private String topic;
    @Size(max = 45)
    @Column(length = 45)
    private String venue;
    @Size(max = 45)
    @Column(name = "number_of_trainees", length = 45)
    private String numberOfTrainees;
    @Size(max = 200)
    @Column(length = 200)
    private String attendance;
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonDetails person;
    @JoinColumn(name = "person_role_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonRoleDetails personRole;

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public String getNumberOfTrainees() {
        return numberOfTrainees;
    }

    public void setNumberOfTrainees(String numberOfTrainees) {
        this.numberOfTrainees = numberOfTrainees;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public PersonDetails getPerson() {
        return person;
    }

    public void setPerson(PersonDetails person) {
        this.person = person;
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

}
