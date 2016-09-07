/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.divisionallocation;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.DivisionalLocation;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.DivisionalLocationDetails;

/**
 *
 * @author siech
 */
@Local
public interface DivisionalLocationRequestsLocal {

    /**
     *
     * @param divisionalLocationDetails details of the divisional location record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addDivisionalLocation(DivisionalLocationDetails divisionalLocationDetails) throws MilesException;

    /**
     *
     * @return the list of divisional location record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<DivisionalLocationDetails> retrieveDivisionalLocations() throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county of which the
     * sub-counties are part
     * @return the list of divisional location record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<DivisionalLocationDetails> retrieveDivisionalLocations(short countyId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the divisional location record to be retrieved
     * @return the details of the divisional location record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public DivisionalLocationDetails retrieveDivisionalLocation(int id) throws MilesException;

    /**
     *
     * @param divisionalLocationDetails details of the divisional location record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editDivisionalLocation(DivisionalLocationDetails divisionalLocationDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the divisional location record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeDivisionalLocation(int id) throws MilesException;

    /**
     *
     * @param divisionalLocation the divisional location to be converted
     * @return the details of the converted divisional location
     */
    public DivisionalLocationDetails convertDivisionalLocationToDivisionalLocationDetails(DivisionalLocation divisionalLocation);

}
