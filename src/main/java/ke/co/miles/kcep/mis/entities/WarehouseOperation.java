package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "warehouse_operation", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WarehouseOperation.findByWarehouseId", query = "SELECT w FROM WarehouseOperation w WHERE w.warehouse.id = :warehouseId"),
    @NamedQuery(name = "WarehouseOperation.findAll", query = "SELECT w FROM WarehouseOperation w"),
    @NamedQuery(name = "WarehouseOperation.findById", query = "SELECT w FROM WarehouseOperation w WHERE w.id = :id"),
    @NamedQuery(name = "WarehouseOperation.findByQuantityBrought", query = "SELECT w FROM WarehouseOperation w WHERE w.quantityBrought = :quantityBrought"),
    @NamedQuery(name = "WarehouseOperation.findBySellingDate", query = "SELECT w FROM WarehouseOperation w WHERE w.sellingDate = :sellingDate"),
    @NamedQuery(name = "WarehouseOperation.findByQuantitySold", query = "SELECT w FROM WarehouseOperation w WHERE w.quantitySold = :quantitySold"),
    @NamedQuery(name = "WarehouseOperation.findBySellingPrice", query = "SELECT w FROM WarehouseOperation w WHERE w.sellingPrice = :sellingPrice"),
    @NamedQuery(name = "WarehouseOperation.findByBuyer", query = "SELECT w FROM WarehouseOperation w WHERE w.buyer = :buyer")})
public class WarehouseOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity_brought")
    private Double quantityBrought;
    @Column(name = "selling_date")
    @Temporal(TemporalType.DATE)
    private Date sellingDate;
    @Column(name = "quantity_sold")
    private Double quantitySold;
    @Column(name = "selling_price")
    private BigDecimal sellingPrice;
    @Size(max = 120)
    @Column(name = "buyer")
    private String buyer;
    @JoinColumn(name = "produce_type_brought", referencedColumnName = "id")
    @ManyToOne
    private StaticInput produceTypeBrought;
    @JoinColumn(name = "produce_type_sold", referencedColumnName = "id")
    @ManyToOne
    private StaticInput produceTypeSold;
    @JoinColumn(name = "warehouse", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Warehouse warehouse;

    public WarehouseOperation() {
    }

    public WarehouseOperation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantityBrought() {
        return quantityBrought;
    }

    public void setQuantityBrought(Double quantityBrought) {
        this.quantityBrought = quantityBrought;
    }

    public Date getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(Date sellingDate) {
        this.sellingDate = sellingDate;
    }

    public Double getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Double quantitySold) {
        this.quantitySold = quantitySold;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public StaticInput getProduceTypeBrought() {
        return produceTypeBrought;
    }

    public void setProduceTypeBrought(StaticInput produceTypeBrought) {
        this.produceTypeBrought = produceTypeBrought;
    }

    public StaticInput getProduceTypeSold() {
        return produceTypeSold;
    }

    public void setProduceTypeSold(StaticInput produceTypeSold) {
        this.produceTypeSold = produceTypeSold;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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
        if (!(object instanceof WarehouseOperation)) {
            return false;
        }
        WarehouseOperation other = (WarehouseOperation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.WarehouseOperation[ id=" + id + " ]";
    }

}
