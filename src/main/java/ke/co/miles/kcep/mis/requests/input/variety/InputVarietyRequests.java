/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.input.variety;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.InputVariety;
import ke.co.miles.kcep.mis.entities.StaticInput;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.input.staticinput.StaticInputRequestsLocal;
import ke.co.miles.kcep.mis.utilities.InputVarietyDetails;

/**
 *
 * @author siech
 */
@Stateless
public class InputVarietyRequests extends EntityRequests implements InputVarietyRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addInputVariety(InputVarietyDetails inputVarietyDetails) throws MilesException {

        if (inputVarietyDetails == null) {
            throw new InvalidArgumentException("error_051_01");
        } else if (inputVarietyDetails.getVariety() != null && inputVarietyDetails.getVariety().length() > 45) {
            throw new InvalidArgumentException("error_051_02");
        } else if (inputVarietyDetails.getStaticInput() == null) {
            throw new InvalidArgumentException("error_051_03");
        }

        InputVariety inputVariety = new InputVariety();
        inputVariety.setVariety(inputVarietyDetails.getVariety());
        inputVariety.setStaticInput(em.getReference(StaticInput.class, inputVarietyDetails.getId()));

        try {
            em.persist(inputVariety);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return inputVariety.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<InputVarietyDetails> retrieveInputVarieties() throws MilesException {
        List<InputVariety> inputVarieties = new ArrayList<>();
        setQ(em.createNamedQuery("InputVariety.findAll"));
        try {
            inputVarieties = q.getResultList();
        } catch (Exception e) {
        }

        return convertInputVarietiesToInputVarietyDetailsList(inputVarieties);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<InputVarietyDetails> retrieveInputVarieties(int staticInputId) throws MilesException {
        List<InputVariety> inputVarieties = new ArrayList<>();
        setQ(em.createNamedQuery("InputVariety.findByStaticInputId"));
        q.setParameter("staticInputId", staticInputId);
        try {
            inputVarieties = q.getResultList();
        } catch (Exception e) {
        }

        return convertInputVarietiesToInputVarietyDetailsList(inputVarieties);
    }

    @Override
    public InputVarietyDetails retrieveInputVariety(int id) throws MilesException {
        InputVariety inputVariety;
        setQ(em.createNamedQuery("InputVariety.findById"));
        q.setParameter("id", id);
        try {
            inputVariety = (InputVariety) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertInputVarietyToInputVarietyDetails(inputVariety);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editInputVariety(InputVarietyDetails inputVarietyDetails) throws MilesException {

        if (inputVarietyDetails == null) {
            throw new InvalidArgumentException("error_051_01");
        } else if (inputVarietyDetails.getId() == null) {
            throw new InvalidArgumentException("error_051_04");
        } else if (inputVarietyDetails.getVariety() != null && inputVarietyDetails.getVariety().length() > 45) {
            throw new InvalidArgumentException("error_051_02");
        } else if (inputVarietyDetails.getStaticInput() == null) {
            throw new InvalidArgumentException("error_051_03");
        }

        InputVariety inputVariety = em.find(InputVariety.class, inputVarietyDetails.getId());
        inputVariety.setId(inputVarietyDetails.getId());
        inputVariety.setVariety(inputVarietyDetails.getVariety());
        inputVariety.setStaticInput(em.getReference(StaticInput.class, inputVarietyDetails.getId()));

        try {
            em.merge(inputVariety);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeInputVariety(int id) throws MilesException {
        InputVariety inputVariety = em.find(InputVariety.class, id);
        try {
            em.remove(inputVariety);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public InputVarietyDetails convertInputVarietyToInputVarietyDetails(InputVariety inputVariety) {

        InputVarietyDetails inputVarietyDetails;
        try {
            inputVarietyDetails = new InputVarietyDetails(inputVariety.getId());
        } catch (Exception e) {
            return null;
        }
        inputVarietyDetails.setVariety(inputVariety.getVariety());
        inputVarietyDetails.setStaticInput(inputService.
                convertStaticInputToStaticInputDetails(inputVariety.getStaticInput()));
        return inputVarietyDetails;

    }

    private List<InputVarietyDetails> convertInputVarietiesToInputVarietyDetailsList(List<InputVariety> inputVarieties) {

        List<InputVarietyDetails> inputVarietyDetailsList = new ArrayList<>();
        for (InputVariety inputVariety : inputVarieties) {
            inputVarietyDetailsList.add(convertInputVarietyToInputVarietyDetails(inputVariety));
        }

        return inputVarietyDetailsList;

    }

//</editor-fold>
    @EJB
    private StaticInputRequestsLocal inputService;
}
