/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.county.sub;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.SubCounty;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;

/**
 *
 * @author siech
 */
@Local
public interface SubCountyRequestsLocal {

    /**
     *
     * @param subCountyDetails details of the sub-county record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addSubCounty(SubCountyDetails subCountyDetails) throws MilesException;

    /**
     *
     * @return the list of sub-county record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubCountyDetails> retrieveSubCounties() throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub-county record to be retrieved
     * @return the details of the sub-county record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public SubCountyDetails retrieveSubCounty(int id) throws MilesException;

    /**
     *
     * @param subCountyDetails details of the sub-county record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editSubCounty(SubCountyDetails subCountyDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub-county record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeSubCounty(int id) throws MilesException;

    /**
     *
     * @param subCounty the sub-county to be converted
     * @return the details of the converted sub-county
     */
    public SubCountyDetails convertSubCountyToSubCountyDetails(SubCounty subCounty);

}
