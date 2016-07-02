/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.planning.component.sub;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.SubComponent;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.SubComponentDetails;

/**
 *
 * @author siech
 */
@Local
public interface SubComponentRequestsLocal {

    /**
     *
     * @param subComponentDetails details of the sub-component record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addSubComponent(SubComponentDetails subComponentDetails) throws MilesException;

    /**
     *
     * @return the list of sub-component record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubComponentDetails> retrieveSubComponents() throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub-component record to be retrieved
     * @return the details of the sub-component record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public SubComponentDetails retrieveSubComponent(int id) throws MilesException;

    /**
     *
     * @param subComponentDetails details of the sub-component record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editSubComponent(SubComponentDetails subComponentDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub-component record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeSubComponent(int id) throws MilesException;

    /**
     *
     * @param subComponent the sub-component to be converted
     * @return the details of the converted sub-component
     */
    public SubComponentDetails convertSubComponentToSubComponentDetails(SubComponent subComponent);

}
