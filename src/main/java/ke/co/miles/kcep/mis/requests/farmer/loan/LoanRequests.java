/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.loan;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Account;
import ke.co.miles.kcep.mis.entities.Loan;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.account.AccountRequestsLocal;
import ke.co.miles.kcep.mis.utilities.LoanDetails;

/**
 *
 * @author siech
 */
@Stateless
public class LoanRequests extends EntityRequests implements LoanRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">  
    @Override
    public int addLoan(LoanDetails loanDetails) throws MilesException {

        if (loanDetails == null) {
            throw new InvalidArgumentException("error_046_01");
        } else if (loanDetails.getAccount() == null) {
            throw new InvalidArgumentException("error_046_02");
        } else if (loanDetails.getType() != null && loanDetails.getType().trim().length() > 45) {
            throw new InvalidArgumentException("error_046_03");
        }

        Loan loan = new Loan();
        loan.setType(loanDetails.getType());
        loan.setAmount(loanDetails.getAmount());
        loan.setAccount(getEm().getReference(Account.class, loanDetails.getAccount().getId()));

        try {
            getEm().persist(loan);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return loan.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public LoanDetails retrieveLoan(int id) throws MilesException {

        setQ(getEm().createNamedQuery("Loan.findById"));
        getQ().setParameter("id", id);
        Loan loan;
        try {
            loan = (Loan) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertLoanToLoanDetails(loan);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LoanDetails> retrieveLoans(int accountId) throws MilesException {

        setQ(getEm().createNamedQuery("Loan.findByAccountId"));
        getQ().setParameter("accountId", accountId);
        List<Loan> loans;
        try {
            loans = getQ().getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertLoansToLoanDetailsList(loans);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editLoan(LoanDetails loanDetails) throws MilesException {

        if (loanDetails == null) {
            throw new InvalidArgumentException("error_046_01");
        } else if (loanDetails.getId() == null) {
            throw new InvalidArgumentException("error_046_04");
        } else if (loanDetails.getAccount() == null) {
            throw new InvalidArgumentException("error_046_02");
        } else if (loanDetails.getType() != null && loanDetails.getType().trim().length() > 45) {
            throw new InvalidArgumentException("error_046_03");
        }

        Loan loan = getEm().find(Loan.class, loanDetails.getId());
        loan.setId(loanDetails.getId());
        loan.setType(loanDetails.getType());
        loan.setAmount(loanDetails.getAmount());
        loan.setAccount(getEm().getReference(Account.class, loanDetails.getAccount().getId()));

        try {
            getEm().merge(loan);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeLoan(int id) throws MilesException {

        Loan loan = getEm().find(Loan.class, id);
        try {
            getEm().remove(loan);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert"> 
    @Override
    public LoanDetails convertLoanToLoanDetails(Loan loan) {

        LoanDetails loanDetails = new LoanDetails(loan.getId());
        loanDetails.setAccount(accountService.convertAccountToAccountDetails(loan.getAccount()));
        loanDetails.setType(loan.getType());
        loanDetails.setAmount(loan.getAmount());

        return loanDetails;

    }

    private List<LoanDetails> convertLoansToLoanDetailsList(List<Loan> loans) {

        List<LoanDetails> loanDetailsList = new ArrayList<>();
        for (Loan loan : loans) {
            loanDetailsList.add(convertLoanToLoanDetails(loan));
        }

        return loanDetailsList;

    }

//</editor-fold>
    @EJB
    private AccountRequestsLocal accountService;
}
