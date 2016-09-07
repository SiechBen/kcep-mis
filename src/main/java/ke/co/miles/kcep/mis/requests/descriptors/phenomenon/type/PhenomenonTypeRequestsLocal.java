/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.descriptors.phenomenon.type;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.PhenomenonType;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PhenomenonTypeDetails;

/**
 *
 * @author siech
 */
@Local
public interface PhenomenonTypeRequestsLocal {
    
    /**
     *
     * @param phenomenonTypeDetails details of the phenomenon type record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPhenomenonType(PhenomenonTypeDetails phenomenonTypeDetails) throws MilesException;

    /**
     *
     * @return the list of phenomenon type record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonTypeDetails> retrievePhenomenonTypes() throws MilesException;

    /**
     *
     * @param id the unique identifier of the phenomenon type record to be retrieved
     * @return the details of the phenomenon type record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public PhenomenonTypeDetails retrievePhenomenonType(int id) throws MilesException;

    /**
     *
     * @param phenomenonTypeDetails details of the phenomenon type record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPhenomenonType(PhenomenonTypeDetails phenomenonTypeDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the phenomenon type record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removePhenomenonType(int id) throws MilesException;

    /**
     *
     * @param phenomenonType the phenomenon type to be converted
     * @return the details of the converted phenomenonType
     */
    public PhenomenonTypeDetails convertPhenomenonTypeToPhenomenonTypeDetails(PhenomenonType phenomenonType);

}
