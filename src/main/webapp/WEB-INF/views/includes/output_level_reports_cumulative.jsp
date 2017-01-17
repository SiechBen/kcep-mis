<%--
    Document   : output_level_reports
    Created on : Sep 28, 2016, 11:26:22 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Report for output level indicators
            </div>
            <div class="panel-body">
                <h4>Output level indicators report</h4>
                <table class="table table-striped table-bordered table-hover indicator-report-table">
                    <thead>
                        <tr>
                            <th rowspan="2">&nbsp;</th>
                            <th rowspan="2">Output</th>
                            <th rowspan="2">Indicator</th>
                            <th rowspan="2">Indicator source</th>
                            <th rowspan="2">Unit</th>
                            <th rowspan="2">Baseline date</th>
                            <th rowspan="2">Baseline value</th>
                                <c:forEach var="year" items="${sessionScope.projectYears}" varStatus="index">
                                <th colspan="3">Year ${year}</th>
                                </c:forEach>
                            <th rowspan="2">Appraisal Target</th>
                            <th colspan="2">Cumulative</th>
                        </tr>
                        <tr>
                            <c:forEach var="year" items="${sessionScope.projectYears}">
                                <th>Annual Target</th>
                                <th>Actual</th>
                                <th>% Annual</th>
                                </c:forEach>
                            <th>Actual</th>
                            <th>% Appraisal</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <td colspan="${10 + (fn:length(sessionScope.projectYears) * 3)}"> Report on output level indicators</td>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="outputIndicator" items="${sessionScope.outputsReport.keySet()}" varStatus="index">
                            <tr>
                                <td>${index.count}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${outputIndicator.resultHierarchy.description}">${outputIndicator.resultHierarchy.description}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${outputIndicator.description}">${outputIndicator.description}</td>
                                <td>${outputIndicator.performanceIndicatorType.category.name}</td>
                                <td class="editable pencil" onclick="editMeasurementUnit(this, ${outputIndicator.id}, '${outputIndicator.measurementUnit.id}', '${outputIndicator.description}')">${outputIndicator.measurementUnit.unit}<c:if test="${measurementUnit.symbol}">(${measurementUnit.symbol})</c:if></td>
                                <td class="editable pencil" onclick="editBaselineDate(this, ${outputIndicator.id}, '${outputIndicator.baselineDate}', '${outputIndicator.description}')"><fmt:formatDate pattern="yy-MMM-dd" value="${outputIndicator.baselineDate}"/></td>
                                <td class="editable pencil" onclick="editBaselineValue(this, ${outputIndicator.id}, '${outputIndicator.baselineValue}', '${outputIndicator.description}')">${outputIndicator.baselineValue}</td>
                                <c:forEach var="cumulativeIndicatorValues" items="${sessionScope.outputsReport.get(outputIndicator).keySet()}">
                                    <c:forEach var="outputIndicatorValues" items="${sessionScope.outputsReport.get(outputIndicator).get(cumulativeIndicatorValues)}">
                                        <td class="editable">${outputIndicatorValues.expectedValue}</td>
                                        <td>${outputIndicatorValues.actualValue}</td>
                                        <td><c:if test="${not empty outputIndicatorValues.ratio}">${outputIndicatorValues.ratio}%</c:if></td>
                                    </c:forEach>
                                    <td id="appraisal-target-${outputIndicator.id}" class="editable pencil" onclick="setAppraisalTarget('${outputIndicator.id}', '${cumulativeIndicatorValues.actualValue}', '${outputIndicator.appraisalTarget}', '${outputIndicator.description}')">${outputIndicator.appraisalTarget}</td>
                                    <td>${cumulativeIndicatorValues.actualValue}</td>
                                    <td id="output-ratio-${cumulativeIndicatorValues.id}"><c:if test="${not empty cumulativeIndicatorValues.ratio}">${cumulativeIndicatorValues.ratio}%</c:if></td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="output-report-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Appraisal target
                        <input id="appraisal-target" class="form-control">
                    </div>
                    <div class="form-group">
                        Actual value
                        <input id="actual-value" readonly class="form-control">
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