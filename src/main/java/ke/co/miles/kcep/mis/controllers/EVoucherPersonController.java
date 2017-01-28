/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.EVoucherDetails;
import ke.co.miles.kcep.mis.utilities.InputTypeDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 *
 * @author siech
 */
@WebServlet(name = "EVoucherController", urlPatterns = {
    //        "/addEVoucher", "/doAddEVoucher", "/doEditEVoucher", "/doDeleteEVoucher", "/eVouchers",
    "/addFarmer", "/addAgroDealer", "/farmers", "/getLocations", "/agroDealers",
    "/mapFarmers", "/mapAgroDealers"})
@MultipartConfig
public class EVoucherPersonController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
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
                switch (rightsMap) {
                    case "systemAdminSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/getLocations");
                            urlPaths.add("/addFarmer");
                            urlPaths.add("/addAgroDealer");
                            urlPaths.add("/doAddEVoucher");
                            urlPaths.add("/doEditEVoucher");
                            urlPaths.add("/doDeleteEVoucher");
                            switch (path) {
                                case "/mapAgroDealers":
                                    path = "/head_view_agro_dealers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/mapFarmers":
                                    path = "/head_view_farmers_on_map";
                                    urlPaths.add(path);
                                    break;
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
                                case "/addFarmer":
                                    path = "/head_addFarmer";
                                    urlPaths.add(path);
                                    break;
                                case "/addAgroDealer":
                                    path = "/head_addAgroDealer";
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
                        break;
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/getLocations");
                            urlPaths.add("/addFarmer");
                            urlPaths.add("/addAgroDealer");
                            urlPaths.add("/doAddEVoucher");
                            switch (path) {
                                case "/mapAgroDealers":
                                    path = "/head_view_agro_dealers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/mapFarmers":
                                    path = "/head_view_farmers_on_map";
                                    urlPaths.add(path);
                                    break;
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
                                case "/addFarmer":
                                    path = "/head_addFarmer";
                                    urlPaths.add(path);
                                    break;
                                case "/addAgroDealer":
                                    path = "/head_addAgroDealer";
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
                        break;
                    case "equityPersonnelSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/addFarmer");
                            urlPaths.add("/getLocations");
                            urlPaths.add("/addAgroDealer");
                            urlPaths.add("/doAddEVoucher");
                            switch (path) {
                                case "/mapAgroDealers":
                                    path = "/equity_view_agro_dealers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/mapFarmers":
                                    path = "/equity_view_farmers_on_map";
                                    urlPaths.add(path);
                                    break;
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
                                case "/addFarmer":
                                    path = "/equity_addFarmer";
                                    urlPaths.add(path);
                                    break;
                                case "/addAgroDealer":
                                    path = "/equity_addAgroDealer";
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
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/addFarmer");
                            urlPaths.add("/getLocations");
                            urlPaths.add("/addAgroDealer");
                            urlPaths.add("/doAddEVoucher");
                            switch (path) {
                                case "/mapAgroDealers":
                                    path = "/county_view_agro_dealers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/mapFarmers":
                                    path = "/county_view_farmers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/eVouchers":
                                    path = "/county_eVouchers";
                                    urlPaths.add(path);
                                    break;
                                case "/addEVoucher":
                                    path = "/county_addEVoucher";
                                    urlPaths.add(path);
                                    break;
                                case "/farmers":
                                    path = "/county_farmers";
                                    urlPaths.add(path);
                                    break;
                                case "/addFarmer":
                                    path = "/county_addFarmer";
                                    urlPaths.add(path);
                                    break;
                                case "/addAgroDealer":
                                    path = "/county_addAgroDealer";
                                    urlPaths.add(path);
                                    break;
                                case "/agroDealers":
                                    path = "/county_agro_dealers";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/getLocations");
                            urlPaths.add("/addFarmer");
                            urlPaths.add("/addAgroDealer");
                            switch (path) {
                                case "/mapAgroDealers":
                                    path = "/sub_county_view_agro_dealers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/mapFarmers":
                                    path = "/sub_county_view_farmers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/farmers":
                                    path = "/sub_county_farmers";
                                    urlPaths.add(path);
                                    break;
                                case "/addFarmer":
                                    path = "/sub_county_addFarmer";
                                    urlPaths.add(path);
                                    break;
                                case "/addAgroDealer":
                                    path = "/sub_county_addAgroDealer";
                                    urlPaths.add(path);
                                    break;
                                case "/agroDealers":
                                    path = "/sub_county_agro_dealers";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/addFarmer");
                            urlPaths.add("/getLocations");
                            urlPaths.add("/addAgroDealer");
                            switch (path) {
                                case "/mapAgroDealers":
                                    path = "/wao_view_agro_dealers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/mapFarmers":
                                    path = "/wao_view_farmers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/farmers":
                                    path = "/ward_farmers";
                                    urlPaths.add(path);
                                    break;
                                case "/addFarmer":
                                    path = "/ward_addFarmer";
                                    urlPaths.add(path);
                                    break;
                                case "/addAgroDealer":
                                    path = "/ward_addAgroDealer";
                                    urlPaths.add(path);
                                    break;
                                case "/agroDealers":
                                    path = "/ward_agro_dealers";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "agmarkSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/getLocations");
                            switch (path) {
                                case "/mapAgroDealers":
                                    path = "/agmark_view_agro_dealers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/mapFarmers":
                                    path = "/agmark_view_farmers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/eVouchers":
                                    path = "/agmark_eVouchers";
                                    urlPaths.add(path);
                                    break;
                                case "/farmers":
                                    path = "/agmark_farmers";
                                    urlPaths.add(path);
                                    break;
                                case "/agroDealers":
                                    path = "/agmark_agro_dealers";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "kalroSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/getLocations");
                            switch (path) {
                                case "/mapAgroDealers":
                                    path = "/kalro_view_agro_dealers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/mapFarmers":
                                    path = "/kalro_view_farmers_on_map";
                                    urlPaths.add(path);
                                    break;
                                case "/eVouchers":
                                    path = "/kalro_eVouchers";
                                    urlPaths.add(path);
                                    break;
                                case "/farmers":
                                    path = "/kalro_farmers";
                                    urlPaths.add(path);
                                    break;
                                case "/agroDealers":
                                    path = "/kalro_agro_dealers";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
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
                case "/county_farmers":
                    if (session.getAttribute("farmerSearchFunction") == null || (session.getAttribute("farmerSearchFunction") != null && !((Boolean) session.getAttribute("farmerSearchFunction")))) {
                        HashMap<String, Integer> countMap;
                        try {
                            countMap = personService.countCountyFarmersAndAgrodealers(((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId());
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
                            session.setAttribute("farmers", personService.retrieveCountyFarmers(((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId()));
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

                case "/equity_farmers":
                case "/kalro_farmers":
                case "/agmark_farmers":
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

                case "/getLocations":

                    try {

                        JSONObject jsonLocation;
                        JSONArray jsonList = new JSONArray();
                        StringBuilder personInfo;
                        List<PersonDetails> people = new ArrayList<>();

                        String personType = request.getParameter("personType");
                        if (personType.equals("Farmer")) {
                            people = (List<PersonDetails>) session.getAttribute("farmers");
                        } else if (personType.equals("AgroDealer")) {
                            people = (List<PersonDetails>) session.getAttribute("agroDealers");
                        }

                        for (PersonDetails person : people) {
                            personInfo = new StringBuilder();
                            personInfo.append("<strong>");
                            personInfo.append(person.getName());
                            personInfo.append("</strong>");
                            personInfo.append("<br>");
                            if (person.getLocation() == null) {
                                continue;
                            }
                            if (person.getLocation().getWard() != null) {
                                personInfo.append(person.getLocation().getWard().getName());
                                if (person.getLocation().getWard().getName() != null) {
                                    personInfo.append(", ");
                                }
                                personInfo.append(person.getLocation().getWard().getSubCounty().getName());
                                personInfo.append(", ");
                                personInfo.append(person.getLocation().getWard().getSubCounty().getCounty().getName());
                                personInfo.append("<br>");
                            }
                            if (person.getLocation().getVillage() != null) {
                                personInfo.append(person.getLocation().getVillage().getName());
                                if (person.getLocation().getVillage().getName() != null) {
                                    personInfo.append(" village");
                                    if (person.getLocation().getDivisionalLocation() != null) {
                                        personInfo.append(", ");
                                        personInfo.append(person.getLocation().getDivisionalLocation().getName());
                                        personInfo.append(" division");
                                    }
                                }

                            } else if (person.getLocation().getDivisionalLocation() != null) {
                                personInfo.append(person.getLocation().getDivisionalLocation().getName());
                            }
                            personInfo.append("<br>");
                            jsonLocation = new JSONObject();
                            jsonLocation.put("info", personInfo.toString());
                            try {
                                jsonLocation.put("lat", person.getLocation().getLatitude().doubleValue());
                                jsonLocation.put("long", person.getLocation().getLongitude().doubleValue());
                            } catch (Exception e) {
                            }
                            jsonList.add(jsonLocation);
                        }

                        response.setContentType("application/json");
                        response.getWriter().write(jsonList.toJSONString());

                    } catch (Exception ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during performance indicator retrieval", ex);
                        return;
                    }

                    return;

                case "/county_addFarmer":

                    PersonDetails countyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    List<SubCountyDetails> subCounties;
                    try {
                        subCounties = subCountyService.retrieveSubCounties(countyDeskOfficer.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of sub-counties", ex);
                        return;
                    }

                    if (subCounties != null) {

                        for (SubCountyDetails subCountyDetails : subCounties) {
                            try {
                                session.setAttribute("wards", wardService.retrieveWards(subCountyDetails.getId()));
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                                return;
                            }
                        }

                        session.setAttribute("subCounties", subCounties);
                    }
                    break;

                case "/sub_county_addFarmer":

                    PersonDetails subCountyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        session.setAttribute("wards", wardService.retrieveWards(subCountyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                        return;
                    }
                    break;

                case "/county_agro_dealers":
                case "/equity_agro_dealers":
                case "/kalro_agro_dealers":
                case "/agmark_agro_dealers":
                case "/head_agro_dealers":
                    if (session.getAttribute("agroDealerSearchFunction") == null || (session.getAttribute("agroDealerSearchFunction") != null && !((Boolean) session.getAttribute("agroDealerSearchFunction")))) {
                        try {
                            HashMap<String, Integer> countMap;

                            if (path.equals("/county_agro_dealers")) {
                                countMap = personService.countCountyFarmersAndAgrodealers(((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId());
                            } else {
                                countMap = personService.countAllFarmersAndAgrodealers();
                            }
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
                case "/agmark_eVouchers":
                case "/kalro_eVouchers":
                case "/county_eVouchers":
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
                    BufferedOutputStream outStream = null;
                    BufferedInputStream inStream = null;

                    try {
                        if (fileName != null & !fileName.isEmpty() && fileName.trim().length() != 0 && !fileName.equals("")) {
                            filePath += fileSeparator;
                            File toDelete = new File(filePath);
                            if (toDelete.isFile()) {
                                toDelete.delete();
                            }
                            filePath += fileName;
                            new File(filePath).getParentFile().mkdirs();

                            outStream = new BufferedOutputStream(new FileOutputStream(filePath));
                            inStream = new BufferedInputStream(filePart.getInputStream());

                            final int startOffset = 0;
                            final byte[] buffer = new byte[4096];
                            while (inStream.read(buffer) > 0) {
                                outStream.write(buffer, startOffset, buffer.length);
                            }

                            eVoucher.setInputsLogbookPage(filePath);
                        }
                    } catch (FileNotFoundException e) {
                        eVoucher.setInputsLogbookPage(null);
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
                    } finally {
                        if (outStream != null) {
                            outStream.close();
                        }
                        if (inStream != null) {
                            inStream.close();
                        }
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
    private static final Logger LOGGER = Logger.getLogger(EVoucherPersonController.class.getSimpleName());
    @EJB
    private WardRequestsLocal wardService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private EVoucherRequestsLocal eVoucherService;
    @EJB
    private InputTypeRequestsLocal inputTypeService;
    @EJB
    private SubCountyRequestsLocal subCountyService;

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
