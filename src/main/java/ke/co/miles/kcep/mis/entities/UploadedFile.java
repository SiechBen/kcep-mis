package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "uploaded_file", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UploadedFile.findAllByPurposeId", query = "SELECT f FROM UploadedFile f WHERE f.purpose.id = :purposeId ORDER BY f.timeUploaded DESC"),
    @NamedQuery(name = "UploadedFile.findByWardIdAndPurposeId", query = "SELECT f FROM UploadedFile f WHERE f.uploader.location.ward.id = :wardId AND f.purpose.id = :purposeId ORDER BY f.timeUploaded DESC"),
    @NamedQuery(name = "UploadedFile.findByCountyIdAndPurposeId", query = "SELECT f FROM UploadedFile f WHERE f.uploader.location.county.id = :countyId AND f.purpose.id = :purposeId ORDER BY f.timeUploaded DESC"),
    @NamedQuery(name = "UploadedFile.findByRegionIdAndPurposeId", query = "SELECT f FROM UploadedFile f WHERE f.uploader.location.county.region.id = :regionId AND f.purpose.id = :purposeId ORDER BY f.timeUploaded DESC"),
    @NamedQuery(name = "UploadedFile.findBySubCountyIdAndPurposeId", query = "SELECT f FROM UploadedFile f WHERE f.uploader.location.subCounty.id = :subCountyId AND f.purpose.id = :purposeId ORDER BY f.timeUploaded DESC"),
    @NamedQuery(name = "UploadedFile.findLatest", query = "SELECT f FROM UploadedFile f ORDER BY f.timeUploaded DESC"),
    @NamedQuery(name = "UploadedFile.findAll", query = "SELECT u FROM UploadedFile u"),
    @NamedQuery(name = "UploadedFile.findById", query = "SELECT u FROM UploadedFile u WHERE u.id = :id")})
public class UploadedFile implements Serializable {

    @Column(name = "first_row")
    private Short firstRow;

    @Column(name = "time_uploaded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeUploaded;
    @JoinColumn(name = "uploader", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person uploader;

    @Column(name = "populated")
    private Boolean populated;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "purpose", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Phenomenon purpose;

    public UploadedFile() {
    }

    public UploadedFile(Integer id) {
        this.id = id;
    }

    public UploadedFile(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Phenomenon getPurpose() {
        return purpose;
    }

    public void setPurpose(Phenomenon purpose) {
        this.purpose = purpose;
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
        if (!(object instanceof UploadedFile)) {
            return false;
        }
        UploadedFile other = (UploadedFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.UploadedFile[ id=" + id + " ]";
    }

    public Boolean getPopulated() {
        return populated;
    }

    public void setPopulated(Boolean populated) {
        this.populated = populated;
    }

    public Date getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(Date timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    public Person getUploader() {
        return uploader;
    }

    public void setUploader(Person uploader) {
        this.uploader = uploader;
    }

    public Short getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(Short firstRow) {
        this.firstRow = firstRow;
    }

}
