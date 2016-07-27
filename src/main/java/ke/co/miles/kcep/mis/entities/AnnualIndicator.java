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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "annual_indicator", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnnualIndicator.findBySubActivityId", query = "SELECT a FROM AnnualIndicator a WHERE a.subActivity.id = :subActivityId"),
    @NamedQuery(name = "AnnualIndicator.findAll", query = "SELECT a FROM AnnualIndicator a"),
    @NamedQuery(name = "AnnualIndicator.findById", query = "SELECT a FROM AnnualIndicator a WHERE a.id = :id")})
public class AnnualIndicator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "performance_indicator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PerformanceIndicator performanceIndicator;
    @JoinColumn(name = "sub_activity", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubActivity subActivity;

    public AnnualIndicator() {
    }

    public AnnualIndicator(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PerformanceIndicator getPerformanceIndicator() {
        return performanceIndicator;
    }

    public void setPerformanceIndicator(PerformanceIndicator performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
    }

    public SubActivity getSubActivity() {
        return subActivity;
    }

    public void setSubActivity(SubActivity subActivity) {
        this.subActivity = subActivity;
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
        if (!(object instanceof AnnualIndicator)) {
            return false;
        }
        AnnualIndicator other = (AnnualIndicator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.AnnualIndicator[ id=" + id + " ]";
    }
    
}
