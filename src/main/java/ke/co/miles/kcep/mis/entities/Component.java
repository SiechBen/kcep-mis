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
@Table(name = "component", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c"),
    @NamedQuery(name = "Component.findById", query = "SELECT c FROM Component c WHERE c.id = :id"),
    @NamedQuery(name = "Component.findByComponent", query = "SELECT c FROM Component c WHERE c.component = :component")})
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "component")
    private String component;
    @OneToMany(mappedBy = "component")
    private List<SubActivity> subActivityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "component")
    private List<SubComponent> subComponentList;
    @OneToMany(mappedBy = "component")
    private List<ResultHierarchy> resultHierarchyList;

    public Component() {
    }

    public Component(Short id) {
        this.id = id;
    }

    public Component(Short id, String component) {
        this.id = id;
        this.component = component;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList() {
        return subActivityList;
    }

    public void setSubActivityList(List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
    }

    @XmlTransient
    public List<SubComponent> getSubComponentList() {
        return subComponentList;
    }

    public void setSubComponentList(List<SubComponent> subComponentList) {
        this.subComponentList = subComponentList;
    }

    @XmlTransient
    public List<ResultHierarchy> getResultHierarchyList() {
        return resultHierarchyList;
    }

    public void setResultHierarchyList(List<ResultHierarchy> resultHierarchyList) {
        this.resultHierarchyList = resultHierarchyList;
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
        if (!(object instanceof Component)) {
            return false;
        }
        Component other = (Component) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Component[ id=" + id + " ]";
    }

}
