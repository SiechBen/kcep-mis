/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.feedback;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Feedback;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.FeedbackDetails;
import ke.co.miles.kcep.mis.utilities.FeedbackTypeDetail;

/**
 *
 * @author siech
 */
@Local
public interface FeedbackRequestsLocal {

    /**
     *
     * @param feedbackDetails details of the feedback record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addFeedback(FeedbackDetails feedbackDetails) throws MilesException;

    /**
     *
     * @param feedbackType the type of feedback
     * @return the list of feedback record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FeedbackDetails> retrieveFeedback(FeedbackTypeDetail feedbackType) throws MilesException;

    /**
     *
     * @return the list of latest feedback records
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FeedbackDetails> retrieveLatestFeedback() throws MilesException;

    /**
     *
     * @param feedbackType the type of feedback
     * @param countyId the county in which the farmers who posted the feedback
     * were posted belongs
     * @return the list of feedback records from a county
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FeedbackDetails> retrieveCountyFeedback(FeedbackTypeDetail feedbackType, short countyId) throws MilesException;

    /**
     *
     * @param feedbackType the type of feedback
     * @param subCountyId the sub-county in which the farmers who posted the
     * feedback were posted belongs
     * @return the list of feedback records from a sub-county
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FeedbackDetails> retrieveSubCountyFeedback(FeedbackTypeDetail feedbackType, int subCountyId) throws MilesException;

    /**
     *
     * @param feedbackType the type of feedback
     * @param wardId the ward in which the farmers who posted the feedback were
     * posted belongs
     * @return the list of feedback records from a ward
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FeedbackDetails> retrieveWardFeedback(FeedbackTypeDetail feedbackType, int wardId) throws MilesException;

    /**
     *
     * @param feedbackType the type of feedback
     * @param regionId the region in which the farmers who posted the feedback
     * were posted belongs
     * @return the list of feedback records from a region
     * @throws MilesException when the database is in an incorrect state
     */
    public List<FeedbackDetails> retrieveRegionFeedback(FeedbackTypeDetail feedbackType, int regionId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the feedback record to be retrieved
     * @return the details of the feedback record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public FeedbackDetails retrieveFeedback(int id) throws MilesException;

    /**
     *
     * @param feedbackDetails details of the feedback record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editFeedback(FeedbackDetails feedbackDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the feedback record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeFeedback(int id) throws MilesException;

    /**
     *
     * @param feedback the feedback to be converted
     * @return the details of the converted feedback
     */
    public FeedbackDetails convertFeedbackToFeedbackDetails(Feedback feedback);

}
