/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.loan;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Loan;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.LoanDetails;

/**
 *
 * @author siech
 */
@Local
public interface LoanRequestsLocal {

    /**
     *
     * @param loanDetails details of the loan record to be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addLoan(LoanDetails loanDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the loan record to retrieve
     * @return the details of the loan record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public LoanDetails retrieveLoan(int id) throws MilesException;

    /**
     * @param accountId the unique identifier of the account
     * @return the list of loan record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<LoanDetails> retrieveLoans(int accountId) throws MilesException;

    /**
     *
     * @param loanDetails details of the loan record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editLoan(LoanDetails loanDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the loan record to be removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeLoan(int id) throws MilesException;

    /**
     *
     * @param loan the loan record
     * @return the details of the loan record
     */
    public LoanDetails convertLoanToLoanDetails(Loan loan);

}
