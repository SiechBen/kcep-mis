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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import ke.co.miles.kcep.mis.requests.procurement.ProcurementRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.ProcurementDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "ProcurementController", urlPatterns = {"/procurements", "/addProcurement", "/doAddProcurement"})
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

        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                if (rightsMap.equals("systemAdminSession") || rightsMap.equals("nationalOfficerSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddProcurement");
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
                    //Retrieve the list of procurements
                    List<ProcurementDetails> procurements;
                    try {
                        procurements = procurementService.retrieveProcurements();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during procurements retrieval", ex);
                        return;
                    }

                    //Avail the procurements in the application scope
                    if (procurements != null) {
                        for (ProcurementDetails procurement : procurements) {
                            if (procurement.getInvoiceOrReceipt() != null) {
                                String[] folders = procurement.getInvoiceOrReceipt().split(fileSeparator);
                                String fileName = folders[folders.length - 1];
                                procurement.setFileName(fileName);
                            }
                        }
                        session.setAttribute("procurements", procurements);
                    }
                    break;
                case "/head_addProcurement":

                    break;

                case "/doAddProcurement":

                    CountyDetails county = new CountyDetails();
                    try {
                        county.setId(Short.valueOf(String.valueOf(request.getParameter("county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    ProcurementDetails procurement = new ProcurementDetails();
                    procurement.setCounty(county);
                    procurement.setCost(String.valueOf(request.getParameter("cost")));
                    procurement.setItem(String.valueOf(request.getParameter("item")));
                    procurement.setSubCounty(String.valueOf(request.getParameter("sub-county")));
                    procurement.setDescription(String.valueOf(request.getParameter("description")));
                    procurement.setTargetOffice(String.valueOf(request.getParameter("target-office")));
                    procurement.setLpoNumber(String.valueOf(request.getParameter("lpo-number")));
                    procurement.setSerialNumber(String.valueOf(request.getParameter("serial-number")));
                    procurement.setDatePurchased(String.valueOf(request.getParameter("date-procurementd")));

                    if (procurement.getCost().equals("null")) {
                        procurement.setCost(null);
                    }
                    if (procurement.getDatePurchased().equals("null")) {
                        procurement.setDatePurchased(null);
                    }
                    if (procurement.getDescription().equals("null")) {
                        procurement.setDescription(null);
                    }
                    if (procurement.getItem().equals("null")) {
                        procurement.setItem(null);
                    }
                    if (procurement.getLpoNumber().equals("null")) {
                        procurement.setLpoNumber(null);
                    }
                    if (procurement.getSerialNumber().equals("null")) {
                        procurement.setSerialNumber(null);
                    }
                    if (procurement.getSubCounty().equals("null")) {
                        procurement.setSubCounty(null);
                    }
                    if (procurement.getTargetOffice().equals("null")) {
                        procurement.setTargetOffice(null);
                    }

                    ServletContext context = getServletContext();
                    String realPath = context.getRealPath("/");
                    String filePath = realPath + fileSeparator + "documents" + fileSeparator + "procurement"
                            + fileSeparator + "invoice_or_receipt";
                    final Part filePart = request.getPart("invoice-or-receipt");
                    final String fileName = getFileName(filePart);

                    if (fileName == null) {
                        procurement.setInvoiceOrReceipt(null);
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
                    } else {

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

                            procurement.setInvoiceOrReceipt(filePath);
                            outStream.close();

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
                        LOGGER.log(Level.INFO, getBundle().getString(""), e);
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

}
