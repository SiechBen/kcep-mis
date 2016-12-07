<%--
    Document   : uploadPeople
    Created on : Nov 25, 2016, 3:18:26 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<div>
    <div class="col-lg-9">
        <div class="panel panel-default">
            <div class="panel-heading ${sessionScope.uploadedClass}">
                ${applicationScope.populationInfo}
            </div>
            <div class="panel-body">
                <div class="btn btn-outline btn-primary form-control pointable" onclick="loadAjaxWindow('downloadPeopleUploadFile')">
                    Click here to get a copy of people-upload excel file
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Upload the file with records
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
                                        <td colspan="2">
                                            <input type="submit" value="Submit" class="btn btn-outline btn-primary">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

