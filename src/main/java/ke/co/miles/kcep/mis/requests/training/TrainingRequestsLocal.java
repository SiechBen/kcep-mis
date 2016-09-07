/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training;

import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Training;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.TrainingDetails;

/**
 *
 * @author siech
 */
@Local
public interface TrainingRequestsLocal {

    /**
     *
     * @param trainingDetails details of the training record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addTraining(TrainingDetails trainingDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the training record to be retrieved
     * @return the details of the training record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public TrainingDetails retrieveTraining(int id) throws MilesException;

    /**
     *
     * @param trainingDetails details of the training record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editTraining(TrainingDetails trainingDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the training record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeTraining(int id) throws MilesException;

    /**
     *
     * @param training the training to be converted
     * @return the details of the converted training
     */
    public TrainingDetails convertTrainingToTrainingDetails(Training training);

}
