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
public enum WarehouseTypeDetail implements Serializable {

    WAREHOUSE(new Integer("20"), "Warehouse"),
    COLLECTION_CENTRE(new Integer("21"), "Collection Centre");

    private WarehouseTypeDetail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static WarehouseTypeDetail getNameDetail(Integer id) {
        switch (id) {
            case 1:
                return WAREHOUSE;
            case 2:
                return COLLECTION_CENTRE;
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
        return "ke.co.miles.kcep.mis.utilities.WarehouseTypeDetail[ name=" + getName() + " ]";
    }

    private final Integer id;
    private final String name;

}
