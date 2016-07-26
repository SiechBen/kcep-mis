/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.name;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ActivityName;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ActivityNameDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ActivityNameRequests extends EntityRequests implements ActivityNameRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addActivityName(ActivityNameDetails activityNameDetails) throws MilesException {

        if (activityNameDetails == null) {
            throw new InvalidArgumentException("error_032_01");
        } else if (activityNameDetails.getName() == null) {
            throw new InvalidArgumentException("error_032_02");
        } else if (activityNameDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_032_03");
        }

        ActivityName activityName;
        q = em.createNamedQuery("ActivityName.findByName");
        q.setParameter("name", activityNameDetails.getName());
        try {
            activityName = (ActivityName) q.getSingleResult();
        } catch (Exception e) {
            activityName = null;
        }
        if (activityName != null) {
            throw new InvalidArgumentException("error_032_04");
        }

        activityName = new ActivityName();
        activityName.setName(activityNameDetails.getName());

        try {
            em.persist(activityName);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(e);
            throw new InvalidStateException("error_000_01");
        }

        return activityName.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ActivityNameDetails> retrieveActivityNames() throws MilesException {
        List<ActivityName> activities = new ArrayList<>();
        q = em.createNamedQuery("ActivityName.findAll");
        try {
            activities = q.getResultList();
        } catch (Exception e) {
        }

        return convertActivitiesToActivityNameDetailsList(activities);
    }

    @Override
    public ActivityNameDetails retrieveActivityName(int id) throws MilesException {
        ActivityName activityName;
        q = em.createNamedQuery("ActivityName.findById");
        q.setParameter("id", id);
        try {
            activityName = (ActivityName) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertActivityNameToActivityNameDetails(activityName);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editActivityName(ActivityNameDetails activityNameDetails) throws MilesException {

        if (activityNameDetails == null) {
            throw new InvalidArgumentException("error_032_01");
        } else if (activityNameDetails.getId() == null) {
            throw new InvalidArgumentException("error_032_05");
        } else if (activityNameDetails.getName() == null) {
            throw new InvalidArgumentException("error_032_02");
        } else if (activityNameDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_032_03");
        }

        ActivityName activityName;
        q = em.createNamedQuery("ActivityName.findByName");
        q.setParameter("name", activityNameDetails.getName());
        try {
            activityName = (ActivityName) q.getSingleResult();
        } catch (Exception e) {
            activityName = null;
        }
        if (activityName != null) {
            if (activityName.getId().equals(activityNameDetails.getId())) {
                throw new InvalidArgumentException("error_032_04");
            }
        }

        activityName = em.find(ActivityName.class, activityNameDetails.getId());
        activityName.setId(activityNameDetails.getId());
        activityName.setName(activityNameDetails.getName());

        try {
            em.merge(activityName);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeActivityName(int id) throws MilesException {
        ActivityName activityName = em.find(ActivityName.class, id);
        try {
            em.remove(activityName);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ActivityNameDetails convertActivityNameToActivityNameDetails(ActivityName activityName) {

        ActivityNameDetails activityNameDetails = new ActivityNameDetails();
        try {
            activityNameDetails.setId(activityName.getId());
        } catch (Exception e) {
        }
        try {
            activityNameDetails.setName(activityName.getName());
        } catch (Exception e) {
        }
        return activityNameDetails;

    }

    private List<ActivityNameDetails> convertActivitiesToActivityNameDetailsList(List<ActivityName> activities) {

        List<ActivityNameDetails> activityNameDetailsList = new ArrayList<>();
        for (ActivityName activityName : activities) {
            activityNameDetailsList.add(convertActivityNameToActivityNameDetails(activityName));
        }

        return activityNameDetailsList;

    }

//</editor-fold>
}
