package ke.co.miles.kcep.mis.requests.activityplanning.activity.name;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ActivityName;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ActivityNameDetails;

/**
 *
 * @author siech
 */
@Local
public interface ActivityNameRequestsLocal {

    /**
     *
     * @param activityNameDetails details of the activity name record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addActivityName(ActivityNameDetails activityNameDetails) throws MilesException;

    /**
     *
     * @return the list of activity name record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ActivityNameDetails> retrieveActivityNames() throws MilesException;

    /**
     *
     * @param id the unique identifier of the activity name record to be retrieved
     * @return the details of the activity name record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ActivityNameDetails retrieveActivityName(int id) throws MilesException;

    /**
     *
     * @param activityNameDetails details of the activity name record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editActivityName(ActivityNameDetails activityNameDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the activity name record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeActivityName(int id) throws MilesException;

    /**
     *
     * @param activityName the activityName to be converted
     * @return the details of the converted activityName
     */
    public ActivityNameDetails convertActivityNameToActivityNameDetails(ActivityName activityName);

}
