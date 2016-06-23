/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.inputtype;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.InputType;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.InputTypeDetails;

/**
 *
 * @author siech
 */
@Local
public interface InputTypeRequestsLocal {

    /**
     *
     * @param inputTypeDetails details of the input type record to be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addInputType(InputTypeDetails inputTypeDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the input type record to retrieve
     * @return the details of the input type record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public InputTypeDetails retrieveInputType(int id) throws MilesException;

    /**
     *
     * @return the list of input type record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<InputTypeDetails> retrieveInputTypes() throws MilesException;

    /**
     *
     * @param inputTypeDetails details of the input type record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editInputType(InputTypeDetails inputTypeDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the input type record to be removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeInputType(int id) throws MilesException;

    /**
     *
     * @param inputType the input type record
     * @return the details of the input type record
     */
    public InputTypeDetails convertInputTypeToInputTypeDetails(InputType inputType);

}
