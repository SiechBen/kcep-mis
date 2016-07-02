/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "sub_activity", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubActivity.findAll", query = "SELECT s FROM SubActivity s"),
    @NamedQuery(name = "SubActivity.findById", query = "SELECT s FROM SubActivity s WHERE s.id = :id"),
    @NamedQuery(name = "SubActivity.findByDescription", query = "SELECT s FROM SubActivity s WHERE s.description = :description"),
    @NamedQuery(name = "SubActivity.findByStartDate", query = "SELECT s FROM SubActivity s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "SubActivity.findByEndDate", query = "SELECT s FROM SubActivity s WHERE s.endDate = :endDate"),
    @NamedQuery(name = "SubActivity.findByActualExpenditure", query = "SELECT s FROM SubActivity s WHERE s.actualExpenditure = :actualExpenditure")})
public class SubActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "actual_expenditure")
    private BigDecimal actualExpenditure;
    @JoinColumn(name = "activity", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Activity activity;
    @JoinColumn(name = "measurement_unit", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MeasurementUnit measurementUnit;

    public SubActivity() {
    }

    public SubActivity(Integer id) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getActualExpenditure() {
        return actualExpenditure;
    }

    public void setActualExpenditure(BigDecimal actualExpenditure) {
        this.actualExpenditure = actualExpenditure;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
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
        if (!(object instanceof SubActivity)) {
            return false;
        }
        SubActivity other = (SubActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubActivity[ id=" + id + " ]";
    }
    
}
