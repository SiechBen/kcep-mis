/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ActivityPlanning;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ActivityPlanningDetails;

/**
 *
 * @author siech
 */
@Local
public interface ActivityPlanningRequestsLocal {

    /**
     *
     * @param planningDetails details of the planning record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addActivityPlanning(ActivityPlanningDetails planningDetails) throws MilesException;

    /**
     *
     * @return the list of planning record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ActivityPlanningDetails> retrieveActivityPlannings() throws MilesException;

    /**
     *
     * @param id the unique identifier of the planning record to be retrieved
     * @return the details of the planning record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ActivityPlanningDetails retrieveActivityPlanning(int id) throws MilesException;

    /**
     *
     * @param planningDetails details of the planning record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editActivityPlanning(ActivityPlanningDetails planningDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the planning record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeActivityPlanning(int id) throws MilesException;

    /**
     *
     * @param activityPlanning the record to be converted to activity planning
     * details
     * @return the details resulting from the conversion
     */
    public ActivityPlanningDetails convertActivityPlanningToActivityPlanningDetails(ActivityPlanning activityPlanning);

}
