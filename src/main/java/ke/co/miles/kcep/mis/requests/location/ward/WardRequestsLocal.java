/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.ward;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Ward;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.WardDetails;

/**
 *
 * @author siech
 */
@Local
public interface WardRequestsLocal {

    /**
     *
     * @param wardDetails details of the ward record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addWard(WardDetails wardDetails) throws MilesException;

    /**
     *
     * @return the list of ward record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<WardDetails> retrieveWards() throws MilesException;

    /**
     *
     * @param subCountyId the unique identifier of the sub-county of which the
     * wards are part
     * @return the list of ward record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<WardDetails> retrieveWards(int subCountyId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the ward record to be retrieved
     * @return the details of the ward record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public WardDetails retrieveWard(int id) throws MilesException;

    /**
     *
     * @param wardDetails details of the ward record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editWard(WardDetails wardDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the ward record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeWard(int id) throws MilesException;

    /**
     *
     * @param ward the ward to be converted
     * @return the details of the converted ward
     */
    public WardDetails convertWardToWardDetails(Ward ward);

}
