/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.county;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.County;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.CountyDetails;

/**
 *
 * @author siech
 */
@Local
public interface CountyRequestsLocal {

    /**
     *
     * @param countyDetails details of the county record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addCounty(CountyDetails countyDetails) throws MilesException;

    /**
     *
     * @return the list of county record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<CountyDetails> retrieveCountys() throws MilesException;

    /**
     *
     * @param id the unique identifier of the county record to be retrieved
     * @return the details of the county record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public CountyDetails retrieveCounty(int id) throws MilesException;

    /**
     *
     * @param countyDetails details of the county record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editCounty(CountyDetails countyDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the county record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeCounty(int id) throws MilesException;

    /**
     *
     * @param county the county to be converted
     * @return the details of the converted county
     */
    public CountyDetails convertCountyToCountyDetails(County county);

}
