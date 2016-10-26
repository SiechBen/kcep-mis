<%--
    Document   : sub_activity_names
    Created on : Oct 26, 2016, 10:15:19 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of sub-activity names
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addSubActivityName</label>
                    <table id="sub-activity-name-table" class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Sub-activity name</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="4"> List of sub-activity names </td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="subActivityName" items="${sessionScope.subActivityNames}" varStatus="index">
                                <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                    <td>${index.count}</td>
                                    <td>${subActivityName.name}</td>
                                    <td><button onclick='editSubActivityName("${subActivityName.id}", "${subActivityName.name}", "${subActivityName.activityName.id}")'><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deleteSubActivityName(${subActivityName.id}, ${subActivityName.activityName.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="sub-activity-name-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Sub-activity Name
                        <input class="form-control" id="name" required>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
