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
public enum SexDetail implements Serializable {

    FEMALE(new Short("1"), "Female"),
    MALE(new Short("2"), "Male");

    private SexDetail(Short id, String sex) {
        this.id = id;
        this.sex = sex;
    }

    public static SexDetail getSexDetail(Short id) {
        switch (id) {
            case 1:
                return FEMALE;
            case 2:
                return MALE;
            default:
                return null;
        }
    }

    public static SexDetail getSexDetail(String sex) {
        switch (sex) {
            case "F":
                return FEMALE;
            case "M":
                return MALE;
            default:
                return null;
        }
    }

    public Short getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.SexDetail[ sex=" + getSex() + " ]";
    }

    private final Short id;
    private final String sex;

}
