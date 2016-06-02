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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "number_of_farmers", catalog = "kcep_mis", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NumberOfFarmers.findAll", query = "SELECT n FROM NumberOfFarmers n"),
    @NamedQuery(name = "NumberOfFarmers.findById", query = "SELECT n FROM NumberOfFarmers n WHERE n.id = :id"),
    @NamedQuery(name = "NumberOfFarmers.findByNumber", query = "SELECT n FROM NumberOfFarmers n WHERE n.number = :number")})
public class NumberOfFarmers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "number")
    private Integer number;
    @JoinColumn(name = "sampled_farmer_data", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private SampledFarmerData sampledFarmerData;
    @JoinColumn(name = "age_bracket", referencedColumnName = "id")
    @ManyToOne
    private AgeBracket ageBracket;
    @JoinColumn(name = "number_description", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private NumberDescription numberDescription;
    @JoinColumn(name = "sex", referencedColumnName = "id")
    @ManyToOne
    private Sex sex;

    public NumberOfFarmers() {
    }

    public NumberOfFarmers(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public SampledFarmerData getSampledFarmerData() {
        return sampledFarmerData;
    }

    public void setSampledFarmerData(SampledFarmerData sampledFarmerData) {
        this.sampledFarmerData = sampledFarmerData;
    }

    public AgeBracket getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(AgeBracket ageBracket) {
        this.ageBracket = ageBracket;
    }

    public NumberDescription getNumberDescription() {
        return numberDescription;
    }

    public void setNumberDescription(NumberDescription numberDescription) {
        this.numberDescription = numberDescription;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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
        if (!(object instanceof NumberOfFarmers)) {
            return false;
        }
        NumberOfFarmers other = (NumberOfFarmers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.NumberOfFarmers[ id=" + id + " ]";
    }
    
}
