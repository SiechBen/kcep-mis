package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "topic", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findByModuleAndTrainerId", query = "SELECT t FROM Topic t WHERE t.module = :module AND t.trainer.id = :trainerId"),
    @NamedQuery(name = "Topic.findByModule", query = "SELECT t FROM Topic t WHERE t.module = :module"),
    @NamedQuery(name = "Topic.findByModuleId", query = "SELECT t FROM Topic t WHERE t.module.id = :moduleId"),
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t"),
    @NamedQuery(name = "Topic.findById", query = "SELECT t FROM Topic t WHERE t.id = :id"),
    @NamedQuery(name = "Topic.findByTopic", query = "SELECT t FROM Topic t WHERE t.topic = :topic")})
public class Topic implements Serializable {

    @JoinColumn(name = "trainer", referencedColumnName = "id")
    @ManyToOne
    private Phenomenon trainer;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "topic")
    private String topic;
    @OneToMany(mappedBy = "module")
    private List<Topic> topicList;
    @JoinColumn(name = "module", referencedColumnName = "id")
    @ManyToOne
    private Topic module;
    @OneToMany(mappedBy = "topic")
    private List<Training> trainingList;

    public Topic() {
    }

    public Topic(Short id) {
        this.id = id;
    }

    public Topic(Short id, String topic) {
        this.id = id;
        this.topic = topic;
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

    @XmlTransient
    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public Topic getModule() {
        return module;
    }

    public void setModule(Topic module) {
        this.module = module;
    }

    @XmlTransient
    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
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
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Topic[ id=" + id + " ]";
    }

    public Phenomenon getTrainer() {
        return trainer;
    }

    public void setTrainer(Phenomenon trainer) {
        this.trainer = trainer;
    }

}
