package ke.co.miles.kcep.mis.requests.warehouse.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.StaticInput;
import ke.co.miles.kcep.mis.entities.Warehouse;
import ke.co.miles.kcep.mis.entities.WarehouseOperation;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.input.staticinput.StaticInputRequestsLocal;
import ke.co.miles.kcep.mis.requests.warehouse.WarehouseRequestsLocal;
import ke.co.miles.kcep.mis.utilities.WarehouseOperationDetails;

/**
 *
 * @author siech
 */
@Stateless
public class WarehouseOperationRequests extends EntityRequests
        implements WarehouseOperationRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addWarehouseOperation(
            WarehouseOperationDetails warehouseOperationDetails)
            throws MilesException {

        if (warehouseOperationDetails == null) {
            throw new InvalidArgumentException("error_009_01");
        } else if (warehouseOperationDetails.getWarehouse() == null) {
            throw new InvalidArgumentException("error_009_02");
        } else if (warehouseOperationDetails.getBuyer() != null
                && warehouseOperationDetails.getBuyer().trim().length() > 120) {
            throw new InvalidArgumentException("error_009_03");
        }

        WarehouseOperation warehouseOperation = new WarehouseOperation();
        warehouseOperation.setSellingDate(
                warehouseOperationDetails.getSellingDate());
        warehouseOperation.setSellingPrice(
                warehouseOperationDetails.getSellingPrice());
        warehouseOperation.setBuyer(warehouseOperationDetails.getBuyer());
        warehouseOperation.setQuantitySold(
                warehouseOperationDetails.getQuantitySold());
        warehouseOperation.setQuantityBrought(
                warehouseOperationDetails.getQuantityBrought());
        warehouseOperation.setWarehouse(em.getReference(Warehouse.class,
                warehouseOperationDetails.getWarehouse().getId()));
        if (warehouseOperationDetails.getProduceTypeSold().getId() != null) {
            warehouseOperation.setProduceTypeSold(em.getReference(
                    StaticInput.class, warehouseOperationDetails
                    .getProduceTypeSold().getId()));
        }
        if (warehouseOperationDetails.getProduceTypeBrought().getId() != null) {
            warehouseOperation.setProduceTypeBrought(em.getReference(
                    StaticInput.class, warehouseOperationDetails
                    .getProduceTypeBrought().getId()));
        }

        try {
            em.persist(warehouseOperation);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return warehouseOperation.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<String, Integer> countWarehouseProduce(int warehouseId, int produceTypeId) throws MilesException {

        int totalBagsBroughtIn = 0;
        int totalBagsSold = 0;
        setQ(em.createNamedQuery("WarehouseOperation.findProduceBroughtByWarehouseIdAndProduceTypeId"));
        q.setParameter("warehouseId", warehouseId);
        q.setParameter("produceTypeId", produceTypeId);

        List<WarehouseOperationDetails> warehouseOperations;
        try {
            warehouseOperations = convertWarehouseOperationListToWarehouseOperationDetailsList(q.getResultList());
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        for (WarehouseOperationDetails warehouseOperation : warehouseOperations) {
            if (warehouseOperation.getQuantityBrought() != null) {
                totalBagsBroughtIn += warehouseOperation.getQuantityBrought();
            }
        }

        setQ(em.createNamedQuery("WarehouseOperation.findProduceSoldByWarehouseIdAndProduceTypeId"));
        q.setParameter("warehouseId", warehouseId);
        q.setParameter("produceTypeId", produceTypeId);

        try {
            warehouseOperations = convertWarehouseOperationListToWarehouseOperationDetailsList(q.getResultList());
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        for (WarehouseOperationDetails warehouseOperation : warehouseOperations) {
            if (warehouseOperation.getQuantitySold() != null) {
                totalBagsSold += warehouseOperation.getQuantitySold();
            }
        }

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("tbbi", totalBagsBroughtIn);
        countMap.put("tbs", totalBagsSold);
        countMap.put("tbi", totalBagsBroughtIn - totalBagsSold);

        return countMap;
    }

    @Override
    public WarehouseOperationDetails retrieveWarehouseOperation(int id)
            throws MilesException {

        setQ(em.createNamedQuery("WarehouseOperation.findById"));
        q.setParameter("id", id);
        WarehouseOperation warehouseOperation;
        try {
            warehouseOperation = (WarehouseOperation) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehouseOperationToWarehouseOperationDetails(
                warehouseOperation);

    }

    @SuppressWarnings({"unchecked"})
    @Override
    public HashMap<HashMap<String, Integer>, List<WarehouseOperationDetails>> retrieveWarehouseOperations(
            int warehouseId) throws MilesException {

        setQ(em.createNamedQuery("WarehouseOperation.findByWarehouseId"));
        q.setParameter("warehouseId", warehouseId);
        List<WarehouseOperationDetails> warehouseOperations;
        try {
            warehouseOperations = convertWarehouseOperationListToWarehouseOperationDetailsList(q.getResultList());
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        int totalBagsBroughtIn = 0;
        int totalBagsSold = 0;

        for (WarehouseOperationDetails warehouseOperation : warehouseOperations) {
            if (warehouseOperation.getQuantityBrought() != null) {
                totalBagsBroughtIn += warehouseOperation.getQuantityBrought();
            }
            if (warehouseOperation.getQuantitySold() != null) {
                totalBagsSold += warehouseOperation.getQuantitySold();
            }
        }

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("tbbi", totalBagsBroughtIn);
        countMap.put("tbs", totalBagsSold);
        countMap.put("tbi", totalBagsBroughtIn - totalBagsSold);

        HashMap<HashMap<String, Integer>, List<WarehouseOperationDetails>> map = new HashMap<>();
        map.put(countMap, warehouseOperations);

        return map;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editWarehouseOperation(
            WarehouseOperationDetails warehouseOperationDetails)
            throws MilesException {

        if (warehouseOperationDetails == null) {
            throw new InvalidArgumentException("error_009_01");
        } else if (warehouseOperationDetails.getId() == null) {
            throw new InvalidArgumentException("error_009_05");
        } else if (warehouseOperationDetails.getWarehouse() == null) {
            throw new InvalidArgumentException("error_009_02");
        } else if (warehouseOperationDetails.getBuyer() != null
                && warehouseOperationDetails.getBuyer().trim().length() > 120) {
            throw new InvalidArgumentException("error_009_03");
        }

        WarehouseOperation warehouseOperation = (em.getReference(
                WarehouseOperation.class, warehouseOperationDetails.getId()));
        warehouseOperation.setId(
                warehouseOperationDetails.getId());
        warehouseOperation.setSellingDate(
                warehouseOperationDetails.getSellingDate());
        warehouseOperation.setSellingPrice(
                warehouseOperationDetails.getSellingPrice());
        warehouseOperation.setBuyer(warehouseOperationDetails.getBuyer());
        warehouseOperation.setQuantitySold(
                warehouseOperationDetails.getQuantitySold());
        warehouseOperation.setQuantityBrought(
                warehouseOperationDetails.getQuantityBrought());
        warehouseOperation.setWarehouse(em.getReference(Warehouse.class,
                warehouseOperationDetails.getWarehouse().getId()));
        if (warehouseOperationDetails.getProduceTypeSold().getId() != null) {
            warehouseOperation.setProduceTypeSold(em.getReference(
                    StaticInput.class, warehouseOperationDetails
                    .getProduceTypeSold().getId()));
        }
        if (warehouseOperationDetails.getProduceTypeBrought().getId() != null) {
            warehouseOperation.setProduceTypeBrought(em.getReference(
                    StaticInput.class, warehouseOperationDetails
                    .getProduceTypeBrought().getId()));
        }

        try {
            em.merge(warehouseOperation);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    @SuppressWarnings("unchecked")
    public void removeWarehouseWarehouseOperation(int warehouseId)
            throws MilesException {

        List<WarehouseOperation> warehouseOperationList = new ArrayList<>();
        setQ(em.createNamedQuery("WarehouseOperation.findByWarehouseId"));
        q.setParameter("warehouseId", warehouseId);
        try {
            warehouseOperationList = q.getResultList();
        } catch (Exception e) {
        }
        if (!warehouseOperationList.isEmpty()) {
            for (WarehouseOperation warehouseOperation : warehouseOperationList) {
                removeWarehouseOperation(warehouseOperation);
            }
        }

    }

    @Override
    public void removeWarehouseOperation(int id) throws MilesException {

        WarehouseOperation warehouseOperation = em.
                find(WarehouseOperation.class, id);
        try {
            em.remove(warehouseOperation);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    private void removeWarehouseOperation(WarehouseOperation warehouseOperation)
            throws MilesException {

        try {
            em.remove(warehouseOperation);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">
    @Override
    public WarehouseOperationDetails
            convertWarehouseOperationToWarehouseOperationDetails(
                    WarehouseOperation warehouseOperation) {

        WarehouseOperationDetails warehouseOperationDetails
                = new WarehouseOperationDetails(warehouseOperation.getId());
        warehouseOperationDetails.setWarehouse(
                warehouseService.convertWarehouseToWarehouseDetails(
                        warehouseOperation.getWarehouse()));
        if (warehouseOperation.getProduceTypeSold() != null) {
            warehouseOperationDetails.setProduceTypeSold(
                    staticInputService.convertStaticInputToStaticInputDetails(
                            warehouseOperation.getProduceTypeSold()));
        }
        if (warehouseOperation.getProduceTypeBrought() != null) {
            warehouseOperationDetails.setProduceTypeBrought(
                    staticInputService.convertStaticInputToStaticInputDetails(
                            warehouseOperation.getProduceTypeBrought()));
        }
        warehouseOperationDetails.setQuantityBrought(
                warehouseOperation.getQuantityBrought());
        warehouseOperationDetails.setQuantitySold(
                warehouseOperation.getQuantitySold());
        warehouseOperationDetails.setSellingPrice(
                warehouseOperation.getSellingPrice());
        warehouseOperationDetails.setSellingDate(
                warehouseOperation.getSellingDate());
        warehouseOperationDetails.setBuyer(warehouseOperation.getBuyer());

        return warehouseOperationDetails;

    }

    private List<WarehouseOperationDetails>
            convertWarehouseOperationListToWarehouseOperationDetailsList(
                    List<WarehouseOperation> warehouseOperationList) {
        List<WarehouseOperationDetails> warehouseOperationDetailsList
                = new ArrayList<>();
        for (WarehouseOperation warehouseOperation : warehouseOperationList) {
            warehouseOperationDetailsList.add(
                    convertWarehouseOperationToWarehouseOperationDetails(
                            warehouseOperation));
        }

        return warehouseOperationDetailsList;

    }

//</editor-fold>
    @EJB
    private WarehouseRequestsLocal warehouseService;
    @EJB
    private StaticInputRequestsLocal staticInputService;
}
