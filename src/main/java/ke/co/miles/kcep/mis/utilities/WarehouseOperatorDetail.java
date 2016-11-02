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
public enum WarehouseOperatorDetail implements Serializable {

    FARMER_OWNED(new Integer("12"), "Farmer Owned"),
    PRIVATELY_OWNED(new Integer("13"), "Privately Owned");

    private WarehouseOperatorDetail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static WarehouseOperatorDetail getNameDetail(Integer id) {
        switch (id) {
            case 1:
                return FARMER_OWNED;
            case 2:
                return PRIVATELY_OWNED;
            default:
                return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.WarehouseOperatorDetail[ name=" + getName() + " ]";
    }

    private final Integer id;
    private final String name;

}
