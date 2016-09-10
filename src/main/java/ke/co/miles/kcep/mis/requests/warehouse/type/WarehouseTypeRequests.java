/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse.type;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.WarehouseType;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.WarehouseTypeDetails;

/**
 *
 * @author siech
 */
@Stateless
public class WarehouseTypeRequests extends EntityRequests implements WarehouseTypeRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addWarehouseType(WarehouseTypeDetails warehouseTypeDetails) throws MilesException {

        if (warehouseTypeDetails == null) {
            throw new InvalidArgumentException("error_024_01");
        } else if (warehouseTypeDetails.getType() == null) {
            throw new InvalidArgumentException("error_024_02");
        } else if (warehouseTypeDetails.getType().length() > 45) {
            throw new InvalidArgumentException("error_024_03");
        }

        WarehouseType warehouseType;
        setQ(getEm().createNamedQuery("WarehouseType.findByType"));
        getQ().setParameter("type", warehouseTypeDetails.getType());
        try {
            warehouseType = (WarehouseType) getQ().getSingleResult();
        } catch (Exception e) {
            warehouseType = null;
        }
        if (warehouseType != null) {
            throw new InvalidArgumentException("error_024_04");
        }

        warehouseType = new WarehouseType();
        warehouseType.setType(warehouseTypeDetails.getType());

        try {
            getEm().persist(warehouseType);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return warehouseType.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<WarehouseTypeDetails> retrieveWarehouseTypes() throws MilesException {
        List<WarehouseType> warehouseTypes = new ArrayList<>();
        setQ(getEm().createNamedQuery("WarehouseType.findAll"));
        try {
            warehouseTypes = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertWarehouseTypesToWarehouseTypeDetailsList(warehouseTypes);
    }

    @Override
    public WarehouseTypeDetails retrieveWarehouseType(int id) throws MilesException {
        WarehouseType warehouseType;
        setQ(getEm().createNamedQuery("WarehouseType.findById"));
        getQ().setParameter("id", id);
        try {
            warehouseType = (WarehouseType) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehouseTypeToWarehouseTypeDetails(warehouseType);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editWarehouseType(WarehouseTypeDetails warehouseTypeDetails) throws MilesException {

        if (warehouseTypeDetails == null) {
            throw new InvalidArgumentException("error_024_01");
        } else if (warehouseTypeDetails.getId() == null) {
            throw new InvalidArgumentException("error_024_05");
        } else if (warehouseTypeDetails.getType() == null) {
            throw new InvalidArgumentException("error_024_02");
        } else if (warehouseTypeDetails.getType().length() > 45) {
            throw new InvalidArgumentException("error_024_03");
        }

        WarehouseType warehouseType;
        setQ(getEm().createNamedQuery("WarehouseType.findByType"));
        getQ().setParameter("type", warehouseTypeDetails.getType());
        try {
            warehouseType = (WarehouseType) getQ().getSingleResult();
        } catch (Exception e) {
            warehouseType = null;
        }
        if (warehouseType != null) {
            if (warehouseType.getId().equals(warehouseTypeDetails.getId())) {
                throw new InvalidArgumentException("error_024_04");
            }
        }

        warehouseType = getEm().find(WarehouseType.class, warehouseTypeDetails.getId());
        warehouseType.setId(warehouseTypeDetails.getId());
        warehouseType.setType(warehouseTypeDetails.getType());

        try {
            getEm().merge(warehouseType);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeWarehouseType(int id) throws MilesException {
        WarehouseType warehouseType = getEm().find(WarehouseType.class, id);
        try {
            getEm().remove(warehouseType);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public WarehouseTypeDetails convertWarehouseTypeToWarehouseTypeDetails(WarehouseType warehouseType
    ) {

        WarehouseTypeDetails warehouseTypeDetails;
        try {
            warehouseTypeDetails = new WarehouseTypeDetails(warehouseType.getId());
        } catch (Exception e) {
            return null;
        }
        
        warehouseTypeDetails.setType(warehouseType.getType());
        return warehouseTypeDetails;

    }

    private List<WarehouseTypeDetails> convertWarehouseTypesToWarehouseTypeDetailsList(List<WarehouseType> warehouseTypes) {

        List<WarehouseTypeDetails> warehouseTypeDetailsList = new ArrayList<>();
        for (WarehouseType warehouseType : warehouseTypes) {
            warehouseTypeDetailsList.add(convertWarehouseTypeToWarehouseTypeDetails(warehouseType));
        }

        return warehouseTypeDetailsList;

    }

//</editor-fold>
}
