/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.measurementunit;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.MeasurementUnit;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;

/**
 *
 * @author siech
 */
@Stateless
public class MeasurementUnitRequests extends EntityRequests implements MeasurementUnitRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addMeasurementUnit(MeasurementUnitDetails measurementUnitDetails) throws MilesException {

        if (measurementUnitDetails == null) {
            throw new InvalidArgumentException("error_007_01");
        } else if (measurementUnitDetails.getUnit() == null) {
            throw new InvalidArgumentException("error_007_02");
        } else if (measurementUnitDetails.getUnit().length() > 45) {
            throw new InvalidArgumentException("error_007_03");
        } else if (measurementUnitDetails.getSymbol().length() > 20) {
            throw new InvalidArgumentException("error_007_05");
        }

        MeasurementUnit measurementUnit;
        setQ(getEm().createNamedQuery("MeasurementUnit.findByUnit"));
        getQ().setParameter("unit", measurementUnitDetails.getUnit());
        try {
            measurementUnit = (MeasurementUnit) getQ().getSingleResult();
        } catch (Exception e) {
            measurementUnit = null;
        }
        if (measurementUnit != null) {
            throw new InvalidArgumentException("error_007_04");
        }

        measurementUnit = new MeasurementUnit();
        measurementUnit.setUnit(measurementUnitDetails.getUnit());
        measurementUnit.setSymbol(measurementUnitDetails.getSymbol());

        try {
            getEm().persist(measurementUnit);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return measurementUnit.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<MeasurementUnitDetails> retrieveMeasurementUnits() throws MilesException {
        List<MeasurementUnit> measurementUnits = new ArrayList<>();
        setQ(getEm().createNamedQuery("MeasurementUnit.findAll"));
        try {
            measurementUnits = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertMeasurementUnitsToMeasurementUnitDetailsList(measurementUnits);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MeasurementUnitDetails> retrievePlanningMeasurementUnits() throws MilesException {
        List<MeasurementUnit> measurementUnits = new ArrayList<>();
        setQ(getEm().createNamedQuery("MeasurementUnit.findByUse"));
        getQ().setParameter("use", "Planning");
        try {
            measurementUnits = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertMeasurementUnitsToMeasurementUnitDetailsList(measurementUnits);
    }

    @Override
    public MeasurementUnitDetails retrieveMeasurementUnit(int id) throws MilesException {
        MeasurementUnit measurementUnit;
        setQ(getEm().createNamedQuery("MeasurementUnit.findById"));
        getQ().setParameter("id", id);
        try {
            measurementUnit = (MeasurementUnit) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertMeasurementUnitToMeasurementUnitDetails(measurementUnit);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editMeasurementUnit(MeasurementUnitDetails measurementUnitDetails) throws MilesException {

        if (measurementUnitDetails == null) {
            throw new InvalidArgumentException("error_007_01");
        } else if (measurementUnitDetails.getId() == null) {
            throw new InvalidArgumentException("error_007_06");
        } else if (measurementUnitDetails.getUnit() == null) {
            throw new InvalidArgumentException("error_007_02");
        } else if (measurementUnitDetails.getUnit().length() > 200) {
            throw new InvalidArgumentException("error_007_03");
        } else if (measurementUnitDetails.getSymbol().length() > 20) {
            throw new InvalidArgumentException("error_007_05");
        }

        MeasurementUnit measurementUnit;
        setQ(getEm().createNamedQuery("MeasurementUnit.findByUnit"));
        getQ().setParameter("unit", measurementUnitDetails.getUnit());
        try {
            measurementUnit = (MeasurementUnit) getQ().getSingleResult();
        } catch (Exception e) {
            measurementUnit = null;
        }
        if (measurementUnit != null) {
            if (measurementUnit.getId().equals(measurementUnitDetails.getId())) {
                throw new InvalidArgumentException("error_007_04");
            }
        }

        measurementUnit = getEm().find(MeasurementUnit.class, measurementUnitDetails.getId());
        measurementUnit.setUnit(measurementUnitDetails.getUnit());
        measurementUnit.setSymbol(measurementUnitDetails.getSymbol());

        try {
            getEm().merge(measurementUnit);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeMeasurementUnit(int id) throws MilesException {
        MeasurementUnit measurementUnit = getEm().find(MeasurementUnit.class, id);
        try {
            getEm().remove(measurementUnit);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public MeasurementUnitDetails convertMeasurementUnitToMeasurementUnitDetails(MeasurementUnit measurementUnit) {

        MeasurementUnitDetails measurementUnitDetails = new MeasurementUnitDetails(measurementUnit.getId());
        measurementUnitDetails.setUnit(measurementUnit.getUnit());
        measurementUnitDetails.setSymbol(measurementUnit.getSymbol());
        return measurementUnitDetails;

    }

    private List<MeasurementUnitDetails> convertMeasurementUnitsToMeasurementUnitDetailsList(List<MeasurementUnit> measurementUnits) {

        List<MeasurementUnitDetails> measurementUnitDetailsList = new ArrayList<>();
        for (MeasurementUnit measurementUnit : measurementUnits) {
            measurementUnitDetailsList.add(convertMeasurementUnitToMeasurementUnitDetails(measurementUnit));
        }

        return measurementUnitDetailsList;

    }

//</editor-fold>
}
