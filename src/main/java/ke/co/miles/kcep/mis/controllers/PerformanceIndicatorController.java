/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.hierarchy.ResultHierarchyRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.type.PerformanceIndicatorTypeRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorTypeDetails;
import ke.co.miles.kcep.mis.utilities.ResultHierarchyDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "PerformanceIndicatorController", urlPatterns = {"/performance_indicators", "/addPerformanceIndicator", "/doAddPerformanceIndicator"})
public class PerformanceIndicatorController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        //Get the user session
        HttpSession session = request.getSession();

        //Get the user path
        String path = request.getServletPath();
        String destination;

        DecimalFormat decimalFormat = new DecimalFormat("######.##");

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps
                = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerformanceIndicator");
                            if (path.equals("/performance_indicators")) {
                                path = "/head_performance_indicators";
                                urlPaths.add(path);
                            } else if (path.equals("/addPerformanceIndicator")) {
                                path = "/head_addPerformanceIndicator";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerformanceIndicator");
                            if (path.equals("/performance_indicators")) {
                                path = "/region_performance_indicators";
                                urlPaths.add(path);
                            } else if (path.equals("/addPerformanceIndicator")) {
                                path = "/region_addPerformanceIndicator";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerformanceIndicator");
                            if (path.equals("/performance_indicators")) {
                                path = "/county_performance_indicators";
                                urlPaths.add(path);
                            } else if (path.equals("/addPerformanceIndicator")) {
                                path = "/county_addPerformanceIndicator";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (urlPaths.contains(path)) {

            switch (path) {

                case "/head_performance_indicators":
                case "/county_performance_indicators":
                case "/region_performance_indicators":

                    List<PerformanceIndicatorDetails> performanceIndicators;
                    try {
                        performanceIndicators = performanceIndicatorService.retrievePerformanceIndicators();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during performance indicator retrieval", ex);
                        return;
                    }

                    if (performanceIndicators != null) {
                        session.setAttribute("performanceIndicators", performanceIndicators);
                    }
                    break;

                case "/head_addPerformanceIndicator":
                case "/county_addPerformanceIndicator":
                case "/region_addPerformanceIndicator":

                    List<PerformanceIndicatorTypeDetails> performanceIndicatorTypes;
                    try {
                        performanceIndicatorTypes = performanceIndicatorTypeService.retrievePerformanceIndicatorTypes();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of performance indicator types ", ex);
                        return;
                    }

                    if (performanceIndicatorTypes != null) {
                        session.setAttribute("performanceIndicatorTypes", performanceIndicatorTypes);
                    }

                    List<ResultHierarchyDetails> resultHierarchies;
                    try {
                        resultHierarchies = resultHierarchyService.retrieveResultHierarchies();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of result hierarchies ", ex);
                        return;
                    }

                    if (resultHierarchies != null) {
                        session.setAttribute("resultHierarchies", resultHierarchies);
                    }

                    break;

                case "/doAddPerformanceIndicator":

                    PerformanceIndicatorDetails performanceIndicator = new PerformanceIndicatorDetails();
                    performanceIndicator.setDescription(request.getParameter("description"));

                    try {
                        performanceIndicator.setExpectedValue(Double.valueOf(request.getParameter("expectedValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setExpectedValue(null);
                    }

                    try {
                        performanceIndicator.setBaselineValue(Double.valueOf(request.getParameter("baselineValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setBaselineValue(null);
                    }

                    try {
                        performanceIndicator.setActualValue(Double.valueOf(request.getParameter("actualValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setActualValue(null);
                    }

                    try {
                        performanceIndicator.setRatio(Double.valueOf(request.getParameter("ratio")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setRatio(null);
                        try {
                            performanceIndicator.setRatio(Double.
                                    parseDouble(decimalFormat.format((performanceIndicator.getActualValue()
                                            / performanceIndicator.getExpectedValue()) * 100
                                    )));
                        } catch (NumberFormatException ex) {
                        }

                    }

                    ResultHierarchyDetails resultHierarchy;
                    try {
                        resultHierarchy = new ResultHierarchyDetails(Integer.valueOf(request.getParameter("resultHierarchy")));
                    } catch (Exception e) {
                        resultHierarchy = null;
                    }

                    PerformanceIndicatorTypeDetails performanceIndicatorType;
                    try {
                        performanceIndicatorType = new PerformanceIndicatorTypeDetails(Integer.valueOf(request.getParameter("performanceIndicatorType")));
                    } catch (Exception e) {
                        performanceIndicatorType = null;
                    }

                    performanceIndicator.setResultHierarchy(resultHierarchy);
                    performanceIndicator.setPerformanceIndicatorType(performanceIndicatorType);

                    try {
                        date = userDateFormat.parse(request.getParameter("baselineDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        performanceIndicator.setBaselineDate(date);
                    } catch (ParseException e) {
                        response.getWriter().write(getBundle().getString("string_parse_error"));
                        LOGGER.log(Level.WARNING, getBundle().getString("string_parse_error"), e);
                        performanceIndicator.setBaselineDate(null);
                    }

                    try {
                        performanceIndicator.setYearOfUse(Short.valueOf(request.getParameter("yearOfUse")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setYearOfUse(null);
                    }

                    try {
                        performanceIndicatorService.addPerformanceIndicator(performanceIndicator);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                default:
                    break;
            }
            //Use request dispatcher to foward request internally
            destination = "/WEB-INF/views" + path + ".jsp";

            LOGGER.log(Level.INFO,
                    "Request dispatch to forward to: {0}", destination);
            try {
                request.getRequestDispatcher(destination).forward(request, response);
            } catch (ServletException | IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(getBundle().getString("redirection_failed") + "<br>");
                LOGGER.log(Level.INFO, getBundle().getString("redirection_failed"), e);

            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString("error_016_02") + "<br>");
            LOGGER.log(Level.INFO, getBundle().getString("error_016_02"));
        }
    }

    private static final Logger LOGGER = Logger.getLogger(PerformanceIndicatorController.class
            .getSimpleName());

    @EJB
    private PerformanceIndicatorTypeRequestsLocal performanceIndicatorTypeService;
    @EJB
    private ResultHierarchyRequestsLocal resultHierarchyService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;
    @EJB
    private SubComponentRequestsLocal subComponentService;

}
