/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Contact;
import ke.co.miles.kcep.mis.entities.FarmerGroup;
import ke.co.miles.kcep.mis.entities.Location;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.Sex;
import ke.co.miles.kcep.mis.entities.UserAccount;
import ke.co.miles.kcep.mis.exceptions.AlgorithmException;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.LoginException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.access.AccessRequestsLocal;
import ke.co.miles.kcep.mis.requests.contact.ContactRequestsLocal;
import ke.co.miles.kcep.mis.requests.farmergroup.FarmerGroupRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.role.PersonRoleRequestsLocal;
import ke.co.miles.kcep.mis.requests.useraccount.UserAccountRequestsLocal;
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

        Person person;
        q = em.createNamedQuery("Person.findByNationalId");
        q.setParameter("nationalId", personDetails.getNationalId());
        try {
            person = (Person) q.getSingleResult();
        } catch (Exception e) {
            person = null;
        }
        if (person != null) {
            throw new InvalidStateException("error_001_02");
        }

        person = new Person();
        person.setName(personDetails.getName());
        person.setNationalId(personDetails.getNationalId());
        person.setBusinessName(personDetails.getBusinessName());

        person.setContact(contactService.addContact(personDetails.getContact()));
        person.setLocation(locationService.addLocation(personDetails.getLocation()));

        if (personDetails.getSex().getId() != null) {
            person.setSex(em.find(Sex.class, personDetails.getSex().getId()));
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
    public Map<PersonDetails, PersonRoleDetails> retrievePerson(String username, String password) throws MilesException {
        //Method for retrieving person record from the database

        //Checking validity of details
        if (username == null || username.trim().length() == 0) {
            throw new InvalidArgumentException("error_015_02");
        } else if (username.trim().length() > 150) {
            throw new InvalidArgumentException("error_015_03");
        } else if (password == null || password.trim().length() == 0) {
            throw new InvalidArgumentException("error_015_04");
        } else if (password.trim().length() > 150) {
            throw new InvalidArgumentException("error_015_05");
        }

        //Get the hashed password
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            throw new AlgorithmException("error_0016_01");
        }

        String hashedPassword = accessService.generateSHAPassword(messageDigest, password);

        //Retrieve the user account record from the database
        UserAccount userAccount;
        try {
            userAccount = userAccountService.retrieveUserAccount(username, hashedPassword);
        } catch (InvalidArgumentException | LoginException e) {
            throw new LoginException("error_000_01");
        }

        //Creating and valuating a map of person details to their user group
        Map<PersonDetails, PersonRoleDetails> personToPersonRoleMap = new HashMap<>();
        personToPersonRoleMap.put(convertPersonToPersonDetails(userAccount.getPerson()),
                personRoleService.convertPersonRoleToPersonRoleDetails(userAccount.getPersonRole()));

        //Returning the details list of person record
        return personToPersonRoleMap;
    }

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
        } else if (personDetails.getId() == null) {
            throw new InvalidStateException("error_001_03");
        }

        Person person;
        q = em.createNamedQuery("Person.findByNationalId");
        q.setParameter("nationalId", personDetails.getNationalId());
        try {
            person = (Person) q.getSingleResult();
        } catch (Exception e) {
            person = null;
        }
        if (person != null) {
            if (!person.getId().equals(personDetails.getId())) {
                throw new InvalidStateException("error_001_02");
            }
        }

        contactService.editContact(personDetails.getContact());
        locationService.editLocation(personDetails.getLocation());

        person = em.find(Person.class, personDetails.getId());
        person.setId(personDetails.getId());
        person.setName(personDetails.getName());
        person.setNationalId(personDetails.getNationalId());
        person.setBusinessName(personDetails.getBusinessName());

        person.setContact(em.find(Contact.class, personDetails.getContact().getId()));
        person.setLocation(em.find(Location.class, personDetails.getLocation().getId()));

        if (personDetails.getSex().getId() != null) {
            person.setSex(em.find(Sex.class, personDetails.getSex().getId()));

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
    @Override
    public PersonDetails convertPersonToPersonDetails(Person person) {

        ContactDetails contactDetails = null;
        if (person.getContact().getId() != null) {
            contactDetails = contactService.convertContactToContactDetails(person.getContact());
        }

        LocationDetails locationDetails = null;
        if (person.getLocation().getId() != null) {
            locationDetails = locationService.convertLocationToLocationDetails(person.getLocation());
        }

        FarmerGroupDetails farmerGroupDetails = null;
        if (person.getFarmerGroup().getId() != null) {
            farmerGroupDetails = farmerGroupService.convertFarmerGroupToFarmerGroupDetails(person.getFarmerGroup());
        }

        PersonDetails personDetails = new PersonDetails(person.getId());
        personDetails.setBusinessName(person.getBusinessName());
        personDetails.setContact(contactDetails);
        personDetails.setLocation(locationDetails);
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
    AccessRequestsLocal accessService;
    @EJB
    LocationRequestsLocal locationService;
    @EJB
    PersonRoleRequestsLocal personRoleService;
    @EJB
    FarmerGroupRequestsLocal farmerGroupService;
    @EJB
    UserAccountRequestsLocal userAccountService;

}
