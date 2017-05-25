package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "activity_progress", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityProgress.findForRegionByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgress a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.quarter = :quarter AND a.progressType.id = :progressTypeId AND a.subActivity.region.id = :regionId AND a.subActivity.county IS NULL"),
    @NamedQuery(name = "ActivityProgress.findForCountyByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgress a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.quarter = :quarter AND a.progressType.id = :progressTypeId AND a.subActivity.county.id = :countyId AND a.subActivity.region IS NULL"),
    @NamedQuery(name = "ActivityProgress.findForHeadOrPartnerByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgress a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.quarter = :quarter AND a.progressType.id = :progressTypeId AND a.subActivity.awpbOwner.id = :awpbOwnerId"),
    @NamedQuery(name = "ActivityProgress.findAppraisalForRegionByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgress a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.quarter IS NULL AND a.progressType.id = :progressTypeId AND a.subActivity.region.id = :regionId AND a.subActivity.county IS NULL"),
    @NamedQuery(name = "ActivityProgress.findAppraisalForCountyByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgress a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.quarter IS NULL AND a.progressType.id = :progressTypeId AND a.subActivity.county.id = :countyId AND a.subActivity.region IS NULL"),
    @NamedQuery(name = "ActivityProgress.findAppraisalForHeadOrPartnerByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgress a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.quarter IS NULL AND a.progressType.id = :progressTypeId AND  a.subActivity.awpbOwner.id = :awpbOwnerId"),
    @NamedQuery(name = "ActivityProgress.findByFinancialYearId", query = "SELECT a FROM ActivityProgress a WHERE a.subActivity.financialYear.id = :financialYearId"),
    @NamedQuery(name = "ActivityProgress.findBySubActivityId", query = "SELECT a FROM ActivityProgress a WHERE a.subActivity.id = :subActivityId"),
    @NamedQuery(name = "ActivityProgress.findAll", query = "SELECT a FROM ActivityProgress a"),
    @NamedQuery(name = "ActivityProgress.findById", query = "SELECT a FROM ActivityProgress a WHERE a.id = :id"),
    @NamedQuery(name = "ActivityProgress.findByValueAchievedOrExpense", query = "SELECT a FROM ActivityProgress a WHERE a.valueAchievedOrExpense = :valueAchievedOrExpense"),
    @NamedQuery(name = "ActivityProgress.findByTargetOrBudget", query = "SELECT a FROM ActivityProgress a WHERE a.targetOrBudget = :targetOrBudget"),
    @NamedQuery(name = "ActivityProgress.findByQuarter", query = "SELECT a FROM ActivityProgress a WHERE a.quarter = :quarter")})
public class ActivityProgress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value_achieved_or_expense")
    private BigDecimal valueAchievedOrExpense;
    @Column(name = "target_or_budget")
    private BigDecimal targetOrBudget;
    @Column(name = "quarter")
    private Short quarter;
    @JoinColumn(name = "progress_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Phenomenon progressType;
    @JoinColumn(name = "sub_activity", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubActivity subActivity;

    public ActivityProgress() {
    }

    public ActivityProgress(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValueAchievedOrExpense() {
        return valueAchievedOrExpense;
    }

    public void setValueAchievedOrExpense(BigDecimal valueAchievedOrExpense) {
        this.valueAchievedOrExpense = valueAchievedOrExpense;
    }

    public BigDecimal getTargetOrBudget() {
        return targetOrBudget;
    }

    public void setTargetOrBudget(BigDecimal targetOrBudget) {
        this.targetOrBudget = targetOrBudget;
    }

    public Short getQuarter() {
        return quarter;
    }

    public void setQuarter(Short quarter) {
        this.quarter = quarter;
    }

    public Phenomenon getProgressType() {
        return progressType;
    }

    public void setProgressType(Phenomenon progressType) {
        this.progressType = progressType;
    }

    public SubActivity getSubActivity() {
        return subActivity;
    }

    public void setSubActivity(SubActivity subActivity) {
        this.subActivity = subActivity;
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
        if (!(object instanceof ActivityProgress)) {
            return false;
        }
        ActivityProgress other = (ActivityProgress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ActivityProgress[ id=" + id + " ]";
    }

}
