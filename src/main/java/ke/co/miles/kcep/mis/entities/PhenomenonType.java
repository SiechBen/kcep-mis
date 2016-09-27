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
@Table(name = "phenomenon_type", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhenomenonType.findAll", query = "SELECT p FROM PhenomenonType p"),
    @NamedQuery(name = "PhenomenonType.findById", query = "SELECT p FROM PhenomenonType p WHERE p.id = :id"),
    @NamedQuery(name = "PhenomenonType.findByName", query = "SELECT p FROM PhenomenonType p WHERE p.name = :name")})
public class PhenomenonType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phenomenonType")
    private List<Phenomenon> phenomenonList;

    public PhenomenonType() {
    }

    public PhenomenonType(Integer id) {
        this.id = id;
    }

    public PhenomenonType(Integer id, String name) {
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
    public List<Phenomenon> getPhenomenonList() {
        return phenomenonList;
    }

    public void setPhenomenonList(List<Phenomenon> phenomenonList) {
        this.phenomenonList = phenomenonList;
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
        if (!(object instanceof PhenomenonType)) {
            return false;
        }
        PhenomenonType other = (PhenomenonType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PhenomenonType[ id=" + id + " ]";
    }

}
