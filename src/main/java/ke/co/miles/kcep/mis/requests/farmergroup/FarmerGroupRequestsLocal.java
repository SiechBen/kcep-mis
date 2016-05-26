/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmergroup;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.FarmerGroup;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.FarmerGroupDetails;

/**
 *
 * @author siech
 */
@Local
public interface FarmerGroupRequestsLocal {

    /**
     *
     * @param farmerGroupDetails details of the contact record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addFarmerGroup(FarmerGroupDetails farmerGroupDetails) throws MilesException;

    /**
     *
     * @return the list of farmer group record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FarmerGroupDetails> retrieveFarmerGroups() throws MilesException;

    /**
     *
     * @param id the unique identifier of the farmer group record to be retrieved
     * @return the details of the farmer group record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public FarmerGroupDetails retrieveFarmerGroup(int id) throws MilesException;

    /**
     *
     * @param farmerGroupDetails details of the contact record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editFarmerGroup(FarmerGroupDetails farmerGroupDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the farmer group record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeFarmerGroup(int id) throws MilesException;

      /**
     *
     * @param farmerGroup the farmer group to be converted to details
     * @return the details of the converted farmer group
     */
    public FarmerGroupDetails convertFarmerGroupToFarmerGroupDetails(FarmerGroup farmerGroup);

}
