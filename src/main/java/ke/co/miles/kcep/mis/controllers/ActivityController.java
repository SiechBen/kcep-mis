/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.inputtype.InputTypeRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.planning.activity.ActivityRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "ActivityController", urlPatterns = {"/addActivity", "/doAddActivity", "/activities"})
@MultipartConfig
public class ActivityController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        //Get the user session
        HttpSession session = request.getSession(false);

        //Get the user path
        String path = request.getServletPath();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                if (rightsMap.equals("systemAdminSession") || rightsMap.equals("nationalOfficerSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddActivity");
                        if (path.equals("/activities")) {
                            path = "/head_activities";
                            urlPaths.add(path);
                        } else if (path.equals("/addActivity")) {
                            path = "/head_addActivity";
                            urlPaths.add(path);
                        }
                    }
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/head_activities":
                    //Retrieve the list of activities
                    LOGGER.log(Level.INFO, "Retrieving the list of activities");
                    List<ActivityDetails> activities;
                    try {
                        activities = activityService.retrieveActivitys();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    //Avail the activity in the application scope
                    LOGGER.log(Level.INFO, "Avail the activity in the application scope");
                    if (activities != null) {
                        session.setAttribute("activities", activities);
                    }
                    break;

                case "/head_addActivity":

                    break;

                case "/doAddActivity":

                    ActivityDetails activity = new ActivityDetails();
                    try {
                        activity.setDescription(String.valueOf(request.getParameter("description")));
                    } catch (Exception e) {
                        activity.setDescription(null);
                    }

                    try {
                        activityService.addActivity(activity);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getMessage()));
                        LOGGER.log(Level.INFO, getBundle().getString(""), e);
                    }
                    return;
            }
            //Use request dispatcher to foward request internally
            destination = "/WEB-INF/views" + path + ".jsp";

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

    //</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(ActivityController.class.getSimpleName());
    @EJB
    private ActivityRequestsLocal activityService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private InputTypeRequestsLocal inputTypeService;

}
