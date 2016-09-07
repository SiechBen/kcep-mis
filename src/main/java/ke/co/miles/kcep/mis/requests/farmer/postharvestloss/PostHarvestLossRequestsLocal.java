/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.postharvestloss;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.PostHarvestLoss;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PostHarvestLossDetails;

/**
 *
 * @author siech
 */
@Local
public interface PostHarvestLossRequestsLocal {

    /**
     *
     * @param postHarvestLossDetails details of the post harvest loss record to
     * be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPostHarvestLoss(PostHarvestLossDetails postHarvestLossDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the post harvest loss record to retrieve
     * @return the details of the post harvest loss record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public PostHarvestLossDetails retrievePostHarvestLoss(int id) throws MilesException;

    /**
     *
     * @param farmerId unique identifier of the farmer for which post harvest
     * loss records are to be retrieved
     * @return the details list of the post harvest loss records retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public List<PostHarvestLossDetails> retrievePostHarvestLosses(int farmerId) throws MilesException;

    /**
     *
     * @return the list of post harvest loss record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PostHarvestLossDetails> retrievePostHarvestLosses() throws MilesException;

    /**
     *
     * @param postHarvestLossDetails details of the post harvest loss record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPostHarvestLoss(PostHarvestLossDetails postHarvestLossDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the post harvest loss record to be removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removePostHarvestLoss(int id) throws MilesException;

    /**
     *
     * @param postHarvestLoss the post harvest loss record
     * @return the details of the post harvest loss record
     */
    public PostHarvestLossDetails convertPostHarvestLossToPostHarvestLossDetails(PostHarvestLoss postHarvestLoss);

}
