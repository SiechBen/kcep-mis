/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "training", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Training.findByIds", query = "SELECT t FROM Training t WHERE t.id IN (:ids)"),
    @NamedQuery(name = "Training.findByWardId", query = "SELECT t FROM Training t WHERE t.venue.ward.id = :wardId"),
    @NamedQuery(name = "Training.findByCountyId", query = "SELECT t FROM Training t WHERE t.venue.county.id = :countyId"),
    @NamedQuery(name = "Training.findBySubCountyId", query = "SELECT t FROM Training t WHERE t.venue.subCounty.id = :subCountyId"),
    @NamedQuery(name = "Training.findAll", query = "SELECT t FROM Training t"),
    @NamedQuery(name = "Training.findById", query = "SELECT t FROM Training t WHERE t.id = :id"),
    @NamedQuery(name = "Training.findByStartDate", query = "SELECT t FROM Training t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Training.findByEndDate", query = "SELECT t FROM Training t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "Training.findByNumberOfTrainees", query = "SELECT t FROM Training t WHERE t.numberOfTrainees = :numberOfTrainees"),
    @NamedQuery(name = "Training.findByAttendanceSheet", query = "SELECT t FROM Training t WHERE t.attendanceSheet = :attendanceSheet")})
public class Training implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "number_of_trainees")
    private Integer numberOfTrainees;
    @Size(max = 200)
    @Column(name = "attendance_sheet")
    private String attendanceSheet;
    @JoinColumn(name = "venue", referencedColumnName = "id")
    @ManyToOne
    private Location venue;
    @JoinColumn(name = "category_of_trainees", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon categoryOfTrainees;
    @JoinColumn(name = "topic", referencedColumnName = "id")
    @ManyToOne
    private Topic topic;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "training")
    private List<Trainee> traineeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "training")
    private List<Trainer> trainerList;

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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getNumberOfTrainees() {
        return numberOfTrainees;
    }

    public void setNumberOfTrainees(Integer numberOfTrainees) {
        this.numberOfTrainees = numberOfTrainees;
    }

    public String getAttendanceSheet() {
        return attendanceSheet;
    }

    public void setAttendanceSheet(String attendanceSheet) {
        this.attendanceSheet = attendanceSheet;
    }

    public Location getVenue() {
        return venue;
    }

    public void setVenue(Location venue) {
        this.venue = venue;
    }

    public Phenomenon getCategoryOfTrainees() {
        return categoryOfTrainees;
    }

    public void setCategoryOfTrainees(Phenomenon categoryOfTrainees) {
        this.categoryOfTrainees = categoryOfTrainees;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @XmlTransient
    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public void setTraineeList(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    @XmlTransient
    public List<Trainer> getTrainerList() {
        return trainerList;
    }

    public void setTrainerList(List<Trainer> trainerList) {
        this.trainerList = trainerList;
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
