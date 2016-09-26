/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.workshops;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.ValidationWorkshops;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ValidationWorkshopsDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ValidationWorkshopsRequests extends EntityRequests implements ValidationWorkshopsRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addValidationWorkshops(ValidationWorkshopsDetails validationWorkshopsDetails) throws MilesException {

        if (validationWorkshopsDetails == null) {
            throw new InvalidArgumentException("error_025_01");
        }

        ValidationWorkshops validationWorkshops = new ValidationWorkshops();
        validationWorkshops.setAttendanceSheet(validationWorkshopsDetails.getAttendanceSheet());
        validationWorkshops.setNoInEasternRegion(validationWorkshopsDetails.getNoInEasternRegion());
        validationWorkshops.setNoInEasternRegion(validationWorkshopsDetails.getNoInEasternRegion());
        try {
            validationWorkshops.setKalroOfficer(em.getReference(Person.class, validationWorkshopsDetails.getKalroOfficer().getId()));
        } catch (Exception e) {
            validationWorkshops.setKalroOfficer(null);
        }

        try {
            em.persist(validationWorkshops);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return validationWorkshops.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<ValidationWorkshopsDetails> retrieveTechnologies() throws MilesException {
        List<ValidationWorkshops> technologies = new ArrayList<>();
        setQ(em.createNamedQuery("ValidationWorkshops.findAll"));
        try {
            technologies = q.getResultList();
        } catch (Exception e) {
        }

        return convertTechnologiesToValidationWorkshopsDetailsList(technologies);
    }

    @Override
    public ValidationWorkshopsDetails retrieveValidationWorkshops(int id) throws MilesException {
        ValidationWorkshops validationWorkshops;
        setQ(em.createNamedQuery("ValidationWorkshops.findById"));
        q.setParameter("id", id);
        try {
            validationWorkshops = (ValidationWorkshops) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertValidationWorkshopsToValidationWorkshopsDetails(validationWorkshops);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editValidationWorkshops(ValidationWorkshopsDetails validationWorkshopsDetails) throws MilesException {

        if (validationWorkshopsDetails == null) {
            throw new InvalidArgumentException("error_025_01");
        } else if (validationWorkshopsDetails.getId() == null) {
            throw new InvalidArgumentException("error_025_02");
        }

        ValidationWorkshops validationWorkshops = em.find(ValidationWorkshops.class, validationWorkshopsDetails.getId());
        validationWorkshops.setId(validationWorkshopsDetails.getId());
        validationWorkshops.setAttendanceSheet(validationWorkshopsDetails.getAttendanceSheet());
        validationWorkshops.setNoInEasternRegion(validationWorkshopsDetails.getNoInEasternRegion());
        validationWorkshops.setNoInEasternRegion(validationWorkshopsDetails.getNoInEasternRegion());
        try {
            validationWorkshops.setKalroOfficer(em.getReference(Person.class, validationWorkshopsDetails.getKalroOfficer().getId()));
        } catch (Exception e) {
            validationWorkshops.setKalroOfficer(null);
        }

        try {
            em.merge(validationWorkshops);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeValidationWorkshops(int id) throws MilesException {
        ValidationWorkshops validationWorkshops = em.find(ValidationWorkshops.class, id);
        try {
            em.remove(validationWorkshops);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private ValidationWorkshopsDetails convertValidationWorkshopsToValidationWorkshopsDetails(ValidationWorkshops validationWorkshops) {

        ValidationWorkshopsDetails validationWorkshopsDetails = new ValidationWorkshopsDetails(validationWorkshops.getId());
        validationWorkshopsDetails.setAttendanceSheet(validationWorkshops.getAttendanceSheet());
        validationWorkshopsDetails.setNoInEasternRegion(validationWorkshops.getNoInEasternRegion());
        validationWorkshopsDetails.setNoInEasternRegion(validationWorkshops.getNoInEasternRegion());
        validationWorkshopsDetails.setKalroOfficer(personService.convertPersonToPersonDetails(validationWorkshops.getKalroOfficer()));

        return validationWorkshopsDetails;

    }

    private List<ValidationWorkshopsDetails> convertTechnologiesToValidationWorkshopsDetailsList(List<ValidationWorkshops> technologies) {

        List<ValidationWorkshopsDetails> validationWorkshopsDetailsList = new ArrayList<>();
        for (ValidationWorkshops validationWorkshops : technologies) {
            validationWorkshopsDetailsList.add(convertValidationWorkshopsToValidationWorkshopsDetails(validationWorkshops));
        }

        return validationWorkshopsDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
}
