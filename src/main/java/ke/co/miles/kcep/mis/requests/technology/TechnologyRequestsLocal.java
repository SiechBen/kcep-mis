/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.technology;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.TechnologyDetails;

/**
 *
 * @author siech
 */
@Local
public interface TechnologyRequestsLocal {

    /**
     *
     * @param technologyDetails details of the technology record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addTechnology(TechnologyDetails technologyDetails) throws MilesException;

    /**
     *
     * @return the list of technology record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<TechnologyDetails> retrieveTechnologies() throws MilesException;

    /**
     *
     * @param id the unique identifier of the technology record to be retrieved
     * @return the details of the technology record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public TechnologyDetails retrieveTechnology(int id) throws MilesException;

    /**
     *
     * @param technologyDetails details of the technology record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editTechnology(TechnologyDetails technologyDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the technology record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeTechnology(int id) throws MilesException;

}