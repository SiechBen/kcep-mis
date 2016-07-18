/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.inputscollection;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.InputsCollection;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.InputsCollectionDetails;

/**
 *
 * @author siech
 */
@Local
public interface InputsCollectionRequestsLocal {

    /**
     *
     * @param inputsCollectionDetails details of the inputs collection record to
     * be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addInputsCollection(InputsCollectionDetails inputsCollectionDetails) throws MilesException;

    /**
     *
     * @param farmerId the unique identifier of the farmer whose inputs
     * collection record is to be retrieved
     * @return the details of the inputs collection record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<InputsCollectionDetails> retrieveInputsCollections(int farmerId) throws MilesException;

    /**
     *
     * @param inputsCollectionDetails details of the inputs collection record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editInputsCollection(InputsCollectionDetails inputsCollectionDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the inputs collection record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeInputsCollection(int id) throws MilesException;

    /**
     *
     * @param inputsCollection the inputs collection to be converted
     * @return the details of the converted inputs collection
     */
    public InputsCollectionDetails convertInputsCollectionToInputsCollectionDetails(InputsCollection inputsCollection);

}
