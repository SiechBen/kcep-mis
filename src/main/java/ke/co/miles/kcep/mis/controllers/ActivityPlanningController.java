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
import java.util.ListIterator;
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
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.ActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.sub.SubActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.financialyear.FinancialYearRequestsLocal;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityNameDetails;
import ke.co.miles.kcep.mis.utilities.CategoryDetails;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.FinancialYearDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonTypeDetails;
import ke.co.miles.kcep.mis.utilities.RegionDetail;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityNameDetails;

/**
 *
 * @author siech
 */
@WebServlet(
        name = "PlanningController",
        urlPatterns = {
            "/addSubActivity",
            "/doAddPhenomenon",
            "/sub_activities",
            "/activity_names",
            "/addActivityName",
            "/financial_years",
            "/addFinancialYear",
            "/doAddSubActivity",
            "/doAddSubComponent",
            "/doAddActivityName",
            "/doEditSubActivity",
            "/doAddFinancialYear",
            "/sub_activity_names",
            "/addSubActivityName",
            "/doEditActivityName",
            "/flyAddActivityName",
            "/updateSubComponents",
            "/doDeleteSubActivity",
            "/doDeleteActivityName",
            "/doAddSubActivityName",
            "/flyAddSubActivityName",
            "/doEditSubActivityName",
            "/updateSubActivityNames",
            "/doDeleteSubActivityName"
        })
