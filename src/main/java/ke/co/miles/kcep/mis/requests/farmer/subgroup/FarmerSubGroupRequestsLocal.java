/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.subgroup;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.FarmerSubGroup;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.FarmerSubGroupDetails;

/**
 *
 * @author siech
 */
@Local
public interface FarmerSubGroupRequestsLocal {

    /**
     *
     * @param farmerSubGroupDetails details of the farmer sub-group record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addFarmerSubGroup(FarmerSubGroupDetails farmerSubGroupDetails) throws MilesException;

    /**
     *
     * @return the list of farmer sub-group record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FarmerSubGroupDetails> retrieveCounties() throws MilesException;

    /**
     *
     * @param id the unique identifier of the farmer sub-group record to be
     * retrieved
     * @return the details of the farmer sub-group record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public FarmerSubGroupDetails retrieveFarmerSubGroup(int id) throws MilesException;

    /**
     *
     * @param farmerSubGroupDetails details of the farmer sub-group record to be
     * edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editFarmerSubGroup(FarmerSubGroupDetails farmerSubGroupDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the farmer sub-group record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeFarmerSubGroup(int id) throws MilesException;

    /**
     *
     * @param farmerSubGroup the farmer sub-group to be converted
     * @return the details of the converted farmerSubGroup
     */
    public FarmerSubGroupDetails convertFarmerSubGroupToFarmerSubGroupDetails(FarmerSubGroup farmerSubGroup);

}
