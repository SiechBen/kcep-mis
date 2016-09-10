/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.topic;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Topic;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.TopicDetails;

/**
 *
 * @author siech
 */
@Stateless
public class TopicRequests extends EntityRequests implements TopicRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addTopic(TopicDetails topicDetails) throws MilesException {

        if (topicDetails == null) {
            throw new InvalidArgumentException("error_033_01");
        } else if (topicDetails.getTopic() == null) {
            throw new InvalidArgumentException("error_033_02");
        } else if (topicDetails.getTopic().length() > 200) {
            throw new InvalidArgumentException("error_033_03");
        }

        Topic topic;
        setQ(getEm().createNamedQuery("Topic.findByTopic"));
        getQ().setParameter("topic", topicDetails.getTopic());
        try {
            topic = (Topic) getQ().getSingleResult();
        } catch (Exception e) {
            topic = null;
        }
        if (topic != null) {
            throw new InvalidArgumentException("error_033_04");
        }

        topic = new Topic();
        topic.setTopic(topicDetails.getTopic());

        try {
            getEm().persist(topic);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return topic.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<TopicDetails> retrieveTopics() throws MilesException {
        List<Topic> topics = new ArrayList<>();
        setQ(getEm().createNamedQuery("Topic.findAll"));
        try {
            topics = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertTopicsToTopicDetailsList(topics);
    }

    @Override
    public TopicDetails retrieveTopic(int id) throws MilesException {
        Topic topic;
        setQ(getEm().createNamedQuery("Topic.findById"));
        getQ().setParameter("id", id);
        try {
            topic = (Topic) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertTopicToTopicDetails(topic);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editTopic(TopicDetails topicDetails) throws MilesException {

        if (topicDetails == null) {
            throw new InvalidArgumentException("error_033_01");
        } else if (topicDetails.getId() == null) {
            throw new InvalidArgumentException("error_033_05");
        } else if (topicDetails.getTopic() == null) {
            throw new InvalidArgumentException("error_033_02");
        } else if (topicDetails.getTopic().length() > 200) {
            throw new InvalidArgumentException("error_033_03");
        }

        Topic topic;
        setQ(getEm().createNamedQuery("Topic.findByTopic"));
        getQ().setParameter("topic", topicDetails.getTopic());
        try {
            topic = (Topic) getQ().getSingleResult();
        } catch (Exception e) {
            topic = null;
        }
        if (topic != null) {
            if (topic.getId().equals(topicDetails.getId())) {
                throw new InvalidArgumentException("error_033_04");
            }
        }

        topic = getEm().find(Topic.class, topicDetails.getId());
        topic.setId(topicDetails.getId());
        topic.setTopic(topicDetails.getTopic());

        try {
            getEm().merge(topic);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeTopic(int id) throws MilesException {
        Topic topic = getEm().find(Topic.class, id);
        try {
            getEm().remove(topic);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public TopicDetails convertTopicToTopicDetails(Topic topic) {

        TopicDetails topicDetails = new TopicDetails();
        try {
            topicDetails.setId(topic.getId());
        } catch (Exception e) {
        }
        try {
            topicDetails.setTopic(topic.getTopic());
        } catch (Exception e) {
        }
        return topicDetails;

    }

    private List<TopicDetails> convertTopicsToTopicDetailsList(List<Topic> topics) {

        List<TopicDetails> topicDetailsList = new ArrayList<>();
        for (Topic topic : topics) {
            topicDetailsList.add(convertTopicToTopicDetails(topic));
        }

        return topicDetailsList;

    }

//</editor-fold>
}
