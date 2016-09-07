/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.descriptors.category;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Category;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.CategoryDetails;

/**
 *
 * @author siech
 */
@Local
public interface CategoryRequestsLocal {

    /**
     *
     * @param categoryDetails details of the category record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addCategory(CategoryDetails categoryDetails) throws MilesException;

    /**
     *
     * @return the list of category record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<CategoryDetails> retrieveCategories() throws MilesException;

    /**
     *
     * @param id the unique identifier of the category record to be retrieved
     * @return the details of the category record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public CategoryDetails retrieveCategory(int id) throws MilesException;

    /**
     *
     * @param categoryDetails details of the category record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editCategory(CategoryDetails categoryDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the category record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeCategory(int id) throws MilesException;

    /**
     *
     * @param category the category to be converted
     * @return the details of the converted category
     */
    public CategoryDetails convertCategoryToCategoryDetails(Category category);

}
