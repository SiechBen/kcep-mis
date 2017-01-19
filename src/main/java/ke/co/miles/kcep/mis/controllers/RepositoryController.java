/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.uploadedfile.UploadedFileRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.UploadedFileDetails;
import ke.co.miles.kcep.mis.utilities.UploadedFileTypeDetail;

/**
 *
 * @author siech
 */
@WebServlet(name = "DocumentController",
        urlPatterns = {"/saveDocument", "/deleteDocument", "/documents"})
@MultipartConfig
public class RepositoryController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession(false);
        String path = request.getServletPath();
        String destination = "";

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            urlPaths.add("/deleteDocument");
                            destination = "/head_documents";
                            if (path.equals("/documents")) {
                                path = "/head_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/head_documents";
                            if (path.equals("/documents")) {
                                path = "/head_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "agroDealerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/agro_dealer_documents";
                            if (path.equals("/documents")) {
                                path = "/agro_dealer_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/county_documents";
                            if (path.equals("/documents")) {
                                path = "/county_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/sub_county_documents";
                            if (path.equals("/documents")) {
                                path = "/sub_county_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "equityPersonellSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/equity_documents";
                            if (path.equals("/documents")) {
                                path = "/equitydocuments";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "kalroOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/kalro_documents";
                            if (path.equals("/documents")) {
                                path = "/kalro_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "agmarkOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/agmark_documents";
                            if (path.equals("/documents")) {
                                path = "/agmark_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/region_documents";
                            if (path.equals("/documents")) {
                                path = "/region_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/saveDocument");
                            destination = "/ward_documents";
                            if (path.equals("/documents")) {
                                path = "/ward_documents";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        urlPaths.add("/saveDocument");

        if (urlPaths.contains(path)) {

            PersonDetails user = (PersonDetails) session.getAttribute("person");

            switch (path) {

                case "/agro_dealer_documents":
                case "/sub_county_documents":

                    try {
                        session.setAttribute("documents", uploadedFileService.retrieveSubCountyUploadedFiles(UploadedFileTypeDetail.COMMON_KCEP_FILE, user.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of documents from a sub-county", ex);
                        return;
                    }

                    break;

                case "/county_documents":

                    try {
                        session.setAttribute("documents", uploadedFileService.retrieveCountyUploadedFiles(UploadedFileTypeDetail.COMMON_KCEP_FILE, user.getLocation().getCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of documents from a county", ex);
                        return;
                    }

                    break;

                case "/head_documents":
                case "/agmark_documents":
                case "/kalro_documents":

                    try {
                        session.setAttribute("documents", uploadedFileService.retrieveUploadedFiles(UploadedFileTypeDetail.COMMON_KCEP_FILE));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of documents", ex);
                        return;
                    }

                    break;

                case "/region_documents":

                    try {
                        session.setAttribute("documents", uploadedFileService.retrieveRegionUploadedFiles(UploadedFileTypeDetail.COMMON_KCEP_FILE, user.getLocation().getCounty().getRegion().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of documents from a region", ex);
                        return;
                    }

                    break;

                case "/ward_documents":
                case "/warehouse_documents":

                    try {
                        session.setAttribute("documents", uploadedFileService.retrieveWardUploadedFiles(UploadedFileTypeDetail.COMMON_KCEP_FILE, user.getLocation().getWard().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of documents from a region", ex);
                        return;
                    }

                    break;

                case "/deleteDocument":

                    int documentId = 0;
                    try {
                        documentId = Integer.valueOf(request.getParameter("id"));
                        uploadedFileService.removeUploadedFile(documentId);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    try {
                        @SuppressWarnings("unchecked")
                        List<UploadedFileDetails> uploadedFiles = (List<UploadedFileDetails>) session.getAttribute("documents");
                        uploadedFiles.remove(new UploadedFileDetails(documentId));
                        session.setAttribute("documents", uploadedFiles);
                    } catch (Exception e) {
                    }
                    return;

                case "/saveDocument":

                    UploadedFileDetails uploadedFile = new UploadedFileDetails();
                    uploadedFile.setTimeUploaded(new Date());
                    uploadedFile.setUploader((PersonDetails) session.getAttribute("person"));
                    uploadedFile.setPurpose(UploadedFileTypeDetail.COMMON_KCEP_FILE);

                    ServletContext context = getServletContext();
                    String realPath = context.getRealPath("/");
                    String filePath = realPath + "documents" + fileSeparator + "kcep_common_files";
                    Part filePart = request.getPart("document");
                    String fileName = getFileName(filePart);
                    BufferedOutputStream outStream = null;
                    BufferedInputStream inStream = null;

                    try {
                        if (fileName != null & !fileName.isEmpty() && fileName.trim().length() != 0 && !fileName.equals("")) {
                            filePath += fileSeparator;
                            File toDelete = new File(filePath);
                            if (toDelete.isFile()) {
                                toDelete.delete();
                            }
                            filePath += fileName;
                            new File(filePath).getParentFile().mkdirs();

                            outStream = new BufferedOutputStream(new FileOutputStream(filePath));
                            inStream = new BufferedInputStream(filePart.getInputStream());

                            final int startOffset = 0;
                            final byte[] buffer = new byte[4096];
                            while (inStream.read(buffer) > 0) {
                                outStream.write(buffer, startOffset, buffer.length);
                            }

                            uploadedFile.setName(filePath);
                            outStream.close();

                        }
                    } catch (FileNotFoundException ex) {
                        uploadedFile.setName(null);
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
                    } finally {
                        if (outStream != null) {
                            outStream.close();
                        }
                        if (inStream != null) {
                            inStream.close();
                        }
                    }

                    try {
                        uploadedFile.setId(uploadedFileService.addUploadedFile(uploadedFile));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    if (uploadedFile.getName() != null) {
                        String[] folders = uploadedFile.getName().split(File.separator);
                        fileName = folders[folders.length - 1];
                        uploadedFile.setFileName(fileName);
                    }

                    try {
                        @SuppressWarnings("unchecked")
                        List<UploadedFileDetails> uploadedFiles = (List<UploadedFileDetails>) session.getAttribute("documents");
                        uploadedFiles.add(0, uploadedFile);
                        session.setAttribute("documents", uploadedFiles);
                    } catch (Exception e) {
                    }

                    path = destination;

                    break;

                default:
                    break;

            }

            destination = "/WEB-INF/views/pages" + path + ".jsp";

            LOGGER.log(Level.INFO, "Request dispatch to forward to: {0}", destination);
            try {
                request.getRequestDispatcher(destination).forward(request, response);
            } catch (ServletException | IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(getBundle().getString("redirection_failed") + "<br>");
                LOGGER.log(Level.INFO, getBundle().getString("redirection_failed"), e);

            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString("error_016_02") + "<br>");
            LOGGER.log(Level.INFO, getBundle().getString("error_016_02"));
        }
    }

//</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(EVoucherPersonController.class
            .getSimpleName());
    @EJB
    private UploadedFileRequestsLocal uploadedFileService;

}
