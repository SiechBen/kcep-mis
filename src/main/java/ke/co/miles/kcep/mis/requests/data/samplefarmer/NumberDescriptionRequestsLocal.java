/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.data.samplefarmer;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.NumberDescription;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.NumberDescriptionDetails;

/**
 *
 * @author siech
 */
@Local
public interface NumberDescriptionRequestsLocal {

    /**
     *
     * @param numberDescriptionDetails details of the number description record
     * to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addNumberDescription(NumberDescriptionDetails numberDescriptionDetails) throws MilesException;

    /**
     *
     * @return the list of number description record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<NumberDescriptionDetails> retrieveNumberDescriptions() throws MilesException;

    /**
     *
     * @param id the unique identifier of the number description record to be
     * retrieved
     * @return the details of the number description record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public NumberDescriptionDetails retrieveNumberDescription(int id) throws MilesException;

    /**
     *
     * @param numberDescriptionDetails details of the number description record
     * to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editNumberDescription(NumberDescriptionDetails numberDescriptionDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the number description record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeNumberDescription(int id) throws MilesException;

    /**
     *
     * @param numberDescription the number description to be converted
     * @return the details of the converted numberDescription
     */
    public NumberDescriptionDetails convertNumberDescriptionToNumberDescriptionDetails(NumberDescription numberDescription);

}
