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
public class SubActivityDescriptionDetails implements Serializable, Comparable<SubActivityDescriptionDetails> {

    public SubActivityDescriptionDetails() {
    }

    public SubActivityDescriptionDetails(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityDetails getActivity() {
        return activity;
    }

    public void setActivity(ActivityDetails activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SubActivityDescriptionDetails)) {
            return false;
        }
        SubActivityDescriptionDetails other = (SubActivityDescriptionDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubActivityDescription[ id=" + id + " ]";
    }

    @Override
    public int compareTo(SubActivityDescriptionDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Short id;
    private String description;
    private ActivityDetails activity;

}
