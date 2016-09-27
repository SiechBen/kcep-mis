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
@Table(name = "procurement_plan", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcurementPlan.findAll", query = "SELECT p FROM ProcurementPlan p"),
    @NamedQuery(name = "ProcurementPlan.findById", query = "SELECT p FROM ProcurementPlan p WHERE p.id = :id"),
    @NamedQuery(name = "ProcurementPlan.findByDescription", query = "SELECT p FROM ProcurementPlan p WHERE p.description = :description"),
    @NamedQuery(name = "ProcurementPlan.findByCost", query = "SELECT p FROM ProcurementPlan p WHERE p.cost = :cost"),
    @NamedQuery(name = "ProcurementPlan.findByCompleteBd", query = "SELECT p FROM ProcurementPlan p WHERE p.completeBd = :completeBd"),
    @NamedQuery(name = "ProcurementPlan.findByApprovalByIfad1", query = "SELECT p FROM ProcurementPlan p WHERE p.approvalByIfad1 = :approvalByIfad1"),
    @NamedQuery(name = "ProcurementPlan.findByApprovalBySda", query = "SELECT p FROM ProcurementPlan p WHERE p.approvalBySda = :approvalBySda"),
    @NamedQuery(name = "ProcurementPlan.findByIssueBd", query = "SELECT p FROM ProcurementPlan p WHERE p.issueBd = :issueBd"),
    @NamedQuery(name = "ProcurementPlan.findByReceiveBids", query = "SELECT p FROM ProcurementPlan p WHERE p.receiveBids = :receiveBids"),
    @NamedQuery(name = "ProcurementPlan.findByEvaluateBids", query = "SELECT p FROM ProcurementPlan p WHERE p.evaluateBids = :evaluateBids"),
    @NamedQuery(name = "ProcurementPlan.findByApprovalByIfad2", query = "SELECT p FROM ProcurementPlan p WHERE p.approvalByIfad2 = :approvalByIfad2"),
    @NamedQuery(name = "ProcurementPlan.findByAward", query = "SELECT p FROM ProcurementPlan p WHERE p.award = :award"),
    @NamedQuery(name = "ProcurementPlan.findByApprovalBySdaOrAg", query = "SELECT p FROM ProcurementPlan p WHERE p.approvalBySdaOrAg = :approvalBySdaOrAg"),
    @NamedQuery(name = "ProcurementPlan.findBySignContract", query = "SELECT p FROM ProcurementPlan p WHERE p.signContract = :signContract"),
    @NamedQuery(name = "ProcurementPlan.findByCommenceContract", query = "SELECT p FROM ProcurementPlan p WHERE p.commenceContract = :commenceContract")})
public class ProcurementPlan implements Serializable {

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
    @Column(name = "complete_bd")
    @Temporal(TemporalType.DATE)
    private Date completeBd;
    @Column(name = "approval_by_ifad1")
    @Temporal(TemporalType.DATE)
    private Date approvalByIfad1;
    @Column(name = "approval_by_sda")
    @Temporal(TemporalType.DATE)
    private Date approvalBySda;
    @Column(name = "issue_bd")
    @Temporal(TemporalType.DATE)
    private Date issueBd;
    @Column(name = "receive_bids")
    @Temporal(TemporalType.DATE)
    private Date receiveBids;
    @Column(name = "evaluate_bids")
    @Temporal(TemporalType.DATE)
    private Date evaluateBids;
    @Column(name = "approval_by_ifad2")
    @Temporal(TemporalType.DATE)
    private Date approvalByIfad2;
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

    public ProcurementPlan() {
    }

    public ProcurementPlan(Integer id) {
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
        if (!(object instanceof ProcurementPlan)) {
            return false;
        }
        ProcurementPlan other = (ProcurementPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ProcurementPlan[ id=" + id + " ]";
    }

}
