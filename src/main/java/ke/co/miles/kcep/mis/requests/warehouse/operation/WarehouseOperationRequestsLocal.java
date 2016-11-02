/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse.operation;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.WarehouseOperation;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.WarehouseOperationDetails;

/**
 *
 * @author siech
 */
@Local
public interface WarehouseOperationRequestsLocal {

    /**
     *
     * @param warehouseOperationDetails details of the warehouse operation
     * record to be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addWarehouseOperation(
            WarehouseOperationDetails warehouseOperationDetails)
            throws MilesException;

    /**
     *
     * @param id unique identifier of the warehouse operation record to retrieve
     * @return the details of the warehouse operation record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public WarehouseOperationDetails retrieveWarehouseOperation(int id)
            throws MilesException;

    /**
     *
     * @param warehouseId the unique identifier for the warehouse to which the
     * warehouse operations belong
     * @return the map of warehouse operation summaries and list of warehouse
     * operation record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<HashMap<String, Integer>, List<WarehouseOperationDetails>> retrieveWarehouseOperations(
            int warehouseId) throws MilesException;

    /**
     *
     * @param warehouseOperationDetails details of the warehouse operation
     * record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editWarehouseOperation(
            WarehouseOperationDetails warehouseOperationDetails)
            throws MilesException;

    /**
     *
     * @param warehouseId unique identifier of the warehouse to which the
     * warehouse operation record to be removed is situated
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeWarehouseWarehouseOperation(int warehouseId)
            throws MilesException;

    /**
     *
     * @param id unique identifier of the warehouse operation record to be
     * removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeWarehouseOperation(int id) throws MilesException;

    /**
     *
     * @param warehouseOperation the warehouse operation record
     * @return the details of the warehouse operation record
     */
    public WarehouseOperationDetails
            convertWarehouseOperationToWarehouseOperationDetails(
                    WarehouseOperation warehouseOperation);

    /**
     *
     * @param warehouseId the unique identifier of the warehouse for which the
     * produce summary is to be retrieved
     * @param produceTypeId the unique identifier of the produce type for which
     * the summary is to be retrieved
     * @return the map of counts/summaries
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<String, Integer> countWarehouseProduce(int warehouseId, int produceTypeId) throws MilesException;
}
