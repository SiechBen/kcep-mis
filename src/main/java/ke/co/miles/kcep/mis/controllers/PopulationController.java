package ke.co.miles.kcep.mis.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import ke.co.miles.kcep.mis.defaults.Generator;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.population.MysqlConnection;
import ke.co.miles.kcep.mis.requests.population.PopulationTimer;
import ke.co.miles.kcep.mis.requests.population.excelreader.AgroDealerReader;
import ke.co.miles.kcep.mis.requests.population.excelreader.FarmerReader;
import ke.co.miles.kcep.mis.requests.uploadedfile.UploadedFileRequestsLocal;
import ke.co.miles.kcep.mis.utilities.AccountDetails;
import ke.co.miles.kcep.mis.utilities.ContactDetails;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.DivisionalLocationDetails;
import ke.co.miles.kcep.mis.utilities.EblBranchDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.UploadedFileDetails;
import ke.co.miles.kcep.mis.utilities.UploadedFileTypeDetail;
import ke.co.miles.kcep.mis.utilities.VillageDetails;

/**
 *
 * @author siech
 */
@MultipartConfig
@WebServlet(name = "PopulationController", urlPatterns = {"/uploadPeople", "/uploadExcelFile"})
public class PopulationController extends Controller {

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
                urlPaths.add("/changeCounter");
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/uploadPeople":
                                    path = "/head_upload_people";
                                    urlPaths.add(path);
                                    break;
                                case "/uploadExcelFile":
                                    destination = "/head_upload_people";
                                    urlPaths.add("/uploadExcelFile");
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/uploadPeople":
                                    path = "/region_upload_people";
                                    urlPaths.add(path);
                                    break;
                                case "/uploadExcelFile":
                                    destination = "/region_upload_people";
                                    urlPaths.add("/uploadExcelFile");
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/uploadPeople":
                                    path = "/county_upload_people";
                                    urlPaths.add(path);
                                    break;
                                case "/uploadExcelFile":
                                    destination = "/county_upload_people";
                                    urlPaths.add("/uploadExcelFile");
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/uploadExcelFile");
                            switch (path) {
                                case "/uploadPeople":
                                    path = "/sub_county_upload_people";
                                    urlPaths.add(path);
                                    break;
                                case "/uploadExcelFile":
                                    destination = "/sub_acounty_upload_people";
                                    urlPaths.add("/uploadExcelFile");
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/uploadExcelFile");
                            switch (path) {
                                case "/people":
                                    path = "/ward_upload_people";
                                    urlPaths.add(path);
                                    break;
                                case "/uploadExcelFile":
                                    destination = "/ward_upload_people";
                                    urlPaths.add("/uploadExcelFile");
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {
                case "/uploadExcelFile":

                    UploadedFileDetails uploadedFile = new UploadedFileDetails();
                    uploadedFile.setPopulated(Boolean.FALSE);
                    String fileType = request.getParameter("file-type");
                    uploadedFile.setPurpose(UploadedFileTypeDetail
                            .getUploadedFileTypeDetail(fileType == null ? null : Integer.valueOf(fileType)));
                    uploadedFile.setUploader((PersonDetails) session.getAttribute("person"));

                    try {
                        uploadedFile.setFirstRow(new Short(request.getParameter("first-row")));
                    } catch (Exception e) {
                        uploadedFile.setFirstRow(new Short("0"));
                    }

                    ServletContext context = getServletContext();
                    String realPath = context.getRealPath("/");
                    String filePath = realPath + "documents" + fileSeparator + "population" + fileSeparator + "uploaded_files";
                    Part filePart = request.getPart("excel-file");
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
                            if (filePath.endsWith("SN PUF.xlsx")) {

                                filePath = filePath.replace(".xlsx", "") + " " + new Date() + ".xlsx";

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
                            } else {
                                uploadedFile.setName(null);
                            }

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

                    this.fileName = uploadedFile.getName();

                    path = destination;

                    try {
                        uploadedFileService.addUploadedFile(uploadedFile);
                    } catch (MilesException ex) {
                        session.setAttribute("uploadedClass", "not-uploaded");
                        getServletContext().setAttribute("populationInfo", getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                        break;
                    }

                    try {
                        session.setAttribute("uploadedClass", "uploaded");
                        getServletContext().setAttribute("populationInfo", getBundle().getString("excel_uploaded"));
                    } catch (Exception ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred when updating population information", ex);
                        break;
                    }

                    if (uploadedFile.getPurpose().equals(UploadedFileTypeDetail.FARMERS_EXCEL_FILE)) {
                        new FarmerPopulation().start();
                    } else if (uploadedFile.getPurpose().equals(UploadedFileTypeDetail.AGRO_DEALERS_EXCEL_FILE)) {
                        new AgroDealerPopulation().start();
                    }

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

    private class FarmerPopulation extends Thread {

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();

            Connection connection = MysqlConnection.getConnection();

            ResultSet result;
            Statement statement;
            MessageDigest messageDigest;
            String password, likelyEmail;
            int j = 0;

            /**
             * int firstRow =
             * Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the
             * row number of the first record.")); int lastRow =
             * Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the
             * row number of the last record.")); firstRow = 6; lastRow = 137;
             */
            HashMap<PersonDetails, AccountDetails> peopleMap;
            try {
                peopleMap = new FarmerReader().retrievePeopleFromExcel(fileName);
            } catch (MilesException e) {
//                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                response.getWriter().write(getBundle().getString(e.getCode()) + "<br>");
                getServletContext().setAttribute("populationInfo", getBundle().getString(e.getCode()));
                LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                return;
            }
            ContactDetails contact;
            CountyDetails county;
            EblBranchDetails eblBranch;
            SubCountyDetails subCounty;
            DivisionalLocationDetails divisionalLocation;
            LocationDetails location;

            //<editor-fold defaultstate="collapsed" desc="Loop excel records">
//        for (int i = firstRow - 1; i < lastRow; i++, j++) {
            for (PersonDetails person : peopleMap.keySet()) {
                try {
                    messageDigest = MessageDigest.getInstance("SHA-256");
                    password = Generator.generateSHAPassword(messageDigest, person.getNationalId());
                } catch (NoSuchAlgorithmException e) {
                    LOGGER.log(Level.WARNING, "Message digest algorithm not found");
                    return;
                }

                likelyEmail = (person.getName() + "@gmail.com").replaceAll(" ", "").toLowerCase();

                contact = new ContactDetails();
                contact.setEmail(Generator.generateRandomEmailAddress(likelyEmail));

                person.setContact(contact);

                try {
                    statement = connection.createStatement();

                    //Create the person's contact record
                    statement.executeUpdate("INSERT INTO contact(phone,email,postal_address) VALUES ('" + person.getNationalId() + "','" + person.getContact().getEmail() + "',NULL)");
                    result = statement.executeQuery("SELECT * FROM contact ORDER BY id DESC LIMIT 1");
                    result.next();
                    person.getContact().setId(result.getInt("id"));

                    county = new CountyDetails();
                    subCounty = new SubCountyDetails();

                    //Create village record if not exist
                    try {
                        result = statement.executeQuery("SELECT * FROM village WHERE name LIKE '%" + person.getLocation().getVillage().getName() + "%' ");
                        result.next();
                        person.getLocation().getVillage().setId(result.getInt("id"));
                    } catch (SQLException e) {
                        statement.executeUpdate("INSERT INTO village(name) VALUES('" + person.getLocation().getVillage().getName() + "')");
                        result = statement.executeQuery("SELECT * FROM village ORDER BY id DESC LIMIT 1 ");
                        result.next();
                        person.getLocation().getVillage().setId(result.getInt("id"));
                    } catch (NullPointerException ex) {
                        VillageDetails village = null;
                        location = person.getLocation();
                        location.setVillage(village);
                        person.setLocation(location);
                    }

                    //Create divisional location record if not exist
                    if (person.getLocation().getDivisionalLocation() != null && person.getLocation().getDivisionalLocation().getName().trim().length() > 2) {
                        result = statement.executeQuery("SELECT * FROM divisional_location WHERE name LIKE '%" + person.getLocation().getDivisionalLocation().getName() + "%'");
                        try {
                            result.next();
                            divisionalLocation = person.getLocation().getDivisionalLocation();
                            divisionalLocation.setId(result.getShort("id"));
                            person.getLocation().setDivisionalLocation(divisionalLocation);
                        } catch (SQLException e) {
                            statement.executeUpdate("INSERT INTO divisional_location(name) VALUES('" + person.getLocation().getDivisionalLocation().getName() + "')");
                            result = statement.executeQuery("SELECT * FROM divisional_location ORDER BY id DESC LIMIT 1 ");
                            result.next();
                            divisionalLocation = person.getLocation().getDivisionalLocation();
                            divisionalLocation.setId(result.getShort("id"));
                            person.getLocation().setDivisionalLocation(divisionalLocation);
                        }
                    } else {
                        divisionalLocation = null;
                        location = person.getLocation();
                        location.setDivisionalLocation(divisionalLocation);
                        person.setLocation(location);
                    }

                    //Determine the person's sub-county, and county
                    try {
                        result = statement.executeQuery("SELECT * FROM ward where id=" + person.getLocation().getWard().getId() + "");
                        result.next();
                        subCounty.setId(result.getShort("sub_county"));
                        result = statement.executeQuery("SELECT * FROM sub_county where id=" + subCounty.getId() + "");
                        result.next();
                        county.setId(result.getShort("county"));
                        subCounty.setCounty(county);
                        person.getLocation().setCounty(county);
                        person.getLocation().setSubCounty(subCounty);
                    } catch (Exception e) {
                        person.setLocation(null);
                    }

                    //Create the person's location record
                    if (person.getLocation() != null) {
                        statement.executeUpdate("INSERT INTO location(ward,sub_county,county,village,divisional_location) VALUES (" + person.getLocation().getWard().getId() + "," + person.getLocation().getSubCounty().getId() + "," + person.getLocation().getCounty().getId() + "," + (person.getLocation().getVillage() == null ? null : person.getLocation().getVillage().getId()) + "," + (person.getLocation().getDivisionalLocation() == null ? null : person.getLocation().getDivisionalLocation().getId()) + ")");
                        result = statement.executeQuery("SELECT * FROM location ORDER BY id DESC LIMIT 1");
                        result.next();
                        person.getLocation().setId(result.getInt("id"));
                    }

                    //Create ebl branch record if not exist
                    try {
                        result = statement.executeQuery("SELECT * FROM ebl_branch WHERE name LIKE '%" + peopleMap.get(person).getEblBranch().getName() + "%' ");
                        result.next();
                        eblBranch = peopleMap.get(person).getEblBranch();
                        eblBranch.setId(result.getShort("id"));
                        peopleMap.get(person).setEblBranch(eblBranch);
                    } catch (SQLException e) {
                        statement.executeUpdate("INSERT INTO ebl_branch(name) VALUES('" + peopleMap.get(person).getEblBranch().getName() + "')");
                        result = statement.executeQuery("SELECT * FROM ebl_branch ORDER BY id DESC LIMIT 1 ");
                        result.next();
                        eblBranch = peopleMap.get(person).getEblBranch();
                        eblBranch.setId(result.getShort("id"));
                        peopleMap.get(person).setEblBranch(eblBranch);
                    }

                    //Create the person's record. remember to use java.sql.Date
                    statement.executeUpdate("INSERT INTO person(name,sex,national_id,year_of_birth,age,location,contact) VALUES ('" + person.getName() + "'," + (person.getSex() == null ? null : person.getSex().getId()) + ",'" + person.getNationalId() + "'," + person.getYearOfBirth() + ",'" + (person.getAge() == null ? 0 : person.getAge()) + "'," + (person.getLocation() == null ? null : person.getLocation().getId()) + "," + person.getContact().getId() + ")");
                    result = statement.executeQuery("SELECT * FROM person ORDER BY id DESC LIMIT 1");
                    result.next();
                    /**
                     * Doing person.setId(result.getInt("id)) alters the of the
                     * person which is a peopleMap key. Doing this renders
                     * peopleMap.get(person) misleading.
                     */
                    int personId = result.getInt("id");

                    //Create bank account for person
                    statement.executeUpdate("INSERT INTO account(account_number,ebl_branch,sol_id,farmer) VALUES('" + peopleMap.get(person).getAccountNumber() + "'," + peopleMap.get(person).getEblBranch().getId() + ",'" + peopleMap.get(person).getSolId() + "'," + personId + ")");

                    //Create the person's user account record
                    statement.executeUpdate("INSERT INTO user_account(person, username, password, person_role) VALUES (" + personId + ",'" + person.getContact().getEmail() + "','" + password + "'," + PersonRoleDetail.FARMER.getId() + ")");

                    LOGGER.log(Level.INFO, "{0} Farmer {1} saved successfully", new Object[]{++j, person.getName()});

                } catch (SQLException e) {
                    System.err.println("Farmer record error occurred: " + e);
                }

            }
//</editor-fold>

            PopulationTimer.recordPopulationTime(startTime);
        }

    }

    private class AgroDealerPopulation extends Thread {

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();

            Connection connection = MysqlConnection.getConnection();

            ResultSet result;
            Statement statement;
            MessageDigest messageDigest;
            String password, likelyEmail;

            /**
             * int firstRow =
             * Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the
             * row number of the first record.")); int lastRow =
             * Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the
             * row number of the last record.")); firstRow = 6; lastRow = 137;
             */
            List<PersonDetails> people;
            try {
                people = new AgroDealerReader().retrievePeopleFromExcel(fileName);
            } catch (MilesException e) {
//                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                response.getWriter().write(getBundle().getString(e.getCode()) + "<br>");
                getServletContext().setAttribute("populationInfo", getBundle().getString(e.getCode()));
                LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                return;
            }
            int j = 0;

            //<editor-fold defaultstate="collapsed" desc="Loop excel records">
            for (PersonDetails person : people) {

                try {
                    messageDigest = MessageDigest.getInstance("SHA-256");
                    password = Generator.generateSHAPassword(messageDigest, person.getNationalId());
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("Message digest algorithm not found");
                    return;
                }

                likelyEmail = (person.getName() + "@gmail.com").replaceAll(" ", "").toLowerCase();

                ContactDetails contact = new ContactDetails();
                contact.setEmail(Generator.generateRandomEmailAddress(likelyEmail));

                person.setContact(contact);

                try {
                    person.setYearOfBirth(new Short("2016"));
                } catch (Exception e) {
                }

                //Set the start date
                person.setYearOfBirth(new Short("2016"));

                try {
                    statement = connection.createStatement();

                    //Create the person's contact record
                    statement.executeUpdate("INSERT INTO contact(phone,email,postal_address) VALUES ('" + person.getNationalId() + "','" + person.getContact().getEmail() + "',NULL)");
                    result = statement.executeQuery("SELECT * FROM contact ORDER BY id DESC LIMIT 1");
                    result.next();
                    person.getContact().setId(result.getInt("id"));

                    CountyDetails county = new CountyDetails();
                    SubCountyDetails subCounty = new SubCountyDetails();

                    //Determine the person's sub-county, and county
                    result = statement.executeQuery("SELECT * FROM ward where id=" + person.getLocation().getWard().getId() + "");
                    result.next();
                    subCounty.setId(result.getShort("sub_county"));
                    result = statement.executeQuery("SELECT * FROM sub_county where id=" + subCounty.getId() + "");
                    result.next();
                    county.setId(result.getShort("county"));
                    subCounty.setCounty(county);
                    person.getLocation().setCounty(county);
                    person.getLocation().setSubCounty(subCounty);

                    //Create the person's location record
                    statement.executeUpdate("INSERT INTO location(ward,sub_county,county) VALUES (" + person.getLocation().getWard().getId() + "," + person.getLocation().getSubCounty().getId() + "," + person.getLocation().getCounty().getId() + ")");
                    result = statement.executeQuery("SELECT * FROM location ORDER BY id DESC LIMIT 1");
                    result.next();
                    person.getLocation().setId(result.getInt("id"));

                    //Create the person's record
                    statement.executeUpdate("INSERT INTO person(name,sex,national_id,year_of_birth,business_name,location,contact) VALUES ('" + person.getName() + "'," + person.getSex().getId() + ",'" + person.getNationalId() + "'," + null + ",'" + person.getBusinessName() + "'," + person.getLocation().getId() + "," + person.getContact().getId() + ")");
                    result = statement.executeQuery("SELECT * FROM person ORDER BY id DESC LIMIT 1");
                    result.next();
                    person.setId(result.getInt("id"));

                    //Create the person's user account record
                    statement.executeUpdate("INSERT INTO user_account(person, username, password, person_role) VALUES (" + person.getId() + ",'" + person.getContact().getEmail() + "','" + password + "'," + PersonRoleDetail.AGRO_DEALER.getId() + ")");

                    LOGGER.log(Level.INFO, ++j + " Agro-dealer " + person.getName() + " saved successfully");

                } catch (Exception e) {
                    System.err.println("Agro-dealer record error occurred: " + e);
                }
            }
//</editor-fold>

            PopulationTimer.recordPopulationTime(startTime);

        }

    }

    @EJB
    private UploadedFileRequestsLocal uploadedFileService;
    private String fileName;
    private static final Logger LOGGER = Logger.getLogger(PopulationController.class.getSimpleName());

}
