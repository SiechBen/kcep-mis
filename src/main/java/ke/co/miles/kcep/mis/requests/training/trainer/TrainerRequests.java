/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.trainer;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.Trainer;
import ke.co.miles.kcep.mis.entities.Training;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.TrainingRequestsLocal;
import ke.co.miles.kcep.mis.utilities.TrainerDetails;

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
            throw new InvalidArgumentException("error_010_01");
        } else if (trainerDetails.getPerson() == null) {
            throw new InvalidArgumentException("error_010_02");
        } else if (trainerDetails.getTraining() == null) {
            throw new InvalidArgumentException("error_010_03");
        }

        Trainer trainer = new Trainer();
        trainer.setPerson(em.find(Person.class, trainerDetails.getPerson().getId()));
        trainer.setTraining(em.find(Training.class, trainerDetails.getTraining().getId()));

        try {
            em.persist(trainer);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    @Override
    public void addTrainers(List<TrainerDetails> trainerDetailsList) throws MilesException {
        for (TrainerDetails trainerDetails : trainerDetailsList) {
            addTrainer(trainerDetails);
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
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
            throw new InvalidArgumentException("error_010_01");
        } else if (trainerDetails.getId() == null) {
            throw new InvalidArgumentException("error_010_04");
        } else if (trainerDetails.getPerson() == null) {
            throw new InvalidArgumentException("error_010_02");
        } else if (trainerDetails.getTraining() == null) {
            throw new InvalidArgumentException("error_010_03");
        }

        Trainer trainer = new Trainer();
        trainer.setId(trainerDetails.getId());
        trainer.setPerson(em.find(Person.class, trainerDetails.getPerson().getId()));
        trainer.setTraining(em.find(Training.class, trainerDetails.getTraining().getId()));

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
        trainerDetails.setPerson(personService.convertPersonToPersonDetails(trainer.getPerson()));
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
    private PersonRequestsLocal personService;
    @EJB
    private TrainingRequestsLocal trainingService;
}
