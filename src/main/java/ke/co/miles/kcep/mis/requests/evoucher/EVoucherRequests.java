/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.evoucher;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.EVoucher;
import ke.co.miles.kcep.mis.entities.InputType;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.inputtype.InputTypeRequestsLocal;
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
    public EVoucher addEVoucher(EVoucherDetails eVoucherDetails) throws MilesException {

        if (eVoucherDetails == null) {
            throw new InvalidArgumentException("error_019_01");
        }

        EVoucher eVoucher = new EVoucher();
        eVoucher.setInputsLogbookPage(eVoucherDetails.getInputsLogbookPage());
        eVoucher.setAmount(eVoucherDetails.getAmount());
        eVoucher.setDateRedeemed(eVoucherDetails.getDateRedeemed());
        try {
            eVoucher.setInputType(em.find(InputType.class, eVoucherDetails.getInputType().getId()));
        } catch (Exception e) {
            eVoucher.setInputType(null);
        }
        try {
            eVoucher.setPerson(em.find(Person.class, eVoucherDetails.getPerson().getId()));
        } catch (Exception e) {
            eVoucher.setPerson(null);
        }

        try {
            em.persist(eVoucher);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return eVoucher;

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<EVoucherDetails> retrieveEVouchers() throws MilesException {
        List<EVoucher> eVouchers = new ArrayList<>();
        q = em.createNamedQuery("EVoucher.findAll");
        try {
            eVouchers = q.getResultList();
        } catch (Exception e) {
        }

        return convertEVouchersToEVoucherDetailsList(eVouchers);
    }

    @Override
    public EVoucherDetails retrieveEVoucher(int id) throws MilesException {
        EVoucher eVoucher;
        q = em.createNamedQuery("EVoucher.findById");
        q.setParameter("id", id);
        try {
            eVoucher = (EVoucher) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertEVoucherToEVoucherDetails(eVoucher);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public EVoucher editEVoucher(EVoucherDetails eVoucherDetails) throws MilesException {

        if (eVoucherDetails == null) {
            throw new InvalidArgumentException("error_019_01");
        } else if (eVoucherDetails.getId() == null) {
            throw new InvalidArgumentException("error_019_02");
        }

        EVoucher eVoucher = em.find(EVoucher.class, eVoucherDetails.getId());
        eVoucher.setId(eVoucherDetails.getId());
        eVoucher.setInputsLogbookPage(eVoucherDetails.getInputsLogbookPage());
        eVoucher.setAmount(eVoucherDetails.getAmount());
        eVoucher.setDateRedeemed(eVoucherDetails.getDateRedeemed());
        try {
            eVoucher.setInputType(em.find(InputType.class, eVoucherDetails.getInputType().getId()));
        } catch (Exception e) {
            eVoucher.setInputType(null);
        }
        try {
            eVoucher.setPerson(em.find(Person.class, eVoucherDetails.getPerson().getId()));
        } catch (Exception e) {
            eVoucher.setPerson(null);
        }

        try {
            em.merge(eVoucher);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return eVoucher;

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeEVoucher(int id) throws MilesException {
        EVoucher eVoucher = em.find(EVoucher.class, id);
        try {
            em.remove(eVoucher);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public EVoucherDetails convertEVoucherToEVoucherDetails(EVoucher eVoucher
    ) {

        EVoucherDetails eVoucherDetails = new EVoucherDetails(eVoucher.getId());
        eVoucherDetails.setInputType(inputTypeService.convertInputTypeToInputTypeDetails(eVoucher.getInputType()));
        eVoucherDetails.setPerson(personService.convertPersonToPersonDetails(eVoucher.getPerson()));
        eVoucherDetails.setDateRedeemed(eVoucher.getDateRedeemed());
        eVoucherDetails.setAmount(eVoucher.getAmount());
        eVoucherDetails.setInputsLogbookPage(eVoucher.getInputsLogbookPage());
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
    InputTypeRequestsLocal inputTypeService;
    @EJB
    PersonRequestsLocal personService;
}
