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
public enum RegionDetail implements Serializable {

    EASTERN(new Short("1"), "Eastern"), WESTERN(new Short("2"), "Western");

    private RegionDetail(Short id, String name) {
        this.id = id;
        this.name = name;
    }

    public static RegionDetail getRegionDetail(short id) {
        switch (id) {
            case 1:
                return EASTERN;
            case 2:
                return WESTERN;
            default:
                return null;
        }
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

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Region[ name=" + name + " ]";
    }

    private Short id;
    private String name;

}
