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
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
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
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.TrainingRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.topic.TopicRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.trainee.TraineeRequestsLocal;
import ke.co.miles.kcep.mis.requests.training.trainer.TrainerRequestsLocal;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.TopicDetails;
import ke.co.miles.kcep.mis.utilities.TraineeDetails;
import ke.co.miles.kcep.mis.utilities.TrainerDetails;
import ke.co.miles.kcep.mis.utilities.TrainingDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "TrainingController", urlPatterns = {"/training",
    "/addTraining", "/doAddTraining", "/doEditTraining", "/doDeleteTraining",
    "/loadTrainees", "/trainees", "/updateTopics", "/updateTrainingModules",
    "/changeTraineeCounter"})
@MultipartConfig
public class TrainingController extends Controller {

    private static final long serialVersionUID = 1L;
    private String path;
    private ArrayList<String> urlPaths;

    @Override
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));
        path = request.getServletPath();
        String destination;

        switchPaths(session);

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/changeTraineeCounter":

                    HashMap<String, Integer> countMap;
                    short counter = Short.valueOf(request.getParameter("counter"));
                    try {
                        countMap = traineeService.countTrainees(PersonRoleDetail
                                .getPersonRoleDetail(counter), (int) session.getAttribute("trainingId"));
                        int femaleYouth = 0;
                        int femaleElderly = 0;
                        int femaleTotal = 0;
                        int maleYouth = 0;
                        int maleElderly = 0;
                        int maleTotal = 0;
                        int totalPeople = 0;

                        for (String countType : countMap.keySet()) {
                            switch (countType) {
                                case "Female youth":
                                    femaleYouth = countMap.get(countType);
                                    break;
                                case "Female elderly":
                                    femaleElderly = countMap.get(countType);
                                    break;
                                case "Female total":
                                    femaleTotal = countMap.get(countType);
                                    break;
                                case "Male youth":
                                    maleYouth = countMap.get(countType);
                                    break;
                                case "Male elderly":
                                    maleElderly = countMap.get(countType);
                                    break;
                                case "Male total":
                                    maleTotal = countMap.get(countType);
                                    break;
                                case "Total people":
                                    totalPeople = countMap.get(countType);
                                    break;
                            }
                        }

                        session.setAttribute("femaleYouth", femaleYouth);
                        session.setAttribute("femaleElderly", femaleElderly);
                        session.setAttribute("femaleTotal", femaleTotal);
                        session.setAttribute("maleYouth", maleYouth);
                        session.setAttribute("maleElderly", maleElderly);
                        session.setAttribute("maleTotal", maleTotal);
                        session.setAttribute("totalPeople", totalPeople);

                        out.write("<td> " + femaleYouth + "</td>");
                        out.write("<td>" + femaleElderly + "</td>");
                        out.write("<td> " + femaleTotal + "</td>");
                        out.write("<td> " + maleYouth + "</td>");
                        out.write("<td>" + maleElderly + "</td>");
                        out.write("<td> " + maleTotal + "</td>");
                        out.write("<td> " + totalPeople + "</td>");

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);

                    }

                    return;

                case "/updateTopics":
                    try {
                        List<TopicDetails> topics = topicService.retrieveTopics(
                                Short.valueOf(request.getParameter("moduleId")));
                        session.setAttribute("topics", topics);
                        updateTopicOptions(response, topics);
                    } catch (MilesException e) {
                    }
                    return;

                case "/updateTrainingModules":
                    try {
                        List<TopicDetails> trainingModules = topicService.retrieveTrainingModules(
                                Integer.valueOf(request.getParameter("trainerId")));
                        session.setAttribute("trainingModules", trainingModules);
                        if (trainingModules.isEmpty()) {
                            MilesDebugger.debug("No modules");
                            trainingModules = topicService.retrieveTrainingModules();
                            session.setAttribute("trainingModules", trainingModules);
                        }
                        updateTopicOptions(response, trainingModules);

                    } catch (MilesException e) {
                    }
                    return;

                case "/head_training":
                    availHeadTrainingMap(response, session);
                    availSessionAttributes(session, response);
                    break;

                case "/kalro_training":

                    HashMap<TrainingDetails, List<TrainerDetails>> trainingMap;
                    try {
                        trainingMap = trainerService.retrieveKalroTrainings();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

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
                    availSessionAttributes(session, response);

                    break;

                case "/equity_training":

                    try {
                        trainingMap = trainerService.retrieveEquityTrainings();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

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
                    availSessionAttributes(session, response);

                    break;

                case "/ward_training":

                    PersonDetails waoOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        trainingMap = trainerService.retrieveWardTrainings(waoOfficer.getLocation().getWard().getId());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

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

                    availSessionAttributes(session, response);

                    break;

                case "/county_training":

                    PersonDetails countyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        trainingMap = trainerService.retrieveCountyTrainings(countyDeskOfficer.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

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

                    availSessionAttributes(session, response);

                    break;

                case "/sub_county_training":

                    PersonDetails subCountyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        trainingMap = trainerService.retrieveSubCountyTrainings(subCountyDeskOfficer.getLocation().getSubCounty().getId());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

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

                    availSessionAttributes(session, response);

                    break;

                case "/head_addTraining":
                case "/kalro_addTraining":
                case "/ward_addTraining":

                    List<PersonDetails> people;
                    try {
                        people = personService.retrievePeople();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }

                    List<PhenomenonDetails> traineeCategories;
                    try {
                        traineeCategories = phenomenonService.retrieveTraineeCategories();
                        session.setAttribute("traineeCategories", traineeCategories);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    List<PhenomenonDetails> trainerCategories;
                    try {
                        trainerCategories = phenomenonService.retrieveTrainerCategories();
                        session.setAttribute("trainerCategories", trainerCategories);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    break;

                case "/loadTrainees":

                    int trainingId = Integer.valueOf(request.getParameter("trainingId"));

                    List<TraineeDetails> trainees;
                    TrainingDetails training;
                    try {
                        training = trainingService.retrieveTraining(trainingId);
                        trainees = traineeService.retrieveTrainees(trainingId);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    if (trainees != null) {
                        session.setAttribute("trainingId", trainingId);
                        session.setAttribute("training", training);
                        session.setAttribute("trainees", trainees);
                    }

                    try {
                        countMap = traineeService.countAllTrainees(trainingId);
                        int femaleYouth = 0;
                        int femaleElderly = 0;
                        int femaleTotal = 0;
                        int maleYouth = 0;
                        int maleElderly = 0;
                        int maleTotal = 0;
                        int totalPeople = 0;

                        for (String countType : countMap.keySet()) {
                            switch (countType) {
                                case "Female youth":
                                    femaleYouth = countMap.get(countType);
                                    break;
                                case "Female elderly":
                                    femaleElderly = countMap.get(countType);
                                    break;
                                case "Female total":
                                    femaleTotal = countMap.get(countType);
                                    break;
                                case "Male youth":
                                    maleYouth = countMap.get(countType);
                                    break;
                                case "Male elderly":
                                    maleElderly = countMap.get(countType);
                                    break;
                                case "Male total":
                                    maleTotal = countMap.get(countType);
                                    break;
                                case "Total people":
                                    totalPeople = countMap.get(countType);
                                    break;
                            }
                        }

                        session.setAttribute("femaleYouth", femaleYouth);
                        session.setAttribute("femaleElderly", femaleElderly);
                        session.setAttribute("femaleTotal", femaleTotal);
                        session.setAttribute("maleYouth", maleYouth);
                        session.setAttribute("maleElderly", maleElderly);
                        session.setAttribute("maleTotal", maleTotal);
                        session.setAttribute("totalPeople", totalPeople);
                        List<PersonRoleDetail> countOptions = new ArrayList<>();
                        countOptions.add(PersonRoleDetail.FARMER);
                        countOptions.add(PersonRoleDetail.AGRO_DEALER);
                        session.setAttribute("countOptions", countOptions);

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    return;

                case "/sub_county_addTraining":

                    countyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        session.setAttribute("wards", wardService.retrieveWards(countyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                        return;
                    }

                    try {
                        people = personService.retrievePeople();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }

                    try {
                        traineeCategories = phenomenonService.retrieveTraineeCategories();
                        session.setAttribute("traineeCategories", traineeCategories);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        trainerCategories = phenomenonService.retrieveTrainerCategories();
                        session.setAttribute("trainerCategories", trainerCategories);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    break;

                case "/doAddTraining":

                    PhenomenonDetails categoryOfTrainees;
                    try {
                        categoryOfTrainees = new PhenomenonDetails(Integer.valueOf(request.getParameter("category-of-trainees")));
                    } catch (Exception e) {
                        categoryOfTrainees = null;
                    }

                    SubCountyDetails subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Short.valueOf(String.valueOf(request.getParameter("training-sub-county"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

                    CountyDetails county = new CountyDetails();
                    try {
                        county.setId(Short.valueOf(String.valueOf(request.getParameter("training-county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    TopicDetails topic = new TopicDetails();
                    try {
                        topic.setId(Short.valueOf(String.valueOf(request.getParameter("topic"))));
                    } catch (Exception e) {
                        try {
                            topic.setId(Short.valueOf(String.valueOf(request.getParameter("training-module"))));
                        } catch (Exception ex) {
                        }
                    }

                    WardDetails ward = new WardDetails();
                    try {
                        ward.setId(Short.valueOf(String.valueOf(request.getParameter("training-ward"))));
                    } catch (Exception e) {
                        ward = null;
                    }

                    LocationDetails venue = new LocationDetails();
                    venue.setSubCounty(subCounty);
                    venue.setCounty(county);
                    venue.setWard(ward);

                    training = new TrainingDetails();
                    try {
                        training.setNumberOfTrainees(Integer.valueOf(String.valueOf(request.getParameter("number-of-trainees"))));
                    } catch (Exception e) {
                        training.setNumberOfTrainees(null);
                    }
                    training.setTopic(topic);
                    training.setCategoryOfTrainees(categoryOfTrainees);
                    training.setVenue(venue);

                    try {
                        date = userDateFormat.parse(request.getParameter("start-date"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        training.setStartDate(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        training.setStartDate(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("end-date"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        training.setEndDate(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        training.setEndDate(null);
                    }

                    ServletContext context = getServletContext();
                    String realPath = context.getRealPath("/");
                    String filePath = realPath + "documents" + fileSeparator + "training" + fileSeparator + "attendance_sheets";
                    Part filePart = request.getPart("attendance-sheet");
                    String fileName = getFileName(filePart);
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
                        response.getWriter().write(getBundle().getString("file_not_found_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
                    }

                    String[] trainerPersonIds = String.valueOf(request.getParameter("trainer-ids")).split("-");
                    TrainerDetails trainerRecord;
                    List<TrainerDetails> trainerRecords = new ArrayList<>();
                    for (String trainerCategoryId : trainerPersonIds) {
                        PhenomenonDetails trainerCategory = new PhenomenonDetails();
                        trainerRecord = new TrainerDetails();
                        try {
                            trainerCategory.setId(Integer.valueOf(trainerCategoryId));
                            trainerRecord.setPhenomenon(trainerCategory);
                            trainerRecords.add(trainerRecord);
                        } catch (Exception e) {
                        }
                    }

                    String[] traineePersonIds = String.valueOf(request.getParameter("trainee-ids")).split("-");
                    TraineeDetails traineeRecord;
                    List<TraineeDetails> traineeRecords = new ArrayList<>();
                    for (String traineePersonId : traineePersonIds) {
                        PersonDetails traineePerson = new PersonDetails();
                        traineeRecord = new TraineeDetails();
                        try {
                            traineePerson.setId(Integer.valueOf(traineePersonId));
                            traineeRecord.setPerson(traineePerson);
                            traineeRecords.add(traineeRecord);
                        } catch (Exception e) {
                        }
                    }

                    try {
                        trainingId = trainingService.addTraining(training);
                        training.setId(trainingId);
                        for (TrainerDetails trainerRecord1 : trainerRecords) {
                            trainerRecord1.setTraining(training);
                        }
                        for (TraineeDetails traineeRecord1 : traineeRecords) {
                            traineeRecord1.setTraining(training);
                        }
                        trainerService.addTrainers(trainerRecords);
                        traineeService.addTrainees(traineeRecords);

                        session.setAttribute("training", trainingService.retrieveTraining(trainingId));
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    availHeadTrainingMap(response, session);
                    path = "/training";
                    switchPaths(session);

                    break;

                case "/doEditTraining":

                    subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Short.valueOf(String.valueOf(
                                request.getParameter("subCounty"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

                    county = new CountyDetails();
                    try {
                        county.setId(Short.valueOf(String.valueOf(
                                request.getParameter("county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    topic = new TopicDetails();
                    try {
                        topic.setId(Short.valueOf(String.valueOf(request.getParameter("topic"))));
                    } catch (Exception e) {
                        try {
                            topic.setId(Short.valueOf(String.valueOf(request.getParameter("training-module"))));
                        } catch (Exception ex) {
                        }
                    }

                    ward = new WardDetails();
                    try {
                        ward.setId(Short.valueOf(String.valueOf(request.getParameter("ward"))));
                    } catch (Exception e) {
                        ward = null;
                    }

                    try {
                        venue = new LocationDetails(Integer.valueOf(request.getParameter("venue")));
                    } catch (Exception e) {
                        venue = new LocationDetails();
                    }
                    venue.setSubCounty(subCounty);
                    venue.setCounty(county);
                    venue.setWard(ward);

                    training = new TrainingDetails();
                    try {
                        training.setId(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }

                    try {
                        training.setNumberOfTrainees(Integer.valueOf(
                                String.valueOf(request.getParameter("numberOfTrainees"))));
                    } catch (Exception e) {
                        training.setNumberOfTrainees(null);
                    }
                    training.setTopic(topic);
                    training.setVenue(venue);

                    try {
                        date = userDateFormat.parse(request.getParameter("startDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        training.setStartDate(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        training.setStartDate(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("endDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        training.setEndDate(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        training.setEndDate(null);
                    }

                    try {
                        trainingService.editTraining(training);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doDeleteTraining":
                    try {
                        trainingService.removeTraining(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                    }

                    return;

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

//<editor-fold defaultstate="collapsed" desc="Switch paths">
    private void switchPaths(HttpSession session) throws IOException {
        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateTopics");
                            urlPaths.add("/changeTraineeCounter");
                            urlPaths.add("/updateTrainingModules");
                            urlPaths.add("/doAddTraining");
                            urlPaths.add("/doEditTraining");
                            urlPaths.add("/doDeleteTraining");
                            urlPaths.add("/loadTrainees");
                            switch (path) {
                                case "/training":
                                    path = "/head_training";
                                    urlPaths.add(path);
                                    break;
                                case "/trainees":
                                    path = "/head_trainees";
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
                    case "equityPersonnelSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateTopics");
                            urlPaths.add("/changeTraineeCounter");
                            urlPaths.add("/updateTrainingModules");
                            urlPaths.add("/doAddTraining");
                            urlPaths.add("/doEditTraining");
                            urlPaths.add("/doDeleteTraining");
                            urlPaths.add("/loadTrainees");
                            switch (path) {
                                case "/training":
                                    path = "/equity_training";
                                    urlPaths.add(path);
                                    break;
                                case "/trainees":
                                    path = "/equity_trainees";
                                    urlPaths.add(path);
                                    break;
                                case "/addTraining":
                                    path = "/equity_addTraining";
                                    urlPaths.add(path);
                                    break;
                                case "/editTraining":
                                    path = "/equity_editTraining";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "kalroSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateTopics");
                            urlPaths.add("/changeTraineeCounter");
                            urlPaths.add("/updateTrainingModules");
                            urlPaths.add("/doAddTraining");
                            urlPaths.add("/doEditTraining");
                            urlPaths.add("/doDeleteTraining");
                            urlPaths.add("/loadTrainees");
                            switch (path) {
                                case "/training":
                                    path = "/kalro_training";
                                    urlPaths.add(path);
                                    break;
                                case "/trainees":
                                    path = "/kalro_trainees";
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
                            urlPaths.add("/updateTopics");
                            urlPaths.add("/changeTraineeCounter");
                            urlPaths.add("/updateTrainingModules");
                            urlPaths.add("/doAddTraining");
                            urlPaths.add("/doEditTraining");
                            urlPaths.add("/doDeleteTraining");
                            urlPaths.add("/loadTrainees");
                            switch (path) {
                                case "/training":
                                    path = "/ward_training";
                                    urlPaths.add(path);
                                    break;
                                case "/trainees":
                                    path = "/ward_trainees";
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
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateTopics");
                            urlPaths.add("/changeTraineeCounter");
                            urlPaths.add("/updateTrainingModules");
                            urlPaths.add("/doAddTraining");
                            urlPaths.add("/doEditTraining");
                            urlPaths.add("/doDeleteTraining");
                            urlPaths.add("/loadTrainees");
                            switch (path) {
                                case "/training":
                                    path = "/county_training";
                                    urlPaths.add(path);
                                    break;
                                case "/trainees":
                                    path = "/county_trainees";
                                    urlPaths.add(path);
                                    break;
                                case "/addTraining":
                                    path = "/county_addTraining";
                                    urlPaths.add(path);
                                    break;
                                case "/editTraining":
                                    path = "/county_editTraining";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/updateTopics");
                            urlPaths.add("/changeTraineeCounter");
                            urlPaths.add("/updateTrainingModules");
                            urlPaths.add("/doAddTraining");
                            urlPaths.add("/doEditTraining");
                            urlPaths.add("/doDeleteTraining");
                            urlPaths.add("/loadTrainees");
                            switch (path) {
                                case "/training":
                                    path = "/sub_county_training";
                                    urlPaths.add(path);
                                    break;
                                case "/trainees":
                                    path = "/sub_county_trainees";
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
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Avail attributes">

    private void availHeadTrainingMap(HttpServletResponse response,
            HttpSession session) throws IOException {

        HashMap<TrainingDetails, List<TrainerDetails>> trainingMap;
        try {
            trainingMap = trainerService.retrieveTrainings();
        } catch (MilesException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString(ex.getCode()));
            LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
            return;
        }

        if (!trainingMap.isEmpty()) {
            for (TrainingDetails trainingDetails : trainingMap.keySet()) {
                if (trainingDetails.getAttendanceSheet() != null) {
                    try {
                        String[] folders = trainingDetails.
                                getAttendanceSheet().split(fileSeparator);
                        String fileName = folders[folders.length - 1];
                        trainingDetails.setFileName(fileName);
                    } catch (Exception e) {
                    }
                }
            }
            session.setAttribute("trainingMap", trainingMap);
        }

    }

    private void availSessionAttributes(HttpSession session,
            HttpServletResponse response) throws IOException {

        try {
            session.setAttribute("trainingModules", topicService.retrieveTrainingModules());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during retrieval of training modules", e);
            return;
        }

        try {
            session.setAttribute("people", personService.retrievePeople());
        } catch (MilesException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString(ex.getCode()));
            LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
            return;
        }

        try {
            session.setAttribute("traineeCategories", phenomenonService.retrieveTraineeCategories());
        } catch (MilesException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString(ex.getCode()));
            LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
            return;
        }

        try {
            session.setAttribute("trainerCategories", phenomenonService.retrieveTrainerCategories());
        } catch (MilesException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString(ex.getCode()));
            LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update topics options">

    private void updateTopicOptions(HttpServletResponse response,
            List<TopicDetails> topics) throws IOException {
        for (TopicDetails topic : topics) {
            response.getWriter().write("<option value=" + topic.getId() + ">" + topic.getTopic() + "</option>");
        }
    }
//</editor-fold>

    private static final Logger LOGGER = Logger.getLogger(TrainingController.class.getSimpleName());
    @EJB
    private WardRequestsLocal wardService;
    @EJB
    private TopicRequestsLocal topicService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private TraineeRequestsLocal traineeService;
    @EJB
    private TrainerRequestsLocal trainerService;
    @EJB
    private TrainingRequestsLocal trainingService;
    @EJB
    private PhenomenonRequestsLocal phenomenonService;

}
