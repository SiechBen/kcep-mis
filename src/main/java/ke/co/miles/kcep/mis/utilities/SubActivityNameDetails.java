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
public class SubActivityNameDetails implements Serializable, Comparable<SubActivityNameDetails> {

    public SubActivityNameDetails() {
    }

    public SubActivityNameDetails(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityNameDetails getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityNameDetails activity) {
        this.activityName = activity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SubActivityNameDetails)) {
            return false;
        }
        SubActivityNameDetails other = (SubActivityNameDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.SubActivityName[ name=" + name + " ]";
    }

    @Override
    public int compareTo(SubActivityNameDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Short id;
    private String name;
    private ActivityNameDetails activityName;

}
