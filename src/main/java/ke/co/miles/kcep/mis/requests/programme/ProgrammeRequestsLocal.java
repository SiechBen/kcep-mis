/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.programme;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ProgrammeDetails;

/**
 *
 * @author siech
 */
@Local
public interface ProgrammeRequestsLocal {

    /**
     *
     * @param programmeDetails details of the programme record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addProgramme(ProgrammeDetails programmeDetails) throws MilesException;

    /**
     *
     * @return the list of programme record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ProgrammeDetails> retrieveProgrammes() throws MilesException;

    /**
     *
     * @param id the unique identifier of the programme record to be retrieved
     * @return the details of the programme record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ProgrammeDetails retrieveProgramme(int id) throws MilesException;

    /**
     *
     * @param programmeDetails details of the programme record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editProgramme(ProgrammeDetails programmeDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the programme record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeProgramme(int id) throws MilesException;

}
