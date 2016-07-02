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
@Table(name = "procurement_plan_type", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcurementPlanType.findAll", query = "SELECT p FROM ProcurementPlanType p"),
    @NamedQuery(name = "ProcurementPlanType.findById", query = "SELECT p FROM ProcurementPlanType p WHERE p.id = :id"),
    @NamedQuery(name = "ProcurementPlanType.findByType", query = "SELECT p FROM ProcurementPlanType p WHERE p.type = :type")})
public class ProcurementPlanType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procurementPlanType")
    private List<ProcurementPlanNcs> procurementPlanNcsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procurementPlanType")
    private List<ProcurementPlan> procurementPlanList;

    public ProcurementPlanType() {
    }

    public ProcurementPlanType(Short id) {
        this.id = id;
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
    public List<ProcurementPlanNcs> getProcurementPlanNcsList() {
        return procurementPlanNcsList;
    }

    public void setProcurementPlanNcsList(List<ProcurementPlanNcs> procurementPlanNcsList) {
        this.procurementPlanNcsList = procurementPlanNcsList;
    }

    @XmlTransient
    public List<ProcurementPlan> getProcurementPlanList() {
        return procurementPlanList;
    }

    public void setProcurementPlanList(List<ProcurementPlan> procurementPlanList) {
        this.procurementPlanList = procurementPlanList;
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
        if (!(object instanceof ProcurementPlanType)) {
            return false;
        }
        ProcurementPlanType other = (ProcurementPlanType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ProcurementPlanType[ id=" + id + " ]";
    }
    
}
