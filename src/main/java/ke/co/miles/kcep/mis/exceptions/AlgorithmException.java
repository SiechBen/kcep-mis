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
public class AlgorithmException extends MilesException {

    private static final long serialVersionUID = 1L;

    public AlgorithmException(String code) {
        super(code);
    }

    public AlgorithmException(String code, String prepend, String append) {
        super(code, prepend, append);
    }

}
