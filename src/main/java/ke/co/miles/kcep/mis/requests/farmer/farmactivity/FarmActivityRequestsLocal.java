/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.farmactivity;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.FarmActivity;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.FarmActivityDetails;

/**
 *
 * @author siech
 */
@Local
public interface FarmActivityRequestsLocal {

    /**
     *
     * @param farmActivityDetails details of the farm activity record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addFarmActivity(FarmActivityDetails farmActivityDetails) throws MilesException;

    /**
     *
     * @param farmerId the unique identifier of the farmer for whom farm
     * activity records are to be retrieved
     * @return the list of farm activity record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FarmActivityDetails> retrieveFarmActivities(int farmerId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the farm activity record to be
     * retrieved
     * @return the details of the farm activity record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public FarmActivityDetails retrieveFarmActivity(int id) throws MilesException;

    /**
     *
     * @param farmActivityDetails details of the farm activity record to be
     * edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editFarmActivity(FarmActivityDetails farmActivityDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the farm activity record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeFarmActivity(int id) throws MilesException;

    /**
     *
     * @param farmActivity the farm activity record to be converted to details
     * @return the result of the conversion
     */
    public FarmActivityDetails convertFarmActivityToFarmActivityDetails(FarmActivity farmActivity);

}
