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
@Table(name = "soil_fertility_package", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SoilFertilityPackage.findAll", query = "SELECT s FROM SoilFertilityPackage s"),
    @NamedQuery(name = "SoilFertilityPackage.findById", query = "SELECT s FROM SoilFertilityPackage s WHERE s.id = :id"),
    @NamedQuery(name = "SoilFertilityPackage.findByNoOfSamplingAndAnalysisActivities", query = "SELECT s FROM SoilFertilityPackage s WHERE s.noOfSamplingAndAnalysisActivities = :noOfSamplingAndAnalysisActivities"),
    @NamedQuery(name = "SoilFertilityPackage.findByNoOfPackagesDeveloped", query = "SELECT s FROM SoilFertilityPackage s WHERE s.noOfPackagesDeveloped = :noOfPackagesDeveloped"),
    @NamedQuery(name = "SoilFertilityPackage.findByNoOfMeetingsForEVoucherDefinition", query = "SELECT s FROM SoilFertilityPackage s WHERE s.noOfMeetingsForEVoucherDefinition = :noOfMeetingsForEVoucherDefinition")})
public class SoilFertilityPackage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "no_of_sampling_and_analysis_activities")
    private Short noOfSamplingAndAnalysisActivities;
    @Column(name = "no_of_packages_developed")
    private Short noOfPackagesDeveloped;
    @Lob
    @Size(max = 65535)
    @Column(name = "target_locations")
    private String targetLocations;
    @Column(name = "no_of_meetings_for_e_voucher_definition")
    private Short noOfMeetingsForEVoucherDefinition;
    @JoinColumn(name = "kalro_officer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person kalroOfficer;

    public SoilFertilityPackage() {
    }

    public SoilFertilityPackage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNoOfSamplingAndAnalysisActivities() {
        return noOfSamplingAndAnalysisActivities;
    }

    public void setNoOfSamplingAndAnalysisActivities(Short noOfSamplingAndAnalysisActivities) {
        this.noOfSamplingAndAnalysisActivities = noOfSamplingAndAnalysisActivities;
    }

    public Short getNoOfPackagesDeveloped() {
        return noOfPackagesDeveloped;
    }

    public void setNoOfPackagesDeveloped(Short noOfPackagesDeveloped) {
        this.noOfPackagesDeveloped = noOfPackagesDeveloped;
    }

    public String getTargetLocations() {
        return targetLocations;
    }

    public void setTargetLocations(String targetLocations) {
        this.targetLocations = targetLocations;
    }

    public Short getNoOfMeetingsForEVoucherDefinition() {
        return noOfMeetingsForEVoucherDefinition;
    }

    public void setNoOfMeetingsForEVoucherDefinition(Short noOfMeetingsForEVoucherDefinition) {
        this.noOfMeetingsForEVoucherDefinition = noOfMeetingsForEVoucherDefinition;
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
        if (!(object instanceof SoilFertilityPackage)) {
            return false;
        }
        SoilFertilityPackage other = (SoilFertilityPackage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SoilFertilityPackage[ id=" + id + " ]";
    }

}
