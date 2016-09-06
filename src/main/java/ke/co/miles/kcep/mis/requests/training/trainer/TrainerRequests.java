/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.trainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.Trainer;
import ke.co.miles.kcep.mis.entities.Training;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.TrainingRequestsLocal;
import ke.co.miles.kcep.mis.utilities.TrainerDetails;
import ke.co.miles.kcep.mis.utilities.TrainingDetails;

/**
 *
 * @author siech
 */
@Stateless
public class TrainerRequests extends EntityRequests implements TrainerRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public void addTrainer(TrainerDetails trainerDetails) throws MilesException {

        if (trainerDetails == null) {
            throw new InvalidArgumentException("error_028_01");
        } else if (trainerDetails.getPhenomenon() == null) {
            throw new InvalidArgumentException("error_028_02");
        } else if (trainerDetails.getTraining() == null) {
            throw new InvalidArgumentException("error_028_03");
        }

        Trainer trainer = new Trainer();
        trainer.setPhenomenon(em.getReference(Phenomenon.class, trainerDetails.getPhenomenon().getId()));
        trainer.setTraining(em.getReference(Training.class, trainerDetails.getTraining().getId()));

        try {
            em.persist(trainer);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    @Override
    public void addTrainers(List<TrainerDetails> trainerDetailsList) throws MilesException {
        for (TrainerDetails trainerDetails : trainerDetailsList) {
            addTrainer(trainerDetails);
        }
        em.flush();
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveWardTrainings(short wardId) throws MilesException {

        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findByWardId");
        q.setParameter("wardId", wardId);
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<TrainingDetails, List<TrainerDetails>> trainingMap = new HashMap<>();

        for (Training training : trainings) {
            trainingMap.put(trainingService.convertTrainingToTrainingDetails(training), retrieveTrainers(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveCountyTrainings(short countyId) throws MilesException {

        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findByCountyId");
        q.setParameter("countyId", countyId);
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<TrainingDetails, List<TrainerDetails>> trainingMap = new HashMap<>();

        for (Training training : trainings) {
            trainingMap.put(trainingService.convertTrainingToTrainingDetails(training), retrieveTrainers(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveSubCountyTrainings(short subCountyId) throws MilesException {

        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findBySubCountyId");
        q.setParameter("subCountyId", subCountyId);
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<TrainingDetails, List<TrainerDetails>> trainingMap = new HashMap<>();

        for (Training training : trainings) {
            trainingMap.put(trainingService.convertTrainingToTrainingDetails(training), retrieveTrainers(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveTrainings() throws MilesException {

        List<Training> trainings = new ArrayList<>();
        q = em.createNamedQuery("Training.findAll");
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<TrainingDetails, List<TrainerDetails>> trainingMap = new HashMap<>();

        for (Training training : trainings) {
            trainingMap.put(trainingService.convertTrainingToTrainingDetails(training), retrieveTrainers(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TrainerDetails> retrieveTrainers(int trainingId) throws MilesException {
        List<Trainer> trainers = new ArrayList<>();
        q = em.createNamedQuery("Trainer.findByTrainingId");
        q.setParameter("trainingId", trainingId);
        try {
            trainers = q.getResultList();
        } catch (Exception e) {
        }

        return convertTrainersToTrainerDetailsList(trainers);
    }

    @Override
    public TrainerDetails retrieveTrainer(int id) throws MilesException {
        Trainer trainer;
        q = em.createNamedQuery("Trainer.findById");
        q.setParameter("id", id);
        try {
            trainer = (Trainer) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertTrainerToTrainerDetails(trainer);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editTrainer(TrainerDetails trainerDetails) throws MilesException {

        if (trainerDetails == null) {
            throw new InvalidArgumentException("error_028_01");
        } else if (trainerDetails.getId() == null) {
            throw new InvalidArgumentException("error_028_04");
        } else if (trainerDetails.getPhenomenon() == null) {
            throw new InvalidArgumentException("error_028_02");
        } else if (trainerDetails.getTraining() == null) {
            throw new InvalidArgumentException("error_028_03");
        }

        Trainer trainer = new Trainer();
        trainer.setId(trainerDetails.getId());
        trainer.setPhenomenon(em.getReference(Phenomenon.class, trainerDetails.getPhenomenon().getId()));
        trainer.setTraining(em.getReference(Training.class, trainerDetails.getTraining().getId()));

        try {
            em.merge(trainer);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeTrainer(int id) throws MilesException {
        Trainer trainer = em.find(Trainer.class, id);
        try {
            em.remove(trainer);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public TrainerDetails convertTrainerToTrainerDetails(Trainer trainer) {

        TrainerDetails trainerDetails = new TrainerDetails(trainer.getId());
        trainerDetails.setPhenomenon(phenomenonService.convertPhenomenonToPhenomenonDetails(trainer.getPhenomenon()));
        trainerDetails.setTraining(trainingService.convertTrainingToTrainingDetails(trainer.getTraining()));

        return trainerDetails;

    }

    private List<TrainerDetails> convertTrainersToTrainerDetailsList(List<Trainer> trainers) {

        List<TrainerDetails> trainerDetailsList = new ArrayList<>();
        for (Trainer trainer : trainers) {
            trainerDetailsList.add(convertTrainerToTrainerDetails(trainer));
        }

        return trainerDetailsList;

    }

//</editor-fold>
    @EJB
    private PhenomenonRequestsLocal phenomenonService;
    @EJB
    private TrainingRequestsLocal trainingService;
}
