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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "number_description", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NumberDescription.findAll", query = "SELECT n FROM NumberDescription n"),
    @NamedQuery(name = "NumberDescription.findById", query = "SELECT n FROM NumberDescription n WHERE n.id = :id"),
    @NamedQuery(name = "NumberDescription.findByDescription", query = "SELECT n FROM NumberDescription n WHERE n.description = :description")})
public class NumberDescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numberDescription")
    private List<NumberOfFarmers> numberOfFarmersList;

    public NumberDescription() {
    }

    public NumberDescription(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<NumberOfFarmers> getNumberOfFarmersList() {
        return numberOfFarmersList;
    }

    public void setNumberOfFarmersList(List<NumberOfFarmers> numberOfFarmersList) {
        this.numberOfFarmersList = numberOfFarmersList;
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
        if (!(object instanceof NumberDescription)) {
            return false;
        }
        NumberDescription other = (NumberDescription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.NumberDescription[ id=" + id + " ]";
    }

}
