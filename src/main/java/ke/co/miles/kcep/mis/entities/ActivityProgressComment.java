package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "activity_progress_comment", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityProgressComment.findForRegionByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgressComment a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.subActivity.region.id = :regionId AND a.subActivity.county IS NULL"),
    @NamedQuery(name = "ActivityProgressComment.findForCountyByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgressComment a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.subActivity.county.id = :countyId AND a.subActivity.region IS NULL"),
    @NamedQuery(name = "ActivityProgressComment.findForHeadByFinancialYearIdAndReferenceCode", query = "SELECT a FROM ActivityProgressComment a WHERE a.subActivity.financialYear.id = :financialYearId AND a.subActivity.annualWorkplanReferenceCode = :awpbReferenceCode AND a.subActivity.county IS NULL AND a.subActivity.region IS NULL"),
    @NamedQuery(name = "ActivityProgressComment.findAll", query = "SELECT a FROM ActivityProgressComment a"),
    @NamedQuery(name = "ActivityProgressComment.findById", query = "SELECT a FROM ActivityProgressComment a WHERE a.id = :id")})
public class ActivityProgressComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "sub_activity", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubActivity subActivity;

    public ActivityProgressComment() {
    }

    public ActivityProgressComment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        if (!(object instanceof ActivityProgressComment)) {
            return false;
        }
        ActivityProgressComment other = (ActivityProgressComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.ActivityProgressComment[ id=" + id + " ]";
    }

}
