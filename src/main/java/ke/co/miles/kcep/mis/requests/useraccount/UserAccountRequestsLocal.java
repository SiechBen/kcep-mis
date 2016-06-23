/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.useraccount;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.UserAccount;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.UserAccountDetails;

/**
 *
 * @author Ben Siech
 */
@Local
public interface UserAccountRequestsLocal {

    /**
     *
     * @param details the details of the user account record to be created
     * @return the unique identifier of the new user account record created
     * @throws MilesException when the database is in an incorrect state or when
     * the arguments are incorrectly specified
     */
    public Integer addUserAccount(UserAccountDetails details) throws MilesException;

    /**
     *
     * @return the list of user account records
     * @throws MilesException when the database is in an incorrect state
     */
    public List<UserAccountDetails> retrieveUserAccounts() throws MilesException;

    /**
     *
     * @param details the details to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the arguments are incorrectly specified
     */
    public void editUserAccount(UserAccountDetails details) throws MilesException;

    /**
     *
     * @param personId the unique identifier of the user account record to
     * remove
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeUserAccount(Integer personId) throws MilesException;

    /**
     *
     * @param username the username
     * @param password the password
     * @return the user account retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the user credentials are incorrect
     */
    public UserAccountDetails retrieveUserAccount(String username, String password) throws MilesException;

    /**
     *
     * @param personId the unique identifier of the user account to retrieve
     * @return the user account retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public UserAccountDetails retrieveUserAccountByPersonId(Integer personId) throws MilesException;

    /**
     *
     * @param userAccount the user account to be converted to details
     * @return the details resulting from the conversion
     */
    public UserAccountDetails convertUserAccountToUserAccountDetails(UserAccount userAccount);

}
