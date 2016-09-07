/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.financialyear;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.FinancialYear;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.FinancialYearDetails;

/**
 *
 * @author siech
 */
@Local
public interface FinancialYearRequestsLocal {

    /**
     *
     * @param financialYearDetails details of the financial year record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addFinancialYear(FinancialYearDetails financialYearDetails) throws MilesException;

    /**
     *
     * @return the list of financial year record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FinancialYearDetails> retrieveFinancialYears() throws MilesException;

    /**
     *
     * @param id the unique identifier of the financial year record to be
     * retrieved
     * @return the details of the financial year record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public FinancialYearDetails retrieveFinancialYear(int id) throws MilesException;

    /**
     *
     * @return the details of the current financial year record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public FinancialYearDetails retrieveCurrentFinancialYear() throws MilesException;

    /**
     *
     * @param financialYearDetails details of the financial year record to be
     * edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editFinancialYear(FinancialYearDetails financialYearDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the financial year record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeFinancialYear(int id) throws MilesException;

    /**
     *
     * @param financialYear the financial year to be converted
     * @return the details of the converted financial year
     */
    public FinancialYearDetails convertFinancialYearToFinancialYearDetails(FinancialYear financialYear);

}
