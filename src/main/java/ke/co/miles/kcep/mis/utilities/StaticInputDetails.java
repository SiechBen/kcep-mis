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
public class StaticInputDetails implements Serializable, Comparable<StaticInputDetails> {

    private static final long serialVersionUID = 1L;

    public StaticInputDetails() {
    }

    public StaticInputDetails(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StaticInputDetails)) {
            return false;
        }
        StaticInputDetails other = (StaticInputDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.StaticInputDetails[ name=" + name + " ]";
    }

    @Override
    public int compareTo(StaticInputDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the inputType
     */
    public InputTypeDetails getInputType() {
        return inputType;
    }

    /**
     * @param inputType the inputType to set
     */
    public void setInputType(InputTypeDetails inputType) {
        this.inputType = inputType;
    }

    private Integer id;
    private String name;
    private InputTypeDetails inputType;

}
