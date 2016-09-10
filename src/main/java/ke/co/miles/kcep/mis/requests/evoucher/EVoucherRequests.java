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
            eVoucher.setInputType(getEm().getReference(InputType.class, eVoucherDetails.getInputType().getId()));
        } catch (Exception e) {
            eVoucher.setInputType(null);
        }
        try {
            eVoucher.setPerson(getEm().getReference(Person.class, eVoucherDetails.getPerson().getId()));
        } catch (Exception e) {
            eVoucher.setPerson(null);
        }

        try {
            getEm().persist(eVoucher);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return eVoucher.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<EVoucherDetails> retrieveEVouchers() throws MilesException {
        List<EVoucher> eVouchers = new ArrayList<>();
        setQ(getEm().createNamedQuery("EVoucher.findAll"));
        try {
            eVouchers = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertEVouchersToEVoucherDetailsList(eVouchers);
    }

    @Override
    public EVoucherDetails retrieveEVoucher(int id) throws MilesException {
        EVoucher eVoucher;
        setQ(getEm().createNamedQuery("EVoucher.findById"));
        getQ().setParameter("id", id);
        try {
            eVoucher = (EVoucher) getQ().getSingleResult();
        } catch (Exception e) {
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

        EVoucher eVoucher = getEm().find(EVoucher.class, eVoucherDetails.getId());
        eVoucher.setId(eVoucherDetails.getId());
        if (eVoucherDetails.getInputsLogbookPage() != null) {
            eVoucher.setInputsLogbookPage(eVoucherDetails.getInputsLogbookPage());
        }
        eVoucher.setAmount(eVoucherDetails.getAmount());
        eVoucher.setDateRedeemed(eVoucherDetails.getDateRedeemed());
        try {
            eVoucher.setInputType(getEm().getReference(InputType.class, eVoucherDetails.getInputType().getId()));
        } catch (Exception e) {
            eVoucher.setInputType(null);
        }
        try {
            eVoucher.setPerson(getEm().getReference(Person.class, eVoucherDetails.getPerson().getId()));
        } catch (Exception e) {
            eVoucher.setPerson(null);
        }

        try {
            getEm().merge(eVoucher);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeEVoucher(int id) throws MilesException {
        EVoucher eVoucher = getEm().find(EVoucher.class, id);
        try {
            getEm().remove(eVoucher);
        } catch (Exception e) {
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
