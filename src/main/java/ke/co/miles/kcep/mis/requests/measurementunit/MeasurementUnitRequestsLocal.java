/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.measurementunit;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.MeasurementUnit;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;

/**
 *
 * @author siech
 */
@Local
public interface MeasurementUnitRequestsLocal {

    /**
     *
     * @param measurementUnitDetails details of the measurement unit record to
     * be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addMeasurementUnit(MeasurementUnitDetails measurementUnitDetails) throws MilesException;

    /**
     *
     * @return the list of measurement unit record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<MeasurementUnitDetails> retrieveMeasurementUnits() throws MilesException;

    /**
     *
     * @return the list of measurement unit record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<MeasurementUnitDetails> retrieveProgrammeMeasurementUnits() throws MilesException;

    /**
     *
     * @param id the unique identifier of the measurement unit record to be
     * retrieved
     * @return the details of the measurement unit record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public MeasurementUnitDetails retrieveMeasurementUnit(int id) throws MilesException;

    /**
     *
     * @param measurementUnitDetails details of the measurement unit record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editMeasurementUnit(MeasurementUnitDetails measurementUnitDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the measurement unit record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeMeasurementUnit(int id) throws MilesException;

    /**
     *
     * @param measurementUnit the measurement unit to be converted
     * @return the details of the converted measurement unit
     */
    public MeasurementUnitDetails convertMeasurementUnitToMeasurementUnitDetails(MeasurementUnit measurementUnit);

}
