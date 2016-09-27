package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "farmer_sub_group", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FarmerSubGroup.findByNameAndFarmerGroupId", query = "SELECT f FROM FarmerSubGroup f WHERE f.farmerGroup.id =:farmerGroupId AND f.name =:name"),
    @NamedQuery(name = "FarmerSubGroup.findAll", query = "SELECT f FROM FarmerSubGroup f"),
    @NamedQuery(name = "FarmerSubGroup.findById", query = "SELECT f FROM FarmerSubGroup f WHERE f.id = :id"),
    @NamedQuery(name = "FarmerSubGroup.findByName", query = "SELECT f FROM FarmerSubGroup f WHERE f.name = :name")})
public class FarmerSubGroup implements Serializable {

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
    @JoinColumn(name = "farmer_group", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FarmerGroup farmerGroup;
    @OneToMany(mappedBy = "farmerSubGroup")
    private List<Person> personList;

    public FarmerSubGroup() {
    }

    public FarmerSubGroup(Integer id) {
        this.id = id;
    }

    public FarmerSubGroup(Integer id, String name) {
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

    public FarmerGroup getFarmerGroup() {
        return farmerGroup;
    }

    public void setFarmerGroup(FarmerGroup farmerGroup) {
        this.farmerGroup = farmerGroup;
    }

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
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
        if (!(object instanceof FarmerSubGroup)) {
            return false;
        }
        FarmerSubGroup other = (FarmerSubGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.FarmerSubGroup[ id=" + id + " ]";
    }

}
