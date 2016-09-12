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
public enum IfadPriorReviewDetail implements Serializable {

    YES(new Short("1"), "Yes"), NO(new Short("2"), "No");

    private IfadPriorReviewDetail(Short id, String choice) {
        this.id = id;
        this.choice = choice;
    }

    public static IfadPriorReviewDetail getIfadPriorReviewDetail(Short id) {
        switch (id) {
            case 1:
                return YES;
            case 2:
                return NO;
            default:
                return null;
        }
    }

    private static final long serialVersionUID = 1L;
    private final Short id;
    private final String choice;

    public Short getId() {
        return id;
    }

    public String getChoice() {
        return choice;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.IfadPriorReviewDetails[ id=" + id + " ]";
    }

}
