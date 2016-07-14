/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.divisionallocation;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.DivisionalLocation;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.utilities.DivisionalLocationDetails;

/**
 *
 * @author siech
 */
@Stateless
public class DivisionalLocationRequests extends EntityRequests implements DivisionalLocationRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addDivisionalLocation(DivisionalLocationDetails divisionalLocationDetails) throws MilesException {

        if (divisionalLocationDetails == null) {
            throw new InvalidArgumentException("error_041_01");
        } else if (divisionalLocationDetails.getName() == null) {
            throw new InvalidArgumentException("error_041_02");
        } else if (divisionalLocationDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_041_03");
        }

        DivisionalLocation divisionalLocation;
        q = em.createNamedQuery("DivisionalLocation.findByName");
        q.setParameter("name", divisionalLocationDetails.getName());
        try {
            divisionalLocation = (DivisionalLocation) q.getSingleResult();
        } catch (Exception e) {
            divisionalLocation = null;
        }
        if (divisionalLocation != null) {
            throw new InvalidArgumentException("error_041_04");
        }

        divisionalLocation = new DivisionalLocation();
        divisionalLocation.setName(divisionalLocationDetails.getName());

        try {
            em.persist(divisionalLocation);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return divisionalLocation.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<DivisionalLocationDetails> retrieveDivisionalLocations() throws MilesException {
        List<DivisionalLocation> divisionalLocations = new ArrayList<>();
        q = em.createNamedQuery("DivisionalLocation.findAll");
        try {
            divisionalLocations = q.getResultList();
        } catch (Exception e) {
        }

        return convertDivisionalLocationsToDivisionalLocationDetailsList(divisionalLocations);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DivisionalLocationDetails> retrieveDivisionalLocations(short countyId) throws MilesException {
        List<DivisionalLocation> divisionalLocations = new ArrayList<>();
        q = em.createNamedQuery("DivisionalLocation.findByCountyId");
        q.setParameter("countyId", countyId);
        try {
            divisionalLocations = q.getResultList();
        } catch (Exception e) {
        }

        return convertDivisionalLocationsToDivisionalLocationDetailsList(divisionalLocations);
    }

    @Override
    public DivisionalLocationDetails retrieveDivisionalLocation(int id) throws MilesException {
        DivisionalLocation divisionalLocation;
        q = em.createNamedQuery("DivisionalLocation.findById");
        q.setParameter("id", id);
        try {
            divisionalLocation = (DivisionalLocation) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertDivisionalLocationToDivisionalLocationDetails(divisionalLocation);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editDivisionalLocation(DivisionalLocationDetails divisionalLocationDetails) throws MilesException {

        if (divisionalLocationDetails == null) {
            throw new InvalidArgumentException("error_041_01");
        } else if (divisionalLocationDetails.getId() == null) {
            throw new InvalidArgumentException("error_041_05");
        } else if (divisionalLocationDetails.getName() == null) {
            throw new InvalidArgumentException("error_041_02");
        } else if (divisionalLocationDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_041_03");
        }

        DivisionalLocation divisionalLocation;
        q = em.createNamedQuery("DivisionalLocation.findByName");
        q.setParameter("name", divisionalLocationDetails.getName());
        try {
            divisionalLocation = (DivisionalLocation) q.getSingleResult();
        } catch (Exception e) {
            divisionalLocation = null;
        }
        if (divisionalLocation != null) {
            if (divisionalLocation.getId().equals(divisionalLocationDetails.getId())) {
                throw new InvalidArgumentException("error_041_04");
            }
        }

        divisionalLocation = em.find(DivisionalLocation.class, divisionalLocationDetails.getId());
        divisionalLocation.setId(divisionalLocationDetails.getId());
        divisionalLocation.setName(divisionalLocationDetails.getName());

        try {
            em.merge(divisionalLocation);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeDivisionalLocation(int id) throws MilesException {
        DivisionalLocation divisionalLocation = em.find(DivisionalLocation.class, id);
        try {
            em.remove(divisionalLocation);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public DivisionalLocationDetails convertDivisionalLocationToDivisionalLocationDetails(DivisionalLocation divisionalLocation) {

        DivisionalLocationDetails divisionalLocationDetails = new DivisionalLocationDetails();
        try {
            divisionalLocationDetails.setId(divisionalLocation.getId());
        } catch (Exception e) {
        }
        try {
            divisionalLocationDetails.setName(divisionalLocation.getName());
        } catch (Exception e) {
        }

        return divisionalLocationDetails;

    }

    private List<DivisionalLocationDetails> convertDivisionalLocationsToDivisionalLocationDetailsList(List<DivisionalLocation> divisionalLocations) {

        List<DivisionalLocationDetails> divisionalLocationDetailsList = new ArrayList<>();
        for (DivisionalLocation divisionalLocation : divisionalLocations) {
            divisionalLocationDetailsList.add(convertDivisionalLocationToDivisionalLocationDetails(divisionalLocation));
        }

        return divisionalLocationDetailsList;

    }

//</editor-fold>
    @EJB
    private CountyRequestsLocal countyService;
}
