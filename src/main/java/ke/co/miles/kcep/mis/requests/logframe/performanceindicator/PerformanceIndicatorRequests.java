/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.performanceindicator;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.PerformanceIndicator;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.ResultHierarchy;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.hierarchy.ResultHierarchyRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PerformanceIndicatorRequests extends EntityRequests implements PerformanceIndicatorRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addPerformanceIndicator(PerformanceIndicatorDetails performanceIndicatorDetails) throws MilesException {
        if (performanceIndicatorDetails == null) {
            throw new InvalidArgumentException("error_040_01");
        } else if (performanceIndicatorDetails.getResultHierarchy() == null) {
            throw new InvalidArgumentException("error_040_02");
        } else if (performanceIndicatorDetails.getDescription() == null || performanceIndicatorDetails.getDescription().trim().length() == 0) {
            throw new InvalidArgumentException("error_040_03");
        } else if (performanceIndicatorDetails.getDescription().length() > 400) {
            throw new InvalidArgumentException("error_040_04");
        }

        PerformanceIndicator performanceIndicator = new PerformanceIndicator();
        performanceIndicator.setRatio(performanceIndicatorDetails.getRatio());
        performanceIndicator.setYearOfUse(performanceIndicatorDetails.getYearOfUse());
        performanceIndicator.setActualValue(performanceIndicatorDetails.getActualValue());
        performanceIndicator.setDescription(performanceIndicatorDetails.getDescription());
        performanceIndicator.setBaselineDate(performanceIndicatorDetails.getBaselineDate());
        performanceIndicator.setBaselineValue(performanceIndicatorDetails.getBaselineValue());
        performanceIndicator.setExpectedValue(performanceIndicatorDetails.getExpectedValue());
        performanceIndicator.setResultHierarchy(em.getReference(ResultHierarchy.class, performanceIndicatorDetails.getResultHierarchy().getId()));
        if (performanceIndicatorDetails.getPerformanceIndicatorType() != null) {
            performanceIndicator.setPerformanceIndicatorType(em.getReference(Phenomenon.class, performanceIndicatorDetails.getPerformanceIndicatorType().getId()));
        }

        try {
            em.persist(performanceIndicator);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return performanceIndicator.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<PerformanceIndicatorDetails> retrievePerformanceIndicators() throws MilesException {
        List<PerformanceIndicator> performanceIndicators = new ArrayList<>();
        setQ(em.createNamedQuery("PerformanceIndicator.findAll"));
        try {
            performanceIndicators = q.getResultList();
        } catch (Exception e) {
        }

        return convertPerformanceIndicatorsToPerformanceIndicatorDetailsList(performanceIndicators);
    }

    @Override
    public PerformanceIndicatorDetails retrievePerformanceIndicator(int id) throws MilesException {
        PerformanceIndicator performanceIndicator;
        setQ(em.createNamedQuery("PerformanceIndicator.findById"));
        q.setParameter("id", id);
        try {
            performanceIndicator = (PerformanceIndicator) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPerformanceIndicatorToPerformanceIndicatorDetails(performanceIndicator);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPerformanceIndicatorValues(PerformanceIndicatorDetails performanceIndicatorDetails) throws MilesException {

        if (performanceIndicatorDetails == null) {
            throw new InvalidArgumentException("error_040_01");
        } else if (performanceIndicatorDetails.getId() == null) {
            throw new InvalidArgumentException("error_040_05");
        }

        PerformanceIndicator performanceIndicator = em.find(PerformanceIndicator.class, performanceIndicatorDetails.getId());
        performanceIndicator.setId(performanceIndicatorDetails.getId());
        performanceIndicator.setRatio(performanceIndicatorDetails.getRatio());
        performanceIndicator.setBaselineDate(performanceIndicatorDetails.getBaselineDate());
        performanceIndicator.setBaselineValue(performanceIndicatorDetails.getBaselineValue());
        performanceIndicator.setExpectedValue(performanceIndicatorDetails.getExpectedValue());

        try {
            em.merge(performanceIndicator);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(e);
            throw new InvalidStateException("error_000_01");
        }

    }

    @Override
    public void editPerformanceIndicator(PerformanceIndicatorDetails performanceIndicatorDetails) throws MilesException {

        if (performanceIndicatorDetails == null) {
            throw new InvalidArgumentException("error_040_01");
        } else if (performanceIndicatorDetails.getId() == null) {
            throw new InvalidArgumentException("error_040_05");
        } else if (performanceIndicatorDetails.getResultHierarchy() == null) {
            throw new InvalidArgumentException("error_040_02");
        } else if (performanceIndicatorDetails.getDescription() == null || performanceIndicatorDetails.getDescription().trim().length() == 0) {
            throw new InvalidArgumentException("error_040_03");
        } else if (performanceIndicatorDetails.getDescription().length() > 400) {
            throw new InvalidArgumentException("error_040_04");
        }

        PerformanceIndicator performanceIndicator = em.find(PerformanceIndicator.class, performanceIndicatorDetails.getId());
        performanceIndicator.setId(performanceIndicatorDetails.getId());
        performanceIndicator.setRatio(performanceIndicatorDetails.getRatio());
        performanceIndicator.setYearOfUse(performanceIndicatorDetails.getYearOfUse());
        performanceIndicator.setActualValue(performanceIndicatorDetails.getActualValue());
        performanceIndicator.setDescription(performanceIndicatorDetails.getDescription());
        performanceIndicator.setBaselineDate(performanceIndicatorDetails.getBaselineDate());
        performanceIndicator.setBaselineValue(performanceIndicatorDetails.getBaselineValue());
        performanceIndicator.setExpectedValue(performanceIndicatorDetails.getExpectedValue());
        if (performanceIndicatorDetails.getPerformanceIndicatorType() != null) {
            performanceIndicator.setPerformanceIndicatorType(em.getReference(Phenomenon.class,
                    performanceIndicatorDetails.getPerformanceIndicatorType().getId()));
        }
        if (performanceIndicatorDetails.getResultHierarchy() != null) {
            performanceIndicator.setResultHierarchy(em.getReference(ResultHierarchy.class,
                    performanceIndicatorDetails.getResultHierarchy().getId()));
        }

        try {
            em.merge(performanceIndicator);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removePerformanceIndicator(int id) throws MilesException {
        PerformanceIndicator performanceIndicator = em.find(PerformanceIndicator.class, id);
        try {
            em.remove(performanceIndicator);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public PerformanceIndicatorDetails convertPerformanceIndicatorToPerformanceIndicatorDetails(PerformanceIndicator performanceIndicator) {

        PerformanceIndicatorDetails performanceIndicatorDetails = new PerformanceIndicatorDetails(performanceIndicator.getId());
        performanceIndicatorDetails.setExpectedValue(performanceIndicator.getExpectedValue());
        performanceIndicatorDetails.setBaselineValue(performanceIndicator.getBaselineValue());
        performanceIndicatorDetails.setBaselineDate(performanceIndicator.getBaselineDate());
        performanceIndicatorDetails.setDescription(performanceIndicator.getDescription());
        performanceIndicatorDetails.setActualValue(performanceIndicator.getActualValue());
        performanceIndicatorDetails.setYearOfUse(performanceIndicator.getYearOfUse());
        performanceIndicatorDetails.setRatio(performanceIndicator.getRatio());
        if (performanceIndicator.getResultHierarchy() != null) {
            performanceIndicatorDetails.setPerformanceIndicatorType(phenomenonService.
                    convertPhenomenonToPhenomenonDetails(performanceIndicator.getPerformanceIndicatorType()));
        }
        if (performanceIndicator.getResultHierarchy() != null) {
            performanceIndicatorDetails.setResultHierarchy(resultHierarchyService.
                    convertResultHierarchyToResultHierarchyDetails(performanceIndicator.getResultHierarchy())
            );
        }

        return performanceIndicatorDetails;

    }

    private List<PerformanceIndicatorDetails> convertPerformanceIndicatorsToPerformanceIndicatorDetailsList(List<PerformanceIndicator> performanceIndicators) {

        List<PerformanceIndicatorDetails> performanceIndicatorDetailsList = new ArrayList<>();
        for (PerformanceIndicator performanceIndicator : performanceIndicators) {
            performanceIndicatorDetailsList.add(convertPerformanceIndicatorToPerformanceIndicatorDetails(performanceIndicator));

        }

        return performanceIndicatorDetailsList;

    }

//</editor-fold>
    @EJB
    private ResultHierarchyRequestsLocal resultHierarchyService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;
}
