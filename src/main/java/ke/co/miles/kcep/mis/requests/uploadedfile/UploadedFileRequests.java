/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.uploadedfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.UploadedFile;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.UploadedFileDetails;
import ke.co.miles.kcep.mis.utilities.UploadedFileTypeDetail;

/**
 *
 * @author siech
 */
@Stateless
public class UploadedFileRequests extends EntityRequests implements UploadedFileRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addUploadedFile(UploadedFileDetails uploadedFileDetails) throws MilesException {

        if (uploadedFileDetails == null) {
            throw new InvalidArgumentException("error_057_01");
        } else if (uploadedFileDetails.getName() == null) {
            throw new InvalidArgumentException("error_057_02");
        } else if (uploadedFileDetails.getPurpose() == null) {
            throw new InvalidArgumentException("error_057_03");
        } else if (uploadedFileDetails.getUploader() == null) {
            throw new InvalidArgumentException("error_057_04");
        }

        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setName(uploadedFileDetails.getName());
        uploadedFile.setFirstRow(uploadedFileDetails.getFirstRow());
        uploadedFile.setPopulated(uploadedFileDetails.getPopulated());
        uploadedFile.setPurpose(em.getReference(Phenomenon.class, uploadedFileDetails.getPurpose().getId()));
        if (uploadedFileDetails.getUploader() != null) {
            uploadedFile.setUploader(em.getReference(Person.class, uploadedFileDetails.getUploader().getId()));
        }

        try {
            em.persist(uploadedFile);
            em.flush();
        } catch (Exception e) {
            System.out.println(e);
            throw new InvalidStateException("error_000_01");
        }

        return uploadedFile.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<UploadedFileDetails> retrieveLatestUploadedFile() throws MilesException {
        List<UploadedFile> uploadedFileList = new ArrayList<>();
        setQ(em.createNamedQuery("UploadedFile.findLatest"));
        q.setMaxResults(3);
        try {
            uploadedFileList = q.getResultList();
        } catch (Exception e) {
        }

        return convertUploadedFilesToUploadedFileDetailsList(uploadedFileList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UploadedFileDetails> retrieveUploadedFile(UploadedFileTypeDetail uploadedFileType) throws MilesException {
        List<UploadedFile> uploadedFileList = new ArrayList<>();
        setQ(em.createNamedQuery("UploadedFile.findAllByUploadedFileTypeId"));
        q.setParameter("uploadedFileTypeId", uploadedFileType.getId());
        try {
            uploadedFileList = q.getResultList();
        } catch (Exception e) {
        }

        return convertUploadedFilesToUploadedFileDetailsList(uploadedFileList);
    }

    @Override
    public UploadedFileDetails retrieveUploadedFile(int id) throws MilesException {
        UploadedFile uploadedFile;
        setQ(em.createNamedQuery("UploadedFile.findById"));
        q.setParameter("id", id);
        try {
            uploadedFile = (UploadedFile) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertUploadedFileToUploadedFileDetails(uploadedFile);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editUploadedFile(UploadedFileDetails uploadedFileDetails) throws MilesException {

        if (uploadedFileDetails == null) {
            throw new InvalidArgumentException("error_057_01");
        } else if (uploadedFileDetails.getId() == null) {
            throw new InvalidArgumentException("error_057_05");
        } else if (uploadedFileDetails.getName() == null) {
            throw new InvalidArgumentException("error_057_02");
        } else if (uploadedFileDetails.getPurpose() == null) {
            throw new InvalidArgumentException("error_057_03");
        } else if (uploadedFileDetails.getUploader() == null) {
            throw new InvalidArgumentException("error_057_04");
        }

        UploadedFile uploadedFile = em.getReference(UploadedFile.class, uploadedFileDetails.getId());
        uploadedFile.setId(uploadedFileDetails.getId());
        uploadedFile.setName(uploadedFileDetails.getName());
        uploadedFile.setFirstRow(uploadedFileDetails.getFirstRow());
        uploadedFile.setTimeUploaded(uploadedFileDetails.getTimeUploaded());
        uploadedFile.setPopulated(uploadedFileDetails.getPopulated());
        uploadedFile.setPurpose(em.getReference(Phenomenon.class, uploadedFileDetails.getPurpose().getId()));
        if (uploadedFileDetails.getUploader() != null) {
            uploadedFile.setUploader(em.getReference(Person.class, uploadedFileDetails.getUploader().getId()));
        }

        try {
            em.merge(uploadedFile);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeUploadedFile(int id) throws MilesException {
        UploadedFile uploadedFile = em.getReference(UploadedFile.class, id);
        try {
            em.remove(uploadedFile);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public UploadedFileDetails convertUploadedFileToUploadedFileDetails(UploadedFile uploadedFile) {

        UploadedFileDetails uploadedFileDetails = new UploadedFileDetails(uploadedFile.getId());
        if (uploadedFile.getUploader() != null) {
            uploadedFileDetails.setUploader(personService.convertPersonToPersonDetails(uploadedFile.getUploader()));
        }
        if (uploadedFile.getPurpose() != null) {
            uploadedFileDetails.setPurpose(UploadedFileTypeDetail.getUploadedFileTypeDetail(uploadedFile.getPurpose().getId()));
        }
        uploadedFileDetails.setTimeUploaded(uploadedFile.getTimeUploaded());
        uploadedFileDetails.setPopulated(uploadedFile.getPopulated());
        uploadedFileDetails.setFirstRow(uploadedFile.getFirstRow());
        uploadedFileDetails.setName(uploadedFile.getName());

        if (uploadedFileDetails.getName() != null) {
            String[] folders = uploadedFileDetails.getName().split(File.separator);
            String fileName = folders[folders.length - 1];
            uploadedFileDetails.setFileName(fileName);
        }

        return uploadedFileDetails;

    }

    private List<UploadedFileDetails> convertUploadedFilesToUploadedFileDetailsList(List<UploadedFile> uploadedFileList) {

        List<UploadedFileDetails> uploadedFileDetailsList = new ArrayList<>();
        for (UploadedFile uploadedFile : uploadedFileList) {
            uploadedFileDetailsList.add(convertUploadedFileToUploadedFileDetails(uploadedFile));
        }

        return uploadedFileDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
}
