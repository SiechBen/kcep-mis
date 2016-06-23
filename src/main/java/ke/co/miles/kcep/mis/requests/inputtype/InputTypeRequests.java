/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.inputtype;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.InputType;
import ke.co.miles.kcep.mis.entities.StaticInput;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.staticinput.StaticInputRequestsLocal;
import ke.co.miles.kcep.mis.utilities.InputTypeDetails;
import ke.co.miles.kcep.mis.utilities.StaticInputDetails;

/**
 *
 * @author siech
 */
@Stateless
public class InputTypeRequests extends EntityRequests implements InputTypeRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">  
    @Override
    public int addInputType(InputTypeDetails inputTypeDetails) throws MilesException {

        if (inputTypeDetails == null) {
            throw new InvalidArgumentException("error_020_01");
        }

        InputType inputType = new InputType();
        inputType.setType(inputTypeDetails.getType());
        if (inputTypeDetails.getStaticInput().getId() != null) {
            inputType.setStaticInput(em.find(StaticInput.class, inputTypeDetails.getStaticInput().getId()));
        }

        try {
            em.persist(inputType);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return inputType.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public InputTypeDetails retrieveInputType(int id) throws MilesException {

        q = em.createNamedQuery("InputType.findById");
        q.setParameter("id", id);
        InputType inputType;
        try {
            inputType = (InputType) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertInputTypeToInputTypeDetails(inputType);

    }

    @Override
    public List<InputTypeDetails> retrieveInputTypes() throws MilesException {

        q = em.createNamedQuery("InputType.findAll");
        List<InputType> inputTypes;
        try {
            inputTypes = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertInputTypesToInputTypeDetailsList(inputTypes);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editInputType(InputTypeDetails inputTypeDetails) throws MilesException {

        if (inputTypeDetails == null) {
            throw new InvalidArgumentException("error_020_01");
        } else if (inputTypeDetails.getId() == null) {
            throw new InvalidArgumentException("error_020_02");
        }

        InputType inputType = em.find(InputType.class, inputTypeDetails.getId());
        inputType.setId(inputTypeDetails.getId());
        inputType.setType(inputTypeDetails.getType());
        if (inputTypeDetails.getStaticInput().getId() != null) {
            inputType.setStaticInput(em.find(StaticInput.class, inputTypeDetails.getStaticInput().getId()));
        }

        try {
            em.merge(inputType);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeInputType(int id) throws MilesException {

        InputType inputType = em.find(InputType.class, id);
        try {
            em.remove(inputType);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert"> 
    @Override
    public InputTypeDetails convertInputTypeToInputTypeDetails(InputType inputType) {

        StaticInputDetails staticInputDetails = null;
        if (inputType.getStaticInput() != null) {
            staticInputDetails = staticInputService.convertStaticInputToStaticInputDetails(inputType.getStaticInput());
        }

        InputTypeDetails inputTypeDetails = new InputTypeDetails(inputType.getId());
        inputTypeDetails.setStaticInput(staticInputDetails);
        inputTypeDetails.setType(inputType.getType());

        return inputTypeDetails;

    }

    private List<InputTypeDetails> convertInputTypesToInputTypeDetailsList(List<InputType> inputTypes) {

        List<InputTypeDetails> inputTypeDetailsList = new ArrayList<>();
        for (InputType inputType : inputTypes) {
            inputTypeDetailsList.add(convertInputTypeToInputTypeDetails(inputType));
        }

        return inputTypeDetailsList;

    }

//</editor-fold>
    @EJB
    private StaticInputRequestsLocal staticInputService;
}
