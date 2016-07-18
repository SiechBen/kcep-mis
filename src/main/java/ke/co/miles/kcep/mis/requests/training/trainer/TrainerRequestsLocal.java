/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.trainer;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Trainer;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.TrainerDetails;
import ke.co.miles.kcep.mis.utilities.TrainingDetails;

/**
 *
 * @author siech
 */
@Local
public interface TrainerRequestsLocal {

    /**
     *
     * @param trainerDetails details of the trainer record to be created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void addTrainer(TrainerDetails trainerDetails) throws MilesException;

    /**
     *
     * @param trainersDetailsList list of details of the trainer records to be
     * created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void addTrainers(List<TrainerDetails> trainersDetailsList) throws MilesException;

    /**
     *
     * @param trainingId the unique identifier of the training of which the
     * trainers to be retrieved were involved
     * @return the list of trainer record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<TrainerDetails> retrieveTrainers(int trainingId) throws MilesException;

    /**
     *
     * @return the retrieved map of training record details to trainers
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveTrainings() throws MilesException;

    /**
     *
     * @param wardId the unique identifier of the ward at which the training
     * activities were held
     * @return the retrieved map of training record details to trainers
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveWardTrainings(short wardId) throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county at which the training
     * activities were held
     * @return the retrieved map of training record details to trainers
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveCountyTrainings(short countyId) throws MilesException;

    /**
     *
     * @param subCountyId the unique identifier of the sub-county at which the
     * training activities were held
     * @return the retrieved map of training record details to trainers
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveSubCountyTrainings(short subCountyId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the trainer record to be retrieved
     * @return the details of the trainer record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public TrainerDetails retrieveTrainer(int id) throws MilesException;

    /**
     *
     * @param trainerDetails details of the trainer record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editTrainer(TrainerDetails trainerDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the trainer record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeTrainer(int id) throws MilesException;

    /**
     *
     * @param trainer the trainer to be converted
     * @return the details of the converted trainer
     */
    public TrainerDetails convertTrainerToTrainerDetails(Trainer trainer);

}
