/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.procurement.method;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ProcurementMethod;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ProcurementMethodDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ProcurementMethodRequests extends EntityRequests implements ProcurementMethodRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addProcurementMethod(ProcurementMethodDetails procurementMethodDetails) throws MilesException {

        if (procurementMethodDetails == null) {
            throw new InvalidArgumentException("error_010_01");
        } else if (procurementMethodDetails.getMethod() == null) {
            throw new InvalidArgumentException("error_010_02");
        } else if (procurementMethodDetails.getMethod().length() > 45) {
            throw new InvalidArgumentException("error_010_03");
        }

        ProcurementMethod procurementMethod;
        setQ(em.createNamedQuery("ProcurementMethod.findByName"));
        q.setParameter("procurementMethod", procurementMethodDetails.getMethod());
        try {
            procurementMethod = (ProcurementMethod) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            procurementMethod = null;
        }
        if (procurementMethod != null) {
            throw new InvalidArgumentException("error_010_05");
        }

        procurementMethod = new ProcurementMethod();
        procurementMethod.setMethod(procurementMethodDetails.getMethod());

        try {
            em.persist(procurementMethod);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return procurementMethod.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<ProcurementMethodDetails> retrieveProcurementMethods() throws MilesException {
        List<ProcurementMethod> procurementMethods = new ArrayList<>();
        setQ(em.createNamedQuery("ProcurementMethod.findAll"));
        try {
            procurementMethods = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertProcurementMethodsToProcurementMethodDetailsList(procurementMethods);
    }

    @Override
    public ProcurementMethodDetails retrieveProcurementMethod(int id) throws MilesException {
        ProcurementMethod procurementMethod;
        setQ(em.createNamedQuery("ProcurementMethod.findById"));
        q.setParameter("id", id);
        try {
            procurementMethod = (ProcurementMethod) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertProcurementMethodToProcurementMethodDetails(procurementMethod);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editProcurementMethod(ProcurementMethodDetails procurementMethodDetails) throws MilesException {

        if (procurementMethodDetails == null) {
            throw new InvalidArgumentException("error_010_01");
        } else if (procurementMethodDetails.getId() == null) {
            throw new InvalidArgumentException("error_010_06");
        } else if (procurementMethodDetails.getMethod() == null) {
            throw new InvalidArgumentException("error_010_02");
        } else if (procurementMethodDetails.getMethod().length() > 45) {
            throw new InvalidArgumentException("error_010_03");
        }

        ProcurementMethod procurementMethod;
        setQ(em.createNamedQuery("ProcurementMethod.findByName"));
        q.setParameter("procurementMethod", procurementMethodDetails.getMethod());
        try {
            procurementMethod = (ProcurementMethod) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            procurementMethod = null;
        }
        if (procurementMethod != null) {
            if (procurementMethod.getId().equals(procurementMethodDetails.getId())) {
                throw new InvalidArgumentException("error_010_05");
            }
        }

        procurementMethod = em.find(ProcurementMethod.class, procurementMethodDetails.getId());
        procurementMethod.setId(procurementMethodDetails.getId());
        procurementMethod.setMethod(procurementMethodDetails.getMethod());

        try {
            em.merge(procurementMethod);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeProcurementMethod(int id) throws MilesException {
        ProcurementMethod procurementMethod = em.find(ProcurementMethod.class, id);
        try {
            em.remove(procurementMethod);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ProcurementMethodDetails convertProcurementMethodToProcurementMethodDetails(ProcurementMethod procurementMethod) {

        ProcurementMethodDetails procurementMethodDetails = new ProcurementMethodDetails();
        try {
            procurementMethodDetails.setId(procurementMethod.getId());
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }
        try {
            procurementMethodDetails.setMethod(procurementMethod.getMethod());
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return procurementMethodDetails;

    }

    private List<ProcurementMethodDetails> convertProcurementMethodsToProcurementMethodDetailsList(List<ProcurementMethod> procurementMethods) {

        List<ProcurementMethodDetails> procurementMethodDetailsList = new ArrayList<>();
        for (ProcurementMethod procurementMethod : procurementMethods) {
            procurementMethodDetailsList.add(convertProcurementMethodToProcurementMethodDetails(procurementMethod));
        }

        return procurementMethodDetailsList;

    }

//</editor-fold>
}
