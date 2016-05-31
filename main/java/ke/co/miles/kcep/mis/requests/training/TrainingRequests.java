/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.entities.Training;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.role.PersonRoleRequestsLocal;
import ke.co.miles.kcep.mis.utilities.TrainingDetails;

/**
 *
 * @author siech
 */
@Stateless
public class TrainingRequests extends EntityRequests implements TrainingRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addTraining(TrainingDetails trainingDetails) throws MilesException {

        if (trainingDetails == null) {
            throw new InvalidArgumentException("error_006_01");
        }

        Training training = new Training();
        training.setTopic(trainingDetails.getTopic());
        training.setVenue(trainingDetails.getVenue());
        training.setEndDate(trainingDetails.getEndDate());
        training.setStartDate(trainingDetails.getStartDate());
        training.setAttendance(trainingDetails.getAttendance());
        training.setNumberOfTrainees(trainingDetails.getNumberOfTrainees());
        training.setTrainer(em.find(Person.class, trainingDetails.getTrainer().getId()));
        training.setPersonRole(em.find(PersonRole.class, trainingDetails.getPersonRole().getId()));

        try {
            em.persist(training);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return training.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<TrainingDetails> retrieveTrainings() throws MilesException {
        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findAll");
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
        }

        return convertTrainingsToTrainingDetailsList(trainings);
    }

    @Override
    public TrainingDetails retrieveTraining(int id) throws MilesException {
        Training training;
        q = em.createNamedQuery("Training.findById");
        q.setParameter("id", id);
        try {
            training = (Training) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertTrainingToTrainingDetails(training);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editTraining(TrainingDetails trainingDetails) throws MilesException {

        if (trainingDetails == null) {
            throw new InvalidArgumentException("error_006_01");
        } else if (trainingDetails.getId() == null) {
            throw new InvalidArgumentException("error_006_02");
        }

        Training training = em.find(Training.class, trainingDetails.getId());
        training.setTopic(trainingDetails.getTopic());
        training.setVenue(trainingDetails.getVenue());
        training.setEndDate(trainingDetails.getEndDate());
        training.setStartDate(trainingDetails.getStartDate());
        training.setAttendance(trainingDetails.getAttendance());
        training.setNumberOfTrainees(trainingDetails.getNumberOfTrainees());
        training.setTrainer(em.find(Person.class, trainingDetails.getTrainer().getId()));
        training.setPersonRole(em.find(PersonRole.class, trainingDetails.getPersonRole().getId()));

        try {
            em.merge(training);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeTraining(int id) throws MilesException {
        Training training = em.find(Training.class, id);
        try {
            em.remove(training);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private TrainingDetails convertTrainingToTrainingDetails(Training training) {

        TrainingDetails trainingDetails = new TrainingDetails(training.getId());

        trainingDetails.setTopic(training.getTopic());
        trainingDetails.setVenue(training.getVenue());
        trainingDetails.setEndDate(training.getEndDate());
        trainingDetails.setStartDate(training.getStartDate());
        trainingDetails.setAttendance(training.getAttendance());
        trainingDetails.setNumberOfTrainees(training.getNumberOfTrainees());
        trainingDetails.setTrainer(personService.convertPersonToPersonDetails(training.getTrainer()));
        trainingDetails.setPersonRole(personRoleService.convertPersonRoleToPersonRoleDetails(training.getPersonRole()));

        return trainingDetails;

    }

    private List<TrainingDetails> convertTrainingsToTrainingDetailsList(List<Training> trainings) {

        List<TrainingDetails> trainingDetailsList = new ArrayList<>();
        trainings.stream().forEach((training) -> {
            trainingDetailsList.add(convertTrainingToTrainingDetails(training));
        });
        return trainingDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private PersonRoleRequestsLocal personRoleService;

}
