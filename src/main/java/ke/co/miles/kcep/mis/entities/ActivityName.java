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
@Table(name = "activity_name", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityName.findAll", query = "SELECT a FROM ActivityName a"),
    @NamedQuery(name = "ActivityName.findById", query = "SELECT a FROM ActivityName a WHERE a.id = :id"),
    @NamedQuery(name = "ActivityName.findByName", query = "SELECT a FROM ActivityName a WHERE a.name = :name")})
public class ActivityName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "parent")
    private List<ActivityName> activityNameList;
    @JoinColumn(name = "parent", referencedColumnName = "id")
    @ManyToOne
    private ActivityName parent;
    @OneToMany(mappedBy = "activityName")
    private List<SubActivity> subActivityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityName")
    private List<SubActivityName> subActivityNameList;

    public ActivityName() {
    }

    public ActivityName(Short id) {
        this.id = id;
    }

    public ActivityName(Short id, String name) {
        this.id = id;
        this.name = name;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<ActivityName> getActivityNameList() {
        return activityNameList;
    }

    public void setActivityNameList(List<ActivityName> activityNameList) {
        this.activityNameList = activityNameList;
    }

    public ActivityName getParent() {
        return parent;
    }

    public void setParent(ActivityName parent) {
        this.parent = parent;
    }

    @XmlTransient
    public List<SubActivity> getSubActivityList() {
        return subActivityList;
    }

    public void setSubActivityList(List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
    }

    @XmlTransient
    public List<SubActivityName> getSubActivityNameList() {
        return subActivityNameList;
    }

    public void setSubActivityNameList(List<SubActivityName> subActivityNameList) {
        this.subActivityNameList = subActivityNameList;
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
        if (!(object instanceof ActivityName)) {
            return false;
        }
        ActivityName other = (ActivityName) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ActivityName[ id=" + id + " ]";
    }

}
