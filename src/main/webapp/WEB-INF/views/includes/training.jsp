<%--
    Document   : training
    Created on : Sep 6, 2016, 12:55:48 AM
    Author     : qortez
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of training
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addTraining</label>
                    <table id="training-table" class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Start date</th>
                                <th>End date</th>
                                <th>Trainer</th>
                                <th>Module</th>
                                <th>Topic</th>
                                <th>County</th>
                                <th>Sub-county</th>
                                <th>Ward</th>
                                <th>Number of trainees</th>
                                <th>Category of trainees</th>
                                <th>Attachments(e.g attendance sheet)</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="14"> List of training </td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="training" items="${sessionScope.trainingMap.keySet()}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${training.startDate}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${training.endDate}"/></td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="<c:forEach var="trainer" items="${sessionScope.trainingMap.get(training)}" varStatus="i">${i.count}. ${trainer.phenomenon.category.name} </c:forEach>">
                                        <c:forEach var="trainer" items="${sessionScope.trainingMap.get(training)}">
                                            ${trainer.phenomenon.category.name}
                                        </c:forEach>
                                    </td>
                                    <td>${training.topic.module.topic}</td>
                                    <td>${training.topic.topic}</td>
                                    <td>${training.venue.county.name}</td>
                                    <td>${training.venue.subCounty.name}</td>
                                    <td>${training.venue.ward.name}</td>
                                    <td class="pointable" onclick="showTrainees(${training.id})">${training.numberOfTrainees}</td>
                                    <td>${training.categoryOfTrainees.category.name}</td>
                                    <td><a href="download?filePath=${training.attendanceSheet}" target="_blank">${training.fileName}</a></td>
                                    <td><button onclick="editTraining(
                                                    '${training.id}',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${training.startDate}"/>',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${training.endDate}"/>',
                                                    '${training.topic.id}',
                                                    '${training.venue.id}',
                                                    '${training.venue.county.id}',
                                                    '${training.venue.subCounty.id}',
                                                    '${training.venue.ward.id}',
                                                    '${training.numberOfTrainees}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deleteTraining(${training.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="dialog" id="training-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Start date
                        <input id="start-date" name="start-date" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        End date
                        <input id="end-date" name="end-date" class="form-control datefield">
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
                        <select id="county" name="training-county" class="form-control" onchange="updateSubCounties()">
                            <c:forEach var="county" items="${sessionScope.counties}" varStatus="index">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="sub-county" name="training-sub-county" class="form-control" onchange="updateWards()">
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
                        <input type="number" step="1" id="number-of-trainees" name="number-of-trainees" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
