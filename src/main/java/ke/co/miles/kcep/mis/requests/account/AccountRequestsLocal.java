/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.account;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Account;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.AccountDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;

/**
 *
 * @author siech
 */
@Local
public interface AccountRequestsLocal {

    /**
     *
     * @param accountDetails details of the account record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addAccount(AccountDetails accountDetails) throws MilesException;

    /**
     *
     * @return the list of account record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<AccountDetails> retrieveAccounts() throws MilesException;

    /**
     *
     * @param accountDetails details of the account record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     * @return the modified account details
     */
    public AccountDetails editAccount(AccountDetails accountDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the account record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeAccount(int id) throws MilesException;

    /**
     *
     * @param account the account to be converted
     * @return the details of the converted account
     */
    public AccountDetails convertAccountToAccountDetails(Account account);

    /**
     *
     * @param account the account to be converted to account details
     * @param farmer the farmer who the account belongs to
     * @return the details of the converted account
     */
    public AccountDetails convertAccountToAccountDetails(Account account, PersonDetails farmer);

    /**
     *
     * @param farmerId the unique identifier of the farmer to whom the account
     * belongs
     * @return the details of the account retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public AccountDetails retrieveAccount(int farmerId) throws MilesException;

    /**
     *
     * @param farmer the details of the farmer to whom the account belongs
     * @return the details of the account retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public AccountDetails retrieveAccount(PersonDetails farmer) throws MilesException;

}
