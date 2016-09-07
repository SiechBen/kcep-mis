/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.component;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Component;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;

/**
 *
 * @author siech
 */
@Local
public interface ComponentRequestsLocal {

    /**
     *
     * @param componentDetails details of the component record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addComponent(ComponentDetails componentDetails) throws MilesException;

    /**
     *
     * @return the list of component record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ComponentDetails> retrieveComponents() throws MilesException;

    /**
     *
     * @param id the unique identifier of the component record to be retrieved
     * @return the details of the component record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ComponentDetails retrieveComponent(int id) throws MilesException;

    /**
     *
     * @param componentDetails details of the component record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editComponent(ComponentDetails componentDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the component record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeComponent(int id) throws MilesException;

    /**
     *
     * @param component the component to be converted
     * @return the details of the converted component
     */
    public ComponentDetails convertComponentToComponentDetails(Component component);

}
