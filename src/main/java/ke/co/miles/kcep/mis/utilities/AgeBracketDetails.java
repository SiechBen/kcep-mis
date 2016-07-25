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
public class AgeBracketDetails implements Serializable, Comparable<AgeBracketDetails> {

    private static final long serialVersionUID = 1L;

    public AgeBracketDetails() {
    }

    public AgeBracketDetails(Short id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AgeBracketDetails)) {
            return false;
        }
        AgeBracketDetails other = (AgeBracketDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.AgeBracket[ bracket=" + bracket + " ]";
    }

    @Override
    public int compareTo(AgeBracketDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Short id;
    private String bracket;

}
