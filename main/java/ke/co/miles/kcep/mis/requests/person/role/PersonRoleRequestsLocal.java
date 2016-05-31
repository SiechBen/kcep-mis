/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person.role;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetails;

/**
 *
 * @author siech
 */
@Local
public interface PersonRoleRequestsLocal {

    /**
     *
     * @param personRoleDetails details of the person role record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPersonRole(PersonRoleDetails personRoleDetails) throws MilesException;

    /**
     *
     * @return the list of person role record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonRoleDetails> retrievePersonRoles() throws MilesException;

    /**
     *
     * @param id the unique identifier of the person role record to be retrieved
     * @return the details of the person role record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public PersonRoleDetails retrievePersonRole(int id) throws MilesException;

    /**
     *
     * @param personRoleDetails details of the person role record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPersonRole(PersonRoleDetails personRoleDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the person role record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removePersonRole(int id) throws MilesException;

    /**
     *
     * @param personRole the person role to be converted
     * @return the details of the converted person role
     */
    public PersonRoleDetails convertPersonRoleToPersonRoleDetails(PersonRole personRole);

}
