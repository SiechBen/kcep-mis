/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person.useraccount;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.entities.UserAccount;
import ke.co.miles.kcep.mis.exceptions.AlgorithmException;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.LoginException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.access.AccessRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.role.PersonRoleRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.UserAccountDetails;

/**
 *
 * @author Ben Siech
 */
@Stateless
public class UserAccountRequests extends EntityRequests implements UserAccountRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public Integer addUserAccount(UserAccountDetails personDetails) throws MilesException {
        //Method for adding a faculty record to the database

        //Checking validity of details
        if (personDetails == null) {
            throw new InvalidArgumentException("error_015_01");
        } else if (personDetails.getUsername() == null || personDetails.getUsername().trim().length() == 0) {
            throw new InvalidArgumentException("error_015_02");
        } else if (personDetails.getUsername().trim().length() > 150) {
            throw new InvalidArgumentException("error_015_03");
        } else if (personDetails.getPassword() == null || personDetails.getPassword().trim().length() == 0) {
            throw new InvalidArgumentException("error_015_04");
        } else if (personDetails.getPassword().trim().length() > 150) {
            throw new InvalidArgumentException("error_015_05");
        } else if (personDetails.getPerson() == null) {
            throw new InvalidArgumentException("error_015_06");
        } else if (personDetails.getPersonRole() == null) {
            throw new InvalidArgumentException("error_015_07");
        }

        //Checking if the username is unique to a faculty
        setQ(em.createNamedQuery("UserAccount.findByUsername"));
        q.setParameter("username", personDetails.getUsername());
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            userAccount = null;
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
        if (userAccount != null) {
            throw new MilesException("error_015_08");
        }