public class ActivityPlanningController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateSubComponents");
                            urlPaths.add("/doAddSubActivity");
                            urlPaths.add("/doAddPhenomenon");
                            urlPaths.add("/flyAddActivityName");
                            urlPaths.add("/flyAddSubActivityName");
                            urlPaths.add("/doEditSubActivity");
                            urlPaths.add("/doDeleteSubActivity");
                            urlPaths.add("/doAddActivityName");
                            urlPaths.add("/doEditActivityName");
                            urlPaths.add("/doDeleteActivityName");
                            urlPaths.add("/doEditSubActivityName");
                            urlPaths.add("/doDeleteSubActivityName");
                            urlPaths.add("/doAddFinancialYear");
                            urlPaths.add("/doAddSubActivityName");
                            urlPaths.add("/updateSubActivityNames");
                            switch (path) {
                                case "/sub_activities":
                                    path = "/head_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/sub_activity_names":
                                    path = "/head_sub_activity_names";
                                    urlPaths.add(path);
                                    break;
                                case "/activity_names":
                                    path = "/head_activity_names";
                                    urlPaths.add(path);
                                    break;
                                case "/addActivityName":
                                    path = "/head_addActivityName";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_years":
                                    path = "/head_financial_years";
                                    urlPaths.add(path);
                                    break;
                                case "/addFinancialYear":
                                    path = "/head_addFinancialYear";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/head_addSubActivity";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivityName":
                                    path = "/head_addSubActivityName";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateSubComponents");
                            urlPaths.add("/doAddSubActivity");
                            urlPaths.add("/doAddPhenomenon");
                            urlPaths.add("/flyAddActivityName");
                            urlPaths.add("/flyAddSubActivityName");
                            urlPaths.add("/doEditSubActivity");
                            urlPaths.add("/doDeleteSubActivity");
                            urlPaths.add("/doAddActivityName");
                            urlPaths.add("/doEditActivityName");
                            urlPaths.add("/doDeleteActivityName");
                            urlPaths.add("/doEditSubActivityName");
                            urlPaths.add("/doDeleteSubActivityName");
                            urlPaths.add("/doAddFinancialYear");
                            urlPaths.add("/doAddSubActivityName");
                            urlPaths.add("/updateSubActivityNames");
                            switch (path) {
                                case "/sub_activities":
                                    path = "/region_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/region_addSubActivity";
                                    urlPaths.add(path);
                                    break;
                                case "/sub_activity_names":
                                    path = "/region_sub_activity_names";
                                    urlPaths.add(path);
                                    break;
                                case "/activity_names":
                                    path = "/region_activity_names";
                                    urlPaths.add(path);
                                    break;
                                case "/addActivityName":
                                    path = "/region_addActivityName";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_years":
                                    path = "/region_financial_years";
                                    urlPaths.add(path);
                                    break;
                                case "/addFinancialYear":
                                    path = "/region_addFinancialYear";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivityName":
                                    path = "/region_addSubActivityName";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateSubComponents");
                            urlPaths.add("/doAddPhenomenon");
                            urlPaths.add("/flyAddActivityName");
                            urlPaths.add("/flyAddSubActivityName");
                            urlPaths.add("/doAddSubActivity");
                            urlPaths.add("/doEditSubActivity");
                            urlPaths.add("/doDeleteSubActivity");
                            urlPaths.add("/doAddActivityName");
                            urlPaths.add("/doEditActivityName");
                            urlPaths.add("/doDeleteActivityName");
                            urlPaths.add("/doEditSubActivityName");
                            urlPaths.add("/doDeleteSubActivityName");
                            urlPaths.add("/doAddFinancialYear");
                            urlPaths.add("/doAddSubActivityName");
                            urlPaths.add("/updateSubActivityNames");
                            switch (path) {
                                case "/sub_activities":
                                    path = "/county_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/county_addSubActivity";
                                    urlPaths.add(path);
                                    break;
                                case "/sub_activity_names":
                                    path = "/county_sub_activity_names";
                                    urlPaths.add(path);
                                    break;
                                case "/activity_names":
                                    path = "/county_activity_names";
                                    urlPaths.add(path);
                                    break;
                                case "/addActivityName":
                                    path = "/county_addActivityName";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_years":
                                    path = "/county_financial_years";
                                    urlPaths.add(path);
                                    break;
                                case "/addFinancialYear":
                                    path = "/county_addFinancialYear";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivityName":
                                    path = "/county_addSubActivityName";
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

            switch (path) {

                case "/flyAddActivityName":

                    ActivityNameDetails activityName;

                    try {
                        activityName = new ActivityNameDetails();
                        activityName.setName(request.getParameter("name"));

                        out.write(String.valueOf(activityNameService.addActivityName(activityName)));

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/flyAddSubActivityName":

                    SubActivityNameDetails subActivityName;

                    try {
                        activityName = new ActivityNameDetails(
                                Integer.valueOf(request.getParameter("activityNameId")));

                        subActivityName = new SubActivityNameDetails();
                        subActivityName.setName(request.getParameter("name"));
                        subActivityName.setActivityName(activityName);

                        out.write(String.valueOf(subActivityNameService.addSubActivityName(subActivityName)));

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException ex) {
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doAddPhenomenon":

                    CategoryDetails category;
                    PhenomenonDetails phenomenon;
                    PhenomenonTypeDetails phenomenonType;

                    try {
                        category = new CategoryDetails();
                        category.setName(request.getParameter("name"));
                        if (request.getParameter("phenomenonType").equals("Sub-component")) {
                            try {
                                category.setRelative(phenomenonService.retrievePhenomenon(Integer.valueOf(request.getParameter("relativeId"))).getCategory());
                            } catch (MilesException e) {
                                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                response.getWriter().write(getBundle().getString(e.getCode()));
                                LOGGER.log(Level.INFO, "", e);
                                return;
                            } catch (NumberFormatException e) {
                                LOGGER.log(Level.INFO, "", e);
                                return;
                            }
                        }

                        try {
                            phenomenonType = new PhenomenonTypeDetails(
                                    Integer.valueOf(request.getParameter("phenomenonTypeId")));

                        } catch (Exception e) {
                            phenomenonType = new PhenomenonTypeDetails();
                            phenomenonType.setName(request.getParameter("phenomenonType"));
                        }

                        phenomenon = new PhenomenonDetails();
                        phenomenon.setCategory(category);
                        phenomenon.setPhenomenonType(phenomenonType);

                        out.write(String.valueOf(phenomenonService.addPhenomenon(phenomenon)));

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/updateSubComponents":
                    try {
                        for (PhenomenonDetails subComponent : phenomenonService.retrieveSubComponents(Integer.parseInt(request.getParameter("componentId")))) {
                            out.write("<option value=\"" + subComponent.getId() + "\">" + subComponent.getCategory().getName() + "</option>");
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    return;

                case "/updateSubActivityNames":
                    try {
                        for (SubActivityNameDetails subActivityName1
                                : subActivityNameService.retrieveSubActivityNames(
                                        Integer.valueOf(request
                                                .getParameter("activityNameId")))) {
                            out.write("<option value=\"" + subActivityName1
                                    .getId() + "\">" + subActivityName1.getName()
                                    + "</option>");
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/head_sub_activity_names":
                case "/county_sub_activity_names":
                case "/region_sub_activity_names":
                    try {
                        int activityNameId = Integer.valueOf(
                                request.getParameter("activityNameId"));
                        session.setAttribute("activityNameId", activityNameId);
                        session.setAttribute("subActivityNames", subActivityNameService.
                                retrieveSubActivityNames(activityNameId));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException e) {
                    }
                    break;

                case "/head_financial_years":
                case "/region_financial_years":
                case "/county_financial_years":
                    try {
                        session.setAttribute("financialYears",
                                financialYearService.retrieveFinancialYears());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    break;

                case "/head_activity_names":
                case "/region_activity_names":
                case "/county_activity_names":
                    try {
                        session.setAttribute("activityNames",
                                activityNameService.retrieveActivityNames());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    break;

                case "/doAddActivityName":
                    activityName = new ActivityNameDetails();
                    activityName.setName(request.getParameter("name"));

                    try {
                        activityNameService.addActivityName(activityName);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("activityNames", activityNameService.retrieveActivityNames());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doEditActivityName":
                    try {
                        activityName = new ActivityNameDetails(Integer.valueOf(request.getParameter("id")));
                        activityName.setName(request.getParameter("name"));
                        activityNameService.editActivityName(activityName);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }
                    List<ActivityNameDetails> activityNames;

                    try {
                        activityNames = activityNameService.retrieveActivityNames();
                        session.setAttribute("activityNames", activityNames);
                        updateActivityNameTable(response, activityNames);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doDeleteActivityName":
                    try {
                        activityNameService.removeActivityName(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        activityNames = activityNameService.retrieveActivityNames();
                        session.setAttribute("activityNames", activityNames);
                        updateActivityNameTable(response, activityNames);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doAddFinancialYear":
                    FinancialYearDetails financialYear = new FinancialYearDetails();
                    financialYear.setFinancialYear(request.getParameter("financialYear"));
                    financialYear.setCurrentYear(Boolean.valueOf(request.getParameter("current")));

                    try {
                        financialYearService.addFinancialYear(financialYear);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("financialYears", financialYearService.retrieveFinancialYears());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doAddSubActivityName":
                    subActivityName = new SubActivityNameDetails();
                    subActivityName.setName(request.getParameter("name"));
                    try {
                        subActivityName.setActivityName(new ActivityNameDetails(
                                Integer.valueOf(request.getParameter("activityNameId"))));
                    } catch (Exception e) {
                        subActivityName.setActivityName(null);
                    }

                    try {
                        subActivityNameService.addSubActivityName(subActivityName);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("subActivityNames", subActivityNameService.
                                retrieveSubActivityNames(Integer.valueOf(request.getParameter("activityNameId"))));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doEditSubActivityName":
                    try {
                        subActivityName = new SubActivityNameDetails(Integer.valueOf(request.getParameter("id")));
                        subActivityName.setName(request.getParameter("name"));
                        subActivityNameService.editSubActivityName(subActivityName);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }
                    List<SubActivityNameDetails> subActivityNames;

                    try {
                        subActivityNames = subActivityNameService.retrieveSubActivityNames(Integer.valueOf(request.getParameter("activityNameId")));
                        session.setAttribute("subActivityNames", subActivityNames);
                        updateSubActivityNameTable(response, subActivityNames);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException ex) {
                    }
                    return;

                case "/doDeleteSubActivityName":
                    try {
                        subActivityNameService.removeSubActivityName(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        subActivityNames = subActivityNameService.retrieveSubActivityNames(Integer.valueOf(request.getParameter("activityNameId")));
                        session.setAttribute("subActivityNames", subActivityNames);
                        updateSubActivityNameTable(response, subActivityNames);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException ex) {
                    }
                    return;

                case "/head_sub_activities":
                case "/county_sub_activities":
                case "/region_sub_activities":
                    try {
                        session.setAttribute("expectedOutcomes", phenomenonService.retrieveExpectedOutcomes());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of expected outcomes", ex);
                        return;
                    }

                    try {
                        session.setAttribute("gfssCodes", phenomenonService.retrieveGFSSCodes());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during procurements retrieval", ex);
                        return;
                    }

                    try {
                        switch (path) {
                            case "/head_sub_activities":
                                session.setAttribute("subActivities", subActivityService.retrieveHeadSubActivities());
                                break;
                            default:
                                session.setAttribute("subActivities", subActivityService.retrieveCountySubActivities(((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getId()));
                                break;
                            case "/region_sub_activities":
                                session.setAttribute("subActivities", subActivityService.retrieveRegionSubActivities(((PersonDetails) session.getAttribute("person")).getLocation().getCounty().getRegion().getId()));
                                break;
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException ex) {
                        LOGGER.log(Level.INFO, "", ex);

                    }

                    try {
                        session.setAttribute("subActivityNames", subActivityNameService.retrieveSubActivityNames());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    try {
                        session.setAttribute("activityNames", activityNameService.retrieveActivityNames());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    try {
                        session.setAttribute("financialYears", financialYearService.retrieveFinancialYears());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    try {
                        session.setAttribute("measurementUnits", measurementUnitService.retrievePlanningMeasurementUnits());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of measurement units", ex);
                        return;
                    }

                    try {
                        session.setAttribute("responsePCUList", phenomenonService.retrieveResponsePCUList());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of list of response PCU", ex);
                        return;
                    }

                    try {
                        session.setAttribute("annualIndicators", phenomenonService.retrieveAnnualIndicators());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of annual indicators", ex);
                        return;
                    }

                    try {
                        session.setAttribute("expenditureCategories", phenomenonService.retrieveExpenditureCategories());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of expenditure categories", ex);
                        return;
                    }

                    try {
                        session.setAttribute("implementingPartners", phenomenonService.retrieveImplementingPartners());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of implementing partners", ex);
                        return;
                    }

                    List<PhenomenonDetails> components;
                    try {
                        components = phenomenonService.retrieveComponents();
                        session.setAttribute("components", components);
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of components", ex);
                        return;
                    }

                    try {
                        ListIterator<PhenomenonDetails> iterator = components.listIterator();
                        if (iterator.hasNext()) {
                            session.setAttribute("subComponents", phenomenonService.retrieveSubComponents(iterator.next().getId()));
                        }
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of sub-components", ex);
                        return;
                    }

                    try {
                        session.setAttribute("performanceIndicators", performanceIndicatorService.retrievePerformanceIndicators());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of performance indicators", ex);
                        return;
                    }

                    break;

                case "/doAddSubActivity":

                    SubActivityDetails subActivity = new SubActivityDetails();
                    subActivity.setProcurementPlan(request.getParameter("procurementPlan"));
                    subActivity.setDescription(request.getParameter("description"));
                    subActivity.setAnnualWorkplanReferenceCode(
                            request.getParameter("annualWorkplanReferenceCode"));
                    try {
                        subActivity.setTotals(new BigDecimal(request.getParameter("totals")));
                    } catch (NumberFormatException e) {
                        subActivity.setTotals(null);
                    }
                    try {
                        subActivity.setExpectedOutcome(new PhenomenonDetails(Integer.valueOf(request.getParameter("expectedOutcome"))));
                    } catch (NumberFormatException e) {
                        subActivity.setExpectedOutcome(null);
                    }
                    try {
                        subActivity.setUnitCost(new BigDecimal(request.getParameter("unitCost")));
                    } catch (NumberFormatException e) {
                        subActivity.setUnitCost(null);
                    }
                    try {
                        subActivity.setAwpbTarget(new BigDecimal(request.getParameter("awpbTarget")));
                    } catch (NumberFormatException e) {
                        subActivity.setAwpbTarget(null);
                    }
                    try {
                        subActivity.setValueAchieved(new BigDecimal(request.getParameter("valueAchieved")));
                    } catch (Exception e) {
                        subActivity.setValueAchieved(null);
                    }
                    try {
                        subActivity.setAllocatedBudget(new BigDecimal(request.getParameter("allocatedBudget")));
                    } catch (Exception e) {
                        subActivity.setAllocatedBudget(null);
                    }
                    try {
                        subActivity.setGokPercentage(new Double(request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setGokPercentage(null);
                    }
                    try {
                        subActivity.setIfadLoanPercentage(new Double(
                                request.getParameter("ifadLoanPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadLoanPercentage(null);
                    }
                    try {
                        subActivity.setIfadGrantPercentage(new Double(
                                request.getParameter("ifadGrantPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadGrantPercentage(null);
                    }
                    try {
                        subActivity.setBeneficiariesPercentage(new Double(
                                request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setBeneficiariesPercentage(null);
                    }
                    try {
                        subActivity.setEuPercentage(new Double(request.getParameter("euPercentage")));
                    } catch (Exception e) {
                        subActivity.setEuPercentage(null);
                    }
                    try {
                        subActivity.setFinancialInstitutionPercentage(new Double(
                                request.getParameter("financialInstitutionPercentage")));
                    } catch (Exception e) {
                        subActivity.setFinancialInstitutionPercentage(null);
                    }
                    try {
                        subActivity.setProgrammeTarget(new BigDecimal(
                                request.getParameter("programmeTarget")));
                    } catch (Exception e) {
                        subActivity.setProgrammeTarget(null);
                    }
                    try {
                        subActivity.setFinancialYear(new FinancialYearDetails(
                                Short.valueOf(request.getParameter("financialYear"))));
                    } catch (NumberFormatException e) {
                        subActivity.setFinancialYear(null);
                    }
                    try {
                        subActivity.setComponent(new PhenomenonDetails(Integer.valueOf(
                                request.getParameter("component"))));
                    } catch (Exception e) {
                        subActivity.setComponent(null);
                    }
                    try {
                        subActivity.setActivityName(new ActivityNameDetails(
                                Integer.valueOf(request.getParameter("activityName"))));
                    } catch (Exception e) {
                        subActivity.setActivityName(null);
                    }
                    try {
                        subActivity.setSubComponent(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("subComponent"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setResponsePcu(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("responsePcu"))));
                    } catch (Exception e) {
                        subActivity.setResponsePcu(null);
                    }
                    try {
                        subActivity.setSubActivityName(new SubActivityNameDetails(
                                Integer.valueOf(request.getParameter("subActivityName"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setExpenditureCategory(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("expenditureCategory"))));
                    } catch (Exception e) {
                        subActivity.setExpenditureCategory(null);
                    }
                    try {
                        subActivity.setImplementingPartner(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("implementingPartner"))));
                    } catch (Exception e) {
                        subActivity.setImplementingPartner(null);
                    }
                    try {
                        subActivity.setPerformanceIndicator(new PerformanceIndicatorDetails(
                                Short.valueOf(request.getParameter("performanceIndicator"))));
                    } catch (Exception e) {
                        subActivity.setPerformanceIndicator(null);
                    }
                    try {
                        subActivity.setMeasurementUnit(new MeasurementUnitDetails(
                                Short.valueOf(request.getParameter("measurementUnit"))));
                    } catch (Exception e) {
                        subActivity.setMeasurementUnit(null);
                    }
                    try {
                        subActivity.setAnnualIndicator(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("annualIndicator"))));
                    } catch (Exception e) {
                        subActivity.setMeasurementUnit(null);
                    }
                    try {
                        subActivity.setGfssCode(new PhenomenonDetails(Integer.valueOf(request.getParameter("gfssCode"))));
                    } catch (Exception e) {
                    }
                    PersonDetails accessingPerson = (PersonDetails) session.getAttribute("person");
                    try {
                        if (accessingPerson.getPersonRoleId().equals(PersonRoleDetail.COUNTY_OFFICER.getId())) {
                            subActivity.setCounty(new CountyDetails(accessingPerson.getLocation().getCounty().getId()));
                        } else if (accessingPerson.getPersonRoleId().equals(PersonRoleDetail.REGIONAL_COORDINATOR.getId())) {
                            subActivity.setRegion(RegionDetail.getRegionDetail(accessingPerson.getLocation().getCounty().getRegion().getId()));
                        }
                    } catch (Exception e) {
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("startDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setStartDate(date);
                    } catch (Exception ex) {
                        subActivity.setStartDate(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("endDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setEndDate(date);
                    } catch (Exception ex) {
                        subActivity.setEndDate(null);
                    }

                    try {
                        subActivityService.addSubActivity(subActivity);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    return;

                case "/doEditSubActivity":

                    subActivity = new SubActivityDetails();
                    try {
                        subActivity.setId(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }
                    subActivity.setProcurementPlan(request.getParameter("procurementPlan"));
                    subActivity.setDescription(request.getParameter("description"));
                    subActivity.setAnnualWorkplanReferenceCode(
                            request.getParameter("annualWorkplanReferenceCode"));
                    try {
                        subActivity.setTotals(new BigDecimal(request.getParameter("totals")));
                    } catch (NumberFormatException e) {
                        subActivity.setTotals(null);
                    }
                    try {
                        subActivity.setExpectedOutcome(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("expectedOutcome"))));
                    } catch (NumberFormatException e) {
                        subActivity.setExpectedOutcome(null);
                    }
                    try {
                        subActivity.setUnitCost(new BigDecimal(request.getParameter("unitCost")));
                    } catch (NumberFormatException e) {
                        subActivity.setUnitCost(null);
                    }
                    try {
                        subActivity.setAwpbTarget(new BigDecimal(request.getParameter("awpbTarget")));
                    } catch (NumberFormatException e) {
                        subActivity.setAwpbTarget(null);
                    }
                    try {
                        subActivity.setValueAchieved(new BigDecimal(request.getParameter("valueAchieved")));
                    } catch (Exception e) {
                        subActivity.setValueAchieved(null);
                    }
                    try {
                        subActivity.setAllocatedBudget(new BigDecimal(request.getParameter("allocatedBudget")));
                    } catch (Exception e) {
                        subActivity.setAllocatedBudget(null);
                    }
                    try {
                        subActivity.setGokPercentage(new Double(request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setGokPercentage(null);
                    }
                    try {
                        subActivity.setIfadLoanPercentage(new Double(
                                request.getParameter("ifadLoanPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadLoanPercentage(null);
                    }
                    try {
                        subActivity.setIfadGrantPercentage(new Double(
                                request.getParameter("ifadGrantPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadGrantPercentage(null);
                    }
                    try {
                        subActivity.setBeneficiariesPercentage(new Double(
                                request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setBeneficiariesPercentage(null);
                    }
                    try {
                        subActivity.setEuPercentage(new Double(request.getParameter("euPercentage")));
                    } catch (Exception e) {
                        subActivity.setEuPercentage(null);
                    }
                    try {
                        subActivity.setFinancialInstitutionPercentage(new Double(
                                request.getParameter("financialInstitutionPercentage")));
                    } catch (Exception e) {
                        subActivity.setFinancialInstitutionPercentage(null);
                    }
                    try {
                        subActivity.setProgrammeTarget(new BigDecimal(
                                request.getParameter("programmeTarget")));
                    } catch (Exception e) {
                        subActivity.setProgrammeTarget(null);
                    }
                    try {
                        subActivity.setFinancialYear(new FinancialYearDetails(
                                Short.valueOf(request.getParameter("financialYear"))));
                    } catch (NumberFormatException e) {
                        subActivity.setFinancialYear(null);
                    }
                    try {
                        subActivity.setComponent(new PhenomenonDetails(Integer.valueOf(
                                request.getParameter("component"))));
                    } catch (Exception e) {
                        subActivity.setComponent(null);
                    }
                    try {
                        subActivity.setActivityName(new ActivityNameDetails(
                                Integer.valueOf(request.getParameter("activityName"))));
                    } catch (Exception e) {
                        subActivity.setActivityName(null);
                    }
                    try {
                        subActivity.setSubComponent(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("subComponent"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setResponsePcu(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("responsePcu"))));
                    } catch (Exception e) {
                        subActivity.setResponsePcu(null);
                    }
                    try {
                        subActivity.setSubActivityName(new SubActivityNameDetails(
                                Integer.valueOf(request.getParameter("subActivityName"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setExpenditureCategory(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("expenditureCategory"))));
                    } catch (Exception e) {
                        subActivity.setExpenditureCategory(null);
                    }
                    try {
                        subActivity.setImplementingPartner(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("implementingPartner"))));
                    } catch (Exception e) {
                        subActivity.setImplementingPartner(null);
                    }
                    try {
                        subActivity.setPerformanceIndicator(new PerformanceIndicatorDetails(
                                Short.valueOf(request.getParameter("performanceIndicator"))));
                    } catch (Exception e) {
                        subActivity.setPerformanceIndicator(null);
                    }
                    try {
                        subActivity.setMeasurementUnit(new MeasurementUnitDetails(
                                Short.valueOf(request.getParameter("measurementUnit"))));
                    } catch (Exception e) {
                        subActivity.setMeasurementUnit(null);
                    }
                    try {
                        subActivity.setAnnualIndicator(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("annualIndicator"))));
                    } catch (Exception e) {
                        subActivity.setMeasurementUnit(null);
                    }
                    try {
                        subActivity.setGfssCode(new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("gfssCode"))));
                    } catch (Exception e) {
                    }
                    accessingPerson = (PersonDetails) session.getAttribute("person");
                    try {
                        if (accessingPerson.getPersonRoleId().equals(PersonRoleDetail.COUNTY_OFFICER.getId())) {
                            subActivity.setCounty(new CountyDetails(accessingPerson.getLocation().getCounty().getId()));
                        } else if (accessingPerson.getPersonRoleId().equals(PersonRoleDetail.REGIONAL_COORDINATOR.getId())) {
                            subActivity.setRegion(RegionDetail.getRegionDetail(accessingPerson.getLocation().getCounty().getRegion().getId()));
                        }
                    } catch (Exception e) {
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("startDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setStartDate(date);
                    } catch (Exception ex) {
                        subActivity.setStartDate(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("endDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setEndDate(date);
                    } catch (Exception ex) {
                        subActivity.setEndDate(null);
                    }

                    try {
                        subActivityService.editSubActivity(subActivity);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    return;

                case "/doDeleteSubActivity":
                    try {
                        subActivityService.removeSubActivity(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
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

    //<editor-fold defaultstate="collapsed" desc="Update tables">
    private void updateActivityNameTable(HttpServletResponse response, List<ActivityNameDetails> activityNames) throws IOException {
        PrintWriter out = response.getWriter();
        int index = 0;
        for (ActivityNameDetails activityName : activityNames) {
            if (index % 2 == 0) {
                out.write("<tr class=\"odd\">");
            } else {
                out.write("<tr>");
            }
            out.write(" <td>" + ++index + "</td>\n"
                    + "                                            <td class=\"pointable\" onclick=\"loadSubActivityNamesWindow(" + activityName.getId() + ")\">" + activityName.getName() + "</td>\n"
                    + "                                            <td><button onclick=\"editActivityName('" + activityName.getId() + "', '" + activityName.getName() + "')\"><span class=\"glyphicon glyphicon-pencil\"></span></button></td>\n"
                    + "                                            <td><button onclick=\"deleteActivityName('" + activityName.getId() + "')\"><span class=\"glyphicon glyphicon-trash\"></span></button></td>\n"
                    + "                                        </tr>");
        }
    }

    private void updateSubActivityNameTable(HttpServletResponse response, List<SubActivityNameDetails> subActivityNames) throws IOException {
        PrintWriter out = response.getWriter();
        int index = 0;
        for (SubActivityNameDetails subActivityName : subActivityNames) {
            if (index % 2 == 0) {
                out.write("<tr class=\"odd\">");
            } else {
                out.write("<tr>");
            }
            out.write(" <td>" + ++index + "</td>\n"
                    + "                                            <td>" + subActivityName.getName() + "</td>\n"
                    + "                                            <td><button onclick='editSubActivityName(\"" + subActivityName.getId() + "\", \"" + subActivityName.getName() + "\",\"" + subActivityName.getActivityName().getId() + "\")'><span class=\"glyphicon glyphicon-pencil\"></span></button></td>\n"
                    + "                                            <td><button onclick=\"deleteSubActivityName(" + subActivityName.getId() + "," + subActivityName.getActivityName().getId() + ")\"><span class=\"glyphicon glyphicon-trash\"></span></button></td>\n"
                    + "                                        </tr>");
        }
    }
    //</editor-fold>

    private static final Logger LOGGER = Logger.getLogger(ActivityPlanningController.class.getSimpleName());

    @EJB
    private PhenomenonRequestsLocal phenomenonService;
    @EJB
    private SubActivityRequestsLocal subActivityService;
    @EJB
    private ActivityNameRequestsLocal activityNameService;
    @EJB
    private FinancialYearRequestsLocal financialYearService;
    @EJB
    private SubActivityNameRequestsLocal subActivityNameService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;
}
