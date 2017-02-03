/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.requests.zip.ZipRequestsLocal;
import org.eclipse.jdt.internal.compiler.batch.Main;

/**
 *
 * @author siech
 */
@WebServlet(name = "FileController", urlPatterns = {"/download",
    "/downloadPeopleUploadFile", "/backupSystem", "/startScheduler"})
public class FileController extends Controller {

    static {
        backupDir = System.getProperty("user.home") + File.separator + "backups" + File.separator;
        BACKUP_COPY_DIR = FileController.backupDir + "most_recent" + File.separator;
    }

    @Override
    public void init() throws ServletException {

        startScheduler();
        LOGGER.log(Level.INFO, "FileController initialized successfully");

    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        setBundle(ResourceBundle.getBundle("text", request.getLocale()));

        OutputStream outStream = response.getOutputStream();
        ServletContext context = getServletContext();
        String mimeType, headerKey, headerValue;
        String path = request.getServletPath();
        FileInputStream inStream;
        String filePath = "";
        File downloadFile;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps
                = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/backupSystem");
                            urlPaths.add("/download");
                            urlPaths.add("/downloadPeopleUploadFile");
                        }
                        break;

                    default:
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/download");
                            urlPaths.add("/downloadPeopleUploadFile");
                        }
                }
            }
        }
        urlPaths.add("/startScheduler");

        if (urlPaths.contains(path)) {

            switch (path) {

                case "/download":

                    filePath = request.getParameter("filePath");
                    break;

                case "/downloadPeopleUploadFile":

                    filePath = getServletContext().getRealPath("/") + "documents"
                            + fileSeparator + "population" + fileSeparator + "download_files"
                            + fileSeparator + "SN PUF.xlsx";
                    break;

                case "/backupSystem":

                    backupDatabase();
                    backupAttachments();
                    zipBackups();

                    filePath = getServletContext().getRealPath("/") + "backup.zip";
                    break;

                case "/startScheduler":

                    startScheduler();

            }

            try {
                downloadFile = new File(filePath);
                inStream = new FileInputStream(downloadFile);

                mimeType = context.getMimeType(filePath);
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                /* response.setHeader("Content-Length", String.valueOf(downloadFile.length())); */
                response.setContentLength((int) downloadFile.length());
                response.setContentType(mimeType);

                headerKey = "Content-Disposition";
                /* restricts browser to downloading the file */
 /* headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName()); */
 /* allows browser to open the file for viewing */
                headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);

                final int startOffset = 0;
                final byte[] buffer = new byte[4096];
                while (inStream.read(buffer) != -1) {
                    outStream.write(buffer, startOffset, buffer.length);
                }

                outStream.flush();
                outStream.close();
                inStream.close();

            } catch (FileNotFoundException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                outStream.write((getBundle().getString("file_not_found_error") + "<br>").getBytes());
                LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
            }

        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            outStream.write((getBundle().getString("error_016_02") + "<br>").getBytes());
            LOGGER.log(Level.INFO, getBundle().getString("error_016_02"));
        }
    }

    public void backupDatabase() {
        try {

            /* Defining database credentials */
            String username = "root";
            String password = "complex";
            String databaseName = "kcep_mis";

            /* Building the file name and path */
            String fileName = databaseName + "_" + (new Date().toString()).replaceAll(" ", "_") + ".sql";
            backupDir = System.getProperty("user.home") + File.separator + "backups" + File.separator;
            String filePath = backupDir + "database" + File.separator + databaseName + File.separator;
            new File(filePath).mkdirs();
            filePath += fileName;

            /* Building mysqldump command */
            String command = "mysqldump -u" + username + " -p" + password + " --add-drop-database -B " + databaseName + " -r " + filePath;

            /* Executing mysqldump command */
            Runtime runtime = Runtime.getRuntime();
            int state = runtime.exec(command).waitFor();

            /* Check process exit value. 0 denotes successful termination  */
            if (state == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

            new File(BACKUP_COPY_DIR).mkdirs();

            /* Building file copy command */
            command = "cp " + filePath + " " + BACKUP_COPY_DIR + databaseName + ".sql";

            state = runtime.exec(command).waitFor();

            /* Check process exit value. 0 denotes successful termination  */
            if (state == 0) {
                System.out.println("Backup copied successfully");
            } else {
                System.out.println("Backup copy failed");
            }

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backup" + ex.getMessage());
        }
    }

    public void backupAttachments() {
        try {

            /* Defining attachments folder */
            String attachmentsFolder = "documents";

            /* Building the archive name and path */
            String fileName = attachmentsFolder + "_" + (new Date().toString()).replaceAll(" ", "_") + ".zip";
            String filePath = backupDir + "attachments" + File.separator + attachmentsFolder + File.separator;
            new File(filePath).mkdirs();
            filePath += fileName;

            /* Archiving the attachments */
            try {
                zipService.zipFolder(getServletContext().getRealPath("/") + attachmentsFolder, filePath);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            new File(BACKUP_COPY_DIR).mkdirs();

            /* Building file copy command */
            String command = "cp " + filePath + " " + BACKUP_COPY_DIR + attachmentsFolder + ".zip";

            Runtime runtime = Runtime.getRuntime();
            int state = runtime.exec(command).waitFor();

            /* Check process exit value. 0 denotes successful termination  */
            if (state == 0) {
                System.out.println("Backup copied successfully");
            } else {
                System.out.println("Backup copy failed");
            }

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at archiving" + ex.getMessage());
        }
    }

    public void zipBackups() {

        /* Archiving the attachments */
        try {
            zipService.zipFolder(new File(BACKUP_COPY_DIR).getPath(), getServletContext().getRealPath("/") + "backup.zip");
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "An error occurred while zipping up ", ex);
        }

    }

    public void startScheduler() {

        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("Africa/Nairobi");
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zoned2Am;
        zoned2Am = zonedNow.withHour(2).withMinute(0).withSecond(0);
        if (zonedNow.compareTo(zoned2Am) > 0) {
            zoned2Am = zoned2Am.plusDays(1);
        }

        Duration duration = Duration.between(zonedNow, zoned2Am);
        long initialDelay = duration.getSeconds();
        LOGGER.log(Level.INFO, "Initial delay is: {0} seconds", initialDelay);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                backupDatabase();
                backupAttachments();
                zipBackups();
            }
        }, initialDelay, 24 * 60 * 60, TimeUnit.SECONDS);
    }

    @EJB
    private ZipRequestsLocal zipService;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(FileController.class.getSimpleName());
    private static final String BACKUP_COPY_DIR;
    private static String backupDir;

}
