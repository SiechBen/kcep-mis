/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activity;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.debugger.Debugger;
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
        } else if (activityDetails.getName() == null) {
            throw new InvalidArgumentException("error_032_02");
        } else if (activityDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_032_03");
        }

        Activity activity;
        q = em.createNamedQuery("Activity.findByName");
        q.setParameter("name", activityDetails.getName());
        try {
            activity = (Activity) q.getSingleResult();
        } catch (Exception e) {
            activity = null;
        }
        if (activity != null) {
            throw new InvalidArgumentException("error_032_04");
        }

        activity = new Activity();
        activity.setName(activityDetails.getName());

        try {
            em.persist(activity);
            em.flush();
        } catch (Exception e) {
            Debugger.debug(e);
            throw new InvalidStateException("error_000_01");
        }

        return activity.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ActivityDetails> retrieveActivities() throws MilesException {
        List<Activity> activities = new ArrayList<>();
        q = em.createNamedQuery("Activity.findAll");
        try {
            activities = q.getResultList();
        } catch (Exception e) {
        }

        return convertActivitiesToActivityDetailsList(activities);
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
        } else if (activityDetails.getName() == null) {
            throw new InvalidArgumentException("error_032_02");
        } else if (activityDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_032_03");
        }

        Activity activity;
        q = em.createNamedQuery("Activity.findByName");
        q.setParameter("name", activityDetails.getName());
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
        activity.setName(activityDetails.getName());

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
            activityDetails.setName(activity.getName());
        } catch (Exception e) {
        }
        return activityDetails;

    }

    private List<ActivityDetails> convertActivitiesToActivityDetailsList(List<Activity> activities) {

        List<ActivityDetails> activityDetailsList = new ArrayList<>();
        for (Activity activity : activities) {
            activityDetailsList.add(convertActivityToActivityDetails(activity));
        }

        return activityDetailsList;

    }

//</editor-fold>
}
