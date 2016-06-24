/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.requests.programme.ProgrammeRequestsLocal;
import ke.co.miles.kcep.mis.requests.programme.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.programme.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.programme.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;
import ke.co.miles.kcep.mis.utilities.ImplementingPartnerDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.ProgrammeDetails;
import ke.co.miles.kcep.mis.utilities.SubComponentDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "ProgrammeController", urlPatterns = {"/programmes", "/addProgramme", "/doAddProgramme"})
public class ProgrammeController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        bundle = ResourceBundle.getBundle("text", locale);

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
                if (rightsMap.equals("systemAdminSession") || rightsMap.equals("nationalOfficerSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddProgramme");
                        if (path.equals("/programmes")) {
                            path = "/head_programmes";
                            urlPaths.add(path);
                        } else if (path.equals("/addProgramme")) {
                            path = "/head_addProgramme";
                            urlPaths.add(path);
                        }
                    }
                } else if (rightsMap.equals("regionalCoordinatorSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddProgramme");
                        if (path.equals("/programmes")) {
                            path = "/region_programmes";
                            urlPaths.add(path);
                        } else if (path.equals("/addProgramme")) {
                            path = "/region_addProgramme";
                            urlPaths.add(path);
                        }
                    }
                }
            }
        }

        if (urlPaths.contains(path)) {

            switch (path) {

                case "/head_programmes":
                case "/region_programmes":

                    List<ProgrammeDetails> programmes;
                    try {
                        programmes = programmeService.retrieveProgrammes();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during programmes retrieval", ex);
                        return;
                    }

                    if (programmes != null) {
                        session.setAttribute("programmes", programmes);
                    }
                    break;

                case "/head_addProgramme":
                case "/region_addProgramme":

                    List<MeasurementUnitDetails> programmeMeasurementUnits;
                    try {
                        programmeMeasurementUnits = measurementUnitService.retrieveProgrammeMeasurementUnits();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of measurement units", ex);
                        return;
                    }

                    if (programmeMeasurementUnits != null) {
                        session.setAttribute("programmeMeasurementUnits", programmeMeasurementUnits);
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

                case "/doAddProgramme":

                    ProgrammeDetails programme = new ProgrammeDetails();
                    programme.setActivity(String.valueOf(request.getParameter("activity")));
                    programme.setAwpTarget(String.valueOf(request.getParameter("awpTarget")));
                    programme.setEndPeriod(String.valueOf(request.getParameter("endPeriod")));
                    programme.setStartPeriod(String.valueOf(request.getParameter("startPeriod")));
                    programme.setValueAchieved(String.valueOf(request.getParameter("valueAchieved")));
                    programme.setRequestedBudget(String.valueOf(request.getParameter("requestedBudget")));
                    programme.setProgrammeTarget(String.valueOf(request.getParameter("programmeTarget")));
                    programme.setActualExpenditure(String.valueOf(request.getParameter("actualExpenditure")));

                    if (programme.getActivity().equals("null")) {
                        programme.setActivity(null);
                    }
                    if (programme.getAwpTarget().equals("null")) {
                        programme.setAwpTarget(null);
                    }
                    if (programme.getEndPeriod().equals("null")) {
                        programme.setEndPeriod(null);
                    }
                    if (programme.getStartPeriod().equals("null")) {
                        programme.setStartPeriod(null);
                    }
                    if (programme.getValueAchieved().equals("null")) {
                        programme.setValueAchieved(null);
                    }
                    if (programme.getRequestedBudget().equals("null")) {
                        programme.setRequestedBudget(null);
                    }
                    if (programme.getProgrammeTarget().equals("null")) {
                        programme.setProgrammeTarget(null);
                    }
                    if (programme.getActualExpenditure().equals("null")) {
                        programme.setActualExpenditure(null);
                    }

                    MeasurementUnitDetails measurementUnit;
                    try {
                        measurementUnit = new MeasurementUnitDetails(Short.valueOf(request.getParameter("measurementUnit")));
                    } catch (Exception e) {
                        measurementUnit = null;
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

                    programme.setComponent(component);
                    programme.setSubComponent(subComponent);
                    programme.setImplementingPartner(implementingPartner);

                    try {
                        programmeService.addProgramme(programme);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(bundle.getString(e.getCode()));
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
                response.getWriter().write(bundle.getString("redirection_failed") + "<br>");
                LOGGER.log(Level.INFO, bundle.getString("redirection_failed"), e);

            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(bundle.getString("error_016_02") + "<br>");
            LOGGER.log(Level.INFO, bundle.getString("error_016_02"));
        }
    }

    private static final Logger LOGGER = Logger.getLogger(ProgrammeController.class.getSimpleName());

    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private ProgrammeRequestsLocal programmeService;
    @EJB
    private SubComponentRequestsLocal subComponentService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private ImplementingPartnerRequestsLocal implementingPartnerService;

}
