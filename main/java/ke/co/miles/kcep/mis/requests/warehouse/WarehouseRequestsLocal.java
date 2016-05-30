/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Warehouse;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;

/**
 *
 * @author siech
 */
@Local
public interface WarehouseRequestsLocal {

    /**
     *
     * @param warehouseDetails details of the warehouse record to be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addWarehouse(WarehouseDetails warehouseDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the warehouse record to retrieve
     * @return the details of the warehouse record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public WarehouseDetails retrieveWarehouse(int id) throws MilesException;

    /**
     *
     * @return the list of warehouse record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<WarehouseDetails> retrieveWarehouses() throws MilesException;

    /**
     *
     * @param warehouseDetails details of the warehouse record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editWarehouse(WarehouseDetails warehouseDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the warehouse record to be
     * removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeWarehouse(int id) throws MilesException;

    /**
     *
     * @param warehouse the warehouse record
     * @return the details of the warehouse record
     */
    public WarehouseDetails convertWarehouseToWarehouseDetails(Warehouse warehouse);

}
