package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
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
import ke.co.miles.kcep.mis.requests.activityplanning.activity.progress.comment.ActivityProgressCommentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.financialyear.FinancialYearRequestsLocal;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.values.PerformanceIndicatorValuesRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityProgressCommentDetails;
import ke.co.miles.kcep.mis.utilities.ActivityProgressDetails;
import ke.co.miles.kcep.mis.utilities.ActivityProgressReportDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorValuesDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "ReportsController", urlPatterns = {"/reports",
    "/financial_report_by_categories", "/financial_report_by_components",
    "/updateIndicatorValues", "/changeOutcomeReport", "/goalLevelReports",
    "/outputLevelReports", "/outcomeLevelReports", "/activity_report",
    "/getActivityProgress", "/setAppraisalTarget",
    "/doEditActivityProgress", "/doEditActivityProgressComment",
    "/doEditMeasurementUnit", "/changeQuarter", "/changeFinancialYear"})
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
        PrintWriter out = response.getWriter();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {

                switch (rightsMap) {
                    case "systemAdminSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/getActivityProgress");
                            urlPaths.add("/changeQuarter");
                            urlPaths.add("/changeFinancialYear");
                            urlPaths.add("/doEditMeasurementUnit");
                            urlPaths.add("/doEditActivityProgress");
                            urlPaths.add("/doEditActivityProgressComment");
                            urlPaths.add("/updateIndicatorValues");
                            urlPaths.add("/setAppraisalTarget");
                            urlPaths.add("/changeOutcomeReport");
                            switch (path) {
                                case "/reports":
                                    path = "/head_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/activity_report":
                                    path = "/head_activity_report";
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
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/getActivityProgress");
                            urlPaths.add("/changeQuarter");
                            urlPaths.add("/changeFinancialYear");
                            urlPaths.add("/doEditMeasurementUnit");
                            urlPaths.add("/doEditActivityProgress");
                            urlPaths.add("/doEditActivityProgressComment");
                            urlPaths.add("/updateIndicatorValues");
                            urlPaths.add("/setAppraisalTarget");
                            urlPaths.add("/changeOutcomeReport");
                            switch (path) {
                                case "/reports":
                                    path = "/head_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/activity_report":
                                    path = "/head_activity_report";
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
                            urlPaths.add("/getActivityProgress");
                            urlPaths.add("/changeQuarter");
                            urlPaths.add("/doEditMeasurementUnit");
                            urlPaths.add("/doEditActivityProgress");
                            urlPaths.add("/doEditActivityProgressComment");
                            urlPaths.add("/changeFinancialYear");
                            urlPaths.add("/setAppraisalTarget");
                            urlPaths.add("/updateIndicatorValues");
                            urlPaths.add("/changeOutcomeReport");
                            switch (path) {
                                case "/reports":
                                    path = "/region_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/activity_report":
                                    path = "/region_activity_report";
                                    urlPaths.add(path);
                                    break;
                                case "/outputLevelReports":
                                    path = "/region_output_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/goalLevelReports":
                                    path = "/region_goal_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/outcomeLevelReports":
                                    path = "/region_outcome_level_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_report_by_components":
                                    path = "/region_financial_report_by_components";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_report_by_categories":
                                    path = "/region_financial_report_by_categories";
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
                            urlPaths.add("/changeQuarter");
                            urlPaths.add("/changeFinancialYear");
                            urlPaths.add("/updateIndicatorValues");
                            urlPaths.add("/doEditMeasurementUnit");
                            urlPaths.add("/doEditActivityProgress");
                            urlPaths.add("/doEditActivityProgressComment");
                            urlPaths.add("/setAppraisalTarget");
                            urlPaths.add("/changeOutcomeReport");
                            switch (path) {
                                case "/reports":
                                    path = "/county_reports";
                                    urlPaths.add(path);
                                    break;
                                case "/activity_report":
                                    path = "/county_activity_report";
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

                case "/head_activity_report":
                case "/county_activity_report":
                case "/region_activity_report":

                    try {
                        activityProgressService.checkForActivityProgress(null);
                    } catch (Exception e) {
                    }
                    try {
                        session.setAttribute("financialYears", financialYearService.retrieveFinancialYears());
                    } catch (Exception e) {
                    }
                    try {
                        session.setAttribute("awpbReferenceCodes", subActivityService.retrieveReferenceCodes());
                    } catch (Exception e) {
                    }
                    Short financialYearId = null;
                    try {
                        financialYearId = (Short) session.getAttribute("financialYear");
                    } catch (Exception e) {
                    }

                    PersonDetails accessingPerson = (PersonDetails) session.getAttribute("person");
                    Set<ActivityProgressReportDetails> previousActivityProgressReports;
                    Set<ActivityProgressReportDetails> newActivityProgressReports;
                    ActivityProgressReportDetails newActivityProgressReport;
                    newActivityProgressReports = new HashSet<>();
                    try {
                        previousActivityProgressReports = (Set< ActivityProgressReportDetails>) session.getAttribute("activityProgressReports");
                        if (previousActivityProgressReports == null) {
                            previousActivityProgressReports = new HashSet<>();
                        }
                    } catch (Exception e) {
                        previousActivityProgressReports = new HashSet<>();
                    }
                    try {
                        /* Retrieve activity progress details and add those available previously to the report(set) */
                        if (accessingPerson.getPersonRoleId().equals(PersonRoleDetail.REGIONAL_COORDINATOR.getId())) {
                            for (ActivityProgressReportDetails previousActivityProgressReport : previousActivityProgressReports) {
                                newActivityProgressReports.add(activityProgressService.retrieveActivityProgress(previousActivityProgressReport.getActivityProgressComment().getSubActivity().getAnnualWorkplanReferenceCode(), "Region", accessingPerson.getLocation().getCounty().getRegion().getId(), financialYearId));
                            }
                        } else if (accessingPerson.getPersonRoleId().equals(PersonRoleDetail.COUNTY_OFFICER.getId())) {
                            for (ActivityProgressReportDetails previousActivityProgressReport : previousActivityProgressReports) {
                                newActivityProgressReports.add(activityProgressService.retrieveActivityProgress(previousActivityProgressReport.getActivityProgressComment().getSubActivity().getAnnualWorkplanReferenceCode(), "County", accessingPerson.getLocation().getCounty().getId(), financialYearId));
                            }
                        } else {
                            for (ActivityProgressReportDetails previousActivityProgressReport : previousActivityProgressReports) {
                                newActivityProgressReports.add(activityProgressService.retrieveActivityProgress(previousActivityProgressReport.getActivityProgressComment().getSubActivity().getAnnualWorkplanReferenceCode(), "Head", null, financialYearId));
                            }
                        }

                        /* Avail the progress report set in session */
                        session.setAttribute("activityProgressReports", newActivityProgressReports);
                    } catch (Exception e) {
                        MilesDebugger.debug(e);
                    }

                    break;

                case "/doEditActivityProgress":

                    ActivityProgressDetails activityProgress;
                    try {
                        activityProgress = new ActivityProgressDetails(Integer.valueOf(request.getParameter("id")));
                        switch (request.getParameter("valueType")) {
                            case "Value achieved":
                            case "Expense":
                                activityProgress.setValueAchievedOrExpense(new BigDecimal(request.getParameter("activityProgressValue")));
                                break;
                            case "Target":
                            case "Budget":
                            case "Appraisal":
                                activityProgress.setTargetOrBudget(new BigDecimal(request.getParameter("activityProgressValue")));
                        }

                        activityProgressService.editActivityProgress(activityProgress);

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NumberFormatException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("invalid_number_format") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("invalid_number_format"), e);
                    }
                    try {
                        activityProgressService.checkForActivityProgress(null);
                    } catch (Exception e) {
                    }

                    try {
                        session.setAttribute("awpbReferenceCodes", subActivityService.retrieveReferenceCodes());
                    } catch (Exception e) {
                    }

                    return;

                case "/doEditActivityProgressComment":

                    ActivityProgressCommentDetails activityProgressComment;
                    try {

                        activityProgressComment = new ActivityProgressCommentDetails(Integer.valueOf(request.getParameter("id")));
                        activityProgressComment.setComment(request.getParameter("comment"));

                        activityProgressCommentService.editActivityProgressComment(activityProgressComment);

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NumberFormatException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("invalid_number_format") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("invalid_number_format"), e);
                    }
                    try {
                        activityProgressService.checkForActivityProgress(null);
                    } catch (Exception e) {
                    }

                    try {
                        session.setAttribute("awpbReferenceCodes", subActivityService.retrieveReferenceCodes());
                    } catch (Exception e) {
                    }

                    return;

                case "/doEditMeasurementUnit":

                    PerformanceIndicatorDetails performanceIndicator;
                    try {

                        performanceIndicator = new PerformanceIndicatorDetails(Short.valueOf(request.getParameter("id")));
                        performanceIndicator.setMeasurementUnit(new MeasurementUnitDetails(Short.valueOf(request.getParameter("measurementUnit"))));

                        performanceIndicatorService.editPerformanceIndicator(performanceIndicator);

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NumberFormatException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("invalid_number_format") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("invalid_number_format"), e);
                    }
                    return;

                case "/getActivityProgress":

                    accessingPerson = (PersonDetails) session.getAttribute("person");

                    financialYearId = null;
                    try {
                        financialYearId = (Short) session.getAttribute("financialYear");
                    } catch (Exception e) {
                    }

                    newActivityProgressReports = new HashSet<>();
                    try {
                        previousActivityProgressReports = (Set< ActivityProgressReportDetails>) session.getAttribute("activityProgressReports");
                        if (previousActivityProgressReports == null) {
                            previousActivityProgressReports = new HashSet<>();
                        }
                    } catch (Exception e) {
                        previousActivityProgressReports = new HashSet<>();
                    }
                    try {
                        /* Retrieve activity progress details and add those available previously to the report(set) */
                        if (accessingPerson.getPersonRoleId().equals(PersonRoleDetail.REGIONAL_COORDINATOR.getId())) {
                            for (ActivityProgressReportDetails previousActivityProgressReport : previousActivityProgressReports) {
                                newActivityProgressReports.add(activityProgressService.retrieveActivityProgress(previousActivityProgressReport.getActivityProgressComment().getSubActivity().getAnnualWorkplanReferenceCode(), "Region", accessingPerson.getLocation().getCounty().getRegion().getId(), financialYearId));
                            }
                            newActivityProgressReport = activityProgressService.retrieveActivityProgress(request.getParameter("awpbReferenceCode"), "Region", accessingPerson.getLocation().getCounty().getRegion().getId(), financialYearId);
                        } else if (accessingPerson.getPersonRoleId().equals(PersonRoleDetail.COUNTY_OFFICER.getId())) {
                            for (ActivityProgressReportDetails previousActivityProgressReport : previousActivityProgressReports) {
                                newActivityProgressReports.add(activityProgressService.retrieveActivityProgress(previousActivityProgressReport.getActivityProgressComment().getSubActivity().getAnnualWorkplanReferenceCode(), "County", accessingPerson.getLocation().getCounty().getId(), financialYearId));
                            }
                            newActivityProgressReport = activityProgressService.retrieveActivityProgress(request.getParameter("awpbReferenceCode"), "County", accessingPerson.getLocation().getCounty().getId(), financialYearId);
                        } else {
                            for (ActivityProgressReportDetails previousActivityProgressReport : previousActivityProgressReports) {
                                newActivityProgressReports.add(activityProgressService.retrieveActivityProgress(previousActivityProgressReport.getActivityProgressComment().getSubActivity().getAnnualWorkplanReferenceCode(), "Head", null, financialYearId));
                            }
                            newActivityProgressReport = activityProgressService.retrieveActivityProgress(request.getParameter("awpbReferenceCode"), "Head", null, financialYearId);
                        }

                        /* Add the new activity progress details to the report if it doesn't exist already */
                        newActivityProgressReports.add(newActivityProgressReport);

                        /* Avail the progress report set in session */
                        session.setAttribute("activityProgressReports", newActivityProgressReports);
                    } catch (Exception e) {
                        MilesDebugger.debug(e);
                    }

                    return;

                case "/updateIndicatorValues":
                    PerformanceIndicatorValuesDetails performanceIndicatorValues;
                    try {
                        performanceIndicatorValues = new PerformanceIndicatorValuesDetails(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                        performanceIndicatorValues = new PerformanceIndicatorValuesDetails();
                    }
                    try {
                        performanceIndicatorValues.setActualValue(Double.valueOf(request.getParameter("actualValue")));
                    } catch (Exception e) {
                    }
                    try {
                        performanceIndicatorValues.setExpectedValue(Double.valueOf(request.getParameter("expectedValue")));
                    } catch (Exception e) {
                    }

                    try {
                        performanceIndicatorValuesService.editPerformanceIndicatorValues(performanceIndicatorValues);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }

                    return;

                case "/setAppraisalTarget":
                    try {
                        performanceIndicator = new PerformanceIndicatorDetails(Short.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                        performanceIndicator = new PerformanceIndicatorDetails();
                    }
                    try {
                        performanceIndicator.setAppraisalTarget(Double.valueOf(request.getParameter("appraisalTarget")));
                    } catch (Exception e) {
                    }

                    try {
                        performanceIndicatorService.setAppraisalTarget(performanceIndicator);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                        MilesDebugger.debug(e);
                    }

                    return;

                case "/changeQuarter":
                    short quarter = Short.valueOf(request.getParameter("quarter"));
                    session.setAttribute("quarter", quarter);

                    return;

                case "/changeFinancialYear":
                    financialYearId = Short.valueOf(request.getParameter("financialYear"));
                    session.setAttribute("financialYear", financialYearId);

                    return;

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
                        session.setAttribute("measurementUnits", measurementUnitService.retrieveMeasurementUnits());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of measurement units", ex);
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
                        session.setAttribute("projectYear", projectYear);
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

                case "/head_financial_report_by_categories":

                    try {
                        session.setAttribute("financialPlanByCategoryMap", subActivityService.
                                summarizeFinancialPlanByCategories(financialYearService.retrieveCurrentFinancialYear().getId(), null));
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
                                summarizeFinancialPlanByComponents(financialYearService.retrieveCurrentFinancialYear().getId(), null));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("report_generation_failed") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }
                    break;

                case "/county_financial_report_by_categories":

                    try {
                        session.setAttribute("financialPlanByCategoryMap", subActivityService.
                                summarizeFinancialPlanByCategories(financialYearService.retrieveCurrentFinancialYear().getId(), ((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId()));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (NullPointerException e) {
                    }

                    break;
                case "/county_financial_report_by_components":

                    try {
                        session.setAttribute("financialPlanByComponentMap", subActivityService.
                                summarizeFinancialPlanByComponents(financialYearService.retrieveCurrentFinancialYear().getId(), ((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId()));
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
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private ActivityProgressRequestsLocal activityProgressService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;
    @EJB
    private ActivityProgressCommentRequestsLocal activityProgressCommentService;
    @EJB
    private PerformanceIndicatorValuesRequestsLocal performanceIndicatorValuesService;

}
