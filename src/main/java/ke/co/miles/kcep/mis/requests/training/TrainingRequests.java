/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Location;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.Topic;
import ke.co.miles.kcep.mis.entities.Training;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.topic.TopicRequestsLocal;
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
        training.setEndDate(trainingDetails.getEndDate());
        training.setStartDate(trainingDetails.getStartDate());
        training.setAttendanceSheet(trainingDetails.getAttendanceSheet());
        training.setNumberOfTrainees(trainingDetails.getNumberOfTrainees());
        if (trainingDetails.getTopic() != null) {
            training.setTopic(getEm().getReference(Topic.class, trainingDetails.getTopic().getId()));
        }
        if (trainingDetails.getCategoryOfTrainees() != null) {
            training.setCategoryOfTrainees(getEm().getReference(Phenomenon.class,
                    trainingDetails.getCategoryOfTrainees().getId()));
        }
        training.setVenue(locationService.addLocation(trainingDetails.getVenue()));

        try {
            getEm().persist(training);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return training.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public TrainingDetails retrieveTraining(int id) throws MilesException {
        Training training;
        setQ(getEm().createNamedQuery("Training.findById"));
        getQ().setParameter("id", id);
        try {
            training = (Training) getQ().getSingleResult();
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

        Training training = getEm().find(Training.class, trainingDetails.getId());
        training.setId(trainingDetails.getId());
        training.setEndDate(trainingDetails.getEndDate());
        training.setStartDate(trainingDetails.getStartDate());
        training.setNumberOfTrainees(trainingDetails.getNumberOfTrainees());
        if (trainingDetails.getAttendanceSheet() != null) {
            training.setAttendanceSheet(trainingDetails.getAttendanceSheet());
        }
        if (trainingDetails.getTopic() != null) {
            training.setTopic(getEm().getReference(Topic.class, trainingDetails.getTopic().getId()));
        }
        if (trainingDetails.getCategoryOfTrainees() != null) {
            training.setCategoryOfTrainees(getEm().getReference(Phenomenon.class,
                    trainingDetails.getCategoryOfTrainees().getId()));
        }
        training.setVenue(getEm().getReference(Location.class, trainingDetails.getVenue().getId()));

        try {
            getEm().merge(training);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeTraining(int id) throws MilesException {
        Training training = getEm().find(Training.class, id);
        try {
            getEm().remove(training);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public TrainingDetails convertTrainingToTrainingDetails(Training training) {

        TrainingDetails trainingDetails = new TrainingDetails(training.getId());

        trainingDetails.setEndDate(training.getEndDate());
        trainingDetails.setStartDate(training.getStartDate());
        trainingDetails.setAttendanceSheet(training.getAttendanceSheet());
        trainingDetails.setNumberOfTrainees(training.getNumberOfTrainees());
        trainingDetails.setTopic(topicService.convertTopicToTopicDetails(training.getTopic()));
        if (training.getVenue() != null) {
            trainingDetails.setVenue(locationService.convertLocationToLocationDetails(training.getVenue()));
        }
        if (training.getCategoryOfTrainees() != null) {
            trainingDetails.setCategoryOfTrainees(phenomenonService.convertPhenomenonToPhenomenonDetails(training.getCategoryOfTrainees()));
        }

        if (trainingDetails.getAttendanceSheet() != null) {
            try {
                String[] folders = trainingDetails.getAttendanceSheet().split(File.separator);
                String fileName = folders[folders.length - 1];
                trainingDetails.setFileName(fileName);
            } catch (Exception e) {
            }
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
    private TopicRequestsLocal topicService;
    @EJB
    private LocationRequestsLocal locationService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;

}
