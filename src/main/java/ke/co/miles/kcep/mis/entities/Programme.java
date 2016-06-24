/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "programme", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programme.findAll", query = "SELECT p FROM Programme p"),
    @NamedQuery(name = "Programme.findById", query = "SELECT p FROM Programme p WHERE p.id = :id"),
    @NamedQuery(name = "Programme.findByStartPeriod", query = "SELECT p FROM Programme p WHERE p.startPeriod = :startPeriod"),
    @NamedQuery(name = "Programme.findByEndPeriod", query = "SELECT p FROM Programme p WHERE p.endPeriod = :endPeriod"),
    @NamedQuery(name = "Programme.findByAwpbTarget", query = "SELECT p FROM Programme p WHERE p.awpbTarget = :awpbTarget"),
    @NamedQuery(name = "Programme.findByProgrammeTarget", query = "SELECT p FROM Programme p WHERE p.programmeTarget = :programmeTarget"),
    @NamedQuery(name = "Programme.findByValueAchieved", query = "SELECT p FROM Programme p WHERE p.valueAchieved = :valueAchieved"),
    @NamedQuery(name = "Programme.findByRequestedBudget", query = "SELECT p FROM Programme p WHERE p.requestedBudget = :requestedBudget"),
    @NamedQuery(name = "Programme.findByActualExpenditure", query = "SELECT p FROM Programme p WHERE p.actualExpenditure = :actualExpenditure")})
public class Programme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "activity")
    private String activity;
    @Size(max = 45)
    @Column(name = "start_period")
    private String startPeriod;
    @Size(max = 45)
    @Column(name = "end_period")
    private String endPeriod;
    @Size(max = 45)
    @Column(name = "awpb_target")
    private String awpbTarget;
    @Size(max = 45)
    @Column(name = "programme_target")
    private String programmeTarget;
    @Size(max = 45)
    @Column(name = "value_achieved")
    private String valueAchieved;
    @Size(max = 45)
    @Column(name = "requested_budget")
    private String requestedBudget;
    @Size(max = 45)
    @Column(name = "actual_expenditure")
    private String actualExpenditure;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Component component;
    @JoinColumn(name = "measurement_unit", referencedColumnName = "id")
    @ManyToOne
    private MeasurementUnit measurementUnit;
    @JoinColumn(name = "sub_component", referencedColumnName = "id")
    @ManyToOne
    private SubComponent subComponent;
    @JoinColumn(name = "implementing_partner", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ImplementingPartner implementingPartner;

    public Programme() {
    }

    public Programme(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getAwpbTarget() {
        return awpbTarget;
    }

    public void setAwpbTarget(String awpbTarget) {
        this.awpbTarget = awpbTarget;
    }

    public String getProgrammeTarget() {
        return programmeTarget;
    }

    public void setProgrammeTarget(String programmeTarget) {
        this.programmeTarget = programmeTarget;
    }

    public String getValueAchieved() {
        return valueAchieved;
    }

    public void setValueAchieved(String valueAchieved) {
        this.valueAchieved = valueAchieved;
    }

    public String getRequestedBudget() {
        return requestedBudget;
    }

    public void setRequestedBudget(String requestedBudget) {
        this.requestedBudget = requestedBudget;
    }

    public String getActualExpenditure() {
        return actualExpenditure;
    }

    public void setActualExpenditure(String actualExpenditure) {
        this.actualExpenditure = actualExpenditure;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
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
        if (!(object instanceof Programme)) {
            return false;
        }
        Programme other = (Programme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Programme[ id=" + id + " ]";
    }
    
}
