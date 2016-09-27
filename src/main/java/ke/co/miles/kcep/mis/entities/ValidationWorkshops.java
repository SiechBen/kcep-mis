package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "validation_workshops", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValidationWorkshops.findAll", query = "SELECT v FROM ValidationWorkshops v"),
    @NamedQuery(name = "ValidationWorkshops.findById", query = "SELECT v FROM ValidationWorkshops v WHERE v.id = :id"),
    @NamedQuery(name = "ValidationWorkshops.findByNoInWesternRegion", query = "SELECT v FROM ValidationWorkshops v WHERE v.noInWesternRegion = :noInWesternRegion"),
    @NamedQuery(name = "ValidationWorkshops.findByNoInEasternRegion", query = "SELECT v FROM ValidationWorkshops v WHERE v.noInEasternRegion = :noInEasternRegion")})
public class ValidationWorkshops implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "no_in_western_region")
    private Integer noInWesternRegion;
    @Column(name = "no_in_eastern_region")
    private Integer noInEasternRegion;
    @Lob
    @Size(max = 65535)
    @Column(name = "attendance_sheet")
    private String attendanceSheet;
    @JoinColumn(name = "kalro_officer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person kalroOfficer;

    public ValidationWorkshops() {
    }

    public ValidationWorkshops(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoInWesternRegion() {
        return noInWesternRegion;
    }

    public void setNoInWesternRegion(Integer noInWesternRegion) {
        this.noInWesternRegion = noInWesternRegion;
    }

    public Integer getNoInEasternRegion() {
        return noInEasternRegion;
    }

    public void setNoInEasternRegion(Integer noInEasternRegion) {
        this.noInEasternRegion = noInEasternRegion;
    }

    public String getAttendanceSheet() {
        return attendanceSheet;
    }

    public void setAttendanceSheet(String attendanceSheet) {
        this.attendanceSheet = attendanceSheet;
    }

    public Person getKalroOfficer() {
        return kalroOfficer;
    }

    public void setKalroOfficer(Person kalroOfficer) {
        this.kalroOfficer = kalroOfficer;
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
        if (!(object instanceof ValidationWorkshops)) {
            return false;
        }
        ValidationWorkshops other = (ValidationWorkshops) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ValidationWorkshops[ id=" + id + " ]";
    }

}
