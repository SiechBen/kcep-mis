/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location;

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
     * @param locationDetails details of the contact record to be created
     * @return the created record
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public Location addLocation(LocationDetails locationDetails) throws MilesException;

    /**
     *
     * @param locationDetails details of the contact record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editLocation(LocationDetails locationDetails) throws MilesException;
}
