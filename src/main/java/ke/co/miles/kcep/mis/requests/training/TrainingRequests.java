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
import ke.co.miles.kcep.mis.entities.Location;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.entities.Training;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
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
        } else if (trainingDetails.getVenue() == null) {
            throw new InvalidArgumentException("error_006_02");
        }

        Training training = new Training();
        training.setTopic(trainingDetails.getTopic());
        training.setEndDate(trainingDetails.getEndDate());
        training.setStartDate(trainingDetails.getStartDate());
        training.setAttendanceSheet(trainingDetails.getAttendanceSheet());
        training.setNumberOfTrainees(trainingDetails.getNumberOfTrainees());
        if (trainingDetails.getTrainer() != null) {
            training.setTrainer(em.find(Person.class, trainingDetails.getTrainer().getId()));
        }
        if (trainingDetails.getCategoryOfTrainees() != null) {
            training.setCategoryOfTrainees(em.find(PersonRole.class, trainingDetails.getCategoryOfTrainees().getId()));
        }

        training.setVenue(locationService.addLocation(trainingDetails.getVenue()));

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
            throw new InvalidArgumentException("error_006_03");
        } else if (trainingDetails.getVenue() == null) {
            throw new InvalidArgumentException("error_006_02");
        }

        locationService.editLocation(trainingDetails.getVenue());

        Training training = em.find(Training.class, trainingDetails.getId());
        training.setId(trainingDetails.getId());
        training.setTopic(trainingDetails.getTopic());
        training.setEndDate(trainingDetails.getEndDate());
        training.setStartDate(trainingDetails.getStartDate());
        training.setAttendanceSheet(trainingDetails.getAttendanceSheet());
        training.setNumberOfTrainees(trainingDetails.getNumberOfTrainees());
        if (trainingDetails.getTrainer() != null) {
            training.setTrainer(em.find(Person.class, trainingDetails.getTrainer().getId()));
        }
        if (trainingDetails.getCategoryOfTrainees() != null) {
            training.setCategoryOfTrainees(em.find(PersonRole.class, trainingDetails.getCategoryOfTrainees().getId()));
        }

        training.setVenue(em.find(Location.class, trainingDetails.getVenue().getId()));

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
        trainingDetails.setEndDate(training.getEndDate());
        trainingDetails.setStartDate(training.getStartDate());
        trainingDetails.setAttendanceSheet(training.getAttendanceSheet());
        trainingDetails.setNumberOfTrainees(training.getNumberOfTrainees());
        if (training.getVenue() != null) {
            trainingDetails.setVenue(locationService.convertLocationToLocationDetails(training.getVenue()));
        }
        if (training.getTrainer() != null) {
            trainingDetails.setTrainer(trainingService.convertPersonToPersonDetails(training.getTrainer()));
        }
        if (training.getCategoryOfTrainees() != null) {
            trainingDetails.setCategoryOfTrainees(trainingRoleService.convertPersonRoleToPersonRoleDetails(training.getCategoryOfTrainees()));
        }

        return trainingDetails;

    }

    private List<TrainingDetails> convertTrainingsToTrainingDetailsList(List<Training> trainings) {

        List<TrainingDetails> trainingDetailsList = new ArrayList<>();
        for (Training training : trainings) {

            trainingDetailsList.add(convertTrainingToTrainingDetails(training));
        }

        return trainingDetailsList;

    }

//</editor-fold>
    @EJB
    LocationRequestsLocal locationService;
    @EJB
    private PersonRequestsLocal trainingService;
    @EJB
    private PersonRoleRequestsLocal trainingRoleService;

}
