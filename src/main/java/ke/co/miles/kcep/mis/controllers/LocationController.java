package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "LocationController", urlPatterns = {"/updateCounties", "/updateSubCounties", "/updateWards"})
public class LocationController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        String path = request.getServletPath();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            urlPaths.add("/updateSubCounties");
            urlPaths.add("/updateCounties");
            urlPaths.add("/updateWards");
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/updateSubCounties":

                    try {
                        for (SubCountyDetails subCounty : subCountyService.retrieveSubCounties(Short.valueOf(request.getParameter("countyId")))) {
                            out.write("<option value=\"" + subCounty.getId() + "\">" + subCounty.getName() + "</option>");
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    return;

                case "/updateCounties":

                    try {
                        for (CountyDetails county : countyService.retrieveCounties(Short.valueOf(request.getParameter("regionId")))) {
                            out.write("<option value=\"" + county.getId() + "\">" + county.getName() + "</option>");
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    return;

                case "/updateWards":

                    try {
                        for (WardDetails ward : wardService.retrieveWards(Short.valueOf(request.getParameter("subCountyId")))) {
                            out.write("<option value=\"" + ward.getId() + "\">" + ward.getName() + "</option>");
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    return;
            }
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

    private static final Logger LOGGER = Logger.getLogger(LocationController.class.getSimpleName());

    @EJB
    private SubCountyRequestsLocal subCountyService;
    @EJB
    private CountyRequestsLocal countyService;
    @EJB
    private WardRequestsLocal wardService;
}
