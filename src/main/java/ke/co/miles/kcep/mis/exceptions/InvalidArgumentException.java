/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.exceptions;

/**
 *
 * @author siech
 */
public class InvalidArgumentException extends MilesException {

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String code, String prepend, String append) {
        super(code, prepend, append);
    }

}
