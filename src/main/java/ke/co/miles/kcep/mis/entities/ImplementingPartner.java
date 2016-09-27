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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "implementing_partner", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImplementingPartner.findByPersonRoleId", query = "SELECT i FROM ImplementingPartner i WHERE i.personRole.id = :personRoleId"),
    @NamedQuery(name = "ImplementingPartner.findAll", query = "SELECT i FROM ImplementingPartner i"),
    @NamedQuery(name = "ImplementingPartner.findById", query = "SELECT i FROM ImplementingPartner i WHERE i.id = :id")})
public class ImplementingPartner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @OneToMany(mappedBy = "implementingPartner")
    private List<SubActivity> subActivityList;
    @JoinColumn(name = "person_role", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonRole personRole;

    public ImplementingPartner() {
    }

    public ImplementingPartner(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList() {
        return subActivityList;
    }

    public void setSubActivityList(List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
    }

    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
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
        if (!(object instanceof ImplementingPartner)) {
            return false;
        }
        ImplementingPartner other = (ImplementingPartner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ImplementingPartner[ id=" + id + " ]";
    }

}
