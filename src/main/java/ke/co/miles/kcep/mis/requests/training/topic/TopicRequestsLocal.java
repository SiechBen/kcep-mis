/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.topic;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Topic;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.TopicDetails;

/**
 *
 * @author siech
 */
@Local
public interface TopicRequestsLocal {

    /**
     *
     * @param topicDetails details of the topic record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public short addTopic(TopicDetails topicDetails) throws MilesException;

    /**
     *
     * @return the list of training modules details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<TopicDetails> retrieveTrainingModules() throws MilesException;
    
    /**
     *
     * @param moduleId the unique identifier of the training module for
     * which the list of training topics to be retrieved fall under
     * @return the list of topic record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<TopicDetails> retrieveTopics(short moduleId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the topic record to be retrieved
     * @return the details of the topic record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public TopicDetails retrieveTopic(short id) throws MilesException;

    /**
     *
     * @param topicDetails details of the topic record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editTopic(TopicDetails topicDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the topic record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeTopic(short id) throws MilesException;

    /**
     *
     * @param topic the topic to be converted
     * @return the details of the converted topic
     */
    public TopicDetails convertTopicToTopicDetails(Topic topic);

}
