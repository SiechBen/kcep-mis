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
public class InputVarietyDetails implements Serializable {

    public InputVarietyDetails() {
    }

    public InputVarietyDetails(Integer id) {
        this.id = id;
    }

    public InputVarietyDetails(Integer id, String variety) {
        this.id = id;
        this.variety = variety;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
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
        if (!(object instanceof InputVarietyDetails)) {
            return false;
        }
        InputVarietyDetails other = (InputVarietyDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.InputVariety[ id=" + id + " ]";
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String variety;
    private StaticInputDetails staticInput;

}
