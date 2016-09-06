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
                    <table class="table table-striped table-bordered table-hover data-table">
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
                                <th>Attachments(e.g attendance sheet e.t.c)</th>
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
                                            ${trainer.person.name} 
                                        </c:forEach>
                                    </td>
                                    <td>${training.topic.topic}</td>
                                    <td>${training.venue.county.name}</td>
                                    <td>${training.venue.subCounty.name}</td>
                                    <td>${training.venue.ward.name}</td>
                                    <td class="pointable" onclick="showTrainees(${training.id})">${training.numberOfTrainees}</td>
                                    <td>${training.categoryOfTrainees.personRole}</td>
                                    <td><a href="download?filePath=${training.attendanceSheet}" target="_blank">${training.fileName}</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
