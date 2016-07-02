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
@Table(name = "farmer_group", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FarmerGroup.findAll", query = "SELECT f FROM FarmerGroup f"),
    @NamedQuery(name = "FarmerGroup.findById", query = "SELECT f FROM FarmerGroup f WHERE f.id = :id"),
    @NamedQuery(name = "FarmerGroup.findByName", query = "SELECT f FROM FarmerGroup f WHERE f.name = :name")})
public class FarmerGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmerGroup")
    private List<FarmerSubGroup> farmerSubGroupList;
    @OneToMany(mappedBy = "farmerGroup")
    private List<Person> personList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmerGroup")
    private List<FarmerGroupRecord> farmerGroupRecordList;

    public FarmerGroup() {
    }

    public FarmerGroup(Integer id) {
        this.id = id;
    }

    public FarmerGroup(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<FarmerSubGroup> getFarmerSubGroupList() {
        return farmerSubGroupList;
    }

    public void setFarmerSubGroupList(List<FarmerSubGroup> farmerSubGroupList) {
        this.farmerSubGroupList = farmerSubGroupList;
    }

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @XmlTransient
    public List<FarmerGroupRecord> getFarmerGroupRecordList() {
        return farmerGroupRecordList;
    }

    public void setFarmerGroupRecordList(List<FarmerGroupRecord> farmerGroupRecordList) {
        this.farmerGroupRecordList = farmerGroupRecordList;
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
        if (!(object instanceof FarmerGroup)) {
            return false;
        }
        FarmerGroup other = (FarmerGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.FarmerGroup[ id=" + id + " ]";
    }
    
}
