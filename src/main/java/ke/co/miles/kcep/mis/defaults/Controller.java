/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.defaults;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.farmer.group.FarmerGroupRequestsLocal;
import ke.co.miles.kcep.mis.requests.feedback.FeedbackRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.FeedbackDetails;
import ke.co.miles.kcep.mis.utilities.FeedbackTypeDetail;
import ke.co.miles.kcep.mis.utilities.SexDetail;

/**
 *
 * @author siech
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public abstract class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    {
        setBundle(ResourceBundle.getBundle("text"));
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    abstract protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Avail application attributes">
    protected void availApplicationAttributes() {

        List<FeedbackDetails> feedback = new ArrayList<>();

        try {
            feedback.addAll(feedbackService.retrieveFeedback(FeedbackTypeDetail.FEEDBACK));
            feedback.addAll(feedbackService.retrieveFeedback(FeedbackTypeDetail.SUCCESS_STORY));
            getServletContext().setAttribute("feedbackList", feedback);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during feedback records retrieval", e);
            return;
        }

        try {
            getServletContext().setAttribute("latestFeedbackList", feedbackService.retrieveLatestFeedback());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during retrieval of latest feedback records", e);
            return;
        }

        try {
            getServletContext().setAttribute("warehouseTypes", phenomenonService.retrieveWarehouseTypes());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during retrieval of warehouse types", e);
            return;
        }

        try {
            getServletContext().setAttribute("farmerGroups", farmerGroupService.retrieveFarmerGroups());
        } catch (MilesException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred during farmer groups retrieval", ex);
            return;
        }

        try {
            getServletContext().setAttribute("populationInfo", bundle.getString("provide_excel"));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "An error occurred when availing population information", ex);
            return;
        }

        getServletContext().setAttribute("sexes", Arrays.asList(SexDetail.values()));

        try {
            getServletContext().setAttribute("measurementUnits", measurementUnitService.retrieveMeasurementUnits());
        } catch (MilesException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred during measurement units retrieval", ex);
        }

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get file name">
    protected String getFileName(Part filePart) {
        final String partHeader = filePart.getHeader("content-disposition");
//        final String partHeader = filePart.getHeader("Content-Disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    /**
     * @return the bundle
     */
    public ResourceBundle getBundle() {
        return bundle;
    }

    /**
     * @param bundle the bundle to set
     */
    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getSimpleName());
    private ResourceBundle bundle;
    @EJB
    private FeedbackRequestsLocal feedbackService;
    @EJB
    private FarmerGroupRequestsLocal farmerGroupService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;

    protected final DateFormat userDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected final DateFormat databaseDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    protected Date date;
    protected final String fileSeparator = File.separator;

}
