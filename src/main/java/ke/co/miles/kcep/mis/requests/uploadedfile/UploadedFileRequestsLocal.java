/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.uploadedfile;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.UploadedFile;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.UploadedFileDetails;
import ke.co.miles.kcep.mis.utilities.UploadedFileTypeDetail;

/**
 *
 * @author siech
 */
@Local
public interface UploadedFileRequestsLocal {

    /**
     *
     * @param feedbackDetails details of the feedback record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addUploadedFile(UploadedFileDetails feedbackDetails) throws MilesException;

    /**
     *
     * @param feedbackType the type of feedback
     * @return the list of feedback record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<UploadedFileDetails> retrieveUploadedFiles(UploadedFileTypeDetail feedbackType) throws MilesException;

    /**
     *
     * @return the list of latest feedback records
     * @throws MilesException when the database is in an incorrect state
     */
    public List<UploadedFileDetails> retrieveLatestUploadedFiles() throws MilesException;

    /**
     *
     * @param id the unique identifier of the feedback record to be retrieved
     * @return the details of the feedback record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public UploadedFileDetails retrieveUploadedFile(int id) throws MilesException;

    /**
     *
     * @param feedbackDetails details of the feedback record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editUploadedFile(UploadedFileDetails feedbackDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the feedback record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeUploadedFile(int id) throws MilesException;

    /**
     *
     * @param feedback the feedback to be converted
     * @return the details of the converted feedback
     */
    public UploadedFileDetails convertUploadedFileToUploadedFileDetails(UploadedFile feedback);

    /**
     *
     * @param uploadedFileType the purpose of the file uploaded
     * @param regionId the unique identifier of the region where the uploader
     * belongs
     * @return the list of uploaded documents
     * @throws MilesException when the database is in an incorrect state
     */
    public List<UploadedFileDetails> retrieveRegionUploadedFiles(UploadedFileTypeDetail uploadedFileType, int regionId) throws MilesException;

    /**
     *
     * @param uploadedFileType the purpose of the file uploaded
     * @param countyId the unique identifier of the county where the uploader
     * belongs
     * @return the list of uploaded documents
     * @throws MilesException when the database is in an incorrect state
     */
    public List<UploadedFileDetails> retrieveCountyUploadedFiles(UploadedFileTypeDetail uploadedFileType, short countyId) throws MilesException;

    /**
     *
     * @param uploadedFileType the purpose of the file uploaded
     * @param subCountyId the unique identifier of the sub-county where the
     * uploader belongs
     * @return the list of uploaded documents
     * @throws MilesException when the database is in an incorrect state
     */
    public List<UploadedFileDetails> retrieveSubCountyUploadedFiles(UploadedFileTypeDetail uploadedFileType, int subCountyId) throws MilesException;

    /**
     *
     * @param uploadedFileType the purpose of the file uploaded
     * @param wardId the unique identifier of the ward where the uploader
     * belongs
     * @return the list of uploaded documents
     * @throws MilesException when the database is in an incorrect state
     */
    public List<UploadedFileDetails> retrieveWardUploadedFiles(UploadedFileTypeDetail uploadedFileType, int wardId) throws MilesException;

    /**
     *
     * @return the list of latest uploaded documents
     * @throws MilesException when the database is in an incorrect state
     */
    public List<UploadedFileDetails> retrieveLatestUploadedFile() throws MilesException;

}
