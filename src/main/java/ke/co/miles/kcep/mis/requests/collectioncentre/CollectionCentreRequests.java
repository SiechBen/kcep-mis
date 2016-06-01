/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.collectioncentre;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.CollectionCentre;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CollectionCentreDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;

/**
 *
 * @author siech
 */
@Stateless
public class CollectionCentreRequests extends EntityRequests implements CollectionCentreRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">  
    @Override
    public int addCollectionCentre(CollectionCentreDetails collectionCentreDetails) throws MilesException {

        if (collectionCentreDetails == null) {
            throw new InvalidArgumentException("error_009_01");
        } else if (collectionCentreDetails.getWardExtensionOfficer() == null) {
            throw new InvalidArgumentException("error_009_02");
        }

        CollectionCentre collectionCentre = new CollectionCentre();
        collectionCentre.setOperational(collectionCentreDetails.getOperational());
        if (collectionCentreDetails.getWardExtensionOfficer().getId() != null) {
            collectionCentre.setWardExtensionOfficer(em.find(Person.class, collectionCentreDetails.getWardExtensionOfficer().getId()));
        }

        try {
            em.persist(collectionCentre);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return collectionCentre.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public CollectionCentreDetails retrieveCollectionCentre(int id) throws MilesException {

        q = em.createNamedQuery("CollectionCentre.findById");
        q.setParameter("id", id);
        CollectionCentre collectionCentre;
        try {
            collectionCentre = (CollectionCentre) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertCollectionCentreToCollectionCentreDetails(collectionCentre);

    }

    @Override
    public List<CollectionCentreDetails> retrieveCollectionCentreList(int warehouseId) throws MilesException {

        q = em.createNamedQuery("CollectionCentre.findByWardExtensionOfficerId");
        q.setParameter("warehouseId", warehouseId);
        List<CollectionCentre> collectionCentreList;
        try {
            collectionCentreList = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertCollectionCentreListToCollectionCentreDetailsList(collectionCentreList);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editCollectionCentre(CollectionCentreDetails collectionCentreDetails) throws MilesException {

        if (collectionCentreDetails == null) {
            throw new InvalidArgumentException("error_009_01");
        } else if (collectionCentreDetails.getId() == null) {
            throw new InvalidArgumentException("error_009_03");
        } else if (collectionCentreDetails.getWardExtensionOfficer() == null) {
            throw new InvalidArgumentException("error_009_02");
        }

        CollectionCentre collectionCentre = em.find(CollectionCentre.class, collectionCentreDetails.getId());
        collectionCentre.setId(collectionCentreDetails.getId());
        collectionCentre.setOperational(collectionCentreDetails.getOperational());
        if (collectionCentreDetails.getWardExtensionOfficer().getId() != null) {
            collectionCentre.setWardExtensionOfficer(em.find(Person.class, collectionCentreDetails.getWardExtensionOfficer().getId()));
        }

        try {
            em.merge(collectionCentre);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeCollectionCentre(int id) throws MilesException {

        CollectionCentre collectionCentre = em.find(CollectionCentre.class, id);
        try {
            em.remove(collectionCentre);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert"> 
    @Override
    public CollectionCentreDetails convertCollectionCentreToCollectionCentreDetails(CollectionCentre collectionCentre) {

        PersonDetails wardExtensionOfficerDetails = null;
        if (collectionCentre.getWardExtensionOfficer().getId() != null) {
            wardExtensionOfficerDetails = personService.convertPersonToPersonDetails(collectionCentre.getWardExtensionOfficer());
        }

        CollectionCentreDetails collectionCentreDetails = new CollectionCentreDetails(collectionCentre.getId());
        collectionCentreDetails.setWardExtensionOfficer(wardExtensionOfficerDetails);
        collectionCentreDetails.setOperational(collectionCentre.getOperational());

        return collectionCentreDetails;

    }

    private List<CollectionCentreDetails> convertCollectionCentreListToCollectionCentreDetailsList(List<CollectionCentre> collectionCentreList) {
        List<CollectionCentreDetails> collectionCentreDetailsList = new ArrayList<>();
        collectionCentreList.stream().forEach((collectionCentre) -> {
            collectionCentreDetailsList.add(convertCollectionCentreToCollectionCentreDetails(collectionCentre));
        });

        return collectionCentreDetailsList;

    }

//</editor-fold>
    @EJB
    PersonRequestsLocal personService;
}
