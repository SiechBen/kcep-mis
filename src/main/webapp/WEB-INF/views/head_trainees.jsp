<%-- 
    Document   : head_trainees
    Created on : Jun 29, 2016, 2:55:04 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view people </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">National Officer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Below is the list of trainees for the training that started on <em>${sessionScope.training.startDate}</em>.
                            The topic of the training was <b>${sessionScope.training.topic.topic}</b>.
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover data-table">
                                    <thead>
                                        <tr>
                                            <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addPerson')">Add</button></th>
                                            <th>Name</th>
                                            <th>National id</th>
                                            <th>Date of birth</th>
                                            <th>Business name</th>
                                            <th>Farmer group</th>
                                            <th>Farmer sub-group</th>
                                            <th>County</th>
                                            <th>Sub-county</th>
                                            <th>Ward</th>  
                                            <th>Phone number</th>
                                            <th>Email address</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <td colspan="8"> List of trainees</td>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach var="trainee" items="${sessionScope.trainees}" varStatus="index">
                                            <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                                <td>${index.count}</td>
                                                <td>${trainee.person.name}</td>
                                                <td>${trainee.person.nationalId}</td>
                                                <td>${trainee.person.dateOfBirth}</td>
                                                <td>${trainee.person.businessName}</td>
                                                <td>${trainee.person.farmerGroup.name}</td>
                                                <td>${trainee.person.farmerSubGroup.name}</td>
                                                <td>${trainee.person.location.county.name}</td>
                                                <td>${trainee.person.location.subCounty.name}</td>
                                                <td>${trainee.person.location.ward.name}</td>
                                                <td>${trainee.person.contact.phone}</td>
                                                <td>${trainee.person.contact.email}</td>
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
</kcep:head>