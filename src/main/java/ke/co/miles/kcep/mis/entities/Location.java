package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "location", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findById", query = "SELECT l FROM Location l WHERE l.id = :id"),
    @NamedQuery(name = "Location.findByLongitude", query = "SELECT l FROM Location l WHERE l.longitude = :longitude"),
    @NamedQuery(name = "Location.findByLatitude", query = "SELECT l FROM Location l WHERE l.latitude = :latitude")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Column(name = "latitude")
    private BigDecimal latitude;
    @OneToMany(mappedBy = "location")
    private List<Warehouse> warehouseList;
    @OneToMany(mappedBy = "location")
    private List<Person> personList;
    @OneToMany(mappedBy = "venue")
    private List<Training> trainingList;
    @JoinColumn(name = "county", referencedColumnName = "id")
    @ManyToOne
    private County county;
    @JoinColumn(name = "divisional_location", referencedColumnName = "id")
    @ManyToOne
    private DivisionalLocation divisionalLocation;
    @JoinColumn(name = "sub_county", referencedColumnName = "id")
    @ManyToOne
    private SubCounty subCounty;
    @JoinColumn(name = "village", referencedColumnName = "id")
    @ManyToOne
    private Village village;
    @JoinColumn(name = "ward", referencedColumnName = "id")
    @ManyToOne
    private Ward ward;

    public Location() {
    }

    public Location(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @XmlTransient
    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @XmlTransient
    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public DivisionalLocation getDivisionalLocation() {
        return divisionalLocation;
    }

    public void setDivisionalLocation(DivisionalLocation divisionalLocation) {
        this.divisionalLocation = divisionalLocation;
    }

    public SubCounty getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(SubCounty subCounty) {
        this.subCounty = subCounty;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
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
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Location[ id=" + id + " ]";
    }

}
