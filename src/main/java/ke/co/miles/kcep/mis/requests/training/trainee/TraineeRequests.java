/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.trainee;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.FarmerGroup;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.Trainee;
import ke.co.miles.kcep.mis.entities.Training;
import ke.co.miles.kcep.mis.entities.UserAccount;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.TrainingRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SexDetail;
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
        Person person = trainee.getPerson();

        TrainingDetails trainingDetails = traineeDetails.getTraining();

        if (null != trainingDetails.getCategoryOfTrainees().getId() && trainingDetails.getCategoryOfTrainees().getId().equals(1)) {
            if (trainingDetails.getTopic() != null && trainingDetails.getTopic().getId() == 15) {
                if (null != person.getFarmerGroup() && !person.getFarmerGroup().getTrained()) {
                    MilesDebugger.debug();
                    MilesDebugger.debug(person.getFarmerGroup().getTrained());
                    try {
                        FarmerGroup farmerGroup = person.getFarmerGroup();
                        farmerGroup.setTrained(Boolean.TRUE);
                        em.merge(farmerGroup);

                        setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                        q.setParameter(1, 1);
                        q.setParameter(2, 30);
                        q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                        q.executeUpdate();
                    } catch (Exception e) {
                    }
                }
            }
        }

        if (trainee.getTraining().getTopic() != null && trainee.getTraining().getTopic().getId() == 11) {
            Date date = (null == person.getDateOfBirth() ? new Date() : person.getDateOfBirth());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            LocalDate birthDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            LocalDate now = LocalDate.now(ZoneId.of("Africa/Nairobi"));
            int age = Period.between(birthDate, now).getYears();
            if (person.getSex().getId().equals(SexDetail.FEMALE.getId())
                    || (person.getSex().getId().equals(SexDetail.MALE.getId())
                    && age <= 35)) {
                try {
                    setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                    q.setParameter(1, 1);
                    q.setParameter(2, 77);
                    q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                    q.executeUpdate();
                } catch (Exception e) {
                }
            }
        }

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

    @SuppressWarnings("unchecked")
    @Override
    public HashMap<String, Integer> countTrainees(PersonRoleDetail personRoleDetail, int trainingId) throws MilesException {

        String youthQuery = "SELECT * FROM trainee t INNER JOIN training tr on (tr.id = t.training ) WHERE tr.id = ?1 AND t.person IN (SELECT p.id FROM person p INNER JOIN user_account u ON (p.id = u.person) INNER JOIN sex s ON (s.id = p.sex) INNER JOIN person_role r ON (u.person_role = r.id) WHERE s.id = ?2 AND r.id = ?3 AND ((CASE WHEN (date_of_birth IS NULL) THEN 0 ELSE (TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) END) <= 35))";
        String elderlyQuery = "SELECT * FROM trainee t INNER JOIN training tr on (tr.id = t.training ) WHERE tr.id = ?1 AND t.person IN (SELECT p.id FROM person p INNER JOIN user_account u ON (p.id = u.person) INNER JOIN sex s ON (s.id = p.sex) INNER JOIN person_role r ON (u.person_role = r.id) WHERE s.id = ?2 AND r.id = ?3 AND ((CASE WHEN (date_of_birth IS NULL) THEN 0 ELSE (TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) END) > 35))";

        List<UserAccount> femaleYouth = new ArrayList<>();
        setQ(em.createNativeQuery(youthQuery, UserAccount.class));
        q.setParameter(1, trainingId);
        q.setParameter(2, SexDetail.FEMALE.getId());
        q.setParameter(3, personRoleDetail.getId());
        try {
            femaleYouth = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<UserAccount> maleYouth = new ArrayList<>();
        setQ(em.createNativeQuery(youthQuery, UserAccount.class));
        q.setParameter(1, trainingId);
        q.setParameter(2, SexDetail.MALE.getId());
        q.setParameter(3, personRoleDetail.getId());
        try {
            maleYouth = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<UserAccount> femaleElderly = new ArrayList<>();
        setQ(em.createNativeQuery(elderlyQuery, UserAccount.class));
        q.setParameter(1, trainingId);
        q.setParameter(2, SexDetail.FEMALE.getId());
        q.setParameter(3, personRoleDetail.getId());
        try {
            femaleElderly = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<UserAccount> maleElderly = new ArrayList<>();
        setQ(em.createNativeQuery(elderlyQuery, UserAccount.class));
        q.setParameter(1, trainingId);
        q.setParameter(2, SexDetail.MALE.getId());
        q.setParameter(3, personRoleDetail.getId());
        try {
            maleElderly = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("Female youth", femaleYouth.size());
        countMap.put("Female elderly", femaleElderly.size());
        countMap.put("Female total", femaleYouth.size() + femaleElderly.size());
        countMap.put("Male youth", maleYouth.size());
        countMap.put("Male elderly", maleElderly.size());
        countMap.put("Male total", maleYouth.size() + maleElderly.size());
        countMap.put("Total people", countMap.get("Female total") + countMap.get("Male total"));

        return countMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public HashMap<String, Integer> countAllTrainees(int trainingId) throws MilesException {

        String youthQuery = "SELECT * FROM trainee t INNER JOIN training tr on (tr.id = t.training ) WHERE tr.id = ?1 AND t.person IN (SELECT p.id FROM person p INNER JOIN user_account u ON (p.id = u.person) INNER JOIN sex s ON (s.id = p.sex) INNER JOIN person_role r ON (u.person_role = r.id) WHERE s.id = ?2 AND ((CASE WHEN (date_of_birth IS NULL) THEN 0 ELSE (TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) END) <= 35))";
        String elderlyQuery = "SELECT * FROM trainee t INNER JOIN training tr on (tr.id = t.training ) WHERE tr.id = ?1 AND t.person IN (SELECT p.id FROM person p INNER JOIN user_account u ON (p.id = u.person) INNER JOIN sex s ON (s.id = p.sex) INNER JOIN person_role r ON (u.person_role = r.id) WHERE s.id = ?2 AND ((CASE WHEN (date_of_birth IS NULL) THEN 0 ELSE (TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) END) > 35))";

        List<UserAccount> femaleYouth = new ArrayList<>();
        setQ(em.createNativeQuery(youthQuery, UserAccount.class));
        q.setParameter(1, trainingId);
        q.setParameter(2, SexDetail.FEMALE.getId());
        try {
            femaleYouth = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<UserAccount> maleYouth = new ArrayList<>();
        setQ(em.createNativeQuery(youthQuery, UserAccount.class));
        q.setParameter(1, trainingId);
        q.setParameter(2, SexDetail.MALE.getId());
        try {
            maleYouth = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<UserAccount> femaleElderly = new ArrayList<>();
        setQ(em.createNativeQuery(elderlyQuery, UserAccount.class));
        q.setParameter(1, trainingId);
        q.setParameter(2, SexDetail.FEMALE.getId());
        try {
            femaleElderly = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        List<UserAccount> maleElderly = new ArrayList<>();
        setQ(em.createNativeQuery(elderlyQuery, UserAccount.class));
        q.setParameter(1, trainingId);
        q.setParameter(2, SexDetail.MALE.getId());
        try {
            maleElderly = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("Female youth", femaleYouth.size());
        countMap.put("Female elderly", femaleElderly.size());
        countMap.put("Female total", femaleYouth.size() + femaleElderly.size());
        countMap.put("Male youth", maleYouth.size());
        countMap.put("Male elderly", maleElderly.size());
        countMap.put("Male total", maleYouth.size() + maleElderly.size());
        countMap.put("Total people", countMap.get("Female total") + countMap.get("Male total"));

        return countMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<TrainingDetails, List<TraineeDetails>> retrieveWardTrainings(int wardId) throws MilesException {

        List<Training> trainings = new ArrayList<>();
        setQ(em.createNamedQuery("Training.findByWardId"));
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
        setQ(em.createNamedQuery("Training.findByCountyId"));
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
        setQ(em.createNamedQuery("Training.findBySubCountyId"));
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
        setQ(em.createNamedQuery("Training.findAll"));
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
        setQ(em.createNamedQuery("Trainee.findByTrainingId"));
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
        setQ(em.createNamedQuery("Trainee.findById"));
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
    public void editTrainees(List<TraineeDetails> traineeDetailsList) throws MilesException {
        for (TraineeDetails traineeDetails : traineeDetailsList) {
            editTrainee(traineeDetails);
        }
        em.flush();
    }

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
