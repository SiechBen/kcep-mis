/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.agebracket;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.AgeBracket;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.AgeBracketDetails;

/**
 *
 * @author siech
 */
@Local
public interface AgeBracketRequestsLocal {
    
    /**
     *
     * @param ageBracketDetails details of the age bracket record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addAgeBracket(AgeBracketDetails ageBracketDetails) throws MilesException;

    /**
     *
     * @return the list of age bracket record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<AgeBracketDetails> retrieveAgeBrackets() throws MilesException;

    /**
     *
     * @param id the unique identifier of the age bracket record to be retrieved
     * @return the details of the age bracket record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public AgeBracketDetails retrieveAgeBracket(int id) throws MilesException;

    /**
     *
     * @param ageBracketDetails details of the age bracket record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editAgeBracket(AgeBracketDetails ageBracketDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the age bracket record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeAgeBracket(int id) throws MilesException;

    /**
     *
     * @param ageBracket the age bracket to be converted
     * @return the details of the converted ageBracket
     */
    public AgeBracketDetails convertAgeBracketToAgeBracketDetails(AgeBracket ageBracket);

}
