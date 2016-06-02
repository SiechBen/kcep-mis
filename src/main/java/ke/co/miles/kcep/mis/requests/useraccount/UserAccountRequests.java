/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.useraccount;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.entities.UserAccount;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.LoginException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.role.PersonRoleRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetails;
import ke.co.miles.kcep.mis.utilities.UserAccountDetails;

/**
 *
 * @author Ben Siech
 */
@Stateless
public class UserAccountRequests extends EntityRequests implements UserAccountRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public Integer addUserAccount(UserAccountDetails details) throws MilesException {
        //Method for adding a faculty record to the database

        //Checking validity of details
        if (details == null) {
            throw new InvalidArgumentException("error_001_01");
        } else if (details.getUsername() == null || details.getUsername().trim().length() == 0) {
            throw new InvalidArgumentException("error_001_02", "The", null);
        } else if (details.getUsername().trim().length() > 20) {
            throw new InvalidArgumentException("error_001_03");
        } else if (details.getPassword() == null || details.getPassword().trim().length() == 0) {
            throw new InvalidArgumentException("error_001_04", "The", null);
        } else if (details.getPassword().trim().length() > 150) {
            throw new InvalidArgumentException("error_001_05");
        } else if (details.getPerson() == null) {
            throw new InvalidArgumentException("error_001_06");
        } else if (details.getPersonRole() == null) {
            throw new InvalidArgumentException("error_001_07");
        }

