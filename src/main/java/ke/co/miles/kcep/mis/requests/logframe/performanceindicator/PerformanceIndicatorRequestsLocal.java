/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.performanceindicator;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;

/**
 *
 * @author siech
 */
@Local
public interface PerformanceIndicatorRequestsLocal {

    /**
     *
     * @param performanceIndicatorDetails details of the performanceIndicator record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPerformanceIndicator(PerformanceIndicatorDetails performanceIndicatorDetails) throws MilesException;

    /**
     *
     * @return the list of performanceIndicator record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PerformanceIndicatorDetails> retrievePerformanceIndicators() throws MilesException;

    /**
     *
     * @param id the unique identifier of the performanceIndicator record to be retrieved
     * @return the details of the performanceIndicator record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public PerformanceIndicatorDetails retrievePerformanceIndicator(int id) throws MilesException;

    /**
     *
     * @param performanceIndicatorDetails details of the performanceIndicator record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPerformanceIndicator(PerformanceIndicatorDetails performanceIndicatorDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the performanceIndicator record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removePerformanceIndicator(int id) throws MilesException;

}
