/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.expenditurecategory;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ExpenditureCategory;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ExpenditureCategoryDetails;

/**
 *
 * @author siech
 */
@Local
public interface ExpenditureCategoryRequestsLocal {

    /**
     *
     * @param expenditureCategoryDetails details of the expenditure category record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addExpenditureCategory(ExpenditureCategoryDetails expenditureCategoryDetails) throws MilesException;

    /**
     *
     * @return the list of expenditure category record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ExpenditureCategoryDetails> retrieveExpenditureCategories() throws MilesException;

    /**
     *
     * @param id the unique identifier of the expenditure category record to be retrieved
     * @return the details of the expenditure category record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ExpenditureCategoryDetails retrieveExpenditureCategory(int id) throws MilesException;

    /**
     *
     * @param expenditureCategoryDetails details of the expenditure category record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editExpenditureCategory(ExpenditureCategoryDetails expenditureCategoryDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the expenditure category record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeExpenditureCategory(int id) throws MilesException;

    /**
     *
     * @param expenditureCategory the expenditureCategory to be converted
     * @return the details of the converted expenditureCategory
     */
    public ExpenditureCategoryDetails convertExpenditureCategoryToExpenditureCategoryDetails(ExpenditureCategory expenditureCategory);

}
