<%-- 
    Document   : county_addPerformanceIndicator
    Created on : Jul 3, 2016, 3:50:27 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add planning </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Performance indicator details
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                Performance indicator type
                                <select id="performance-indicator-type" class="form-control">
                                    <c:forEach var="performanceIndicatorType" items="${sessionScope.performanceIndicatorTypes}">
                                        <option value="${performanceIndicatorType.id}">${performanceIndicatorType.type}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                            <div class="form-group">
                                Result hierarchy
                                <select id="result-hierarchy" class="form-control">
                                    <c:forEach var="resultHierarchy" items="${sessionScope.resultHierarchies}">
                                        <option value="${resultHierarchy.id}">${resultHierarchy.description}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Key Performance Indicator
                                <input id="description" class="form-control">
                            </div>
                            <div class="form-group">
                                Baseline date
                                <input id="baseline-date" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Baseline value
                                <input type="number" step="0.01"  id="baseline-value" class="form-control">
                            </div>
                            <div class="form-group">
                                Year
                                <select id="year-of-use" class="form-control yearfield" ></select>
                            </div>
                            <div class="form-group">
                                Actual value
                                <input type="number" step="0.01"  id="actual-value"  value="0.00" class="form-control">
                            </div>
                            <div class="form-group">
                                Expected value
                                <input type="number" step="0.01"  id="expected-value" value="0.00" class="form-control">
                            </div>
                            <div class="form-group">
                                Ratio( = (AV/EV) * 100)
                                <input id="ratio" class="form-control" readonly="true">
                            </div>
                            <button type="button" class="btn btn-outline btn-primary" onclick="addPerformanceIndicator()">Save planning</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:county>