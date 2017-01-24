/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.access.AccessRequestsLocal;
import ke.co.miles.kcep.mis.requests.account.AccountRequestsLocal;
import ke.co.miles.kcep.mis.requests.input.type.InputTypeRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.useraccount.UserAccountRequestsLocal;
import ke.co.miles.kcep.mis.utilities.AccountDetails;
import ke.co.miles.kcep.mis.utilities.ContactDetails;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.FarmerGroupDetails;
import ke.co.miles.kcep.mis.utilities.FarmerSubGroupDetails;
import ke.co.miles.kcep.mis.utilities.InputTypeDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.RegionDetail;
import ke.co.miles.kcep.mis.utilities.SexDetail;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.UserAccountDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "PersonController", urlPatterns = {"/people", "/addPerson",
    "/doAddPerson", "/doEditPerson", "/doDeletePerson", "/userProfile",
    "/user_account", "/validatePassword", "/editUserAccount",
    "/changeCounter", "/searchFarmer", "/searchAgroDealer"})
public class PersonController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

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
                urlPaths.add("/changeCounter");
                switch (rightsMap) {
                    case "systemAdminSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/doEditPerson");
                            urlPaths.add("/doDeletePerson");
                            urlPaths.add("/searchFarmer");
                            urlPaths.add("/validatePassword");
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/searchAgroDealer");
                            switch (path) {
                                case "/people":
                                    path = "/head_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/head_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/editPerson":
                                    path = "/head_editPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/head_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/head_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/searchFarmer");
                            urlPaths.add("/validatePassword");
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/searchAgroDealer");
                            switch (path) {
                                case "/people":
                                    path = "/head_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/head_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/head_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/head_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "equityPersonnelSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/searchFarmer");
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/validatePassword");
                            urlPaths.add("/searchAgroDealer");
                            switch (path) {
                                case "/people":
                                    path = "/equity_people";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/equity_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/equity_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "kalroSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/validatePassword");
                            switch (path) {
                                case "/people":
                                    path = "/kalro_people";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/kalro_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/kalro_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "agmarkSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/validatePassword");
                            switch (path) {
                                case "/people":
                                    path = "/agmark_people";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/agmark_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/agmark_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/validatePassword");
                            switch (path) {
                                case "/people":
                                    path = "/region_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/region_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/region_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/region_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/searchFarmer");
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/validatePassword");
                            urlPaths.add("/searchAgroDealer");
                            switch (path) {
                                case "/people":
                                    path = "/county_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/county_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/county_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/county_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/validatePassword");
                            switch (path) {
                                case "/people":
                                    path = "/sub_county_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/sub_county_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/sub_county_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/sub_county_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/validatePassword");
                            switch (path) {
                                case "/people":
                                    path = "/ward_people";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/ward_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/ward_user_account";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "agroDealerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            urlPaths.add("/editUserAccount");
                            urlPaths.add("/validatePassword");
                            switch (path) {
                                case "/people":
                                    path = "/agro_dealer_people";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/agro_dealer_userProfile";
                                    urlPaths.add(path);
                                    break;
                                case "/user_account":
                                    path = "/agro_dealer_user_account";
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

                case "/head_people":

                    HashMap<String, Integer> countMap;
                    try {
                        countMap = personService.countAllPeople();
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
                        session.setAttribute("countOptions", PersonRoleDetail.values());

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    List<PersonDetails> people;
                    try {
                        long startTime = System.currentTimeMillis();
                        MilesDebugger.debug("Started retrieving list of people at: " + startTime);

                        session.setAttribute("people", personService.retrieveNonFarmersAndNonAgroDealers());

                        long endTime = System.currentTimeMillis();
                        MilesDebugger.debug("Finished retrieving list of people at: " + endTime);
                        MilesDebugger.debug("Time taken: " + (endTime - startTime) / 1000);
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/equity_people":

                    try {
                        countMap = personService.countAllPeople();
                        int femaleCount = 0,
                                maleCount = 0,
                                totalCount = 0;

                        for (String countType : countMap.keySet()) {
                            switch (countType) {
                                case "Female":
                                    femaleCount = countMap.get(countType);
                                    break;
                                case "Male":
                                    maleCount = countMap.get(countType);
                                    break;
                                default:
                                    totalCount = countMap.get(countType);
                                    break;
                            }
                        }

                        session.setAttribute("totalCount", totalCount);
                        session.setAttribute("femaleCount", femaleCount);
                        session.setAttribute("maleCount", maleCount);
                        session.setAttribute("countOptions", PersonRoleDetail.values());

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);

                    }

                    try {
                        long startTime = System.currentTimeMillis();
                        MilesDebugger.debug("Started retrieving list of people at: " + startTime);

                        session.setAttribute("people", personService.retrieveFarmersAndAgroDealers());

                        long endTime = System.currentTimeMillis();
                        MilesDebugger.debug("Finished retrieving list of people at: " + endTime);
                        MilesDebugger.debug("Time taken: " + (endTime - startTime) / 1000);
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/ward_people":

                    PersonDetails wao = (PersonDetails) session.getAttribute("person");
                    try {
                        session.setAttribute("people", personService.retrieveWardPeople(wao.getLocation().getWard().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/kalro_people":

                    try {
                        session.setAttribute("people", personService.retrieveKalroPeople());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/agmark_people":

                    try {
                        session.setAttribute("people", personService.retrieveAgmarkPeople());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/region_people":

                    PersonDetails regionalCoordinator = (PersonDetails) session.getAttribute("person");

                    try {
                        session.setAttribute("people", personService.retrieveRegionPeople(regionalCoordinator.getLocation().getCounty().getRegion().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/county_people":

                    try {
                        countMap = personService.countCountyPeople(((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId());
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
                        session.setAttribute("countOptions", PersonRoleDetail.values());

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    try {
                        session.setAttribute("people", personService.retrieveCountyNonFarmersAndNonAgroDealers(((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/sub_county_people":

                    PersonDetails subCountyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        session.setAttribute("people", personService.retrieveSubCountyPeople(subCountyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    try {
                        session.setAttribute("wards", wardService.retrieveWards(subCountyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                        return;
                    }

                    break;

                case "/agro_dealer_people":

                    PersonDetails agroDealer = (PersonDetails) session.getAttribute("person");

                    try {
                        session.setAttribute("people", personService.retrieveSubCountyFarmers(agroDealer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    break;

                case "/doAddPerson":

                    ContactDetails contact = new ContactDetails();
                    contact.setEmail(String.valueOf(request.getParameter("email")));
                    contact.setPhone(String.valueOf(request.getParameter("phoneNumber")));
                    contact.setPostalAddress(String.valueOf(request.getParameter("postalAddress")));

                    FarmerGroupDetails farmerGroup = new FarmerGroupDetails();
                    try {
                        farmerGroup.setId(Integer.valueOf(String.valueOf(request.getParameter("farmerGroup"))));
                    } catch (Exception e) {
                        farmerGroup = null;
                    }

                    FarmerSubGroupDetails farmerSubGroup = new FarmerSubGroupDetails();
                    try {
                        farmerSubGroup.setId(Integer.valueOf(String.valueOf(request.getParameter("farmerSubGroup"))));
                    } catch (Exception e) {
                        farmerSubGroup = null;
                    }

                    SubCountyDetails subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Short.valueOf(String.valueOf(request.getParameter("subCounty"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

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

                    RegionDetail region;
                    try {
                        region = RegionDetail.getRegionDetail(Short.valueOf(String.valueOf(request.getParameter("region"))));
                    } catch (Exception e) {
                        region = null;
                    }

                    LocationDetails location = new LocationDetails();
                    location.setSubCounty(subCounty);
                    location.setCounty(county);
                    location.setWard(ward);

                    PersonRoleDetail personRole;
                    try {
                        personRole = PersonRoleDetail.getPersonRoleDetail(Short.valueOf(String.valueOf(request.getParameter("personRole"))));
                        if (personRole.equals(PersonRoleDetail.NATIONAL_OFFICER)
                                || personRole.equals(PersonRoleDetail.SYSTEM_ADMIN)
                                || personRole.equals(PersonRoleDetail.AGMARK_OFFICER)) {
                            location.setCounty(null);
                            location.setSubCounty(null);
                            location.setWard(null);
                            location.setRegion(null);
                        } else if (personRole.equals(PersonRoleDetail.REGIONAL_COORDINATOR)) {
                            location.setCounty(null);
                            location.setSubCounty(null);
                            location.setWard(null);
                            location.setRegion(region);
                        }
                    } catch (Exception e) {
                        personRole = null;
                    }

                    PersonDetails person = new PersonDetails();
                    person.setBusinessName(String.valueOf(request.getParameter("businessName")));

                    person.setContact(contact);
                    person.setFarmerGroup(farmerGroup);
                    person.setFarmerSubGroup(farmerSubGroup);
                    person.setLocation(location);
                    person.setName(String.valueOf(request.getParameter("name")));
                    person.setNationalId(String.valueOf(request.getParameter("nationalId")));
                    if (person.getBusinessName().equals("null")) {
                        person.setBusinessName(null);
                    }
                    if (person.getNationalId().equals("null")) {
                        person.setNationalId(null);
                    }
                    if (person.getName().equals("null")) {
                        person.setName(null);
                    }
                    if (contact.getEmail().equals("null")) {
                        contact.setEmail(null);
                    }
                    if (contact.getPhone().equals("null")) {
                        contact.setPhone(null);
                    }
                    if (contact.getPostalAddress().equals("null")) {
                        contact.setPostalAddress(null);
                    }
                    try {
                        person.setSex(SexDetail.getSexDetail(Short.valueOf(request.getParameter("sex"))));
                    } catch (Exception e) {
                        person.setSex(null);
                    }
                    try {
                        person.setYearOfBirth(Short.valueOf(request.getParameter("yearOfBirth")));
                    } catch (Exception e) {
                        person.setYearOfBirth(null);
                    }

                    try {
                        personService.addPerson(person, personRole);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/doEditPerson":

                    try {
                        contact = new ContactDetails(Integer.valueOf(
                                request.getParameter("contactId")));
                    } catch (Exception e) {
                        contact = new ContactDetails();
                    }
                    contact.setEmail(String.valueOf(
                            request.getParameter("email")));
                    contact.setPhone(String.valueOf(
                            request.getParameter("phoneNumber")));
                    contact.setPostalAddress(String.valueOf(
                            request.getParameter("postalAddress")));

                    farmerGroup = new FarmerGroupDetails();
                    try {
                        farmerGroup.setId(Integer.valueOf(String.valueOf(
                                request.getParameter("farmerGroup"))));
                    } catch (Exception e) {
                        farmerGroup = null;
                    }

                    farmerSubGroup = new FarmerSubGroupDetails();
                    try {
                        farmerSubGroup.setId(Integer.valueOf(String.valueOf(
                                request.getParameter("farmerSubGroup"))));
                    } catch (Exception e) {
                        farmerSubGroup = null;
                    }

                    subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Short.valueOf(String.valueOf(
                                request.getParameter("subCounty"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

                    county = new CountyDetails();
                    try {
                        county.setId(Short.valueOf(String.valueOf(
                                request.getParameter("county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    ward = new WardDetails();
                    try {
                        ward.setId(Short.valueOf(String.valueOf(
                                request.getParameter("ward"))));
                    } catch (Exception e) {
                        ward = null;
                    }

                    try {
                        location = new LocationDetails(Integer.valueOf(
                                request.getParameter("locationId")));
                    } catch (Exception e) {
                        location = new LocationDetails();
                    }
                    location.setSubCounty(subCounty);
                    location.setCounty(county);
                    location.setWard(ward);

                    try {
                        region = RegionDetail.getRegionDetail(Short.valueOf(String.valueOf(request.getParameter("region"))));
                    } catch (Exception e) {
                        region = null;
                    }

                    try {
                        personRole = PersonRoleDetail.getPersonRoleDetail(Short.valueOf(String.valueOf(request.getParameter("personRole"))));
                        if (personRole.equals(PersonRoleDetail.NATIONAL_OFFICER)
                                || personRole.equals(PersonRoleDetail.SYSTEM_ADMIN)
                                || personRole.equals(PersonRoleDetail.AGMARK_OFFICER)) {
                            location.setCounty(null);
                            location.setSubCounty(null);
                            location.setWard(null);
                            location.setRegion(null);
                        } else if (personRole.equals(PersonRoleDetail.REGIONAL_COORDINATOR)) {
                            location.setCounty(null);
                            location.setSubCounty(null);
                            location.setWard(null);
                            location.setRegion(region);
                        }
                    } catch (Exception e) {
                        personRole = null;
                    }

                    try {
                        person = new PersonDetails(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                        person = new PersonDetails();
                    }
                    person.setBusinessName(String.valueOf(
                            request.getParameter("businessName")));

                    person.setContact(contact);
                    person.setFarmerGroup(farmerGroup);
                    person.setFarmerSubGroup(farmerSubGroup);
                    person.setLocation(location);
                    person.setName(String.valueOf(request.getParameter("name")));
                    person.setNationalId(String.valueOf(
                            request.getParameter("nationalId")));
                    if (person.getBusinessName().equals("null")) {
                        person.setBusinessName(null);
                    }
                    if (person.getNationalId().equals("null")) {
                        person.setNationalId(null);
                    }
                    if (person.getName().equals("null")) {
                        person.setName(null);
                    }
                    if (contact.getEmail().equals("null")) {
                        contact.setEmail(null);
                    }
                    if (contact.getPhone().equals("null")) {
                        contact.setPhone(null);
                    }
                    if (contact.getPostalAddress().equals("null")) {
                        contact.setPostalAddress(null);
                    }
                    try {
                        person.setSex(SexDetail.getSexDetail(
                                Short.valueOf(String.valueOf(request.getParameter("sex")))));
                    } catch (Exception e) {
                        person.setSex(null);
                    }
                    try {
                        person.setYearOfBirth(Short.valueOf(request.getParameter("yearOfBirth")));
                    } catch (Exception e) {
                        person.setYearOfBirth(null);
                    }

                    /* set the person role */
                    UserAccountDetails userAccount = new UserAccountDetails();
                    userAccount.setPersonRole(personRole);

                    try {
                        personService.editPerson(person, userAccount);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/validatePassword":

                    //Retrieve the person's user account
                    LOGGER.log(Level.INFO, "Retrieving the person's user account");
                    try {
                        userAccount = userAccountService.retrieveUserAccountByPersonId(Integer.parseInt(request.getParameter("personId")));
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                        return;
                    }

                    //Create a message digest algorithm for SHA-256 hashing algorithm
                    LOGGER.log(Level.INFO, "Creating a message digest hashing algorithm object");
                    MessageDigest messageDigest;
                    try {
                        messageDigest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(getBundle().getString("error_007_01"));
                        LOGGER.log(Level.INFO, getBundle().getString("error_007_01"));
                        break;
                    }

                    //Read in the entered password
                    LOGGER.log(Level.INFO, "Reading in the entered password");
                    String oldPassword;
                    oldPassword = accessService.generateSHAPassword(messageDigest, request.getParameter("password"));

                    //Check validity of the entered password
                    LOGGER.log(Level.INFO, "Checking validity of the entered password");
                    if (oldPassword.equals(userAccount.getPassword())) {
                        //Password is valid
                        LOGGER.log(Level.INFO, "Old password is valid");
                        out.write("");
                    } else {
                        //Password is invalid
                        LOGGER.log(Level.INFO, "Old password is invalid");
                        out.write("<span class=\"btn btn-warning\">Wrong password entered!</span>");
                    }

                    return;

                case "/editUserAccount":

                    //Create a message digest algorithm for SHA-256 hashing algorithm
                    LOGGER.log(Level.INFO, "Creating a message digest hashing algorithm object");
                    try {
                        messageDigest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {

                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(getBundle().getString("error_007_01"));
                        LOGGER.log(Level.INFO, getBundle().getString("error_007_01"));
                        break;
                    }

                    //Retrieve the person's user account
                    LOGGER.log(Level.INFO, "Retrieving the person's user account");
                    try {
                        userAccount = userAccountService.retrieveUserAccountByPersonId(Integer.parseInt(request.getParameter("personId")));
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                        return;
                    }

                    //Read in the old entered password
                    LOGGER.log(Level.INFO, "Reading in the old entered password");
                    oldPassword = request.getParameter("oldPassword");
                    if (oldPassword.trim().length() != 0) {
                        oldPassword = accessService.generateSHAPassword(messageDigest, oldPassword);

                        //Check validity of the entered old password
                        LOGGER.log(Level.INFO, "Checking validity of the entered old password");
                        if (oldPassword.equals(userAccount.getPassword())) {
                            //Password is valid
                            LOGGER.log(Level.INFO, "Old password is valid");
                            String newPassword = request.getParameter("newPassword");
                            String confirmationPassword = request.getParameter("confirmPassword");
                            if (newPassword != null && newPassword.trim().length() > 0) {
                                if (newPassword.equals(confirmationPassword)) {
                                    userAccount.setPassword(accessService.generateSHAPassword(messageDigest, newPassword));
                                } else {
                                    //Password is invalid
                                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                    response.getWriter().write(getBundle().getString("confirm_password_mismatch"));
                                    LOGGER.log(Level.INFO, getBundle().getString("confirm_password_mismatch"));
                                    return;
                                }
                            } else {
                                //Password is invalid
                                LOGGER.log(Level.INFO, "Old password is unchanged");
                            }
                        } else {
                            //Password is invalid
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            response.getWriter().write(getBundle().getString("invalid_old_password"));
                            LOGGER.log(Level.INFO, getBundle().getString("invalid_old_password"));
                            return;
                        }
                    }

                    /* set the username */
                    userAccount.setUsername(request.getParameter("username"));

                    try {
                        userAccountService.editCredentials(userAccount);
                        response.getWriter().write(getBundle().getString("user_account_update_success"));
                        LOGGER.log(Level.INFO, getBundle().getString("user_account_update_success"));
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/doDeletePerson":
                    try {
                        personService.removePerson(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                    }

                    return;

                case "/head_farm":
                case "/ward_farm":
                case "/kalro_farm":
                case "/agmark_farm":
                case "/region_farm":
                case "/county_farm":
                case "/sub_county_farm":
                case "/agro_dealer_farm":

                    PersonDetails farmer;
                    Integer farmerId;
                    try {
                        farmerId = Integer.valueOf(request.getParameter("farmerId"));
                        farmer = personService.retrievePerson(farmerId);
                        session.setAttribute("farmer", farmer);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        return;
                    } catch (NumberFormatException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        LOGGER.log(Level.INFO, ("An error occurred"), ex.getMessage());
                        break;
                    }

                    AccountDetails account;
                    try {
                        account = accountService.retrieveAccount(farmerId);
                        session.setAttribute("account", account);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        return;
                    }

                    List<PersonDetails> agroDealers;
                    try {
                        agroDealers = personService.retrievePeople(PersonRoleDetail.AGRO_DEALER);
                        session.setAttribute("agroDealers", agroDealers);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    List<InputTypeDetails> inputTypes;
                    try {
                        inputTypes = inputTypeService.retrieveInputTypes();
                        session.setAttribute("inputTypes", inputTypes);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    break;

                case "/region_addPerson":
                case "/ward_addPerson":
                case "/kalro_addPerson":
                case "/agmark_addPerson":
                case "/head_addPerson":
                case "/agro_dealer_addPerson":
                    break;

                case "/county_addPerson":

                    List<SubCountyDetails> subCounties;
                    try {
                        subCounties = subCountyService.retrieveSubCounties(((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId());
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

                case "/sub_county_addPerson":

                    subCountyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        session.setAttribute("wards", wardService.retrieveWards(subCountyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                    }
                    break;

                case "/head_userProfile":
                case "/ward_userProfile":
                case "/kalro_userProfile":
                case "/agmark_userProfile":
                case "/region_userProfile":
                case "/county_userProfile":
                case "/sub_county_userProfile":
                case "/agro_dealer_userProfile":
                    break;

                case "/head_user_account":
                case "/ward_user_account":
                case "/kalro_user_account":
                case "/agmark_user_account":
                case "/region_user_account":
                case "/county_user_account":
                case "/sub_county_user_account":
                case "/agro_dealer_user_account":
                    try {
                        session.setAttribute("person", personService.retrievePerson(((PersonDetails) session.getAttribute("person")).getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                    }
                    break;

                case "/head_editPerson":
                case "/ward_editPerson":
                case "/kalro_editPerson":
                case "/agmark_editPerson":
                case "/region_editPerson":
                case "/county_editPerson":
                case "/sub_county_editPerson":
                case "/agro_dealer_editPerson":

                    break;

                case "/changeCounter":

                    short counter = Short.valueOf(request.getParameter("counter"));
                    person = (PersonDetails) session.getAttribute("person");
                    try {
                        if (person.getPersonRoleId().equals(PersonRoleDetail.COUNTY_OFFICER.getId())) {
                            countMap = personService.countCountyPeople(person.getLocation().getCounty().getId());
                        } else {
                            countMap = personService.countPeople(PersonRoleDetail.getPersonRoleDetail(counter));
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

                        out.write("<td> " + femaleYouth + "</td>");
                        out.write("<td>" + femaleElderly + "</td>");
                        out.write("<td> " + femaleTotal + "</td>");
                        out.write("<td> " + maleYouth + "</td>");
                        out.write("<td>" + maleElderly + "</td>");
                        out.write("<td> " + maleTotal + "</td>");
                        out.write("<td> " + totalPeople + "</td>");

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }
                    return;

                case "/searchFarmer":

                    List<PersonDetails> farmers;
                    try {
                        farmers = personService.searchFarmer(request.getParameter("name"), request.getParameter("nationalId"));
                        session.setAttribute("farmers", farmers);
                        session.setAttribute("farmerSearchFunction", true);
                        session.setAttribute("path", path);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    return;

                case "/searchAgroDealer":

                    try {
                        agroDealers = personService.searchAgroDealer(request.getParameter("name"), request.getParameter("nationalId"));
                        session.setAttribute("agroDealers", agroDealers);
                        session.setAttribute("agroDealerSearchFunction", true);
                        session.setAttribute("path", path);

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    } catch (Exception e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(e + "<br>");
                    }

                    return;

                default:
                    break;
            }

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

    private static final Logger LOGGER = Logger.getLogger(PersonController.class
            .getSimpleName());
    @EJB
    private UserAccountRequestsLocal userAccountService;
    @EJB
    private AccessRequestsLocal accessService;
    @EJB
    private WardRequestsLocal wardService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private AccountRequestsLocal accountService;
    @EJB
    private InputTypeRequestsLocal inputTypeService;
    @EJB
    private SubCountyRequestsLocal subCountyService;

}
