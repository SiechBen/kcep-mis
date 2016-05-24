/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PersonDetails;

/**
 *
 * @author siech
 */
@Local
public interface PersonRequestsLocal {

    /**
     *
     * @param personDetails details of the person record to be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPerson(PersonDetails personDetails) throws MilesException;

    /**
     *
     * @param personId unique identifier of the person record to retrieve
     * @return the details of the person record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public PersonDetails retrievePerson(int personId) throws MilesException;

    /**
     *
     * @return the list of person record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrievePeople() throws MilesException;

    /**
     *
     * @param personDetails details of the person record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPerson(PersonDetails personDetails) throws MilesException;

    /**
     *
     * @param personId unique identifier of the person record to be removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removePerson(int personId) throws MilesException;

}
