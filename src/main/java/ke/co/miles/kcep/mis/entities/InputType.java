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
@Table(name = "input_type", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InputType.findAll", query = "SELECT i FROM InputType i"),
    @NamedQuery(name = "InputType.findById", query = "SELECT i FROM InputType i WHERE i.id = :id"),
    @NamedQuery(name = "InputType.findByType", query = "SELECT i FROM InputType i WHERE i.type = :type")})
public class InputType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "inputType")
    private List<InputsCollection> inputsCollectionList;
    @OneToMany(mappedBy = "inputType")
    private List<EVoucher> eVoucherList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inputType")
    private List<StaticInput> staticInputList;

    public InputType() {
    }

    public InputType(Short id) {
        this.id = id;
    }

    public InputType(Short id, String type) {
        this.id = id;
        this.type = type;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<InputsCollection> getInputsCollectionList() {
        return inputsCollectionList;
    }

    public void setInputsCollectionList(List<InputsCollection> inputsCollectionList) {
        this.inputsCollectionList = inputsCollectionList;
    }

    @XmlTransient
    public List<EVoucher> getEVoucherList() {
        return eVoucherList;
    }

    public void setEVoucherList(List<EVoucher> eVoucherList) {
        this.eVoucherList = eVoucherList;
    }

    @XmlTransient
    public List<StaticInput> getStaticInputList() {
        return staticInputList;
    }

    public void setStaticInputList(List<StaticInput> staticInputList) {
        this.staticInputList = staticInputList;
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
        if (!(object instanceof InputType)) {
            return false;
        }
        InputType other = (InputType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.InputType[ id=" + id + " ]";
    }

}
