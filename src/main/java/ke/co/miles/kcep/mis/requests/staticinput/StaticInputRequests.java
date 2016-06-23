/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.staticinput;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.StaticInput;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.StaticInputDetails;

/**
 *
 * @author siech
 */
@Stateless
public class StaticInputRequests extends EntityRequests implements StaticInputRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addStaticInput(StaticInputDetails staticInputDetails) throws MilesException {

        if (staticInputDetails == null) {
            throw new InvalidArgumentException("error_021_01");
        } else if (staticInputDetails.getName() != null && staticInputDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_021_02");
        }

        StaticInput staticInput;
        q = em.createNamedQuery("StaticInput.findByName");
        q.setParameter("staticInput", staticInputDetails.getName());
        try {
            staticInput = (StaticInput) q.getSingleResult();
        } catch (Exception e) {
            staticInput = null;
        }
        if (staticInput != null) {
            throw new InvalidArgumentException("error_021_03");
        }

        staticInput = new StaticInput();
        staticInput.setName(staticInputDetails.getName());

        try {
            em.persist(staticInput);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return staticInput.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<StaticInputDetails> retrieveStaticInputs() throws MilesException {
        List<StaticInput> staticInputs = new ArrayList<>();
        q = em.createNamedQuery("StaticInput.findAll");
        try {
            staticInputs = q.getResultList();
        } catch (Exception e) {
        }

        return convertStaticInputsToStaticInputDetailsList(staticInputs);
    }

    @Override
    public StaticInputDetails retrieveStaticInput(int id) throws MilesException {
        StaticInput staticInput;
        q = em.createNamedQuery("StaticInput.findById");
        q.setParameter("id", id);
        try {
            staticInput = (StaticInput) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertStaticInputToStaticInputDetails(staticInput);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editStaticInput(StaticInputDetails staticInputDetails) throws MilesException {

        if (staticInputDetails == null) {
            throw new InvalidArgumentException("error_021_01");
        } else if (staticInputDetails.getId() == null) {
            throw new InvalidArgumentException("error_021_04");
        } else if (staticInputDetails.getName() != null && staticInputDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_021_02");
        }

        StaticInput staticInput;
        q = em.createNamedQuery("StaticInput.findByName");
        q.setParameter("staticInput", staticInputDetails.getName());
        try {
            staticInput = (StaticInput) q.getSingleResult();
        } catch (Exception e) {
            staticInput = null;
        }
        if (staticInput != null) {
            if (staticInput.getId().equals(staticInputDetails.getId())) {
                throw new InvalidArgumentException("error_021_03");
            }
        }

        staticInput = em.find(StaticInput.class, staticInputDetails.getId());
        staticInput.setId(staticInputDetails.getId());
        staticInput.setName(staticInputDetails.getName());

        try {
            em.merge(staticInput);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeStaticInput(int id) throws MilesException {
        StaticInput staticInput = em.find(StaticInput.class, id);
        try {
            em.remove(staticInput);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public StaticInputDetails convertStaticInputToStaticInputDetails(StaticInput staticInput) {

        StaticInputDetails staticInputDetails = new StaticInputDetails(staticInput.getId());
        staticInputDetails.setName(staticInput.getName());
        return staticInputDetails;

    }

    private List<StaticInputDetails> convertStaticInputsToStaticInputDetailsList(List<StaticInput> staticInputs) {

        List<StaticInputDetails> staticInputDetailsList = new ArrayList<>();
        for (StaticInput staticInput : staticInputs) {
            staticInputDetailsList.add(convertStaticInputToStaticInputDetails(staticInput));
        }

        return staticInputDetailsList;

    }

//</editor-fold>
}
