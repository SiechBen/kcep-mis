/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.progress.comment;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ActivityProgressComment;
import ke.co.miles.kcep.mis.entities.SubActivity;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityProgressCommentDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ActivityProgressCommentRequests extends EntityRequests implements ActivityProgressCommentRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addActivityProgressComment(ActivityProgressCommentDetails activityProgressCommentDetails) throws MilesException {

        if (activityProgressCommentDetails == null) {
            throw new InvalidArgumentException("error_058_01");
        } else if (activityProgressCommentDetails.getSubActivity() == null) {
            throw new InvalidArgumentException("error_058_02");
        } else if (activityProgressCommentDetails.getComment() != null
                && activityProgressCommentDetails.getComment().length() > 65535) {
            throw new InvalidArgumentException("error_058_03");
        }

        ActivityProgressComment activityProgressComment = new ActivityProgressComment();
        activityProgressComment.setComment(activityProgressCommentDetails.getComment());
        activityProgressComment.setSubActivity(em.getReference(SubActivity.class, activityProgressCommentDetails.getSubActivity().getId()));

        try {
            em.persist(activityProgressComment);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return activityProgressComment.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ActivityProgressCommentDetails> retrieveActivityProgressComments() throws MilesException {
        List<ActivityProgressComment> activityProgressComments = new ArrayList<>();
        setQ(em.createNamedQuery("ActivityProgressComment.findAll"));
        try {
            activityProgressComments = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertActivityProgressCommentsToActivityProgressCommentDetailsList(activityProgressComments);
    }

    @Override
    public ActivityProgressCommentDetails retrieveActivityProgressComment(int id) throws MilesException {
        ActivityProgressComment activityProgressComment;
        setQ(em.createNamedQuery("ActivityProgressComment.findById"));
        q.setParameter("id", id);
        try {
            activityProgressComment = (ActivityProgressComment) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertActivityProgressCommentToActivityProgressCommentDetails(activityProgressComment);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editActivityProgressComment(ActivityProgressCommentDetails activityProgressCommentDetails) throws MilesException {

        if (activityProgressCommentDetails == null) {
            throw new InvalidArgumentException("error_058_01");
        } else if (activityProgressCommentDetails.getId() == null) {
            throw new InvalidArgumentException("error_058_04");
        } else if (activityProgressCommentDetails.getComment() != null
                && activityProgressCommentDetails.getComment().length() > 65535) {
            throw new InvalidArgumentException("error_058_03");
        }

        ActivityProgressComment activityProgressComment = em.find(ActivityProgressComment.class, activityProgressCommentDetails.getId());
        activityProgressComment.setComment(activityProgressCommentDetails.getComment());

        try {
            em.merge(activityProgressComment);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeActivityProgressComment(int id) throws MilesException {
        ActivityProgressComment activityProgressComment = em.find(ActivityProgressComment.class, id);
        try {
            em.remove(activityProgressComment);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ActivityProgressCommentDetails convertActivityProgressCommentToActivityProgressCommentDetails(ActivityProgressComment activityProgressComment) {

        ActivityProgressCommentDetails activityProgressCommentDetails = new ActivityProgressCommentDetails();
        try {
            activityProgressCommentDetails.setId(activityProgressComment.getId());
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }
        try {
            activityProgressCommentDetails.setSubActivity(subActivityService.
                    convertSubActivityToSubActivityDetails(activityProgressComment.getSubActivity()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }
        activityProgressCommentDetails.setComment(activityProgressComment.getComment());
        return activityProgressCommentDetails;

    }

    private List<ActivityProgressCommentDetails> convertActivityProgressCommentsToActivityProgressCommentDetailsList(List<ActivityProgressComment> activityProgressComments) {

        List<ActivityProgressCommentDetails> activityProgressCommentDetailsList = new ArrayList<>();
        for (ActivityProgressComment activityProgressComment : activityProgressComments) {
            activityProgressCommentDetailsList.add(convertActivityProgressCommentToActivityProgressCommentDetails(activityProgressComment));
        }

        return activityProgressCommentDetailsList;

    }

//</editor-fold>
    @EJB
    SubActivityRequestsLocal subActivityService;
}
