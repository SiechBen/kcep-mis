/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.name.sub;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.SubActivityName;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.SubActivityNameDetails;

/**
 *
 * @author siech
 */
@Local
public interface SubActivityNameRequestsLocal {

    /**
     *
     * @param subActivityNameDetails details of the sub activity name record to
     * be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addSubActivityName(SubActivityNameDetails subActivityNameDetails) throws MilesException;

    /**
     *
     * @param activityNameId the unique identifier of the activity name to which
     * the sub-activity name to be retrieved belongs
     * @return the list of sub activity name record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubActivityNameDetails> retrieveSubActivityNames(int activityNameId) throws MilesException;

    /**
     *
     * @return the list of sub activity name record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubActivityNameDetails> retrieveSubActivityNames() throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub activity name record to be
     * retrieved
     * @return the details of the sub activity name record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public SubActivityNameDetails retrieveSubActivityName(int id) throws MilesException;

    /**
     *
     * @param subActivityNameDetails details of the sub activity name record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editSubActivityName(SubActivityNameDetails subActivityNameDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub activity name record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeSubActivityName(int id) throws MilesException;

    /**
     *
     * @param subActivityName the subActivityName to be converted
     * @return the details of the converted subActivityName
     */
    public SubActivityNameDetails convertSubActivityNameToSubActivityNameDetails(SubActivityName subActivityName);

}
