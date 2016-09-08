<%-- 
    Document   : training
    Created on : Sep 6, 2016, 12:55:48 AM
    Author     : qortez
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of training
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table id="training-table" class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addTraining')">Add</button></th>
                                <th>Start date</th>
                                <th>End date</th>
                                <th>Trainer</th>
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
                                <td colspan="8"> List of training </td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="training" items="${sessionScope.trainingMap.keySet()}" varStatus="index">
                                <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                    <td>${index.count}</td>
                                    <td>${training.startDate}</td>
                                    <td>${training.endDate}</td>
                                    <td>
                                        <c:forEach var="trainer" items="${sessionScope.trainingMap.get(training)}">
                                            ${trainer.phenomenon.category.name} 
                                        </c:forEach>
                                    </td>
                                    <td>${training.topic.topic}</td>
                                    <td>${training.venue.county.name}</td>
                                    <td>${training.venue.subCounty.name}</td>
                                    <td>${training.venue.ward.name}</td>
                                    <td class="pointable" onclick="showTrainees(${training.id})">${training.numberOfTrainees}</td>
                                    <td>${training.categoryOfTrainees.category.name}</td>
                                    <td><a href="download?filePath=${training.attendanceSheet}" target="_blank">${training.fileName}</a></td>
                                    <td><button onclick="editTraining('${training.id}', '${training.startDate}', '${training.endDate}', '${training.topic.topic}', '${training.venue.county.name}', '${training.venue.subCounty.name}', '${training.venue.ward.name}', '${training.numberOfTrainees}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
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
                        Topic
                        <select id="topic" name="topic" class="form-control">
                            <c:forEach var="topic" items="${applicationScope.topics}" varStatus="index"> 
                                <option value="${topic.id}">${topic.topic}</option>
                            </c:forEach>
                        </select>  
                    </div>
                    <div class="form-group">
                        <input type="hidden" id="training-county" name="training-county" value="${sessionScope.person.location.county.id}">
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="training-sub-county" name="training-sub-county" class="form-control">
                            <c:forEach var="subCounty" items="${sessionScope.subCounties}" varStatus="index"> 
                                <option value="${subCounty.id}">${subCountytopic.topic}</option>
                            </c:forEach>
                        </select>  
                    </div>
                    <div class="form-group">
                        Ward
                        <select id="training-ward" name="ward" class="form-control">
                            <c:forEach var="ward" items="${sessionScope.wards}" varStatus="index"> 
                                <option value="${ward.id}">${ward.name}</option>
                            </c:forEach>
                        </select>  
                    </div>
                    <div class="form-group">
                        Number of trainees
                        <input type="number" id="number-of-trainees" name="number-of-trainees" class="form-control">
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
