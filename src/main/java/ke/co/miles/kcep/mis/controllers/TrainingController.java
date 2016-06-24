/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.TrainingRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.trainer.TrainerRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.TrainerDetails;
import ke.co.miles.kcep.mis.utilities.TrainingDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "TrainingController", urlPatterns = {"/training", "/addTraining", "/doAddTraining"})
@MultipartConfig
public class TrainingController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Locale locale = request.getLocale();
        bundle = ResourceBundle.getBundle("text", locale);

        //Get the user session
        HttpSession session = request.getSession(false);

        //Get the user path
        String path = request.getServletPath();
        String destination;

        DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat databaseDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        Date date;

        String fileSeparator = File.separator;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddTraining");
                            switch (path) {
                                case "/training":
                                    path = "/head_training";
                                    urlPaths.add(path);
                                    break;
                                case "/addTraining":
                                    path = "/head_addTraining";
                                    urlPaths.add(path);
                                    break;
                                case "/editTraining":
                                    path = "/head_editTraining";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "kalroSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddTraining");
                            switch (path) {
                                case "/training":
                                    path = "/kalro_training";
                                    urlPaths.add(path);
                                    break;
                                case "/addTraining":
                                    path = "/kalro_addTraining";
                                    urlPaths.add(path);
                                    break;
                                case "/editTraining":
                                    path = "/kalro_editTraining";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddTraining");
                            switch (path) {
                                case "/training":
                                    path = "/ward_training";
                                    urlPaths.add(path);
                                    break;
                                case "/addTraining":
                                    path = "/ward_addTraining";
                                    urlPaths.add(path);
                                    break;
                                case "/editTraining":
                                    path = "/ward_editTraining";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddTraining");
                            switch (path) {
                                case "/training":
                                    path = "/sub_county_training";
                                    urlPaths.add(path);
                                    break;
                                case "/addTraining":
                                    path = "/sub_county_addTraining";
                                    urlPaths.add(path);
                                    break;
                                case "/editTraining":
                                    path = "/sub_county_editTraining";
                                    urlPaths.add(path);
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

                case "/head_training":
                case "/kalro_training":
                case "/ward_training":
                case "/sub_county_training":

                    //Retrieve the list of training
                    HashMap<TrainingDetails, List<TrainerDetails>> trainingMap;
                    try {
                        trainingMap = trainerService.retrieveTrainings();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(bundle.getString(ex.getCode()));
                        LOGGER.log(Level.INFO, bundle.getString(ex.getCode()));
                        return;
                    }

                    //Avail the training in the application scope
                    if (!trainingMap.isEmpty()) {
                        for (TrainingDetails trainingDetails : trainingMap.keySet()) {
                            if (trainingDetails.getAttendanceSheet() != null) {
                                try {
                                    String[] folders = trainingDetails.getAttendanceSheet().split(fileSeparator);
                                    String fileName = folders[folders.length - 1];
                                    trainingDetails.setFileName(fileName);
                                } catch (Exception e) {
                                }
                            }
                        }
                        session.setAttribute("trainingMap", trainingMap);
                    }
                    break;

                case "/head_addTraining":
                case "/kalro_addTraining":
                case "/ward_addTraining":
                case "/sub_county_addTraining":

                    //Retrieve the list of people
                    List<PersonDetails> people;
                    try {
                        people = personService.retrievePeople();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(bundle.getString(ex.getCode()));
                        LOGGER.log(Level.INFO, bundle.getString(ex.getCode()));
                        return;
                    }

                    //Avail the people in the application scope
                    if (people != null) {
                        session.setAttribute("people", people);
                    }

                    break;

                case "/doAddTraining":

                    PersonRoleDetail categoryOfTrainees;
                    try {
                        categoryOfTrainees = PersonRoleDetail.getPersonRoleDetail(Short.valueOf(String.valueOf(request.getParameter("category-of-trainees"))));
                    } catch (Exception e) {
                        categoryOfTrainees = null;
                    }

                    SubCountyDetails subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Integer.valueOf(String.valueOf(request.getParameter("training-sub-county"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

                    CountyDetails county = new CountyDetails();
                    try {
                        county.setId(Short.valueOf(String.valueOf(request.getParameter("training-county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    WardDetails ward = new WardDetails();
                    try {
                        ward.setId(Integer.valueOf(String.valueOf(request.getParameter("training-ward"))));
                    } catch (Exception e) {
                        ward = null;
                    }

                    LocationDetails venue = new LocationDetails();
                    venue.setSubCounty(subCounty);
                    venue.setCounty(county);
                    venue.setWard(ward);

                    TrainingDetails training = new TrainingDetails();
                    try {
                        training.setNumberOfTrainees(Integer.valueOf(String.valueOf(request.getParameter("number-of-trainees"))));
                    } catch (Exception e) {
                        training.setNumberOfTrainees(null);
                    }
                    training.setTopic(String.valueOf(request.getParameter("topic")));
                    training.setCategoryOfTrainees(categoryOfTrainees);
                    training.setVenue(venue);

                    try {
                        //Read in date string in the format MM/dd/yyyy and parse it to date
                        date = userDateFormat.parse(request.getParameter("start-date"));

                        //Format the date string to yyyy/MM/dd and parse it to date
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));

                        //Set the start date
                        training.setStartDate(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(bundle.getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, bundle.getString("string_parse_error"));
                        training.setStartDate(null);
                    }
                    try {
                        //Read in date string in the format MM/dd/yyyy and parse it to date
                        date = userDateFormat.parse(request.getParameter("end-date"));

                        //Format the date string to yyyy/MM/dd and parse it to date
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));

                        //Set the end date
                        training.setEndDate(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(bundle.getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, bundle.getString("string_parse_error"));
                        training.setEndDate(null);
                    }

                    if (training.getTopic().equals("null")) {
                        training.setTopic(null);
                    }

                    ServletContext context = getServletContext();
                    String realPath = context.getRealPath("/");
                    String filePath = realPath + "documents" + fileSeparator + "training" + fileSeparator + "attendance_sheets";
                    final Part filePart = request.getPart("attendance-sheet");
                    final String fileName = getFileName(filePart);
                    FileOutputStream outStream;
                    InputStream inStream;

                    try {
                        filePath = filePath + fileSeparator + fileName;
                        new File(filePath).getParentFile().mkdirs();

                        outStream = new FileOutputStream(filePath);
                        inStream = filePart.getInputStream();

                        final int startOffset = 0;
                        final byte[] buffer = new byte[1024];
                        while (inStream.read(buffer) > 0) {
                            outStream.write(buffer, startOffset, buffer.length);
                        }

                        training.setAttendanceSheet(filePath);
                        outStream.close();

                    } catch (FileNotFoundException e) {
                        training.setAttendanceSheet(null);
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(bundle.getString("file_not_found_error") + "<br>");
                        LOGGER.log(Level.INFO, bundle.getString("file_not_found_error"));
                    }

                    String[] trainerPersonIds = String.valueOf(request.getParameter("trainer-ids")).split("-");
                    TrainerDetails trainerRecord;
                    List<TrainerDetails> trainerRecords = new ArrayList<>();
                    for (String trainerPersonId : trainerPersonIds) {
                        PersonDetails trainerPerson = new PersonDetails();
                        trainerRecord = new TrainerDetails();
                        try {
                            trainerPerson.setId(Integer.valueOf(trainerPersonId));
                            trainerRecord.setPerson(trainerPerson);
                            trainerRecords.add(trainerRecord);
                        } catch (Exception e) {
                        }
                    }

                    try {
                        int trainingId = trainingService.addTraining(training);
                        trainerService.addTrainers(trainerRecords, trainingId);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(bundle.getString(e.getMessage()));
                        LOGGER.log(Level.INFO, bundle.getString(""), e);
                    }

                    return;

                default:
                    break;

            }
            //Use request dispatcher to foward request internally
            destination = "/WEB-INF/views" + path + ".jsp";

            LOGGER.log(Level.INFO, "Request dispatch to forward to: {0}", destination);
            try {
                request.getRequestDispatcher(destination).forward(request, response);
            } catch (ServletException | IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(bundle.getString("redirection_failed") + "<br>");
                LOGGER.log(Level.INFO, bundle.getString("redirection_failed"), e);

            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(bundle.getString("error_016_02") + "<br>");
            LOGGER.log(Level.INFO, bundle.getString("error_016_02"));
        }
    }

    //</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(TrainingController.class.getSimpleName());
    @EJB
    private TrainingRequestsLocal trainingService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private TrainerRequestsLocal trainerService;

}
