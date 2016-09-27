package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "extension_and_field_visit_data", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtensionAndFieldVisitData.findAll", query = "SELECT e FROM ExtensionAndFieldVisitData e"),
    @NamedQuery(name = "ExtensionAndFieldVisitData.findById", query = "SELECT e FROM ExtensionAndFieldVisitData e WHERE e.id = :id"),
    @NamedQuery(name = "ExtensionAndFieldVisitData.findByNumberOfPeopleSeekingOrOfferedAdvisoryServices", query = "SELECT e FROM ExtensionAndFieldVisitData e WHERE e.numberOfPeopleSeekingOrOfferedAdvisoryServices = :numberOfPeopleSeekingOrOfferedAdvisoryServices"),
    @NamedQuery(name = "ExtensionAndFieldVisitData.findByNatureOfAdvisoryServices", query = "SELECT e FROM ExtensionAndFieldVisitData e WHERE e.natureOfAdvisoryServices = :natureOfAdvisoryServices"),
    @NamedQuery(name = "ExtensionAndFieldVisitData.findByNumberOfFieldVisitsConducted", query = "SELECT e FROM ExtensionAndFieldVisitData e WHERE e.numberOfFieldVisitsConducted = :numberOfFieldVisitsConducted"),
    @NamedQuery(name = "ExtensionAndFieldVisitData.findByNumberOfFarmersVisited", query = "SELECT e FROM ExtensionAndFieldVisitData e WHERE e.numberOfFarmersVisited = :numberOfFarmersVisited"),
    @NamedQuery(name = "ExtensionAndFieldVisitData.findByFieldVisitsWardLocations", query = "SELECT e FROM ExtensionAndFieldVisitData e WHERE e.fieldVisitsWardLocations = :fieldVisitsWardLocations")})
public class ExtensionAndFieldVisitData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "number_of_people_seeking_or_offered_advisory_services")
    private Integer numberOfPeopleSeekingOrOfferedAdvisoryServices;
    @Size(max = 400)
    @Column(name = "nature_of_advisory_services")
    private String natureOfAdvisoryServices;
    @Column(name = "number_of_field_visits_conducted")
    private Integer numberOfFieldVisitsConducted;
    @Column(name = "number_of_farmers_visited")
    private Integer numberOfFarmersVisited;
    @Size(max = 400)
    @Column(name = "field_visits_ward_locations")
    private String fieldVisitsWardLocations;
    @JoinColumn(name = "ward_extension_officer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person wardExtensionOfficer;

    public ExtensionAndFieldVisitData() {
    }

    public ExtensionAndFieldVisitData(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfPeopleSeekingOrOfferedAdvisoryServices() {
        return numberOfPeopleSeekingOrOfferedAdvisoryServices;
    }

    public void setNumberOfPeopleSeekingOrOfferedAdvisoryServices(Integer numberOfPeopleSeekingOrOfferedAdvisoryServices) {
        this.numberOfPeopleSeekingOrOfferedAdvisoryServices = numberOfPeopleSeekingOrOfferedAdvisoryServices;
    }

    public String getNatureOfAdvisoryServices() {
        return natureOfAdvisoryServices;
    }

    public void setNatureOfAdvisoryServices(String natureOfAdvisoryServices) {
        this.natureOfAdvisoryServices = natureOfAdvisoryServices;
    }

    public Integer getNumberOfFieldVisitsConducted() {
        return numberOfFieldVisitsConducted;
    }

    public void setNumberOfFieldVisitsConducted(Integer numberOfFieldVisitsConducted) {
        this.numberOfFieldVisitsConducted = numberOfFieldVisitsConducted;
    }

    public Integer getNumberOfFarmersVisited() {
        return numberOfFarmersVisited;
    }

    public void setNumberOfFarmersVisited(Integer numberOfFarmersVisited) {
        this.numberOfFarmersVisited = numberOfFarmersVisited;
    }

    public String getFieldVisitsWardLocations() {
        return fieldVisitsWardLocations;
    }

    public void setFieldVisitsWardLocations(String fieldVisitsWardLocations) {
        this.fieldVisitsWardLocations = fieldVisitsWardLocations;
    }

    public Person getWardExtensionOfficer() {
        return wardExtensionOfficer;
    }

    public void setWardExtensionOfficer(Person wardExtensionOfficer) {
        this.wardExtensionOfficer = wardExtensionOfficer;
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
        if (!(object instanceof ExtensionAndFieldVisitData)) {
            return false;
        }
        ExtensionAndFieldVisitData other = (ExtensionAndFieldVisitData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ExtensionAndFieldVisitData[ id=" + id + " ]";
    }

}
