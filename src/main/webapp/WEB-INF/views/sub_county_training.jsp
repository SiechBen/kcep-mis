<%-- 
    Document   : sub_county_training
    Created on : Jun 22, 2016, 5:16:28 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view training </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Sub-county Desk Officer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
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
                                            <th>Attendance sheet</th>
                                            <th>Category of trainees</th>
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
                                                <td>${training.topic}</td>
                                                <td>${training.venue.county.name}</td>
                                                <td>${training.venue.subCounty.name}</td>
                                                <td>${training.venue.ward.name}</td>
                                                <td>${training.numberOfTrainees}</td>
                                                <td>${training.categoryOfTrainees.personRole}</td>
                                                <td><a href="download?filePath=${training.attendanceSheet}" target="_blank">${training.fileName}</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

        </div>
        <!-- /#page-wrapper -->

    </jsp:attribute>
</kcep:sub_county>