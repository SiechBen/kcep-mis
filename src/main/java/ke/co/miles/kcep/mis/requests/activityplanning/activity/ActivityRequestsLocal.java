/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Activity;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ActivityDetails;

/**
 *
 * @author siech
 */
@Local
public interface ActivityRequestsLocal {

    /**
     *
     * @param activityDetails details of the activity record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addActivity(ActivityDetails activityDetails) throws MilesException;

    /**
     *
     * @return the list of activity record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ActivityDetails> retrieveActivities() throws MilesException;

    /**
     *
     * @param id the unique identifier of the activity record to be retrieved
     * @return the details of the activity record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ActivityDetails retrieveActivity(int id) throws MilesException;

    /**
     *
     * @param activityDetails details of the activity record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editActivity(ActivityDetails activityDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the activity record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeActivity(int id) throws MilesException;

    /**
     *
     * @param activity the activity to be converted
     * @return the details of the converted activity
     */
    public ActivityDetails convertActivityToActivityDetails(Activity activity);

}
