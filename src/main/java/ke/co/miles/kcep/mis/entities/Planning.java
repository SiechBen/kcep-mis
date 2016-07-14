/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "planning", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planning.findAll", query = "SELECT p FROM Planning p"),
    @NamedQuery(name = "Planning.findById", query = "SELECT p FROM Planning p WHERE p.id = :id"),
    @NamedQuery(name = "Planning.findByAnnualWorkplanReferenceCode", query = "SELECT p FROM Planning p WHERE p.annualWorkplanReferenceCode = :annualWorkplanReferenceCode"),
    @NamedQuery(name = "Planning.findByAwpbTarget", query = "SELECT p FROM Planning p WHERE p.awpbTarget = :awpbTarget"),
    @NamedQuery(name = "Planning.findByProgrammeTarget", query = "SELECT p FROM Planning p WHERE p.programmeTarget = :programmeTarget"),
    @NamedQuery(name = "Planning.findByValueAchieved", query = "SELECT p FROM Planning p WHERE p.valueAchieved = :valueAchieved"),
    @NamedQuery(name = "Planning.findByAllocatedBudget", query = "SELECT p FROM Planning p WHERE p.allocatedBudget = :allocatedBudget")})
public class Planning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "annual_workplan_reference_code")
    private String annualWorkplanReferenceCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "awpb_target")
    private BigDecimal awpbTarget;
    @Column(name = "programme_target")
    private BigDecimal programmeTarget;
    @Column(name = "value_achieved")
    private BigDecimal valueAchieved;
    @Column(name = "allocated_budget")
    private BigDecimal allocatedBudget;
    @JoinColumn(name = "activity", referencedColumnName = "id")
    private Activity activity;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Component component;
    @JoinColumn(name = "sub_component", referencedColumnName = "id")
    @ManyToOne
    private SubComponent subComponent;
    @JoinColumn(name = "implementing_partner", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ImplementingPartner implementingPartner;

    public Planning() {
    }

    public Planning(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnnualWorkplanReferenceCode() {
        return annualWorkplanReferenceCode;
    }

    public void setAnnualWorkplanReferenceCode(String annualWorkplanReferenceCode) {
        this.annualWorkplanReferenceCode = annualWorkplanReferenceCode;
    }

    public BigDecimal getAwpbTarget() {
        return awpbTarget;
    }

    public void setAwpbTarget(BigDecimal awpbTarget) {
        this.awpbTarget = awpbTarget;
    }

    public BigDecimal getProgrammeTarget() {
        return programmeTarget;
    }

    public void setProgrammeTarget(BigDecimal programmeTarget) {
        this.programmeTarget = programmeTarget;
    }

    public BigDecimal getValueAchieved() {
        return valueAchieved;
    }

    public void setValueAchieved(BigDecimal valueAchieved) {
        this.valueAchieved = valueAchieved;
    }

    public BigDecimal getAllocatedBudget() {
        return allocatedBudget;
    }

    public void setAllocatedBudget(BigDecimal allocatedBudget) {
        this.allocatedBudget = allocatedBudget;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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

    public ImplementingPartner getImplementingPartner() {
        return implementingPartner;
    }

    public void setImplementingPartner(ImplementingPartner implementingPartner) {
        this.implementingPartner = implementingPartner;
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
        if (!(object instanceof Planning)) {
            return false;
        }
        Planning other = (Planning) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Planning[ id=" + id + " ]";
    }
    
}
