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
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
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
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.procurement.ProcurementRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.ProcurementDetails;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "ProcurementController", urlPatterns = {"/procurements",
    "/addProcurement", "/doAddProcurement", "/doEditProcurement",
    "/doDeleteProcurement"})
@MultipartConfig
public class ProcurementController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        //Get the user session
        HttpSession session = request.getSession();

        //Get the user path
        String path = request.getServletPath();
        String destination;

        final String fileSeparator = File.separator;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                if (rightsMap.equals("systemAdminSession") || rightsMap.equals("nationalOfficerSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddProcurement");
                        urlPaths.add("/doEditProcurement");
                        urlPaths.add("/doDeleteProcurement");
                        if (path.equals("/procurements")) {
                            path = "/head_procurements";
                            urlPaths.add(path);
                        } else if (path.equals("/addProcurement")) {
                            path = "/head_addProcurement";
                            urlPaths.add(path);
                        }
                    }
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/head_procurements":
                    try {
                        session.setAttribute("procurements", procurementService.retrieveProcurements());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during procurements retrieval", ex);
                        return;
                    }

                    try {
                        session.setAttribute("items", phenomenonService.retrieveGFSSCodes());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during procurements retrieval", ex);
                        return;
                    }

                    break;

                case "/doAddProcurement":

                    ProcurementDetails procurement = new ProcurementDetails();
                    procurement.setDescription(request.getParameter("description"));
                    procurement.setTargetOffice(request.getParameter("target-office"));
                    procurement.setLpoNumber(request.getParameter("lpo-number"));
                    procurement.setItem(request.getParameter("item"));
                    procurement.setSerialNumber(request.getParameter("serial-number"));
                    try {
                        date = userDateFormat.parse(request.getParameter("date-purchased"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurement.setDatePurchased(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        procurement.setDatePurchased(null);
                    }
                    try {
                        procurement.setCounty(new CountyDetails(Short.valueOf(request.getParameter("county"))));
                    } catch (Exception e) {
                    }
                    try {
                        procurement.setSubCounty(new SubCountyDetails(Short.valueOf(request.getParameter("sub-county"))));
                    } catch (Exception e) {
                    }

                    if (procurement.getItem().equals("null")) {
                        procurement.setItem(null);
                    }
                    if (procurement.getDatePurchased().equals("null")) {
                        procurement.setDatePurchased(null);
                    }
                    if (procurement.getDescription().equals("null")) {
                        procurement.setDescription(null);
                    }
                    if (procurement.getLpoNumber().equals("null")) {
                        procurement.setLpoNumber(null);
                    }
                    if (procurement.getSerialNumber().equals("null")) {
                        procurement.setSerialNumber(null);
                    }
                    if (procurement.getTargetOffice().equals("null")) {
                        procurement.setTargetOffice(null);
                    }
                    try {
                        procurement.setCost(new BigDecimal(request.getParameter("cost")));
                    } catch (Exception e) {
                        procurement.setCost(null);
                    }
                    try {
                        procurement.setGfssCode(new PhenomenonDetails(Integer.valueOf(request.getParameter("gfssCode"))));
                    } catch (Exception e) {
                    }

                    ServletContext context = getServletContext();
                    String realPath = context.getRealPath("/");
                    String filePath = realPath + fileSeparator + "documents" + fileSeparator + "procurement"
                            + fileSeparator + "invoice_or_receipt";
                    Part filePart = request.getPart("invoice-or-receipt");
                    String fileName = getFileName(filePart);

                    if (fileName == null) {
                        procurement.setInvoiceOrReceipt(null);
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
                    } else {

                        FileOutputStream outStream;
                        InputStream inStream;

                        try {
                            if (fileName != null & !fileName.isEmpty() && fileName.trim().length() != 0 && !fileName.equals("")) {
                                filePath += fileSeparator;
                                File toDelete = new File(filePath);
                                if (toDelete.isFile()) {
                                    toDelete.delete();
                                }
                                filePath += fileName;
                                new File(filePath).getParentFile().mkdirs();

                                outStream = new FileOutputStream(filePath);
                                inStream = filePart.getInputStream();

                                final int startOffset = 0;
                                final byte[] buffer = new byte[4096];
                                while (inStream.read(buffer) > 0) {
                                    outStream.write(buffer, startOffset, buffer.length);
                                }

                                procurement.setInvoiceOrReceipt(filePath);
                                outStream.close();

                            }
                        } catch (FileNotFoundException e) {
                            procurement.setInvoiceOrReceipt(null);
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
                            LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
                        }
                    }

                    try {
                        procurementService.addProcurement(procurement);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doEditProcurement":

                    try {
                        procurement = new ProcurementDetails(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                        procurement = new ProcurementDetails();
                    }
                    procurement.setDescription(request.getParameter("description"));
                    procurement.setTargetOffice(request.getParameter("target-office"));
                    procurement.setLpoNumber(request.getParameter("lpo-number"));
                    procurement.setItem(request.getParameter("item"));
                    procurement.setSerialNumber(request.getParameter("serial-number"));
                    try {
                        date = userDateFormat.parse(request.getParameter("date-purchased"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurement.setDatePurchased(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        procurement.setDatePurchased(null);
                    }
                    try {
                        procurement.setCounty(new CountyDetails(Short.valueOf(request.getParameter("county"))));
                    } catch (Exception e) {
                    }
                    try {
                        procurement.setSubCounty(new SubCountyDetails(Short.valueOf(request.getParameter("sub-county"))));
                    } catch (Exception e) {
                    }

                    if (procurement.getItem().equals("null")) {
                        procurement.setItem(null);
                    }
                    if (procurement.getDatePurchased().equals("null")) {
                        procurement.setDatePurchased(null);
                    }
                    if (procurement.getDescription().equals("null")) {
                        procurement.setDescription(null);
                    }
                    if (procurement.getLpoNumber().equals("null")) {
                        procurement.setLpoNumber(null);
                    }
                    if (procurement.getSerialNumber().equals("null")) {
                        procurement.setSerialNumber(null);
                    }
                    if (procurement.getTargetOffice().equals("null")) {
                        procurement.setTargetOffice(null);
                    }
                    try {
                        procurement.setCost(new BigDecimal(request.getParameter("cost")));
                    } catch (Exception e) {
                        procurement.setCost(null);
                    }
                    try {
                        procurement.setGfssCode(new PhenomenonDetails(Integer.valueOf(request.getParameter("gfssCode"))));
                    } catch (Exception e) {
                    }

                    try {
                        procurementService.editProcurement(procurement);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doDeleteProcurement":
                    try {
                        procurementService.removeProcurement(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
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

    private String uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String realPath = context.getRealPath("/");
        String fileSeparator = File.separator;
        String filePath = realPath + fileSeparator + "documents" + fileSeparator + "procurement" + fileSeparator + "invoice_or_receipt";
        final Part filePart = request.getPart("invoice-or-receipt");
        final String fileName = getFileName(filePart);
        FileOutputStream outStream;
        InputStream inStream;

        try {
            filePath = filePath + fileSeparator + fileName;
            new File(filePath).getParentFile().mkdirs();//If parent directories do not exist

            outStream = new FileOutputStream(filePath);
            inStream = filePart.getInputStream();

            final int startOffset = 0;
            final byte[] buffer = new byte[4096];
            while (inStream.read(buffer) > 0) {
                outStream.write(buffer, startOffset, buffer.length);
            }

            outStream.close();
        } catch (FileNotFoundException e) {
            filePath = null;
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
            LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
        }

        return filePath;
    }

    private static final Logger LOGGER = Logger.getLogger(ProcurementController.class.getSimpleName());
    @EJB
    private ProcurementRequestsLocal procurementService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;

}
