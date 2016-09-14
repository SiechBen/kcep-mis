/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse.equipment;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Equipment;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.EquipmentDetails;

/**
 *
 * @author siech
 */
@Local
public interface EquipmentRequestsLocal {

    /**
     *
     * @param equipmentDetails details of the equipment record to be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addEquipment(EquipmentDetails equipmentDetails) throws MilesException;

    /**
     *
     * @param id unique identifier of the equipment record to retrieve
     * @return the details of the equipment record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public EquipmentDetails retrieveEquipment(int id) throws MilesException;

    /**
     *
     * @param warehouseId the unique identifier for the warehouse to which the
     * equipment belongs
     * @return the list of equipment record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<EquipmentDetails> retrieveEquipmentList(int warehouseId) throws MilesException;

    /**
     *
     * @param equipmentDetails details of the equipment record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editEquipment(EquipmentDetails equipmentDetails) throws MilesException;

    /**
     *
     * @param warehouseId unique identifier of the warehouse to which the
     * equipment record to be removed is situated
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeWarehouseEquipment(int warehouseId) throws MilesException;

    /**
     *
     * @param id unique identifier of the equipment record to be * removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removeEquipment(int id) throws MilesException;

    /**
     *
     * @param equipment the equipment record
     * @return the details of the equipment record
     */
    public EquipmentDetails convertEquipmentToEquipmentDetails(Equipment equipment);

}
