/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.evoucher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.EVoucher;
import ke.co.miles.kcep.mis.entities.InputType;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.input.type.InputTypeRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.EVoucherDetails;

/**
 *
 * @author siech
 */
@Stateless
public class EVoucherRequests extends EntityRequests implements EVoucherRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addEVoucher(EVoucherDetails eVoucherDetails) throws MilesException {

        if (eVoucherDetails == null) {
            throw new InvalidArgumentException("error_019_01");
        }

        EVoucher eVoucher = new EVoucher();
        eVoucher.setInputsLogbookPage(eVoucherDetails.getInputsLogbookPage());
        eVoucher.setAmount(eVoucherDetails.getAmount());
        eVoucher.setDateRedeemed(eVoucherDetails.getDateRedeemed());
        try {
            eVoucher.setInputType(em.getReference(InputType.class, eVoucherDetails.getInputType().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            eVoucher.setInputType(null);
        }
        try {
            eVoucher.setPerson(em.getReference(Person.class, eVoucherDetails.getPerson().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            eVoucher.setPerson(null);
        }

        try {
            em.persist(eVoucher);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return eVoucher.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<EVoucherDetails> retrieveEVouchers() throws MilesException {
        List<EVoucher> eVouchers = new ArrayList<>();
        setQ(em.createNamedQuery("EVoucher.findAll"));
        try {
            eVouchers = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertEVouchersToEVoucherDetailsList(eVouchers);
    }

    @Override
    public EVoucherDetails retrieveEVoucher(int id) throws MilesException {
        EVoucher eVoucher;
        setQ(em.createNamedQuery("EVoucher.findById"));
        q.setParameter("id", id);
        try {
            eVoucher = (EVoucher) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertEVoucherToEVoucherDetails(eVoucher);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editEVoucher(EVoucherDetails eVoucherDetails) throws MilesException {

        if (eVoucherDetails == null) {
            throw new InvalidArgumentException("error_019_01");
        } else if (eVoucherDetails.getId() == null) {
            throw new InvalidArgumentException("error_019_02");
        }

        EVoucher eVoucher = em.find(EVoucher.class, eVoucherDetails.getId());
        eVoucher.setId(eVoucherDetails.getId());
        if (eVoucherDetails.getInputsLogbookPage() != null) {
            eVoucher.setInputsLogbookPage(eVoucherDetails.getInputsLogbookPage());
        }
        eVoucher.setAmount(eVoucherDetails.getAmount());
        eVoucher.setDateRedeemed(eVoucherDetails.getDateRedeemed());
        try {
            eVoucher.setInputType(em.getReference(InputType.class, eVoucherDetails.getInputType().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            eVoucher.setInputType(null);
        }
        try {
            eVoucher.setPerson(em.getReference(Person.class, eVoucherDetails.getPerson().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            eVoucher.setPerson(null);
        }

        try {
            em.merge(eVoucher);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeEVoucher(int id) throws MilesException {
        EVoucher eVoucher = em.find(EVoucher.class, id);
        try {
            em.remove(eVoucher);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public EVoucherDetails convertEVoucherToEVoucherDetails(EVoucher eVoucher) {

        EVoucherDetails eVoucherDetails = new EVoucherDetails(eVoucher.getId());
        eVoucherDetails.setInputType(inputTypeService.convertInputTypeToInputTypeDetails(eVoucher.getInputType()));
        eVoucherDetails.setPerson(personService.convertPersonToPersonDetails(eVoucher.getPerson()));
        eVoucherDetails.setDateRedeemed(eVoucher.getDateRedeemed());
        eVoucherDetails.setAmount(eVoucher.getAmount());
        eVoucherDetails.setInputsLogbookPage(eVoucher.getInputsLogbookPage());

        if (eVoucherDetails.getInputsLogbookPage() != null) {
            String[] folders = eVoucherDetails.getInputsLogbookPage().split(File.separator);
            String fileName = folders[folders.length - 1];
            eVoucherDetails.setFileName(fileName);
        }

        return eVoucherDetails;

    }

    private List<EVoucherDetails> convertEVouchersToEVoucherDetailsList(List<EVoucher> eVouchers) {

        List<EVoucherDetails> eVoucherDetailsList = new ArrayList<>();
        for (EVoucher eVoucher : eVouchers) {

            eVoucherDetailsList.add(convertEVoucherToEVoucherDetails(eVoucher));
        }
        return eVoucherDetailsList;

    }

//</editor-fold>
    @EJB
    private InputTypeRequestsLocal inputTypeService;
    @EJB
    private PersonRequestsLocal personService;
}
