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
public class NumberOfFarmersDetails implements Serializable, Comparable<NumberOfFarmersDetails> {

    private static final long serialVersionUID = 1L;

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

    public SexDetail getSex() {
        return sex;
    }

    public void setSex(SexDetail sex) {
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
        if (!(object instanceof NumberOfFarmersDetails)) {
            return false;
        }
        NumberOfFarmersDetails other = (NumberOfFarmersDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.NumberOfFarmers[ id=" + id + " ]";
    }

    @Override
    public int compareTo(NumberOfFarmersDetails o) {
        return this.id.compareTo(getId());
    }

    private Integer id;
    private SexDetail sex;
    private Integer number;
    private AgeBracketDetails ageBracket;
    private NumberDescriptionDetails numberDescription;
    private SampledFarmerDataDetails sampledFarmerData;

}
