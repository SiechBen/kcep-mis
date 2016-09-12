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
public enum ProcurementPlanTypeDetail implements Serializable {

    GOODS(new Short("1"), "Goods"),
    NON_CONSULTING_SERVICES(new Short("2"), "Non-consulting Services"),
    CONSULTING_SERVICES(new Short("3"), "Consulting Services");

    private ProcurementPlanTypeDetail(short id, String type) {
        this.id = id;
        this.type = type;
    }

    public static ProcurementPlanTypeDetail getProcurementPlanTypeDetail(Short id) {
        switch (id) {
            case 1:
                return GOODS;
            case 2:
                return NON_CONSULTING_SERVICES;
            case 3:
                return CONSULTING_SERVICES;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ProcurementPlanTypeDetails[ id=" + getId() + " ]";
    }

    /**
     * @return the id
     */
    public short getId() {
        return id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    private final short id;
    private final String type;
}
