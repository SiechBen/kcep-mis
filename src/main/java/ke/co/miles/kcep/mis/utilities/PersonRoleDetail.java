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
    REGIONAL_COORDINATOR(new Short("6"), "Regional Coordinator"),
    NATIONAL_OFFICER(new Short("7"), "National Officer"),
    KALRO_OFFICER(new Short("8"), "KALRO Officer"),
    SYSTEM_ADMIN(new Short("9"), "System Admin"),
    EQUITY_OFFICER(new Short("10"), "Equity"),
    WAREHOUSE_OPERATOR(new Short("11"), "Warehouse Operator"),
    AGMARK(new Short("12"), "Agmark");

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
                return REGIONAL_COORDINATOR;
            case 7:
                return NATIONAL_OFFICER;
            case 8:
                return KALRO_OFFICER;
            case 9:
                return SYSTEM_ADMIN;
            case 10:
                return EQUITY_OFFICER;
            case 11:
                return WAREHOUSE_OPERATOR;
            case 12:
                return AGMARK;
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
        return "ke.co.miles.kcep.mis.utilities.PersonRole[ personRole=" + personRole + " ]";
    }

    private final Short id;
    private final String personRole;

}
