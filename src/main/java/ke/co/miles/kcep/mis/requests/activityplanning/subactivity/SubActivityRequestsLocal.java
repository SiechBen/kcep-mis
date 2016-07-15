/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.subactivity;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.SubActivity;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;

/**
 *
 * @author siech
 */
@Local
public interface SubActivityRequestsLocal {

    /**
     *
     * @param details the details of the sub-activity record to be created
     * @return the unique identifier of the new sub-activity record created
     * @throws MilesException when the database is in an incorrect state or when
     * the arguments are incorrectly specified
     */
    public Integer addSubActivity(SubActivityDetails details) throws MilesException;

    /**
     *
     * @param activityPlanningId the unique identifier of the activity planning
     * to which the sub activities to be retrieved belong
     * @return the list of sub-activity records
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubActivityDetails> retrieveSubActivities(Integer activityPlanningId) throws MilesException;

    /**
     *
     * @param details the details to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the arguments are incorrectly specified
     */
    public void editSubActivity(SubActivityDetails details) throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub-activity record to be
     * retrieved
     * @return the sub-activity retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the user credentials are incorrect
     */
    public SubActivityDetails retrieveSubActivity(Integer id) throws MilesException;

    /**
     *
     * @param subActivity the sub-activity to be converted to details
     * @return the details resulting from the conversion
     */
    public SubActivityDetails convertSubActivityToSubActivityDetails(SubActivity subActivity);

    /**
     *
     * @param id the unique identifier of the sub-activity record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeSubActivity(Integer id) throws MilesException;

}
