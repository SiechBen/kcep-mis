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
public enum ProgressTypeDetail implements Serializable {

    PHYSICAL(new Integer("167"), "Physical"),
    FINANCIAL(new Integer("168"), "Financial");

    private ProgressTypeDetail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ProgressTypeDetail getProgressTypeDetail(int id) {
        switch (id) {
            case 157:
                return FINANCIAL;
            case 158:
                return PHYSICAL;
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
