/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse.equipment;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Equipment;
import ke.co.miles.kcep.mis.entities.Warehouse;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.warehouse.WarehouseRequestsLocal;
import ke.co.miles.kcep.mis.utilities.EquipmentDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;

/**
 *
 * @author siech
 */
@Stateless
public class EquipmentRequests extends EntityRequests implements EquipmentRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">  
    @Override
    public int addEquipment(EquipmentDetails equipmentDetails) throws MilesException {

        if (equipmentDetails == null) {
            throw new InvalidArgumentException("error_009_01");
        } else if (equipmentDetails.getWarehouse() == null) {
            throw new InvalidArgumentException("error_009_02");
        } else if (equipmentDetails.getType() != null && equipmentDetails.
                getType().trim().length() > 45) {
            throw new InvalidArgumentException("error_009_03");
        } else if (equipmentDetails.getSerialNumber() != null && equipmentDetails.
                getSerialNumber().trim().length() > 45) {
            throw new InvalidArgumentException("error_009_04");
        }

        Equipment equipment = new Equipment();
        equipment.setType(equipmentDetails.getType());
        equipment.setStatus(equipmentDetails.getStatus());
        equipment.setTotalCount(equipmentDetails.getTotalCount());
        equipment.setSerialNumber(equipmentDetails.getSerialNumber());
        if (equipmentDetails.getWarehouse().getId() != null) {
            equipment.setWarehouse(getEm().getReference(Warehouse.class, equipmentDetails.getWarehouse().getId()));
        }

        try {
            getEm().persist(equipment);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return equipment.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public EquipmentDetails retrieveEquipment(int id) throws MilesException {

        setQ(getEm().createNamedQuery("Equipment.findById"));
        getQ().setParameter("id", id);
        Equipment equipment;
        try {
            equipment = (Equipment) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertEquipmentToEquipmentDetails(equipment);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EquipmentDetails> retrieveEquipmentList(int warehouseId) throws MilesException {

        setQ(getEm().createNamedQuery("Equipment.findByWarehouseId"));
        getQ().setParameter("warehouseId", warehouseId);
        List<Equipment> equipmentList;
        try {
            equipmentList = getQ().getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertEquipmentListToEquipmentDetailsList(equipmentList);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editEquipment(EquipmentDetails equipmentDetails) throws MilesException {

        if (equipmentDetails == null) {
            throw new InvalidArgumentException("error_009_01");
        } else if (equipmentDetails.getId() == null) {
            throw new InvalidArgumentException("error_009_05");
        } else if (equipmentDetails.getWarehouse() == null) {
            throw new InvalidArgumentException("error_009_02");
        } else if (equipmentDetails.getType() != null && equipmentDetails.
                getType().trim().length() > 45) {
            throw new InvalidArgumentException("error_009_03");
        } else if (equipmentDetails.getSerialNumber() != null && equipmentDetails.
                getSerialNumber().trim().length() > 45) {
            throw new InvalidArgumentException("error_009_04");
        }

        Equipment equipment = getEm().find(Equipment.class, equipmentDetails.getId());
        equipment.setId(equipmentDetails.getId());
        equipment.setType(equipmentDetails.getType());
        equipment.setStatus(equipmentDetails.getStatus());
        equipment.setTotalCount(equipmentDetails.getTotalCount());
        equipment.setSerialNumber(equipmentDetails.getSerialNumber());
        if (equipmentDetails.getWarehouse().getId() != null) {
            equipment.setWarehouse(getEm().getReference(Warehouse.class, equipmentDetails.getWarehouse().getId()));
        }

        try {
            getEm().merge(equipment);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    @SuppressWarnings("unchecked")
    public void removeWarehouseEquipment(int warehouseId) throws MilesException {

        List<Equipment> equipmentList = new ArrayList<>();
        setQ(getEm().createNamedQuery("Equipment.findByWarehouseId"));
        getQ().setParameter("warehouseId", warehouseId);
        try {
            equipmentList = getQ().getResultList();
        } catch (Exception e) {
        }
        if (!equipmentList.isEmpty()) {
            for (Equipment equipment : equipmentList) {
                removeEquipment(equipment);
            }
        }

    }

    @Override
    public void removeEquipment(int id) throws MilesException {

        Equipment equipment = getEm().find(Equipment.class, id);
        try {
            getEm().remove(equipment);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    private void removeEquipment(Equipment equipment) throws MilesException {

        try {
            getEm().remove(equipment);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert"> 
    @Override
    public EquipmentDetails convertEquipmentToEquipmentDetails(Equipment equipment) {

        WarehouseDetails warehouseDetails = null;
        if (equipment.getWarehouse().getId() != null) {
            warehouseDetails = warehouseService.convertWarehouseToWarehouseDetails(equipment.getWarehouse());
        }

        EquipmentDetails equipmentDetails = new EquipmentDetails(equipment.getId());
        equipmentDetails.setSerialNumber(equipment.getSerialNumber());
        equipmentDetails.setTotalCount(equipment.getTotalCount());
        equipmentDetails.setWarehouse(warehouseDetails);
        equipmentDetails.setStatus(equipment.getStatus());
        equipmentDetails.setType(equipment.getType());

        return equipmentDetails;

    }

    private List<EquipmentDetails> convertEquipmentListToEquipmentDetailsList(List<Equipment> equipmentList) {
        List<EquipmentDetails> equipmentDetailsList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            equipmentDetailsList.add(convertEquipmentToEquipmentDetails(equipment));
        }

        return equipmentDetailsList;

    }

//</editor-fold>
    @EJB
    private WarehouseRequestsLocal warehouseService;
}
