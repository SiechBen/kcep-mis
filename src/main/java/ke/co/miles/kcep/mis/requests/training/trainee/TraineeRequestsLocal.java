/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.trainee;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Trainee;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.TraineeDetails;
import ke.co.miles.kcep.mis.utilities.TrainingDetails;

/**
 *
 * @author siech
 */
@Local
public interface TraineeRequestsLocal {

    /**
     *
     * @param traineeDetails details of the trainee record to be created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void addTrainee(TraineeDetails traineeDetails) throws MilesException;

    /**
     *
     * @param traineesDetailsList list of details of the trainee records to be
     * created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void addTrainees(List<TraineeDetails> traineesDetailsList) throws MilesException;

    /**
     *
     * @param trainingId the unique identifier of the training of which the
     * trainees to be retrieved were involved
     * @return the list of trainee record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<TraineeDetails> retrieveTrainees(int trainingId) throws MilesException;

    /**
     *
     * @return the retrieved map of training record details to trainees
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveTrainings() throws MilesException;

    /**
     *
     * @param wardId the unique identifier of the ward at which the training
     * activities were held
     * @return the retrieved map of training record details to trainees
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveWardTrainings(int wardId) throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county at which the training
     * activities were held
     * @return the retrieved map of training record details to trainees
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveCountyTrainings(short countyId) throws MilesException;

    /**
     *
     * @param subCountyId the unique identifier of the sub-county at which the
     * training activities were held
     * @return the retrieved map of training record details to trainees
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveSubCountyTrainings(int subCountyId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the trainee record to be retrieved
     * @return the details of the trainee record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public TraineeDetails retrieveTrainee(int id) throws MilesException;

    /**
     *
     * @param traineeDetails details of the trainee record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editTrainee(TraineeDetails traineeDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the trainee record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeTrainee(int id) throws MilesException;

    /**
     *
     * @param trainee the trainee to be converted
     * @return the details of the converted trainee
     */
    public TraineeDetails convertTraineeToTraineeDetails(Trainee trainee);

}
    