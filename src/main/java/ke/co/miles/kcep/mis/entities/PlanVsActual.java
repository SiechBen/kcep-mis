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
@Table(name = "plan_vs_actual", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanVsActual.findAll", query = "SELECT p FROM PlanVsActual p"),
    @NamedQuery(name = "PlanVsActual.findById", query = "SELECT p FROM PlanVsActual p WHERE p.id = :id"),
    @NamedQuery(name = "PlanVsActual.findByChoice", query = "SELECT p FROM PlanVsActual p WHERE p.choice = :choice")})
public class PlanVsActual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 45)
    @Column(name = "choice")
    private String choice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planVsActual")
    private List<ProcurementPlanNcs> procurementPlanNcsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planVsActual")
    private List<ProcurementPlan> procurementPlanList;

    public PlanVsActual() {
    }

    public PlanVsActual(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
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
        if (!(object instanceof PlanVsActual)) {
            return false;
        }
        PlanVsActual other = (PlanVsActual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PlanVsActual[ id=" + id + " ]";
    }
    
}
