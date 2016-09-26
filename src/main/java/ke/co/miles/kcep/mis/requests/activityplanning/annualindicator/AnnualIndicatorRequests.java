/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.annualindicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.AnnualIndicator;
import ke.co.miles.kcep.mis.entities.PerformanceIndicator;
import ke.co.miles.kcep.mis.entities.SubActivity;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.utilities.AnnualIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;

/**
 *
 * @author siech
 */
@Stateless
public class AnnualIndicatorRequests extends EntityRequests implements AnnualIndicatorRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public void addAnnualIndicator(AnnualIndicatorDetails annualIndicatorDetails) throws MilesException {

        if (annualIndicatorDetails == null) {
            throw new InvalidArgumentException("error_049_01");
        } else if (annualIndicatorDetails.getPerformanceIndicator() == null) {
            throw new InvalidArgumentException("error_049_02");
        } else if (annualIndicatorDetails.getSubActivity() == null) {
            throw new InvalidArgumentException("error_049_03");
        }

        AnnualIndicator annualIndicator = new AnnualIndicator();
        annualIndicator.setPerformanceIndicator(em.getReference(PerformanceIndicator.class, annualIndicatorDetails.getPerformanceIndicator().getId()));
        annualIndicator.setSubActivity(em.getReference(SubActivity.class, annualIndicatorDetails.getSubActivity().getId()));

        try {
            em.persist(annualIndicator);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    @Override
    public void addAnnualIndicators(List<AnnualIndicatorDetails> annualIndicatorDetailsList) throws MilesException {
        for (AnnualIndicatorDetails annualIndicatorDetails : annualIndicatorDetailsList) {
            addAnnualIndicator(annualIndicatorDetails);
        }
        em.flush();
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<SubActivityDetails, List<AnnualIndicatorDetails>> retrieveSubActivities() throws MilesException {

        List<SubActivity> trainings = new ArrayList<>();
        setQ(em.createNamedQuery("SubActivity.findAll"));
        try {
            trainings = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        HashMap<SubActivityDetails, List<AnnualIndicatorDetails>> trainingMap = new HashMap<>();

        for (SubActivity training : trainings) {
            trainingMap.put(trainingService.convertSubActivityToSubActivityDetails(training), retrieveAnnualIndicators(training.getId()));
        }

        return trainingMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AnnualIndicatorDetails> retrieveAnnualIndicators(int subActivityId) throws MilesException {
        List<AnnualIndicator> annualIndicators = new ArrayList<>();
        setQ(em.createNamedQuery("AnnualIndicator.findBySubActivityId"));
        q.setParameter("subActivityId", subActivityId);
        try {
            annualIndicators = q.getResultList();
        } catch (Exception e) {
        }

        return convertAnnualIndicatorsToAnnualIndicatorDetailsList(annualIndicators);
    }

    @Override
    public AnnualIndicatorDetails retrieveAnnualIndicator(int id) throws MilesException {
        AnnualIndicator annualIndicator;
        setQ(em.createNamedQuery("AnnualIndicator.findById"));
        q.setParameter("id", id);
        try {
            annualIndicator = (AnnualIndicator) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertAnnualIndicatorToAnnualIndicatorDetails(annualIndicator);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editAnnualIndicator(AnnualIndicatorDetails annualIndicatorDetails) throws MilesException {

        if (annualIndicatorDetails == null) {
            throw new InvalidArgumentException("error_049_01");
        } else if (annualIndicatorDetails.getId() == null) {
            throw new InvalidArgumentException("error_049_04");
        } else if (annualIndicatorDetails.getPerformanceIndicator() == null) {
            throw new InvalidArgumentException("error_049_02");
        } else if (annualIndicatorDetails.getSubActivity() == null) {
            throw new InvalidArgumentException("error_049_03");
        }

        AnnualIndicator annualIndicator = new AnnualIndicator();
        annualIndicator.setId(annualIndicatorDetails.getId());
        annualIndicator.setPerformanceIndicator(em.getReference(PerformanceIndicator.class, annualIndicatorDetails.getPerformanceIndicator().getId()));
        annualIndicator.setSubActivity(em.getReference(SubActivity.class, annualIndicatorDetails.getSubActivity().getId()));

        try {
            em.merge(annualIndicator);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeAnnualIndicator(int id) throws MilesException {
        AnnualIndicator annualIndicator = em.find(AnnualIndicator.class, id);
        try {
            em.remove(annualIndicator);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public AnnualIndicatorDetails convertAnnualIndicatorToAnnualIndicatorDetails(AnnualIndicator annualIndicator) {

        AnnualIndicatorDetails annualIndicatorDetails = new AnnualIndicatorDetails(annualIndicator.getId());
        annualIndicatorDetails.setPerformanceIndicator(personService.convertPerformanceIndicatorToPerformanceIndicatorDetails(annualIndicator.getPerformanceIndicator()));
        annualIndicatorDetails.setSubActivity(trainingService.convertSubActivityToSubActivityDetails(annualIndicator.getSubActivity()));

        return annualIndicatorDetails;

    }

    private List<AnnualIndicatorDetails> convertAnnualIndicatorsToAnnualIndicatorDetailsList(List<AnnualIndicator> annualIndicators) {

        List<AnnualIndicatorDetails> annualIndicatorDetailsList = new ArrayList<>();
        for (AnnualIndicator annualIndicator : annualIndicators) {
            annualIndicatorDetailsList.add(convertAnnualIndicatorToAnnualIndicatorDetails(annualIndicator));
        }

        return annualIndicatorDetailsList;

    }

//</editor-fold>
    @EJB
    private PerformanceIndicatorRequestsLocal personService;
    @EJB
    private SubActivityRequestsLocal trainingService;
}
