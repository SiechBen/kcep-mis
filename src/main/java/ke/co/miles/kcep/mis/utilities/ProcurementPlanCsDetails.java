/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author siech
 */
public class ProcurementPlanCsDetails implements Serializable, Comparable<ProcurementPlanCsDetails> {

    public ProcurementPlanCsDetails() {
    }

    public ProcurementPlanCsDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IfadPriorReviewDetail getIfadPriorReview() {
        return ifadPriorReview;
    }

    public void setIfadPriorReview(IfadPriorReviewDetail ifadPriorReview) {
        this.ifadPriorReview = ifadPriorReview;
    }

    public PlanVsActualDetail getPlanVsActual() {
        return planVsActual;
    }

    public void setPlanVsActual(PlanVsActualDetail planVsActual) {
        this.planVsActual = planVsActual;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getSubmitTor() {
        return submitTor;
    }

    public void setSubmitTor(Date submitTor) {
        this.submitTor = submitTor;
    }

    public Date getCompleteReoi() {
        return completeReoi;
    }

    public void setCompleteReoi(Date completeReoi) {
        this.completeReoi = completeReoi;
    }

    public Date getCompleteBd() {
        return completeBd;
    }

    public void setCompleteBd(Date completeBd) {
        this.completeBd = completeBd;
    }

    public Date getApprovalByIfad1() {
        return approvalByIfad1;
    }

    public void setApprovalByIfad1(Date approvalByIfad1) {
        this.approvalByIfad1 = approvalByIfad1;
    }

    public Date getIssueReoi() {
        return issueReoi;
    }

    public void setIssueReoi(Date issueReoi) {
        this.issueReoi = issueReoi;
    }

    public Date getReceiveEois() {
        return receiveEois;
    }

    public void setReceiveEois(Date receiveEois) {
        this.receiveEois = receiveEois;
    }

    public Date getEstablishShortList() {
        return establishShortList;
    }

    public void setEstablishShortList(Date establishShortList) {
        this.establishShortList = establishShortList;
    }

    public Date getCompleteRfp() {
        return completeRfp;
    }

    public void setCompleteRfp(Date completeRfp) {
        this.completeRfp = completeRfp;
    }

    public Date getApprovalByIfad2() {
        return approvalByIfad2;
    }

    public void setApprovalByIfad2(Date approvalByIfad2) {
        this.approvalByIfad2 = approvalByIfad2;
    }

    public Date getApprovalBySda() {
        return approvalBySda;
    }

    public void setApprovalBySda(Date approvalBySda) {
        this.approvalBySda = approvalBySda;
    }

    public Date getIssueRfp() {
        return issueRfp;
    }

    public void setIssueRfp(Date issueRfp) {
        this.issueRfp = issueRfp;
    }

    public Date getReceiveProposals() {
        return receiveProposals;
    }

    public void setReceiveProposals(Date receiveProposals) {
        this.receiveProposals = receiveProposals;
    }

    public Date getEvaluateTechnicalProposals() {
        return evaluateTechnicalProposals;
    }

    public void setEvaluateTechnicalProposals(Date evaluateTechnicalProposals) {
        this.evaluateTechnicalProposals = evaluateTechnicalProposals;
    }

    public Date getApprovalByIfad3() {
        return approvalByIfad3;
    }

    public void setApprovalByIfad3(Date approvalByIfad3) {
        this.approvalByIfad3 = approvalByIfad3;
    }

    public Date getNegotiate() {
        return negotiate;
    }

    public void setNegotiate(Date negotiate) {
        this.negotiate = negotiate;
    }

    public Date getApprovalByIfad4() {
        return approvalByIfad4;
    }

    public void setApprovalByIfad4(Date approvalByIfad4) {
        this.approvalByIfad4 = approvalByIfad4;
    }

    public Date getAward() {
        return award;
    }

    public void setAward(Date award) {
        this.award = award;
    }

    public Date getApprovalBySdaOrAg() {
        return approvalBySdaOrAg;
    }

    public void setApprovalBySdaOrAg(Date approvalBySdaOrAg) {
        this.approvalBySdaOrAg = approvalBySdaOrAg;
    }

    public Date getSignContract() {
        return signContract;
    }

    public void setSignContract(Date signContract) {
        this.signContract = signContract;
    }

    public Date getCommenceContract() {
        return commenceContract;
    }

    public void setCommenceContract(Date commenceContract) {
        this.commenceContract = commenceContract;
    }

    public ProcurementMethodDetails getProcurementMethod() {
        return procurementMethod;
    }

    public void setProcurementMethod(ProcurementMethodDetails procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    public ProcurementPlanTypeDetail getProcurementPlanType() {
        return procurementPlanType;
    }

    public void setProcurementPlanType(ProcurementPlanTypeDetail procurementPlanType) {
        this.procurementPlanType = procurementPlanType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProcurementPlanCsDetails)) {
            return false;
        }
        ProcurementPlanCsDetails other = (ProcurementPlanCsDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ProcurementPlanNcsDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ProcurementPlanCsDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;
    private BigDecimal cost;
    private Date submitTor;
    private Date completeReoi;
    private Date completeBd;
    private Date approvalByIfad1;
    private Date issueReoi;
    private Date receiveEois;
    private Date establishShortList;
    private Date completeRfp;
    private Date approvalByIfad2;
    private Date approvalBySda;
    private Date issueRfp;
    private Date receiveProposals;
    private Date evaluateTechnicalProposals;
    private Date approvalByIfad3;
    private Date negotiate;
    private Date approvalByIfad4;
    private Date award;
    private Date approvalBySdaOrAg;
    private Date signContract;
    private Date commenceContract;
    private PlanVsActualDetail planVsActual;
    private IfadPriorReviewDetail ifadPriorReview;
    private ProcurementMethodDetails procurementMethod;
    private ProcurementPlanTypeDetail procurementPlanType;

}
