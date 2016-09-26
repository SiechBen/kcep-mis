/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.account.eblbranch;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.EblBranch;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.EblBranchDetails;

/**
 *
 * @author siech
 */
@Stateless
public class EblBranchRequests extends EntityRequests implements EblBranchRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addEblBranch(EblBranchDetails eblBranchDetails) throws MilesException {

        if (eblBranchDetails == null) {
            throw new InvalidArgumentException("error_044_01");
        } else if (eblBranchDetails.getName() == null) {
            throw new InvalidArgumentException("error_044_02");
        } else if (eblBranchDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_044_03");
        }

        EblBranch eblBranch;
        setQ(em.createNamedQuery("EblBranch.findByName"));
        q.setParameter("name", eblBranchDetails.getName());
        try {
            eblBranch = (EblBranch) q.getSingleResult();
        } catch (Exception e) {
            eblBranch = null;
        }
        if (eblBranch != null) {
            throw new InvalidArgumentException("error_044_04");
        }

        eblBranch = new EblBranch();
        eblBranch.setName(eblBranchDetails.getName());

        try {
            em.persist(eblBranch);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return eblBranch.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<EblBranchDetails> retrieveEblBranches() throws MilesException {
        List<EblBranch> eblBranches = new ArrayList<>();
        setQ(em.createNamedQuery("EblBranch.findAll"));
        try {
            eblBranches = q.getResultList();
        } catch (Exception e) {
        }

        return convertEblBranchesToEblBranchDetailsList(eblBranches);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EblBranchDetails> retrieveEblBranches(short countyId) throws MilesException {
        List<EblBranch> eblBranches = new ArrayList<>();
        setQ(em.createNamedQuery("EblBranch.findByCountyId"));
        q.setParameter("countyId", countyId);
        try {
            eblBranches = q.getResultList();
        } catch (Exception e) {
        }

        return convertEblBranchesToEblBranchDetailsList(eblBranches);
    }

    @Override
    public EblBranchDetails retrieveEblBranch(int id) throws MilesException {
        EblBranch eblBranch;
        setQ(em.createNamedQuery("EblBranch.findById"));
        q.setParameter("id", id);
        try {
            eblBranch = (EblBranch) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertEblBranchToEblBranchDetails(eblBranch);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editEblBranch(EblBranchDetails eblBranchDetails) throws MilesException {

        if (eblBranchDetails == null) {
            throw new InvalidArgumentException("error_044_01");
        } else if (eblBranchDetails.getId() == null) {
            throw new InvalidArgumentException("error_044_05");
        } else if (eblBranchDetails.getName() == null) {
            throw new InvalidArgumentException("error_044_02");
        } else if (eblBranchDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_044_03");
        }

        EblBranch eblBranch;
        setQ(em.createNamedQuery("EblBranch.findByName"));
        q.setParameter("name", eblBranchDetails.getName());
        try {
            eblBranch = (EblBranch) q.getSingleResult();
        } catch (Exception e) {
            eblBranch = null;
        }
        if (eblBranch != null) {
            if (eblBranch.getId().equals(eblBranchDetails.getId())) {
                throw new InvalidArgumentException("error_044_04");
            }
        }

        eblBranch = em.find(EblBranch.class, eblBranchDetails.getId());
        eblBranch.setId(eblBranchDetails.getId());
        eblBranch.setName(eblBranchDetails.getName());

        try {
            em.merge(eblBranch);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeEblBranch(int id) throws MilesException {
        EblBranch eblBranch = em.find(EblBranch.class, id);
        try {
            em.remove(eblBranch);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public EblBranchDetails convertEblBranchToEblBranchDetails(EblBranch eblBranch) {

        EblBranchDetails eblBranchDetails = new EblBranchDetails();
        try {
            eblBranchDetails.setId(eblBranch.getId());
        } catch (Exception e) {
        }
        try {
            eblBranchDetails.setName(eblBranch.getName());
        } catch (Exception e) {
        }

        return eblBranchDetails;

    }

    private List<EblBranchDetails> convertEblBranchesToEblBranchDetailsList(List<EblBranch> eblBranches) {

        List<EblBranchDetails> eblBranchDetailsList = new ArrayList<>();
        for (EblBranch eblBranch : eblBranches) {
            eblBranchDetailsList.add(convertEblBranchToEblBranchDetails(eblBranch));
        }

        return eblBranchDetailsList;

    }

//</editor-fold>
}
