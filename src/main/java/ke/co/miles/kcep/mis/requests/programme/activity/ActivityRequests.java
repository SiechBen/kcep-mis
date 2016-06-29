/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.programme.activity;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Activity;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ActivityDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ActivityRequests extends EntityRequests implements ActivityRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addActivity(ActivityDetails activityDetails) throws MilesException {

        if (activityDetails == null) {
            throw new InvalidArgumentException("error_032_01");
        } else if (activityDetails.getDescription() == null) {
            throw new InvalidArgumentException("error_032_02");
        } else if (activityDetails.getDescription().length() > 45) {
            throw new InvalidArgumentException("error_032_03");
        }

        Activity activity;
        q = em.createNamedQuery("Activity.findByDescription");
        q.setParameter("description", activityDetails.getDescription());
        try {
            activity = (Activity) q.getSingleResult();
        } catch (Exception e) {
            activity = null;
        }
        if (activity != null) {
            throw new InvalidArgumentException("error_032_04");
        }

        activity = new Activity();
        activity.setDescription(activityDetails.getDescription());

        try {
            em.persist(activity);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return activity.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<ActivityDetails> retrieveActivitys() throws MilesException {
        List<Activity> activitys = new ArrayList<>();
        q = em.createNamedQuery("Activity.findAll");
        try {
            activitys = q.getResultList();
        } catch (Exception e) {
        }

        return convertActivitysToActivityDetailsList(activitys);
    }

    @Override
    public ActivityDetails retrieveActivity(int id) throws MilesException {
        Activity activity;
        q = em.createNamedQuery("Activity.findById");
        q.setParameter("id", id);
        try {
            activity = (Activity) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertActivityToActivityDetails(activity);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editActivity(ActivityDetails activityDetails) throws MilesException {

        if (activityDetails == null) {
            throw new InvalidArgumentException("error_032_01");
        } else if (activityDetails.getId() == null) {
            throw new InvalidArgumentException("error_032_05");
        } else if (activityDetails.getDescription() == null) {
            throw new InvalidArgumentException("error_032_02");
        } else if (activityDetails.getDescription().length() > 45) {
            throw new InvalidArgumentException("error_032_03");
        }

        Activity activity;
        q = em.createNamedQuery("Activity.findByDescription");
        q.setParameter("description", activityDetails.getDescription());
        try {
            activity = (Activity) q.getSingleResult();
        } catch (Exception e) {
            activity = null;
        }
        if (activity != null) {
            if (activity.getId().equals(activityDetails.getId())) {
                throw new InvalidArgumentException("error_032_04");
            }
        }

        activity = em.find(Activity.class, activityDetails.getId());
        activity.setId(activityDetails.getId());
        activity.setDescription(activityDetails.getDescription());

        try {
            em.merge(activity);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeActivity(int id) throws MilesException {
        Activity activity = em.find(Activity.class, id);
        try {
            em.remove(activity);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ActivityDetails convertActivityToActivityDetails(Activity activity) {

        ActivityDetails activityDetails = new ActivityDetails();
        try {
            activityDetails.setId(activity.getId());
        } catch (Exception e) {
        }
        try {
            activityDetails.setDescription(activity.getDescription());
        } catch (Exception e) {
        }
        return activityDetails;

    }

    private List<ActivityDetails> convertActivitysToActivityDetailsList(List<Activity> activitys) {

        List<ActivityDetails> activityDetailsList = new ArrayList<>();
        for (Activity activity : activitys) {
            activityDetailsList.add(convertActivityToActivityDetails(activity));
        }

        return activityDetailsList;

    }

//</editor-fold>
}
