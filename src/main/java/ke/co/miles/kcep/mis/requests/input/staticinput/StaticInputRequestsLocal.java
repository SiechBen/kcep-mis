/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.input.staticinput;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.StaticInput;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.StaticInputDetails;

/**
 *
 * @author siech
 */
@Local
public interface StaticInputRequestsLocal {

    /**
     *
     * @param staticInputDetails details of the static input record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addStaticInput(StaticInputDetails staticInputDetails)
            throws MilesException;

    /**
     *
     * @return the list of static input record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<StaticInputDetails> retrieveStaticInputs() throws MilesException;

    /**
     *
     * @param id the unique identifier of the static input record to be
     * retrieved
     * @return the details of the static input record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public StaticInputDetails retrieveStaticInput(int id) throws MilesException;

    /**
     *
     * @param staticInputDetails details of the static input record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editStaticInput(StaticInputDetails staticInputDetails)
            throws MilesException;

    /**
     *
     * @param id the unique identifier of the static input record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeStaticInput(int id) throws MilesException;

    /**
     *
     * @param staticInput the static input to be converted to details
     * @return the result of the conversion
     */
    public StaticInputDetails convertStaticInputToStaticInputDetails(
            StaticInput staticInput);

    /**
     *
     * @param inputTypeId the unique identifier of the input type for which the
     * static inputs are to be retrieved
     * @return the list of static inputs retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<StaticInputDetails> retrieveStaticInputs(short inputTypeId)
            throws MilesException;

    /**
     *
     * @return the list of produce types retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<StaticInputDetails> retrieveProduceTypes() throws MilesException;

}
