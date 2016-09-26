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
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.equipment.EquipmentRequestsLocal;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;

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
            warehouse.setUnits(em.getReference(MeasurementUnit.class,
                    warehouseDetails.getUnits().getId()));
        }
        if (warehouseDetails.getWarehouseOperator() != null) {
            warehouse.setWarehouseOperator(em.getReference(Phenomenon.class,
                    warehouseDetails.getWarehouseOperator().getId()));
        }
        if (warehouseDetails.getWarehouseType() != null) {
            warehouse.setWarehouseType(em.getReference(Phenomenon.class,
                    warehouseDetails.getWarehouseType().getId()));
        }

        try {
            em.persist(warehouse);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return warehouse.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public WarehouseDetails retrieveWarehouse(int warehouseOperatorId) throws MilesException {

        setQ(em.createNamedQuery("Warehouse.findByWarehouseOperatorId"));
        q.setParameter("warehouseOperatorId", warehouseOperatorId);
        Warehouse warehouse;
        try {
            warehouse = (Warehouse) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehouseToWarehouseDetails(warehouse);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WarehouseDetails> retrieveWardWarehouses(short wardId) throws MilesException {

        setQ(em.createNamedQuery("Warehouse.findByWardId"));
        q.setParameter("wardId", wardId);
        System.out.println(wardId);
        List<Warehouse> warehouses;
        try {
            warehouses = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        System.out.println(warehouses);

        return convertWarehousesToWarehouseDetailsList(warehouses);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WarehouseDetails> retrieveCountyWarehouses(short countyId) throws MilesException {

        setQ(em.createNamedQuery("Warehouse.findByCountyId"));
        q.setParameter("countyId", countyId);
        List<Warehouse> warehouses;
        try {
            warehouses = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehousesToWarehouseDetailsList(warehouses);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WarehouseDetails> retrieveSubCountyWarehouses(short subCountyId) throws MilesException {

        setQ(em.createNamedQuery("Warehouse.findBySubCountyId"));
        q.setParameter("subCountyId", subCountyId);
        List<Warehouse> warehouses;
        try {
            warehouses = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehousesToWarehouseDetailsList(warehouses);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<WarehouseDetails> retrieveWarehouses() throws MilesException {

        setQ(em.createNamedQuery("Warehouse.findAll"));
        List<Warehouse> warehouses;
        try {
            warehouses = q.getResultList();
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

        Warehouse warehouse = em.find(Warehouse.class, warehouseDetails.getId());
        warehouse.setId(warehouseDetails.getId());
        warehouse.setOffersWrs(warehouseDetails.getOffersWrs());
        warehouse.setName(warehouseDetails.getName());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setCertified(warehouseDetails.getCertified());
        warehouse.setLocation(locationService.editLocation(warehouseDetails.getLocation()));
        if (warehouseDetails.getUnits() != null) {
            warehouse.setUnits(em.getReference(MeasurementUnit.class,
                    warehouseDetails.getUnits().getId()));
        }
        if (warehouseDetails.getWarehouseOperator() != null) {
            warehouse.setWarehouseOperator(em.getReference(Phenomenon.class,
                    warehouseDetails.getWarehouseOperator().getId()));
        }
        if (warehouseDetails.getWarehouseType() != null) {
            warehouse.setWarehouseType(em.getReference(Phenomenon.class,
                    warehouseDetails.getWarehouseType().getId()));
        }

        try {
            em.merge(warehouse);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeWarehouse(int id) throws MilesException {

        Warehouse warehouse = em.find(Warehouse.class, id);
        equipmentService.removeWarehouseEquipment(warehouse.getId());
        try {
            em.remove(warehouse);
        } catch (Exception e) {
            MilesDebugger.debug(e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">
    @Override
    public WarehouseDetails convertWarehouseToWarehouseDetails(Warehouse warehouse) {

        WarehouseDetails warehouseDetails = new WarehouseDetails(warehouse.getId());

        if (warehouse.getLocation() != null) {
            warehouseDetails.setLocation(locationService
                    .convertLocationToLocationDetails(warehouse.getLocation()));
        }

        if (warehouse.getUnits() != null) {
            warehouseDetails.setUnits(measurementUnitService
                    .convertMeasurementUnitToMeasurementUnitDetails(warehouse.getUnits()));
        }

        if (warehouse.getWarehouseOperator() != null) {
            warehouseDetails.setWarehouseOperator(phenomenonService
                    .convertPhenomenonToPhenomenonDetails(warehouse.getWarehouseOperator()));
        }

        if (warehouse.getWarehouseType() != null) {
            warehouseDetails.setWarehouseType(phenomenonService
                    .convertPhenomenonToPhenomenonDetails(warehouse.getWarehouseType()));
        }

        warehouseDetails.setOffersWrs(warehouse.getOffersWrs());
        warehouseDetails.setCertified(warehouse.getCertified());
        warehouseDetails.setCapacity(warehouse.getCapacity());
        warehouseDetails.setName(warehouse.getName());

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
    private LocationRequestsLocal locationService;
    @EJB
    private EquipmentRequestsLocal equipmentService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;

}
