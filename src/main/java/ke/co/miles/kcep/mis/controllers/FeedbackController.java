/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import ke.co.miles.kcep.mis.requests.farmer.feedback.FeedbackRequestsLocal;
import ke.co.miles.kcep.mis.utilities.FeedbackDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "FeedbackController", urlPatterns = {"/feedback", "/saveFeedback"})
public class FeedbackController extends Controller {

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
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/head_feedback";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "farmerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/feedback");
                            urlPaths.add("/farmer_saveFeedback");
                            urlPaths.add(path);
                        }
                        break;
                    case "agroDealerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/agro_dealer_feedback";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/county_feedback";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/sub_county_feedback";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "equityPersonellSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/equityfeedback";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "kalroOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/kalro_feedback";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/region_feedback";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/ward_feedback";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "warehouseOperatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveFeedback");
                            if (path.equals("/feedback")) {
                                path = "/warehouse_feedback";
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

            PersonDetails user = (PersonDetails) session.getAttribute("person");

            switch (path) {

                case "/agro_dealer_feedback":
                case "/sub_county_feedback":

                    List<FeedbackDetails> feedbackList;
                    try {
                        feedbackList = feedbackService.retrieveSubCountyFeedback(user.getLocation().getSubCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a sub-county", ex);
                        return;
                    }

                    if (!feedbackList.isEmpty()) {
                        session.setAttribute("feedbackList", feedbackList);
                    }

                    break;

                case "/county_feedback":

                    try {
                        feedbackList = feedbackService.retrieveCountyFeedback(user.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a county", ex);
                        return;
                    }

                    if (!feedbackList.isEmpty()) {
                        session.setAttribute("feedbackList", feedbackList);
                    }

                    break;

                case "/head_feedback":
                case "/kalro_feedback":

                    try {
                        feedbackList = feedbackService.retrieveFeedback();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback", ex);
                        return;
                    }

                    if (!feedbackList.isEmpty()) {
                        session.setAttribute("feedbackList", feedbackList);
                    }

                    break;

                case "/region_feedback":

                    try {
                        feedbackList = feedbackService.retrieveRegionFeedback(user.getLocation().getCounty().getRegion().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a region", ex);
                        return;
                    }

                    if (!feedbackList.isEmpty()) {
                        session.setAttribute("feedbackList", feedbackList);
                    }

                    break;

                case "/ward_feedback":
                case "/warehouse_feedback":

                    try {
                        feedbackList = feedbackService.retrieveWardFeedback(user.getLocation().getWard().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a region", ex);
                        return;
                    }

                    if (!feedbackList.isEmpty()) {
                        session.setAttribute("feedbackList", feedbackList);
                    }

                    break;

                case "/farmer_saveFeedback":

                    String feedbackMessage = request.getParameter("feedback");
                    if (feedbackMessage.equals("null")) {
                        feedbackMessage = null;
                    }

                    PersonDetails farmer = (PersonDetails) session.getAttribute("person");

                    FeedbackDetails feedback = new FeedbackDetails();
                    feedback.setMessage(feedbackMessage);
                    feedback.setTimePosted(new Date());
                    feedback.setFarmer(farmer);

                    try {
                        feedbackService.addFeedback(feedback);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    try {
                        feedbackList = feedbackService.retrieveLatestFeedback();
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of latest feedback records", e);
                        return;
                    }
                    if (feedbackList != null) {
                        getServletContext().setAttribute("latestFeedbackList", feedbackList);
                    }

                    return;

                default:
                    break;

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
    private static final Logger LOGGER = Logger.getLogger(EVoucherController.class.getSimpleName());
    @EJB
    private FeedbackRequestsLocal feedbackService;

}
