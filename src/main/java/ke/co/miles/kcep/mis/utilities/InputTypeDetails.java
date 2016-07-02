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
public class InputTypeDetails implements Serializable, Comparable<InputTypeDetails> {

    private static final long serialVersionUID = 1L;

    public InputTypeDetails() {
    }

    public InputTypeDetails(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StaticInputDetails getStaticInput() {
        return staticInput;
    }

    public void setStaticInput(StaticInputDetails staticInput) {
        this.staticInput = staticInput;
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
        if (!(object instanceof InputTypeDetails)) {
            return false;
        }
        InputTypeDetails other = (InputTypeDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.InputType[ type=" + type + " ]";
    }

    @Override
    public int compareTo(InputTypeDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Short id;
    private String type;
    private StaticInputDetails staticInput;

}
