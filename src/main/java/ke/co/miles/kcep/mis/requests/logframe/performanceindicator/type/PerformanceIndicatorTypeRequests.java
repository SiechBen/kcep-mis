/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.performanceindicator.type;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.PerformanceIndicatorType;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorTypeDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PerformanceIndicatorTypeRequests extends EntityRequests implements PerformanceIndicatorTypeRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addPerformanceIndicatorType(PerformanceIndicatorTypeDetails performanceIndicatorTypeDetails) throws MilesException {

        if (performanceIndicatorTypeDetails == null) {
            throw new InvalidArgumentException("error_039_01");
        } else if (performanceIndicatorTypeDetails.getType() == null) {
            throw new InvalidArgumentException("error_039_02");
        } else if (performanceIndicatorTypeDetails.getType().length() > 45) {
            throw new InvalidArgumentException("error_039_03");
        }

        PerformanceIndicatorType performanceIndicatorType;
        setQ(getEm().createNamedQuery("PerformanceIndicatorType.findByType"));
        getQ().setParameter("type", performanceIndicatorTypeDetails.getType());
        try {
            performanceIndicatorType = (PerformanceIndicatorType) getQ().getSingleResult();
        } catch (Exception e) {
            performanceIndicatorType = null;
        }
        if (performanceIndicatorType != null) {
            throw new InvalidArgumentException("error_039_04");
        }

        performanceIndicatorType = new PerformanceIndicatorType();
        performanceIndicatorType.setType(performanceIndicatorTypeDetails.getType());

        try {
            getEm().persist(performanceIndicatorType);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return performanceIndicatorType.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<PerformanceIndicatorTypeDetails> retrievePerformanceIndicatorTypes() throws MilesException {
        List<PerformanceIndicatorType> performanceIndicatorTypes = new ArrayList<>();
        setQ(getEm().createNamedQuery("PerformanceIndicatorType.findAll"));
        try {
            performanceIndicatorTypes = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertPerformanceIndicatorTypesToPerformanceIndicatorTypeDetailsList(performanceIndicatorTypes);
    }

    @Override
    public PerformanceIndicatorTypeDetails retrievePerformanceIndicatorType(int id) throws MilesException {
        PerformanceIndicatorType performanceIndicatorType;
        setQ(getEm().createNamedQuery("PerformanceIndicatorType.findById"));
        getQ().setParameter("id", id);
        try {
            performanceIndicatorType = (PerformanceIndicatorType) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPerformanceIndicatorTypeToPerformanceIndicatorTypeDetails(performanceIndicatorType);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPerformanceIndicatorType(PerformanceIndicatorTypeDetails performanceIndicatorTypeDetails) throws MilesException {

        if (performanceIndicatorTypeDetails == null) {
            throw new InvalidArgumentException("error_039_01");
        } else if (performanceIndicatorTypeDetails.getId() == null) {
            throw new InvalidArgumentException("error_039_05");
        } else if (performanceIndicatorTypeDetails.getType() == null) {
            throw new InvalidArgumentException("error_039_02");
        } else if (performanceIndicatorTypeDetails.getType().length() > 45) {
            throw new InvalidArgumentException("error_039_03");
        }

        PerformanceIndicatorType performanceIndicatorType;
        setQ(getEm().createNamedQuery("PerformanceIndicatorType.findByType"));
        getQ().setParameter("type", performanceIndicatorTypeDetails.getType());
        try {
            performanceIndicatorType = (PerformanceIndicatorType) getQ().getSingleResult();
        } catch (Exception e) {
            performanceIndicatorType = null;
        }
        if (performanceIndicatorType != null) {
            if (performanceIndicatorType.getId().equals(performanceIndicatorTypeDetails.getId())) {
                throw new InvalidArgumentException("error_039_04");
            }
        }

        performanceIndicatorType = getEm().find(PerformanceIndicatorType.class, performanceIndicatorTypeDetails.getId());
        performanceIndicatorType.setId(performanceIndicatorTypeDetails.getId());
        performanceIndicatorType.setType(performanceIndicatorTypeDetails.getType());

        try {
            getEm().merge(performanceIndicatorType);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removePerformanceIndicatorType(int id) throws MilesException {
        PerformanceIndicatorType performanceIndicatorType = getEm().find(PerformanceIndicatorType.class, id);
        try {
            getEm().remove(performanceIndicatorType);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public PerformanceIndicatorTypeDetails convertPerformanceIndicatorTypeToPerformanceIndicatorTypeDetails(PerformanceIndicatorType performanceIndicatorType) {

        PerformanceIndicatorTypeDetails performanceIndicatorTypeDetails = new PerformanceIndicatorTypeDetails();
        try {
            performanceIndicatorTypeDetails.setId(performanceIndicatorType.getId());
        } catch (Exception e) {
        }
        try {
            performanceIndicatorTypeDetails.setType(performanceIndicatorType.getType());
        } catch (Exception e) {
        }
        return performanceIndicatorTypeDetails;

    }

    private List<PerformanceIndicatorTypeDetails> convertPerformanceIndicatorTypesToPerformanceIndicatorTypeDetailsList(List<PerformanceIndicatorType> performanceIndicatorTypes) {

        List<PerformanceIndicatorTypeDetails> performanceIndicatorTypeDetailsList = new ArrayList<>();
        for (PerformanceIndicatorType performanceIndicatorType : performanceIndicatorTypes) {
            performanceIndicatorTypeDetailsList.add(convertPerformanceIndicatorTypeToPerformanceIndicatorTypeDetails(performanceIndicatorType));
        }

        return performanceIndicatorTypeDetailsList;

    }

//</editor-fold>
}
