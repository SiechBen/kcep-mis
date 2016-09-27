package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "technology", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Technology.findAll", query = "SELECT t FROM Technology t"),
    @NamedQuery(name = "Technology.findById", query = "SELECT t FROM Technology t WHERE t.id = :id"),
    @NamedQuery(name = "Technology.findByNumberOfStudiesConducted", query = "SELECT t FROM Technology t WHERE t.numberOfStudiesConducted = :numberOfStudiesConducted"),
    @NamedQuery(name = "Technology.findByName", query = "SELECT t FROM Technology t WHERE t.name = :name"),
    @NamedQuery(name = "Technology.findByTypePurpose", query = "SELECT t FROM Technology t WHERE t.typePurpose = :typePurpose")})
public class Technology implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "number_of_studies_conducted")
    private Short numberOfStudiesConducted;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 400)
    @Column(name = "type_purpose")
    private String typePurpose;
    @Lob
    @Size(max = 65535)
    @Column(name = "target_sub_counties")
    private String targetSubCounties;
    @JoinColumn(name = "kalro_officer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person kalroOfficer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technology")
    private List<TechnologyTargetCounty> technologyTargetCountyList;

    public Technology() {
    }

    public Technology(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNumberOfStudiesConducted() {
        return numberOfStudiesConducted;
    }

    public void setNumberOfStudiesConducted(Short numberOfStudiesConducted) {
        this.numberOfStudiesConducted = numberOfStudiesConducted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypePurpose() {
        return typePurpose;
    }

    public void setTypePurpose(String typePurpose) {
        this.typePurpose = typePurpose;
    }

    public String getTargetSubCounties() {
        return targetSubCounties;
    }

    public void setTargetSubCounties(String targetSubCounties) {
        this.targetSubCounties = targetSubCounties;
    }

    public Person getKalroOfficer() {
        return kalroOfficer;
    }

    public void setKalroOfficer(Person kalroOfficer) {
        this.kalroOfficer = kalroOfficer;
    }

    @XmlTransient
    public List<TechnologyTargetCounty> getTechnologyTargetCountyList() {
        return technologyTargetCountyList;
    }

    public void setTechnologyTargetCountyList(List<TechnologyTargetCounty> technologyTargetCountyList) {
        this.technologyTargetCountyList = technologyTargetCountyList;
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
        if (!(object instanceof Technology)) {
            return false;
        }
        Technology other = (Technology) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Technology[ id=" + id + " ]";
    }

}
