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
public class TechnologyDetails implements Serializable, Comparable<TechnologyDetails> {

    private static final long serialVersionUID = 1L;

    public TechnologyDetails() {
    }

    public TechnologyDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNumberOfStudiesConducted() {
        return numberOfStudiesConducted;
    }

    public void setNumberOfStudiesConducted(Short numberOfStudiesConducted) {
        this.numberOfStudiesConducted = numberOfStudiesConducted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypePurpose() {
        return typePurpose;
    }

    public void setTypePurpose(String typePurpose) {
        this.typePurpose = typePurpose;
    }

    public String getTargetSubCounties() {
        return targetSubCounties;
    }

    public void setTargetSubCounties(String targetSubCounties) {
        this.targetSubCounties = targetSubCounties;
    }

    public PersonDetails getKalroOfficer() {
        return kalroOfficer;
    }

    public void setKalroOfficer(PersonDetails kalroOfficer) {
        this.kalroOfficer = kalroOfficer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TechnologyDetails)) {
            return false;
        }
        TechnologyDetails other = (TechnologyDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.TechnologyDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(TechnologyDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String name;
    private String typePurpose;
    private String targetSubCounties;
    private PersonDetails kalroOfficer;
    private Short numberOfStudiesConducted;

}
