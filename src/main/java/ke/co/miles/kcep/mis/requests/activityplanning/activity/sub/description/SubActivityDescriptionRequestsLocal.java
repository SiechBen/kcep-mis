/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.description;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.SubActivityDescription;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.SubActivityDescriptionDetails;

/**
 *
 * @author siech
 */
@Local
public interface SubActivityDescriptionRequestsLocal {

    /**
     *
     * @param subActivityDescriptionDetails details of the sub activity description record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addSubActivityDescription(SubActivityDescriptionDetails subActivityDescriptionDetails) throws MilesException;

    /**
     *
     * @return the list of sub activity description record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubActivityDescriptionDetails> retrieveSubActivityDescriptions() throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub activity description record to be retrieved
     * @return the details of the sub activity description record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public SubActivityDescriptionDetails retrieveSubActivityDescription(int id) throws MilesException;

    /**
     *
     * @param subActivityDescriptionDetails details of the sub activity description record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editSubActivityDescription(SubActivityDescriptionDetails subActivityDescriptionDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub activity description record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeSubActivityDescription(int id) throws MilesException;

    /**
     *
     * @param subActivityDescription the subActivityDescription to be converted
     * @return the details of the converted subActivityDescription
     */
    public SubActivityDescriptionDetails convertSubActivityDescriptionToSubActivityDescriptionDetails(SubActivityDescription subActivityDescription);

}
