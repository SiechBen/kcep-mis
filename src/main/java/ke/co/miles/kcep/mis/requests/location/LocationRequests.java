/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location;

import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Location;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.LocationDetails;

/**
 *
 * @author siech
 */
@Stateless
public class LocationRequests extends EntityRequests implements LocationRequestsLocal {

    @Override
    public Location addLocation(LocationDetails locationDetails) throws MilesException {

        Location location = new Location();
        location.setWard(locationDetails.getWard());
        location.setCounty(locationDetails.getCounty());
        location.setLatitude(locationDetails.getLatitude());
        location.setLongitude(locationDetails.getLongitude());
        location.setSubCounty(locationDetails.getSubCounty());

        try {
            em.persist(location);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return location;

    }

}
