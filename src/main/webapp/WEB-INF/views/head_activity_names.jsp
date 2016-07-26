<%-- 
    Document   : head_activity_names
    Created on : Jul 22, 2016, 11:26:41 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view activity names </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of activity names
                    </div>
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addActivityName')">Add</button></th>
                                        <th>Activity name</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of activity names </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="activityName" items="${sessionScope.activityNames}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd pointable"</c:if><c:if test="${index.count % 2 != 0}">class="pointable"</c:if> onclick="loadSubActivityNamesWindow(${activityName.id})">
                                            <td>${index.count}</td>
                                            <td>${activityName.name}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>