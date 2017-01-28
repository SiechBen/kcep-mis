/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.performanceindicator.values;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.PerformanceIndicator;
import ke.co.miles.kcep.mis.entities.PerformanceIndicatorValues;
import ke.co.miles.kcep.mis.entities.ResultHierarchy;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.logframe.hierarchy.ResultHierarchyRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorValuesDetails;
import ke.co.miles.kcep.mis.utilities.ResultHierarchyDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PerformanceIndicatorValuesRequests extends EntityRequests implements PerformanceIndicatorValuesRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    @SuppressWarnings("unchecked")
    public void addProjectYear(short year) throws MilesException {

        List<PerformanceIndicatorValues> performanceIndicatorValuesList
                = new ArrayList<>();
        setQ(em.createNamedQuery("PerformanceIndicatorValues.findByProjectYear"));
        q.setParameter("projectYear", year);
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
            PerformanceIndicatorValues outcomeIndicatorValues;
            for (PerformanceIndicator performanceIndicator : performanceIndicators) {
                performanceIndicatorValues = new PerformanceIndicatorValues();
                performanceIndicatorValues.setProjectYear(year);
                performanceIndicatorValues.setPerformanceIndicator(performanceIndicator);
                try {
                    em.persist(performanceIndicatorValues);
                } catch (Exception e) {
                    throw new InvalidStateException("error_000_01");
                }
                outcomeIndicatorValues = new PerformanceIndicatorValues();
                outcomeIndicatorValues.setProjectYear(year);
                outcomeIndicatorValues.setPurpose("Outcome report");
                outcomeIndicatorValues.setPerformanceIndicator(performanceIndicator);
                try {
                    em.persist(outcomeIndicatorValues);
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
    @SuppressWarnings({"unchecked", "unchecked"})
    @Override
    public List<ResultHierarchyDetails> retrieveAllIndicators(List<Short> projectYears) throws MilesException {
        ResultHierarchyDetails resultHierarchyDetails;
        PerformanceIndicatorDetails performanceIndicatorDetails;
        List<ResultHierarchy> resultHierarchies = new ArrayList<>();
        List<ResultHierarchyDetails> resultHierarchyDetailsList = new ArrayList<>();
        List<PerformanceIndicatorDetails> performanceIndicatorDetailsList = new ArrayList<>();
        setQ(em.createNamedQuery("ResultHierarchy.findAll"));
        try {
            resultHierarchies = q.getResultList();
        } catch (Exception e) {
        }

        ArrayList<PerformanceIndicatorValuesDetails> orderedList;
        try {
            setQ(em.createNamedQuery("PerformanceIndicatorValues.findByPerformanceIndicatorIdAndProjectYearAndPurpose"));
            for (ResultHierarchy rh : resultHierarchies) {
                resultHierarchyDetails = resultHierarchyService.convertResultHierarchyToResultHierarchyDetails(rh);

                for (PerformanceIndicator performanceIndicator : rh.getPerformanceIndicatorList()) {
                    performanceIndicatorDetails = performanceIndicatorService.convertPerformanceIndicatorToPerformanceIndicatorDetails(performanceIndicator);
                    orderedList = new ArrayList<>();
                    q.setParameter("performanceIndicatorId", performanceIndicator.getId());
                    for (short projectYear : projectYears) {
                        q.setParameter("projectYear", projectYear);
                        try {
                            orderedList.add(convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails((PerformanceIndicatorValues) q.getSingleResult()));
                        } catch (Exception e) {
                        }
                    }
                    performanceIndicatorDetails.setPerformanceIndicatorValuesList(orderedList);
                    performanceIndicatorDetailsList.add(performanceIndicatorDetails);
                }
                resultHierarchyDetails.setPerformanceIndicatorList(performanceIndicatorDetailsList);
                resultHierarchyDetailsList.add(resultHierarchyDetails);
            }
        } catch (Exception e) {
        }

        return resultHierarchyDetailsList;

    }

    @SuppressWarnings({"unchecked", "unchecked"})
    @Override
    public List<ResultHierarchyDetails> reportOnOutputLevelIndicators(List<Short> projectYears) throws MilesException {
        ResultHierarchyDetails resultHierarchyDetails;
        PerformanceIndicatorDetails performanceIndicatorDetails;
        List<ResultHierarchy> resultHierarchies = new ArrayList<>();
        List<ResultHierarchyDetails> resultHierarchyDetailsList = new ArrayList<>();
        List<PerformanceIndicatorDetails> performanceIndicatorDetailsList = new ArrayList<>();
        setQ(em.createNativeQuery("SELECT * FROM result_hierarchy r WHERE r.description REGEXP ?1"));
        q.setParameter(1, "^Output");
        try {
            resultHierarchies = q.getResultList();
        } catch (Exception e) {
        }

        ArrayList<PerformanceIndicatorValuesDetails> orderedList;
        try {
            setQ(em.createNamedQuery("PerformanceIndicatorValues.findByPerformanceIndicatorIdAndProjectYearAndPurpose"));
            for (ResultHierarchy rh : resultHierarchies) {
                resultHierarchyDetails = resultHierarchyService.convertResultHierarchyToResultHierarchyDetails(rh);

                for (PerformanceIndicator performanceIndicator : rh.getPerformanceIndicatorList()) {
                    performanceIndicatorDetails = performanceIndicatorService.convertPerformanceIndicatorToPerformanceIndicatorDetails(performanceIndicator);
                    orderedList = new ArrayList<>();
                    q.setParameter("performanceIndicatorId", performanceIndicator.getId());
                    for (short projectYear : projectYears) {
                        q.setParameter("projectYear", projectYear);
                        try {
                            orderedList.add(convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails((PerformanceIndicatorValues) q.getSingleResult()));
                        } catch (Exception e) {
                        }
                    }
                    performanceIndicatorDetails.setPerformanceIndicatorValuesList(orderedList);
                    performanceIndicatorDetailsList.add(performanceIndicatorDetails);
                }
                resultHierarchyDetails.setPerformanceIndicatorList(performanceIndicatorDetailsList);
                resultHierarchyDetailsList.add(resultHierarchyDetails);
            }
        } catch (Exception e) {
        }

        return resultHierarchyDetailsList;

    }

    @Override
    public HashMap<PerformanceIndicatorDetails, HashMap<PerformanceIndicatorValuesDetails, ArrayList<PerformanceIndicatorValuesDetails>>> reportOnOutputIndicators() throws MilesException {

        HashMap<PerformanceIndicatorDetails, HashMap<PerformanceIndicatorValuesDetails, ArrayList<PerformanceIndicatorValuesDetails>>> reportMap = new HashMap<>();
        HashMap<PerformanceIndicatorValuesDetails, ArrayList<PerformanceIndicatorValuesDetails>> cummulativeValuesToValuesMap;
        HashMap<PerformanceIndicatorDetails, ArrayList<PerformanceIndicatorValuesDetails>> performanceIndicatorToValuesMap;
        ArrayList<PerformanceIndicatorValuesDetails> performanceIndicatorValuesDetailsList;
        PerformanceIndicatorValuesDetails cummulativePerformanceIndicatorValues;

        performanceIndicatorToValuesMap = retrieveOutputLevelIndicators(retrieveProjectYears());

        setQ(em.createNativeQuery("SELECT id FROM performance_indicator_values p ORDER BY id DESC LIMIT 1", Integer.class));

        int index = 0;
        try {
            index = (int) q.getSingleResult();
        } catch (Exception e) {
        }

        for (PerformanceIndicatorDetails performanceIndicatorDetails : performanceIndicatorToValuesMap.keySet()) {

            performanceIndicatorValuesDetailsList = new ArrayList<>();
            performanceIndicatorValuesDetailsList.addAll(performanceIndicatorToValuesMap.get(performanceIndicatorDetails));

            /* cummulative performance indicator values are not stored in the database. They are created on-the-fly */
            cummulativePerformanceIndicatorValues = new PerformanceIndicatorValuesDetails(++index);

            for (PerformanceIndicatorValuesDetails performanceIndicatorValuesDetails : performanceIndicatorValuesDetailsList) {

                try {
                    if (cummulativePerformanceIndicatorValues.getExpectedValue() == null) {
                        cummulativePerformanceIndicatorValues.setExpectedValue(performanceIndicatorValuesDetails.getExpectedValue());
                    } else {
                        cummulativePerformanceIndicatorValues.setExpectedValue(cummulativePerformanceIndicatorValues.getExpectedValue() + performanceIndicatorValuesDetails.getExpectedValue());
                    }
                } catch (Exception e) {
                }

                try {
                    if (cummulativePerformanceIndicatorValues.getActualValue() == null) {
                        cummulativePerformanceIndicatorValues.setActualValue(performanceIndicatorValuesDetails.getActualValue());
                    } else {
                        cummulativePerformanceIndicatorValues.setActualValue(cummulativePerformanceIndicatorValues.getActualValue() + performanceIndicatorValuesDetails.getActualValue());
                    }
                } catch (Exception e) {
                }

            }

            try {
                cummulativePerformanceIndicatorValues.setRatio(Double.parseDouble(decimalFormat.format(cummulativePerformanceIndicatorValues.getActualValue() / cummulativePerformanceIndicatorValues.getExpectedValue() * 100)));
            } catch (Exception e) {
            }

            cummulativeValuesToValuesMap = new HashMap<>();
            cummulativeValuesToValuesMap.put(cummulativePerformanceIndicatorValues, performanceIndicatorValuesDetailsList);
            reportMap.put(performanceIndicatorDetails, cummulativeValuesToValuesMap);
        }

        return reportMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Short> retrieveProjectYears() throws MilesException {

        List<Short> projectYears = new ArrayList<>();
        setQ(em.createNamedQuery("PerformanceIndicatorValues.findProjectYears"));

        try {
            projectYears = q.getResultList();
        } catch (Exception e) {
        }

        return projectYears;
    }

    @SuppressWarnings({"unchecked", "unchecked"})
    private HashMap<PerformanceIndicatorDetails, ArrayList<PerformanceIndicatorValuesDetails>> retrievePerformanceIndicators(List<Short> projectYears) throws MilesException {

        HashMap<PerformanceIndicatorDetails, ArrayList<PerformanceIndicatorValuesDetails>> map = new HashMap<>();
        List<PerformanceIndicator> performanceIndicators;
        ArrayList<PerformanceIndicatorValuesDetails> orderedList;
        try {
            performanceIndicators = q.getResultList();
            setQ(em.createNamedQuery("PerformanceIndicatorValues.findByPerformanceIndicatorIdAndProjectYearAndPurpose"));
            for (PerformanceIndicator performanceIndicator : performanceIndicators) {
                orderedList = new ArrayList<>();
                q.setParameter("performanceIndicatorId", performanceIndicator.getId());
                for (short projectYear : projectYears) {
                    q.setParameter("projectYear", projectYear);
                    try {
                        orderedList.add(convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails((PerformanceIndicatorValues) q.getSingleResult()));
                    } catch (Exception e) {
                    }
                }
                map.put(performanceIndicatorService.convertPerformanceIndicatorToPerformanceIndicatorDetails(performanceIndicator), orderedList);
            }
        } catch (Exception e) {
        }

        return map;
    }

    @Override
    @SuppressWarnings({"unchecked", "unchecked"})
    public HashMap<PerformanceIndicatorDetails, ArrayList<PerformanceIndicatorValuesDetails>> retrieveAllPerformanceIndicators(List<Short> projectYears) throws MilesException {

        setQ(em.createNamedQuery("PerformanceIndicator.findAll"));

        return retrievePerformanceIndicators(projectYears);
    }

    @SuppressWarnings({"unchecked", "unchecked"})
    private HashMap<PerformanceIndicatorDetails, ArrayList<PerformanceIndicatorValuesDetails>> retrieveOutputLevelIndicators(List<Short> projectYears) throws MilesException {
        setQ(em.createNativeQuery("SELECT * FROM performance_indicator p INNER "
                + "JOIN result_hierarchy r ON (p.result_hierarchy = r.id) WHERE "
                + "r.description REGEXP ?1", PerformanceIndicator.class));
        q.setParameter(1, "^Output ");

        return retrievePerformanceIndicators(projectYears);
    }

    @SuppressWarnings({"unchecked", "unchecked"})
    @Override
    public List<PerformanceIndicatorValuesDetails> reportOnOutcomeIndicators(Short projectYear) throws MilesException {

        Calendar calendar = Calendar.getInstance();
        short year = Short.valueOf(String.valueOf(projectYear == null ? calendar.get(Calendar.YEAR) : projectYear));

        setQ(em.createNativeQuery("SELECT * FROM performance_indicator_values pv INNER JOIN performance_indicator p ON (pv.performance_indicator = p.id) INNER JOIN result_hierarchy r ON (p.result_hierarchy = r.id) WHERE r.description REGEXP ?1 OR r.description REGEXP ?4 AND pv.project_year = ?2 AND pv.purpose = ?3", PerformanceIndicatorValues.class));
        q.setParameter(1, "^Outcome ");
        q.setParameter(4, "^Programme ");
        q.setParameter(2, year);
        q.setParameter(3, "Outcome report");
        List<PerformanceIndicatorValuesDetails> orderedList = new ArrayList<>();
        List<PerformanceIndicatorValues> orderedValues;
        try {
            orderedValues = q.getResultList();
            for (PerformanceIndicatorValues performanceIndicatorValues : orderedValues) {
                if (performanceIndicatorValues.getPurpose() != null && performanceIndicatorValues.getPurpose().equals("Outcome report")) {
                    orderedList.add(convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails(performanceIndicatorValues));
                }
            }
        } catch (Exception e) {
        }

        for (PerformanceIndicatorValuesDetails performanceIndicatorValues : orderedList) {
            setQ(em.createNamedQuery("PerformanceIndicatorValues.findOfPreviousYears"));
            q.setParameter("projectYear", projectYear);
            q.setParameter("purpose", "Outcome report");
            q.setParameter("performanceIndicatorId", performanceIndicatorValues.getPerformanceIndicator().getId());
            try {
                performanceIndicatorValues.getPerformanceIndicator().setAccumulatedActual((double) q.getSingleResult());
            } catch (Exception e) {
            }
        }

        return orderedList;
    }

    @SuppressWarnings({"unchecked", "unchecked"})
    public List<PerformanceIndicatorValuesDetails> reportOnGoalIndicators(Short projectYear) throws MilesException {

        Calendar calendar = Calendar.getInstance();
        short year = Short.valueOf(String.valueOf(projectYear == null ? calendar.get(Calendar.YEAR) : projectYear));

        setQ(em.createNativeQuery("SELECT * FROM performance_indicator_values pv INNER JOIN performance_indicator p ON (pv.performance_indicator = p.id) INNER JOIN result_hierarchy r ON (p.result_hierarchy = r.id) WHERE r.description REGEXP ?1 AND pv.project_year = ?2 AND pv.purpose = ?3", PerformanceIndicatorValues.class));
        q.setParameter(1, "^Goal ");
        q.setParameter(2, year);
        q.setParameter(3, "Goal report");
        List<PerformanceIndicatorValues> orderedList = new ArrayList<>();
        try {
            orderedList = q.getResultList();
        } catch (Exception e) {
        }

        return convertPerformanceIndicatorValuesListToPerformanceIndicatorValuesDetailsList(orderedList);
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
        performanceIndicatorValues.setActualValue(performanceIndicatorValuesDetails.getActualValue());
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
        performanceIndicatorValuesDetails.setPurpose(performanceIndicatorValues.getPurpose());
        performanceIndicatorValuesDetails.setExpectedValue(performanceIndicatorValues.getExpectedValue());
        performanceIndicatorValuesDetails.setProjectYear(performanceIndicatorValues.getProjectYear());
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
    private ResultHierarchyRequestsLocal resultHierarchyService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;
    private final DecimalFormat decimalFormat = new DecimalFormat("######.##");

}
