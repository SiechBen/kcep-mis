/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.village;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Village;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.VillageDetails;

/**
 *
 * @author siech
 */
@Local
public interface VillageRequestsLocal {

    /**
     *
     * @param villageDetails details of the village
     * record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addVillage(VillageDetails villageDetails) throws MilesException;

    /**
     *
     * @return the list of village record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<VillageDetails> retrieveVillages() throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county of which the
     * sub-counties are part
     * @return the list of village record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<VillageDetails> retrieveVillages(short countyId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the village record to be
     * retrieved
     * @return the details of the village record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public VillageDetails retrieveVillage(int id) throws MilesException;

    /**
     *
     * @param villageDetails details of the village
     * record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editVillage(VillageDetails villageDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the village record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeVillage(int id) throws MilesException;

    /**
     *
     * @param village the village to be converted
     * @return the details of the converted village
     */
    public VillageDetails convertVillageToVillageDetails(Village village);

}
