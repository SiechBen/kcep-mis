<%--
    Document   : county_addSubActivityName
    Created on : Jul 15, 2016, 8:06:46 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add sub-activity name </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-countying">
                        Sub-activity name details
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <input type="hidden" id="activityNameId" value="${sessionScope.activityNameId}">
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

    </jsp:attribute>
</kcep:county>