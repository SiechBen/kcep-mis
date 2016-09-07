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
@Table(name = "procurement_method", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcurementMethod.findAll", query = "SELECT p FROM ProcurementMethod p"),
    @NamedQuery(name = "ProcurementMethod.findById", query = "SELECT p FROM ProcurementMethod p WHERE p.id = :id"),
    @NamedQuery(name = "ProcurementMethod.findByMethod", query = "SELECT p FROM ProcurementMethod p WHERE p.method = :method")})
public class ProcurementMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Size(max = 45)
    @Column(name = "method")
    private String method;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procurementMethod")
    private List<ProcurementPlanCs> procurementPlanCsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procurementMethod")
    private List<ProcurementPlan> procurementPlanList;

    public ProcurementMethod() {
    }

    public ProcurementMethod(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @XmlTransient
    public List<ProcurementPlanCs> getProcurementPlanCsList() {
        return procurementPlanCsList;
    }

    public void setProcurementPlanCsList(List<ProcurementPlanCs> procurementPlanCsList) {
        this.procurementPlanCsList = procurementPlanCsList;
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
        if (!(object instanceof ProcurementMethod)) {
            return false;
        }
        ProcurementMethod other = (ProcurementMethod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ProcurementMethod[ id=" + id + " ]";
    }
    
}
