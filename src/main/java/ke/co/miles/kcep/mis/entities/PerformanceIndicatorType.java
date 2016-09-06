/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "performance_indicator_type", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerformanceIndicatorType.findAll", query = "SELECT p FROM PerformanceIndicatorType p"),
    @NamedQuery(name = "PerformanceIndicatorType.findById", query = "SELECT p FROM PerformanceIndicatorType p WHERE p.id = :id"),
    @NamedQuery(name = "PerformanceIndicatorType.findByType", query = "SELECT p FROM PerformanceIndicatorType p WHERE p.type = :type")})
public class PerformanceIndicatorType implements Serializable {

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
    @OneToMany(mappedBy = "performanceIndicatorType")
    private List<PerformanceIndicator> performanceIndicatorList;

    public PerformanceIndicatorType() {
    }

    public PerformanceIndicatorType(Short id) {
        this.id = id;
    }

    public PerformanceIndicatorType(Short id, String type) {
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
    public List<PerformanceIndicator> getPerformanceIndicatorList() {
        return performanceIndicatorList;
    }

    public void setPerformanceIndicatorList(List<PerformanceIndicator> performanceIndicatorList) {
        this.performanceIndicatorList = performanceIndicatorList;
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
        if (!(object instanceof PerformanceIndicatorType)) {
            return false;
        }
        PerformanceIndicatorType other = (PerformanceIndicatorType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PerformanceIndicatorType[ id=" + id + " ]";
    }
    
}
