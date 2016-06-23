<%-- 
    Document   : kalro
    Created on : Jun 22, 2016, 8:52:36 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - KALRO officer </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">KALRO Officer assigned to KCEP</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Technologies responding to farmers' needs
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Name
                                    <input id="person-name" class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of studies conducted
                                    <input id="national-id" class="form-control">
                                </div>
                                <div class="form-group">
                                    Type/purpose of technology developed
                                    <input id="business-name" class="form-control">
                                </div>
                                <div class="form-group">
                                    Target sub counties
                                    <input id="phone" type="text" class="form-control">
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
                                <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save technology</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Validation workshops
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    No of validation workshops in western region
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    No of validation workshops in eastern region
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Attendance sheet of the validation workshops
                                    <input type="file" class="form-control">
                                </div>
                                <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save workshop</button>
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
                            Dissemination of results
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Number of result set in the western region
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of result set in the eastern region
                                    <input class="form-control">
                                </div>
                                <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save record</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-6 -->
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Extension material and guideline
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Number of the extension material and guidelines developed
                                    <input class="form-control">
                                </div>
                                <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save record</button>
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
                            Soil fertility package
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Number of sampling and analysis activities done
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of packages developed
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Target locations
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    Number of meetings for e-voucher definitions
                                    <input class="form-control">
                                </div>
                                <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save technology</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-6 (nested) -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            On-farm trials and demonstrations
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Number of on-farm trials
                                    <input type="datetime" class="form-control datefield">
                                </div>
                                <div class="form-group">
                                    Number of demonstrations
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Target locations
                                    <input class="form-control">
                                </div>
                                <div class="form-group">
                                    Attendance sheet of the on-farm trials and demonstrations
                                    <input type="file">
                                </div>
                                <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save technology</button>
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
</kcep:kalro>