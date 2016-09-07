/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.data.samplefarmer;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.NumberOfFarmers;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.NumberOfFarmersDetails;

/**
 *
 * @author siech
 */
@Local
public interface NumberOfFarmersRequestsLocal {

    /**
     *
     * @param numberOfFarmersDetails details of the number of farmers record to
     * be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addNumberOfFarmers(NumberOfFarmersDetails numberOfFarmersDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the number of farmers record to retrieve
     * @return the details of the number of farmers record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public NumberOfFarmersDetails retrieveNumberOfFarmers(int id) throws MilesException;

    /**
     *
     * @return the list of number of farmers record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<NumberOfFarmersDetails> retrievePeople() throws MilesException;

    /**
     *
     * @param numberOfFarmersDetails details of the number of farmers record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editNumberOfFarmers(NumberOfFarmersDetails numberOfFarmersDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the number of farmers record to be removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeNumberOfFarmers(int id) throws MilesException;

    /**
     *
     * @param numberOfFarmers the number of farmers record
     * @return the details of the number of farmers record
     */
    public NumberOfFarmersDetails convertNumberOfFarmersToNumberOfFarmersDetails(NumberOfFarmers numberOfFarmers);

}
