/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.account;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Account;
import ke.co.miles.kcep.mis.entities.EblBranch;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.account.eblbranch.EblBranchRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.AccountDetails;

/**
 *
 * @author siech
 */
@Stateless
public class AccountRequests extends EntityRequests implements AccountRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addAccount(AccountDetails accountDetails) throws MilesException {

        if (accountDetails == null) {
            throw new InvalidArgumentException("error_043_01");
        } else if (accountDetails.getFarmer() == null) {
            throw new InvalidArgumentException("error_043_02");
        } else if (accountDetails.getSolId() != null && accountDetails.getSolId().trim().length() > 45) {
            throw new InvalidArgumentException("error_043_03");
        } else if (accountDetails.getAccountNumber() != null && accountDetails.getAccountNumber().trim().length() > 45) {
            throw new InvalidArgumentException("error_043_04");
        }

        Account account;
        q = em.createNamedQuery("Account.findByAccountNumber");
        q.setParameter("accountNumber", accountDetails.getAccountNumber());
        try {
            account = (Account) q.getSingleResult();
        } catch (Exception e) {
            account = null;
        }
        if (account != null) {
            throw new InvalidArgumentException("error_043_05");
        }

        account = new Account();
        account.setSolId(accountDetails.getSolId());
        account.setSavings(accountDetails.getSavings());
        account.setAccountNumber(accountDetails.getAccountNumber());
        if (accountDetails.getEblBranch() != null) {
            account.setEblBranch(em.getReference(EblBranch.class, accountDetails.getEblBranch().getId()));
        }
        account.setFarmer(em.getReference(Person.class, accountDetails.getFarmer().getId()));

        try {
            em.persist(account);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return account.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<AccountDetails> retrieveCounties() throws MilesException {
        List<Account> accounts = new ArrayList<>();
        q = em.createNamedQuery("Account.findAll");
        try {
            accounts = q.getResultList();
        } catch (Exception e) {
        }

        return convertCountiesToAccountDetailsList(accounts);
    }

    @Override
    public AccountDetails retrieveAccount(int farmerId) throws MilesException {
        Account account;
        q = em.createNamedQuery("Account.findByFarmerId");
        q.setParameter("farmerId", farmerId);
        try {
            account = (Account) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertAccountToAccountDetails(account);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editAccount(AccountDetails accountDetails) throws MilesException {

        if (accountDetails == null) {
            throw new InvalidArgumentException("error_043_01");
        } else if (accountDetails.getId() == null) {
            throw new InvalidArgumentException("error_043_06");
        } else if (accountDetails.getAccountNumber() == null) {
            throw new InvalidArgumentException("error_043_02");
        } else if (accountDetails.getSolId() != null && accountDetails.getSolId().trim().length() > 45) {
            throw new InvalidArgumentException("error_043_03");
        } else if (accountDetails.getAccountNumber() != null && accountDetails.getAccountNumber().trim().length() > 45) {
            throw new InvalidArgumentException("error_043_04");
        }

        Account account;
        q = em.createNamedQuery("Account.findByAccountNumber");
        q.setParameter("accountNumber", accountDetails.getAccountNumber());
        try {
            account = (Account) q.getSingleResult();
        } catch (Exception e) {
            account = null;
        }

        if (account != null) {
            if (account.getId().equals(accountDetails.getId())) {
                throw new InvalidArgumentException("error_043_05");
            }
        }

        account = em.find(Account.class, accountDetails.getId());
        account.setId(accountDetails.getId());
        account.setSolId(accountDetails.getSolId());
        account.setSavings(accountDetails.getSavings());
        account.setAccountNumber(accountDetails.getAccountNumber());
        if (accountDetails.getEblBranch() != null) {
            account.setEblBranch(em.getReference(EblBranch.class, accountDetails.getEblBranch().getId()));
        }
        account.setFarmer(em.getReference(Person.class, accountDetails.getFarmer().getId()));

        try {
            em.merge(account);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeAccount(int id) throws MilesException {
        Account account = em.find(Account.class, id);
        try {
            em.remove(account);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public AccountDetails convertAccountToAccountDetails(Account account) {

        AccountDetails accountDetails = new AccountDetails();
        try {
            accountDetails.setId(account.getId());
        } catch (Exception e) {
        }
        try {
            accountDetails.setAccountNumber(account.getAccountNumber());
        } catch (Exception e) {
        }
        try {
            accountDetails.setSolId(account.getSolId());
        } catch (Exception e) {
        }
        try {
            accountDetails.setSavings(account.getSavings());
        } catch (Exception e) {
        }
        try {
            accountDetails.setFarmer((personService.convertPersonToPersonDetails(account.getFarmer())));
        } catch (Exception e) {
        }
        try {
            accountDetails.setEblBranch((eblBranchService.convertEblBranchToEblBranchDetails(account.getEblBranch())));
        } catch (Exception e) {
        }
        return accountDetails;

    }

    private List<AccountDetails> convertCountiesToAccountDetailsList(List<Account> accounts) {

        List<AccountDetails> accountDetailsList = new ArrayList<>();
        for (Account account : accounts) {
            accountDetailsList.add(convertAccountToAccountDetails(account));
        }

        return accountDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private EblBranchRequestsLocal eblBranchService;
}
