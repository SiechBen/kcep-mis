/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;

/**
 *
 * @author siech
 */
public class ExtensionMaterialAndGuidelineDetails implements Serializable, Comparable<ExtensionMaterialAndGuidelineDetails> {

    private static final long serialVersionUID = 1L;

    public ExtensionMaterialAndGuidelineDetails() {
    }

    public ExtensionMaterialAndGuidelineDetails(Integer id) {
        this.id = id;
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

    public PersonDetails getKalroOfficer() {
        return kalroOfficer;
    }

    public void setKalroOfficer(PersonDetails kALROofficer) {
        this.kalroOfficer = kALROofficer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExtensionMaterialAndGuidelineDetails)) {
            return false;
        }
        ExtensionMaterialAndGuidelineDetails other = (ExtensionMaterialAndGuidelineDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ExtensionMaterialAndGuideline[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ExtensionMaterialAndGuidelineDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String name;
    private PersonDetails kalroOfficer;

}