        //Checking if the username is unique to a faculty
        q = em.createNamedQuery("UserAccount.findByUsername");
        q.setParameter("username", details.getUsername());
        q.setParameter("active", Boolean.TRUE);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "User account name is available for use");
            userAccount = null;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "An error occurred during record retrieval", e);
            throw new EJBException("error_000_01");
        }
        if (userAccount != null) {
            LOGGER.log(Level.INFO, "User account name exists already in the database");
            throw new EJBException("error_001_08");
        }

        //Creating a container to hold faculty record
        LOGGER.log(Level.INFO, "Creating a container to hold faculty record");
        userAccount = new UserAccount();
        userAccount.setUsername(details.getUsername());
        userAccount.setPassword(details.getPassword());
        userAccount.setPerson(em.find(Person.class, details.getPerson().getId()));
        userAccount.setPersonRole(em.find(PersonRole.class, details.getPersonRole().getId()));

        //Adding a faculty record to the database
        LOGGER.log(Level.INFO, "Adding a faculty record to the database");
        try {
            em.persist(userAccount);
            em.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during record creation", e);
            throw new EJBException("error_000_01");
        }

        //Returning the unique identifier of the new record added
        LOGGER.log(Level.INFO, "Returning the unique identifier of the new record added");
        return userAccount.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public UserAccount retrieveUserAccount(String username, String hashedPassword) throws MilesException {
        //Method for retrieving user account record from the database
        LOGGER.log(Level.INFO, "Entered the method for retrieving user account record from the database");

        //Checking validity of details
        LOGGER.log(Level.INFO, "Checking validity of the institution unique identifier passed in");
        if (username == null || username.trim().length() == 0) {
            LOGGER.log(Level.INFO, "The username is null");
            throw new InvalidArgumentException("error_001_02", "Your", null);
        } else if (username.trim().length() > 20) {
            LOGGER.log(Level.INFO, "The username is longer than 20 characters");
            throw new InvalidArgumentException("error_001_03");
        } else if (hashedPassword == null || hashedPassword.trim().length() == 0) {
            LOGGER.log(Level.INFO, "The password is null");
            throw new InvalidArgumentException("error_001_04", "Your", null);
        } else if (hashedPassword.trim().length() > 150) {
            LOGGER.log(Level.INFO, "The password is longer than 150 characters");
            throw new InvalidArgumentException("error_001_05");
        }

        //Retrieving person record from the database
        LOGGER.log(Level.INFO, "Retrieving person record from the database");
        q = em.createNamedQuery("UserAccount.findByUsernameAndPassword");
        q.setParameter("username", username.toUpperCase());
        q.setParameter("password", hashedPassword);
        q.setParameter("active", Boolean.TRUE);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.SEVERE, "No such user account record found");
            throw new LoginException("error_001_09");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Invalid login credentials");
            throw new EJBException("error_000_01");
        }

        //Returning the details list of user account record
        LOGGER.log(Level.INFO, "Returning the details list of user account record");
        return userAccount;
    }

    @Override
    public UserAccountDetails retrieveUserAccount(String referenceNumber) throws MilesException, InvalidStateException {
        //Method for retrieving user account record from the database
        LOGGER.log(Level.INFO, "Entered the method for retrieving user account record from the database");

        //Checking validity of details
        LOGGER.log(Level.INFO, "Checking validity of the institution unique identifier passed in");
        if (referenceNumber == null || referenceNumber.trim().length() == 0) {
            LOGGER.log(Level.INFO, "The reference number is null");
            throw new InvalidArgumentException("error_001_10");
        } else if (referenceNumber.trim().length() > 20) {
            LOGGER.log(Level.INFO, "The reference number is longer than 20 characters");
            throw new InvalidArgumentException("error_001_11");
        }

        //Retrieving person record from the database
        LOGGER.log(Level.INFO, "Retrieving person record from the database");
        q = em.createNamedQuery("UserAccount.findByUsername");
        q.setParameter("username", referenceNumber.toUpperCase());
        q.setParameter("active", Boolean.TRUE);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.SEVERE, "An error occurred during record retrieval");
            throw new InvalidStateException("error_001_12");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while retrieving user account");
            throw new EJBException("error_000_01");
        }

        //Returning the details of user account record
        LOGGER.log(Level.INFO, "Returning the details of user account record");
        return convertUserAccountToUserAccountDetails(userAccount);
    }

    @Override
    public UserAccountDetails retrieveUserAccountByPersonId(Integer personId) throws MilesException, InvalidStateException {
        //Method for retrieving user account record from the database
        LOGGER.log(Level.INFO, "Entered the method for retrieving user account record from the database");

        //Checking validity of details
        LOGGER.log(Level.INFO, "Checking validity of the institution unique identifier passed in");
        if (personId == null) {
            LOGGER.log(Level.INFO, "The person unique identifier is null");
            throw new InvalidArgumentException("error_001_13");
        }

        //Retrieving person record from the database
        LOGGER.log(Level.INFO, "Retrieving person record from the database");
        q = em.createNamedQuery("UserAccount.findByPersonId");
        q.setParameter("personId", personId);
        q.setParameter("active", Boolean.TRUE);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.SEVERE, "An error occurred during record retrieval");
            throw new InvalidStateException("error_001_14");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while the retrieving user account");
            throw new EJBException("error_000_01");
        }

        //Returning the details of user account record
        LOGGER.log(Level.INFO, "Returning the details of user account record");
        return convertUserAccountToUserAccountDetails(userAccount);
    }

    @Override
    public List<UserAccountDetails> retrieveUserAccounts() throws MilesException, InvalidStateException {
        //Method for retrieving user account records from the database
        LOGGER.log(Level.INFO, "Entered the method for retrieving user account records from the database");

        //Retrieving user account records from the database
        LOGGER.log(Level.INFO, "Retrieving user account records from the database");
        q = em.createNamedQuery("UserAccount.findAll");
        q.setParameter("active", Boolean.TRUE);
        List<UserAccount> userAccounts = new ArrayList<>();
        try {
            userAccounts = q.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during record retrieval", e);
            throw new EJBException("error_000_01");
        }

        //Returning the details list of user account records
        LOGGER.log(Level.INFO, "Returning the details list of user account records");
        return convertUserAccountsToUserAccountDetailsList(userAccounts);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editUserAccount(UserAccountDetails details) throws MilesException, InvalidStateException {
        //Method for editing a faculty record in the database
        LOGGER.log(Level.INFO, "Entered the method for editing a faculty record in the database");

        //Checking validity of details
        LOGGER.log(Level.INFO, "Checking validity of the details passed in");
        if (details == null) {
            LOGGER.log(Level.INFO, "The details are null");
            throw new InvalidArgumentException("error_001_01");
        } else if (details.getId() == null) {
            LOGGER.log(Level.INFO, "The faculty's unique identifier is null");
            throw new InvalidArgumentException("error_001_15");
        } else if (details.getUsername() == null || details.getUsername().trim().length() == 0) {
            LOGGER.log(Level.INFO, "The username is null");
            throw new InvalidArgumentException("error_001_02", "The", null);
        } else if (details.getUsername().trim().length() > 20) {
            LOGGER.log(Level.INFO, "The username is longer than 20 characters");
            throw new InvalidArgumentException("error_001_03");
        } else if (details.getPassword() == null || details.getPassword().trim().length() == 0) {
            LOGGER.log(Level.INFO, "The password is null");
            throw new InvalidArgumentException("error_001_04", "The", null);
        } else if (details.getPassword().trim().length() > 150) {
            LOGGER.log(Level.INFO, "The password is longer than 150 characters");
            throw new InvalidArgumentException("error_001_05");
        } else if (details.getPerson() == null) {
            LOGGER.log(Level.INFO, "The account owner is null");
            throw new InvalidArgumentException("error_001_06");
        } else if (details.getPersonRole() == null) {
            LOGGER.log(Level.INFO, "The account's user group is null");
            throw new InvalidArgumentException("error_001_07");
        }

        //Checking if the username is unique to a faculty
        LOGGER.log(Level.INFO, "Checking if the username is unique");
        q = em.createNamedQuery("UserAccount.findByUsername");
        q.setParameter("username", details.getUsername());
        q.setParameter("active", Boolean.TRUE);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "User account name is available for use");
            userAccount = null;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "An error occurred during record retrieval", e);
            throw new EJBException("error_000_01");
        }
        if (userAccount != null) {
            if (!userAccount.getId().equals(details.getId())) {
                LOGGER.log(Level.INFO, "User account name exists already in the database for this faculty");
                throw new EJBException("error_001_08");
            }
        }

        //Creating a container to hold school record
        LOGGER.log(Level.INFO, "Creating a container to hold faculty record");
        userAccount = em.find(UserAccount.class, details.getId());
        userAccount.setId(details.getId());
        userAccount.setUsername(details.getUsername().toUpperCase());
        userAccount.setPassword(details.getPassword());
        userAccount.setPerson(em.find(Person.class, details.getPerson().getId()));
        userAccount.setPersonRole(em.find(PersonRole.class, details.getPersonRole().getId()));

        //Editing a faculty record in the database
        LOGGER.log(Level.INFO, "Editing a faculty record in the database");
        try {
            em.merge(userAccount);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during record update", e);
            throw new EJBException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeUserAccount(Integer personId) throws MilesException, InvalidStateException {
        //Method for removing a faculty record from the database
        LOGGER.log(Level.INFO, "Entered the method for removing a faculty record from the database");

        //Checking validity of details
        LOGGER.log(Level.INFO, "Checking validity of the unique identifier passed in");
        if (personId == null) {
            LOGGER.log(Level.INFO, "The unique identifier of the person is null");
            throw new InvalidArgumentException("error_001_13");
        }

        //Get the user account record to be removed
        LOGGER.log(Level.INFO, "Getting the user account record to be removed");
        q = em.createNamedQuery("UserAccount.findByPersonId");
        q.setParameter("personId", personId);
        q.setParameter("active", Boolean.TRUE);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during record retrieval", e);
            throw new InvalidStateException("error_000_01");
        }

        //Deactivate the user account record in the database
        try {
            em.merge(userAccount);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during record removal", e);
            throw new InvalidStateException("error_000_01");
        }

//        //Removing a faculty record from the database
//        LOGGER.log(Level.INFO, "Removing a faculty record from the database");
//         q.setParameter("active", Boolean.TRUE);
//         userAccount = em.find(UserAccount.class, id);
//        try {
//            em.remove(userAccount);
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An error occurred during record removal", e);
//            throw new InvalidStateException("error_000_01");
//        }
    }
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private List<UserAccountDetails> convertUserAccountsToUserAccountDetailsList(List<UserAccount> userAccounts) {
        //Entered method for converting user accounts list to faculty details list
        LOGGER.log(Level.FINE, "Entered method for converting user accounts list to faculty details list");

        //Convert list of user accounts to faculty details list
        LOGGER.log(Level.FINE, "Convert list of user accounts to faculty details list");
        List<UserAccountDetails> details = new ArrayList<>();
        userAccounts.stream().forEach((userAccount) -> {
            details.add(convertUserAccountToUserAccountDetails(userAccount));
        });

        //Returning converted faculty details list
        LOGGER.log(Level.FINE, "Returning converted faculty details list");
        return details;
    }

    @Override
    public UserAccountDetails convertUserAccountToUserAccountDetails(UserAccount userAccount) {
        //Entered method for converting faculty to faculty details
        LOGGER.log(Level.FINE, "Entered method for converting user accounts to faculty details");

        //Convert list of faculty to faculty details
        LOGGER.log(Level.FINE, "Convert list of faculty to faculty details");
        PersonDetails personDetails = new PersonDetails();
        personDetails.setId(userAccount.getPerson().getId());

        PersonRoleDetails personRoleDetails = personRoleService.convertPersonRoleToPersonRoleDetails(userAccount.getPersonRole());

        UserAccountDetails details = new UserAccountDetails(userAccount.getId());
        details.setUsername(userAccount.getUsername());
        details.setPassword(userAccount.getPassword());
        details.setPersonRole(personRoleDetails);
        details.setPerson(personDetails);

        //Returning converted faculty details
        LOGGER.log(Level.FINE, "Returning converted faculty details");
        return details;
    }
//</editor-fold>

    private static final Logger LOGGER = Logger.getLogger(UserAccountRequests.class.getSimpleName());
    @EJB
    PersonRoleRequestsLocal personRoleService;

}
