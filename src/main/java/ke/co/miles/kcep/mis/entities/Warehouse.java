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
@Table(name = "warehouse", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warehouse.findByWardId", query = "SELECT w FROM Warehouse w WHERE w.location.ward.id = :wardId"),
    @NamedQuery(name = "Warehouse.findByCountyId", query = "SELECT w FROM Warehouse w WHERE w.location.county.id = :countyId"),
    @NamedQuery(name = "Warehouse.findBySubCountyId", query = "SELECT w FROM Warehouse w WHERE w.location.subCounty.id = :subCountyId"),
    @NamedQuery(name = "Warehouse.findByWarehouseOperatorId", query = "SELECT w FROM Warehouse w WHERE w.warehouseOperator.id = :warehouseOperatorId"),
    @NamedQuery(name = "Warehouse.findAll", query = "SELECT w FROM Warehouse w"),
    @NamedQuery(name = "Warehouse.findById", query = "SELECT w FROM Warehouse w WHERE w.id = :id"),
    @NamedQuery(name = "Warehouse.findByName", query = "SELECT w FROM Warehouse w WHERE w.name = :name"),
    @NamedQuery(name = "Warehouse.findByCapacity", query = "SELECT w FROM Warehouse w WHERE w.capacity = :capacity"),
    @NamedQuery(name = "Warehouse.findByOffersWrs", query = "SELECT w FROM Warehouse w WHERE w.offersWrs = :offersWrs"),
    @NamedQuery(name = "Warehouse.findByCertified", query = "SELECT w FROM Warehouse w WHERE w.certified = :certified")})
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "name")
    private String name;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "offers_wrs")
    private Boolean offersWrs;
    @Column(name = "certified")
    private Boolean certified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    private List<Equipment> equipmentList;
    @JoinColumn(name = "location", referencedColumnName = "id")
    @ManyToOne
    private Location location;
    @JoinColumn(name = "warehouse_operator", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon warehouseOperator;
    @JoinColumn(name = "warehouse_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Phenomenon warehouseType;
    @JoinColumn(name = "units", referencedColumnName = "id")
    @ManyToOne
    private MeasurementUnit units;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    private List<WarehouseOperation> warehouseOperationList;

    public Warehouse() {
    }

    public Warehouse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getOffersWrs() {
        return offersWrs;
    }

    public void setOffersWrs(Boolean offersWrs) {
        this.offersWrs = offersWrs;
    }

    public Boolean getCertified() {
        return certified;
    }

    public void setCertified(Boolean certified) {
        this.certified = certified;
    }

    @XmlTransient
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Phenomenon getWarehouseOperator() {
        return warehouseOperator;
    }

    public void setWarehouseOperator(Phenomenon warehouseOperator) {
        this.warehouseOperator = warehouseOperator;
    }

    public Phenomenon getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Phenomenon warehouseType) {
        this.warehouseType = warehouseType;
    }

    public MeasurementUnit getUnits() {
        return units;
    }

    public void setUnits(MeasurementUnit units) {
        this.units = units;
    }

    @XmlTransient
    public List<WarehouseOperation> getWarehouseOperationList() {
        return warehouseOperationList;
    }

    public void setWarehouseOperationList(List<WarehouseOperation> warehouseOperationList) {
        this.warehouseOperationList = warehouseOperationList;
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
        if (!(object instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Warehouse[ id=" + id + " ]";
    }

}
