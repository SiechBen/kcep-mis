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
public enum PlanVsActualDetail implements Serializable {

    PLAN(new Short("1"), "Plan"),
    ACTUAL(new Short("2"), "Actual"),
    UPDATED(new Short("3"), "Updated");

    private PlanVsActualDetail(Short id, String choice) {
        this.id = id;
        this.choice = choice;
    }

    public static PlanVsActualDetail getPlanVsActualDetail(Short id) {
        switch (id) {
            case 1:
                return PLAN;
            case 2:
                return ACTUAL;
            case 3:
                return UPDATED;
            default:
                return null;
        }
    }

    public Short getId() {
        return id;
    }

    public String getChoice() {
        return choice;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.PlanVsActual[ id=" + id + " ]";
    }

    private static final long serialVersionUID = 1L;
    private final Short id;
    private final String choice;
}
