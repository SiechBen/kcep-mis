<%-- 
    Document   : kalro_addTraining
    Created on : Jun 22, 2016, 4:26:17 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add training </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">KALRO Officer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
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
                                    <input id="start-date" name="start-date" class="form-control datefield">
                                </div>
                                <div class="form-group">
                                    End date
                                    <input id="end-date" name="end-date" class="form-control datefield">  
                                </div>
                                <div class="form-group">
                                    Trainers
                                    <select id="trainer" name="trainer" class="form-control" onchange="addToTrainers()">
                                        <option selected>Select trainer</option>
                                        <c:forEach var="training" items="${sessionScope.people}" varStatus="index"> 
                                            <option value="${training.id}">${training.name}</option>
                                        </c:forEach>
                                    </select> 
                                    <input type="text" id="trainer-names" value="" class="form-control">
                                    <input type="hidden" id="trainer-ids" name="trainer-ids" value="">
                                </div>
                                <div class="form-group">
                                    Topic
                                    <input id="topic" name="topic" class="form-control">
                                </div>
                                <div class="form-group">
                                    County
                                    <select id="training-county" name="training-county" class="form-control">
                                        <c:forEach var="county" items="${applicationScope.counties}" varStatus="index"> 
                                            <option value="${county.id}">${county.name}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                                <div class="form-group">
                                    Sub-county
                                    <select id="training-sub-county" name="training-sub-county" class="form-control">
                                        <c:forEach var="subCounty" items="${applicationScope.subCounties}" varStatus="index"> 
                                            <option value="${subCounty.id}">${subCounty.name}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                                <div class="form-group">
                                    Ward
                                    <select id="training-ward" name="ward" class="form-control">
                                        <c:forEach var="ward" items="${applicationScope.wards}" varStatus="index"> 
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
                                        <c:forEach var="personRole" items="${applicationScope.personRoles}" varStatus="index"> 
                                            <option value="${personRole.id}">${personRole.personRole}</option>
                                        </c:forEach>
                                    </select>  
                                </div>  
                                <div class="form-group">
                                    Attendance sheet
                                    <input type="file" id="attendance-sheet" name="attendance-sheet" class="form-control">
                                </div>
                                <input type="submit" class="btn btn-outline btn-primary" value="Save training">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:kalro>