/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.progress;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ActivityProgress;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ActivityProgressDetails;
import ke.co.miles.kcep.mis.utilities.ActivityProgressReportDetails;

/**
 *
 * @author siech
 */
@Local
public interface ActivityProgressRequestsLocal {

    /**
     *
     * @param activityProgressDetails details of the activity progress record to
     * be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addActivityProgress(ActivityProgressDetails activityProgressDetails) throws MilesException;

    /**
     * @param financialYearId the financial year to which the activity progress
     * to be retrieved belong
     * @return the list of activity progress record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public void checkForActivityProgress(Short financialYearId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the activity progress record to be
     * retrieved
     * @return the details of the activity progress record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ActivityProgressDetails retrieveActivityProgress(int id) throws MilesException;

    /**
     *
     * @param awpbReferenceCode the awpb reference code of the sub-activity for
     * which the activity progress is to be retrieved
     * @param level the administrative level for which the activity progress
     * report to be retrieved belongs to
     * @param levelId the unique identifier of the administrative level
     * @param financialYearId the unique identifier of the financial year
     * @return the details of the activity progress record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ActivityProgressReportDetails retrieveActivityProgress(String awpbReferenceCode, String level, Short levelId, Short financialYearId) throws MilesException;

    /**
     *
     * @param activityProgressDetails details of the activity progress record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editActivityProgress(ActivityProgressDetails activityProgressDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the activity progress record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeActivityProgress(int id) throws MilesException;

    /**
     *
     * @param activityProgress the activity progress to be converted
     * @return the details of the converted activity progress
     */
    public ActivityProgressDetails convertActivityProgressToActivityProgressDetails(ActivityProgress activityProgress);

    /**
     *
     * @param subActivityId the unique identifier of the sub-activity under
     * which the activity progress to be retrieved to belong
     * @return
     * @throws MilesException
     */
    public List<ActivityProgressDetails> retrieveActivityProgressList(int subActivityId) throws MilesException;

}
