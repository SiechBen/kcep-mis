/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;

/**
 *
 * @author siech
 */
public class ActivityProgressCommentDetails implements Serializable, Comparable<ActivityProgressCommentDetails> {

    public ActivityProgressCommentDetails() {
    }

    public ActivityProgressCommentDetails(Integer id) {
        this.id = id;
    }

    public ActivityProgressCommentDetails(Integer id, String comment) {
        this.id = id;
        this.comment = comment;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivityProgressCommentDetails)) {
            return false;
        }
        ActivityProgressCommentDetails other = (ActivityProgressCommentDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ActivityProgressCommentDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ActivityProgressCommentDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the subActivity
     */
    public SubActivityDetails getSubActivity() {
        return subActivity;
    }

    /**
     * @param subActivity the subActivity to set
     */
    public void setSubActivity(SubActivityDetails subActivity) {
        this.subActivity = subActivity;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String comment;
    private SubActivityDetails subActivity;

}
