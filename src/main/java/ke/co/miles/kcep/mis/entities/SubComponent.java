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
@Table(name = "sub_component", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubComponent.findByComponentId", query = "SELECT s FROM SubComponent s WHERE s.component.id = :componentId"),
    @NamedQuery(name = "SubComponent.findBySubComponentAndComponentId", query = "SELECT s FROM SubComponent s WHERE s.subComponent = :subComponent AND s.component.id = :componentId"),
    @NamedQuery(name = "SubComponent.findAll", query = "SELECT s FROM SubComponent s"),
    @NamedQuery(name = "SubComponent.findById", query = "SELECT s FROM SubComponent s WHERE s.id = :id"),
    @NamedQuery(name = "SubComponent.findBySubComponent", query = "SELECT s FROM SubComponent s WHERE s.subComponent = :subComponent")})
public class SubComponent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "sub_component")
    private String subComponent;
    @OneToMany(mappedBy = "subComponent")
    private List<SubActivity> subActivityList;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Component component;
    @OneToMany(mappedBy = "subComponent")
    private List<ResultHierarchy> resultHierarchyList;

    public SubComponent() {
    }

    public SubComponent(Short id) {
        this.id = id;
    }

    public SubComponent(Short id, String subComponent) {
        this.id = id;
        this.subComponent = subComponent;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getSubComponent() {
        return subComponent;
    }

    public void setSubComponent(String subComponent) {
        this.subComponent = subComponent;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList() {
        return subActivityList;
    }

    public void setSubActivityList(List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
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
        if (!(object instanceof SubComponent)) {
            return false;
        }
        SubComponent other = (SubComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubComponent[ id=" + id + " ]";
    }
}
