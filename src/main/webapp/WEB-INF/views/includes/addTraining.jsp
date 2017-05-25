<%--
    Document   : addTraining
    Created on : Sep 6, 2016, 11:47:38 AM
    Author     : qortez
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Training details
            </div>
            <div class="panel-body">
                <form id="training-form" role="form" action="doAddTraining" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        Start date
                        <input id="start-date" name="start-date" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        End date
                        <input id="end-date" name="end-date" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Trainers
                        <select id="trainer" name="trainer" class="form-control" onchange="addToTrainers()">
                            <option selected>Select trainers</option>
                            <c:forEach var="trainerCategory" items="${sessionScope.trainerCategories}" varStatus="index">
                                <option value="${trainerCategory.id}">${trainerCategory.category.name}</option>
                            </c:forEach>
                        </select>
                        <input id="trainer-names" value="" class="form-control" readonly>
                        <input type="hidden" id="trainer-ids" name="trainer-ids" value="">
                    </div>
                    <div class="form-group">
                        Training module
                        <select id="training-module" name="training-module" class="form-control" onchange="updateTrainingTopics()">
                            <c:forEach var="trainingModule" items="${sessionScope.trainingModules}" varStatus="index">
                                <option value="${trainingModule.id}">${trainingModule.topic}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Topic
                        <select id="topic" name="topic" class="form-control">
                            <c:forEach var="topic" items="${sessionScope.topics}" varStatus="index">
                                <option value="${topic.id}">${topic.topic}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        County
                        <select id="county" name="county" class="form-control" onchange="updateSubCounties()">
                            <c:forEach var="county" items="${sessionScope.counties}" varStatus="index">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="sub-county" name="sub-county" class="form-control" onchange="updateWards()">
                            <c:forEach var="subCounty" items="${sessionScope.subCounties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Ward
                        <select id="ward" name="ward" class="form-control">
                            <c:forEach var="ward" items="${sessionScope.wards}" varStatus="index">
                                <option value="${ward.id}">${ward.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Number of trainees
                        <input type="number" id="number-of-trainees" name="number-of-trainees" class="form-control">
                    </div>
                    <div class="form-group">
                        Category of trainees
                        <select id="category-of-trainees" name="category-of-trainees" class="form-control">
                            <c:forEach var="traineeCategory" items="${sessionScope.traineeCategories}" varStatus="index">
                                <option value="${traineeCategory.id}">${traineeCategory.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Trainees
                        <select id="trainee" name="trainee" class="form-control" onchange="addToTrainees()">
                            <option selected>Select trainees</option>
                            <c:forEach var="training" items="${sessionScope.traineePeople}" varStatus="index">
                                <option value="${training.id}">${training.name}</option>
                            </c:forEach>
                        </select>
                        <input id="trainee-names" value="" class="form-control" readonly>
                        <input type="hidden" id="trainee-ids" name="trainee-ids" value="">
                    </div>
                    <div class="form-group">
                        Attachments (e.g attendance sheet)
                        <input type="file" id="attendance-sheet" name="attendance-sheet" class="form-control" multiple>
                    </div>
                    <span>
                        <input type="submit" class="btn btn-outline btn-primary" value="Save training">
                    </span>
                    <span class="float-right">
                        <input type="reset" class="btn btn-outline btn-primary right" value="Reset details">
                    </span>
                </form>
            </div>
        </div>
    </div>
</div>
