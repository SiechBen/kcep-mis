/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import ke.co.miles.kcep.mis.utilities.WarehouseOperatorDetail;
import ke.co.miles.kcep.mis.utilities.WarehouseTypeDetail;

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

        if (null != warehouse.getCertified()) {
            if (warehouse.getCertified()) {
                try {
                    setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                    q.setParameter(1, 1);
                    q.setParameter(2, 46);
                    q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                    q.executeUpdate();
                } catch (Exception e) {
                    MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                }
            }
        }

        if (warehouse.getWarehouseType() != null) {
            if (warehouse.getWarehouseType().getId() == 20) {
                try {
                    setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NOT NULL) THEN pv.actual_value + ?1 ELSE ?1 END) WHERE (pv.performance_indicator = ?2 OR pv.performance_indicator = ?3) AND pv.project_year = ?4"));
                    q.setParameter(1, 1);
                    q.setParameter(2, 48);
                    q.setParameter(3, 50);
                    q.setParameter(4, Calendar.getInstance().get(Calendar.YEAR));
                    q.executeUpdate();
                } catch (Exception e) {
                    MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                }
            } else if (warehouse.getWarehouseType().getId() == 21) {
                try {
                    setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NOT NULL) THEN pv.actual_value + ?1 ELSE ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                    q.setParameter(1, 1);
                    q.setParameter(2, 50);
                    q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                    q.executeUpdate();
                } catch (Exception e) {
                    MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                }
            }
        }

        try {
            em.persist(warehouse);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return warehouse.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @SuppressWarnings("unchecked")
    @Override
    public HashMap<String, Integer> countAllWarehouses() throws MilesException {

        //<editor-fold defaultstate="collapsed" desc="certified/uncertified">
        int certifiedWarehouses;
        setQ(em.createNamedQuery("Warehouse.findByCertifiedAndWarehouseTypeId"));
        q.setParameter("certified", Boolean.TRUE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            certifiedWarehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int certifiedCollectionCentres;
        setQ(em.createNamedQuery("Warehouse.findByCertifiedAndWarehouseTypeId"));
        q.setParameter("certified", Boolean.TRUE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            certifiedCollectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int unCertifiedWarehouses;
        setQ(em.createNamedQuery("Warehouse.findByCertifiedAndWarehouseTypeId"));
        q.setParameter("certified", Boolean.FALSE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            unCertifiedWarehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int unCertifiedCollectionCentres;
        setQ(em.createNamedQuery("Warehouse.findByCertifiedAndWarehouseTypeId"));
        q.setParameter("certified", Boolean.FALSE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            unCertifiedCollectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="oferring wrs/not">
        int warehousesOfferingWrs;
        setQ(em.createNamedQuery("Warehouse.findByOffersWrsWarehouseTypeId"));
        q.setParameter("offersWrs", Boolean.TRUE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            warehousesOfferingWrs = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int collectionCentresOfferingWrs;
        setQ(em.createNamedQuery("Warehouse.findByOffersWrsWarehouseTypeId"));
        q.setParameter("offersWrs", Boolean.TRUE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            collectionCentresOfferingWrs = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int warehousesNotOfferingWrs;
        setQ(em.createNamedQuery("Warehouse.findByOffersWrsWarehouseTypeId"));
        q.setParameter("offersWrs", Boolean.FALSE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            warehousesNotOfferingWrs = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int collectionCentresNotOfferingWrs;
        setQ(em.createNamedQuery("Warehouse.findByOffersWrsWarehouseTypeId"));
        q.setParameter("offersWrs", Boolean.FALSE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            collectionCentresNotOfferingWrs = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="farmer/privately owned">
        int farmerOwnedWarehouses;
        setQ(em.createNamedQuery("Warehouse.findByWarehouseTypeIdAndWarehouseOperatorId"));
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        q.setParameter("warehouseOperatorId", WarehouseOperatorDetail.FARMER_OWNED.getId());
        try {
            farmerOwnedWarehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int farmerOwnedCollectionCentres;
        setQ(em.createNamedQuery("Warehouse.findByWarehouseTypeIdAndWarehouseOperatorId"));
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        q.setParameter("warehouseOperatorId", WarehouseOperatorDetail.FARMER_OWNED.getId());
        try {
            farmerOwnedCollectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int privatelyOwnedWarehouses;
        setQ(em.createNamedQuery("Warehouse.findByWarehouseTypeIdAndWarehouseOperatorId"));
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        q.setParameter("warehouseOperatorId", WarehouseOperatorDetail.PRIVATELY_OWNED.getId());
        try {
            privatelyOwnedWarehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int privatelyOwnedCollectionCentres;
        setQ(em.createNamedQuery("Warehouse.findByWarehouseTypeIdAndWarehouseOperatorId"));
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        q.setParameter("warehouseOperatorId", WarehouseOperatorDetail.PRIVATELY_OWNED.getId());
        try {
            privatelyOwnedCollectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="totals">
        int total;
        setQ(em.createNamedQuery("Warehouse.countAll"));
        try {
            total = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int warehouses;
        setQ(em.createNamedQuery("Warehouse.countByWarehouseTypeId"));
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            warehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int collectionCentres;
        setQ(em.createNamedQuery("Warehouse.countByWarehouseTypeId"));
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            collectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
        //</editor-fold>

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("cw", certifiedWarehouses);
        countMap.put("uw", unCertifiedWarehouses);
        countMap.put("fow", farmerOwnedWarehouses);
        countMap.put("pow", privatelyOwnedWarehouses);
        countMap.put("wnow", warehousesNotOfferingWrs);
        countMap.put("wow", warehousesOfferingWrs);

        countMap.put("ccc", certifiedCollectionCentres);
        countMap.put("ucc", unCertifiedCollectionCentres);
        countMap.put("ccow", collectionCentresOfferingWrs);
        countMap.put("focc", farmerOwnedCollectionCentres);
        countMap.put("pocc", privatelyOwnedCollectionCentres);
        countMap.put("ccnow", collectionCentresNotOfferingWrs);

        countMap.put("Total", total);
        countMap.put("Total w", warehouses);
        countMap.put("Total cc", collectionCentres);

        return countMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public HashMap<String, Integer> countCountyWarehouses(short countyId) throws MilesException {

        //<editor-fold defaultstate="collapsed" desc="certified/uncertified">
        int certifiedWarehouses;
        setQ(em.createNamedQuery("Warehouse.findByCertifiedAndWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("certified", Boolean.TRUE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            certifiedWarehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int certifiedCollectionCentres;
        setQ(em.createNamedQuery("Warehouse.findByCertifiedAndWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("certified", Boolean.TRUE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            certifiedCollectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int unCertifiedWarehouses;
        setQ(em.createNamedQuery("Warehouse.findByCertifiedAndWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("certified", Boolean.FALSE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            unCertifiedWarehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int unCertifiedCollectionCentres;
        setQ(em.createNamedQuery("Warehouse.findByCertifiedAndWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("certified", Boolean.FALSE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            unCertifiedCollectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="oferring wrs/not">
        int warehousesOfferingWrs;
        setQ(em.createNamedQuery("Warehouse.findByOffersWrsWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("offersWrs", Boolean.TRUE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            warehousesOfferingWrs = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int collectionCentresOfferingWrs;
        setQ(em.createNamedQuery("Warehouse.findByOffersWrsWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("offersWrs", Boolean.TRUE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            collectionCentresOfferingWrs = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int warehousesNotOfferingWrs;
        setQ(em.createNamedQuery("Warehouse.findByOffersWrsWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("offersWrs", Boolean.FALSE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            warehousesNotOfferingWrs = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int collectionCentresNotOfferingWrs;
        setQ(em.createNamedQuery("Warehouse.findByOffersWrsWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("offersWrs", Boolean.FALSE);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            collectionCentresNotOfferingWrs = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="farmer/privately owned">
        int farmerOwnedWarehouses;
        setQ(em.createNamedQuery("Warehouse.findByWarehouseTypeIdAndWarehouseOperatorIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        q.setParameter("warehouseOperatorId", WarehouseOperatorDetail.FARMER_OWNED.getId());
        try {
            farmerOwnedWarehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int farmerOwnedCollectionCentres;
        setQ(em.createNamedQuery("Warehouse.findByWarehouseTypeIdAndWarehouseOperatorIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        q.setParameter("warehouseOperatorId", WarehouseOperatorDetail.FARMER_OWNED.getId());
        try {
            farmerOwnedCollectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int privatelyOwnedWarehouses;
        setQ(em.createNamedQuery("Warehouse.findByWarehouseTypeIdAndWarehouseOperatorIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        q.setParameter("warehouseOperatorId", WarehouseOperatorDetail.PRIVATELY_OWNED.getId());
        try {
            privatelyOwnedWarehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int privatelyOwnedCollectionCentres;
        setQ(em.createNamedQuery("Warehouse.findByWarehouseTypeIdAndWarehouseOperatorIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        q.setParameter("warehouseOperatorId", WarehouseOperatorDetail.PRIVATELY_OWNED.getId());
        try {
            privatelyOwnedCollectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="totals">
        int total;
        setQ(em.createNamedQuery("Warehouse.countByCountyId"));
        q.setParameter("countyId", countyId);
        try {
            total = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int warehouses;
        setQ(em.createNamedQuery("Warehouse.countByWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.WAREHOUSE.getId());
        try {
            warehouses = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        int collectionCentres;
        setQ(em.createNamedQuery("Warehouse.countByWarehouseTypeIdAndCountyId"));
        q.setParameter("countyId", countyId);
        q.setParameter("warehouseTypeId", WarehouseTypeDetail.COLLECTION_CENTRE.getId());
        try {
            collectionCentres = ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
        //</editor-fold>

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("cw", certifiedWarehouses);
        countMap.put("uw", unCertifiedWarehouses);
        countMap.put("fow", farmerOwnedWarehouses);
        countMap.put("pow", privatelyOwnedWarehouses);
        countMap.put("wnow", warehousesNotOfferingWrs);
        countMap.put("wow", warehousesOfferingWrs);

        countMap.put("ccc", certifiedCollectionCentres);
        countMap.put("ucc", unCertifiedCollectionCentres);
        countMap.put("ccow", collectionCentresOfferingWrs);
        countMap.put("focc", farmerOwnedCollectionCentres);
        countMap.put("pocc", privatelyOwnedCollectionCentres);
        countMap.put("ccnow", collectionCentresNotOfferingWrs);

        countMap.put("Total", total);
        countMap.put("Total w", warehouses);
        countMap.put("Total cc", collectionCentres);

        return countMap;
    }

    @Override
    public WarehouseDetails retrieveWarehouse(int warehouseOperatorId) throws MilesException {

        setQ(em.createNamedQuery("Warehouse.findByWarehouseOperatorId"));
        q.setParameter("warehouseOperatorId", warehouseOperatorId);
        Warehouse warehouse;
        try {
            warehouse = (Warehouse) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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

        if (null != warehouse.getCertified()) {
            if (warehouse.getCertified()) {
                if (warehouseDetails.getCertified() != null && !warehouseDetails.getCertified()) {
                    try {
                        setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NOT NULL) THEN pv.actual_value - ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                        q.setParameter(1, 1);
                        q.setParameter(2, 46);
                        q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                        q.executeUpdate();
                    } catch (Exception e) {
                        MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                    }
                }
            } else if (warehouseDetails.getCertified() != null && warehouseDetails.getCertified()) {
                try {
                    setQ(em.createNativeQuery("UPDATE performance_indicator_values pv SET actual_value = (CASE WHEN (pv.actual_value IS NULL) THEN ?1 ELSE pv.actual_value + ?1 END) WHERE pv.performance_indicator = ?2 AND pv.project_year = ?3"));
                    q.setParameter(1, 1);
                    q.setParameter(2, 46);
                    q.setParameter(3, Calendar.getInstance().get(Calendar.YEAR));
                    q.executeUpdate();
                } catch (Exception e) {
                    MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                }
            }
        }

        warehouse.setCertified(warehouseDetails.getCertified());

        try {
            em.merge(warehouse);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
