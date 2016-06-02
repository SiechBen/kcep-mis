/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.useraccount;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.UserAccount;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
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
     * @param details
     * @return
     * @throws MilesException
     */
    public Integer addUserAccount(UserAccountDetails details) throws MilesException;

    /**
     *
     * @return @throws InvalidArgumentException
     * @throws MilesException
     */
    public List<UserAccountDetails> retrieveUserAccounts() throws MilesException;

    /**
     *
     * @param details
     * @throws MilesException
     */
    public void editUserAccount(UserAccountDetails details) throws MilesException;

    /**
     *
     * @param personId
     * @throws InvalidArgumentException
     * @throws MilesException
     */
    public void removeUserAccount(Integer personId) throws MilesException;

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws MilesException
     */
    public UserAccount retrieveUserAccount(String username, String password) throws MilesException;

    /**
     *
     * @param referenceNumber
     * @return
     * @throws MilesException
     */
    public UserAccountDetails retrieveUserAccount(String referenceNumber) throws MilesException;

    /**
     *
     * @param personId
     * @return
     * @throws MilesException
     */
    public UserAccountDetails retrieveUserAccountByPersonId(Integer personId) throws MilesException;

    /**
     *
     * @param userAccount
     * @return
     */
    public UserAccountDetails convertUserAccountToUserAccountDetails(UserAccount userAccount);

}
