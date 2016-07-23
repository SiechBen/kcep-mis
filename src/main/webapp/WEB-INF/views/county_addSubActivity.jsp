<%-- 
    Document   : county_addSubActivity
    Created on : Jul 15, 2016, 8:06:46 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add sub-activity </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Sub-activity details
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <input type="hidden" id="activityPlanningId" value="${sessionScope.activityPlanningId}">
                            <div class="form-group">
                                Sub-activity description
                                <input id="description" class="form-control">
                            </div>   
                            <div class="form-group">
                                Measurement unit
                                <select id="measurement-unit" class="form-control">
                                    <c:forEach var="measurementUnit" items="${applicationScope.measurementUnits}" varStatus="counter">
                                        <option value="${measurementUnit.id}">${measurementUnit.unit}(${measurementUnit.symbol})</option>
                                    </c:forEach>
                                </select>
                            </div>   
                            <div class="form-group">
                                Start Date
                                <input id="start-date" class="form-control datefield">
                            </div>   
                            <div class="form-group">
                                End date
                                <input id="end-date" class="form-control datefield">
                            </div>   
                            <div class="form-group">
                                Actual expenditure
                                <input id="actual-expenditure" type="number" step="0.01" class="form-control">
                            </div>   
                            <button type="button" class="btn btn-outline btn-primary" onclick="addSubActivity()">Save sub-activity</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:county>