        //Create a message digest algorithm object for SHA-256 hashing algorithm
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new AlgorithmException("error_016_01");
        }

        //Creating a container to hold faculty record
        userAccount = new UserAccount();
        userAccount.setUsername(personDetails.getUsername());
        userAccount.setPerson(em.getReference(Person.class, personDetails.getPerson().getId()));
        userAccount.setPersonRole(em.getReference(PersonRole.class, personDetails.getPersonRole().getId()));
        userAccount.setPassword(accessService.generateSHAPassword(messageDigest, personDetails.getPassword()));

        //Adding a faculty record to the database
        try {
            em.persist(userAccount);
            em.flush();
        } catch (Exception e) {
            throw new MilesException("error_000_01");
        }

        //Returning the unique identifier of the new record added
        return userAccount.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<Person> filterPeople(List<Person> people,
            PersonRoleDetail personRoleDetail) throws MilesException {

        List<Person> filteredPeople = new ArrayList<>();
        setQ(em.createNamedQuery("UserAccount.findByPersonRoleIdAndPersonId"));

        for (Person person : people) {
            q.setParameter("personId", person.getId());
            q.setParameter("personRoleId", personRoleDetail.getId());
            try {
                filteredPeople.add(person);
            } catch (NoResultException e) {
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "", e);
                throw new InvalidStateException("error_000_01");
            }
        }

        return filteredPeople;

    }

    @Override
    public List<Person> filterPeople(List<Person> people,
            PersonRoleDetail... personRoleDetails) throws MilesException {

        List<Person> filteredPeople = new ArrayList<>();
        setQ(em.createNamedQuery("UserAccount.findByPersonRoleIdsAndPersonId"));

        for (Person person : people) {
            q.setParameter("personId", person.getId());
            q.setParameter("personRoleId1", personRoleDetails[0].getId());
            q.setParameter("personRoleId2", personRoleDetails[1].getId());
            try {
                filteredPeople.add(person);
            } catch (NoResultException e) {
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "", e);
                throw new InvalidStateException("error_000_01");
            }
        }

        return filteredPeople;

    }

    @Override
    public UserAccountDetails retrieveUserAccount(String username,
            String hashedPassword) throws MilesException {
        if (username == null || username.trim().length() == 0) {
            throw new InvalidArgumentException("error_015_02");
        } else if (username.trim().length() > 150) {
            throw new InvalidArgumentException("error_015_03");
        } else if (hashedPassword == null || hashedPassword.trim().length() == 0) {
            throw new InvalidArgumentException("error_015_04");
        } else if (hashedPassword.trim().length() > 150) {
            throw new InvalidArgumentException("error_015_05");
        }

        setQ(em.createNamedQuery("UserAccount.findByUsernameAndPassword"));
        q.setParameter("username", username);
        q.setParameter("password", hashedPassword);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            throw new LoginException("error_015_10");
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertUserAccountToUserAccountDetails(userAccount);
    }

    @Override
    public UserAccountDetails retrieveUserAccountByPersonId(Integer personId)
            throws MilesException {
        if (personId == null) {
            throw new InvalidArgumentException("error_015_06");
        }

        setQ(em.createNamedQuery("UserAccount.findByPersonId"));
        q.setParameter("personId", personId);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            throw new InvalidStateException("error_015_11");
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertUserAccountToUserAccountDetails(userAccount);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserAccountDetails> retrieveUserAccounts() throws MilesException {
        setQ(em.createNamedQuery("UserAccount.findAll"));
        List<UserAccount> userAccounts = new ArrayList<>();
        try {
            userAccounts = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertUserAccountsToUserAccountDetailsList(userAccounts);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editUserAccount(UserAccountDetails userAccountDetails) throws MilesException {
        //Method for editing a faculty record in the database

        //Checking validity of details
        if (userAccountDetails == null) {
            throw new InvalidArgumentException("error_015_01");
        } else if (userAccountDetails.getId() == null) {
            throw new InvalidArgumentException("error_015_09");
        } else if (userAccountDetails.getUsername() == null || userAccountDetails.getUsername().trim().length() == 0) {
            throw new InvalidArgumentException("error_015_02");
        } else if (userAccountDetails.getUsername().trim().length() > 150) {
            throw new InvalidArgumentException("error_015_03");
        } else if (userAccountDetails.getPassword() == null || userAccountDetails.getPassword().trim().length() == 0) {
            throw new InvalidArgumentException("error_015_04");
        } else if (userAccountDetails.getPassword().trim().length() > 150) {
            throw new InvalidArgumentException("error_015_05");
        } else if (userAccountDetails.getPerson() == null) {
            throw new InvalidArgumentException("error_015_06");
        } else if (userAccountDetails.getPersonRole() == null) {
            throw new InvalidArgumentException("error_015_07");
        }

        //Checking if the username is unique to a faculty
        setQ(em.createNamedQuery("UserAccount.findByUsername"));
        q.setParameter("username", userAccountDetails.getUsername());
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (NoResultException e) {
            userAccount = null;
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
        if (userAccount != null) {
            if (!userAccount.getId().equals(userAccountDetails.getId())) {
                throw new InvalidStateException("error_015_08");
            }
        }

        //Create a message digest algorithm object for SHA-256 hashing algorithm
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new AlgorithmException("error_016_01");
        }

        //Creating a container to hold faculty record
        userAccount = em.find(UserAccount.class, userAccountDetails.getId());
        userAccount.setId(userAccountDetails.getId());
        userAccount.setPassword(userAccountDetails.getPassword());
        userAccount.setUsername(userAccountDetails.getUsername());
        userAccount.setPerson(em.getReference(Person.class, userAccountDetails.getPerson().getId()));
        userAccount.setPersonRole(em.getReference(PersonRole.class, userAccountDetails.getPersonRole().getId()));
        userAccount.setPassword(accessService.generateSHAPassword(messageDigest, userAccountDetails.getPassword()));

        //Editing a faculty record in the database
        try {
            em.merge(userAccount);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeUserAccount(Integer personId) throws MilesException {
        //Method for removing a faculty record from the database

        //Checking validity of details
        if (personId == null) {
            throw new InvalidArgumentException("error_015_09");
        }

        //Get the user account record to be removed
        setQ(em.createNamedQuery("UserAccount.findByPersonId"));
        q.setParameter("personId", personId);
        UserAccount userAccount;
        try {
            userAccount = (UserAccount) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        //Deactivate the user account record in the database
        try {
            em.merge(userAccount);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private List<UserAccountDetails> convertUserAccountsToUserAccountDetailsList(List<UserAccount> userAccounts) {
        //Entered method for converting user accounts list to faculty details list

        //Convert list of user accounts to faculty details list
        List<UserAccountDetails> details = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {
            details.add(convertUserAccountToUserAccountDetails(userAccount));
        }

        //Returning converted faculty details list
        return details;
    }

    @Override
    public UserAccountDetails convertUserAccountToUserAccountDetails(UserAccount userAccount) {
        //Entered method for converting faculty to faculty details

        //Convert list of faculty to faculty details
        PersonDetails personDetails = personService.convertPersonToPersonDetails(userAccount.getPerson());

        PersonRoleDetail personRoleDetail = personRoleService.convertPersonRoleToPersonRoleDetail(userAccount.getPersonRole());

        UserAccountDetails userAccountDetails = new UserAccountDetails(userAccount.getId());
        userAccountDetails.setUsername(userAccount.getUsername());
        userAccountDetails.setPassword(userAccount.getPassword());
        userAccountDetails.setPersonRole(personRoleDetail);
        userAccountDetails.setPerson(personDetails);

        //Returning converted faculty details
        return userAccountDetails;
    }
//</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(UserAccountRequests.class.getSimpleName());
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private PersonRoleRequestsLocal personRoleService;
    @EJB
    private AccessRequestsLocal accessService;

}
