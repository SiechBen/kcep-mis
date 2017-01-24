<%--
    Document   : documents
    Created on : Jan 17, 2017, 2:16:33 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<div class="row">
    <div class="col-lg-9">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of uploaded KCEP common files
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addDocument</label>
                    <table id="documents-table" class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Document title</th>
                                <th>Date uploaded</th>
                                <th>Uploaded by</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="5"> List of documents</td>

                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="document" items="${sessionScope.documents}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td><a class="file-link" href="download?filePath=${document.name}" target="_blank">${document.fileName}</a></td>
                                    <td><fmt:formatDate pattern="yy-MMM-dd" value="${document.timeUploaded}"/></td>
                                    <td>${document.uploader.name}</td>
                                    <td><button onclick="deleteDocument(${document.id}, this)"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="documents-dialog">
    <form role="form" id="document-form" action="saveDocument" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <table class="table table-bordered table-hover">
                <tbody>
                    <tr>
                        <td>
                            <input type="file" name="document" multiple>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>