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
                    <h1 class="page-header">Ward Agricultural Officer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
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
            </div>
            <!-- /.row -->
            <div class="row">
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
            </div>
        </div>

    </jsp:attribute>
</kcep:ward>