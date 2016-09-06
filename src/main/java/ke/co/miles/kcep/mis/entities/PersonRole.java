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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "person_role", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonRole.findAll", query = "SELECT p FROM PersonRole p"),
    @NamedQuery(name = "PersonRole.findById", query = "SELECT p FROM PersonRole p WHERE p.id = :id"),
    @NamedQuery(name = "PersonRole.findByPersonRole", query = "SELECT p FROM PersonRole p WHERE p.personRole = :personRole")})
public class PersonRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "person_role")
    private String personRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personRole")
    private List<ImplementingPartner> implementingPartnerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personRole")
    private List<UserAccount> userAccountList;

    public PersonRole() {
    }

    public PersonRole(Short id) {
        this.id = id;
    }

    public PersonRole(Short id, String personRole) {
        this.id = id;
        this.personRole = personRole;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

    @XmlTransient
    public List<ImplementingPartner> getImplementingPartnerList() {
        return implementingPartnerList;
    }

    public void setImplementingPartnerList(List<ImplementingPartner> implementingPartnerList) {
        this.implementingPartnerList = implementingPartnerList;
    }

    @XmlTransient
    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
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
        if (!(object instanceof PersonRole)) {
            return false;
        }
        PersonRole other = (PersonRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PersonRole[ id=" + id + " ]";
    }
    
}
