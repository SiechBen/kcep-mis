/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.progress.comment;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ActivityProgressComment;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ActivityProgressCommentDetails;

/**
 *
 * @author siech
 */
@Local
public interface ActivityProgressCommentRequestsLocal {

    /**
     *
     * @param activityProgressCommentDetails details of the phenomenon type
     * record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addActivityProgressComment(ActivityProgressCommentDetails activityProgressCommentDetails) throws MilesException;

    /**
     *
     * @return the list of phenomenon type record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ActivityProgressCommentDetails> retrieveActivityProgressComments() throws MilesException;

    /**
     *
     * @param id the unique identifier of the phenomenon type record to be
     * retrieved
     * @return the details of the phenomenon type record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ActivityProgressCommentDetails retrieveActivityProgressComment(int id) throws MilesException;

    /**
     *
     * @param activityProgressCommentDetails details of the phenomenon type
     * record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editActivityProgressComment(ActivityProgressCommentDetails activityProgressCommentDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the phenomenon type record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeActivityProgressComment(int id) throws MilesException;

    /**
     *
     * @param activityProgressComment the phenomenon type to be converted
     * @return the details of the converted activityProgressComment
     */
    public ActivityProgressCommentDetails convertActivityProgressCommentToActivityProgressCommentDetails(ActivityProgressComment activityProgressComment);

}
