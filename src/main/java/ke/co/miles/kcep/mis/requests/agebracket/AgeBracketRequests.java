/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.agebracket;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.AgeBracket;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.AgeBracketDetails;

/**
 *
 * @author siech
 */
@Stateless
public class AgeBracketRequests extends EntityRequests implements AgeBracketRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addAgeBracket(AgeBracketDetails ageBracketDetails) throws MilesException {

        if (ageBracketDetails == null) {
            throw new InvalidArgumentException("error_013_01");
        } else if (ageBracketDetails.getBracket() == null) {
            throw new InvalidArgumentException("error_013_02");
        } else if (ageBracketDetails.getBracket().length() > 45) {
            throw new InvalidArgumentException("error_013_03");
        }

        AgeBracket ageBracket;
        setQ(em.createNamedQuery("AgeBracket.findByBracket"));
        q.setParameter("bracket", ageBracketDetails.getBracket());
        try {
            ageBracket = (AgeBracket) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            ageBracket = null;
        }
        if (ageBracket != null) {
            throw new InvalidArgumentException("error_013_04");
        }

        ageBracket = new AgeBracket();
        ageBracket.setBracket(ageBracketDetails.getBracket());

        try {
            em.persist(ageBracket);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return ageBracket.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<AgeBracketDetails> retrieveAgeBrackets() throws MilesException {
        List<AgeBracket> ageBrackets = new ArrayList<>();
        setQ(em.createNamedQuery("AgeBracket.findAll"));
        try {
            ageBrackets = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertAgeBracketsToAgeBracketDetailsList(ageBrackets);
    }

    @Override
    public AgeBracketDetails retrieveAgeBracket(int id) throws MilesException {
        AgeBracket ageBracket;
        setQ(em.createNamedQuery("AgeBracket.findById"));
        q.setParameter("id", id);
        try {
            ageBracket = (AgeBracket) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertAgeBracketToAgeBracketDetails(ageBracket);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editAgeBracket(AgeBracketDetails ageBracketDetails) throws MilesException {

        if (ageBracketDetails == null) {
            throw new InvalidArgumentException("error_013_01");
        } else if (ageBracketDetails.getId() == null) {
            throw new InvalidArgumentException("error_013_05");
        } else if (ageBracketDetails.getBracket() == null) {
            throw new InvalidArgumentException("error_013_02");
        } else if (ageBracketDetails.getBracket().length() > 45) {
            throw new InvalidArgumentException("error_013_03");
        }

        AgeBracket ageBracket;
        setQ(em.createNamedQuery("AgeBracket.findByBracket"));
        q.setParameter("bracket", ageBracketDetails.getBracket());
        try {
            ageBracket = (AgeBracket) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            ageBracket = null;
        }
        if (ageBracket != null) {
            if (ageBracket.getId().equals(ageBracketDetails.getId())) {
                throw new InvalidArgumentException("error_013_04");
            }
        }

        ageBracket = em.find(AgeBracket.class, ageBracketDetails.getId());
        ageBracket.setBracket(ageBracketDetails.getBracket());

        try {
            em.merge(ageBracket);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeAgeBracket(int id) throws MilesException {
        AgeBracket ageBracket = em.find(AgeBracket.class, id);
        try {
            em.remove(ageBracket);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public AgeBracketDetails convertAgeBracketToAgeBracketDetails(AgeBracket ageBracket) {

        AgeBracketDetails ageBracketDetails = new AgeBracketDetails(ageBracket.getId());
        ageBracketDetails.setBracket(ageBracket.getBracket());
        return ageBracketDetails;

    }

    private List<AgeBracketDetails> convertAgeBracketsToAgeBracketDetailsList(List<AgeBracket> ageBrackets) {

        List<AgeBracketDetails> ageBracketDetailsList = new ArrayList<>();
        for (AgeBracket ageBracket : ageBrackets) {
            ageBracketDetailsList.add(convertAgeBracketToAgeBracketDetails(ageBracket));

        }
        return ageBracketDetailsList;

    }

//</editor-fold>
}
