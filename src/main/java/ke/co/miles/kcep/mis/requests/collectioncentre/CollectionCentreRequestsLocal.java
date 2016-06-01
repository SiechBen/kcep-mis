/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.collectioncentre;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.CollectionCentre;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.CollectionCentreDetails;

/**
 *
 * @author siech
 */
@Local
public interface CollectionCentreRequestsLocal {
    
    /**
     *
     * @param collectionCentreDetails details of the collection centre record to be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addCollectionCentre(CollectionCentreDetails collectionCentreDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the collection centre record to retrieve
     * @return the details of the collection centre record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public CollectionCentreDetails retrieveCollectionCentre(int id) throws MilesException;

    /**
     *
     * @param warehouseId the unique identifier for the warehouse to which the collection centre belongs
     * @return the list of collection centre record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<CollectionCentreDetails> retrieveCollectionCentreList(int warehouseId) throws MilesException;

    /**
     *
     * @param collectionCentreDetails details of the collection centre record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editCollectionCentre(CollectionCentreDetails collectionCentreDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the collection centre record to be
     * removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeCollectionCentre(int id) throws MilesException;

    /**
     *
     * @param collectionCentre the collection centre record
     * @return the details of the collection centre record
     */
    public CollectionCentreDetails convertCollectionCentreToCollectionCentreDetails(CollectionCentre collectionCentre);

}
