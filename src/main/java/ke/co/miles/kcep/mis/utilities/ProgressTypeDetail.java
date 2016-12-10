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
    FINANCIAL(new Integer("168"), "Financial"),
    PHYSICAL_APPRAISAL(new Integer("173"), "Physical appraisal"),
    FINANCIAL_APPRAISAL(new Integer("174"), "Financial appraisal");

    private ProgressTypeDetail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ProgressTypeDetail getProgressTypeDetail(int id) {
        switch (id) {
            case 167:
                return PHYSICAL;
            case 168:
                return FINANCIAL;
            case 173:
                return PHYSICAL_APPRAISAL;
            case 174:
                return FINANCIAL_APPRAISAL;
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
