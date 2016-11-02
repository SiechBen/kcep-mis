/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse;

import java.util.HashMap;
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
     * @param warehouseOperatorId unique identifier of the warehouse operator of
     * the warehouse record to be retrieved
     * @return the details of the warehouse record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public WarehouseDetails retrieveWarehouse(int warehouseOperatorId) throws MilesException;

    /**
     *
     * @return the list of warehouse record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<WarehouseDetails> retrieveWarehouses() throws MilesException;

    /**
     *
     * @param wardId the unique identifier of the ward where the warehouses are
     * located
     * @return the list of warehouses in the ward retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<WarehouseDetails> retrieveWardWarehouses(short wardId) throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county where the warehouses
     * are located
     * @return the list of warehouses in the county retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<WarehouseDetails> retrieveCountyWarehouses(short countyId) throws MilesException;

    /**
     *
     * @param subCountyId the unique identifier of the sub-county where the
     * warehouses are located
     * @return the list of warehouses in the sub-county retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<WarehouseDetails> retrieveSubCountyWarehouses(short subCountyId) throws MilesException;

    /**
     *
     * @param warehouseDetails details of the warehouse record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editWarehouse(WarehouseDetails warehouseDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the warehouse record to be removed
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

    /**
     *
     * @return the map of count values
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<String, Integer> countAllWarehouses() throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county at which the
     * warehouses to be counted are located
     * @return the map of count values
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<String, Integer> countCountyWarehouses(short countyId) throws MilesException;

}
