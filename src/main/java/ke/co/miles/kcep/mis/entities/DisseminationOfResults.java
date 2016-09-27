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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "dissemination_of_results", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DisseminationOfResults.findAll", query = "SELECT d FROM DisseminationOfResults d"),
    @NamedQuery(name = "DisseminationOfResults.findById", query = "SELECT d FROM DisseminationOfResults d WHERE d.id = :id"),
    @NamedQuery(name = "DisseminationOfResults.findByNoOfResultSetInWesternRegion", query = "SELECT d FROM DisseminationOfResults d WHERE d.noOfResultSetInWesternRegion = :noOfResultSetInWesternRegion"),
    @NamedQuery(name = "DisseminationOfResults.findByNoOfResultSetInEasternRegion", query = "SELECT d FROM DisseminationOfResults d WHERE d.noOfResultSetInEasternRegion = :noOfResultSetInEasternRegion")})
public class DisseminationOfResults implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "no_of_result_set_in_western_region")
    private Integer noOfResultSetInWesternRegion;
    @Column(name = "no_of_result_set_in_eastern_region")
    private Integer noOfResultSetInEasternRegion;
    @JoinColumn(name = "kalro_officer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person kalroOfficer;

    public DisseminationOfResults() {
    }

    public DisseminationOfResults(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoOfResultSetInWesternRegion() {
        return noOfResultSetInWesternRegion;
    }

    public void setNoOfResultSetInWesternRegion(Integer noOfResultSetInWesternRegion) {
        this.noOfResultSetInWesternRegion = noOfResultSetInWesternRegion;
    }

    public Integer getNoOfResultSetInEasternRegion() {
        return noOfResultSetInEasternRegion;
    }

    public void setNoOfResultSetInEasternRegion(Integer noOfResultSetInEasternRegion) {
        this.noOfResultSetInEasternRegion = noOfResultSetInEasternRegion;
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
        if (!(object instanceof DisseminationOfResults)) {
            return false;
        }
        DisseminationOfResults other = (DisseminationOfResults) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.DisseminationOfResults[ id=" + id + " ]";
    }

}
