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
import java.io.PrintWriter;
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
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;

/**
 *
 * @author siech
 */
@WebServlet(name = "EVoucherController", urlPatterns = {
    //    "/addEVoucher",
    //    "/doAddEVoucher", "/doEditEVoucher", "/doDeleteEVoucher", "/eVouchers",
    "/farmers", "/agroDealers"})
@MultipartConfig
public class EVoucherController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession(false);
        String path = request.getServletPath();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                if (rightsMap.equals("systemAdminSession") || rightsMap.equals("nationalOfficerSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddEVoucher");
                        urlPaths.add("/doEditEVoucher");
                        urlPaths.add("/doDeleteEVoucher");
                        switch (path) {
                            case "/eVouchers":
                                path = "/head_eVouchers";
                                urlPaths.add(path);
                                break;
                            case "/addEVoucher":
                                path = "/head_addEVoucher";
                                urlPaths.add(path);
                                break;
                            case "/farmers":
                                path = "/head_farmers";
                                urlPaths.add(path);
                                break;
                            case "/agroDealers":
                                path = "/head_agro_dealers";
                                urlPaths.add(path);
                                break;
                            default:
                                break;
                        }
                    }
                } else if (rightsMap.equals("equityPersonnelSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/doAddEVoucher");
                        urlPaths.add("/doEditEVoucher");
                        urlPaths.add("/doDeleteEVoucher");
                        switch (path) {
                            case "/eVouchers":
                                path = "/equity_eVouchers";
                                urlPaths.add(path);
                                break;
                            case "/addEVoucher":
                                path = "/equity_addEVoucher";
                                urlPaths.add(path);
                                break;
                            case "/farmers":
                                path = "/equity_farmers";
                                urlPaths.add(path);
                                break;
                            case "/agroDealers":
                                path = "/equity_agro_dealers";
                                urlPaths.add(path);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/equity_farmers":
                case "/head_farmers":

                    if (session.getAttribute("farmerSearchFunction") == null || (session.getAttribute("farmerSearchFunction") != null && !((Boolean) session.getAttribute("farmerSearchFunction")))) {
                        HashMap<String, Integer> countMap;
                        try {
                            countMap = personService.countAllFarmersAndAgrodealers();
                            int femaleYouth = 0;
                            int femaleElderly = 0;
                            int femaleTotal = 0;
                            int maleYouth = 0;
                            int maleElderly = 0;
                            int maleTotal = 0;
                            int totalPeople = 0;

                            for (String countType : countMap.keySet()) {
                                switch (countType) {
                                    case "Female youth":
                                        femaleYouth = countMap.get(countType);
                                        break;
                                    case "Female elderly":
                                        femaleElderly = countMap.get(countType);
                                        break;
                                    case "Female total":
                                        femaleTotal = countMap.get(countType);
                                        break;
                                    case "Male youth":
                                        maleYouth = countMap.get(countType);
                                        break;
                                    case "Male elderly":
                                        maleElderly = countMap.get(countType);
                                        break;
                                    case "Male total":
                                        maleTotal = countMap.get(countType);
                                        break;
                                    case "Total people":
                                        totalPeople = countMap.get(countType);
                                        break;
                                }
                            }

                            session.setAttribute("femaleYouth", femaleYouth);
                            session.setAttribute("femaleElderly", femaleElderly);
                            session.setAttribute("femaleTotal", femaleTotal);
                            session.setAttribute("maleYouth", maleYouth);
                            session.setAttribute("maleElderly", maleElderly);
                            session.setAttribute("maleTotal", maleTotal);
                            session.setAttribute("totalPeople", totalPeople);
                            List<PersonRoleDetail> countOptions = new ArrayList<>();
                            countOptions.add(PersonRoleDetail.FARMER);
                            countOptions.add(PersonRoleDetail.AGRO_DEALER);
                            session.setAttribute("countOptions", countOptions);

                        } catch (MilesException ex) {
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                            LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        }

                        try {
                            session.setAttribute("farmers", personService.retrieveFarmers());
                            session.setAttribute("farmerSearchFunction", false);
                        } catch (MilesException ex) {
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            response.getWriter().write(getBundle().getString(ex.getCode()));
                            LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                            return;
                        }
                    } else {
                        int farmerSearchTimes = 0;
                        if (session.getAttribute("farmerSearchTimes") == null) {
                            farmerSearchTimes = 1;
                        } else if ((int) session.getAttribute("farmerSearchTimes") >= 2) {
                            session.setAttribute("farmerSearchFunction", false);
                            session.setAttribute("farmerSearchTimes", null);
                        } else {
                            farmerSearchTimes = ((int) session.getAttribute("farmerSearchTimes"));
                        }
                        session.setAttribute("farmerSearchTimes", ++farmerSearchTimes);
                    }

                    break;

                case "/equity_agro_dealers":
                case "/head_agro_dealers":
                    if (session.getAttribute("agroDealerSearchFunction") == null || (session.getAttribute("agroDealerSearchFunction") != null && !((Boolean) session.getAttribute("agroDealerSearchFunction")))) {
                        try {
                            HashMap<String, Integer> countMap = personService.countAllFarmersAndAgrodealers();
                            int femaleYouth = 0;
                            int femaleElderly = 0;
                            int femaleTotal = 0;
                            int maleYouth = 0;
                            int maleElderly = 0;
                            int maleTotal = 0;
                            int totalPeople = 0;

                            for (String countType : countMap.keySet()) {
                                switch (countType) {
                                    case "Female youth":
                                        femaleYouth = countMap.get(countType);
                                        break;
                                    case "Female elderly":
                                        femaleElderly = countMap.get(countType);
                                        break;
                                    case "Female total":
                                        femaleTotal = countMap.get(countType);
                                        break;
                                    case "Male youth":
                                        maleYouth = countMap.get(countType);
                                        break;
                                    case "Male elderly":
                                        maleElderly = countMap.get(countType);
                                        break;
                                    case "Male total":
                                        maleTotal = countMap.get(countType);
                                        break;
                                    case "Total people":
                                        totalPeople = countMap.get(countType);
                                        break;
                                }
                            }

                            session.setAttribute("femaleYouth", femaleYouth);
                            session.setAttribute("femaleElderly", femaleElderly);
                            session.setAttribute("femaleTotal", femaleTotal);
                            session.setAttribute("maleYouth", maleYouth);
                            session.setAttribute("maleElderly", maleElderly);
                            session.setAttribute("maleTotal", maleTotal);
                            session.setAttribute("totalPeople", totalPeople);
                            List<PersonRoleDetail> countOptions = new ArrayList<>();
                            countOptions.add(PersonRoleDetail.FARMER);
                            countOptions.add(PersonRoleDetail.AGRO_DEALER);
                            session.setAttribute("countOptions", countOptions);

                        } catch (MilesException ex) {
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                            LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        }

                        try {
                            session.setAttribute("agroDealers", personService.retrieveAgroDealers());
                            session.setAttribute("agroDealerSearchFunction", false);
                        } catch (MilesException ex) {
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            response.getWriter().write(getBundle().getString(ex.getCode()));
                            LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                            return;
                        }
                    } else {
                        int agroDealerSearchTimes = 0;
                        if (session.getAttribute("agroDealerSearchTimes") == null) {
                            agroDealerSearchTimes = 1;
                        } else if ((int) session.getAttribute("agroDealerSearchTimes") >= 2) {
                            session.setAttribute("farmerSearchFunction", false);
                            session.setAttribute("agroDealerSearchTimes", null);
                        } else {
                            agroDealerSearchTimes = ((int) session.getAttribute("agroDealerSearchTimes"));
                        }
                        session.setAttribute("agroDealerSearchTimes", ++agroDealerSearchTimes);
                    }

                    break;

                case "/equity_eVouchers":
                case "/head_eVouchers":

                    List<EVoucherDetails> eVouchers;
                    try {
                        eVouchers = eVoucherService.retrieveEVouchers();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

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

                    try {
                        session.setAttribute("inputTypes", inputTypeService.retrieveInputTypes());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
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
                        date = userDateFormat.parse(request.getParameter("date-redeemed"));

                        date = databaseDateFormat.parse(databaseDateFormat.format(date));

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
                    Part filePart = request.getPart("inputs-loogbook-page");
                    String fileName = getFileName(filePart);
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

                case "/doEditEVoucher":

                    person = new PersonDetails();
                    try {
                        person.setId(Integer.valueOf(String.valueOf(request.getParameter("person"))));
                    } catch (Exception e) {
                        person = null;
                    }

                    inputType = new InputTypeDetails();
                    try {
                        inputType.setId(Short.valueOf(String.valueOf(request.getParameter("inputType"))));
                    } catch (Exception e) {
                        inputType = null;
                    }

                    try {
                        eVoucher = new EVoucherDetails(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                        eVoucher = new EVoucherDetails();
                    }
                    try {
                        eVoucher.setAmount(String.valueOf(request.getParameter("amount")));
                    } catch (Exception e) {
                        eVoucher.setAmount(null);
                    }

                    eVoucher.setInputType(inputType);
                    eVoucher.setPerson(person);

                    try {
                        date = userDateFormat.parse(request.getParameter("dateRedeemed"));

                        date = databaseDateFormat.parse(databaseDateFormat.format(date));

                        eVoucher.setDateRedeemed(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        eVoucher.setDateRedeemed(null);
                    }

//<editor-fold defaultstate="collapsed" desc="attachment">
                    //                    context = getServletContext();
//                    realPath = context.getRealPath("/");
//                    filePath = realPath + "documents" + fileSeparator + "eVoucher" + fileSeparator + "inputs_logbook_pages";
//                    filePart = request.getPart("inputs-loogbook-page");
//                    fileName = getFileName(filePart);
//
//                    try {
//                        filePath = filePath + fileSeparator + fileName;
//                        new File(filePath).getParentFile().mkdirs();
//
//                        outStream = new FileOutputStream(filePath);
//                        inStream = filePart.getInputStream();
//
//                        final int startOffset = 0;
//                        final byte[] buffer = new byte[4096];
//                        while (inStream.read(buffer) > 0) {
//                            outStream.write(buffer, startOffset, buffer.length);
//                        }
//
//                        eVoucher.setInputsLogbookPage(filePath);
//                        outStream.close();
//
//                    } catch (FileNotFoundException e) {
//                        eVoucher.setInputsLogbookPage(null);
//                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                        response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
//                        LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
//                    }
//</editor-fold>
                    try {
                        eVoucherService.editEVoucher(eVoucher);
                        eVouchers = eVoucherService.retrieveEVouchers();
                        session.setAttribute("eVouchers", eVouchers);
                        updateEVoucherTable(response, eVouchers);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getMessage()));
                        LOGGER.log(Level.INFO, getBundle().getString(""), e);
                    }
                    return;

                case "/doDeleteEVoucher":
                    try {
                        eVoucherService.removeEVoucher(Integer.valueOf(request.getParameter("id")));
                        eVouchers = eVoucherService.retrieveEVouchers();
                        session.setAttribute("eVouchers", eVouchers);
                        updateEVoucherTable(response, eVouchers);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getMessage()));
                        LOGGER.log(Level.INFO, getBundle().getString(""), e);
                    }

                    return;

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

    //</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(EVoucherController.class.getSimpleName());
    @EJB
    private EVoucherRequestsLocal eVoucherService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private InputTypeRequestsLocal inputTypeService;

    //<editor-fold defaultstate="collapsed" desc="Update tables">
    private void updateEVoucherTable(HttpServletResponse response, List<EVoucherDetails> eVouchers) throws IOException {
        PrintWriter out = response.getWriter();
        int index = 0;
        for (EVoucherDetails eVoucher : eVouchers) {
            if (index % 2 == 0) {
                out.write("<tr class=\"odd\">");
            } else {
                out.write("<tr>");
            }
            out.write(" <td>" + ++index + "</td>\n"
                    + "                                    <td>" + eVoucher.getAmount() + "</td>\n"
                    + "                                    <td>" + eVoucher.getInputType().getType() + "</td>\n"
                    + "                                    <td>" + eVoucher.getPerson().getName() + "</td>\n"
                    + "                                    <td>" + eVoucher.getDateRedeemed() + "</td>\n"
                    + "                                    <td><a onclick=\"loadAjaxWindow('download?filePath=" + (eVoucher.getInputsLogbookPage() == null ? "" : eVoucher.getInputsLogbookPage()) + "')\" target=\"_blank\">" + eVoucher.getFileName() + "</a></td>\n"
                    + "                                    <td><button onclick=\"editEVoucher('" + eVoucher.getId() + "', '" + eVoucher.getAmount() + "', '" + eVoucher.getInputType().getId() + "', '" + eVoucher.getPerson().getId() + "', '" + eVoucher.getDateRedeemed() + "')\"><span class=\"glyphicon glyphicon-pencil\"></span></button></td>\n"
                    + "                                    <td><button onclick=\"deleteEVoucher(" + eVoucher.getId() + ")\"><span class=\"glyphicon glyphicon-trash\"></span></button></td>");
        }
    }
//</editor-fold>

}
