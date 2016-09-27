/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.performanceindicator.values;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.PerformanceIndicatorValues;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorValuesDetails;

/**
 *
 * @author siech
 */
@Local
public interface PerformanceIndicatorValuesRequestsLocal {

    /**
     *
     * @param performanceIndicatorDetails details of the performance indicator
     * record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPerformanceIndicatorValues(PerformanceIndicatorValuesDetails performanceIndicatorDetails) throws MilesException;

    /**
     *
     * @return the list of performance indicator record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<PerformanceIndicatorDetails, LinkedList<PerformanceIndicatorValuesDetails>>
            retrievePerformanceIndicators() throws MilesException;

    /**
     *
     * @return the list of performance indicator record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PerformanceIndicatorValuesDetails> retrievePerformanceIndicatorValues() throws MilesException;

    /**
     *
     * @param id the unique identifier of the performance indicator record to be
     * retrieved
     * @return the details of the performance indicator record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public PerformanceIndicatorValuesDetails retrievePerformanceIndicatorValues(int id) throws MilesException;

    /**
     *
     * @param performanceIndicatorDetails details of the performance indicator
     * record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPerformanceIndicatorValues(PerformanceIndicatorValuesDetails performanceIndicatorDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the performance indicator record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removePerformanceIndicatorValues(int id) throws MilesException;

    /**
     *
     * @param performanceIndicator the performance indicator record to be
     * converted to performance indicator details
     * @return the result of the conversion
     */
    public PerformanceIndicatorValuesDetails convertPerformanceIndicatorValuesToPerformanceIndicatorValuesDetails(PerformanceIndicatorValues performanceIndicator);

    /**
     *
     * @param year the year for which the performance indicator values are to be
     * added
     * @throws MilesException when the database is in an incorrect state
     */
    public void addYearOfUse(short year) throws MilesException;

    /**
     *
     * @return the list of years of use
     * @throws MilesException when the database is in an incorrect state
     */
    public Object retrieveYearsOfUse() throws MilesException;
}
