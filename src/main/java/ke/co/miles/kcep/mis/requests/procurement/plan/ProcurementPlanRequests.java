/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.procurement.plan;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.IfadPriorReview;
import ke.co.miles.kcep.mis.entities.PlanVsActual;
import ke.co.miles.kcep.mis.entities.ProcurementMethod;
import ke.co.miles.kcep.mis.entities.ProcurementPlan;
import ke.co.miles.kcep.mis.entities.ProcurementPlanType;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.procurement.method.ProcurementMethodRequestsLocal;
import ke.co.miles.kcep.mis.utilities.IfadPriorReviewDetail;
import ke.co.miles.kcep.mis.utilities.PlanVsActualDetail;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanDetails;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanTypeDetail;

/**
 *
 * @author siech
 */
@Stateless
public class ProcurementPlanRequests extends EntityRequests implements ProcurementPlanRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addProcurementPlan(ProcurementPlanDetails procurementPlanDetails) throws MilesException {

        if (procurementPlanDetails == null) {
            throw new InvalidArgumentException("error_036_01");
        } else if (procurementPlanDetails.getProcurementPlanType() == null) {
            throw new InvalidArgumentException("error_036_02");
        } else if (procurementPlanDetails.getIfadPriorReview() == null) {
            throw new InvalidArgumentException("error_036_03");
        } else if (procurementPlanDetails.getPlanVsActual() == null) {
            throw new InvalidArgumentException("error_036_04");
        } else if (procurementPlanDetails.getProcurementMethod() == null) {
            throw new InvalidArgumentException("error_036_05");
        } else if (procurementPlanDetails.getDescription() != null) {
            if (procurementPlanDetails.getDescription().length() > 400) {
                throw new InvalidStateException("error_036_06");
            }
        }

        ProcurementPlan procurementPlan = new ProcurementPlan();
        procurementPlan.setCost(procurementPlanDetails.getCost());
        procurementPlan.setAward(procurementPlanDetails.getAward());
        procurementPlan.setIssueBd(procurementPlanDetails.getIssueBd());
        procurementPlan.setCompleteBd(procurementPlanDetails.getCompleteBd());
        procurementPlan.setDescription(procurementPlanDetails.getDescription());
        procurementPlan.setReceiveBids(procurementPlanDetails.getReceiveBids());
        procurementPlan.setEvaluateBids(procurementPlanDetails.getEvaluateBids());
        procurementPlan.setSignContract(procurementPlanDetails.getSignContract());
        procurementPlan.setApprovalBySda(procurementPlanDetails.getApprovalBySda());
        procurementPlan.setApprovalByIfad1(procurementPlanDetails.getApprovalByIfad1());
        procurementPlan.setApprovalByIfad2(procurementPlanDetails.getApprovalByIfad2());
        procurementPlan.setCommenceContract(procurementPlanDetails.getCommenceContract());
        procurementPlan.setApprovalBySdaOrAg(procurementPlanDetails.getApprovalBySdaOrAg());
        procurementPlan.setPlanVsActual(em.getReference(PlanVsActual.class, procurementPlanDetails.getPlanVsActual().getId()));
        procurementPlan.setIfadPriorReview(em.getReference(IfadPriorReview.class, procurementPlanDetails.getIfadPriorReview().getId()));
        procurementPlan.setProcurementMethod(em.getReference(ProcurementMethod.class, procurementPlanDetails.getProcurementMethod().getId()));
        procurementPlan.setProcurementPlanType(em.getReference(ProcurementPlanType.class, procurementPlanDetails.getProcurementPlanType().getId()));

