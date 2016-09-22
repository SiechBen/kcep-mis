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
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
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
        trainer.setPhenomenon(getEm().getReference(Phenomenon.class, trainerDetails.getPhenomenon().getId()));
        trainer.setTraining(getEm().getReference(Training.class, trainerDetails.getTraining().getId()));

        try {
            getEm().persist(trainer);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    @Override
    public void addTrainers(List<TrainerDetails> trainerDetailsList) throws MilesException {
        for (TrainerDetails trainerDetails : trainerDetailsList) {
            addTrainer(trainerDetails);
        }
        getEm().flush();
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveWardTrainings(short wardId) throws MilesException {

        List<Training> trainings = new ArrayList<>();
        setQ(getEm().createNamedQuery("Training.findByWardId"));
        getQ().setParameter("wardId", wardId);
        try {
            trainings = getQ().getResultList();
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
        setQ(getEm().createNamedQuery("Training.findByCountyId"));
        getQ().setParameter("countyId", countyId);
        try {
            trainings = getQ().getResultList();
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
        setQ(getEm().createNamedQuery("Training.findBySubCountyId"));
        getQ().setParameter("subCountyId", subCountyId);
        try {
            trainings = getQ().getResultList();
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
        setQ(getEm().createNamedQuery("Training.findAll"));
        try {
            trainings = getQ().getResultList();
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
    public HashMap<TrainingDetails, List<TrainerDetails>> retrieveEquityTrainings() throws MilesException {

        List<TrainerDetails> trainers = retrieveEquityTrainers();
        List<Integer> trainerIds = new ArrayList<>();
        for (TrainerDetails trainer : trainers) {
            trainerIds.add(trainer.getId());
        }

        List<Training> trainings = new ArrayList<>();
        setQ(getEm().createNamedQuery("Training.findByIds"));
        getQ().setParameter("ids", trainerIds);
        try {
            trainings = getQ().getResultList();
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
        setQ(getEm().createNamedQuery("Trainer.findByTrainingId"));
        getQ().setParameter("trainingId", trainingId);
        try {
            trainers = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertTrainersToTrainerDetailsList(trainers);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TrainerDetails> retrieveEquityTrainers() throws MilesException {
        List<Trainer> trainers = new ArrayList<>();
        setQ(getEm().createNamedQuery("Trainer.findByPhenomenonIds"));
        List<Integer> phenomenonIds = new ArrayList<>();
        phenomenonIds.add(6);
        phenomenonIds.add(7);
        getQ().setParameter("phenomenonIds", phenomenonIds);
        try {
            trainers = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertTrainersToTrainerDetailsList(trainers);
    }

    @Override
    public TrainerDetails retrieveTrainer(int id) throws MilesException {
        Trainer trainer;
        setQ(getEm().createNamedQuery("Trainer.findById"));
        getQ().setParameter("id", id);
        try {
            trainer = (Trainer) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertTrainerToTrainerDetails(trainer);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editTrainers(List<TrainerDetails> trainerDetailsList) throws MilesException {
        for (TrainerDetails trainerDetails : trainerDetailsList) {
            editTrainer(trainerDetails);
        }
        getEm().flush();
    }

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
        trainer.setPhenomenon(getEm().getReference(Phenomenon.class, trainerDetails.getPhenomenon().getId()));
        trainer.setTraining(getEm().getReference(Training.class, trainerDetails.getTraining().getId()));

        try {
            getEm().merge(trainer);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeTrainer(int id) throws MilesException {
        Trainer trainer = getEm().find(Trainer.class, id);
        try {
            getEm().remove(trainer);
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
