/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Contact;
import ke.co.miles.kcep.mis.entities.FarmerGroup;
import ke.co.miles.kcep.mis.entities.Location;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.entities.Sex;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.contact.ContactRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PersonDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PersonRequests extends EntityRequests implements PersonRequestsLocal {

    @Override
    public PersonDetails retrievePerson(int person) throws MilesException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PersonDetails> retrievePeople() throws MilesException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePerson(int person) throws MilesException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//<editor-fold defaultstate="collapsed" desc="Create">  
    @Override
    public int addPerson(PersonDetails personDetails) throws MilesException {

        if (personDetails == null) {
            throw new InvalidArgumentException("error_001_01");
        }

        Person person = new Person();
        person.setName(personDetails.getName());
        person.setNationalId(personDetails.getNationalId());
        person.setBusinessName(personDetails.getBusinessName());

        person.setContact(contactService.addContact(personDetails.getContact()));
        person.setLocation(locationService.addLocation(personDetails.getLocation()));

        if (personDetails.getSex().getId() != null) {
            person.setSex(em.find(Sex.class, personDetails.getSex().getId()));
        }
        if (personDetails.getPersonRole().getId() != null) {
            person.setPersonRole(em.find(PersonRole.class, personDetails.getPersonRole().getId()));
        }
        if (personDetails.getFarmerGroup().getId() != null) {
            person.setFarmerGroup(em.find(FarmerGroup.class, personDetails.getFarmerGroup().getId()));
        }
        try {
            em.persist(person);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return person.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPerson(PersonDetails personDetails) throws MilesException {

        if (personDetails == null) {
            throw new InvalidArgumentException("error_001_01");
        }

        contactService.editContact(personDetails.getContact());
        locationService.editLocation(personDetails.getLocation());

        Person person = em.find(Person.class, personDetails.getId());
        person.setId(personDetails.getId());
        person.setName(personDetails.getName());
        person.setNationalId(personDetails.getNationalId());
        person.setBusinessName(personDetails.getBusinessName());

        person.setContact(em.find(Contact.class, personDetails.getContact().getId()));
        person.setLocation(em.find(Location.class, personDetails.getLocation().getId()));

        if (personDetails.getSex().getId() != null) {
            person.setSex(em.find(Sex.class, personDetails.getSex().getId()));
        }
        if (personDetails.getPersonRole().getId() != null) {
            person.setPersonRole(em.find(PersonRole.class, personDetails.getPersonRole().getId()));
        }
        if (personDetails.getFarmerGroup().getId() != null) {
            person.setFarmerGroup(em.find(FarmerGroup.class, personDetails.getFarmerGroup().getId()));
        }

        try {
            em.merge(person);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">
//</editor-fold>

    @EJB
    ContactRequestsLocal contactService;
    @EJB
    LocationRequestsLocal locationService;
}
