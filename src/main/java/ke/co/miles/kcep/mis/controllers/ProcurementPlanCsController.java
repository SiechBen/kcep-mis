/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
import ke.co.miles.kcep.mis.requests.procurement.method.ProcurementMethodRequestsLocal;
import ke.co.miles.kcep.mis.requests.procurement.plan.cs.ProcurementPlanCsRequestsLocal;
import ke.co.miles.kcep.mis.utilities.IfadPriorReviewDetail;
import ke.co.miles.kcep.mis.utilities.PlanVsActualDetail;
import ke.co.miles.kcep.mis.utilities.ProcurementMethodDetails;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanCsDetails;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanTypeDetail;

/**
 *
 * @author siech
 */
@WebServlet(name = "ProcurementPlanCsController",
        urlPatterns = {"/procurement_plans_cs", "/addProcurementPlanCs",
            "/doAddProcurementPlanCs", "/doEditProcurementPlanCs", "/doDeleteProcurementPlanCs"})
public class ProcurementPlanCsController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        //Get the user session
        HttpSession session = request.getSession();

        //Get the user path
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
                            urlPaths.add("/doAddProcurementPlanCs");
                            urlPaths.add("/doEditProcurementPlanCs");
                            urlPaths.add("/doDeleteProcurementPlanCs");
                            if (path.equals("/procurement_plans_cs")) {
                                path = "/head_procurement_plans_cs";
                                urlPaths.add(path);
                            } else if (path.equals("/addProcurementPlanCs")) {
                                path = "/head_addProcurementPlanCs";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddProcurementPlanCs");
                            urlPaths.add("/doEditProcurementPlanCs");
                            urlPaths.add("/doDeleteProcurementPlanCs");
                            if (path.equals("/procurement_plans_cs")) {
                                path = "/region_procurement_plans_cs";
                                urlPaths.add(path);
                            } else if (path.equals("/addProcurementPlanCs")) {
                                path = "/region_addProcurementPlanCs";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddProcurementPlanCs");
                            urlPaths.add("/doEditProcurementPlanCs");
                            urlPaths.add("/doDeleteProcurementPlanCs");
                            if (path.equals("/procurement_plans_cs")) {
                                path = "/county_procurement_plans_cs";
                                urlPaths.add(path);
                            } else if (path.equals("/addProcurementPlanCs")) {
                                path = "/county_addProcurementPlanCs";
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

            switch (path) {

                case "/head_procurement_plans_cs":
                case "/county_procurement_plans_cs":
                case "/region_procurement_plans_cs":

                    try {
                        session.setAttribute("procurementPlansCs", procurementPlanCsService.retrieveProcurementPlansCs());
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
                    session.setAttribute("procurementPlanTypes", ProcurementPlanTypeDetail.values());
                    session.setAttribute("planVsActualChoices", PlanVsActualDetail.values());

                    break;

                case "/doAddProcurementPlanCs":

                    ProcurementPlanCsDetails procurementPlanCs = new ProcurementPlanCsDetails();

                    try {
                        procurementPlanCs.setCost(new BigDecimal(request.getParameter("cost")));
                    } catch (Exception e) {
                        procurementPlanCs.setCost(null);
                    }

                    procurementPlanCs.setDescription(request.getParameter("description"));
                    if (procurementPlanCs.getDescription().equals("null")) {
                        procurementPlanCs.setDescription(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad1"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalByIfad1(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalByIfad1(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad2"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalByIfad2(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalByIfad2(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad3"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalByIfad3(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalByIfad3(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad4"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalByIfad4(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalByIfad4(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalBySda"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalBySda(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalBySda(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalBySdaOrAg"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalBySdaOrAg(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalBySdaOrAg(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("completeBd"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setCompleteBd(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setCompleteBd(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("submitTor"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setSubmitTor(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setSubmitTor(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("completeReoi"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setCompleteReoi(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setCompleteReoi(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("completeRfp"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setCompleteRfp(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setCompleteRfp(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("issueReoi"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setIssueReoi(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setIssueReoi(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("receiveEois"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setReceiveEois(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setReceiveEois(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("establishShortList"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setEstablishShortList(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setEstablishShortList(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("issueRfp"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setIssueRfp(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setIssueRfp(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("receiveProposals"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setReceiveProposals(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setReceiveProposals(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("evaluateTechnicalProposals"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setEvaluateTechnicalProposals(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setEvaluateTechnicalProposals(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("negotiate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setNegotiate(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setNegotiate(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("award"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setAward(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setAward(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("signContract"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setSignContract(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setSignContract(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("commenceContract"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setCommenceContract(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setCommenceContract(null);
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

                    ProcurementMethodDetails procurementMethod;
                    try {
                        procurementMethod = new ProcurementMethodDetails(Short.valueOf(request.getParameter("procurementMethod")));
                    } catch (Exception e) {
                        procurementMethod = null;
                    }

                    procurementPlanCs.setPlanVsActual(planVsActual);
                    procurementPlanCs.setIfadPriorReview(ifadPriorReview);
                    procurementPlanCs.setProcurementMethod(procurementMethod);
                    procurementPlanCs.setProcurementPlanType(ProcurementPlanTypeDetail.CONSULTING_SERVICES);

                    try {
                        procurementPlanCsService.addProcurementPlanCs(procurementPlanCs);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doEditProcurementPlanCs":

                    procurementPlanCs = new ProcurementPlanCsDetails();
                    try {
                        procurementPlanCs.setId(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }

                    try {
                        procurementPlanCs.setCost(new BigDecimal(request.getParameter("cost")));
                    } catch (Exception e) {
                        procurementPlanCs.setCost(null);
                    }

                    procurementPlanCs.setDescription(request.getParameter("description"));
                    if (procurementPlanCs.getDescription().equals("null")) {
                        procurementPlanCs.setDescription(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad1"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalByIfad1(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalByIfad1(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad2"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalByIfad2(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalByIfad2(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad3"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalByIfad3(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalByIfad3(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalByIfad4"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalByIfad4(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalByIfad4(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalBySda"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalBySda(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalBySda(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("approvalBySdaOrAg"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setApprovalBySdaOrAg(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setApprovalBySdaOrAg(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("completeBd"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setCompleteBd(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setCompleteBd(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("submitTor"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setSubmitTor(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setSubmitTor(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("completeReoi"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setCompleteReoi(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setCompleteReoi(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("completeRfp"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setCompleteRfp(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setCompleteRfp(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("issueReoi"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setIssueReoi(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setIssueReoi(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("receiveEois"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setReceiveEois(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setReceiveEois(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("establishShortList"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setEstablishShortList(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setEstablishShortList(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("issueRfp"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setIssueRfp(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setIssueRfp(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("receiveProposals"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setReceiveProposals(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setReceiveProposals(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("evaluateTechnicalProposals"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setEvaluateTechnicalProposals(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setEvaluateTechnicalProposals(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("negotiate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setNegotiate(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setNegotiate(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("award"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setAward(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setAward(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("signContract"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setSignContract(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setSignContract(null);
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("commenceContract"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        procurementPlanCs.setCommenceContract(date);
                    } catch (ParseException ex) {
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"), ex);
                        procurementPlanCs.setCommenceContract(null);
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
                        procurementMethod = new ProcurementMethodDetails(Short.valueOf(request.getParameter("procurementMethod")));
                    } catch (Exception e) {
                        procurementMethod = null;
                    }

                    procurementPlanCs.setPlanVsActual(planVsActual);
                    procurementPlanCs.setIfadPriorReview(ifadPriorReview);
                    procurementPlanCs.setProcurementMethod(procurementMethod);
                    procurementPlanCs.setProcurementPlanType(ProcurementPlanTypeDetail.CONSULTING_SERVICES);

                    try {
                        procurementPlanCsService.editProcurementPlanCs(procurementPlanCs);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doDeleteProcurementPlanCs":
                    try {
                        procurementPlanCsService.removeProcurementPlanCs(Integer.valueOf(request.getParameter("id")));
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

    private static final Logger LOGGER = Logger.getLogger(ProcurementPlanCsController.class.getSimpleName());

    @EJB
    private ProcurementPlanCsRequestsLocal procurementPlanCsService;
    @EJB
    private ProcurementMethodRequestsLocal procurementMethodService;

}