        try {
            em.persist(procurementPlan);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return procurementPlan.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ProcurementPlanDetails> retrieveProcurementPlans() throws MilesException {
        List<ProcurementPlan> procurementPlans = new ArrayList<>();
        q = em.createNamedQuery("ProcurementPlan.findAll");
        try {
            procurementPlans = q.getResultList();
        } catch (Exception e) {
        }

        return convertProcurementPlansToProcurementPlanDetailsList(procurementPlans);
    }

    @Override
    public ProcurementPlanDetails retrieveProcurementPlan(int id) throws MilesException {
        ProcurementPlan procurementPlan;
        q = em.createNamedQuery("ProcurementPlan.findById");
        q.setParameter("id", id);
        try {
            procurementPlan = (ProcurementPlan) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertProcurementPlanToProcurementPlanDetails(procurementPlan);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editProcurementPlan(ProcurementPlanDetails procurementPlanDetails) throws MilesException {

        if (procurementPlanDetails == null) {
            throw new InvalidArgumentException("error_036_01");
        } else if (procurementPlanDetails.getId() == null) {
            throw new InvalidArgumentException("error_036_07");
        } else if (procurementPlanDetails.getProcurementPlanType() == null) {
            throw new InvalidArgumentException("error_036_02");
        } else if (procurementPlanDetails.getIfadPriorReview() == null) {
            throw new InvalidArgumentException("error_036_03");
        } else if (procurementPlanDetails.getPlanVsActual() == null) {
            throw new InvalidArgumentException("error_036_04");
        } else if (procurementPlanDetails.getProcurementMethod() == null) {
            throw new InvalidArgumentException("error_036_05");
        } else if (procurementPlanDetails.getDescription() != null) {
            if (procurementPlanDetails.getDescription().length() > 400) {
                throw new InvalidStateException("error_036_06");
            }
        }

        ProcurementPlan procurementPlan = em.find(ProcurementPlan.class, procurementPlanDetails.getId());
        procurementPlan.setId(procurementPlanDetails.getId());
        procurementPlan.setCost(procurementPlanDetails.getCost());
        procurementPlan.setAward(procurementPlanDetails.getAward());
        procurementPlan.setIssueBd(procurementPlanDetails.getIssueBd());
        procurementPlan.setCompleteBd(procurementPlanDetails.getCompleteBd());
        procurementPlan.setDescription(procurementPlanDetails.getDescription());
        procurementPlan.setReceiveBids(procurementPlanDetails.getReceiveBids());
        procurementPlan.setEvaluateBids(procurementPlanDetails.getEvaluateBids());
        procurementPlan.setSignContract(procurementPlanDetails.getSignContract());
        procurementPlan.setApprovalBySda(procurementPlanDetails.getApprovalBySda());
        procurementPlan.setApprovalByIfad1(procurementPlanDetails.getApprovalByIfad1());
        procurementPlan.setApprovalByIfad2(procurementPlanDetails.getApprovalByIfad2());
        procurementPlan.setCommenceContract(procurementPlanDetails.getCommenceContract());
        procurementPlan.setApprovalBySdaOrAg(procurementPlanDetails.getApprovalBySdaOrAg());
        procurementPlan.setPlanVsActual(em.getReference(PlanVsActual.class, procurementPlanDetails.getPlanVsActual().getId()));
        procurementPlan.setIfadPriorReview(em.getReference(IfadPriorReview.class, procurementPlanDetails.getIfadPriorReview().getId()));
        procurementPlan.setProcurementMethod(em.getReference(ProcurementMethod.class, procurementPlanDetails.getProcurementMethod().getId()));
        procurementPlan.setProcurementPlanType(em.getReference(ProcurementPlanType.class, procurementPlanDetails.getProcurementPlanType().getId()));

        try {
            em.merge(procurementPlan);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeProcurementPlan(int id) throws MilesException {
        ProcurementPlan procurementPlan = em.find(ProcurementPlan.class, id);
        try {
            em.remove(procurementPlan);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private ProcurementPlanDetails convertProcurementPlanToProcurementPlanDetails(ProcurementPlan procurementPlan) {

        ProcurementPlanDetails procurementPlanDetails = new ProcurementPlanDetails(procurementPlan.getId());
        procurementPlanDetails.setApprovalBySdaOrAg(procurementPlan.getApprovalBySdaOrAg());
        procurementPlanDetails.setCommenceContract(procurementPlan.getCommenceContract());
        procurementPlanDetails.setApprovalByIfad1(procurementPlan.getApprovalByIfad1());
        procurementPlanDetails.setApprovalByIfad2(procurementPlan.getApprovalByIfad2());
        procurementPlanDetails.setApprovalBySda(procurementPlan.getApprovalBySda());
        procurementPlanDetails.setSignContract(procurementPlan.getSignContract());
        procurementPlanDetails.setEvaluateBids(procurementPlan.getEvaluateBids());
        procurementPlanDetails.setReceiveBids(procurementPlan.getReceiveBids());
        procurementPlanDetails.setDescription(procurementPlan.getDescription());
        procurementPlanDetails.setCompleteBd(procurementPlan.getCompleteBd());
        procurementPlanDetails.setIssueBd(procurementPlan.getIssueBd());
        procurementPlanDetails.setAward(procurementPlan.getAward());
        procurementPlanDetails.setCost(procurementPlan.getCost());
        procurementPlanDetails.setPlanVsActual(PlanVsActualDetail.
                getPlanVsActualDetail(procurementPlan.getPlanVsActual().getId()));
        procurementPlanDetails.setIfadPriorReview(IfadPriorReviewDetail.
                getIfadPriorReviewDetail(procurementPlan.getIfadPriorReview().getId()));
        procurementPlanDetails.setProcurementPlanType(ProcurementPlanTypeDetail.
                getProcurementPlanTypeDetail(procurementPlan.getProcurementPlanType().getId()));
        procurementPlanDetails.setProcurementMethod(procurementMethodService.
                convertProcurementMethodToProcurementMethodDetails(procurementPlan.getProcurementMethod()));

        return procurementPlanDetails;

    }

    private List<ProcurementPlanDetails> convertProcurementPlansToProcurementPlanDetailsList(List<ProcurementPlan> procurementPlans) {

        List<ProcurementPlanDetails> procurementPlanDetailsList = new ArrayList<>();
        for (ProcurementPlan procurementPlan : procurementPlans) {
            procurementPlanDetailsList.add(convertProcurementPlanToProcurementPlanDetails(procurementPlan));
        }

        return procurementPlanDetailsList;

    }

//</editor-fold>
    @EJB
    private ProcurementMethodRequestsLocal procurementMethodService;
}
