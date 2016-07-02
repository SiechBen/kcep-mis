/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.PlanningRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityDetails;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;
import ke.co.miles.kcep.mis.utilities.ImplementingPartnerDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PlanningDetails;
import ke.co.miles.kcep.mis.utilities.SubComponentDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "PlanningController", urlPatterns = {"/planning", "/addPlanning", "/doAddPlanning"})
public class PlanningController extends Controller {

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
                            if (path.equals("/planning")) {
                                path = "/head_planning";
                                urlPaths.add(path);
                            } else if (path.equals("/addPlanning")) {
                                path = "/head_addPlanning";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPlanning");
                            if (path.equals("/planning")) {
                                path = "/region_planning";
                                urlPaths.add(path);
                            } else if (path.equals("/addPlanning")) {
                                path = "/region_addPlanning";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPlanning");
                            if (path.equals("/planning")) {
                                path = "/county_planning";
                                urlPaths.add(path);
                            } else if (path.equals("/addPlanning")) {
                                path = "/county_addPlanning";
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

                case "/head_planning":
                case "/county_planning":
                case "/region_planning":

                    List<PlanningDetails> planningList;
                    try {
                        planningList = planningService.retrievePlannings();
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

                    PlanningDetails planning = new PlanningDetails();
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

                    MeasurementUnitDetails measurementUnit;
                    try {
                        measurementUnit = new MeasurementUnitDetails(Short
                                .valueOf(request.getParameter("measurementUnit")));
                    } catch (Exception e) {
                        measurementUnit = null;
                    }

                    ComponentDetails component;
                    try {
                        component = new ComponentDetails(Short.valueOf(request.getParameter("component")));
                    } catch (Exception e) {
                        component = null;
                    }

                    ActivityDetails activity;
                    try {
                        activity = new ActivityDetails(Integer.valueOf(request.getParameter("component")));
                    } catch (Exception e) {
                        activity = null;
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

                    planning.setActivity(activity);
                    planning.setComponent(component);
                    planning.setSubComponent(subComponent);
                    planning.setImplementingPartner(implementingPartner);

                    try {
                        planningService.addPlanning(planning);
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

    private static final Logger LOGGER = Logger.getLogger(PlanningController.class
            .getSimpleName());

    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private PlanningRequestsLocal planningService;
    @EJB
    private SubComponentRequestsLocal subComponentService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private ImplementingPartnerRequestsLocal implementingPartnerService;

}
