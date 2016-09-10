/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.MeasurementUnit;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.Warehouse;
import ke.co.miles.kcep.mis.entities.WarehouseType;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.equipment.EquipmentRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.type.WarehouseTypeRequestsLocal;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseTypeDetails;

/**
 *
 * @author siech
 */
@Stateless
public class WarehouseRequests extends EntityRequests implements WarehouseRequestsLocal {
//<editor-fold defaultstate="collapsed" desc="Create">  

    @Override
    public int addWarehouse(WarehouseDetails warehouseDetails) throws MilesException {

        if (warehouseDetails == null) {
            throw new InvalidArgumentException("error_008_01");
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setOffersWrs(warehouseDetails.getOffersWrs());
        warehouse.setName(warehouseDetails.getName());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setCertified(warehouseDetails.getCertified());
        warehouse.setLocation(locationService.addLocation(warehouseDetails.getLocation()));
        if (warehouseDetails.getUnits() != null) {
            warehouse.setUnits(getEm().getReference(MeasurementUnit.class, warehouseDetails.getUnits().getId()));
        }
        if (warehouseDetails.getWarehouseOperator() != null) {
            warehouse.setWarehouseOperator(getEm().getReference(Phenomenon.class, warehouseDetails.getWarehouseOperator().getId()));
        }
        if (warehouseDetails.getWarehouseType() != null) {
            warehouse.setWarehouseType(getEm().getReference(WarehouseType.class, warehouseDetails.getWarehouseType().getId()));
        }

        try {
            getEm().persist(warehouse);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return warehouse.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public WarehouseDetails retrieveWarehouse(int warehouseOperatorId) throws MilesException {

        setQ(getEm().createNamedQuery("Warehouse.findByWarehouseOperatorId"));
        getQ().setParameter("warehouseOperatorId", warehouseOperatorId);
        Warehouse warehouse;
        try {
            warehouse = (Warehouse) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehouseToWarehouseDetails(warehouse);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WarehouseDetails> retrieveWardWarehouses(short wardId) throws MilesException {

        setQ(getEm().createNamedQuery("Warehouse.findByWardId"));
        getQ().setParameter("wardId", wardId);
        System.out.println(wardId);
        List<Warehouse> warehouses;
        try {
            warehouses = getQ().getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        System.out.println(warehouses);

        return convertWarehousesToWarehouseDetailsList(warehouses);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WarehouseDetails> retrieveCountyWarehouses(short countyId) throws MilesException {

        setQ(getEm().createNamedQuery("Warehouse.findByCountyId"));
        getQ().setParameter("countyId", countyId);
        List<Warehouse> warehouses;
        try {
            warehouses = getQ().getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehousesToWarehouseDetailsList(warehouses);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WarehouseDetails> retrieveSubCountyWarehouses(short subCountyId) throws MilesException {

        setQ(getEm().createNamedQuery("Warehouse.findBySubCountyId"));
        getQ().setParameter("subCountyId", subCountyId);
        List<Warehouse> warehouses;
        try {
            warehouses = getQ().getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehousesToWarehouseDetailsList(warehouses);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<WarehouseDetails> retrieveWarehouses() throws MilesException {

        setQ(getEm().createNamedQuery("Warehouse.findAll"));
        List<Warehouse> warehouses;
        try {
            warehouses = getQ().getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehousesToWarehouseDetailsList(warehouses);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editWarehouse(WarehouseDetails warehouseDetails) throws MilesException {

        if (warehouseDetails == null) {
            throw new InvalidArgumentException("error_008_01");
        }
        if (warehouseDetails.getId() == null) {
            throw new InvalidArgumentException("error_008_02");
        }

        Warehouse warehouse = getEm().find(Warehouse.class, warehouseDetails.getId());
        warehouse.setId(warehouseDetails.getId());
        warehouse.setOffersWrs(warehouseDetails.getOffersWrs());
        warehouse.setName(warehouseDetails.getName());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setCertified(warehouseDetails.getCertified());
        warehouse.setLocation(locationService.editLocation(warehouseDetails.getLocation()));
        if (warehouseDetails.getUnits() != null) {
            warehouse.setUnits(getEm().getReference(MeasurementUnit.class, warehouseDetails.getUnits().getId()));
        }
        if (warehouseDetails.getWarehouseOperator() != null) {
            warehouse.setWarehouseOperator(getEm().getReference(Phenomenon.class, warehouseDetails.getWarehouseOperator().getId()));
        }
        if (warehouseDetails.getWarehouseType() != null) {
            warehouse.setWarehouseType(getEm().getReference(WarehouseType.class, warehouseDetails.getWarehouseType().getId()));
        }

        try {
            getEm().merge(warehouse);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeWarehouse(int id) throws MilesException {

        Warehouse warehouse = getEm().find(Warehouse.class, id);
        equipmentService.removeWarehouseEquipment(warehouse.getId());
        try {
            getEm().remove(warehouse);
        } catch (Exception e) {
            MilesDebugger.debug(e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert"> 
    @Override
    public WarehouseDetails convertWarehouseToWarehouseDetails(Warehouse warehouse) {

        LocationDetails locationDetails = null;
        if (warehouse.getLocation() != null) {
            locationDetails = locationService.convertLocationToLocationDetails(warehouse.getLocation());
        }

        MeasurementUnitDetails measurementUnitDetails = null;
        if (warehouse.getUnits() != null) {
            measurementUnitDetails = measurementUnitService.convertMeasurementUnitToMeasurementUnitDetails(warehouse.getUnits());
        }

        PhenomenonDetails warehouseOperatorDetails = null;
        if (warehouse.getWarehouseOperator() != null) {
            warehouseOperatorDetails = personService.convertPhenomenonToPhenomenonDetails(warehouse.getWarehouseOperator());
        }

        WarehouseTypeDetails warehouseTypeDetails = null;
        if (warehouse.getWarehouseType() != null) {
            warehouseTypeDetails = warehouseTypeService.convertWarehouseTypeToWarehouseTypeDetails(warehouse.getWarehouseType());
        }

        WarehouseDetails warehouseDetails = new WarehouseDetails(warehouse.getId());
        warehouseDetails.setWarehouseOperator(warehouseOperatorDetails);
        warehouseDetails.setWarehouseType(warehouseTypeDetails);
        warehouseDetails.setOffersWrs(warehouse.getOffersWrs());
        warehouseDetails.setCertified(warehouse.getCertified());
        warehouseDetails.setCapacity(warehouse.getCapacity());
        warehouseDetails.setUnits(measurementUnitDetails);
        warehouseDetails.setName(warehouse.getName());
        warehouseDetails.setLocation(locationDetails);

        return warehouseDetails;

    }

    private List<WarehouseDetails> convertWarehousesToWarehouseDetailsList(List<Warehouse> warehouses) {

        List<WarehouseDetails> warehouseDetailsList = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            warehouseDetailsList.add(convertWarehouseToWarehouseDetails(warehouse));
        }

        return warehouseDetailsList;

    }

//</editor-fold>
    @EJB
    private PhenomenonRequestsLocal personService;
    @EJB
    private LocationRequestsLocal locationService;
    @EJB
    private EquipmentRequestsLocal equipmentService;
    @EJB
    private WarehouseTypeRequestsLocal warehouseTypeService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;

}
