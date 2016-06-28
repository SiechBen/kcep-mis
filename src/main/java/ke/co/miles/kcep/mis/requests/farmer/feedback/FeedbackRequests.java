/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.feedback;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Feedback;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.FeedbackDetails;

/**
 *
 * @author siech
 */
@Stateless
public class FeedbackRequests extends EntityRequests implements FeedbackRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addFeedback(FeedbackDetails feedbackDetails) throws MilesException {

        if (feedbackDetails == null) {
            throw new InvalidArgumentException("error_031_01");
        } else if (feedbackDetails.getMessage() == null) {
            throw new InvalidArgumentException("error_031_02");
        } else if (feedbackDetails.getFarmer() == null) {
            throw new InvalidArgumentException("error_031_03");
        }

        Feedback feedback = new Feedback();
        feedback.setMessage(feedbackDetails.getMessage());
        feedback.setTimePosted(feedbackDetails.getTimePosted());
        feedback.setFarmer(em.find(Person.class, feedbackDetails.getFarmer().getId()));

        try {
            em.persist(feedback);
            em.flush();
        } catch (Exception e) {
            System.out.println(e);
            throw new InvalidStateException("error_000_01");
        }

        return feedback.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveWardFeedback(int wardId) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        q = em.createNamedQuery("Feedback.findByWardId");
        q.setParameter("wardId", wardId);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveRegionFeedback(int regionId) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        q = em.createNamedQuery("Feedback.findByRegionId");
        q.setParameter("regionId", regionId);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveCountyFeedback(short countyId) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        q = em.createNamedQuery("Feedback.findByCountyId");
        q.setParameter("countyId", countyId);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveSubCountyFeedback(int subCountyId) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        q = em.createNamedQuery("Feedback.findBySubCountyId");
        q.setParameter("subCountyId", subCountyId);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveLatestFeedback() throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        q = em.createNamedQuery("Feedback.findLatest");
        q.setMaxResults(3);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveFeedback() throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        q = em.createNamedQuery("Feedback.findAll");
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    public FeedbackDetails retrieveFeedback(int id) throws MilesException {
        Feedback feedback;
        q = em.createNamedQuery("Feedback.findById");
        q.setParameter("id", id);
        try {
            feedback = (Feedback) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertFeedbackToFeedbackDetails(feedback);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editFeedback(FeedbackDetails feedbackDetails) throws MilesException {

        if (feedbackDetails == null) {
            throw new InvalidArgumentException("error_031_01");
        } else if (feedbackDetails.getId() == null) {
            throw new InvalidArgumentException("error_031_04");
        } else if (feedbackDetails.getMessage() == null) {
            throw new InvalidArgumentException("error_031_02");
        } else if (feedbackDetails.getFarmer() == null) {
            throw new InvalidArgumentException("error_031_03");
        }

        Feedback feedback = em.find(Feedback.class, feedbackDetails.getId());
        feedback.setId(feedbackDetails.getId());
        feedback.setMessage(feedbackDetails.getMessage());
        feedback.setTimePosted(feedbackDetails.getTimePosted());
        feedback.setFarmer(em.find(Person.class, feedbackDetails.getFarmer().getId()));

        try {
            em.merge(feedback);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeFeedback(int id) throws MilesException {
        Feedback feedback = em.find(Feedback.class, id);
        try {
            em.remove(feedback);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public FeedbackDetails convertFeedbackToFeedbackDetails(Feedback feedback) {

        FeedbackDetails feedbackDetails = new FeedbackDetails(feedback.getId());
        feedbackDetails.setFarmer(personService.convertPersonToPersonDetails(feedback.getFarmer()));
        feedbackDetails.setMessage(feedback.getMessage());
        feedbackDetails.setTimePosted(feedback.getTimePosted());
        StringBuilder shortMessage = new StringBuilder();
        if (feedbackDetails.getMessage().length() > 70) {
            shortMessage.append(feedbackDetails.getMessage().substring(0, 70));
            shortMessage.append("...");
        } else {
            shortMessage.append(feedbackDetails.getMessage());
        }
        feedbackDetails.setShortMessage(shortMessage.toString());

        return feedbackDetails;

    }

    private List<FeedbackDetails> convertFeedbacksToFeedbackDetailsList(List<Feedback> feedbackList) {

        List<FeedbackDetails> feedbackDetailsList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDetailsList.add(convertFeedbackToFeedbackDetails(feedback));
        }

        return feedbackDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
}
