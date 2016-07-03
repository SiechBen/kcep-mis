/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "result_hierarchy", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultHierarchy.findAll", query = "SELECT r FROM ResultHierarchy r"),
    @NamedQuery(name = "ResultHierarchy.findById", query = "SELECT r FROM ResultHierarchy r WHERE r.id = :id"),
    @NamedQuery(name = "ResultHierarchy.findByDescription", query = "SELECT r FROM ResultHierarchy r WHERE r.description = :description")})
public class ResultHierarchy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultHierarchy")
    private List<PerformanceIndicator> performanceIndicatorList;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne
    private Component component;
    @JoinColumn(name = "sub_component", referencedColumnName = "id")
    @ManyToOne
    private SubComponent subComponent;

    public ResultHierarchy() {
    }

    public ResultHierarchy(Integer id) {
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
    public List<PerformanceIndicator> getPerformanceIndicatorList() {
        return performanceIndicatorList;
    }

    public void setPerformanceIndicatorList(List<PerformanceIndicator> performanceIndicatorList) {
        this.performanceIndicatorList = performanceIndicatorList;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public SubComponent getSubComponent() {
        return subComponent;
    }

    public void setSubComponent(SubComponent subComponent) {
        this.subComponent = subComponent;
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
        if (!(object instanceof ResultHierarchy)) {
            return false;
        }
        ResultHierarchy other = (ResultHierarchy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ResultHierarchy[ id=" + id + " ]";
    }
    
}
