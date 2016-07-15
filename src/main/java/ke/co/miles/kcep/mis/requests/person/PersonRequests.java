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
import ke.co.miles.kcep.mis.entities.County;
import ke.co.miles.kcep.mis.entities.FarmerGroup;
import ke.co.miles.kcep.mis.entities.FarmerSubGroup;
import ke.co.miles.kcep.mis.entities.Location;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.Sex;
import ke.co.miles.kcep.mis.entities.UserAccount;
import ke.co.miles.kcep.mis.exceptions.AlgorithmException;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.access.AccessRequestsLocal;
import ke.co.miles.kcep.mis.requests.contact.ContactRequestsLocal;
import ke.co.miles.kcep.mis.requests.farmer.group.FarmerGroupRequestsLocal;
import ke.co.miles.kcep.mis.requests.farmer.subgroup.FarmerSubGroupRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
import ke.co.miles.kcep.mis.requests.useraccount.UserAccountRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ContactDetails;
import ke.co.miles.kcep.mis.utilities.FarmerGroupDetails;
import ke.co.miles.kcep.mis.utilities.FarmerSubGroupDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SexDetail;
import ke.co.miles.kcep.mis.utilities.UserAccountDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PersonRequests extends EntityRequests implements PersonRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">  
    @Override
    public int addPerson(PersonDetails personDetails, PersonRoleDetail personRoleDetail) throws MilesException {

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
            if (person.getNationalId() != null) {
                throw new InvalidStateException("error_001_02");
            }
        }

        person = new Person();
        person.setName(personDetails.getName());
        person.setNationalId(personDetails.getNationalId());
        person.setDateOfBirth(personDetails.getDateOfBirth());
        person.setBusinessName(personDetails.getBusinessName());

        person.setContact(contactService.addContact(personDetails.getContact()));
        person.setLocation(locationService.addLocation(personDetails.getLocation()));

        if (personDetails.getSex() != null) {
            person.setSex(em.find(Sex.class, personDetails.getSex().getId()));
        }
        if (personDetails.getFarmerGroup() != null) {
            person.setFarmerGroup(em.find(FarmerGroup.class, personDetails.getFarmerGroup().getId()));
        }
        if (personDetails.getFarmerSubGroup() != null) {
            person.setFarmerSubGroup(em.find(FarmerSubGroup.class, personDetails.getFarmerSubGroup().getId()));
        }

        try {
            em.persist(person);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        UserAccountDetails userAccountDetails = new UserAccountDetails();
        userAccountDetails.setPersonRole(personRoleDetail);
        userAccountDetails.setPassword(personDetails.getNationalId());
        userAccountDetails.setPerson(convertPersonToPersonDetails(person));
        userAccountDetails.setUsername(person.getContact().getEmail().toLowerCase());

        try {
            userAccountService.addUserAccount(userAccountDetails);
        } catch (Exception e) {
            throw e;
        }

        return person.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @SuppressWarnings("unchecked")
    @Override
    public HashMap<String, Integer> countAllPeople() throws MilesException {

        List<Person> female = new ArrayList<>();
        q = em.createNamedQuery("Person.findBySexId");
        q.setParameter("sexId", SexDetail.FEMALE.getId());
        try {
            female = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<Person> male = new ArrayList<>();
        q = em.createNamedQuery("Person.findBySexId");
        q.setParameter("sexId", SexDetail.MALE.getId());
        try {
            male = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("Female", female.size());
        countMap.put("Male", male.size());
        countMap.put("Total", female.size() + male.size());

        return countMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public HashMap<String, Integer> countPeople(PersonRoleDetail personRoleDetail) throws MilesException {

        List<UserAccount> female = new ArrayList<>();
        q = em.createNamedQuery("UserAccount.findBySexAndPersonRoleId");
        q.setParameter("personRoleId", personRoleDetail.getId());
        q.setParameter("sexId", SexDetail.FEMALE.getId());
        try {
            female = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<UserAccount> male = new ArrayList<>();
        q = em.createNamedQuery("UserAccount.findBySexAndPersonRoleId");
        q.setParameter("personRoleId", personRoleDetail.getId());
        q.setParameter("sexId", SexDetail.MALE.getId());
        try {
            male = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("Female", female.size());
        countMap.put("Male", male.size());
        countMap.put("Total", female.size() + male.size());

        return countMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonDetails> retrieveRegionPeople(short regionId) throws MilesException {

        List<County> counties = new ArrayList<>();
        q = em.createNamedQuery("County.findByReqionId");
        q.setParameter("regionId", regionId);
        try {
            counties = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<PersonDetails> peopleDetailsList = new ArrayList<>();

        q = em.createNamedQuery("Person.findByCountyId");
        for (County county : counties) {
            q.setParameter("countyId", county.getId());
            try {
                peopleDetailsList.addAll(convertPeopleToPersonDetailsList(q.getResultList()));
            } catch (Exception e) {
                throw new InvalidStateException("error_000_01");
            }
        }

        return peopleDetailsList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonDetails> retrieveCountyPeople(short countyId) throws MilesException {
        List<PersonDetails> peopleDetailsList = new ArrayList<>();

        q = em.createNamedQuery("Person.findByCountyId");
        q.setParameter("countyId", countyId);
        try {
            peopleDetailsList = convertPeopleToPersonDetailsList(q.getResultList());
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return peopleDetailsList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonDetails> retrieveWardPeople(short wardId) throws MilesException {
        List<PersonDetails> peopleDetailsList = new ArrayList<>();

        q = em.createNamedQuery("Person.findByWardId");
        q.setParameter("wardId", wardId);
        try {
            peopleDetailsList = convertPeopleToPersonDetailsList(q.getResultList());
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return peopleDetailsList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonDetails> retrieveSubCountyPeople(short subCountyId) throws MilesException {
        List<PersonDetails> peopleDetailsList = new ArrayList<>();

        q = em.createNamedQuery("Person.findBySubCountyId");
        q.setParameter("subCountyId", subCountyId);
        try {
            peopleDetailsList = convertPeopleToPersonDetailsList(q.getResultList());
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return peopleDetailsList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonDetails> retrieveSubCountyFarmers(short subCountyId) throws MilesException {
        List<PersonDetails> peopleDetailsList = null;
        List<Person> people;

        q = em.createNamedQuery("Person.findBySubCountyId");
        q.setParameter("subCountyId", subCountyId);
        try {
            people = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        if (people != null && !people.isEmpty()) {
            peopleDetailsList = convertPeopleToPersonDetailsList(userAccountService.filterPeople(people, PersonRoleDetail.FARMER));
        }

        return peopleDetailsList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonDetails> retrieveKalroPeople() throws MilesException {
        List<PersonDetails> peopleDetailsList = null;
        List<Person> people;

        q = em.createNamedQuery("Person.findAll");
        try {
            people = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        if (people != null && !people.isEmpty()) {
            peopleDetailsList = convertPeopleToPersonDetailsList(userAccountService.filterPeople(people, PersonRoleDetail.KALRO_OFFICER));
        }

        return peopleDetailsList;
    }

    @Override
    public Map<PersonDetails, PersonRoleDetail> retrievePerson(String username, String password) throws MilesException {
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
        UserAccountDetails userAccountDetails;
        try {
            userAccountDetails = userAccountService.retrieveUserAccount(username, hashedPassword);
        } catch (MilesException e) {
            throw e;
        }

        //Creating and valuating a map of person details to their user group
        Map<PersonDetails, PersonRoleDetail> personToPersonRoleMap = new HashMap<>();
        personToPersonRoleMap.put(userAccountDetails.getPerson(), userAccountDetails.getPersonRole());

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
    @SuppressWarnings("unchecked")
    public List<PersonDetails> retrievePeople() throws MilesException {

        q = em.createNamedQuery("UserAccount.findAll");
        List<UserAccount> userAccounts;
        try {
            userAccounts = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertUserAccountsToPeople(userAccounts);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editPerson(PersonDetails personDetails, PersonRoleDetail personRoleDetail) throws MilesException {

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
            if (person.getNationalId() != null && !person.getId().equals(personDetails.getId())) {
                throw new InvalidStateException("error_001_02");
            }
        }

        contactService.editContact(personDetails.getContact());
        locationService.editLocation(personDetails.getLocation());

        person = em.find(Person.class, personDetails.getId());
        person.setId(personDetails.getId());
        person.setName(personDetails.getName());
        person.setNationalId(personDetails.getNationalId());
        person.setDateOfBirth(personDetails.getDateOfBirth());
        person.setBusinessName(personDetails.getBusinessName());

        person.setContact(em.find(Contact.class, personDetails.getContact().getId()));
        person.setLocation(em.find(Location.class, personDetails.getLocation().getId()));

        if (personDetails.getSex() != null) {
            person.setSex(em.find(Sex.class, personDetails.getSex().getId()));

        }
        if (personDetails.getFarmerSubGroup() != null) {
            person.setFarmerSubGroup(em.find(FarmerSubGroup.class, personDetails.getFarmerSubGroup().getId()));
        }

        try {
            em.merge(person);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        UserAccountDetails userAccountDetails = new UserAccountDetails();
        userAccountDetails.setId(personDetails.getId());
        userAccountDetails.setPersonRole(personRoleDetail);
        userAccountDetails.setPassword(personDetails.getNationalId());
        userAccountDetails.setPerson(convertPersonToPersonDetails(person));
        userAccountDetails.setUsername(person.getContact().getEmail().toLowerCase());

        try {
            userAccountService.editUserAccount(userAccountDetails);
        } catch (Exception e) {
            throw e;
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
        if (person.getContact() != null) {
            contactDetails = contactService.convertContactToContactDetails(person.getContact());
        }

        LocationDetails locationDetails = null;
        if (person.getLocation() != null) {
            locationDetails = locationService.convertLocationToLocationDetails(person.getLocation());
        }

        FarmerGroupDetails farmerGroupDetails = null;
        if (person.getFarmerGroup() != null) {
            farmerGroupDetails = farmerGroupService.convertFarmerGroupToFarmerGroupDetails(person.getFarmerGroup());
        }

        FarmerSubGroupDetails farmerSubGroupDetails = null;
        if (person.getFarmerSubGroup() != null) {
            farmerSubGroupDetails = farmerSubGroupService.convertFarmerSubGroupToFarmerSubGroupDetails(person.getFarmerSubGroup());
        }

        PersonDetails personDetails;
        try {
            personDetails = new PersonDetails(person.getId());
        } catch (Exception e) {
            return null;
        }
        personDetails.setContact(contactDetails);
        personDetails.setName(person.getName());
        personDetails.setLocation(locationDetails);
        personDetails.setPlotSize(person.getPlotSize());
        personDetails.setNationalId(person.getNationalId());
        personDetails.setFarmerGroup(farmerGroupDetails);
        personDetails.setDateOfBirth(person.getDateOfBirth());
        personDetails.setFarmerSubGroup(farmerSubGroupDetails);
        personDetails.setBusinessName(person.getBusinessName());
        try {
            personDetails.setSex(SexDetail.getSexDetail(person.getSex().getId()));
        } catch (Exception e) {
        }

        return personDetails;

    }

    private List<PersonDetails> convertPeopleToPersonDetailsList(List<Person> people) {

        int maleCount, femaleCount;
        List<PersonDetails> personDetailsList = new ArrayList<>();
        for (Person person : people) {

            PersonDetails personDetails;
            personDetails = convertPersonToPersonDetails(person);
            personDetailsList.add(personDetails);

            if (personDetails.getSex() == SexDetail.FEMALE) {

            }
        }

        return personDetailsList;

    }

    private List<PersonDetails> convertUserAccountsToPeople(List<UserAccount> userAccounts) {
        int maleCount, femaleCount;
        List<PersonDetails> personDetailsList = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {

            PersonDetails personDetails;
            personDetails = convertPersonToPersonDetails(userAccount.getPerson());
            personDetails.setPersonRoleId(userAccount.getPersonRole().getId());
            personDetailsList.add(personDetails);

            if (personDetails.getSex() == SexDetail.FEMALE) {

            }
        }

        return personDetailsList;

    }
//</editor-fold>
    @EJB
    private ContactRequestsLocal contactService;
    @EJB
    private AccessRequestsLocal accessService;
    @EJB
    private LocationRequestsLocal locationService;
    @EJB
    private FarmerGroupRequestsLocal farmerGroupService;
    @EJB
    private UserAccountRequestsLocal userAccountService;
    @EJB
    private FarmerSubGroupRequestsLocal farmerSubGroupService;

}
