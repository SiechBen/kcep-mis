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
public enum PersonRoleDetail implements Serializable {

    FARMER(new Short("1"), "Farmer"),
    AGRO_DEALER(new Short("2"), "Agro-dealer"),
    WAO(new Short("3"), "WAO (Ward Extension Officer)"),
    SUB_COUNTY_OFFICER(new Short("4"), "Sub-county Officer"),
    COUNTY_OFFICER(new Short("5"), "County Officer"),
    PCU_REGIONAL_STAFF(new Short("6"), "PCU Regional Staff"),
    PROGRAMME_COORDINATOR(new Short("7"), "Programme Coordinator"),
    KALRO_OFFICER(new Short("8"), "KALRO Officer"),
    SYSTEM_ADMIN(new Short("9"), "System Admin"),
    EQUITY_OFFICER(new Short("10"), "Equity"),
    WAREHOUSE_OPERATOR(new Short("11"), "Warehouse Operator"),
    AGMARK_OFFICER(new Short("12"), "AGMARK Officer"),
    SENIOR_PROGRAMME_COORDINATOR(new Short("13"), "Senior Programme Coordinator"),
    PCU_STAFF(new Short("14"), "PCU Staff");

    private PersonRoleDetail(Short id, String personRole) {
        this.id = id;
        this.personRole = personRole;
    }

    public static PersonRoleDetail getPersonRoleDetail(Short id) {
        switch (id) {
            case 1:
                return FARMER;
            case 2:
                return AGRO_DEALER;
            case 3:
                return WAO;
            case 4:
                return SUB_COUNTY_OFFICER;
            case 5:
                return COUNTY_OFFICER;
            case 6:
                return PCU_REGIONAL_STAFF;
            case 7:
                return PROGRAMME_COORDINATOR;
            case 8:
                return KALRO_OFFICER;
            case 9:
                return SYSTEM_ADMIN;
            case 10:
                return EQUITY_OFFICER;
            case 11:
                return WAREHOUSE_OPERATOR;
            case 12:
                return AGMARK_OFFICER;
            case 13:
                return SENIOR_PROGRAMME_COORDINATOR;
            case 14:
                return PCU_STAFF;
            default:
                return null;
        }
    }

    public Short getId() {
        return id;
    }

    public String getPersonRole() {
        return personRole;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.PersonRoleDetails[ personRole=" + personRole + " ]";
    }

    private final Short id;
    private final String personRole;

}
