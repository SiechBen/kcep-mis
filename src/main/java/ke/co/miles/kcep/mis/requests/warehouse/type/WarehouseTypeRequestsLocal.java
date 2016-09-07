/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse.type;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.WarehouseType;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.WarehouseTypeDetails;

/**
 *
 * @author siech
 */
@Local
public interface WarehouseTypeRequestsLocal {

    /**
     *
     * @param warehouseTypeDetails details of the warehouse type record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addWarehouseType(WarehouseTypeDetails warehouseTypeDetails) throws MilesException;

    /**
     *
     * @return the list of warehouse type record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<WarehouseTypeDetails> retrieveWarehouseTypes() throws MilesException;

    /**
     *
     * @param id the unique identifier of the warehouse type record to be
     * retrieved
     * @return the details of the warehouse type record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public WarehouseTypeDetails retrieveWarehouseType(int id) throws MilesException;

    /**
     *
     * @param warehouseTypeDetails details of the warehouse type record to be
     * edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editWarehouseType(WarehouseTypeDetails warehouseTypeDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the warehouse type record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeWarehouseType(int id) throws MilesException;

    /**
     *
     * @param warehouseType the warehouse type to be converted
     * @return the details of the converted warehouse type
     */
    public WarehouseTypeDetails convertWarehouseTypeToWarehouseTypeDetails(WarehouseType warehouseType);

}
