package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author siech
 */
public class ActivityProgressDetails implements Serializable, Comparable<ActivityProgressDetails> {

    public ActivityProgressDetails() {
    }

    public ActivityProgressDetails(Integer id) {
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

    public ProgressTypeDetail getProgressType() {
        return progressType;
    }

    public void setProgressType(ProgressTypeDetail progressType) {
        this.progressType = progressType;
    }

    public SubActivityDetails getSubActivity() {
        return subActivity;
    }

    public void setSubActivity(SubActivityDetails subActivity) {
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
        if (!(object instanceof ActivityProgressDetails)) {
            return false;
        }
        ActivityProgressDetails other = (ActivityProgressDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ActivityProgress[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ActivityProgressDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the quarter
     */
    public short getQuarter() {
        return quarter;
    }

    /**
     * @param quarter the quarter to set
     */
    public void setQuarter(short quarter) {
        this.quarter = quarter;
    }

    private static final long serialVersionUID = 1L;
    private BigDecimal valueAchievedOrExpense;
    private ProgressTypeDetail progressType;
    private SubActivityDetails subActivity;
    private BigDecimal targetOrBudget;
    private short quarter;
    private Integer id;

}
