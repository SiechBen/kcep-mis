/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.County;
import ke.co.miles.kcep.mis.entities.Location;
import ke.co.miles.kcep.mis.entities.SubCounty;
import ke.co.miles.kcep.mis.entities.Ward;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.utilities.LocationDetails;

/**
 *
 * @author siech
 */
@Stateless
public class LocationRequests extends EntityRequests implements LocationRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public Location addLocation(LocationDetails locationDetails) throws MilesException {

        if (locationDetails == null) {
            throw new InvalidArgumentException("error_003_01");
        } else if (locationDetails.getLongitude() != null) {
            if (locationDetails.getLatitude() == null) {
                throw new InvalidArgumentException("error_003_02");
            }

        } else if (locationDetails.getLatitude() != null) {
            if (locationDetails.getLongitude() == null) {
                throw new InvalidArgumentException("error_003_03");
            }

        } else if (locationDetails.getLatitude() != null && locationDetails.getLongitude() != null) {
            if (locationDetails.getLatitude().compareTo(new BigDecimal("90")) == 1 || locationDetails.getLatitude().compareTo(new BigDecimal("-90")) == -1) {
                throw new InvalidArgumentException("error_003_04");

            } else if (locationDetails.getLongitude().compareTo(new BigDecimal("180")) == 1 || locationDetails.getLongitude().compareTo(new BigDecimal("-180")) == -1) {
                throw new InvalidArgumentException("error_003_05");

            }
        }

        Location location = new Location();
        location.setLatitude(locationDetails.getLatitude());
        location.setLongitude(locationDetails.getLongitude());
        try {
            location.setCounty(em.find(County.class, locationDetails.getCounty().getId()));
        } catch (Exception e) {
            location.setCounty(null);
        }
        try {
            location.setSubCounty(em.find(SubCounty.class, locationDetails.getSubCounty().getId()));
        } catch (Exception e) {
            location.setSubCounty(null);
        }
        try {
            location.setWard(em.find(Ward.class, locationDetails.getWard().getId()));
        } catch (Exception e) {
            location.setWard(null);
        }

        try {
            em.persist(location);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return location;

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<LocationDetails> retrieveLocations() throws MilesException {
        List<Location> locations = new ArrayList<>();
        q = em.createNamedQuery("Location.findAll");
        try {
            locations = q.getResultList();
        } catch (Exception e) {
        }

        return convertLocationsToLocationDetailsList(locations);
    }

    @Override
    public LocationDetails retrieveLocation(int id) throws MilesException {
        Location location;
        q = em.createNamedQuery("Location.findById");
        q.setParameter("id", id);
        try {
            location = (Location) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertLocationToLocationDetails(location);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public Location editLocation(LocationDetails locationDetails) throws MilesException {

        if (locationDetails == null) {
            throw new InvalidArgumentException("error_003_01");
        } else if (locationDetails.getId() == null) {
            throw new InvalidArgumentException("error_003_06");
        } else if (locationDetails.getLongitude() != null) {
            if (locationDetails.getLatitude() == null) {
                throw new InvalidArgumentException("error_003_02");
            }

        } else if (locationDetails.getLatitude() != null) {
            if (locationDetails.getLongitude() == null) {
                throw new InvalidArgumentException("error_003_03");
            }

        } else if (locationDetails.getLatitude() != null && locationDetails.getLongitude() != null) {
            if (locationDetails.getLatitude().compareTo(new BigDecimal("90")) == 1 || locationDetails.getLatitude().compareTo(new BigDecimal("-90")) == -1) {
                throw new InvalidArgumentException("error_003_04");

            } else if (locationDetails.getLongitude().compareTo(new BigDecimal("180")) == 1 || locationDetails.getLongitude().compareTo(new BigDecimal("-180")) == -1) {
                throw new InvalidArgumentException("error_003_05");

            }
        }

        Location location = em.find(Location.class, locationDetails.getId());
        location.setId(locationDetails.getId());
        location.setLatitude(locationDetails.getLatitude());
        location.setLongitude(locationDetails.getLongitude());
        try {
            location.setCounty(em.find(County.class, locationDetails.getCounty().getId()));
        } catch (Exception e) {
            location.setCounty(null);
        }
        try {
            location.setSubCounty(em.find(SubCounty.class, locationDetails.getSubCounty().getId()));
        } catch (Exception e) {
            location.setSubCounty(null);
        }
        try {
            location.setWard(em.find(Ward.class, locationDetails.getWard().getId()));
        } catch (Exception e) {
            location.setWard(null);
        }

        try {
            em.merge(location);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return location;

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeLocation(int id) throws MilesException {
        Location location = em.find(Location.class, id);
        try {
            em.remove(location);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public LocationDetails convertLocationToLocationDetails(Location location) {

        LocationDetails locationDetails = new LocationDetails(location.getId());
        locationDetails.setDivisionalLocation(divsionalLocationService.convertSubCountyToSubCountyDetails(location.getSubCounty()));
        locationDetails.setSubCounty(subCountyService.convertSubCountyToSubCountyDetails(location.getSubCounty()));
        locationDetails.setVillage(villageService.convertCountyToCountyDetails(location.getCounty()));
        locationDetails.setCounty(countyService.convertCountyToCountyDetails(location.getCounty()));
        locationDetails.setWard(wardService.convertWardToWardDetails(location.getWard()));
        locationDetails.setLatitude(location.getLatitude());
        locationDetails.setLongitude(location.getLongitude());
        return locationDetails;

    }

    private List<LocationDetails> convertLocationsToLocationDetailsList(List<Location> locations) {
        List<LocationDetails> locationDetailsList = new ArrayList<>();
        for (Location location : locations) {
            locationDetailsList.add(convertLocationToLocationDetails(location));
        }

        return locationDetailsList;

    }

//</editor-fold>
    @EJB
    private CountyRequestsLocal countyService;
    @EJB
    private SubCountyRequestsLocal subCountyService;
    @EJB
    private WardRequestsLocal wardService;
}
