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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "static_input", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StaticInput.findByInputTypeId", query = "SELECT s FROM StaticInput s WHERE s.inputType.id = :inputTypeId"),
    @NamedQuery(name = "StaticInput.findAll", query = "SELECT s FROM StaticInput s"),
    @NamedQuery(name = "StaticInput.findById", query = "SELECT s FROM StaticInput s WHERE s.id = :id"),
    @NamedQuery(name = "StaticInput.findByName", query = "SELECT s FROM StaticInput s WHERE s.name = :name")})
public class StaticInput implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staticInput")
    private List<InputVariety> inputVarietyList;
    @OneToMany(mappedBy = "staticInput")
    private List<InputsCollection> inputsCollectionList;
    @OneToMany(mappedBy = "produceTypeBrought")
    private List<WarehouseOperation> warehouseOperationList;
    @OneToMany(mappedBy = "produceTypeSold")
    private List<WarehouseOperation> warehouseOperationList1;
    @JoinColumn(name = "input_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InputType inputType;

    public StaticInput() {
    }

    public StaticInput(Integer id) {
        this.id = id;
    }

    public StaticInput(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @XmlTransient
    public List<InputVariety> getInputVarietyList() {
        return inputVarietyList;
    }

    public void setInputVarietyList(List<InputVariety> inputVarietyList) {
        this.inputVarietyList = inputVarietyList;
    }

    @XmlTransient
    public List<InputsCollection> getInputsCollectionList() {
        return inputsCollectionList;
    }

    public void setInputsCollectionList(List<InputsCollection> inputsCollectionList) {
        this.inputsCollectionList = inputsCollectionList;
    }

    @XmlTransient
    public List<WarehouseOperation> getWarehouseOperationList() {
        return warehouseOperationList;
    }

    public void setWarehouseOperationList(List<WarehouseOperation> warehouseOperationList) {
        this.warehouseOperationList = warehouseOperationList;
    }

    @XmlTransient
    public List<WarehouseOperation> getWarehouseOperationList1() {
        return warehouseOperationList1;
    }

    public void setWarehouseOperationList1(List<WarehouseOperation> warehouseOperationList1) {
        this.warehouseOperationList1 = warehouseOperationList1;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
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
        if (!(object instanceof StaticInput)) {
            return false;
        }
        StaticInput other = (StaticInput) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.StaticInput[ id=" + id + " ]";
    }

}
