/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.feedback.FeedbackRequestsLocal;
import ke.co.miles.kcep.mis.utilities.FeedbackDetails;
import ke.co.miles.kcep.mis.utilities.FeedbackTypeDetail;
import ke.co.miles.kcep.mis.utilities.PersonDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "FeedbackController", urlPatterns = {"/feedback",
    "/saveFeedback", "/saveSuccessStory", "/success_stories"})
@MultipartConfig
public class FeedbackController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession(false);
        String path = request.getServletPath();
        String destination = "";

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveSuccessStory");
                            destination = "/head_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/head_feedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/head_success_stories";
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
                            urlPaths.add("/saveSuccessStory");
                            destination = "/agro_dealer_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/agro_dealer_feedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/agro_dealer_success_stories";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveSuccessStory");
                            destination = "/county_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/county_feedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/county_success_stories";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveSuccessStory");
                            destination = "/sub_county_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/sub_county_feedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/sub_county_success_stories";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "equityPersonellSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveSuccessStory");
                            destination = "/equity_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/equityfeedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/equity_success_stories";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "kalroOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveSuccessStory");
                            destination = "/kalro_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/kalro_feedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/kalro_success_stories";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "agmarkOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveSuccessStory");
                            destination = "/agmark_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/agmark_feedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/agmark_success_stories";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveSuccessStory");
                            destination = "/region_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/region_feedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/regional_success_stories";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveSuccessStory");
                            destination = "/ward_success_stories";
                            if (path.equals("/feedback")) {
                                path = "/ward_feedback";
                                urlPaths.add(path);
                            } else if (path.equals("/success_stories")) {
                                path = "/ward_success_stories";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "warehouseOperatorSession":
                        if (rightsMaps.get(rightsMap)) {
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

        urlPaths.add("/saveFeedback");

        if (urlPaths.contains(path)) {

            PersonDetails user = (PersonDetails) session.getAttribute("person");

            switch (path) {

                case "/agro_dealer_feedback":
                case "/sub_county_feedback":

                    try {
                        session.setAttribute("feedbackList", feedbackService.retrieveSubCountyFeedback(FeedbackTypeDetail.FEEDBACK, user.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a sub-county", ex);
                        return;
                    }

                    try {
                        session.setAttribute("successStories", feedbackService.retrieveFeedback(FeedbackTypeDetail.SUCCESS_STORY));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a sub-county", ex);
                        return;
                    }

                    break;

                case "/county_feedback":

                    try {
                        session.setAttribute("feedbackList", feedbackService.retrieveCountyFeedback(FeedbackTypeDetail.FEEDBACK, user.getLocation().getCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a county", ex);
                        return;
                    }

                    try {
                        session.setAttribute("successStories", feedbackService.retrieveFeedback(FeedbackTypeDetail.SUCCESS_STORY));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a sub-county", ex);
                        return;
                    }

                    break;

                case "/head_feedback":
                case "/agmark_feedback":
                case "/kalro_feedback":

                    try {
                        session.setAttribute("feedbackList", feedbackService.retrieveFeedback(FeedbackTypeDetail.FEEDBACK));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback", ex);
                        return;
                    }

                    try {
                        session.setAttribute("successStories", feedbackService.retrieveFeedback(FeedbackTypeDetail.SUCCESS_STORY));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a sub-county", ex);
                        return;
                    }

                    break;

                case "/region_feedback":

                    try {
                        session.setAttribute("feedbackList", feedbackService.retrieveRegionFeedback(FeedbackTypeDetail.FEEDBACK, user.getLocation().getCounty().getRegion().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a region", ex);
                        return;
                    }

                    try {
                        session.setAttribute("successStories", feedbackService.retrieveFeedback(FeedbackTypeDetail.SUCCESS_STORY));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a sub-county", ex);
                        return;
                    }

                    break;

                case "/ward_feedback":
                case "/warehouse_feedback":

                    try {
                        session.setAttribute("feedbackList", feedbackService.retrieveWardFeedback(FeedbackTypeDetail.FEEDBACK, user.getLocation().getWard().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a region", ex);
                        return;
                    }

                    try {
                        session.setAttribute("successStories", feedbackService.retrieveFeedback(FeedbackTypeDetail.SUCCESS_STORY));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of feedback from a sub-county", ex);
                        return;
                    }

                    break;

                case "/saveFeedback":

                    FeedbackDetails feedback = new FeedbackDetails();
                    feedback.setMessage(request.getParameter("feedback"));
                    if (feedback.getMessage().equals("null")) {
                        feedback.setMessage(null);
                    }
                    feedback.setTimePosted(new Date());
                    feedback.setSubmitter((PersonDetails) session.getAttribute("person"));
                    feedback.setFeedbackType(FeedbackTypeDetail.FEEDBACK);

                    try {
                        feedbackService.addFeedback(feedback);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    try {
                        getServletContext().setAttribute("latestFeedbackList", feedbackService.retrieveLatestFeedback());
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of latest feedback records", e);
                        return;
                    }

                    return;

                case "/saveSuccessStory":

                    FeedbackDetails successStory = new FeedbackDetails();
                    successStory.setMessage(request.getParameter("success-story"));
                    if (successStory.getMessage().equals("null")) {
                        successStory.setMessage(null);
                    }
                    successStory.setTimePosted(new Date());
                    successStory.setSubmitter((PersonDetails) session.getAttribute("person"));
                    successStory.setFeedbackType(FeedbackTypeDetail.SUCCESS_STORY);

                    ServletContext context = getServletContext();
                    String realPath = context.getRealPath("/");
                    String filePath = realPath + "documents" + fileSeparator + "success_stories" + fileSeparator + "media_files";
                    Part filePart = request.getPart("media-file");
                    String fileName = getFileName(filePart);
                    FileOutputStream outStream;
                    InputStream inStream;

                    try {
                        filePath = filePath + fileSeparator + fileName;
                        new File(filePath).getParentFile().mkdirs();

                        outStream = new FileOutputStream(filePath);
                        inStream = filePart.getInputStream();

                        final int startOffset = 0;
                        final byte[] buffer = new byte[4096];
                        while (inStream.read(buffer) > 0) {
                            outStream.write(buffer, startOffset, buffer.length);
                        }

                        successStory.setAttachment(filePath);
                        outStream.close();

                    } catch (FileNotFoundException ex) {
                        successStory.setAttachment(null);
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
                    }

                    try {
                        feedbackService.addFeedback(successStory);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    try {
                        getServletContext().setAttribute("latestFeedbackList", feedbackService.retrieveLatestFeedback());
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of latest feedback records", e);
                        return;
                    }

                    path = destination;

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

//</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(EVoucherController.class
            .getSimpleName());
    @EJB
    private FeedbackRequestsLocal feedbackService;

}
