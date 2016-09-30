/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.financialyear.FinancialYearRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.values.PerformanceIndicatorValuesRequestsLocal;

/**
 *
 * @author siech
 */
@WebServlet(name = "ReportsController", urlPatterns = {"/reports",
    "/financial_report_by_categories", "/financial_report_by_components",
    "/outputLevelReports", "/outcomeLevelReports"})
public class ReportsController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/reports":
                                    path = "/head_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outputLevelReports":
                                    path = "/head_output_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outcomeLevelReports":
                                    path = "/head_outcome_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_report_by_components":
                                    path = "/head_financial_report_by_components";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_report_by_categories":
                                    path = "/head_financial_report_by_categories";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "kalroSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/reports":
                                    path = "/kalro_reports";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/outputLevelReports":
                                    path = "/region_output_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outcomeLevelReports":
                                    path = "/region_outcome_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/reports":
                                    path = "/region_reports";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/outputLevelReports":
                                    path = "/county_output_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outcomeLevelReports":
                                    path = "/county_outcome_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/reports":
                                    path = "/county_reports";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/reports":
                                    path = "/sub_county_reports";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/reports":
                                    path = "/ward_reports";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "agroDealerSession":
                        urlPaths.add("/doAddLoan");
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/reports":
                                    path = "/agro_dealer_reports";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/head_outcome_level_reports":
                case "/county_outcome_level_reports":
                case "/region_outcome_level_reports":
                    try {

                        session.setAttribute("projectYears", performanceIndicatorValuesService.retrieveProjectYears());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of project years ", ex);
                        return;
                    }
                    try {
                        session.setAttribute("indicatorsReport", performanceIndicatorValuesService.reportOnIndicators());
//                        for (PerformanceIndicatorDetails outputIndicator : performanceIndicatorValuesService.reportOnIndicators().keySet()) {
//                            MilesDebugger.debug("\t" + outputIndicator);
//                            for (PerformanceIndicatorValuesDetails cummulativeIndicatorValues : performanceIndicatorValuesService.reportOnIndicators().get(outputIndicator).keySet()) {
//                                for (PerformanceIndicatorValuesDetails outputIndicatorValues : performanceIndicatorValuesService.reportOnIndicators().get(outputIndicator).get(cummulativeIndicatorValues)) {
////                                    MilesDebugger.debug("\t\t\t" + outputIndicatorValues.getActualValue());
//                                }
////                                MilesDebugger.debug("\t\t" + cummulativeIndicatorValues.getActualValue());
//                            }
//                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }
                    break;

                case "/head_output_level_reports":
                case "/county_output_level_reports":
                case "/region_output_level_reports":
                    try {

                        session.setAttribute("projectYears", performanceIndicatorValuesService.retrieveProjectYears());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of project years ", ex);
                        return;
                    }
                    try {
                        session.setAttribute("indicatorsReport", performanceIndicatorValuesService.reportOnIndicators());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }
                    break;

                case "/head_financial_report_by_categories":

                    try {
                        session.setAttribute("financialPlanByCategoryMap", subActivityService.
                                summarizeFinancialPlanByCategories(financialYearService.retrieveCurrentFinancialYear().getId()));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }

                    break;
                case "/head_financial_report_by_components":

                    try {
                        session.setAttribute("financialPlanByComponentMap", subActivityService.
                                summarizeFinancialPlanByComponents(financialYearService.retrieveCurrentFinancialYear().getId()));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("report_generation_failed") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }

                    break;

                default:
                    break;
            }
            //Use request dispatcher to foward request internally
            destination = "/WEB-INF/views/pages" + path + ".jsp";

            LOGGER.log(Level.INFO, "Request dispatch to forward to: {0}", destination);
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
    private static final Logger LOGGER = Logger.getLogger(ReportsController.class
            .getSimpleName());
    @EJB
    private SubActivityRequestsLocal subActivityService;
    @EJB
    private FinancialYearRequestsLocal financialYearService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;
    @EJB
    private PerformanceIndicatorValuesRequestsLocal performanceIndicatorValuesService;

}
