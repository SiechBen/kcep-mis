package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author siech
 */
public class WarehouseOperationDetails implements Serializable,
        Comparable<WarehouseOperationDetails> {

    public WarehouseOperationDetails() {
    }

    public WarehouseOperationDetails(Integer id) {
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

    public StaticInputDetails getProduceTypeBrought() {
        return produceTypeBrought;
    }

    public void setProduceTypeBrought(StaticInputDetails produceTypeBrought) {
        this.produceTypeBrought = produceTypeBrought;
    }

    public StaticInputDetails getProduceTypeSold() {
        return produceTypeSold;
    }

    public void setProduceTypeSold(StaticInputDetails produceTypeSold) {
        this.produceTypeSold = produceTypeSold;
    }

    public WarehouseDetails getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseDetails warehouse) {
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
        if (!(object instanceof WarehouseOperationDetails)) {
            return false;
        }
        WarehouseOperationDetails other = (WarehouseOperationDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null
                && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.WarehouseOperationDetails[ id="
                + id + " ]";
    }

    @Override
    public int compareTo(WarehouseOperationDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String buyer;
    private Date sellingDate;
    private Double quantitySold;
    private Double quantityBrought;
    private BigDecimal sellingPrice;
    private WarehouseDetails warehouse;
    private StaticInputDetails produceTypeSold;
    private StaticInputDetails produceTypeBrought;

}
