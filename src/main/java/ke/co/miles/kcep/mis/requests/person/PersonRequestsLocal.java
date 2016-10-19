/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;

/**
 *
 * @author siech
 */
@Local
public interface PersonRequestsLocal {

    /**
     *
     * @param personDetails details of the person record to be created
     * @param personRoleDetail details of the person role
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPerson(PersonDetails personDetails, PersonRoleDetail personRoleDetail)
            throws MilesException;

    /**
     *
     * @param id unique identifier of the person record to retrieve
     * @return the details of the person record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public PersonDetails retrievePerson(int id) throws MilesException;

    /**
     *
     * @return the list of person record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrievePeople() throws MilesException;

    /**
     *
     * @param username the person's username
     * @param password the person's password
     * @return the map of person to person role
     * @throws MilesException when the database is in an incorrect state or when
     * the login credentials are invalid or non-existent in the database
     */
    public Map<PersonDetails, PersonRoleDetail>
            retrievePerson(String username, String password)
            throws MilesException;

    /**
     *
     * @param personDetails details of the person record to be edited
     * @param personRoleDetail details of the person role
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPerson(PersonDetails personDetails,
            PersonRoleDetail personRoleDetail) throws MilesException;

    /**
     *
     * @param id unique identifier of the person record to be removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removePerson(int id) throws MilesException;

    /**
     *
     * @param person the person record
     * @return the details of the person record
     */
    public PersonDetails convertPersonToPersonDetails(Person person);

    /**
     *
     * @param subCountyId the unique identifier of the sub-county to which the
     * farmers belong
     * @return the list of retrieved farmers
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveSubCountyFarmers(short subCountyId)
            throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county to which the people
     * belong
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveCountyPeople(short countyId)
            throws MilesException;

    /**
     *
     * @param subCountyId the unique identifier of the sub-county to which the
     * people belong
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveSubCountyPeople(short subCountyId)
            throws MilesException;

    /**
     *
     * @param regionId the unique identifier of the region to which the people
     * belong
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveRegionPeople(short regionId)
            throws MilesException;

    /**
     *
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveKalroPeople() throws MilesException;

    /**
     *
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveAgmarkPeople() throws MilesException;

    /**
     *
     * @return the list of farmers and agro-dealers(These people are relevant to
     * Equity bank; e-voucher scheme).
     * @throws MilesException when the database is in an incorrect state
     */
    public Object retrieveFarmersAndAgroDealers() throws MilesException;

    /**
     * Retrieve people of a certain person role
     *
     * @param personRoleDetail the person role of people to be retrieved
     * @return the people retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrievePeople(PersonRoleDetail personRoleDetail)
            throws MilesException;

    /**
     *
     * @param wardId the unique identifier of the ward to which the people
     * belong
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveWardPeople(short wardId)
            throws MilesException;

    /**
     *
     * @param personRoleDetail the person role of people to be counted
     * @return the map of counts
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<String, Integer>
            countPeople(PersonRoleDetail personRoleDetail) throws MilesException;

    /**
     *
     * @return the map of counts
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<String, Integer> countAllPeople() throws MilesException;

    /**
     *
     * @return the map of counts
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<String, Integer> countAllFarmersAndAgrodealers() throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county of which the
     * sub-county desk officer mans
     * @return the map of counts
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<String, Integer> countCountyFarmersAndAgrodealers(short countyId) throws MilesException;

    /**
     *
     * @return the list of farmers retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveFarmers() throws MilesException;

    /**
     *
     * @return the list of agro-dealers retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveAgroDealers() throws MilesException;

    /**
     *
     * @return the list of non-agro-dealers and non-farmers retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveNonFarmersAndNonAgroDealers() throws MilesException;

    /**
     *
     * @param name the name of the farmer
     * @param nationalId the national id of the farmer
     * @return the list of non-agro-dealers and non-farmers retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> searchFarmer(String name, String nationalId) throws MilesException;

    /**
     *
     * @param name the name of the farmer
     * @param nationalId the national id of the farmer
     * @return the list of non-agro-dealers and non-farmers retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> searchAgroDealer(String name, String nationalId) throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county of which the
     * sub-county desk officer mans
     * @return the list of people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveCountyFarmers(short countyId) throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county of which the
     * sub-county desk officer mans
     * @return the list of people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveCountyAgroDealers(short countyId) throws MilesException;

}
