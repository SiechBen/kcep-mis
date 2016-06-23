/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Location;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.LocationDetails;

/**
 *
 * @author siech
 */
@Local
public interface LocationRequestsLocal {

    /**
     *
     * @param locationDetails details of the location record to be created
     * @return the created record
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public Location addLocation(LocationDetails locationDetails) throws MilesException;

    /**
     *
     * @return the list of location record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<LocationDetails> retrieveLocations() throws MilesException;

    /**
     *
     * @param id the unique identifier of the location record to be retrieved
     * @return the details of the location record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public LocationDetails retrieveLocation(int id) throws MilesException;

    /**
     *
     * @param locationDetails details of the location record to be edited
     * @return the location record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public Location editLocation(LocationDetails locationDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the location record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeLocation(int id) throws MilesException;

    /**
     *
     * @param location the location to be converted to details
     * @return the details of the converted location
     */
    public LocationDetails convertLocationToLocationDetails(Location location);

}
