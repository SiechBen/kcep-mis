/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.data.samplefarmer;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.NumberDescription;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.NumberDescriptionDetails;

/**
 *
 * @author siech
 */
@Stateless
public class NumberDescriptionRequests extends EntityRequests implements NumberDescriptionRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addNumberDescription(NumberDescriptionDetails numberDescriptionDetails) throws MilesException {

        if (numberDescriptionDetails == null) {
            throw new InvalidArgumentException("error_014_01");
        } else if (numberDescriptionDetails.getDescription() == null) {
            throw new InvalidArgumentException("error_014_02");
        } else if (numberDescriptionDetails.getDescription().length() > 200) {
            throw new InvalidArgumentException("error_014_03");
        }

        NumberDescription numberDescription;
        setQ(getEm().createNamedQuery("NumberDescription.findByDescription"));
        getQ().setParameter("bracket", numberDescriptionDetails.getDescription());
        try {
            numberDescription = (NumberDescription) getQ().getSingleResult();
        } catch (Exception e) {
            numberDescription = null;
        }
        if (numberDescription != null) {
            throw new InvalidArgumentException("error_014_04");
        }

        numberDescription = new NumberDescription();
        numberDescription.setDescription(numberDescriptionDetails.getDescription());

        try {
            getEm().persist(numberDescription);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return numberDescription.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<NumberDescriptionDetails> retrieveNumberDescriptions() throws MilesException {
        List<NumberDescription> numberDescriptions = new ArrayList<>();
        setQ(getEm().createNamedQuery("NumberDescription.findAll"));
        try {
            numberDescriptions = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertNumberDescriptionsToNumberDescriptionDetailsList(numberDescriptions);
    }

    @Override
    public NumberDescriptionDetails retrieveNumberDescription(int id) throws MilesException {
        NumberDescription numberDescription;
        setQ(getEm().createNamedQuery("NumberDescription.findById"));
        getQ().setParameter("id", id);
        try {
            numberDescription = (NumberDescription) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertNumberDescriptionToNumberDescriptionDetails(numberDescription);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editNumberDescription(NumberDescriptionDetails numberDescriptionDetails) throws MilesException {

        if (numberDescriptionDetails == null) {
            throw new InvalidArgumentException("error_014_01");
        } else if (numberDescriptionDetails.getId() == null) {
            throw new InvalidArgumentException("error_014_05");
        } else if (numberDescriptionDetails.getDescription() == null) {
            throw new InvalidArgumentException("error_014_02");
        } else if (numberDescriptionDetails.getDescription().length() > 200) {
            throw new InvalidArgumentException("error_014_03");
        }

        NumberDescription numberDescription;
        setQ(getEm().createNamedQuery("NumberDescription.findByDescription"));
        getQ().setParameter("bracket", numberDescriptionDetails.getDescription());
        try {
            numberDescription = (NumberDescription) getQ().getSingleResult();
        } catch (Exception e) {
            numberDescription = null;
        }
        if (numberDescription != null) {
            if (numberDescription.getId().equals(numberDescriptionDetails.getId())) {
                throw new InvalidArgumentException("error_014_04");
            }
        }

        numberDescription = getEm().find(NumberDescription.class, numberDescriptionDetails.getId());
        numberDescription.setDescription(numberDescriptionDetails.getDescription());

        try {
            getEm().merge(numberDescription);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeNumberDescription(int id) throws MilesException {
        NumberDescription numberDescription = getEm().find(NumberDescription.class, id);
        try {
            getEm().remove(numberDescription);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public NumberDescriptionDetails convertNumberDescriptionToNumberDescriptionDetails(NumberDescription numberDescription) {

        NumberDescriptionDetails numberDescriptionDetails = new NumberDescriptionDetails(numberDescription.getId());
        numberDescriptionDetails.setDescription(numberDescription.getDescription());
        return numberDescriptionDetails;

    }

    private List<NumberDescriptionDetails> convertNumberDescriptionsToNumberDescriptionDetailsList(List<NumberDescription> numberDescriptions) {

        List<NumberDescriptionDetails> numberDescriptionDetailsList = new ArrayList<>();
        for (NumberDescription numberDescription : numberDescriptions) {
            numberDescriptionDetailsList.add(convertNumberDescriptionToNumberDescriptionDetails(numberDescription));
        }

        return numberDescriptionDetailsList;

    }

//</editor-fold>
}
