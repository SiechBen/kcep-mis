/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(catalog = "kcep_mis", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Training.findAll", query = "SELECT t FROM Training t"),
    @NamedQuery(name = "Training.findById", query = "SELECT t FROM Training t WHERE t.id = :id"),
    @NamedQuery(name = "Training.findByStartDate", query = "SELECT t FROM Training t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Training.findByEndDate", query = "SELECT t FROM Training t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "Training.findByTopic", query = "SELECT t FROM Training t WHERE t.topic = :topic"),
    @NamedQuery(name = "Training.findByVenue", query = "SELECT t FROM Training t WHERE t.venue = :venue"),
    @NamedQuery(name = "Training.findByNumberOfTrainees", query = "SELECT t FROM Training t WHERE t.numberOfTrainees = :numberOfTrainees"),
    @NamedQuery(name = "Training.findByAttendance", query = "SELECT t FROM Training t WHERE t.attendance = :attendance")})
public class Training implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne
    private Person personId;
    @JoinColumn(name = "person_role_id", referencedColumnName = "id")
    @ManyToOne
    private PersonRole personRoleId;

    public Training() {
    }

    public Training(Integer id) {
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

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public PersonRole getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(PersonRole personRoleId) {
        this.personRoleId = personRoleId;
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
        if (!(object instanceof Training)) {
            return false;
        }
        Training other = (Training) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Training[ id=" + id + " ]";
    }
    
}
