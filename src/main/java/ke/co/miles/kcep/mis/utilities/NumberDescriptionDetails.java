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
public class NumberDescriptionDetails implements Serializable, Comparable<NumberDescriptionDetails> {

    private static final long serialVersionUID = 1L;

    public NumberDescriptionDetails() {
    }

    public NumberDescriptionDetails(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NumberDescriptionDetails)) {
            return false;
        }
        NumberDescriptionDetails other = (NumberDescriptionDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.NumberDescription[ description=" + description + " ]";
    }

    @Override
    public int compareTo(NumberDescriptionDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String description;

}
