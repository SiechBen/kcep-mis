<%--
    Document   : performance_indicators
    Created on : Sep 7, 2016, 11:38:11 AM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of key performance indicators
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addProjectYear</label>
                    <table class="table table-striped table-bordered table-hover" id="performance-indicator-table">
                        <thead>
                            <tr>
                                <th rowspan="2">&nbsp;</th>
                                <th rowspan="2">Performance indicator source</th>
                                <th rowspan="2">Result hierarchy</th>
                                <th rowspan="2">Key Performance Indicator</th>
                                <th rowspan="2">Baseline date</th>
                                <th rowspan="2">Baseline value</th>
                                    <c:forEach var="year" items="${sessionScope.projectYears}" varStatus="index">
                                    <th colspan="4">Year ${index.count} (${year})</th>
                                    </c:forEach>
                            </tr>
                            <tr>
                                <c:forEach var="year" items="${sessionScope.projectYears}">
                                    <th>Actual value</th>
                                    <th>Expected value</th>
                                    <th>Ratio</th>
                                    <th>&nbsp;</th>
                                    </c:forEach>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="${6 + (fn:length(sessionScope.projectYears) * 4)}"> List of key performance indicators</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="performanceIndicator" items="${sessionScope.performanceIndicatorsMap.keySet()}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${performanceIndicator.performanceIndicatorType.category.name}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${performanceIndicator.resultHierarchy.description}">${performanceIndicator.resultHierarchy.description}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${performanceIndicator.description}">${performanceIndicator.description}</td>
                                    <td class="editable pencil" onclick="editBaselineDate('${performanceIndicator.id}', '${performanceIndicator.baselineDate}', '${performanceIndicator.description}')"><fmt:formatDate pattern="yy-MMM-dd" value="${performanceIndicator.baselineDate}"/></td>
                                    <td class="editable pencil" onclick="editBaselineValue('${performanceIndicator.id}', '${performanceIndicator.baselineValue}', '${performanceIndicator.description}')">${performanceIndicator.baselineValue}</td>
                                    <c:forEach var="performanceIndicatorValues" items="${sessionScope.performanceIndicatorsMap.get(performanceIndicator)}">
                                        <td>${performanceIndicatorValues.actualValue}</td>
                                        <td class="editable">${performanceIndicatorValues.expectedValue}</td>
                                        <td><c:if test="${not empty performanceIndicatorValues.ratio}">${performanceIndicatorValues.ratio}%</c:if></td>
                                        <td><button onclick="editPerformanceIndicatorValues('${performanceIndicatorValues.id}', '${performanceIndicatorValues.expectedValue}', '${performanceIndicatorValues.actualValue}', '${performanceIndicatorValues.ratio}', '${performanceIndicator.description}')"><span class="glyphicon glyphicon-pencil"></span></button></td></c:forEach>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="performance-indicators-values-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Expected value
                        <input type="number" step="0.01"  id="expected-value" value="0.00" class="form-control">
                    </div>
                    <div class="form-group">
                        Actual value
                        <input type="number" step="0.01"  id="actual-value" readonly value="0.00" class="form-control">
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

<div class="row dialog" id="project-year-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Year
                        <select id="project-year" class="form-control yearfield" ></select>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="baseline-date-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Baseline date
                        <input id="baseline-date" class="form-control datefield" type="date">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="baseline-value-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Baseline value
                        <input type="number" step="0.01"  id="baseline-value" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>