/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.planning;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PlanningDetails;

/**
 *
 * @author siech
 */
@Local
public interface PlanningRequestsLocal {

    /**
     *
     * @param planningDetails details of the planning record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPlanning(PlanningDetails planningDetails) throws MilesException;

    /**
     *
     * @return the list of planning record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PlanningDetails> retrievePlannings() throws MilesException;

    /**
     *
     * @param id the unique identifier of the planning record to be retrieved
     * @return the details of the planning record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public PlanningDetails retrievePlanning(int id) throws MilesException;

    /**
     *
     * @param planningDetails details of the planning record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPlanning(PlanningDetails planningDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the planning record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removePlanning(int id) throws MilesException;

}
