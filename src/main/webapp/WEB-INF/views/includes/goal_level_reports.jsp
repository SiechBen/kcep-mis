<%--
    Document   : goal_level_reports
    Created on : Oct 2, 2016, 5:44:08 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Report for goal level indicators
            </div>
            <div class="panel-body">
                <h4>Goal level indicators report</h4>
                <table class="table table-striped table-bordered table-hover indicator-report-table data-table" id="goal-report-table">
                    <thead>
                        <tr>
                            <th colspan="2">Republic of Kenya</th>
                            <th colspan="6">
                                Report date: &nbsp;
                                <input id="report-date" class="datefield" type="date">
                            </th>
                        </tr>
                        <tr>
                            <th colspan="2">Kenya Cereal Enhancement Programme</th>
                            <th colspan="6">
                                Programme year: &nbsp;
                                <select id="project-year" onchange="changeGoalReport()">
                                    <c:forEach var="projectYear" items="${sessionScope.projectYears}" varStatus="index">
                                        <option value="${projectYear}">${projectYear}</option>
                                    </c:forEach>
                                </select>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="8">Third level results</th>
                        </tr>
                        <tr>
                            <th>&nbsp;</th>
                            <th>Impact</th>
                            <th>Number</th>
                            <th>Baseline/benchmark</th>
                            <th>Mid-term review</th>
                            <th>Impact assessment</th>
                            <th>Endterm evaluation</th>
                            <th>Target</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th colspan="8">Report on third level results</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="goal" items="${sessionScope.goalsReport}" varStatus="index">
                            <tr>
                                <td>${index.count}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${goal.performanceIndicator.resultHierarchy.description}">${goal.performanceIndicator.resultHierarchy.description}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${goal.performanceIndicator.description}">${goal.performanceIndicator.description}</td>
                                <td class="editable pencil" onclick="editMeasurementUnit(this, ${goal.performanceIndicator.id}, '${goal.performanceIndicator.measurementUnit.id}', '${goal.performanceIndicator.description}')">${goal.performanceIndicator.measurementUnit.unit}<c:if test="${measurementUnit.symbol}">(${measurementUnit.symbol})</c:if></td>
                                <td id="expected-value-${goal.id}" onclick="editGoalValue('${goal.id}', '${goal.actualValue}', '${goal.expectedValue}', '${goal.performanceIndicator.description}')">${goal.expectedValue}</td>
                                <td id="actual-value-${goal.id}" onclick="editGoalValue('${goal.id}', '${goal.actualValue}', '${goal.expectedValue}', '${goal.performanceIndicator.description}')">${goal.actualValue}</td>
                                <td id="goal-ratio-${goal.id}"><c:if test="${not empty goal.ratio}">${goal.ratio}%</c:if></td>
                                    <td>&nbsp;</td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="goal-report-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        AWPB target
                        <select id="expected-value" class="form-control">
                            <c:forEach var="ratingValue" items="${sessionScope.ratingValues}" varStatus="index">
                                <option value="${ratingValue.category.name}">${ratingValue.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Actual value
                        <select id="actual-value" class="form-control">
                            <c:forEach var="ratingValue" items="${sessionScope.ratingValues}" varStatus="index">
                                <option value="${ratingValue.category.name}">${ratingValue.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Ratio( = (AV/EV) * 100)
                        <input id="ratio" readonly class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="measurement-unit-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Measurement unit
                        <select id="measurement-unit" class="form-control">
                            <option disabled>Select measurement unit</option>
                            <c:forEach var="measurementUnit" items="${sessionScope.measurementUnits}">
                                <option value="${measurementUnit.id}">${measurementUnit.unit} <c:if test="${measurementUnit.symbol}">(${measurementUnit.symbol})</c:if></option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>