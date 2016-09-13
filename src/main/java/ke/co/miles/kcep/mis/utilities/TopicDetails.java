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
public class TopicDetails implements Serializable, Comparable<TopicDetails> {

    public TopicDetails() {
    }

    public TopicDetails(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TopicDetails)) {
            return false;
        }
        TopicDetails other = (TopicDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.TrainingTopicDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(TopicDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the module
     */
    public TopicDetails getModule() {
        return module;
    }

    /**
     * @param module the module to set
     */
    public void setModule(TopicDetails module) {
        this.module = module;
    }

    private static final long serialVersionUID = 1L;
    private Short id;
    private String topic;
    private TopicDetails module;

}
