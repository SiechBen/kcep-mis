/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.math.BigDecimal;
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
import ke.co.miles.kcep.mis.requests.activityplanning.activity.ActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.description.SubActivityDescriptionRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityDetails;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;
import ke.co.miles.kcep.mis.utilities.ExpenditureCategoryDetails;
import ke.co.miles.kcep.mis.utilities.ImplementingPartnerDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.ResponsePcuDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityDescriptionDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;
import ke.co.miles.kcep.mis.utilities.SubComponentDetails;

/**
 *
 * @author siech
 */
@WebServlet(
        name = "PlanningController",
        urlPatterns = {"/activities", "/addActivity", "/doAddActivity", "/subActivities", "/addSubActivity",
            "/doAddSubActivity",
            "/subActivityDescriptions", "/addSubActivityDescription", "/doAddSubActivityDescription"}
)
public class ActivityPlanningController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession();

        String path = request.getServletPath();
        String destination;

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
                            urlPaths.add("/doAddActivity");
                            urlPaths.add("/doAddPlanning");
                            urlPaths.add("/doAddSubActivity");
                            urlPaths.add("/doAddSubActivityDescription");
                            switch (path) {
                                case "/planning":
                                    path = "/head_planning";
                                    urlPaths.add(path);
                                    break;
                                case "/subActivities":
                                    path = "/head_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/subActivityDescription":
                                    path = "/head_sub_activity_descriptions";
                                    urlPaths.add(path);
                                    break;
                                case "/activities":
                                    path = "/head_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/addActivity":
                                    path = "/head_addActivity";
                                    urlPaths.add(path);
                                    break;
                                case "/addPlanning":
                                    path = "/head_addPlanning";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/head_addSubActivity";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivityDescription":
                                    path = "/head_addSubActivityDescription";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPlanning");
                            urlPaths.add("/doAddSubActivity");
                            switch (path) {
                                case "/planning":
                                    path = "/region_planning";
                                    urlPaths.add(path);
                                    break;
                                case "/subActivities":
                                    path = "/region_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/addPlanning":
                                    path = "/region_addPlanning";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/region_addSubActivity";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPlanning");
                            urlPaths.add("/doAddSubActivity");
                            switch (path) {
                                case "/planning":
                                    path = "/county_planning";
                                    urlPaths.add(path);
                                    break;
                                case "/subActivities":
                                    path = "/county_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/addPlanning":
                                    path = "/county_addPlanning";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/county_addSubActivity";
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

            switch (path) {

                case "/head_sub_activity_descritions":
                    try {
                        session.setAttribute("subActivityDescriptions", subActivityDescriptionService.retrieveSubActivityDescriptions());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    break;

                case "/head_activities":
                    try {
                        session.setAttribute("activities", activityService.retrieveActivities());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    break;

                case "/doAddActivity":
                    ActivityDetails activity = new ActivityDetails();
                    activity.setName(request.getParameter("activityName"));

                    try {
                        activityService.addActivity(activity);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("activities", activityService.retrieveActivities());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doAddSubActivityDescription":
                    SubActivityDescriptionDetails subActivityDescription = new SubActivityDescriptionDetails();
                    subActivityDescription.setDescription(request.getParameter("description"));

                    try {
                        subActivityDescriptionService.addSubActivityDescription(subActivityDescription);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("subActivityDescriptions", subActivityDescriptionService.retrieveSubActivityDescriptions());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/head_sub_activities":
                case "/county_sub_activities":
                case "/region_sub_activities":
                    try {
                        session.setAttribute("subActivities", subActivityService.retrieveSubActivities());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException ex) {
                        LOGGER.log(Level.INFO, "", ex);

                    }
                    break;

                case "/head_addSubActivity":
                case "/county_addSubActivity":
                case "/region_addSubActivity":
                    try {
                        session.setAttribute("activities", activityService.retrieveActivities());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    List<MeasurementUnitDetails> planningMeasurementUnits;
                    try {
                        planningMeasurementUnits = measurementUnitService.retrievePlanningMeasurementUnits();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of measurement units", ex);
                        return;
                    }

                    if (planningMeasurementUnits != null) {
                        session.setAttribute("planningMeasurementUnits", planningMeasurementUnits);
                    }

                    List<ImplementingPartnerDetails> implementingPartners;
                    try {
                        implementingPartners = implementingPartnerService.retrieveImplementingPartners();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE,
                                "An error occurred during retrieval of implementing partners", ex);
                        return;
                    }

                    if (implementingPartners != null) {
                        session.setAttribute("implementingPartners", implementingPartners);
                    }

                    List<ComponentDetails> components;
                    try {
                        components = componentService.retrieveComponents();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of components", ex);
                        return;
                    }

                    if (components != null) {
                        session.setAttribute("components", components);
                    }

                    List<SubComponentDetails> subComponents;
                    try {
                        subComponents = subComponentService.retrieveSubComponents();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of sub-components", ex);
                        return;
                    }

                    if (subComponents != null) {
                        session.setAttribute("subComponents", subComponents);
                    }

                    break;

                case "/doAddPlanning":

                    SubActivityDetails subActivity = new SubActivityDetails();
                    subActivity.setProcurementPlan(request.getParameter("procurementPlan"));
                    subActivity.setDescription(request.getParameter("description"));
                    subActivity.setAnnualWorkplanReferenceCode(
                            request.getParameter("annualWorkplanReferenceCode"));
                    try {
                        subActivity.setTotals(new BigDecimal(request.getParameter("totals")));
                    } catch (NumberFormatException e) {
                        subActivity.setTotals(null);
                    }
                    try {
                        subActivity.setUnitCost(new BigDecimal(request.getParameter("unitCost")));
                    } catch (NumberFormatException e) {
                        subActivity.setUnitCost(null);
                    }
                    try {
                        subActivity.setAwpbTarget(new BigDecimal(request.getParameter("awpbTarget")));
                    } catch (NumberFormatException e) {
                        subActivity.setAwpbTarget(null);
                    }
                    try {
                        subActivity.setValueAchieved(new BigDecimal(request.getParameter("valueAchieved")));
                    } catch (Exception e) {
                        subActivity.setValueAchieved(null);
                    }
                    try {
                        subActivity.setAllocatedBudget(new BigDecimal(request.getParameter("allocatedBudget")));
                    } catch (Exception e) {
                        subActivity.setAllocatedBudget(null);
                    }
                    try {
                        subActivity.setGokPercentage(new Double(request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setGokPercentage(null);
                    }
                    try {
                        subActivity.setIfadLoanPercentage(new Double(
                                request.getParameter("ifadLoanPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadLoanPercentage(null);
                    }
                    try {
                        subActivity.setIfadGrantPercentage(new Double(
                                request.getParameter("ifadGrantPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadGrantPercentage(null);
                    }
                    try {
                        subActivity.setBeneficiariesPercentage(new Double(
                                request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setBeneficiariesPercentage(null);
                    }
                    try {
                        subActivity.setEuPercentage(new Double(request.getParameter("euPercentage")));
                    } catch (Exception e) {
                        subActivity.setEuPercentage(null);
                    }
                    try {
                        subActivity.setFinancialInstitutionPercentage(new Double(
                                request.getParameter("financialInstitutionPercentage")));
                    } catch (Exception e) {
                        subActivity.setFinancialInstitutionPercentage(null);
                    }
                    try {
                        subActivity.setProgrammeTarget(new BigDecimal(
                                request.getParameter("programmeTarget")));
                    } catch (Exception e) {
                        subActivity.setProgrammeTarget(null);
                    }
                    try {
                        subActivity.setComponent(new ComponentDetails(Short.valueOf(
                                request.getParameter("component"))));
                    } catch (Exception e) {
                        subActivity.setComponent(null);
                    }
                    try {
                        subActivity.setActivity(new ActivityDetails(Short.valueOf(request.getParameter("activity"))));
                    } catch (Exception e) {
                        subActivity.setActivity(null);
                    }
                    try {
                        subActivity.setSubComponent(new SubComponentDetails(
                                Short.valueOf(request.getParameter("subComponent"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setResponsePcu(new ResponsePcuDetails(
                                Short.valueOf(request.getParameter("responsePcu"))));
                    } catch (Exception e) {
                        subActivity.setResponsePcu(null);
                    }
                    try {
                        subActivity.setSubActivityDescription(new SubActivityDescriptionDetails(
                                Short.valueOf(request.getParameter("subActivityDescription"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setExpenditureCategory(new ExpenditureCategoryDetails(
                                Short.valueOf(request.getParameter("expenditureCategory"))));
                    } catch (Exception e) {
                        subActivity.setExpenditureCategory(null);
                    }
                    try {
                        subActivity.setImplementingPartner(new ImplementingPartnerDetails(
                                Short.valueOf(request.getParameter("implementingPartner"))));
                    } catch (Exception e) {
                        subActivity.setImplementingPartner(null);
                    }
                    try {
                        subActivity.setPerformanceIndicator(new PerformanceIndicatorDetails(
                                Short.valueOf(request.getParameter("performanceIndicator"))));
                    } catch (Exception e) {
                        subActivity.setPerformanceIndicator(null);
                    }
                    try {
                        subActivity.setMeasurementUnit(new MeasurementUnitDetails(
                                Short.valueOf(request.getParameter("measurementUnit"))));
                    } catch (Exception e) {
                        subActivity.setMeasurementUnit(null);
                    }
                    try {
                        subActivity.setStartDate(databaseDateFormat.parse(userDateFormat.format(
                                userDateFormat.parse(request.getParameter("startDate")))));
                    } catch (Exception ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString("string_parse_error"), ex);
                        subActivity.setStartDate(null);
                    }
                    try {
                        subActivity.setEndDate(databaseDateFormat.parse(userDateFormat.format(
                                userDateFormat.parse(request.getParameter("endDate")))));
                    } catch (Exception ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString("string_parse_error"), ex);
                        subActivity.setEndDate(null);
                    }

                    try {
                        subActivityService.addSubActivity(subActivity);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
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

    private static final Logger LOGGER = Logger.getLogger(ActivityPlanningController.class
            .getSimpleName());

    @EJB
    private ActivityRequestsLocal activityService;
    @EJB
    private SubActivityRequestsLocal subActivityService;
    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private SubComponentRequestsLocal subComponentService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private ImplementingPartnerRequestsLocal implementingPartnerService;
    @EJB
    private SubActivityDescriptionRequestsLocal subActivityDescriptionService;
}
