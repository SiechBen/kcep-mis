package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "procurement_plan_cs", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcurementPlanCs.findAll", query = "SELECT p FROM ProcurementPlanCs p"),
    @NamedQuery(name = "ProcurementPlanCs.findById", query = "SELECT p FROM ProcurementPlanCs p WHERE p.id = :id"),
    @NamedQuery(name = "ProcurementPlanCs.findByDescription", query = "SELECT p FROM ProcurementPlanCs p WHERE p.description = :description"),
    @NamedQuery(name = "ProcurementPlanCs.findByCost", query = "SELECT p FROM ProcurementPlanCs p WHERE p.cost = :cost"),
    @NamedQuery(name = "ProcurementPlanCs.findBySubmitTor", query = "SELECT p FROM ProcurementPlanCs p WHERE p.submitTor = :submitTor"),
    @NamedQuery(name = "ProcurementPlanCs.findByCompleteReoi", query = "SELECT p FROM ProcurementPlanCs p WHERE p.completeReoi = :completeReoi"),
    @NamedQuery(name = "ProcurementPlanCs.findByCompleteBd", query = "SELECT p FROM ProcurementPlanCs p WHERE p.completeBd = :completeBd"),
    @NamedQuery(name = "ProcurementPlanCs.findByApprovalByIfad1", query = "SELECT p FROM ProcurementPlanCs p WHERE p.approvalByIfad1 = :approvalByIfad1"),
    @NamedQuery(name = "ProcurementPlanCs.findByIssueReoi", query = "SELECT p FROM ProcurementPlanCs p WHERE p.issueReoi = :issueReoi"),
    @NamedQuery(name = "ProcurementPlanCs.findByReceiveEois", query = "SELECT p FROM ProcurementPlanCs p WHERE p.receiveEois = :receiveEois"),
    @NamedQuery(name = "ProcurementPlanCs.findByEstablishShortList", query = "SELECT p FROM ProcurementPlanCs p WHERE p.establishShortList = :establishShortList"),
    @NamedQuery(name = "ProcurementPlanCs.findByCompleteRfp", query = "SELECT p FROM ProcurementPlanCs p WHERE p.completeRfp = :completeRfp"),
    @NamedQuery(name = "ProcurementPlanCs.findByApprovalByIfad2", query = "SELECT p FROM ProcurementPlanCs p WHERE p.approvalByIfad2 = :approvalByIfad2"),
    @NamedQuery(name = "ProcurementPlanCs.findByApprovalBySda", query = "SELECT p FROM ProcurementPlanCs p WHERE p.approvalBySda = :approvalBySda"),
    @NamedQuery(name = "ProcurementPlanCs.findByIssueRfp", query = "SELECT p FROM ProcurementPlanCs p WHERE p.issueRfp = :issueRfp"),
    @NamedQuery(name = "ProcurementPlanCs.findByReceiveProposals", query = "SELECT p FROM ProcurementPlanCs p WHERE p.receiveProposals = :receiveProposals"),
    @NamedQuery(name = "ProcurementPlanCs.findByEvaluateTechnicalProposals", query = "SELECT p FROM ProcurementPlanCs p WHERE p.evaluateTechnicalProposals = :evaluateTechnicalProposals"),
    @NamedQuery(name = "ProcurementPlanCs.findByApprovalByIfad3", query = "SELECT p FROM ProcurementPlanCs p WHERE p.approvalByIfad3 = :approvalByIfad3"),
    @NamedQuery(name = "ProcurementPlanCs.findByNegotiate", query = "SELECT p FROM ProcurementPlanCs p WHERE p.negotiate = :negotiate"),
    @NamedQuery(name = "ProcurementPlanCs.findByApprovalByIfad4", query = "SELECT p FROM ProcurementPlanCs p WHERE p.approvalByIfad4 = :approvalByIfad4"),
    @NamedQuery(name = "ProcurementPlanCs.findByAward", query = "SELECT p FROM ProcurementPlanCs p WHERE p.award = :award"),
    @NamedQuery(name = "ProcurementPlanCs.findByApprovalBySdaOrAg", query = "SELECT p FROM ProcurementPlanCs p WHERE p.approvalBySdaOrAg = :approvalBySdaOrAg"),
    @NamedQuery(name = "ProcurementPlanCs.findBySignContract", query = "SELECT p FROM ProcurementPlanCs p WHERE p.signContract = :signContract"),
    @NamedQuery(name = "ProcurementPlanCs.findByCommenceContract", query = "SELECT p FROM ProcurementPlanCs p WHERE p.commenceContract = :commenceContract")})
