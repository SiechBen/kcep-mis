/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person;

import java.util.ArrayList;
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
import ke.co.miles.kcep.mis.utilities.ContactDetails;
import ke.co.miles.kcep.mis.utilities.FarmerGroupDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetails;
import ke.co.miles.kcep.mis.utilities.SexDetail;

/**
 *
 * @author siech
 */
@Stateless
public class PersonRequests extends EntityRequests implements PersonRequestsLocal {

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

    @Override
    public PersonDetails retrievePerson(int id) throws MilesException {

        q = em.createNamedQuery("Person.findById");
        q.setParameter("id", id);
        Person person;
        try {
            person = (Person) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPersonToPersonDetails(person);

    }

    @Override
    public List<PersonDetails> retrievePeople() throws MilesException {

        q = em.createNamedQuery("Person.findAll");
        List<Person> people;
        try {
            people = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPeopleToPersonDetailsList(people);
    }

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

        person
                .setContact(em.find(Contact.class, personDetails.getContact().getId()));
        person
                .setLocation(em.find(Location.class, personDetails.getLocation().getId()));

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

    @Override
    public void removePerson(int id) throws MilesException {

        Person person = em.find(Person.class, id);
        contactService.removeContact(person.getContact().getId());
        try {
            em.remove(person);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert"> 
    private PersonDetails convertPersonToPersonDetails(Person person) {

        ContactDetails contactDetails = null;
        if (person.getContact().getId() != null) {
            contactDetails = new ContactDetails(person.getContact().getId());
            contactDetails.setEmail(person.getContact().getEmail());
            contactDetails.setPhone(person.getContact().getPhone());
            contactDetails.setPostalAddress(person.getContact().getPostalAddress());
        }

        LocationDetails locationDetails = null;
        if (person.getLocation().getId() != null) {
            locationDetails = new LocationDetails(person.getLocation().getId());
            locationDetails.setWard(person.getLocation().getWard());
            locationDetails.setCounty(person.getLocation().getCounty());
            locationDetails.setLatitude(person.getLocation().getLatitude());
            locationDetails.setLongitude(person.getLocation().getLongitude());
            locationDetails.setSubCounty(person.getLocation().getSubCounty());
        }

        PersonRoleDetails personRoleDetails = null;
        if (person.getPersonRole().getId() != null) {
            personRoleDetails = new PersonRoleDetails(person.getPersonRole().getId());
            personRoleDetails.setPersonRole(person.getPersonRole().getPersonRole());
        }

        FarmerGroupDetails farmerGroupDetails = null;
        if (person.getContact().getId() != null) {
            farmerGroupDetails = new FarmerGroupDetails(person.getFarmerGroup().getId());
            farmerGroupDetails.setName(person.getFarmerGroup().getName());
        }

        PersonDetails personDetails = new PersonDetails(person.getId());
        personDetails.setBusinessName(person.getBusinessName());
        personDetails.setContact(contactDetails);
        personDetails.setLocation(locationDetails);
        personDetails.setPersonRole(personRoleDetails);
        personDetails.setFarmerGroup(farmerGroupDetails);
        personDetails.setNationalId(person.getNationalId());
        personDetails.setSex(SexDetail.getSexDetail(person.getSex().getId()));

        return personDetails;

    }

    private List<PersonDetails> convertPeopleToPersonDetailsList(List<Person> people) {
        List<PersonDetails> personDetailsList = new ArrayList<>();
        people.stream().forEach((person) -> {
            personDetailsList.add(convertPersonToPersonDetails(person));
        });

        return personDetailsList;

    }

//</editor-fold>
    @EJB
    ContactRequestsLocal contactService;
    @EJB
    LocationRequestsLocal locationService;

}
