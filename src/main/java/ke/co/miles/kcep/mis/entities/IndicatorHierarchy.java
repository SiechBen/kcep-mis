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
@Table(name = "indicator_hierarchy", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndicatorHierarchy.findAll", query = "SELECT i FROM IndicatorHierarchy i"),
    @NamedQuery(name = "IndicatorHierarchy.findById", query = "SELECT i FROM IndicatorHierarchy i WHERE i.id = :id"),
    @NamedQuery(name = "IndicatorHierarchy.findByDescription", query = "SELECT i FROM IndicatorHierarchy i WHERE i.description = :description")})
public class IndicatorHierarchy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicatorHierarchy")
    private List<Indicator> indicatorList;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Component component;
    @JoinColumn(name = "sub_component", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubComponent subComponent;

    public IndicatorHierarchy() {
    }

    public IndicatorHierarchy(Integer id) {
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
    public List<Indicator> getIndicatorList() {
        return indicatorList;
    }

    public void setIndicatorList(List<Indicator> indicatorList) {
        this.indicatorList = indicatorList;
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
        if (!(object instanceof IndicatorHierarchy)) {
            return false;
        }
        IndicatorHierarchy other = (IndicatorHierarchy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.IndicatorHierarchy[ id=" + id + " ]";
    }
    
}