public class ProcurementPlanCs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "submit_tor")
    @Temporal(TemporalType.DATE)
    private Date submitTor;
    @Column(name = "complete_reoi")
    @Temporal(TemporalType.DATE)
    private Date completeReoi;
    @Column(name = "complete_bd")
    @Temporal(TemporalType.DATE)
    private Date completeBd;
    @Column(name = "approval_by_ifad1")
    @Temporal(TemporalType.DATE)
    private Date approvalByIfad1;
    @Column(name = "issue_reoi")
    @Temporal(TemporalType.DATE)
    private Date issueReoi;
    @Column(name = "receive_eois")
    @Temporal(TemporalType.DATE)
    private Date receiveEois;
    @Column(name = "establish_short_list")
    @Temporal(TemporalType.DATE)
    private Date establishShortList;
    @Column(name = "complete_rfp")
    @Temporal(TemporalType.DATE)
    private Date completeRfp;
    @Column(name = "approval_by_ifad2")
    @Temporal(TemporalType.DATE)
    private Date approvalByIfad2;
    @Column(name = "approval_by_sda")
    @Temporal(TemporalType.DATE)
    private Date approvalBySda;
    @Column(name = "issue_rfp")
    @Temporal(TemporalType.DATE)
    private Date issueRfp;
    @Column(name = "receive_proposals")
    @Temporal(TemporalType.DATE)
    private Date receiveProposals;
    @Column(name = "evaluate_technical_proposals")
    @Temporal(TemporalType.DATE)
    private Date evaluateTechnicalProposals;
    @Column(name = "approval_by_ifad3")
    @Temporal(TemporalType.DATE)
    private Date approvalByIfad3;
    @Column(name = "negotiate")
    @Temporal(TemporalType.DATE)
    private Date negotiate;
    @Column(name = "approval_by_ifad4")
    @Temporal(TemporalType.DATE)
    private Date approvalByIfad4;
    @Column(name = "award")
    @Temporal(TemporalType.DATE)
    private Date award;
    @Column(name = "approval_by_sda_or_ag")
    @Temporal(TemporalType.DATE)
    private Date approvalBySdaOrAg;
    @Column(name = "sign_contract")
    @Temporal(TemporalType.DATE)
    private Date signContract;
    @Column(name = "commence_contract")
    @Temporal(TemporalType.DATE)
    private Date commenceContract;
    @JoinColumn(name = "ifad_prior_review", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IfadPriorReview ifadPriorReview;
    @JoinColumn(name = "plan_vs_actual", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PlanVsActual planVsActual;
    @JoinColumn(name = "procurement_method", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProcurementMethod procurementMethod;
    @JoinColumn(name = "procurement_plan_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProcurementPlanType procurementPlanType;

    public ProcurementPlanCs() {
    }

    public ProcurementPlanCs(Integer id) {
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

    public IfadPriorReview getIfadPriorReview() {
        return ifadPriorReview;
    }

    public void setIfadPriorReview(IfadPriorReview ifadPriorReview) {
        this.ifadPriorReview = ifadPriorReview;
    }

    public PlanVsActual getPlanVsActual() {
        return planVsActual;
    }

    public void setPlanVsActual(PlanVsActual planVsActual) {
        this.planVsActual = planVsActual;
    }

    public ProcurementMethod getProcurementMethod() {
        return procurementMethod;
    }

    public void setProcurementMethod(ProcurementMethod procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    public ProcurementPlanType getProcurementPlanType() {
        return procurementPlanType;
    }

    public void setProcurementPlanType(ProcurementPlanType procurementPlanType) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcurementPlanCs)) {
            return false;
        }
        ProcurementPlanCs other = (ProcurementPlanCs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ProcurementPlanCs[ id=" + id + " ]";
    }

}
