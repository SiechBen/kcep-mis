/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.workshops;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ValidationWorkshopsDetails;

/**
 *
 * @author siech
 */
@Local
public interface ValidationWorkshopsRequestsLocal {
 
    /**
     *
     * @param validationWorkshopsDetails details of the validation workshops record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addValidationWorkshops(ValidationWorkshopsDetails validationWorkshopsDetails) throws MilesException;

    /**
     *
     * @return the list of validation workshops record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ValidationWorkshopsDetails> retrieveTechnologies() throws MilesException;

    /**
     *
     * @param id the unique identifier of the validation workshops record to be retrieved
     * @return the details of the validation workshops record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ValidationWorkshopsDetails retrieveValidationWorkshops(int id) throws MilesException;

    /**
     *
     * @param validationWorkshopsDetails details of the validation workshops record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editValidationWorkshops(ValidationWorkshopsDetails validationWorkshopsDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the validation workshops record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeValidationWorkshops(int id) throws MilesException;

}