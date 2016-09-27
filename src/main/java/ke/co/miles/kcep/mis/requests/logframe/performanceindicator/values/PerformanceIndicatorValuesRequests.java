/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.performanceindicator.values;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.PerformanceIndicator;
import ke.co.miles.kcep.mis.entities.PerformanceIndicatorValues;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorValuesDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PerformanceIndicatorValuesRequests extends EntityRequests implements PerformanceIndicatorValuesRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    @SuppressWarnings("unchecked")
    public void addYearOfUse(short year) throws MilesException {

        List<PerformanceIndicatorValues> performanceIndicatorValuesList
                = new ArrayList<>();
        setQ(em.createNamedQuery("PerformanceIndicatorValues.findByYearOfUse"));
        q.setParameter("yearOfUse", year);
        try {
            performanceIndicatorValuesList = q.getResultList();
        } catch (NoResultException ex) {
            performanceIndicatorValuesList = null;
        } catch (Exception e) {
        }

        if (performanceIndicatorValuesList != null && !performanceIndicatorValuesList.isEmpty()) {
            throw new InvalidArgumentException("error_055_04");
        }

        List<PerformanceIndicator> performanceIndicators;
        setQ(em.createNamedQuery("PerformanceIndicator.findAll"));

        try {
            performanceIndicators = q.getResultList();
            PerformanceIndicatorValues performanceIndicatorValues;
            for (PerformanceIndicator performanceIndicator : performanceIndicators) {
                performanceIndicatorValues = new PerformanceIndicatorValues();
                performanceIndicatorValues.setYearOfUse(year);
                performanceIndicatorValues.setPerformanceIndicator(performanceIndicator);
                try {
                    em.persist(performanceIndicatorValues);
                } catch (Exception e) {
                    throw new InvalidStateException("error_000_01");
                }
            }

            em.flush();

        } catch (Exception e) {
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">
    @Override
    @SuppressWarnings("unchecked")
    public List<Short> retrieveYearsOfUse() throws MilesException {

        List<Short> yearsOfUse = new ArrayList<>();
        setQ(em.createNamedQuery("PerformanceIndicatorValues.findYearsOfUSe"));

        try {
            yearsOfUse = q.getResultList();
        } catch (Exception e) {
        }

        return yearsOfUse;
    }

    @Override
    @SuppressWarnings({"unchecked", "unchecked"})
    public HashMap<PerformanceIndicatorDetails, ArrayList<PerformanceIndicatorValuesDetails>>
            retrievePerformanceIndicators(List<Short> yearsOfUse) throws MilesException {
        HashMap<PerformanceIndicatorDetails, ArrayList<PerformanceIndicatorValuesDetails>> map = new HashMap<>();
        List<PerformanceIndicator> performanceIndicators;
        ArrayList<PerformanceIndicatorValuesDetails> orderedList;
        setQ(em.createNamedQuery("PerformanceIndicator.findAll"));
        try {
            performanceIndicators = q.getResultList();
            setQ(em.createNamedQuery("PerformanceIndicatorValues.findByPerformanceIndicatorIdAndYearOfUse"));
            for (PerformanceIndicator performanceIndicator : performanceIndicators) {
                orderedList = new ArrayList<>();
                q.setParameter("performanceIndicatorId", performanceIndicator.getId());
                for (short yearOfUse : yearsOfUse) {
                    q.setParameter("yearOfUse", yearOfUse);
                    orderedList.add(convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails((PerformanceIndicatorValues) q.getSingleResult()));
                }
                map.put(performanceIndicatorService.convertPerformanceIndicatorToPerformanceIndicatorDetails(performanceIndicator), orderedList);
            }
        } catch (Exception e) {
        }

        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PerformanceIndicatorValuesDetails> retrievePerformanceIndicatorValues() throws MilesException {
        List<PerformanceIndicatorValues> performanceIndicatorValuesList = new ArrayList<>();
        setQ(em.createNamedQuery("PerformanceIndicatorValues.findAll"));
        try {
            performanceIndicatorValuesList = q.getResultList();
        } catch (Exception e) {
        }

        return convertPerformanceIndicatorValuesListToPerformanceIndicatorValuesDetailsList(performanceIndicatorValuesList);
    }

    @Override
    public PerformanceIndicatorValuesDetails retrievePerformanceIndicatorValues(int id) throws MilesException {
        PerformanceIndicatorValues performanceIndicatorValues;
        setQ(em.createNamedQuery("PerformanceIndicatorValues.findById"));
        q.setParameter("id", id);
        try {
            performanceIndicatorValues = (PerformanceIndicatorValues) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails(performanceIndicatorValues);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPerformanceIndicatorValues(PerformanceIndicatorValuesDetails performanceIndicatorValuesDetails) throws MilesException {

        if (performanceIndicatorValuesDetails == null) {
            throw new InvalidArgumentException("error_055_01");
        } else if (performanceIndicatorValuesDetails.getId() == null) {
            throw new InvalidArgumentException("error_055_03");
        }

        PerformanceIndicatorValues performanceIndicatorValues
                = em.find(PerformanceIndicatorValues.class, performanceIndicatorValuesDetails.getId());
        performanceIndicatorValues.setExpectedValue(performanceIndicatorValuesDetails.getExpectedValue());

        try {
            em.merge(performanceIndicatorValues);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removePerformanceIndicatorValues(int id) throws MilesException {
        PerformanceIndicatorValues performanceIndicatorValues = em.find(PerformanceIndicatorValues.class, id);
        try {
            em.remove(performanceIndicatorValues);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public PerformanceIndicatorValuesDetails convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails(PerformanceIndicatorValues performanceIndicatorValues) {

        PerformanceIndicatorValuesDetails performanceIndicatorValuesDetails = new PerformanceIndicatorValuesDetails(performanceIndicatorValues.getId());
        performanceIndicatorValuesDetails.setActualValue(performanceIndicatorValues.getActualValue());
        performanceIndicatorValuesDetails.setExpectedValue(performanceIndicatorValues.getExpectedValue());
        performanceIndicatorValuesDetails.setYearOfUse(performanceIndicatorValues.getYearOfUse());
        try {
            performanceIndicatorValuesDetails.setRatio(Double.
                    parseDouble(decimalFormat.format((performanceIndicatorValues.getActualValue()
                            / performanceIndicatorValues.getExpectedValue()) * 100
                    )));

        } catch (Exception e) {
        }
        performanceIndicatorValuesDetails.setPerformanceIndicator(performanceIndicatorService.
                convertPerformanceIndicatorToPerformanceIndicatorDetails(performanceIndicatorValues.getPerformanceIndicator())
        );

        return performanceIndicatorValuesDetails;

    }

    private List<PerformanceIndicatorValuesDetails> convertPerformanceIndicatorValuesListToPerformanceIndicatorValuesDetailsList(List<PerformanceIndicatorValues> performanceIndicatorValuesList) {

        List<PerformanceIndicatorValuesDetails> performanceIndicatorValuesDetailsList = new ArrayList<>();
        for (PerformanceIndicatorValues performanceIndicatorValues : performanceIndicatorValuesList) {
            performanceIndicatorValuesDetailsList.add(convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails(performanceIndicatorValues));

        }

        return performanceIndicatorValuesDetailsList;

    }

//</editor-fold>
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;
    private final DecimalFormat decimalFormat = new DecimalFormat("######.##");

}
