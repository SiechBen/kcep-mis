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
import ke.co.miles.kcep.mis.requests.procurement.method.ProcurementMethodRequestsLocal;
import ke.co.miles.kcep.mis.requests.procurement.plan.ProcurementPlanRequestsLocal;
import ke.co.miles.kcep.mis.utilities.IfadPriorReviewDetail;
import ke.co.miles.kcep.mis.utilities.PlanVsActualDetail;
import ke.co.miles.kcep.mis.utilities.ProcurementMethodDetails;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanDetails;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanTypeDetail;

/**
 *
 * @author siech
 */
@WebServlet(name = "ProcurementPlanController",
        urlPatterns = {"/procurement_plans_ncs", "/procurement_plans_goods", "/addProcurementPlan",
            "/doAddProcurementPlan", "/doEditProcurementPlan", "/doDeleteProcurementPlan"})
public class ProcurementPlanController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
                            urlPaths.add("/doAddProcurementPlan");
                            urlPaths.add("/doEditProcurementPlan");
                            urlPaths.add("/doDeleteProcurementPlan");
                            switch (path) {
                                case "/procurement_plans_ncs":
                                    path = "/head_procurement_plans_ncs";
                                    urlPaths.add(path);
                                    break;
                                case "/procurement_plans_goods":
                                    path = "/head_procurement_plans_goods";
                                    urlPaths.add(path);
                                    break;
                                case "/addProcurementPlan":
                                    path = "/head_addProcurementPlan";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddProcurementPlan");
                            urlPaths.add("/doEditProcurementPlan");
                            switch (path) {
                                case "/procurement_plans_ncs":
                                    path = "/head_procurement_plans_ncs";
                                    urlPaths.add(path);
                                    break;
                                case "/procurement_plans_goods":
                                    path = "/head_procurement_plans_goods";
                                    urlPaths.add(path);
                                    break;
                                case "/addProcurementPlan":
                                    path = "/head_addProcurementPlan";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddProcurementPlan");
                            urlPaths.add("/doEditProcurementPlan");
                            switch (path) {
                                case "/procurement_plans_ncs":
                                    path = "/region_procurement_plans_ncs";
                                    urlPaths.add(path);
                                    break;
                                case "/procurement_plans_goods":
                                    path = "/region_procurement_plans_goods";
                                    urlPaths.add(path);
                                    break;
                                case "/addProcurementPlan":
                                    path = "/region_addProcurementPlan";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddProcurementPlan");
                            urlPaths.add("/doEditProcurementPlan");
                            switch (path) {
                                case "/procurement_plans_ncs":
                                    path = "/county_procurement_plans_ncs";
                                    urlPaths.add(path);
                                    break;
                                case "/procurement_plans_goods":
                                    path = "/county_procurement_plans_goods";
                                    urlPaths.add(path);
                                    break;
                                case "/addProcurementPlan":
                                    path = "/county_addProcurementPlan";
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

                case "/head_procurement_plans_ncs":
                case "/county_procurement_plans_ncs":
                case "/region_procurement_plans_ncs":

                    try {
                        session.setAttribute("procurementPlans", procurementPlanService.retrieveProcurementPlansNcs());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during procurement plans retrieval", ex);
                        return;
                    }

                    try {
                        session.setAttribute("procurementMethods", procurementMethodService.retrieveProcurementMethods());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of procurement methods", ex);
                        return;
                    }

                    session.setAttribute("ifadPriorReviewChoices", IfadPriorReviewDetail.values());
                    List<ProcurementPlanTypeDetail> procurementPlanTypes = new ArrayList<>();
                    procurementPlanTypes.add(ProcurementPlanTypeDetail.GOODS);
                    procurementPlanTypes.add(ProcurementPlanTypeDetail.NON_CONSULTING_SERVICES);
                    session.setAttribute("procurementPlanTypes", procurementPlanTypes);
                    session.setAttribute("planVsActualChoices", PlanVsActualDetail.values());

                    break;

                case "/head_procurement_plans_goods":
                case "/county_procurement_plans_goods":
                case "/region_procurement_plans_goods":

                    try {
                        session.setAttribute("procurementPlans", procurementPlanService.retrieveProcurementPlansGoods());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during procurement plans retrieval", ex);
                        return;
                    }

                    try {
                        session.setAttribute("procurementMethods", procurementMethodService.retrieveProcurementMethods());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of procurement methods", ex);
                        return;
                    }

                    session.setAttribute("ifadPriorReviewChoices", IfadPriorReviewDetail.values());
                    procurementPlanTypes = new ArrayList<>();
                    procurementPlanTypes.add(ProcurementPlanTypeDetail.GOODS);
                    procurementPlanTypes.add(ProcurementPlanTypeDetail.NON_CONSULTING_SERVICES);
                    session.setAttribute("procurementPlanTypes", procurementPlanTypes);
                    session.setAttribute("planVsActualChoices", PlanVsActualDetail.values());

                    break;

                case "/doAddProcurementPlan":

                    ProcurementPlanDetails procurementPlan = new ProcurementPlanDetails();

                    try {
                        procurementPlan.setCost(new BigDecimal(request.getParameter("cost")));
                    } catch (Exception e) {
                        procurementPlan.setCost(null);
                    }

                    procurementPlan.setDescription(request.getParameter("description"));
                    if (procurementPlan.getDescription().equals("null")) {
                        procurementPlan.setDescription(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad1"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setApprovalByIfad1(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setApprovalByIfad1(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad2"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setApprovalByIfad2(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setApprovalByIfad2(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalBySda"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setApprovalBySda(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setApprovalBySda(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalBySdaOrAg"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setApprovalBySdaOrAg(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setApprovalBySdaOrAg(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("completeBd"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setCompleteBd(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setCompleteBd(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("issueBd"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setIssueBd(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setIssueBd(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("receiveBids"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setReceiveBids(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setReceiveBids(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("evaluateBids"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setEvaluateBids(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setEvaluateBids(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("award"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setAward(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setAward(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("signContract"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setSignContract(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setSignContract(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("commenceContract"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setCommenceContract(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setCommenceContract(null);
                    }

                    PlanVsActualDetail planVsActual;
                    try {
                        planVsActual = PlanVsActualDetail.getPlanVsActualDetail(Short
                                .valueOf(request.getParameter("planVsActual")));
                    } catch (Exception e) {
                        planVsActual = null;
                    }

                    IfadPriorReviewDetail ifadPriorReview;
                    try {
                        ifadPriorReview = IfadPriorReviewDetail.getIfadPriorReviewDetail(Short.valueOf(request.getParameter("ifadPriorReview")));
                    } catch (Exception e) {
                        ifadPriorReview = null;
                    }

                    ProcurementPlanTypeDetail procurementPlanType;
                    try {
                        procurementPlanType = ProcurementPlanTypeDetail.getProcurementPlanTypeDetail(Short.valueOf(request.getParameter("procurementPlanType")));
                    } catch (Exception e) {
                        procurementPlanType = null;
                    }

                    ProcurementMethodDetails procurementMethod;
                    try {
                        procurementMethod = new ProcurementMethodDetails(Short.valueOf(request.getParameter("procurementMethod")));
                    } catch (Exception e) {
                        procurementMethod = null;
                    }

                    procurementPlan.setPlanVsActual(planVsActual);
                    procurementPlan.setIfadPriorReview(ifadPriorReview);
                    procurementPlan.setProcurementMethod(procurementMethod);
                    procurementPlan.setProcurementPlanType(procurementPlanType);

                    try {
                        procurementPlanService.addProcurementPlan(procurementPlan);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doEditProcurementPlan":

                    // ProcurementPlanDetails procurementPlan = new ProcurementPlanDetails();
                    procurementPlan = new ProcurementPlanDetails();
                    try {
                        procurementPlan.setId(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }

                    try {
                        procurementPlan.setCost(new BigDecimal(request.getParameter("cost")));
                    } catch (Exception e) {
                        procurementPlan.setCost(null);
                    }

                    procurementPlan.setDescription(request.getParameter("description"));
                    if (procurementPlan.getDescription().equals("null")) {
                        procurementPlan.setDescription(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad1"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setApprovalByIfad1(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setApprovalByIfad1(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad2"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setApprovalByIfad2(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setApprovalByIfad2(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalBySda"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setApprovalBySda(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setApprovalBySda(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalBySdaOrAg"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setApprovalBySdaOrAg(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setApprovalBySdaOrAg(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("completeBd"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setCompleteBd(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setCompleteBd(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("issueBd"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setIssueBd(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setIssueBd(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("receiveBids"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setReceiveBids(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setReceiveBids(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("evaluateBids"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setEvaluateBids(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setEvaluateBids(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("award"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setAward(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setAward(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("signContract"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setSignContract(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setSignContract(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("commenceContract"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlan.setCommenceContract(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlan.setCommenceContract(null);
                    }

                    try {
                        planVsActual = PlanVsActualDetail.getPlanVsActualDetail(Short
                                .valueOf(request.getParameter("planVsActual")));
                    } catch (Exception e) {
                        planVsActual = null;
                    }

                    try {
                        ifadPriorReview = IfadPriorReviewDetail.getIfadPriorReviewDetail(Short.valueOf(request.getParameter("ifadPriorReview")));
                    } catch (Exception e) {
                        ifadPriorReview = null;
                    }

                    try {
                        procurementPlanType = ProcurementPlanTypeDetail.getProcurementPlanTypeDetail(Short.valueOf(request.getParameter("procurementPlanType")));
                    } catch (Exception e) {
                        procurementPlanType = null;
                    }

                    try {
                        procurementMethod = new ProcurementMethodDetails(Short.valueOf(request.getParameter("procurementMethod")));
                    } catch (Exception e) {
                        procurementMethod = null;
                    }

                    procurementPlan.setPlanVsActual(planVsActual);
                    procurementPlan.setIfadPriorReview(ifadPriorReview);
                    procurementPlan.setProcurementMethod(procurementMethod);
                    procurementPlan.setProcurementPlanType(procurementPlanType);

                    try {
                        procurementPlanService.editProcurementPlan(procurementPlan);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doDeleteProcurementPlan":
                    try {
                        procurementPlanService.removeProcurementPlan(Integer.valueOf(request.getParameter("id")));
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

    private static final Logger LOGGER = Logger.getLogger(ProcurementPlanController.class.getSimpleName());

    @EJB
    private ProcurementPlanRequestsLocal procurementPlanService;
    @EJB
    private ProcurementMethodRequestsLocal procurementMethodService;

}
