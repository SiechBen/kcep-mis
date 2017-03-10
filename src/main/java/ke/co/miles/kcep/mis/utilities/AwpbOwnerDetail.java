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
public enum AwpbOwnerDetail implements Serializable {

    KALRO(new Integer("175"), "KALRO"),
    AGMARK(new Integer("176"), "AGMARK"),
    EQUITY(new Integer("177"), "EQUITY"),
    PCU(new Integer("178"), "PCU");

    private AwpbOwnerDetail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AwpbOwnerDetail getAwpbOwnerDetail(int id) {
        switch (id) {
            case 175:
                return KALRO;
            case 176:
                return AGMARK;
            case 177:
                return EQUITY;
            case 178:
                return PCU;
            default:
                return null;
        }
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    private final Integer id;
    private final String name;
}
