/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.feedback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Feedback;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.FeedbackDetails;
import ke.co.miles.kcep.mis.utilities.FeedbackTypeDetail;

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
        }

        Feedback feedback = new Feedback();
        feedback.setMessage(feedbackDetails.getMessage());
        feedback.setTimePosted(feedbackDetails.getTimePosted());
        feedback.setAttachment(feedbackDetails.getAttachment());
        feedback.setFeedbackType(em.getReference(Phenomenon.class, feedbackDetails.getFeedbackType().getId()));
        if (feedbackDetails.getSubmitter() != null) {
            feedback.setSubmitter(em.getReference(Person.class, feedbackDetails.getSubmitter().getId()));
        }

        try {
            em.persist(feedback);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            System.out.println(e);
            throw new InvalidStateException("error_000_01");
        }

        return feedback.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveWardFeedback(FeedbackTypeDetail feedbackType, int wardId) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        setQ(em.createNamedQuery("Feedback.findByWardIdAndFeedbackTypeId"));
        q.setParameter("wardId", wardId);
        q.setParameter("feedbackTypeId", feedbackType.getId());
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveRegionFeedback(FeedbackTypeDetail feedbackType, int regionId) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        setQ(em.createNamedQuery("Feedback.findByRegionIdAndFeedbackTypeId"));
        q.setParameter("feedbackTypeId", feedbackType.getId());
        q.setParameter("regionId", regionId);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveCountyFeedback(FeedbackTypeDetail feedbackType, short countyId) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        setQ(em.createNamedQuery("Feedback.findByCountyIdAndFeedbackTypeId"));
        q.setParameter("feedbackTypeId", feedbackType.getId());
        q.setParameter("countyId", countyId);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveSubCountyFeedback(FeedbackTypeDetail feedbackType, int subCountyId) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        setQ(em.createNamedQuery("Feedback.findBySubCountyIdAndFeedbackTypeId"));
        q.setParameter("feedbackTypeId", feedbackType.getId());
        q.setParameter("subCountyId", subCountyId);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveLatestFeedback() throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        setQ(em.createNamedQuery("Feedback.findLatest"));
        q.setMaxResults(3);
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FeedbackDetails> retrieveFeedback(FeedbackTypeDetail feedbackType) throws MilesException {
        List<Feedback> feedbackList = new ArrayList<>();
        setQ(em.createNamedQuery("Feedback.findAllByFeedbackTypeId"));
        q.setParameter("feedbackTypeId", feedbackType.getId());
        try {
            feedbackList = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertFeedbacksToFeedbackDetailsList(feedbackList);
    }

    @Override
    public FeedbackDetails retrieveFeedback(int id) throws MilesException {
        Feedback feedback;
        setQ(em.createNamedQuery("Feedback.findById"));
        q.setParameter("id", id);
        try {
            feedback = (Feedback) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
        }

        Feedback feedback = em.getReference(Feedback.class, feedbackDetails.getId());
        feedback.setId(feedbackDetails.getId());
        feedback.setMessage(feedbackDetails.getMessage());
        feedback.setTimePosted(feedbackDetails.getTimePosted());
        feedback.setAttachment(feedbackDetails.getAttachment());
        feedback.setFeedbackType(em.getReference(Phenomenon.class, feedbackDetails.getFeedbackType().getId()));
        if (feedbackDetails.getSubmitter() != null) {
            feedback.setSubmitter(em.getReference(Person.class, feedbackDetails.getSubmitter().getId()));
        }

        try {
            em.merge(feedback);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeFeedback(int id) throws MilesException {
        Feedback feedback = em.getReference(Feedback.class, id);
        try {
            em.remove(feedback);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public FeedbackDetails convertFeedbackToFeedbackDetails(Feedback feedback) {

        FeedbackDetails feedbackDetails = new FeedbackDetails(feedback.getId());
        if (feedback.getSubmitter() != null) {
            feedbackDetails.setSubmitter(personService.convertPersonToPersonDetails(feedback.getSubmitter()));
        }
        if (feedback.getFeedbackType() != null) {
            feedbackDetails.setFeedbackType(FeedbackTypeDetail.getFeedbackTypeDetail(feedback.getFeedbackType().getId()));
        }
        feedbackDetails.setMessage(feedback.getMessage());
        feedbackDetails.setTimePosted(feedback.getTimePosted());
        StringBuilder shortMessage = new StringBuilder();
        if (feedbackDetails.getMessage().length() >= 70) {
            shortMessage.append(feedbackDetails.getMessage().substring(0, 65));
            shortMessage.append("...");
        } else {
            shortMessage.append(feedbackDetails.getMessage());
        }
        feedbackDetails.setShortMessage(shortMessage.toString());
        feedbackDetails.setAttachment(feedback.getAttachment());

        if (feedbackDetails.getAttachment() != null) {
            String[] folders = feedbackDetails.getAttachment().split(File.separator);
            String fileName = folders[folders.length - 1];
            feedbackDetails.setFileName(fileName);
        }

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
