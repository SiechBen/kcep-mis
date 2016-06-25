/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.WarehouseRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "AccessController", urlPatterns = {"/login", "/logout", "/index", "/loginpage", "/home"})
public class AccessController extends Controller {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        //Get the user session
        HttpSession session = request.getSession(false);

        //Get the user path
        String path = request.getServletPath();
        String destination;

        switch (path) {

            case "/loginpage":
                LOGGER.log(Level.SEVERE, "Session is {0}", session);

                boolean loggedIn;
                try {
                    loggedIn = (boolean) session.getAttribute("loggedIn");
                } catch (Exception e) {
                    break;
                }

                if (loggedIn) {
                    path = (String) session.getAttribute("home");
                }

                break;

            case "/index":
                path = "index.jsp";
                try {
                    request.getRequestDispatcher(path).forward(request, response);
                } catch (IOException | ServletException e) {
                    LOGGER.log(Level.SEVERE, "Request dispatch failed", e);
                }
                return;

            case "/login":

                /*if (session == null || ((new Date().getTime() - session.getLastAccessedTime()) > 1800000)) {*/
                //Authentificate the user
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                //Retrieve the logged in user
                Map<PersonDetails, PersonRoleDetail> personToPersonRoleMap;
                try {
                    personToPersonRoleMap = personService.retrievePerson(username, password);
                } catch (MilesException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(getBundle().getString(ex.getCode()));
                    LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);

                    return;

                }

                PersonDetails person = null;
                PersonRoleDetail personRole = null;
                for (Map.Entry<PersonDetails, PersonRoleDetail> entryKey : personToPersonRoleMap.entrySet()) {
                    person = entryKey.getKey();
                    personRole = entryKey.getValue();
                    break;
                }

                HashMap<String, Boolean> rightsMaps = new HashMap<>();

                if (person != null && personRole != null) {
                    //Create a new session
                    session = request.getSession(true);

                    //Set the user
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("person", person);
                    session.setAttribute("user", person.getName());
                    session.setAttribute("personRole", personRole);

                    switch (personRole.getPersonRole()) {
                        case "Farmer":
                            rightsMaps.clear();
                            rightsMaps.put("farmerSession", true);
                            session.setAttribute("home", "/");
                            session.setAttribute("user", null);
                            session.setAttribute("person", null);
                            session.setAttribute("loggedIn", false);
                            session.setAttribute("personRole", null);
                            break;

                        case "Agro-dealer":
                            rightsMaps.clear();
                            rightsMaps.put("agroDealerSession", true);
                            session.setAttribute("home", "/agro_dealer");
                            break;

                        case "WAO (Ward Extension Officer)":
                            rightsMaps.clear();
                            rightsMaps.put("waoSession", true);
                            session.setAttribute("home", "/ward");
                            break;

                        case "KALRO Officer":
                            rightsMaps.clear();
                            rightsMaps.put("kalroSession", true);
                            session.setAttribute("home", "/kalro");
                            break;

                        case "County Officer":
                            rightsMaps.clear();
                            rightsMaps.put("countyDeskOfficerSession", true);
                            session.setAttribute("home", "/county");
                            break;

                        case "Sub-county Officer":
                            rightsMaps.clear();
                            rightsMaps.put("subCountyDeskOfficerSession", true);
                            session.setAttribute("home", "/sub_county");
                            break;

                        case "Regional Coordinator":
                            rightsMaps.clear();
                            rightsMaps.put("regionalCoordinatorSession", true);
                            session.setAttribute("home", "/region");
                            break;

                        case "National Officer":
                        case "System Admin":

                            rightsMaps.clear();
                            session.setAttribute("home", "/head");

                            if (personRole.getPersonRole().equals("National Officer")) {
                                rightsMaps.put("nationalOfficerSession", true);
                            } else {
                                rightsMaps.put("systemAdminSession", true);
                            }

                            break;

                        case "Equity":
                            rightsMaps.clear();
                            rightsMaps.put("equityPersonnelSession", true);
                            break;

                        case "Warehouse Operator":
                            rightsMaps.clear();

                            try {
                                WarehouseDetails warehouse = warehouseService.retrieveWarehouse(person.getId());
                                if (warehouse == null) {
                                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                    response.setContentType("text/html;charset=UTF-8");
                                    response.getWriter().write(getBundle().getString("error_008_04"));
                                    LOGGER.log(Level.INFO, getBundle().getString("error_008_04"));
                                    return;
                                }

                                session.setAttribute("warehouse", warehouse);

                            } catch (MilesException ex) {
                                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                response.setContentType("text/html;charset=UTF-8");
                                response.getWriter().write(getBundle().getString(ex.getCode()));
                                LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                                return;
                            }

                            rightsMaps.put("warehouseOperatorSession", true);

                            break;

                        default:
                            rightsMaps.clear();
                    }

                }

                session.setAttribute("rightsMaps", rightsMaps);

                //Retrieve the the application attributes and avail them in the application session
                availApplicationAttributes();

                return;

            case "/home":
                //Change path to appropriate homepage
                LOGGER.log(Level.INFO, "Directing user to home");
                path = (String) session.getAttribute("home");
                LOGGER.log(Level.SEVERE, "Path is : {0}", path);

                break;

            case "/logout":
                //Logout user by invalidating session
                session.setAttribute("subAdminSession", false);

                session.invalidate();

                //Change path to index page
                path = "index.jsp";
                LOGGER.log(Level.SEVERE, "Path is : {0}", path);
                //Redirect user to the page
                request.getRequestDispatcher(path).forward(request, response);
                return;
        }

        //Retrieve the the application attributes and avail them in the application session
        availApplicationAttributes();

        //Use request dispatcher to foward request internally
        destination = "/WEB-INF/views" + path + ".jsp";

        LOGGER.log(Level.INFO, "Request dispatch to forward to: {0}", destination);
        try {
            request.getRequestDispatcher(destination).forward(request, response);
        } catch (ServletException | IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(getBundle().getString("redirection_failed") + "<br>");
            LOGGER.log(Level.INFO, getBundle().getString("redirection_failed"), e);
        }
    }

    private static final Logger LOGGER = Logger.getLogger(AccessController.class.getSimpleName());
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    WarehouseRequestsLocal warehouseService;

}
