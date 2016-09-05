/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.input.variety;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.InputVariety;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.InputVarietyDetails;

/**
 *
 * @author siech
 */
@Local
public interface InputVarietyRequestsLocal {

    /**
     *
     * @param inputVarietyDetails details of the input variety record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addInputVariety(InputVarietyDetails inputVarietyDetails) throws MilesException;

    /**
     *
     * @return the list of input variety record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<InputVarietyDetails> retrieveInputVarieties() throws MilesException;

    /**
     *
     * @param id the unique identifier of the input variety record to be
     * retrieved
     * @return the details of the input variety record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public InputVarietyDetails retrieveInputVariety(int id) throws MilesException;

    /**
     *
     * @param inputVarietyDetails details of the input variety record to be
     * edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editInputVariety(InputVarietyDetails inputVarietyDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the input variety record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeInputVariety(int id) throws MilesException;

    /**
     *
     * @param inputVariety the input variety to be converted to details
     * @return the result of the conversion
     */
    public InputVarietyDetails convertInputVarietyToInputVarietyDetails(InputVariety inputVariety);

    /**
     *
     * @param staticInputId the unique identifier of the input type for which the
     * input varieties are to be retrieved
     * @return the list of input varieties retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<InputVarietyDetails> retrieveInputVarieties(int staticInputId) throws MilesException;

}
