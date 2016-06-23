/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.data.extensionandfieldvisit;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.ExtensionAndFieldVisitData;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ExtensionAndFieldVisitDataDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ExtensionAndFieldVisitDataRequests extends EntityRequests implements ExtensionAndFieldVisitDataRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addData(ExtensionAndFieldVisitDataDetails extensionAndFieldVisitDataDetails) throws MilesException {

        if (extensionAndFieldVisitDataDetails == null) {
            throw new InvalidArgumentException("error_011_01");
        } else if (extensionAndFieldVisitDataDetails.getWardExtensionOfficer() == null) {
            throw new InvalidArgumentException("error_011_02");
        }

        ExtensionAndFieldVisitData extensionAndFieldVisitData = new ExtensionAndFieldVisitData();
        extensionAndFieldVisitData.setFieldVisitsWardLocations(extensionAndFieldVisitDataDetails.getFieldVisitsWardLocations());
        extensionAndFieldVisitData.setNatureOfAdvisoryServices(extensionAndFieldVisitDataDetails.getNatureOfAdvisoryServices());
        extensionAndFieldVisitData.setNumberOfFarmersVisited(extensionAndFieldVisitDataDetails.getNumberOfFarmersVisited());
        extensionAndFieldVisitData.setNumberOfFieldVisitsConducted(extensionAndFieldVisitDataDetails.getNumberOfFieldVisitsConducted());
        extensionAndFieldVisitData.setNumberOfPeopleSeekingOrOfferedAdvisoryServices(extensionAndFieldVisitDataDetails.getNumberOfPeopleSeekingOrOfferedAdvisoryServices());
        if (extensionAndFieldVisitDataDetails.getWardExtensionOfficer().getId() != null) {
            extensionAndFieldVisitData.setWardExtensionOfficer(em.find(Person.class, extensionAndFieldVisitDataDetails.getWardExtensionOfficer().getId()));
        }

        try {
            em.persist(extensionAndFieldVisitData);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_00_01");
        }

        return extensionAndFieldVisitData.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<ExtensionAndFieldVisitDataDetails> retrieveData() throws MilesException {
        List<ExtensionAndFieldVisitData> extensionAndFieldVisitDatas = new ArrayList<>();
        q = em.createNamedQuery("ExtensionAndFieldVisitData.findAll");
        try {
            extensionAndFieldVisitDatas = q.getResultList();
        } catch (Exception e) {
        }

        return convertExtensionAndFieldVisitDatasToExtensionAndFieldVisitDataDetailsList(extensionAndFieldVisitDatas);
    }

    @Override
    public ExtensionAndFieldVisitDataDetails retrieveData(int id) throws MilesException {
        ExtensionAndFieldVisitData extensionAndFieldVisitData;
        q = em.createNamedQuery("ExtensionAndFieldVisitData.findById");
        q.setParameter("id", id);
        try {
            extensionAndFieldVisitData = (ExtensionAndFieldVisitData) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_00_01");
        }

        return convertExtensionAndFieldVisitDataToExtensionAndFieldVisitDataDetails(extensionAndFieldVisitData);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editData(ExtensionAndFieldVisitDataDetails extensionAndFieldVisitDataDetails) throws MilesException {

        if (extensionAndFieldVisitDataDetails == null) {
            throw new InvalidArgumentException("error_011_01");
        } else if (extensionAndFieldVisitDataDetails.getId() == null) {
            throw new InvalidArgumentException("error_011_03");
        } else if (extensionAndFieldVisitDataDetails.getWardExtensionOfficer() == null) {
            throw new InvalidArgumentException("error_011_02");
        }

        ExtensionAndFieldVisitData extensionAndFieldVisitData = em.find(ExtensionAndFieldVisitData.class, extensionAndFieldVisitDataDetails.getId());
        extensionAndFieldVisitData.setId(extensionAndFieldVisitDataDetails.getId());
        extensionAndFieldVisitData.setFieldVisitsWardLocations(extensionAndFieldVisitDataDetails.getFieldVisitsWardLocations());
        extensionAndFieldVisitData.setNatureOfAdvisoryServices(extensionAndFieldVisitDataDetails.getNatureOfAdvisoryServices());
        extensionAndFieldVisitData.setNumberOfFarmersVisited(extensionAndFieldVisitDataDetails.getNumberOfFarmersVisited());
        extensionAndFieldVisitData.setNumberOfFieldVisitsConducted(extensionAndFieldVisitDataDetails.getNumberOfFieldVisitsConducted());
        extensionAndFieldVisitData.setNumberOfPeopleSeekingOrOfferedAdvisoryServices(extensionAndFieldVisitDataDetails.getNumberOfPeopleSeekingOrOfferedAdvisoryServices());
        if (extensionAndFieldVisitDataDetails.getWardExtensionOfficer().getId() != null) {
            extensionAndFieldVisitData.setWardExtensionOfficer(em.find(Person.class, extensionAndFieldVisitDataDetails.getWardExtensionOfficer().getId()));
        }

        try {
            em.merge(extensionAndFieldVisitData);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_00_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeData(int id) throws MilesException {
        ExtensionAndFieldVisitData extensionAndFieldVisitData = em.find(ExtensionAndFieldVisitData.class, id);
        try {
            em.remove(extensionAndFieldVisitData);
        } catch (Exception e) {
            throw new InvalidStateException("error_00_01");
        }
    }
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private ExtensionAndFieldVisitDataDetails convertExtensionAndFieldVisitDataToExtensionAndFieldVisitDataDetails(ExtensionAndFieldVisitData extensionAndFieldVisitData) {

        ExtensionAndFieldVisitDataDetails extensionAndFieldVisitDataDetails = new ExtensionAndFieldVisitDataDetails(extensionAndFieldVisitData.getId());

        extensionAndFieldVisitDataDetails.setFieldVisitsWardLocations(extensionAndFieldVisitData.getFieldVisitsWardLocations());
        extensionAndFieldVisitDataDetails.setNatureOfAdvisoryServices(extensionAndFieldVisitData.getNatureOfAdvisoryServices());
        extensionAndFieldVisitDataDetails.setNumberOfFarmersVisited(extensionAndFieldVisitData.getNumberOfFarmersVisited());
        extensionAndFieldVisitDataDetails.setNumberOfFieldVisitsConducted(extensionAndFieldVisitData.getNumberOfFieldVisitsConducted());
        extensionAndFieldVisitDataDetails.setWardExtensionOfficer(personService.convertPersonToPersonDetails(extensionAndFieldVisitData.getWardExtensionOfficer()));
        extensionAndFieldVisitDataDetails.setNumberOfPeopleSeekingOrOfferedAdvisoryServices(extensionAndFieldVisitData.getNumberOfPeopleSeekingOrOfferedAdvisoryServices());

        return extensionAndFieldVisitDataDetails;

    }

    private List<ExtensionAndFieldVisitDataDetails> convertExtensionAndFieldVisitDatasToExtensionAndFieldVisitDataDetailsList(List<ExtensionAndFieldVisitData> extensionAndFieldVisitDatas) {

        List<ExtensionAndFieldVisitDataDetails> extensionAndFieldVisitDataDetailsList = new ArrayList<>();
        for (ExtensionAndFieldVisitData extensionAndFieldVisitData : extensionAndFieldVisitDatas) {
            extensionAndFieldVisitDataDetailsList.add(convertExtensionAndFieldVisitDataToExtensionAndFieldVisitDataDetails(extensionAndFieldVisitData));
        }
        return extensionAndFieldVisitDataDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;

}
