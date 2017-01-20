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

/**
 *
 * @author siech
 */
@WebServlet(name = "WarehouseController",
        urlPatterns = {
            "/warehouses", "/addWarehouse",
            "/doAddWarehouse", "/doEditWarehouse",
            "/doDeleteWarehouse", "/changeWarehouseCounter"})
public class WarehouseController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession();
        String path = request.getServletPath();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps
                = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddWarehouse");
                            urlPaths.add("/doEditWarehouse");
                            urlPaths.add("/doDeleteWarehouse");
                            urlPaths.add("/changeWarehouseCounter");
                            if (path.equals("/warehouses")) {
                                path = "/head_warehouses";
                                urlPaths.add(path);
                            } else if (path.equals("/addWarehouse")) {
                                path = "/head_addWarehouse";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddWarehouse");
                            urlPaths.add("/changeWarehouseCounter");
                            if (path.equals("/warehouses")) {
                                path = "/head_warehouses";
                                urlPaths.add(path);
                            } else if (path.equals("/addWarehouse")) {
                                path = "/head_addWarehouse";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddWarehouse");
                            urlPaths.add("/changeWarehouseCounter");
                            if (path.equals("/warehouses")) {
                                path = "/region_warehouses";
                                urlPaths.add(path);
                            } else if (path.equals("/addWarehouse")) {
                                path = "/region_addWarehouse";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "waoSession":
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
                        break;
                    case "subCountyDeskOfficerSession":
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
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddWarehouse");
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

                case "/changeWarehouseCounter":
                    HashMap<String, Integer> countMap;

                    try {
                        countMap = warehouseService.countCountyWarehouses(Short.valueOf(request.getParameter("counter")));

                        session.setAttribute("total", countMap.get("Total"));
                        session.setAttribute("certifiedWarehouses", countMap.get("cw"));
                        session.setAttribute("totalWarehouses", countMap.get("Total w"));
                        session.setAttribute("unCertifiedWarehouses", countMap.get("uw"));
                        session.setAttribute("warehousesOfferingWrs", countMap.get("wow"));
                        session.setAttribute("farmerOwnedWarehouses", countMap.get("fow"));
                        session.setAttribute("privatelyOwnedWarehouses", countMap.get("pow"));
                        session.setAttribute("warehousesNotOfferingWrs", countMap.get("wnow"));
                        session.setAttribute("certifiedCollectionCentres", countMap.get("ccc"));
                        session.setAttribute("totalCollectionCentres", countMap.get("Total cc"));
                        session.setAttribute("unCertifiedCollectionCentres", countMap.get("ucc"));
                        session.setAttribute("collectionCentresOfferingWrs", countMap.get("ccow"));
                        session.setAttribute("farmerOwnedCollectionCentres", countMap.get("focc"));
                        session.setAttribute("privatelyOwnedCollectionCentres", countMap.get("pocc"));
                        session.setAttribute("collectionCentresNotOfferingWrs", countMap.get("ccnow"));

                        out.write("<td>" + countMap.get("cw") + "</td>");
                        out.write("<td>" + countMap.get("uw") + "</td>");
                        out.write("<td>" + countMap.get("wow") + "</td>");
                        out.write("<td>" + countMap.get("wnow") + "</td>");
                        out.write("<td>" + countMap.get("fow") + "</td>");
                        out.write("<td>" + countMap.get("pow") + "</td>");
                        out.write("<td>" + countMap.get("Total w") + "</td>");
                        out.write("<td>" + countMap.get("ccc") + "</td>");
                        out.write("<td>" + countMap.get("ucc") + "</td>");
                        out.write("<td>" + countMap.get("ccow") + "</td>");
                        out.write("<td>" + countMap.get("ccnow") + "</td>");
                        out.write("<td>" + countMap.get("focc") + "</td>");
                        out.write("<td>" + countMap.get("pocc") + "</td>");
                        out.write("<td>" + countMap.get("Total cc") + "</td>");
                        out.write("<td>" + countMap.get("Total") + "</td>");

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    return;

                case "/head_warehouses":
                case "/region_warehouses":

                    try {
                        countMap = warehouseService.countAllWarehouses();

                        session.setAttribute("total", countMap.get("Total"));
                        session.setAttribute("certifiedWarehouses", countMap.get("cw"));
                        session.setAttribute("totalWarehouses", countMap.get("Total w"));
                        session.setAttribute("unCertifiedWarehouses", countMap.get("uw"));
                        session.setAttribute("warehousesOfferingWrs", countMap.get("wow"));
                        session.setAttribute("farmerOwnedWarehouses", countMap.get("fow"));
                        session.setAttribute("privatelyOwnedWarehouses", countMap.get("pow"));
                        session.setAttribute("warehousesNotOfferingWrs", countMap.get("wnow"));
                        session.setAttribute("certifiedCollectionCentres", countMap.get("ccc"));
                        session.setAttribute("totalCollectionCentres", countMap.get("Total cc"));
                        session.setAttribute("unCertifiedCollectionCentres", countMap.get("ucc"));
                        session.setAttribute("collectionCentresOfferingWrs", countMap.get("ccow"));
                        session.setAttribute("farmerOwnedCollectionCentres", countMap.get("focc"));
                        session.setAttribute("privatelyOwnedCollectionCentres", countMap.get("pocc"));
                        session.setAttribute("collectionCentresNotOfferingWrs", countMap.get("ccnow"));

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    try {
                        session.setAttribute("warehouseOperators", phenomenonService.retrieveWarehouseOperators());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("warehouses", warehouseService.retrieveWarehouses());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during warehouses retrieval", ex);
                        return;
                    }

                    try {
                        session.setAttribute("people", personService.retrievePeople());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }
                    break;

                case "/ward_warehouses":

                    try {
                        session.setAttribute("warehouseOperators", phenomenonService.retrieveWarehouseOperators());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    PersonDetails waoOfficer = (PersonDetails) session.getAttribute("person");
                    try {
                        session.setAttribute("warehouses", warehouseService.retrieveWardWarehouses(waoOfficer.getLocation().getWard().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during warehouses retrieval", ex);
                        return;
                    }

                    try {
                        session.setAttribute("people", personService.retrieveWardPeople(waoOfficer.getLocation().getWard().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }
                    break;

                case "/county_warehouses":

                    try {
                        session.setAttribute("warehouseOperators", phenomenonService.retrieveWarehouseOperators());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    PersonDetails countyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        session.setAttribute("warehouses", warehouseService.retrieveCountyWarehouses(countyDeskOfficer.getLocation().getCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during warehouses retrieval", ex);
                        return;
                    }

                    List<SubCountyDetails> subCounties;
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
                                session.setAttribute("wards", wardService.retrieveWards(subCounty.getId()));
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                                return;
                            }
                        }
                    }

                    try {
                        session.setAttribute("people", personService.retrieveCountyPeople(countyDeskOfficer.getLocation().getCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }
                    break;

                case "/sub_county_warehouses":

                    try {
                        session.setAttribute("warehouseOperators", phenomenonService.retrieveWarehouseOperators());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    PersonDetails subCountyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        session.setAttribute("warehouses", warehouseService.retrieveSubCountyWarehouses(subCountyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during warehouses retrieval", ex);
                        return;
                    }

                    try {
                        session.setAttribute("wards", wardService.retrieveWards(subCountyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                        return;
                    }

                    try {
                        session.setAttribute("people", personService.retrieveSubCountyPeople(subCountyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/doAddWarehouse":

                    WarehouseDetails warehouse = new WarehouseDetails();
                    LocationDetails location = new LocationDetails();

                    try {
                        location.setCounty(new CountyDetails(Short.valueOf(
                                String.valueOf(request.getParameter("county")))));
                    } catch (Exception e) {
                        location.setCounty(null);
                    }
                    try {
                        location.setSubCounty(new SubCountyDetails(Short.valueOf(
                                String.valueOf(request.getParameter("subCounty")))));
                    } catch (Exception e) {
                        location.setSubCounty(null);
                    }
                    try {
                        location.setWard(new WardDetails(Short.valueOf(
                                String.valueOf(request.getParameter("ward")))));
                    } catch (Exception e) {
                        location.setWard(null);
                    }

                    try {
                        warehouse.setUnits(new MeasurementUnitDetails(
                                Short.valueOf(String.valueOf(request.getParameter("capacityUnits")))));
                    } catch (Exception e) {
                    }

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
                        warehouse.setWarehouseOperator(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("warehouseOperator"))));
                    } catch (Exception e) {
                        warehouse.setWarehouseOperator(null);
                    }

                    try {
                        warehouse.setWarehouseType(new PhenomenonDetails(
                                Integer.valueOf(String.valueOf(request.getParameter("warehouseType")))));
                    } catch (Exception e) {
                        warehouse.setWarehouseType(null);
                    }

                    try {
                        warehouse.setCapacity(Integer.valueOf(String.valueOf(request.getParameter("capacity"))));
                    } catch (Exception e) {
                        warehouse.setCapacity(null);
                    }

                    warehouse.setCertified(Boolean.valueOf(String.valueOf(request.getParameter("certified"))));
                    warehouse.setOffersWrs(Boolean.valueOf(String.valueOf(request.getParameter("offersWrs"))));
                    warehouse.setName(String.valueOf(request.getParameter("name")));
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

                case "/doEditWarehouse":

                    try {
                        warehouse = new WarehouseDetails(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                        warehouse = new WarehouseDetails();
                    }
                    try {
                        location = new LocationDetails(Integer.valueOf(request.getParameter("location")));
                    } catch (Exception e) {
                        location = new LocationDetails();
                    }

                    try {
                        location.setCounty(new CountyDetails(Short.valueOf(
                                String.valueOf(request.getParameter("county")))));
                    } catch (Exception e) {
                        location.setCounty(null);
                    }
                    try {
                        location.setSubCounty(new SubCountyDetails(Short.valueOf(
                                String.valueOf(request.getParameter("subCounty")))));
                    } catch (Exception e) {
                        location.setSubCounty(null);
                    }
                    try {
                        location.setWard(new WardDetails(Short.valueOf(
                                String.valueOf(request.getParameter("ward")))));
                    } catch (Exception e) {
                        location.setWard(null);
                    }

                    try {
                        warehouse.setUnits(new MeasurementUnitDetails(
                                Short.valueOf(String.valueOf(request.getParameter("capacityUnits")))));
                    } catch (Exception e) {
                    }

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
                        warehouse.setWarehouseOperator(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("warehouseOperator"))));
                    } catch (Exception e) {
                        warehouse.setWarehouseOperator(null);
                    }

                    try {
                        warehouse.setWarehouseType(new PhenomenonDetails(
                                Integer.valueOf(String.valueOf(request.getParameter("warehouseType")))));
                    } catch (Exception e) {
                        warehouse.setWarehouseType(null);
                    }

                    try {
                        warehouse.setCapacity(Integer.valueOf(String.valueOf(request.getParameter("capacity"))));
                    } catch (Exception e) {
                        warehouse.setCapacity(null);
                    }

                    warehouse.setCertified(Boolean.valueOf(String.valueOf(request.getParameter("certified"))));
                    warehouse.setOffersWrs(Boolean.valueOf(String.valueOf(request.getParameter("offersWrs"))));
                    warehouse.setName(String.valueOf(request.getParameter("name")));
                    warehouse.setLocation(location);

                    if (warehouse.getName().equals("null")) {
                        warehouse.setName(null);
                    }

                    try {
                        warehouseService.editWarehouse(warehouse);
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
            destination = "/WEB-INF/views/pages" + path + ".jsp";

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
