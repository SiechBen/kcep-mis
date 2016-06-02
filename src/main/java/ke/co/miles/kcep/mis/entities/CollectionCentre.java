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
@Table(name = "collection_centre", catalog = "kcep_mis", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CollectionCentre.findAll", query = "SELECT c FROM CollectionCentre c"),
    @NamedQuery(name = "CollectionCentre.findById", query = "SELECT c FROM CollectionCentre c WHERE c.id = :id"),
    @NamedQuery(name = "CollectionCentre.findByOperational", query = "SELECT c FROM CollectionCentre c WHERE c.operational = :operational")})
public class CollectionCentre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "operational")
    private Boolean operational;
    @JoinColumn(name = "location", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Location location;
    @JoinColumn(name = "ward_extension_officer", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person wardExtensionOfficer;

    public CollectionCentre() {
    }

    public CollectionCentre(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getOperational() {
        return operational;
    }

    public void setOperational(Boolean operational) {
        this.operational = operational;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Person getWardExtensionOfficer() {
        return wardExtensionOfficer;
    }

    public void setWardExtensionOfficer(Person wardExtensionOfficer) {
        this.wardExtensionOfficer = wardExtensionOfficer;
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
        if (!(object instanceof CollectionCentre)) {
            return false;
        }
        CollectionCentre other = (CollectionCentre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.CollectionCentre[ id=" + id + " ]";
    }
    
}
