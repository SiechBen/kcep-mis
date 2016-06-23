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
@Table(name = "age_bracket", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgeBracket.findAll", query = "SELECT a FROM AgeBracket a"),
    @NamedQuery(name = "AgeBracket.findById", query = "SELECT a FROM AgeBracket a WHERE a.id = :id"),
    @NamedQuery(name = "AgeBracket.findByBracket", query = "SELECT a FROM AgeBracket a WHERE a.bracket = :bracket")})
public class AgeBracket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "bracket")
    private String bracket;
    @OneToMany(mappedBy = "ageBracket")
    private List<NumberOfFarmers> numberOfFarmersList;

    public AgeBracket() {
    }

    public AgeBracket(Short id) {
        this.id = id;
    }

    public AgeBracket(Short id, String bracket) {
        this.id = id;
        this.bracket = bracket;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getBracket() {
        return bracket;
    }

    public void setBracket(String bracket) {
        this.bracket = bracket;
    }

    @XmlTransient
    public List<NumberOfFarmers> getNumberOfFarmersList() {
        return numberOfFarmersList;
    }

    public void setNumberOfFarmersList(List<NumberOfFarmers> numberOfFarmersList) {
        this.numberOfFarmersList = numberOfFarmersList;
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
        if (!(object instanceof AgeBracket)) {
            return false;
        }
        AgeBracket other = (AgeBracket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.AgeBracket[ id=" + id + " ]";
    }
    
}
