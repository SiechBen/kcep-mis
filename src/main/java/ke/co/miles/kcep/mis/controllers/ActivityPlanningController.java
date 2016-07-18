/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.math.BigDecimal;
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
import ke.co.miles.kcep.mis.requests.activityplanning.ActivityPlanningRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.subactivity.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityPlanningDetails;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;
import ke.co.miles.kcep.mis.utilities.ImplementingPartnerDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;
import ke.co.miles.kcep.mis.utilities.SubComponentDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "PlanningController", urlPatterns = {"/planning", "/addPlanning", "/doAddPlanning", "/subActivities", "/addSubActivity", "/doAddSubActivity"})
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
                            urlPaths.add("/doAddPlanning");
                            urlPaths.add("/doAddSubActivity");
                            switch (path) {
                                case "/planning":
                                    path = "/head_planning";
                                    urlPaths.add(path);
                                    break;
                                case "/subActivities":
                                    path = "/head_sub_activities";
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

                case "/head_sub_activities":
                case "/county_sub_activities":
                case "/region_sub_activities":
                    Integer activityPlanningId;
                    List<SubActivityDetails> subActivities;
                    try {
                        activityPlanningId = Integer.valueOf(request.getParameter("activityPlanningId"));
                        subActivities = subActivityService.retrieveSubActivities(activityPlanningId);
                        session.setAttribute("subActivities", subActivities);
                        session.setAttribute("activityPlanningId", activityPlanningId);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException ex) {
                        LOGGER.log(Level.INFO, "", ex);

                    }
                    break;

                case "/doAddSubActivity":
                    SubActivityDetails subActivity = new SubActivityDetails();
                    subActivity.setDescription(request.getParameter("description"));

                    try {
                        subActivity.setActivityPlanning(new ActivityPlanningDetails(Integer.valueOf(request.getParameter("activityPlanning"))));
                    } catch (Exception e) {
                        subActivity.setActivityPlanning(null);
                    }

                    try {
                        subActivity.setActualExpenditure(new BigDecimal(request.getParameter("actualExpenditure")));
                    } catch (Exception e) {
                        subActivity.setActualExpenditure(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("startDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setStartDate(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString("string_parse_error"), ex);
                        subActivity.setStartDate(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("endDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setEndDate(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString("string_parse_errpr"), ex);
                        subActivity.setEndDate(null);
                    }

                    try {
                        subActivity.setMeasurementUnit(new MeasurementUnitDetails(Short.valueOf(request.getParameter("measurementUnit"))));
                    } catch (Exception e) {
                        subActivity.setMeasurementUnit(null);
                    }

                    activityPlanningId = 0;
                    try {
                        activityPlanningId = Integer.valueOf(request.getParameter("activityPlanningId"));
                        subActivity.setActivityPlanning(new ActivityPlanningDetails(activityPlanningId));
                    } catch (Exception e) {
                    }

                    try {
                        subActivityService.addSubActivity(subActivity);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        subActivities = subActivityService.retrieveSubActivities(activityPlanningId);
                        session.setAttribute("subActivities", subActivities);
                    } catch (MilesException e) {
                    }

                    return;

                case "/head_planning":
                case "/county_planning":
                case "/region_planning":

                    List<ActivityPlanningDetails> planningList;
                    try {
                        planningList = planningService.retrieveActivityPlannings();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during planning retrieval", ex);
                        return;
                    }

                    if (planningList != null) {
                        session.setAttribute("planning", planningList);
                    }
                    break;

                case "/head_addPlanning":
                case "/county_addPlanning":
                case "/region_addPlanning":

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
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of implementing partners", ex);
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

                    ActivityPlanningDetails planning = new ActivityPlanningDetails();
                    planning.setAnnualWorkplanReferenceCode(request.getParameter("annualWorkplanReferenceCode"));

                    try {
                        planning.setAwpbTarget(new BigDecimal(request.getParameter("awpbTarget")));
                    } catch (NumberFormatException e) {
                        planning.setAwpbTarget(null);
                    }
                    try {
                        planning.setValueAchieved(new BigDecimal(request.getParameter("valueAchieved")));
                    } catch (Exception e) {
                        planning.setValueAchieved(null);
                    }
                    try {
                        planning.setAllocatedBudget(new BigDecimal(request.getParameter("allocatedBudget")));
                    } catch (Exception e) {
                        planning.setAllocatedBudget(null);
                    }
                    try {
                        planning.setProgrammeTarget(new BigDecimal(request.getParameter("programmeTarget")));
                    } catch (Exception e) {
                        planning.setProgrammeTarget(null);
                    }

                    ComponentDetails component;
                    try {
                        component = new ComponentDetails(Short.valueOf(request.getParameter("component")));
                    } catch (Exception e) {
                        component = null;
                    }

                    SubComponentDetails subComponent;
                    try {
                        subComponent
                                = new SubComponentDetails(Short.valueOf(request.getParameter("subComponent")));
                    } catch (Exception e) {
                        subComponent = null;
                    }

                    ImplementingPartnerDetails implementingPartner;
                    try {
                        implementingPartner = new ImplementingPartnerDetails(
                                Short.valueOf(request.getParameter("implementingPartner")));
                    } catch (Exception e) {
                        implementingPartner = null;
                    }

                    try {
                        planning.setPerformanceIndicator(new PerformanceIndicatorDetails(Integer.
                                valueOf(request.getParameter("performanceIndicator"))));
                    } catch (Exception e) {
                        planning.setPerformanceIndicator(null);
                    }

                    planning.setComponent(component);
                    planning.setSubComponent(subComponent);
                    planning.setImplementingPartner(implementingPartner);

                    try {
                        planningService.addActivityPlanning(planning);
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

    private static final Logger LOGGER = Logger.getLogger(ActivityPlanningController.class
            .getSimpleName());

    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private ActivityPlanningRequestsLocal planningService;
    @EJB
    private SubActivityRequestsLocal subActivityService;
    @EJB
    private SubComponentRequestsLocal subComponentService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private ImplementingPartnerRequestsLocal implementingPartnerService;

}
