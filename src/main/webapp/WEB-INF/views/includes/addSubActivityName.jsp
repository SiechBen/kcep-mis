<%--
    Document   : addSubActivityName
    Created on : Oct 26, 2016, 10:25:00 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Sub-activity name details
            </div>
            <div class="panel-body">
                <form role="form">
                    <input type="hidden" id="activity-name-id" value="${sessionScope.activityNameId}">
                    <div class="form-group">
                        Sub-activity name
                        <input id="name" class="form-control">
                    </div>
                    <button type="button" class="btn btn-outline btn-primary" onclick="addSubActivityName()">Save sub-activity name</button>
                </form>
            </div>
        </div>
    </div>
</div>