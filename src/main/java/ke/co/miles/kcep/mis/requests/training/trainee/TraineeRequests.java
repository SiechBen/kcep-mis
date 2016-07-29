/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.trainee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.Trainee;
import ke.co.miles.kcep.mis.entities.Training;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.TrainingRequestsLocal;
import ke.co.miles.kcep.mis.utilities.TraineeDetails;
import ke.co.miles.kcep.mis.utilities.TrainingDetails;

/**
 *
 * @author siech
 */
@Stateless
public class TraineeRequests extends EntityRequests implements TraineeRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public void addTrainee(TraineeDetails traineeDetails) throws MilesException {

        if (traineeDetails == null) {
            throw new InvalidArgumentException("error_028_01");
        } else if (traineeDetails.getPerson() == null) {
            throw new InvalidArgumentException("error_028_02");
        } else if (traineeDetails.getTraining() == null) {
            throw new InvalidArgumentException("error_028_03");
        }

        Trainee trainee = new Trainee();
        trainee.setPerson(em.getReference(Person.class, traineeDetails.getPerson().getId()));
        trainee.setTraining(em.getReference(Training.class, traineeDetails.getTraining().getId()));

        try {
            em.persist(trainee);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    @Override
    public void addTrainees(List<TraineeDetails> traineeDetailsList) throws MilesException {
        for (TraineeDetails traineeDetails : traineeDetailsList) {
            addTrainee(traineeDetails);
        }
        em.flush();
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveWardTrainings(int wardId) throws MilesException {

        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findByWardId");
        q.setParameter("wardId", wardId);
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<TrainingDetails, List<TraineeDetails>> trainingMap = new HashMap<>();

        for (Training training : trainings) {
            trainingMap.put(trainingService.convertTrainingToTrainingDetails(training), retrieveTrainees(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveCountyTrainings(short countyId) throws MilesException {

        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findByCountyId");
        q.setParameter("countyId", countyId);
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<TrainingDetails, List<TraineeDetails>> trainingMap = new HashMap<>();

        for (Training training : trainings) {
            trainingMap.put(trainingService.convertTrainingToTrainingDetails(training), retrieveTrainees(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveSubCountyTrainings(int subCountyId) throws MilesException {

        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findBySubCountyId");
        q.setParameter("subCountyId", subCountyId);
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<TrainingDetails, List<TraineeDetails>> trainingMap = new HashMap<>();

        for (Training training : trainings) {
            trainingMap.put(trainingService.convertTrainingToTrainingDetails(training), retrieveTrainees(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveTrainings() throws MilesException {

        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findAll");
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<TrainingDetails, List<TraineeDetails>> trainingMap = new HashMap<>();

        for (Training training : trainings) {
            trainingMap.put(trainingService.convertTrainingToTrainingDetails(training), retrieveTrainees(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TraineeDetails> retrieveTrainees(int trainingId) throws MilesException {
        List<Trainee> trainees = new ArrayList<>();
        q = em.createNamedQuery("Trainee.findByTrainingId");
        q.setParameter("trainingId", trainingId);
        try {
            trainees = q.getResultList();
        } catch (Exception e) {
        }

        return convertTraineesToTraineeDetailsList(trainees);
    }

    @Override
    public TraineeDetails retrieveTrainee(int id) throws MilesException {
        Trainee trainee;
        q = em.createNamedQuery("Trainee.findById");
        q.setParameter("id", id);
        try {
            trainee = (Trainee) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertTraineeToTraineeDetails(trainee);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editTrainee(TraineeDetails traineeDetails) throws MilesException {

        if (traineeDetails == null) {
            throw new InvalidArgumentException("error_028_01");
        } else if (traineeDetails.getId() == null) {
            throw new InvalidArgumentException("error_028_04");
        } else if (traineeDetails.getPerson() == null) {
            throw new InvalidArgumentException("error_028_02");
        } else if (traineeDetails.getTraining() == null) {
            throw new InvalidArgumentException("error_028_03");
        }

        Trainee trainee = new Trainee();
        trainee.setId(traineeDetails.getId());
        trainee.setPerson(em.getReference(Person.class, traineeDetails.getPerson().getId()));
        trainee.setTraining(em.getReference(Training.class, traineeDetails.getTraining().getId()));

        try {
            em.merge(trainee);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeTrainee(int id) throws MilesException {
        Trainee trainee = em.find(Trainee.class, id);
        try {
            em.remove(trainee);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public TraineeDetails convertTraineeToTraineeDetails(Trainee trainee) {

        TraineeDetails traineeDetails = new TraineeDetails(trainee.getId());
        traineeDetails.setPerson(personService.convertPersonToPersonDetails(trainee.getPerson()));
        traineeDetails.setTraining(trainingService.convertTrainingToTrainingDetails(trainee.getTraining()));

        return traineeDetails;

    }

    private List<TraineeDetails> convertTraineesToTraineeDetailsList(List<Trainee> trainees) {

        List<TraineeDetails> traineeDetailsList = new ArrayList<>();
        for (Trainee trainee : trainees) {
            traineeDetailsList.add(convertTraineeToTraineeDetails(trainee));
        }

        return traineeDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private TrainingRequestsLocal trainingService;
}
