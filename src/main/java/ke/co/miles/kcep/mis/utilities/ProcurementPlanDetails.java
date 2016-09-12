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
public class ProcurementPlanDetails implements Serializable, Comparable<ProcurementPlanDetails> {

    public ProcurementPlanDetails() {
    }

    public ProcurementPlanDetails(Integer id) {
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

    public Date getApprovalBySda() {
        return approvalBySda;
    }

    public void setApprovalBySda(Date approvalBySda) {
        this.approvalBySda = approvalBySda;
    }

    public Date getIssueBd() {
        return issueBd;
    }

    public void setIssueBd(Date issueBd) {
        this.issueBd = issueBd;
    }

    public Date getReceiveBids() {
        return receiveBids;
    }

    public void setReceiveBids(Date receiveBids) {
        this.receiveBids = receiveBids;
    }

    public Date getEvaluateBids() {
        return evaluateBids;
    }

    public void setEvaluateBids(Date evaluateBids) {
        this.evaluateBids = evaluateBids;
    }

    public Date getApprovalByIfad2() {
        return approvalByIfad2;
    }

    public void setApprovalByIfad2(Date approvalByIfad2) {
        this.approvalByIfad2 = approvalByIfad2;
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
        if (!(object instanceof ProcurementPlanDetails)) {
            return false;
        }
        ProcurementPlanDetails other = (ProcurementPlanDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ProcurementPlanDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ProcurementPlanDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;
    private IfadPriorReviewDetail ifadPriorReview;
    private PlanVsActualDetail planVsActual;
    private BigDecimal cost;
    private Date completeBd;
    private Date approvalByIfad1;
    private Date approvalBySda;
    private Date issueBd;
    private Date receiveBids;
    private Date evaluateBids;
    private Date approvalByIfad2;
    private Date award;
    private Date approvalBySdaOrAg;
    private Date signContract;
    private Date commenceContract;
    private ProcurementMethodDetails procurementMethod;
    private ProcurementPlanTypeDetail procurementPlanType;

}
