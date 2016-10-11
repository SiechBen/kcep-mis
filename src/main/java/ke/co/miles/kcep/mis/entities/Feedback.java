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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "feedback", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findByWardId", query = "SELECT f FROM Feedback f WHERE f.farmer.location.ward.id = :wardId ORDER BY f.timePosted DESC"),
    @NamedQuery(name = "Feedback.findByCountyId", query = "SELECT f FROM Feedback f WHERE f.farmer.location.county.id = :countyId ORDER BY f.timePosted DESC"),
    @NamedQuery(name = "Feedback.findByRegionId", query = "SELECT f FROM Feedback f WHERE f.farmer.location.county.region.id = :regionId ORDER BY f.timePosted DESC"),
    @NamedQuery(name = "Feedback.findBySubCountyId", query = "SELECT f FROM Feedback f WHERE f.farmer.location.subCounty.id = :subCountyId ORDER BY f.timePosted DESC"),
    @NamedQuery(name = "Feedback.findLatest", query = "SELECT f FROM Feedback f ORDER BY f.timePosted DESC"),
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findById", query = "SELECT f FROM Feedback f WHERE f.id = :id"),
    @NamedQuery(name = "Feedback.findByTimePosted", query = "SELECT f FROM Feedback f WHERE f.timePosted = :timePosted")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_posted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timePosted;
    @JoinColumn(name = "farmer", referencedColumnName = "id")
    @ManyToOne
    private Person farmer;

    public Feedback() {
    }

    public Feedback(Integer id) {
        this.id = id;
    }

    public Feedback(Integer id, String message, Date timePosted) {
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

    public Person getFarmer() {
        return farmer;
    }

    public void setFarmer(Person farmer) {
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
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Feedback[ id=" + id + " ]";
    }

}
