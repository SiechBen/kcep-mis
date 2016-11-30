<%--
    Document   : uploadPeople
    Created on : Nov 25, 2016, 3:18:26 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<div>
    <div class="col-lg-8">
        <div class="panel panel-default">
            <div class="panel-heading ${sessionScope.uploadedClass}">
                ${applicationScope.populationInfo}
            </div>
            <div class="panel-body">
                <form id="success-story-form" action="uploadExcelFile" method="POST" enctype="multipart/form-data">
                    <table class="table table-hover" id="people-upload-table">
                        <tbody>
                            <tr>
                                <td>
                                    <input type="file" name="excel-file" accept=".xlsx" required>
                                </td>
                            </tr>
                            <tr>
                                <th class="divider"></th>
                            </tr>
                            <tr>
                                <td>
                                    This file has records of &nbsp;
                                    <input type="radio" name="file-type" value="169" required> &nbsp; Farmers &nbsp;
                                    <input type="radio" name="file-type" value="170" required> &nbsp; Agro-dealers
                                </td>
                            </tr>
                            <tr>
                                <th class="divider"></th>
                            </tr>
                            <tr>
                                <td>
                                    <input type="checkbox" name="has-heading"> &nbsp; This file has heading row
                                </td>
                            </tr>
                            <tr>
                                <th class="divider"></th>
                            </tr>
                            <tr>
                                <td>
                                    First record is at row number[topmost row is row 1] &nbsp;
                                    <input name="first-row" type="number" step="1">
                                </td>
                            </tr>
                            <tr>
                                <th class="divider"></th>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Submit" class="btn btn-outline btn-primary">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div class="panel panel-default reduced">
            <div class="panel-heading">
                Agro-dealer records excel file should follow the format below
            </div>
            <div class="panel-body">
                <table class="table table-bordered table-hover">
                    <tbody>
                        <tr>
                            <th>Full name</th>
                            <th>Gender(F or M)</th>
                            <th>Business name</th>
                            <th class="pointable" onclick="focusSpan()" data-toggle="tooltip" data-placement="auto bottom" title="Click to download the list of wards and their IDs">Ward ID</th>
                            <th>Phone number</th>
                        </tr>
                        <tr>
                            <td>Francis Muniu</td>
                            <td>M</td>
                            <td>Millenium Mega Choice</td>
                            <td>12</td>
                            <td>0718801292</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="panel panel-default panel-wards reduced">
            <div class="panel-heading">
                Ward names and IDs are listed below
            </div>
            <div class="panel-body">
                <table class="table table-bordered table-hover" id="wards-table">
                    <thead>
                        <tr>
                            <th>Ward ID</th>
                            <th>Ward name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ward" items="${sessionScope.wards}">
                            <tr>
                                <td>${ward.id}</td>
                                <td>${ward.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default reduced">
            <div class="panel-heading">
                Farmer records excel file should follow the format below
            </div>
            <div class="panel-body">
                <table class="table table-bordered table-hover">
                    <tbody>
                        <tr>
                            <th>Full name</th>
                            <th>Gender(F or M)</th>
                            <th>National ID</th>
                            <th>Age(e.g. 32)</th>
                            <th>Bank account number</th>
                            <th>SOL ID</th>
                            <th>Equity branch name</th>
                            <th>Divisional location name</th>
                            <th class="pointable" onclick="focusSpan()" data-toggle="tooltip" data-placement="auto bottom" title="Click to download the list of wards and their IDs">Ward ID</th>
                            <th>Village name</th>
                        </tr>
                        <tr>
                            <td>Winny Chepwogen</td>
                            <td>F</td>
                            <td>24616094</td>
                            <td>32</td>
                            <td>0000167048197</td>
                            <td>031</td>
                            <td>Nakuru Kenyatta Avenue</td>
                            <td>Tuiyotich</td>
                            <td>1</td>
                            <td>Saramek</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
