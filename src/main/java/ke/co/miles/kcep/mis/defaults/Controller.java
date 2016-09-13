/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.defaults;

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
import ke.co.miles.kcep.mis.requests.farmer.feedback.FeedbackRequestsLocal;
import ke.co.miles.kcep.mis.requests.farmer.group.FarmerGroupRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.role.PersonRoleRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.topic.TopicRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.type.WarehouseTypeRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.FarmerGroupDetails;
import ke.co.miles.kcep.mis.utilities.FeedbackDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SexDetail;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseTypeDetails;

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

        List<PerformanceIndicatorDetails> performanceIndicators;
        try {
            performanceIndicators = performanceIndicatorService.retrievePerformanceIndicators();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during retrieval of topics", e);
            return;
        }
        if (performanceIndicators != null) {
            getServletContext().setAttribute("performanceIndicators", performanceIndicators);
        }

        try {
            getServletContext().setAttribute("trainingModules", topicService.retrieveTrainingModules());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during retrieval of training modules", e);
            return;
        }

        List<FeedbackDetails> feedbackList;
        try {
            feedbackList = feedbackService.retrieveFeedback();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during feedback records retrieval", e);
            return;
        }
        if (feedbackList != null) {
            getServletContext().setAttribute("feedbackList", feedbackList);
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

        List<WarehouseTypeDetails> warehouseTypes;
        try {
            warehouseTypes = warehouseTypeService.retrieveWarehouseTypes();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", e);
            return;
        }
        if (warehouseTypes != null) {
            getServletContext().setAttribute("warehouseTypes", warehouseTypes);
        }

        //Retrieve the list of counties
        List<CountyDetails> counties;
        try {
            counties = countyService.retrieveCounties();
        } catch (MilesException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
            return;
        }

        //Avail the counties in the application scope
        if (counties != null) {
            getServletContext().setAttribute("counties", counties);
        }

        //Retrieve the list of sub counties
        List<SubCountyDetails> subCounties;
        try {
            subCounties = subCountyService.retrieveSubCounties();
        } catch (MilesException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
            return;
        }

        //Avail the sub-counties in the application scope
        if (subCounties != null) {
            getServletContext().setAttribute("subCounties", subCounties);
        }

        //Retrieve the list of wards
        List<WardDetails> wards;
        try {
            wards = wardService.retrieveWards();
        } catch (MilesException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
            return;
        }

        //Avail the wards in the application scope
        if (wards != null) {
            getServletContext().setAttribute("wards", wards);
        }

        //Retrieve the list of person roles
        List<PersonRoleDetail> personRoles;
        try {
            personRoles = personRoleService.retrievePersonRoles();
        } catch (MilesException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred during person roles retrieval", ex);
            return;
        }

        //Avail the counties in the application scope
        if (personRoles != null) {
            getServletContext().setAttribute("personRoles", personRoles);
        }

        //Retrieve the list of farmer groups
        List<FarmerGroupDetails> farmerGroups;
        try {
            farmerGroups = farmerGroupService.retrieveFarmerGroups();
        } catch (MilesException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred during farmer groups retrieval", ex);
            return;
        }

        //Avail the counties in the application scope
        if (farmerGroups != null) {
            getServletContext().setAttribute("farmerGroups", farmerGroups);
        }

        //Retrieve the list of sex values
        List<SexDetail> sexValues = new ArrayList<>();
        sexValues.addAll(Arrays.asList(SexDetail.values()));

        //Avail the sex values in the application scope
        getServletContext().setAttribute("sexes", sexValues);

        //Retrieve the list of measurement units
        List<MeasurementUnitDetails> measurementUnits;
        try {
            measurementUnits = measurementUnitService.retrieveMeasurementUnits();
        } catch (MilesException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred during measurement units retrieval", ex);
            return;
        }

        //Avail the measurement units in the application scope
        getServletContext().setAttribute("measurementUnits", measurementUnits);

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
    private TopicRequestsLocal topicService;
    @EJB
    private FeedbackRequestsLocal feedbackService;
    @EJB
    private FarmerGroupRequestsLocal farmerGroupService;
    @EJB
    private PersonRoleRequestsLocal personRoleService;
    @EJB
    private CountyRequestsLocal countyService;
    @EJB
    private WardRequestsLocal wardService;
    @EJB
    private SubCountyRequestsLocal subCountyService;
    @EJB
    private WarehouseTypeRequestsLocal warehouseTypeService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;

    protected DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    protected DateFormat databaseDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    protected Date date;
}
