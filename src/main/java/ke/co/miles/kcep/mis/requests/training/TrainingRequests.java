/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
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
            training.setTopic(em.getReference(Topic.class, trainingDetails.getTopic().getId()));
        }
        training.setVenue(locationService.addLocation(trainingDetails.getVenue()));

        if (trainingDetails.getCategoryOfTrainees() != null) {

            training.setCategoryOfTrainees(em.getReference(Phenomenon.class, trainingDetails.getCategoryOfTrainees().getId()));

            if (null != training.getCategoryOfTrainees().getId()) {
                switch (training.getCategoryOfTrainees().getId()) {
                    case 1:
                        if (training.getTopic().getModule() != null && training.getTopic().getModule().getId() == 20) {
                            try {
                                setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                                q.setParameter(1, training.getNumberOfTrainees());
                                q.setParameter(2, 44);
                                q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                                q.executeUpdate();
                            } catch (Exception e) {
                            }
                        }
                        try {
                            setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                            q.setParameter(1, training.getNumberOfTrainees());
                            q.setParameter(2, 69);
                            q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                            q.executeUpdate();
                        } catch (Exception e) {
                        }
                        break;
                    case 2:
                        try {
                            setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                            q.setParameter(1, training.getNumberOfTrainees());
                            q.setParameter(2, 24);
                            q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                            q.executeUpdate();
                        } catch (Exception e) {
                        }
                        break;
                    case 70:
                    case 71:
                    case 72:
                        try {
                            setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                            q.setParameter(1, training.getNumberOfTrainees());
                            q.setParameter(2, 25);
                            q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                            q.executeUpdate();
                            MilesDebugger.debug();
                        } catch (Exception e) {
                        }
                        break;
                    case 4:
                        try {
                            setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                            q.setParameter(1, training.getNumberOfTrainees());
                            q.setParameter(2, 25);
                            q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                            q.executeUpdate();
                            MilesDebugger.debug();
                        } catch (Exception e) {
                        }
                        try {
                            setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                            q.setParameter(1, training.getNumberOfTrainees());
                            q.setParameter(2, 25);
                            q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                            q.executeUpdate();
                        } catch (Exception e) {
                        }
                        try {
                            setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                            q.setParameter(1, training.getNumberOfTrainees());
                            q.setParameter(2, 68);
                            q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                            q.executeUpdate();
                        } catch (Exception e) {
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (training.getTopic() != null) {
            if (training.getTopic().getId() == 6 || training.getTopic().getId() == 7 || training.getTopic().getId() == 16 || training.getTopic().getId() == 17 || training.getTopic().getId() == 18 || training.getTopic().getId() == 19) {
                try {
                    setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                    q.setParameter(1, training.getNumberOfTrainees());
                    q.setParameter(2, 26);
                    q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                    q.executeUpdate();
                    MilesDebugger.debug();
                } catch (Exception e) {
                }
            }
            if (training.getTopic().getId() == 10) {
                try {
                    setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                    q.setParameter(1, training.getNumberOfTrainees());
                    q.setParameter(2, 45);
                    q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                    q.executeUpdate();
                } catch (Exception e) {
                }
            }
            if (training.getTopic().getModule() != null && training.getTopic().getModule().getId() == 20) {
                try {
                    setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                    q.setParameter(1, training.getNumberOfTrainees());
                    q.setParameter(2, 44);
                    q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                    q.executeUpdate();
                } catch (Exception e) {
                }
            }
        }

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
    public TrainingDetails retrieveTraining(int id) throws MilesException {
        Training training;
        setQ(em.createNamedQuery("Training.findById"));
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
        training.setEndDate(trainingDetails.getEndDate());
        training.setStartDate(trainingDetails.getStartDate());
        training.setNumberOfTrainees(trainingDetails.getNumberOfTrainees());
        if (trainingDetails.getAttendanceSheet() != null) {
            training.setAttendanceSheet(trainingDetails.getAttendanceSheet());
        }
        if (trainingDetails.getTopic() != null) {
            training.setTopic(em.getReference(Topic.class, trainingDetails.getTopic().getId()));
        }
        if (trainingDetails.getCategoryOfTrainees() != null) {
            training.setCategoryOfTrainees(em.getReference(Phenomenon.class,
                    trainingDetails.getCategoryOfTrainees().getId()));
        }
        training.setVenue(em.getReference(Location.class, trainingDetails.getVenue().getId()));

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

    @Override
    public TrainingDetails convertTrainingToTrainingDetails(Training training
    ) {

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
