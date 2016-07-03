/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.hierarchy;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ResultHierarchy;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ResultHierarchyDetails;

/**
 *
 * @author siech
 */
@Local
public interface ResultHierarchyRequestsLocal {

    /**
     *
     * @param resultHierarchyDetails details of the result hierarchy record to
     * be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addResultHierarchy(ResultHierarchyDetails resultHierarchyDetails) throws MilesException;

    /**
     *
     * @return the list of result hierarchy record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ResultHierarchyDetails> retrieveResultHierarchies() throws MilesException;

    /**
     *
     * @param id the unique identifier of the result hierarchy record to be
     * retrieved
     * @return the details of the result hierarchy record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ResultHierarchyDetails retrieveResultHierarchy(int id) throws MilesException;

    /**
     *
     * @param resultHierarchyDetails details of the result hierarchy record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editResultHierarchy(ResultHierarchyDetails resultHierarchyDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the result hierarchy record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeResultHierarchy(int id) throws MilesException;

    /**
     *
     * @param resultHierarchy the result hierarchy to be converted
     * @return the details of the converted result hierarchy
     */
    public ResultHierarchyDetails convertResultHierarchyToResultHierarchyDetails(ResultHierarchy resultHierarchy);

}
