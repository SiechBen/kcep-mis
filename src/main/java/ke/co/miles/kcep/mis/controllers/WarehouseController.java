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
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.WarehouseRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseTypeDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "WarehouseController", urlPatterns = {"/warehouses", "/addWarehouse", "/doAddWarehouse", "/doEditWarehouse", "/doDeleteWarehouse"})
public class WarehouseController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddWarehouse");
                            urlPaths.add("/doEditWarehouse");
                            urlPaths.add("/doDeleteWarehouse");
                            if (path.equals("/warehouses")) {
                                path = "/head_warehouses";
                                urlPaths.add(path);
                            } else if (path.equals("/addWarehouse")) {
                                path = "/head_addWarehouse";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddWarehouse");
                            urlPaths.add("/doEditWarehouse");
                            urlPaths.add("/doDeleteWarehouse");
                            if (path.equals("/warehouses")) {
                                path = "/ward_warehouses";
                                urlPaths.add(path);
                            } else if (path.equals("/addWarehouse")) {
                                path = "/ward_addWarehouse";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddWarehouse");
                            urlPaths.add("/doEditWarehouse");
                            urlPaths.add("/doDeleteWarehouse");
                            if (path.equals("/warehouses")) {
                                path = "/sub_county_warehouses";
                                urlPaths.add(path);
                            } else if (path.equals("/addWarehouse")) {
                                path = "/sub_county_addWarehouse";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddWarehouse");
                            urlPaths.add("/doEditWarehouse");
                            urlPaths.add("/doDeleteWarehouse");
                            if (path.equals("/warehouses")) {
                                path = "/county_warehouses";
                                urlPaths.add(path);
                            } else if (path.equals("/addWarehouse")) {
                                path = "/county_addWarehouse";
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

            availApplicationAttributes();

            switch (path) {

                case "/head_warehouses":

                    //Retrieve the list of warehouses
                    List<WarehouseDetails> warehouses;
                    try {
                        warehouses = warehouseService.retrieveWarehouses();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during warehouses retrieval", ex);
                        return;
                    }

                    //Avail the warehouses in the application scope
                    if (warehouses != null) {
                        session.setAttribute("warehouses", warehouses);
                    }

                    List<PersonDetails> people;
                    try {
                        people = personService.retrievePeople();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }

                    PersonDetails nationalOfficer = (PersonDetails) session.getAttribute("person");

                    List<SubCountyDetails> subCounties;
                    try {
                        subCounties = subCountyService.retrieveSubCounties(nationalOfficer.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of sub-counties", ex);
                        return;
                    }

                    List<WardDetails> wards = new ArrayList<>();
                    if (subCounties != null) {

                        for (SubCountyDetails subCounty : subCounties) {
                            try {
                                wards.addAll(wardService.retrieveWards(subCounty.getId()));
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                                return;
                            }
                        }
                    }

                    session.setAttribute("subCounties", subCounties);
                    session.setAttribute("wards", wards);
                    break;

                case "/ward_warehouses":

                    PersonDetails waoOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        warehouses = warehouseService.retrieveWardWarehouses(waoOfficer.getLocation().getWard().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during warehouses retrieval", ex);
                        return;
                    }

                    //Avail the warehouses in the application scope
                    if (warehouses != null) {
                        session.setAttribute("warehouses", warehouses);
                    }

                    try {
                        people = personService.retrieveWardPeople(waoOfficer.getLocation().getWard().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/county_warehouses":

                    PersonDetails countyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        warehouses = warehouseService.retrieveCountyWarehouses(countyDeskOfficer.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during warehouses retrieval", ex);
                        return;
                    }

                    if (warehouses != null) {
                        session.setAttribute("warehouses", warehouses);
                    }

                    try {
                        subCounties = subCountyService.retrieveSubCounties(countyDeskOfficer.getLocation().getCounty().getId());
                        session.setAttribute("subCounties", subCounties);
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of sub-counties", ex);
                        return;
                    }

                    if (subCounties != null) {

                        for (SubCountyDetails subCounty : subCounties) {
                            try {
                                wards = new ArrayList<>();
                                session.setAttribute("wards", wardService.retrieveWards(subCounty.getId()));
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                                return;
                            }
                        }
                    }

                    try {
                        people = personService.retrieveCountyPeople(countyDeskOfficer.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/sub_county_warehouses":

                    PersonDetails subCountyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        warehouses = warehouseService.retrieveSubCountyWarehouses(subCountyDeskOfficer.getLocation().getSubCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during warehouses retrieval", ex);
                        return;
                    }

                    if (warehouses != null) {
                        session.setAttribute("warehouses", warehouses);
                    }

                    try {
                        wards = wardService.retrieveWards(subCountyDeskOfficer.getLocation().getSubCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                        return;
                    }

                    if (wards != null) {
                        session.setAttribute("wards", wards);
                    }

                    try {
                        people = personService.retrieveSubCountyPeople(subCountyDeskOfficer.getLocation().getSubCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/head_addWarehouse":
                case "/ward_addWarehouse":
                case "/county_addWarehouse":
                case "/sub_county_addWarehouse":

                    List<PhenomenonDetails> warehouseOperators;
                    try {
                        warehouseOperators = phenomenonService.retrieveWarehouseOperators();
                        session.setAttribute("warehouseOperators", warehouseOperators);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    break;

                case "/doAddWarehouse":

                    CountyDetails county = new CountyDetails();
                    try {
                        county.setId(Short.valueOf(String.valueOf(request.getParameter("county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    WardDetails ward = new WardDetails();
                    try {
                        ward.setId(Short.valueOf(String.valueOf(request.getParameter("ward"))));
                    } catch (Exception e) {
                        ward = null;
                    }

                    SubCountyDetails subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Short.valueOf(String.valueOf(request.getParameter("subCounty"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

                    MeasurementUnitDetails measurementUnit = new MeasurementUnitDetails();
                    try {
                        measurementUnit.setId(Short.valueOf(String.valueOf(request.getParameter("capacityUnits"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    LocationDetails location = new LocationDetails();
                    location.setCounty(county);
                    location.setWard(ward);
                    location.setSubCounty(subCounty);
                    try {
                        location.setLatitude(new BigDecimal(String.valueOf(request.getParameter("latitude"))));
                        try {
                            location.setLongitude(new BigDecimal(String.valueOf(request.getParameter("longitude"))));
                        } catch (Exception e) {
                            location.setLatitude(null);
                            location.setLongitude(null);
                        }
                    } catch (Exception e) {
                        location.setLatitude(null);
                        location.setLongitude(null);
                    }

                    PhenomenonDetails warehouseOperator;
                    try {
                        warehouseOperator = new PhenomenonDetails(Integer.valueOf(request.getParameter("warehouseOperator")));
                    } catch (Exception e) {
                        warehouseOperator = null;
                    }

                    WarehouseTypeDetails warehouseType = new WarehouseTypeDetails();
                    try {
                        warehouseType.setId(Short.valueOf(String.valueOf(request.getParameter("warehouseType"))));
                    } catch (Exception e) {
                        warehouseType = null;
                    }

                    WarehouseDetails warehouse = new WarehouseDetails();
                    try {
                        warehouse.setCapacity(Integer.valueOf(String.valueOf(request.getParameter("capacity"))));
                    } catch (Exception e) {
                        warehouse.setCapacity(null);
                    }

                    warehouse.setCertified(Boolean.valueOf(String.valueOf(request.getParameter("certified"))));
                    warehouse.setOffersWrs(Boolean.valueOf(String.valueOf(request.getParameter("offersWrs"))));
                    warehouse.setName(String.valueOf(request.getParameter("name")));
                    warehouse.setWarehouseOperator(warehouseOperator);
                    warehouse.setWarehouseType(warehouseType);
                    warehouse.setUnits(measurementUnit);
                    warehouse.setLocation(location);

                    if (warehouse.getName().equals("null")) {
                        warehouse.setName(null);
                    }

                    try {
                        warehouseService.addWarehouse(warehouse);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }
                    break;

                case "/doEditWarehouse":

                    // CountyDetails county = new CountyDetails();
                    county = new CountyDetails();
                    try {
                        county.setId(Short.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }
                    try {
                        county.setId(Short.valueOf(String.valueOf(request.getParameter("county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    ward = new WardDetails();
                    try {
                        ward.setId(Short.valueOf(String.valueOf(request.getParameter("ward"))));
                    } catch (Exception e) {
                        ward = null;
                    }

                    subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Short.valueOf(String.valueOf(request.getParameter("subCounty"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

                    measurementUnit = new MeasurementUnitDetails();
                    try {
                        measurementUnit.setId(Short.valueOf(String.valueOf(request.getParameter("capacityUnits"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    location = new LocationDetails();
                    location.setCounty(county);
                    location.setWard(ward);
                    location.setSubCounty(subCounty);
                    try {
                        location.setLatitude(new BigDecimal(String.valueOf(request.getParameter("latitude"))));
                        try {
                            location.setLongitude(new BigDecimal(String.valueOf(request.getParameter("longitude"))));
                        } catch (Exception e) {
                            location.setLatitude(null);
                            location.setLongitude(null);
                        }
                    } catch (Exception e) {
                        location.setLatitude(null);
                        location.setLongitude(null);
                    }

                    try {
                        warehouseOperator = new PhenomenonDetails(Integer.valueOf(request.getParameter("warehouseOperator")));
                    } catch (Exception e) {
                        warehouseOperator = null;
                    }

                    warehouseType = new WarehouseTypeDetails();
                    try {
                        warehouseType.setId(Short.valueOf(String.valueOf(request.getParameter("warehouseType"))));
                    } catch (Exception e) {
                        warehouseType = null;
                    }

                    warehouse = new WarehouseDetails();
                    try {
                        warehouse.setCapacity(Integer.valueOf(String.valueOf(request.getParameter("capacity"))));
                    } catch (Exception e) {
                        warehouse.setCapacity(null);
                    }

                    warehouse.setCertified(Boolean.valueOf(String.valueOf(request.getParameter("certified"))));
                    warehouse.setOffersWrs(Boolean.valueOf(String.valueOf(request.getParameter("offersWrs"))));
                    warehouse.setName(String.valueOf(request.getParameter("name")));
                    warehouse.setWarehouseOperator(warehouseOperator);
                    warehouse.setWarehouseType(warehouseType);
                    warehouse.setUnits(measurementUnit);
                    warehouse.setLocation(location);

                    if (warehouse.getName().equals("null")) {
                        warehouse.setName(null);
                    }

                    try {
                        warehouseService.addWarehouse(warehouse);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/doDeleteWarehouse":
                    try {
                        warehouseService.removeWarehouse(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
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

    private static final Logger LOGGER = Logger.getLogger(WarehouseController.class.getSimpleName());
    @EJB
    private WarehouseRequestsLocal warehouseService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private WardRequestsLocal wardService;
    @EJB
    private SubCountyRequestsLocal subCountyService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;

}
