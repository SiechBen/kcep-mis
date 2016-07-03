/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.procurement.plan.cs;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.IfadPriorReview;
import ke.co.miles.kcep.mis.entities.PlanVsActual;
import ke.co.miles.kcep.mis.entities.ProcurementMethod;
import ke.co.miles.kcep.mis.entities.ProcurementPlanCs;
import ke.co.miles.kcep.mis.entities.ProcurementPlanType;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.procurement.method.ProcurementMethodRequestsLocal;
import ke.co.miles.kcep.mis.utilities.IfadPriorReviewDetail;
import ke.co.miles.kcep.mis.utilities.PlanVsActualDetail;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanCsDetails;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanTypeDetail;

/**
 *
 * @author siech
 */
@Stateless
public class ProcurementPlanCsRequests extends EntityRequests implements ProcurementPlanCsRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addProcurementPlanCs(ProcurementPlanCsDetails procurementPlanCsDetails) throws MilesException {

        if (procurementPlanCsDetails == null) {
            throw new InvalidArgumentException("error_037_01");
        } else if (procurementPlanCsDetails.getProcurementPlanType() == null) {
            throw new InvalidArgumentException("error_037_02");
        } else if (procurementPlanCsDetails.getIfadPriorReview() == null) {
            throw new InvalidArgumentException("error_037_03");
        } else if (procurementPlanCsDetails.getPlanVsActual() == null) {
            throw new InvalidArgumentException("error_037_04");
        } else if (procurementPlanCsDetails.getProcurementMethod() == null) {
            throw new InvalidArgumentException("error_037_05");
        } else if (procurementPlanCsDetails.getDescription() != null) {
            if (procurementPlanCsDetails.getDescription().length() > 400) {
                throw new InvalidStateException("error_037_06");
            }
        }

        ProcurementPlanCs procurementPlanCs = new ProcurementPlanCs();
        procurementPlanCs.setCost(procurementPlanCsDetails.getCost());
        procurementPlanCs.setAward(procurementPlanCsDetails.getAward());
        procurementPlanCs.setIssueRfp(procurementPlanCsDetails.getIssueRfp());
        procurementPlanCs.setNegotiate(procurementPlanCsDetails.getNegotiate());
        procurementPlanCs.setIssueReoi(procurementPlanCsDetails.getIssueReoi());
        procurementPlanCs.setCompleteBd(procurementPlanCsDetails.getCompleteBd());
        procurementPlanCs.setDescription(procurementPlanCsDetails.getDescription());
        procurementPlanCs.setReceiveEois(procurementPlanCsDetails.getReceiveEois());
        procurementPlanCs.setCompleteRfp(procurementPlanCsDetails.getCompleteRfp());
        procurementPlanCs.setSignContract(procurementPlanCsDetails.getSignContract());
        procurementPlanCs.setCompleteReoi(procurementPlanCsDetails.getCompleteReoi());
        procurementPlanCs.setApprovalBySda(procurementPlanCsDetails.getApprovalBySda());
        procurementPlanCs.setApprovalByIfad1(procurementPlanCsDetails.getApprovalByIfad1());
        procurementPlanCs.setApprovalByIfad2(procurementPlanCsDetails.getApprovalByIfad2());
        procurementPlanCs.setApprovalByIfad3(procurementPlanCsDetails.getApprovalByIfad3());
        procurementPlanCs.setApprovalByIfad4(procurementPlanCsDetails.getApprovalByIfad4());
        procurementPlanCs.setReceiveProposals(procurementPlanCsDetails.getReceiveProposals());
        procurementPlanCs.setEstablishShortList(procurementPlanCsDetails.getEstablishShortList());
        procurementPlanCs.setCommenceContract(procurementPlanCsDetails.getCommenceContract());
        procurementPlanCs.setApprovalBySdaOrAg(procurementPlanCsDetails.getApprovalBySdaOrAg());
        procurementPlanCs.setEvaluateTechnicalProposals(procurementPlanCsDetails.getEvaluateTechnicalProposals());
        procurementPlanCs.setPlanVsActual(em.find(PlanVsActual.class, procurementPlanCsDetails.getPlanVsActual().getId()));
        procurementPlanCs.setIfadPriorReview(em.find(IfadPriorReview.class, procurementPlanCsDetails.getIfadPriorReview().getId()));
        procurementPlanCs.setProcurementMethod(em.find(ProcurementMethod.class, procurementPlanCsDetails.getProcurementMethod().getId()));
        procurementPlanCs.setProcurementPlanType(em.find(ProcurementPlanType.class, procurementPlanCsDetails.getProcurementPlanType().getId()));

