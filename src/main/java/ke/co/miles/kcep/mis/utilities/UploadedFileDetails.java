package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author siech
 */
public class UploadedFileDetails implements Comparable<UploadedFileDetails>, Serializable {

    private static final long serialVersionUID = 1L;

    public UploadedFileDetails() {
    }

    public UploadedFileDetails(Integer id) {
        this.id = id;
    }

    public UploadedFileDetails(Integer id, String name) {
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

    public UploadedFileTypeDetail getPurpose() {
        return purpose;
    }

    public void setPurpose(UploadedFileTypeDetail purpose) {
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
        if (!(object instanceof UploadedFileDetails)) {
            return false;
        }
        UploadedFileDetails other = (UploadedFileDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.details.UploadedFileDetails[ id=" + id + " ]";
    }

    public Boolean getPopulated() {
        return populated;
    }

    public void setPopulated(Boolean populated) {
        this.populated = populated;
    }

    @Override
    public int compareTo(UploadedFileDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the timeUploaded
     */
    public Date getTimeUploaded() {
        return timeUploaded;
    }

    /**
     * @param timeUploaded the timeUploaded to set
     */
    public void setTimeUploaded(Date timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    /**
     * @return the uploader
     */
    public PersonDetails getUploader() {
        return uploader;
    }

    /**
     * @param uploader the uploader to set
     */
    public void setUploader(PersonDetails uploader) {
        this.uploader = uploader;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the firstRow
     */
    public Short getFirstRow() {
        return firstRow;
    }

    /**
     * @param firstRow the firstRow to set
     */
    public void setFirstRow(Short firstRow) {
        this.firstRow = firstRow;
    }

    private Boolean populated;
    private Integer id;
    private String name;
    private String fileName;
    private Short firstRow;
    private UploadedFileTypeDetail purpose;
    private Date timeUploaded;
    private PersonDetails uploader;

}
