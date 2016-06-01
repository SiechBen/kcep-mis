/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.data.samplefarmer;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.SampledFarmerData;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.SampledFarmerDataDetails;

/**
 *
 * @author siech
 */
@Local
public interface SampledFarmerDataRequestsLocal {

    /**
     *
     * @param sampledFarmerData details of the sampled farmer data record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addData(SampledFarmerDataDetails sampledFarmerData) throws MilesException;

    /**
     *
     * @return the list of sampled farmer data record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SampledFarmerDataDetails> retrieveData() throws MilesException;

    /**
     *
     * @param id the unique identifier of the sampled farmer data record to be
     * retrieved
     * @return the details of the sampled farmer data record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public SampledFarmerDataDetails retrieveData(int id) throws MilesException;

    /**
     *
     * @param sampledFarmerData details of the sampled farmer data record to be
     * edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editData(SampledFarmerDataDetails sampledFarmerData) throws MilesException;

    /**
     *
     * @param id the unique identifier of the sampled farmer data record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeData(int id) throws MilesException;

    /**
     *
     * @param sampledFarmerData the sampled data record to be converted to
     * details
     * @return the resultant details
     */
    public SampledFarmerDataDetails convertSampledFarmerDataToSampledFarmerDataDetails(SampledFarmerData sampledFarmerData);

}
