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
import java.text.ParseException;
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
import ke.co.miles.kcep.mis.requests.evoucher.EVoucherRequestsLocal;
import ke.co.miles.kcep.mis.requests.input.type.InputTypeRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.EVoucherDetails;
import ke.co.miles.kcep.mis.utilities.InputTypeDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "EVoucherController", urlPatterns = {"/addEVoucher", "/doAddEVoucher", "/eVouchers"})
@MultipartConfig
public class EVoucherController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        //Get the user session
        HttpSession session = request.getSession(false);

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
                        urlPaths.add("/doAddEVoucher");
                        if (path.equals("/eVouchers")) {
                            path = "/head_eVouchers";
                            urlPaths.add(path);
                        } else if (path.equals("/addEVoucher")) {
                            path = "/head_addEVoucher";
                            urlPaths.add(path);
                        }
                    }
                } else if (rightsMap.equals("equityPersonnelSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddEVoucher");
                        if (path.equals("/eVouchers")) {
                            path = "/equity_eVouchers";
                            urlPaths.add(path);
                        } else if (path.equals("/addEVoucher")) {
                            path = "/equity_addEVoucher";
                            urlPaths.add(path);
                        }
                    }
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/equity_eVouchers":
                case "/head_eVouchers":
                    //Retrieve the list of eVouchers
                    LOGGER.log(Level.INFO, "Retrieving the list of eVouchers");
                    List<EVoucherDetails> eVouchers;
                    try {
                        eVouchers = eVoucherService.retrieveEVouchers();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    //Avail the eVoucher in the application scope
                    LOGGER.log(Level.INFO, "Avail the eVoucher in the application scope");
                    if (eVouchers != null) {
                        for (EVoucherDetails eVoucherDetails : eVouchers) {
                            if (eVoucherDetails.getInputsLogbookPage() != null) {
                                try {
                                    String[] folders = eVoucherDetails.getInputsLogbookPage().split(fileSeparator);
                                    String fileName = folders[folders.length - 1];
                                    eVoucherDetails.setFileName(fileName);
                                } catch (Exception e) {
                                }
                            }
                        }
                        session.setAttribute("eVouchers", eVouchers);
                    }
                    break;

                case "/equity_addEVoucher":
                case "/head_addEVoucher":
                    //Retrieve the list of people
                    List<PersonDetails> people;
                    try {
                        people = personService.retrievePeople();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    //Avail the people in the application scope
                    if (people != null) {
                        session.setAttribute("people", people);
                    }

                    //Retrieve the list of input types
                    List<InputTypeDetails> inputTypes;
                    try {
                        inputTypes = inputTypeService.retrieveInputTypes();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    //Avail the people in the application scope
                    if (inputTypes != null) {
                        session.setAttribute("inputTypes", inputTypes);
                    }

                    break;

                case "/doAddEVoucher":

                    PersonDetails person = new PersonDetails();
                    try {
                        person.setId(Integer.valueOf(String.valueOf(request.getParameter("person"))));
                    } catch (Exception e) {
                        person = null;
                    }

                    InputTypeDetails inputType = new InputTypeDetails();
                    try {
                        inputType.setId(Short.valueOf(String.valueOf(request.getParameter("input-type"))));
                    } catch (Exception e) {
                        inputType = null;
                    }

                    EVoucherDetails eVoucher = new EVoucherDetails();
                    try {
                        eVoucher.setAmount(String.valueOf(request.getParameter("amount")));
                    } catch (Exception e) {
                        eVoucher.setAmount(null);
                    }

                    eVoucher.setInputType(inputType);
                    eVoucher.setPerson(person);

                    try {
                        //Read in date string in the format MM/dd/yyyy and parse it to date
                        date = userDateFormat.parse(request.getParameter("date-redeemed"));

                        //Format the date string to yyyy/MM/dd and parse it to date
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));

                        //Set the start date
                        eVoucher.setDateRedeemed(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        eVoucher.setDateRedeemed(null);
                    }

                    ServletContext context = getServletContext();
                    String realPath = context.getRealPath("/");
                    String filePath = realPath + "documents" + fileSeparator + "eVoucher" + fileSeparator + "inputs_logbook_pages";
                    final Part filePart = request.getPart("inputs-loogbook-page");
                    final String fileName = getFileName(filePart);
                    FileOutputStream outStream;
                    InputStream inStream;

                    try {
                        filePath = filePath + fileSeparator + fileName;
                        new File(filePath).getParentFile().mkdirs();

                        outStream = new FileOutputStream(filePath);
                        inStream = filePart.getInputStream();

                        final int startOffset = 0;
                        final byte[] buffer = new byte[4096];
                        while (inStream.read(buffer) > 0) {
                            outStream.write(buffer, startOffset, buffer.length);
                        }

                        eVoucher.setInputsLogbookPage(filePath);
                        outStream.close();

                    } catch (FileNotFoundException e) {
                        eVoucher.setInputsLogbookPage(null);
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
                    }

                    try {
                        eVoucherService.addEVoucher(eVoucher);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getMessage()));
                        LOGGER.log(Level.INFO, getBundle().getString(""), e);
                    }
                    return;
            }
            //Use request dispatcher to foward request internally
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

    //</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(EVoucherController.class.getSimpleName());
    @EJB
    private EVoucherRequestsLocal eVoucherService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private InputTypeRequestsLocal inputTypeService;

}
