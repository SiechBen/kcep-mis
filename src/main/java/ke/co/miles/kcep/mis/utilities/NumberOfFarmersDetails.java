/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import ke.co.miles.kcep.mis.entities.*;
import java.io.Serializable;

/**
 *
 * @author siech
 */
public class NumberOfFarmersDetails implements Serializable, Comparable<NumberOfFarmersDetails> {

    private Integer id;
    private Integer number;
    private SampledFarmerDataDetails sampledFarmerData;
    private AgeBracketDetails ageBracket;
    private NumberDescriptionDetails numberDescription;
    private Sex sex;

    public NumberOfFarmersDetails() {
    }

    public NumberOfFarmersDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public SampledFarmerDataDetails getSampledFarmerData() {
        return sampledFarmerData;
    }

    public void setSampledFarmerData(SampledFarmerDataDetails sampledFarmerData) {
        this.sampledFarmerData = sampledFarmerData;
    }

    public AgeBracketDetails getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(AgeBracketDetails ageBracket) {
        this.ageBracket = ageBracket;
    }

    public NumberDescriptionDetails getNumberDescription() {
        return numberDescription;
    }

    public void setNumberDescription(NumberDescriptionDetails numberDescription) {
        this.numberDescription = numberDescription;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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
        if (!(object instanceof NumberOfFarmersDetails)) {
            return false;
        }
        NumberOfFarmersDetails other = (NumberOfFarmersDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.NumberOfFarmers[ id=" + id + " ]";
    }

    @Override
    public int compareTo(NumberOfFarmersDetails o) {
        return this.id.compareTo(getId());
    }

}
