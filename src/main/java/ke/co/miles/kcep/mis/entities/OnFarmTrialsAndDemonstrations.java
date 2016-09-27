package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "on_farm_trials_and_demonstrations", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OnFarmTrialsAndDemonstrations.findAll", query = "SELECT o FROM OnFarmTrialsAndDemonstrations o"),
    @NamedQuery(name = "OnFarmTrialsAndDemonstrations.findById", query = "SELECT o FROM OnFarmTrialsAndDemonstrations o WHERE o.id = :id"),
    @NamedQuery(name = "OnFarmTrialsAndDemonstrations.findByNoOfOnFarmTrials", query = "SELECT o FROM OnFarmTrialsAndDemonstrations o WHERE o.noOfOnFarmTrials = :noOfOnFarmTrials"),
    @NamedQuery(name = "OnFarmTrialsAndDemonstrations.findByNoOfDemonstrations", query = "SELECT o FROM OnFarmTrialsAndDemonstrations o WHERE o.noOfDemonstrations = :noOfDemonstrations")})
public class OnFarmTrialsAndDemonstrations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "no_of_on_farm_trials")
    private Short noOfOnFarmTrials;
    @Column(name = "no_of_demonstrations")
    private Short noOfDemonstrations;
    @Lob
    @Size(max = 65535)
    @Column(name = "target_locations")
    private String targetLocations;
    @Lob
    @Size(max = 65535)
    @Column(name = "attendance_sheet")
    private String attendanceSheet;
    @JoinColumn(name = "kalro_officer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person kalroOfficer;

    public OnFarmTrialsAndDemonstrations() {
    }

    public OnFarmTrialsAndDemonstrations(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNoOfOnFarmTrials() {
        return noOfOnFarmTrials;
    }

    public void setNoOfOnFarmTrials(Short noOfOnFarmTrials) {
        this.noOfOnFarmTrials = noOfOnFarmTrials;
    }

    public Short getNoOfDemonstrations() {
        return noOfDemonstrations;
    }

    public void setNoOfDemonstrations(Short noOfDemonstrations) {
        this.noOfDemonstrations = noOfDemonstrations;
    }

    public String getTargetLocations() {
        return targetLocations;
    }

    public void setTargetLocations(String targetLocations) {
        this.targetLocations = targetLocations;
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
        if (!(object instanceof OnFarmTrialsAndDemonstrations)) {
            return false;
        }
        OnFarmTrialsAndDemonstrations other = (OnFarmTrialsAndDemonstrations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.OnFarmTrialsAndDemonstrations[ id=" + id + " ]";
    }

}
