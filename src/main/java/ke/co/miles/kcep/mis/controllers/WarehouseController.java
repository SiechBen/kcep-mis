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
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.WarehouseRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseTypeDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "WarehouseController", urlPatterns = {"/warehouses", "/addWarehouse", "/doAddWarehouse"})
public class WarehouseController extends Controller {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        bundle = ResourceBundle.getBundle("text", locale);

        //Get the user session
        HttpSession session = request.getSession();

        //Get the user path
        String path = request.getServletPath();
        String destination;

        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                if (rightsMap.equals("systemAdminSession") || rightsMap.equals("nationalOfficerSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddWarehouse");
                        if (path.equals("/warehouses")) {
                            path = "/head_warehouses";
                            urlPaths.add(path);
                        } else if (path.equals("/addWarehouse")) {
                            path = "/head_addWarehouse";
                            urlPaths.add(path);
                        }
                    }
                } else if (rightsMap.equals("waoSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddWarehouse");
                        if (path.equals("/warehouses")) {
                            path = "/ward_warehouses";
                            urlPaths.add(path);
                        } else if (path.equals("/addWarehouse")) {
                            path = "/ward_addWarehouse";
                            urlPaths.add(path);
                        }
                    }
                } else if (rightsMap.equals("subCountyDeskOfficerSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddWarehouse");
                        if (path.equals("/warehouses")) {
                            path = "/sub_county_warehouses";
                            urlPaths.add(path);
                        } else if (path.equals("/addWarehouse")) {
                            path = "/sub_county_addWarehouse";
                            urlPaths.add(path);
                        }
                    }
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/ward_warehouses":
                case "/head_warehouses":
                case "/sub_county_warehouses":
                    
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
                    break;
                    
                case "/head_addWarehouse":
                case "/ward_addWarehouse":
                case "/sub_county_addWarehouse":

                    //Retrieve the list of people
                    List<PersonDetails> people;
                    try {
                        people = personService.retrievePeople();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    //Avail the people in the application scope
                    if (people != null) {
                        session.setAttribute("people", people);
                    }

                    break;

                case "/doAddWarehouse":

                    CountyDetails county = new CountyDetails();
                    try {
                        county.setId(Integer.valueOf(String.valueOf(request.getParameter("county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    WardDetails ward = new WardDetails();
                    try {
                        ward.setId(Integer.valueOf(String.valueOf(request.getParameter("ward"))));
                    } catch (Exception e) {
                        ward = null;
                    }

                    SubCountyDetails subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Integer.valueOf(String.valueOf(request.getParameter("subCounty"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

                    MeasurementUnitDetails measurementUnit = new MeasurementUnitDetails();
                    try {
                        measurementUnit.setId(Integer.valueOf(String.valueOf(request.getParameter("capacityUnits"))));
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

                    PersonDetails warehouseOperator = new PersonDetails();
                    try {
                        warehouseOperator.setId(Integer.valueOf(String.valueOf(request.getParameter("warehouseOperator"))));
                    } catch (Exception e) {
                        warehouseOperator = null;
                    }

                    WarehouseTypeDetails warehouseType = new WarehouseTypeDetails();
                    try {
                        warehouseType.setId(Integer.valueOf(String.valueOf(request.getParameter("warehouseType"))));
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
                    try {
                        System.out.println(warehouse.getWarehouseType().getType());
                    } catch (Exception e) {
                    }
                    warehouse.setUnits(measurementUnit);
                    warehouse.setLocation(location);

                    if (warehouse.getName().equals("null")) {
                        warehouse.setName(null);
                    }

                    try {
                        warehouseService.addWarehouse(warehouse);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(bundle.getString(e.getCode()));
                        LOGGER.log(Level.INFO, bundle.getString(e.getCode()));
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

    private static final Logger LOGGER = Logger.getLogger(WarehouseController.class.getSimpleName());
    @EJB
    private WarehouseRequestsLocal warehouseService;
    @EJB
    private PersonRequestsLocal personService;

}
