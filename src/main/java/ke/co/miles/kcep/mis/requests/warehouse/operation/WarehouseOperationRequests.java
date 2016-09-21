package ke.co.miles.kcep.mis.requests.warehouse.operation;

import java.util.ArrayList;
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
        warehouseOperation.setWarehouse(getEm().getReference(Warehouse.class,
                warehouseOperationDetails.getWarehouse().getId()));
        if (warehouseOperationDetails.getProduceTypeSold().getId() != null) {
            warehouseOperation.setProduceTypeSold(getEm().getReference(
                    StaticInput.class, warehouseOperationDetails
                    .getProduceTypeSold().getId()));
        }
        if (warehouseOperationDetails.getProduceTypeBrought().getId() != null) {
            warehouseOperation.setProduceTypeBrought(getEm().getReference(
                    StaticInput.class, warehouseOperationDetails
                    .getProduceTypeBrought().getId()));
        }

        try {
            getEm().persist(warehouseOperation);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return warehouseOperation.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public WarehouseOperationDetails retrieveWarehouseOperation(int id)
            throws MilesException {

        setQ(getEm().createNamedQuery("WarehouseOperation.findById"));
        getQ().setParameter("id", id);
        WarehouseOperation warehouseOperation;
        try {
            warehouseOperation = (WarehouseOperation) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehouseOperationToWarehouseOperationDetails(
                warehouseOperation);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<WarehouseOperationDetails> retrieveWarehouseOperations(
            int warehouseId) throws MilesException {

        setQ(getEm().createNamedQuery("WarehouseOperation.findByWarehouseId"));
        getQ().setParameter("warehouseId", warehouseId);
        List<WarehouseOperation> warehouseOperationList;
        try {
            warehouseOperationList = getQ().getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehouseOperationListToWarehouseOperationDetailsList(
                warehouseOperationList);
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

        WarehouseOperation warehouseOperation = (getEm().getReference(
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
        warehouseOperation.setWarehouse(getEm().getReference(Warehouse.class,
                warehouseOperationDetails.getWarehouse().getId()));
        if (warehouseOperationDetails.getProduceTypeSold().getId() != null) {
            warehouseOperation.setProduceTypeSold(getEm().getReference(
                    StaticInput.class, warehouseOperationDetails
                    .getProduceTypeSold().getId()));
        }
        if (warehouseOperationDetails.getProduceTypeBrought().getId() != null) {
            warehouseOperation.setProduceTypeBrought(getEm().getReference(
                    StaticInput.class, warehouseOperationDetails
                    .getProduceTypeBrought().getId()));
        }

        try {
            getEm().merge(warehouseOperation);
            getEm().flush();
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
        setQ(getEm().createNamedQuery("WarehouseOperation.findByWarehouseId"));
        getQ().setParameter("warehouseId", warehouseId);
        try {
            warehouseOperationList = getQ().getResultList();
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

        WarehouseOperation warehouseOperation = getEm().
                find(WarehouseOperation.class, id);
        try {
            getEm().remove(warehouseOperation);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    private void removeWarehouseOperation(WarehouseOperation warehouseOperation)
            throws MilesException {

        try {
            getEm().remove(warehouseOperation);
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
