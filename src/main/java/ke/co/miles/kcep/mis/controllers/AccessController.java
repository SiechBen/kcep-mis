/*
 * To change this license header, choose License Headers in Programme Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
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
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.role.PersonRoleRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.RegionDetail;

/**
 *
 * @author siech
 */
@WebServlet(name = "AccessController", urlPatterns = {"/login", "/logout", "/index", "/loginpage", "/home", "/load"})
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

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession(false);

        String path = request.getServletPath();
        String destination;

        switch (path) {

            case "/loginpage":
                LOGGER.log(Level.SEVERE, "Session is {0}", session);

                boolean loggedIn;
                try {
                    loggedIn = (boolean) session.getAttribute("loggedIn");
                    if (loggedIn) {
                        path = (String) session.getAttribute("home");
                    }
                } catch (Exception e) {
                    break;
                }

                break;

            case "/index":
                path = "index.jsp";
                try {
                    request.getRequestDispatcher(path).forward(request, response);
                } catch (IOException | ServletException e) {
                    LOGGER.log(Level.SEVERE, "Request dispatch failed");
                }
                return;

            case "/login":

                /*if (session == null || ((new Date().getTime() - session.getLastAccessedTime()) > 1800000)) {*/
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Map<PersonDetails, PersonRoleDetail> personToPersonRoleMap;
                try {
                    personToPersonRoleMap = personService.retrievePerson(username, password);
                } catch (MilesException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(getBundle().getString(ex.getCode()));
                    LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
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
                    session = request.getSession(true);
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("person", person);
                    session.setAttribute("user", person.getName());
                    session.setAttribute("personRole", personRole);
                    MilesDebugger.debug(personRole.getPersonRole());

                    switch (personRole.getPersonRole()) {
                        case "Farmer":
                            rightsMaps.clear();
                            rightsMaps.put("farmerSession", true);
                            session.setAttribute("home", "/farmer");
                            session.setAttribute("userTitle", "Farmer");
                            break;

                        case "Agro-dealer":
                            rightsMaps.clear();
                            rightsMaps.put("agroDealerSession", true);
                            session.setAttribute("home", "/agro_dealer");
                            session.setAttribute("userTitle", "Agro-dealer");
                            session.setAttribute("locationLabel", "service");
                            try {
                                session.setAttribute("counties", countyService.retrieveCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", personRoleService.retrievePersonRolesNotAdminOrPcu());
                            } catch (Exception e) {
                            }
                            break;

                        case "WAO (Ward Extension Officer)":
                            rightsMaps.clear();
                            rightsMaps.put("waoSession", true);
                            session.setAttribute("home", "/ward");
                            session.setAttribute("userTitle", "WAO");
                            session.setAttribute("locationLabel", "ward");
                            try {
                                session.setAttribute("counties", countyService.retrieveCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards(person.getLocation().getSubCounty().getId()));
                            } catch (Exception ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", personRoleService.retrievePersonRolesNotAdminOrPcu());
                            } catch (Exception e) {
                            }
                            break;

                        case "KALRO Officer":
                            rightsMaps.clear();
                            rightsMaps.put("kalroSession", true);
                            session.setAttribute("home", "/kalro");
                            session.setAttribute("userTitle", "KALRO Officer");
                            session.setAttribute("locationLabel", "service");
                            try {
                                session.setAttribute("counties", countyService.retrieveCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", personRoleService.retrievePersonRolesNotAdminOrPcu());
                            } catch (Exception e) {
                            }
                            break;

                        case "AGMARK Officer":
                            rightsMaps.clear();
                            rightsMaps.put("agmarkSession", true);
                            session.setAttribute("home", "/agmark");
                            session.setAttribute("userTitle", "AGMARK Officer");
                            session.setAttribute("locationLabel", "service");
                            try {
                                session.setAttribute("counties", countyService.retrieveCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", personRoleService.retrievePersonRolesNotAdminOrPcu());
                            } catch (Exception e) {
                            }
                            break;

                        case "County Officer":
                            rightsMaps.clear();
                            rightsMaps.put("countyDeskOfficerSession", true);
                            session.setAttribute("home", "/county");
                            session.setAttribute("userTitle", "County Desk Officer");
                            session.setAttribute("locationLabel", "county");
                            try {
                                session.setAttribute("counties", countyService.retrieveCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties(person.getLocation().getCounty().getId()));
                            } catch (Exception ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", personRoleService.retrievePersonRolesNotAdminOrPcu());
                            } catch (Exception e) {
                            }
                            break;

                        case "Sub-county Officer":
                            rightsMaps.clear();
                            rightsMaps.put("subCountyDeskOfficerSession", true);
                            session.setAttribute("home", "/sub_county");
                            session.setAttribute("userTitle", "SCAO");
                            session.setAttribute("locationLabel", "sub-county");

                            try {
                                session.setAttribute("counties", countyService.retrieveCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties(person.getLocation().getCounty().getId()));
                            } catch (Exception ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", personRoleService.retrievePersonRolesNotAdminOrPcu());
                                MilesDebugger.debug(session.getAttribute("personRoles"));
                            } catch (Exception e) {
                            }
                            break;

                        case "Regional Coordinator":
                            rightsMaps.clear();
                            rightsMaps.put("regionalCoordinatorSession", true);
                            session.setAttribute("home", "/region");
                            session.setAttribute("userTitle", "Regional Programme Coordinator");
                            session.setAttribute("locationLabel", "region");
                            session.setAttribute("regions", RegionDetail.values());

                            try {
                                session.setAttribute("counties", countyService.retrieveCounties(person.getLocation().getCounty().getRegion().getId()));
                            } catch (Exception ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", personRoleService.retrievePersonRolesNotAdminOrPcu());
                            } catch (Exception e) {
                            }
                            break;

                        case "Senior Programme Coordinator":
                        case "Programme Coordinator":
                        case "System Admin":

                            rightsMaps.clear();
                            session.setAttribute("home", "/head");
                            session.setAttribute("regions", RegionDetail.values());

                            if (personRole.getPersonRole().equals("Programme Coordinator")) {
                                rightsMaps.put("nationalOfficerSession", true);
                                session.setAttribute("userTitle", "Programme Coordinator");
                                session.setAttribute("locationLabel", "country");
                            } else if (personRole.getPersonRole().equals("Senior Programme Coordinator")) {
                                rightsMaps.put("nationalOfficerSession", true);
                                session.setAttribute("userTitle", "Senior Programme Coordinator");
                                session.setAttribute("locationLabel", "country");
                            } else {
                                rightsMaps.put("systemAdminSession", true);
                                session.setAttribute("userTitle", "System Admin");
                                session.setAttribute("locationLabel", "country");
                            }

                            try {
                                session.setAttribute("counties", countyService.retrieveCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", PersonRoleDetail.values());
                            } catch (Exception e) {
                            }

                            break;

                        case "Equity":
                            rightsMaps.clear();
                            rightsMaps.put("equityPersonnelSession", true);
                            session.setAttribute("home", "/equity");
                            session.setAttribute("userTitle", "Equity Agent");
                            session.setAttribute("locationLabel", "service");
                            try {
                                session.setAttribute("counties", countyService.retrieveCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("subCounties", subCountyService.retrieveSubCounties());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during sub-counties retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("wards", wardService.retrieveWards());
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during wards retrieval", ex);
                                return;
                            }

                            try {
                                session.setAttribute("personRoles", personRoleService.retrievePersonRolesNotAdminOrPcu());
                            } catch (Exception e) {
                            }
                            break;

                        default:
                            rightsMaps.clear();
                    }

                }

                session.setAttribute("rightsMaps", rightsMaps);

                availApplicationAttributes();

                return;

            case "/home":
                LOGGER.log(Level.INFO, "Directing user to home");
                path = (String) session.getAttribute("home");
                LOGGER.log(Level.SEVERE, "Path is : {0}", path);

                break;

            case "/logout":
                if (session != null) {
                    session.invalidate();
                }

                path = "index.jsp";
                LOGGER.log(Level.SEVERE, "Path is : {0}", path);
                request.getRequestDispatcher(path).forward(request, response);

                return;

            case "/load":

                availApplicationAttributes();

                return;

        }

        availApplicationAttributes();
        destination = "/WEB-INF/views/pages" + path + ".jsp";

        LOGGER.log(Level.INFO, "Request dispatch to forward to: {0}", destination);
        try {
            request.getRequestDispatcher(destination).forward(request, response);
        } catch (ServletException | IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(getBundle().getString("redirection_failed") + "<br>");
            LOGGER.log(Level.INFO, getBundle().getString("redirection_failed"));
        }
    }

    private static final Logger LOGGER = Logger.getLogger(AccessController.class.getSimpleName());
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private CountyRequestsLocal countyService;
    @EJB
    private WardRequestsLocal wardService;
    @EJB
    private SubCountyRequestsLocal subCountyService;
    @EJB
    private PersonRoleRequestsLocal personRoleService;

}
