package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.progress.ActivityProgressRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.financialyear.FinancialYearRequestsLocal;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.values.PerformanceIndicatorValuesRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorValuesDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "ReportsController", urlPatterns = {"/reports",
    "/financial_report_by_categories", "/financial_report_by_components",
    "/updateOutcomeValues", "/changeOutcomeReport", "/goalLevelReports",
    "/outputLevelReports", "/outcomeLevelReports", "/activity_report",
    "/getActivityProgress"})
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
                            urlPaths.add("/getActivityProgress");
                            urlPaths.add("/updateOutcomeValues");
                            urlPaths.add("/activity_report");
                            urlPaths.add("/changeOutcomeReport");
                            switch (path) {
                                case "/reports":
                                    path = "/head_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outputLevelReports":
                                    path = "/head_output_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/goalLevelReports":
                                    path = "/head_goal_level_reports";
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
                    case "agmarkSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/reports":
                                    path = "/agmark_reports";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateOutcomeValues");
                            urlPaths.add("/changeOutcomeReport");
                            switch (path) {
                                case "/outputLevelReports":
                                    path = "/region_output_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outcomeLevelReports":
                                    path = "/region_outcome_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/goalLevelReports":
                                    path = "/region_goal_level_reports";
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
                            urlPaths.add("/getActivityProgress");
                            urlPaths.add("/updateOutcomeValues");
                            urlPaths.add("/activity_report");
                            urlPaths.add("/changeOutcomeReport");
                            switch (path) {
                                case "/reports":
                                    path = "/county_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outputLevelReports":
                                    path = "/county_output_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/goalLevelReports":
                                    path = "/county_goal_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outcomeLevelReports":
                                    path = "/county_outcome_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_report_by_components":
                                    path = "/county_financial_report_by_components";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_report_by_categories":
                                    path = "/county_financial_report_by_categories";
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

                case "/activity_report":

                    try {
                        activityProgressService.checkForActivityProgress(null);
                    } catch (Exception e) {
                    }

                    try {
                        session.setAttribute("awpbReferenceCodes", subActivityService.retrieveReferenceCodes());
                    } catch (Exception e) {
                    }

                    break;

                case "/getActivityProgress":

                    try {
                        session.setAttribute("activityProgressReport", activityProgressService.retrieveActivityProgress(request.getParameter("awpbReferenceCode")));
                    } catch (Exception e) {
                        MilesDebugger.debug(e);
                    }

                    return;

                case "/updateOutcomeValues":
                    PerformanceIndicatorValuesDetails outcomeIndicatorValues;
                    try {
                        outcomeIndicatorValues = new PerformanceIndicatorValuesDetails(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                        outcomeIndicatorValues = new PerformanceIndicatorValuesDetails();
                    }
                    try {
                        outcomeIndicatorValues.setActualValue(Double.valueOf(request.getParameter("actualValue")));
                    } catch (Exception e) {
                    }
                    try {
                        outcomeIndicatorValues.setExpectedValue(Double.valueOf(request.getParameter("expectedValue")));
                    } catch (Exception e) {
                    }

                    try {
                        performanceIndicatorValuesService.editOutcomeValues(outcomeIndicatorValues);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }

                    return;

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
                        session.setAttribute("ratingValues", phenomenonService.retrieveRatingValues());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of rating values ", ex);
                        return;
                    }
                    try {
                        Short projectYear;
                        try {
                            projectYear = Short.valueOf(request.getParameter("projectYear"));
                        } catch (Exception e) {
                            projectYear = null;
                        }
                        session.setAttribute("outcomesReport", performanceIndicatorValuesService.reportOnOutcomeIndicators(projectYear));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }

                    break;

                case "/head_goal_level_reports":
                case "/county_goal_level_reports":
                case "/region_goal_level_reports":
                    try {
                        session.setAttribute("projectYears", performanceIndicatorValuesService.retrieveProjectYears());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of project years ", ex);
                        return;
                    }

                    try {
                        session.setAttribute("ratingValues", phenomenonService.retrieveRatingValues());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of rating values ", ex);
                        return;
                    }
                    try {
                        Short projectYear;
                        try {
                            projectYear = Short.valueOf(request.getParameter("projectYear"));
                        } catch (Exception e) {
                            projectYear = null;
                        }
                        session.setAttribute("goalsReport", performanceIndicatorValuesService.reportOnOutcomeIndicators(projectYear));
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
                        session.setAttribute("outputsReport", performanceIndicatorValuesService.reportOnOutputIndicators());
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
    //<editor-fold defaultstate="collapsed" desc="Update tables">

    private void updateOutcomesTable(HttpServletResponse response, List<PerformanceIndicatorValuesDetails> outcomes) throws IOException, MilesException {
        PrintWriter out = response.getWriter();
        int index = 0;
        List<PhenomenonDetails> ratingValues = phenomenonService.retrieveRatingValues();
        for (PerformanceIndicatorValuesDetails outcome : outcomes) {
            out.write(
                    "<tr>\n"
                    + "                                <td>" + ++index + "</td>\n"
                    + "                                <td class=\"tooltipped\" data-toggle=\"tooltip\" data-placement=\"auto bottom\" title=\"" + outcome.getPerformanceIndicator().getResultHierarchy().getDescription() + "\">" + outcome.getPerformanceIndicator().getResultHierarchy().getDescription() + "</td>\n"
                    + "                                <td class=\"tooltipped\" data-toggle=\"tooltip\" data-placement=\"auto bottom\" title=\"" + outcome.getPerformanceIndicator().getDescription() + "\">" + outcome.getPerformanceIndicator().getDescription() + "</td>\n"
                    + "                                <td>Rating</td>\n"
                    + "                                <td class=\"editable\">\n");
            out.write("<select id=\"awpb-outcome-target-" + outcome.getId() + "\" onchange=\"calculateOutcomeRatio(" + outcome.getId() + ")\">");
            for (PhenomenonDetails ratingValue : ratingValues) {
                out.write("<option value=\"" + ratingValue.getCategory().getName() + "\"" + (Double.valueOf(ratingValue.getCategory().getName()).equals(outcome.getExpectedValue()) ? "selected" : "") + ">" + ratingValue.getCategory().getName() + "</option>");
            }
            out.write("</select>");
            out.write("                                </td>\n"
                    + "                                <td class=\"editable\">\n");
            out.write("<select id=\"actual-outcome-value-" + outcome.getId() + "\" onchange=\"calculateOutcomeRatio(" + outcome.getId() + ")\">");
            for (PhenomenonDetails ratingValue : ratingValues) {
                out.write("<option value=\"" + ratingValue.getCategory().getName() + "\"" + (Double.valueOf(ratingValue.getCategory().getName()).equals(outcome.getActualValue()) ? "selected" : "") + ">" + ratingValue.getCategory().getName() + "</option>");
            }
            out.write("</select>");
            out.write("                                </td>\n"
                    + "                                <td id=\"outcome-ratio-" + outcome.getId() + "\">" + outcome.getRatio() + outcome.getRatio() == null ? "" : "%" + "</td>\n"
                            + "                                </tr>"
            );
        }
    }
    //</editor-fold>

    private static final Logger LOGGER = Logger.getLogger(ReportsController.class.getSimpleName());
    @EJB
    private PhenomenonRequestsLocal phenomenonService;
    @EJB
    private SubActivityRequestsLocal subActivityService;
    @EJB
    private FinancialYearRequestsLocal financialYearService;
    @EJB
    private ActivityProgressRequestsLocal activityProgressService;
    @EJB
    private PerformanceIndicatorValuesRequestsLocal performanceIndicatorValuesService;

}