        try {
            em.persist(procurementPlanCs);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return procurementPlanCs.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ProcurementPlanCsDetails> retrieveProcurementPlansCs() throws MilesException {
        List<ProcurementPlanCs> procurementPlanCss = new ArrayList<>();
        q = em.createNamedQuery("ProcurementPlanCs.findAll");
        try {
            procurementPlanCss = q.getResultList();
        } catch (Exception e) {
        }

        return convertProcurementPlanCssToProcurementPlanCsDetailsList(procurementPlanCss);
    }

    @Override
    public ProcurementPlanCsDetails retrieveProcurementPlanCs(int id) throws MilesException {
        ProcurementPlanCs procurementPlanCs;
        q = em.createNamedQuery("ProcurementPlanCs.findById");
        q.setParameter("id", id);
        try {
            procurementPlanCs = (ProcurementPlanCs) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertProcurementPlanCsToProcurementPlanCsDetails(procurementPlanCs);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editProcurementPlanCs(ProcurementPlanCsDetails procurementPlanCsDetails) throws MilesException {

        if (procurementPlanCsDetails == null) {
            throw new InvalidArgumentException("error_037_01");
        } else if (procurementPlanCsDetails.getId() == null) {
            throw new InvalidArgumentException("error_037_07");
        } else if (procurementPlanCsDetails.getProcurementPlanType() == null) {
            throw new InvalidArgumentException("error_037_02");
        } else if (procurementPlanCsDetails.getIfadPriorReview() == null) {
            throw new InvalidArgumentException("error_037_03");
        } else if (procurementPlanCsDetails.getPlanVsActual() == null) {
            throw new InvalidArgumentException("error_037_04");
        } else if (procurementPlanCsDetails.getProcurementMethod() == null) {
            throw new InvalidArgumentException("error_037_05");
        } else if (procurementPlanCsDetails.getDescription() != null) {
            if (procurementPlanCsDetails.getDescription().length() > 400) {
                throw new InvalidStateException("error_037_06");
            }
        }

        ProcurementPlanCs procurementPlanCs = em.find(ProcurementPlanCs.class, procurementPlanCsDetails.getId());
        procurementPlanCs.setId(procurementPlanCsDetails.getId());
        procurementPlanCs.setCost(procurementPlanCsDetails.getCost());
        procurementPlanCs.setAward(procurementPlanCsDetails.getAward());
        procurementPlanCs.setIssueRfp(procurementPlanCsDetails.getIssueRfp());
        procurementPlanCs.setNegotiate(procurementPlanCsDetails.getNegotiate());
        procurementPlanCs.setIssueReoi(procurementPlanCsDetails.getIssueReoi());
        procurementPlanCs.setCompleteBd(procurementPlanCsDetails.getCompleteBd());
        procurementPlanCs.setDescription(procurementPlanCsDetails.getDescription());
        procurementPlanCs.setReceiveEois(procurementPlanCsDetails.getReceiveEois());
        procurementPlanCs.setCompleteRfp(procurementPlanCsDetails.getCompleteRfp());
        procurementPlanCs.setSignContract(procurementPlanCsDetails.getSignContract());
        procurementPlanCs.setCompleteReoi(procurementPlanCsDetails.getCompleteReoi());
        procurementPlanCs.setApprovalBySda(procurementPlanCsDetails.getApprovalBySda());
        procurementPlanCs.setApprovalByIfad1(procurementPlanCsDetails.getApprovalByIfad1());
        procurementPlanCs.setApprovalByIfad2(procurementPlanCsDetails.getApprovalByIfad2());
        procurementPlanCs.setApprovalByIfad3(procurementPlanCsDetails.getApprovalByIfad3());
        procurementPlanCs.setApprovalByIfad4(procurementPlanCsDetails.getApprovalByIfad4());
        procurementPlanCs.setReceiveProposals(procurementPlanCsDetails.getReceiveProposals());
        procurementPlanCs.setEstablishShortList(procurementPlanCsDetails.getEstablishShortList());
        procurementPlanCs.setCommenceContract(procurementPlanCsDetails.getCommenceContract());
        procurementPlanCs.setApprovalBySdaOrAg(procurementPlanCsDetails.getApprovalBySdaOrAg());
        procurementPlanCs.setEvaluateTechnicalProposals(procurementPlanCsDetails.getEvaluateTechnicalProposals());
        procurementPlanCs.setPlanVsActual(em.find(PlanVsActual.class, procurementPlanCsDetails.getPlanVsActual().getId()));
        procurementPlanCs.setIfadPriorReview(em.find(IfadPriorReview.class, procurementPlanCsDetails.getIfadPriorReview().getId()));
        procurementPlanCs.setProcurementMethod(em.find(ProcurementMethod.class, procurementPlanCsDetails.getProcurementMethod().getId()));
        procurementPlanCs.setProcurementPlanType(em.find(ProcurementPlanType.class, procurementPlanCsDetails.getProcurementPlanType().getId()));
        try {
            em.merge(procurementPlanCs);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeProcurementPlanCs(int id) throws MilesException {
        ProcurementPlanCs procurementPlanCs = em.find(ProcurementPlanCs.class, id);
        try {
            em.remove(procurementPlanCs);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private ProcurementPlanCsDetails convertProcurementPlanCsToProcurementPlanCsDetails(ProcurementPlanCs procurementPlanCs) {

        ProcurementPlanCsDetails procurementPlanCsDetails = new ProcurementPlanCsDetails(procurementPlanCs.getId());
        procurementPlanCsDetails.setEvaluateTechnicalProposals(procurementPlanCs.getEvaluateTechnicalProposals());
        procurementPlanCsDetails.setApprovalBySdaOrAg(procurementPlanCs.getApprovalBySdaOrAg());
        procurementPlanCsDetails.setCommenceContract(procurementPlanCs.getCommenceContract());
        procurementPlanCsDetails.setEstablishShortList(procurementPlanCs.getEstablishShortList());
        procurementPlanCsDetails.setReceiveProposals(procurementPlanCs.getReceiveProposals());
        procurementPlanCsDetails.setApprovalByIfad1(procurementPlanCs.getApprovalByIfad1());
        procurementPlanCsDetails.setApprovalByIfad2(procurementPlanCs.getApprovalByIfad2());
        procurementPlanCsDetails.setApprovalByIfad3(procurementPlanCs.getApprovalByIfad3());
        procurementPlanCsDetails.setApprovalByIfad4(procurementPlanCs.getApprovalByIfad4());
        procurementPlanCsDetails.setApprovalBySda(procurementPlanCs.getApprovalBySda());
        procurementPlanCsDetails.setCompleteReoi(procurementPlanCs.getCompleteReoi());
        procurementPlanCsDetails.setSignContract(procurementPlanCs.getSignContract());
        procurementPlanCsDetails.setCompleteRfp(procurementPlanCs.getCompleteRfp());
        procurementPlanCsDetails.setDescription(procurementPlanCs.getDescription());
        procurementPlanCsDetails.setCompleteBd(procurementPlanCs.getCompleteBd());
        procurementPlanCsDetails.setReceiveEois(procurementPlanCs.getReceiveEois());
        procurementPlanCsDetails.setSubmitTor(procurementPlanCs.getSubmitTor());
        procurementPlanCsDetails.setIssueReoi(procurementPlanCs.getIssueReoi());
        procurementPlanCsDetails.setNegotiate(procurementPlanCs.getNegotiate());
        procurementPlanCsDetails.setIssueRfp(procurementPlanCs.getIssueRfp());
        procurementPlanCsDetails.setAward(procurementPlanCs.getAward());
        procurementPlanCsDetails.setCost(procurementPlanCs.getCost());
        procurementPlanCsDetails.setPlanVsActual(PlanVsActualDetail.
                getPlanVsActualDetail(procurementPlanCs.getPlanVsActual().getId()));
        procurementPlanCsDetails.setIfadPriorReview(IfadPriorReviewDetail.
                getIfadPriorReviewDetail(procurementPlanCs.getIfadPriorReview().getId()));
        procurementPlanCsDetails.setProcurementPlanType(ProcurementPlanTypeDetail.
                getProcurementPlanTypeDetail(procurementPlanCs.getProcurementPlanType().getId()));
        procurementPlanCsDetails.setProcurementMethod(procurementMethodService.
                convertProcurementMethodToProcurementMethodDetails(procurementPlanCs.getProcurementMethod()));

        return procurementPlanCsDetails;

    }

    private List<ProcurementPlanCsDetails> convertProcurementPlanCssToProcurementPlanCsDetailsList(List<ProcurementPlanCs> procurementPlanCss) {

        List<ProcurementPlanCsDetails> procurementPlanCsDetailsList = new ArrayList<>();
        for (ProcurementPlanCs procurementPlanCs : procurementPlanCss) {
            procurementPlanCsDetailsList.add(convertProcurementPlanCsToProcurementPlanCsDetails(procurementPlanCs));
        }

        return procurementPlanCsDetailsList;

    }

//</editor-fold>
    @EJB
    private ProcurementMethodRequestsLocal procurementMethodService;

}
