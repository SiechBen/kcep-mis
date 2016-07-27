/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.inputscollection;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.InputType;
import ke.co.miles.kcep.mis.entities.InputsCollection;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.StaticInput;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.input.staticinput.StaticInputRequestsLocal;
import ke.co.miles.kcep.mis.requests.input.type.InputTypeRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.InputsCollectionDetails;

/**
 *
 * @author siech
 */
@Stateless
public class InputsCollectionRequests extends EntityRequests implements InputsCollectionRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addInputsCollection(InputsCollectionDetails inputsCollectionDetails) throws MilesException {

        if (inputsCollectionDetails == null) {
            throw new InvalidArgumentException("error_047_01");
        } else if (inputsCollectionDetails.getFarmer() == null) {
            throw new InvalidArgumentException("error_047_02");
        } else if (inputsCollectionDetails.getAgroDealer() == null) {
            throw new InvalidArgumentException("error_047_03");
        } else if (inputsCollectionDetails.getQuantity() != null && inputsCollectionDetails.getQuantity().trim().length() > 45) {
            throw new InvalidArgumentException("error_047_05");
        }

        InputsCollection inputsCollection = new InputsCollection();
        inputsCollection.setDateCollected(inputsCollectionDetails.getDateCollected());
        inputsCollection.setQuantity(inputsCollectionDetails.getQuantity());
        inputsCollection.setFarmer(em.find(Person.class, inputsCollectionDetails.getFarmer().getId()));
        inputsCollection.setAgroDealer(em.find(Person.class, inputsCollectionDetails.getAgroDealer().getId()));
        if (inputsCollectionDetails.getInputType() != null) {
            inputsCollection.setInputType(em.find(InputType.class, inputsCollectionDetails.getInputType().getId()));
        }
        if (inputsCollectionDetails.getStaticInput() != null) {
            inputsCollection.setStaticInput(em.find(StaticInput.class, inputsCollectionDetails.getStaticInput().getId()));
        }

        try {
            em.persist(inputsCollection);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return inputsCollection.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<InputsCollectionDetails> retrieveInputsCollections(int farmerId) throws MilesException {
        List<InputsCollection> inputsCollection;
        q = em.createNamedQuery("InputsCollection.findByFarmerId");
        q.setParameter("farmerId", farmerId);
        try {
            inputsCollection = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertCountiesToInputsCollectionDetailsList(inputsCollection);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editInputsCollection(InputsCollectionDetails inputsCollectionDetails) throws MilesException {

        if (inputsCollectionDetails == null) {
            throw new InvalidArgumentException("error_047_01");
        } else if (inputsCollectionDetails.getId() == null) {
            throw new InvalidArgumentException("error_047_06");
        } else if (inputsCollectionDetails.getFarmer() == null) {
            throw new InvalidArgumentException("error_047_02");
        } else if (inputsCollectionDetails.getAgroDealer() == null) {
            throw new InvalidArgumentException("error_047_03");
        } else if (inputsCollectionDetails.getQuantity() != null && inputsCollectionDetails.getQuantity().trim().length() > 45) {
            throw new InvalidArgumentException("error_047_05");
        }

        InputsCollection inputsCollection = em.find(InputsCollection.class, inputsCollectionDetails.getId());
        inputsCollection.setId(inputsCollectionDetails.getId());
        inputsCollection.setDateCollected(inputsCollectionDetails.getDateCollected());
        inputsCollection.setQuantity(inputsCollectionDetails.getQuantity());
        inputsCollection.setFarmer(em.find(Person.class, inputsCollectionDetails.getFarmer().getId()));
        inputsCollection.setAgroDealer(em.find(Person.class, inputsCollectionDetails.getAgroDealer().getId()));
        if (inputsCollectionDetails.getInputType() != null) {
            inputsCollection.setInputType(em.find(InputType.class, inputsCollectionDetails.getInputType().getId()));
        }
        if (inputsCollectionDetails.getStaticInput() != null) {
            inputsCollection.setStaticInput(em.find(StaticInput.class, inputsCollectionDetails.getStaticInput().getId()));
        }

        try {
            em.merge(inputsCollection);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeInputsCollection(int id) throws MilesException {
        InputsCollection inputsCollection = em.find(InputsCollection.class, id);
        try {
            em.remove(inputsCollection);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public InputsCollectionDetails convertInputsCollectionToInputsCollectionDetails(InputsCollection inputsCollection
    ) {

        InputsCollectionDetails inputsCollectionDetails = new InputsCollectionDetails(inputsCollection.getId());
        try {
            inputsCollectionDetails.setStaticInput(staticInputService.convertStaticInputToStaticInputDetails(inputsCollection.getStaticInput()));
        } catch (Exception e) {
        }
        try {
            inputsCollectionDetails.setInputType(inputTypeService.convertInputTypeToInputTypeDetails(inputsCollection.getInputType()));
        } catch (Exception e) {
        }
        try {
            inputsCollectionDetails.setAgroDealer((personService.convertPersonToPersonDetails(inputsCollection.getAgroDealer())));
        } catch (Exception e) {
        }
        try {
            inputsCollectionDetails.setFarmer((personService.convertPersonToPersonDetails(inputsCollection.getFarmer())));
        } catch (Exception e) {
        }
        inputsCollectionDetails.setDateCollected(inputsCollection.getDateCollected());
        inputsCollectionDetails.setQuantity(inputsCollection.getQuantity());

        return inputsCollectionDetails;

    }

    private List<InputsCollectionDetails> convertCountiesToInputsCollectionDetailsList(List<InputsCollection> inputsCollections) {

        List<InputsCollectionDetails> inputsCollectionDetailsList = new ArrayList<>();
        for (InputsCollection inputsCollection : inputsCollections) {
            inputsCollectionDetailsList.add(convertInputsCollectionToInputsCollectionDetails(inputsCollection));
        }

        return inputsCollectionDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private InputTypeRequestsLocal inputTypeService;
    @EJB
    private StaticInputRequestsLocal staticInputService;
}
