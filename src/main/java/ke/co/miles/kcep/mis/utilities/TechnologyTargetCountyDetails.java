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
public class TechnologyTargetCountyDetails implements Serializable, Comparable<TechnologyTargetCountyDetails> {

    private static final long serialVersionUID = 1L;

    public TechnologyTargetCountyDetails() {
    }

    public TechnologyTargetCountyDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CountyDetails getCounty() {
        return county;
    }

    public void setCounty(CountyDetails county) {
        this.county = county;
    }

    public PersonDetails getKalroOfficer() {
        return kalroOfficer;
    }

    public void setKalroOfficer(PersonDetails kALROofficer) {
        this.kalroOfficer = kALROofficer;
    }

    public TechnologyDetails getTechnology() {
        return technology;
    }

    public void setTechnology(TechnologyDetails technology) {
        this.technology = technology;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TechnologyTargetCountyDetails)) {
            return false;
        }
        TechnologyTargetCountyDetails other = (TechnologyTargetCountyDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.TechnologyTargetCountyDetailsDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(TechnologyTargetCountyDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private CountyDetails county;
    private PersonDetails kalroOfficer;
    private TechnologyDetails technology;

}
