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
public class InvalidStateException extends MilesException {

    public InvalidStateException(String message) {
        super(message);
    }

    public InvalidStateException(String code, String prepend, String append) {
        super(code, prepend, append);
    }

}