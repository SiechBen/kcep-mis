package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "goal_report", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GoalReport.findAll", query = "SELECT g FROM GoalReport g"),
    @NamedQuery(name = "GoalReport.findById", query = "SELECT g FROM GoalReport g WHERE g.id = :id"),
    @NamedQuery(name = "GoalReport.findByNumber", query = "SELECT g FROM GoalReport g WHERE g.number = :number"),
    @NamedQuery(name = "GoalReport.findByBaseline", query = "SELECT g FROM GoalReport g WHERE g.baseline = :baseline"),
    @NamedQuery(name = "GoalReport.findByMidTermReview", query = "SELECT g FROM GoalReport g WHERE g.midTermReview = :midTermReview"),
    @NamedQuery(name = "GoalReport.findByImpactAssessment", query = "SELECT g FROM GoalReport g WHERE g.impactAssessment = :impactAssessment"),
    @NamedQuery(name = "GoalReport.findByEndTermEvaluation", query = "SELECT g FROM GoalReport g WHERE g.endTermEvaluation = :endTermEvaluation"),
    @NamedQuery(name = "GoalReport.findByTarget", query = "SELECT g FROM GoalReport g WHERE g.target = :target")})
public class GoalReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "number")
    private String number;
    @Size(max = 45)
    @Column(name = "baseline")
    private String baseline;
    @Size(max = 45)
    @Column(name = "mid_term_review")
    private String midTermReview;
    @Column(name = "impact_assessment")
    private Integer impactAssessment;
    @Size(max = 45)
    @Column(name = "end_term_evaluation")
    private String endTermEvaluation;
    @Size(max = 45)
    @Column(name = "target")
    private String target;

    public GoalReport() {
    }

    public GoalReport(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getMidTermReview() {
        return midTermReview;
    }

    public void setMidTermReview(String midTermReview) {
        this.midTermReview = midTermReview;
    }

    public Integer getImpactAssessment() {
        return impactAssessment;
    }

    public void setImpactAssessment(Integer impactAssessment) {
        this.impactAssessment = impactAssessment;
    }

    public String getEndTermEvaluation() {
        return endTermEvaluation;
    }

    public void setEndTermEvaluation(String endTermEvaluation) {
        this.endTermEvaluation = endTermEvaluation;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
        if (!(object instanceof GoalReport)) {
            return false;
        }
        GoalReport other = (GoalReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.GoalReport[ id=" + id + " ]";
    }

}
