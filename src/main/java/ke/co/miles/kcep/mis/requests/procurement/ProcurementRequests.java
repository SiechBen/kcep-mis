/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.procurement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.County;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.Procurement;
import ke.co.miles.kcep.mis.entities.SubCounty;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ProcurementDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ProcurementRequests extends EntityRequests implements ProcurementRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addProcurement(ProcurementDetails procurementDetails) throws MilesException {

        if (procurementDetails == null) {
            throw new InvalidArgumentException("error_018_01");
        }

        Procurement procurement = new Procurement();
        procurement.setItem(procurementDetails.getItem());
        procurement.setCost(procurementDetails.getCost());
        procurement.setDescription(procurementDetails.getDescription());
        procurement.setLpoNumber(procurementDetails.getLpoNumber());
        procurement.setTargetOffice(procurementDetails.getTargetOffice());
        procurement.setSerialNumber(procurementDetails.getSerialNumber());
        procurement.setDatePurchased(procurementDetails.getDatePurchased());
        procurement.setInvoiceOrReceipt(procurementDetails.getInvoiceOrReceipt());
        if (procurementDetails.getGfssCode() != null) {
            procurement.setGfssCode(em.getReference(Phenomenon.class, procurementDetails.getGfssCode().getId()));
        }
        if (procurementDetails.getCounty() != null) {
            procurement.setCounty(em.getReference(County.class, procurementDetails.getCounty().getId()));
        }
        if (procurementDetails.getSubCounty() != null) {
            procurement.setSubCounty(em.getReference(SubCounty.class, procurementDetails.getSubCounty().getId()));
        }

        try {
            em.persist(procurement);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return procurement.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ProcurementDetails> retrieveProcurements() throws MilesException {
        List<Procurement> procurements = new ArrayList<>();
        setQ(em.createNamedQuery("Procurement.findAll"));
        try {
            procurements = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertProcurementsToProcurementDetailsList(procurements);
    }

    @Override
    public ProcurementDetails retrieveProcurement(int id) throws MilesException {
        Procurement procurement;
        setQ(em.createNamedQuery("Procurement.findById"));
        q.setParameter("id", id);
        try {
            procurement = (Procurement) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertProcurementToProcurementDetails(procurement);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editProcurement(ProcurementDetails procurementDetails) throws MilesException {

        if (procurementDetails == null) {
            throw new InvalidArgumentException("error_018_01");
        } else if (procurementDetails.getId() == null) {
            throw new InvalidArgumentException("error_018_02");
        }

        Procurement procurement = em.find(Procurement.class, procurementDetails.getId());
        procurement.setId(procurementDetails.getId());
        procurement.setItem(procurementDetails.getItem());
        procurement.setCost(procurementDetails.getCost());
        procurement.setDescription(procurementDetails.getDescription());
        procurement.setLpoNumber(procurementDetails.getLpoNumber());
        procurement.setTargetOffice(procurementDetails.getTargetOffice());
        procurement.setSerialNumber(procurementDetails.getSerialNumber());
        procurement.setDatePurchased(procurementDetails.getDatePurchased());
        if (procurementDetails.getGfssCode() != null) {
            procurement.setGfssCode(em.getReference(Phenomenon.class, procurementDetails.getGfssCode().getId()));
        }
        if (procurementDetails.getInvoiceOrReceipt() != null) {
            procurement.setInvoiceOrReceipt(procurementDetails.getInvoiceOrReceipt());
        }
        if (procurementDetails.getCounty() != null) {
            procurement.setCounty(em.getReference(County.class, procurementDetails.getCounty().getId()));
        }
        if (procurementDetails.getSubCounty() != null) {
            procurement.setSubCounty(em.getReference(SubCounty.class, procurementDetails.getSubCounty().getId()));
        }

        try {
            em.merge(procurement);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeProcurement(int id) throws MilesException {
        Procurement procurement = em.find(Procurement.class, id);
        try {
            em.remove(procurement);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private ProcurementDetails convertProcurementToProcurementDetails(Procurement procurement) {

        ProcurementDetails procurementDetails = new ProcurementDetails(procurement.getId());
        if (procurement.getCounty() != null) {
            procurementDetails.setCounty(countyService.convertCountyToCountyDetails(procurement.getCounty()));
        }
        if (procurement.getSubCounty() != null) {
            procurementDetails.setSubCounty(subCountyService.convertSubCountyToSubCountyDetails(procurement.getSubCounty()));
        }
        if (procurement.getGfssCode() != null) {
            procurementDetails.setGfssCode(phenomenonService.convertPhenomenonToPhenomenonDetails(procurement.getGfssCode()));
        }
        procurementDetails.setInvoiceOrReceipt(procurement.getInvoiceOrReceipt());
        procurementDetails.setDatePurchased(procurement.getDatePurchased());
        procurementDetails.setSerialNumber(procurement.getSerialNumber());
        procurementDetails.setTargetOffice(procurement.getTargetOffice());
        procurementDetails.setLpoNumber(procurement.getLpoNumber());
        procurementDetails.setDescription(procurement.getDescription());
        procurementDetails.setItem(procurement.getItem());
        procurementDetails.setCost(procurement.getCost());

        if (procurementDetails.getInvoiceOrReceipt() != null) {
            String[] folders = procurementDetails.getInvoiceOrReceipt().split(File.separator);
            String fileName = folders[folders.length - 1];
            procurementDetails.setFileName(fileName);
        }
        return procurementDetails;

    }

    private List<ProcurementDetails> convertProcurementsToProcurementDetailsList(List<Procurement> procurements) {

        List<ProcurementDetails> procurementDetailsList = new ArrayList<>();
        for (Procurement procurement : procurements) {
            procurementDetailsList.add(convertProcurementToProcurementDetails(procurement));

        }

        return procurementDetailsList;

    }

//</editor-fold>
    @EJB
    private CountyRequestsLocal countyService;
    @EJB
    private SubCountyRequestsLocal subCountyService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;
}
