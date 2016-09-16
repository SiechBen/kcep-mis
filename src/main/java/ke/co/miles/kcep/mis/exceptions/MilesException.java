/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.exceptions;

import java.util.ResourceBundle;

/**
 * thrown when arguments are incorrectly specified or when the database is in an
 * incorrect state
 *
 * @author siech
 */
public class MilesException extends Exception {

    {
        resourceBundle = ResourceBundle.getBundle("text");
    }

    public MilesException(String code) {
        this.code = code;
        this.message = resourceBundle.getString(code);
    }

    public MilesException(String code, String prepend, String append) {
        this.code = code;
        this.message = (prepend == null ? "" : prepend + " ")
                + resourceBundle.getString(code)
                + (append == null ? "" : " " + append);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    private static final long serialVersionUID = 1L;
    private final String code;
    private final String message;
    private final ResourceBundle resourceBundle;

}
