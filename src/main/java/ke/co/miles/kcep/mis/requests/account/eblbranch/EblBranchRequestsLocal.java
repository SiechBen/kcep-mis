/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.account.eblbranch;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.EblBranch;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.EblBranchDetails;

/**
 *
 * @author siech
 */
@Local
public interface EblBranchRequestsLocal {

    /**
     *
     * @param eblBranchDetails details of the divisional location record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addEblBranch(EblBranchDetails eblBranchDetails) throws MilesException;

    /**
     *
     * @return the list of divisional location record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<EblBranchDetails> retrieveEblBranches() throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county of which the
     * sub-counties are part
     * @return the list of divisional location record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<EblBranchDetails> retrieveEblBranches(short countyId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the divisional location record to be
     * retrieved
     * @return the details of the divisional location record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public EblBranchDetails retrieveEblBranch(int id) throws MilesException;

    /**
     *
     * @param eblBranchDetails details of the divisional location record to be
     * edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editEblBranch(EblBranchDetails eblBranchDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the divisional location record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeEblBranch(int id) throws MilesException;

    /**
     *
     * @param eblBranch the divisional location to be converted
     * @return the details of the converted divisional location
     */
    public EblBranchDetails convertEblBranchToEblBranchDetails(EblBranch eblBranch);

}
