/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
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
import ke.co.miles.kcep.mis.requests.input.staticinput.StaticInputRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.equipment.EquipmentRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.operation.WarehouseOperationRequestsLocal;
import ke.co.miles.kcep.mis.utilities.EquipmentDetails;
import ke.co.miles.kcep.mis.utilities.StaticInputDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseOperationDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "EquipmentController", urlPatterns = {"/equipment",
    "/doEditEquipment", "/doEditWarehouseOperation", "/addEquipment",
    "/addWarehouseOperation", "/doAddEquipment", "/doAddWarehouseOperation",
    "/doDeleteEquipment", "/doDeleteWarehouseOperation"})
public class EquipmentController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession();
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
                            urlPaths.add("/doAddEquipment");
                            urlPaths.add("/doAddWarehouseOperation");
                            urlPaths.add("/doEditEquipment");
                            urlPaths.add("/doEditWarehouseOperation");
                            urlPaths.add("/doDeleteEquipment");
                            urlPaths.add("/doDeleteWarehouseOperation");
                            if (path.equals("/equipment")) {
                                path = "/head_equipment";
                                urlPaths.add(path);
                            } else if (path.equals("/addEquipment")) {
                                path = "/head_addEquipment";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddEquipment");
                            urlPaths.add("/doAddWarehouseOperation");
                            urlPaths.add("/doEditEquipment");
                            urlPaths.add("/doEditWarehouseOperation");
                            urlPaths.add("/doDeleteEquipment");
                            urlPaths.add("/doDeleteWarehouseOperation");
                            if (path.equals("/equipment")) {
                                path = "/ward_equipment";
                                urlPaths.add(path);
                            } else if (path.equals("/addEquipment")) {
                                path = "/ward_addEquipment";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddEquipment");
                            urlPaths.add("/doAddWarehouseOperation");
                            urlPaths.add("/doEditEquipment");
                            urlPaths.add("/doEditWarehouseOperation");
                            urlPaths.add("/doDeleteEquipment");
                            urlPaths.add("/doDeleteWarehouseOperation");
                            if (path.equals("/equipment")) {
                                path = "/sub_county_equipment";
                                urlPaths.add(path);
                            } else if (path.equals("/addEquipment")) {
                                path = "/sub_county_addEquipment";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddEquipment");
                            urlPaths.add("/doAddWarehouseOperation");
                            urlPaths.add("/doEditEquipment");
                            urlPaths.add("/doEditWarehouseOperation");
                            urlPaths.add("/doDeleteEquipment");
                            urlPaths.add("/doDeleteWarehouseOperation");
                            if (path.equals("/equipment")) {
                                path = "/county_equipment";
                                urlPaths.add(path);
                            } else if (path.equals("/addEquipment")) {
                                path = "/county_addEquipment";
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

            WarehouseDetails warehouse;
            try {
                warehouse = (WarehouseDetails) session.getAttribute("warehouse");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(getBundle().getString("error_008_05"));
                LOGGER.log(Level.INFO, getBundle().getString("error_008_05"), e);
                return;
            }

            switch (path) {

                case "/head_equipment":
                case "/ward_equipment":
                case "/warehouse_equipment":

                    try {
                        int warehouseId = Integer.valueOf(request.getParameter("warehouseId"));
                        session.setAttribute("warehouse", new WarehouseDetails(warehouseId));
                        session.setAttribute("produceTypes", staticInputService.retrieveProduceTypes());
                        session.setAttribute("equipment", equipmentService.retrieveEquipmentList(warehouseId));
                        session.setAttribute("warehouseOperations", warehouseOperationService.retrieveWarehouseOperations(warehouseId));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    } catch (NumberFormatException ex) {
                    }

                    break;

                case "/doAddEquipment":

                    EquipmentDetails newEquipment = new EquipmentDetails();
                    newEquipment.setWarehouse(warehouse);
                    newEquipment.setStatus(request.getParameter("equipmentStatus"));
                    newEquipment.setType(request.getParameter("equipmentType"));
                    newEquipment.setSerialNumber(request.getParameter("serialNumber"));
                    if (newEquipment.getSerialNumber().equals("null")) {
                        newEquipment.setSerialNumber(null);
                    }
                    if (newEquipment.getStatus().equals("null")) {
                        newEquipment.setStatus(null);
                    }
                    if (newEquipment.getType().equals("null")) {
                        newEquipment.setType(null);
                    }
                    try {
                        newEquipment.setTotalCount(Integer.valueOf(request.getParameter("equipmentTotalCount")));
                    } catch (Exception e) {
                    }

                    try {
                        equipmentService.addEquipment(newEquipment);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/doEditEquipment":

                    newEquipment = new EquipmentDetails();
                    try {
                        newEquipment.setId(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }
                    newEquipment.setWarehouse(warehouse);
                    newEquipment.setStatus(request.getParameter("equipmentStatus"));
                    newEquipment.setType(request.getParameter("equipmentType"));
                    newEquipment.setSerialNumber(request.getParameter("serialNumber"));
                    if (newEquipment.getSerialNumber().equals("null")) {
                        newEquipment.setSerialNumber(null);
                    }
                    if (newEquipment.getStatus().equals("null")) {
                        newEquipment.setStatus(null);
                    }
                    if (newEquipment.getType().equals("null")) {
                        newEquipment.setType(null);
                    }
                    try {
                        newEquipment.setTotalCount(Integer.valueOf(request.getParameter("equipmentTotalCount")));
                    } catch (Exception e) {
                    }

                    try {
                        equipmentService.editEquipment(newEquipment);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/doDeleteEquipment":
                    try {
                        equipmentService.removeEquipment(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                    }

                    return;

                case "/doAddWarehouseOperation":

                    WarehouseOperationDetails warehouseOperation
                            = new WarehouseOperationDetails();
                    warehouseOperation.setWarehouse(warehouse);
                    warehouseOperation.setBuyer(request.getParameter("buyer"));
                    if (warehouseOperation.getBuyer().equals("null")) {
                        warehouseOperation.setBuyer(null);
                    }
                    try {
                        warehouseOperation.setQuantityBrought(Double.valueOf(
                                request.getParameter("quantityBrought")));
                    } catch (Exception e) {
                    }
                    try {
                        warehouseOperation.setQuantitySold(Double.valueOf(
                                request.getParameter("quantitySold")));
                    } catch (Exception e) {
                    }
                    try {
                        warehouseOperation.setSellingPrice(new BigDecimal(
                                request.getParameter("sellingPrice")));
                    } catch (Exception e) {
                    }
                    try {
                        warehouseOperation.setProduceTypeBrought(
                                new StaticInputDetails(Integer.valueOf(
                                        request.getParameter("produceTypeBrought"))));
                    } catch (Exception e) {
                    }
                    try {
                        warehouseOperation.setProduceTypeSold(
                                new StaticInputDetails(Integer.valueOf(
                                        request.getParameter("produceTypeSold"))));
                    } catch (Exception e) {
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("sellingDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        warehouseOperation.setSellingDate(date);
                    } catch (ParseException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        warehouseOperation.setSellingDate(null);
                    }

                    try {
                        warehouseOperationService
                                .addWarehouseOperation(warehouseOperation);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/doEditWarehouseOperation":

                    try {
                        warehouseOperation = new WarehouseOperationDetails(
                                Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                        warehouseOperation = new WarehouseOperationDetails();
                    }
                    warehouseOperation.setWarehouse(warehouse);
                    warehouseOperation.setBuyer(request.getParameter("buyer"));
                    if (warehouseOperation.getBuyer().equals("null")) {
                        warehouseOperation.setBuyer(null);
                    }
                    try {
                        warehouseOperation.setQuantityBrought(Double.valueOf(
                                request.getParameter("quantityBrought")));
                    } catch (Exception e) {
                    }
                    try {
                        warehouseOperation.setQuantitySold(Double.valueOf(
                                request.getParameter("quantitySold")));
                    } catch (Exception e) {
                    }
                    try {
                        warehouseOperation.setSellingPrice(new BigDecimal(
                                request.getParameter("sellingPrice")));
                    } catch (Exception e) {
                    }
                    try {
                        warehouseOperation.setProduceTypeBrought(
                                new StaticInputDetails(Integer.valueOf(
                                        request.getParameter("produceTypeBrought"))));
                    } catch (Exception e) {
                    }
                    try {
                        warehouseOperation.setProduceTypeSold(
                                new StaticInputDetails(Integer.valueOf(
                                        request.getParameter("produceTypeSold"))));
                    } catch (Exception e) {
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("sellingDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        warehouseOperation.setSellingDate(date);
                    } catch (ParseException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        warehouseOperation.setSellingDate(null);
                    }

                    try {
                        warehouseOperationService
                                .editWarehouseOperation(warehouseOperation);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/doDeleteWarehouseOperation":
                    try {
                        warehouseOperationService.removeWarehouseOperation(
                                Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                    }

                    return;

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

    private static final Logger LOGGER = Logger.getLogger(EquipmentController.class.getSimpleName());
    @EJB
    private EquipmentRequestsLocal equipmentService;
    @EJB
    private StaticInputRequestsLocal staticInputService;
    @EJB
    private WarehouseOperationRequestsLocal warehouseOperationService;

}
