<%-- 
    Document   : ward
    Created on : Jun 22, 2016, 8:52:24 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - ward officer </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Ward Extension Officer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Person details
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Name
                                    <input id="person-name" class="form-control">
                                </div>
                                <div class="form-group">
                                    Person role
                                    <select id="person-role" class="form-control">
                                        <c:forEach var="personRole" items="${applicationScope.personRoles}" varStatus="index">
                                            <option value="${personRole.id}">${personRole.personRole}</option>
                                        </c:forEach>
                                    </select>    
                                </div>
                                <div class="form-group">
                                    National id number
                                    <input id="national-id" class="form-control">
                                </div>
                                <div class="form-group">
                                    Business name
                                    <input id="business-name" class="form-control">
                                </div>
                                <div class="form-group">
                                    Sex
                                    <select id="sex" class="form-control">
                                        <c:forEach var="sex" items="${applicationScope.sexes}" varStatus="index"> 
                                            <option value="${sex.id}">${sex.sex}</option>
                                        </c:forEach>
                                    </select> 
                                </div>
                                <div class="form-group">
                                    Phone number
                                    <input id="phone" type="phone" class="form-control">
                                </div>
                                <div class="form-group">
                                    Postal address
                                    <input id="postal-address" class="form-control">
                                </div>
                                <div class="form-group">
                                    Email address
                                    <input id="email" type="email" class="form-control">
                                </div>
                                <div class="form-group">
                                    Farmer group
                                    <select id="farmer-group" class="form-control">
                                        <c:forEach var="farmerGroup" items="${applicationScope.farmerGroups}" varStatus="index"> 
                                            <option value="${farmerGroup.id}">${farmerGroup.name}</option>
                                        </c:forEach>
                                    </select>    
                                </div>
                                <div class="form-group">
                                    County
                                    <select id="person-county" class="form-control">
                                        <c:forEach var="county" items="${applicationScope.counties}" varStatus="index"> 
                                            <option value="${county.id}">${county.name}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                                <div class="form-group">
                                    Sub-county
                                    <input id="person-sub-county" class="form-control">
                                </div>
                                <div class="form-group">
                                    Ward
                                    <input id="person-ward" class="form-control">
                                </div>
                                <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save person</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Number of farmers sampled
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Age bracket</th>
                                            <th>Females</th>
                                            <th>Males</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>15-24 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                        <tr>
                                            <td>24-35 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                        <tr>
                                            <td>Over 35 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Number of farmers that used inputs correctly
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Age bracket</th>
                                            <th>Females</th>
                                            <th>Males</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>15-24 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                        <tr>
                                            <td>24-35 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                        <tr>
                                            <td>Over 35 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Number of farmers that have adopted GAPs
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Age bracket</th>
                                            <th>Females</th>
                                            <th>Males</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>15-24 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                        <tr>
                                            <td>24-35 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                        <tr>
                                            <td>Over 35 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Number of farmers adopted post-harvest management technologies
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Age bracket</th>
                                            <th>Females</th>
                                            <th>Males</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>15-24 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                        <tr>
                                            <td>24-35 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                        <tr>
                                            <td>Over 35 years</td>
                                            <td> <input class="form-control"> </td>
                                            <td> <input class="form-control"> </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Random sampled farmer data
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Season
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of farmers sampled
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of farmers that used inputs correctly
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of farmers that adopted GAPs
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of farmers that have adopted post-harvest management technologies
                                    <input class="form-control">
                                </div>
                            </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Extension and Field Visit Data
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Number of people seeking/ offered advisory services
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Nature of advisory services
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of field visits conducted
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of farmers visited
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Ward locations of the particular field visits
                                    <input class="form-control">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Collection centres
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Operational
                                    <select class="form-control">
                                        <option>Yes</option>
                                        <option>No</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    Latitude
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Longitude
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    County
                                    <select class="form-control">
                                        <option>Baringo</option>
                                        <option>Bomet</option>
                                        <option>...</option>
                                    </select>  </div>
                                <div class="form-group">
                                    Sub-county
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Ward
                                    <input class="form-control">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-6 (nested) -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Training
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Date of training
                                    <input type="datetime" class="form-control datefield">
                                </div>
                                <div class="form-group">
                                    Trainer
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Topic of training
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Venue of training
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of trainees
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Attach attendance sheet showing KCEP & Non-KCEP trainees, gender, age, farmer
                                    group                                                <input type="file">
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </jsp:attribute>
</kcep:ward>