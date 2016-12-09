<%--
    Document   : output_level_reports
    Created on : Sep 28, 2016, 11:26:22 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <th rowspan="2">Indicator type</th>
                            <th rowspan="2">Unit</th>
                                <c:forEach var="year" items="${sessionScope.projectYears}" varStatus="index">
                                <th colspan="3">Year ${index.count} (${year})</th>
                                </c:forEach>
                            <th colspan="3">Cummulative</th>
                        </tr>
                        <tr>
                            <c:forEach var="year" items="${sessionScope.projectYears}">
                                <th>AWPB Target</th>
                                <th>Actual</th>
                                <th>% AWPB</th>
                                </c:forEach>
                            <th>Appraisal Target</th>
                            <th>Actual</th>
                            <th>% Appraisal</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <td colspan="${8 + (fn:length(sessionScope.projectYears) * 3)}"> Report on output level indicators</td>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="outputIndicator" items="${sessionScope.outputsReport.keySet()}" varStatus="index">
                            <tr>
                                <td>${index.count}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${outputIndicator.resultHierarchy.description}">${outputIndicator.resultHierarchy.description}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${outputIndicator.description}">${outputIndicator.description}</td>
                                <td>${outputIndicator.performanceIndicatorType.category.name}</td>
                                <td class="editable" data-placement="auto bottom" title="${outputIndicator.description}"></td>
                                <c:forEach var="cummulativeIndicatorValues" items="${sessionScope.outputsReport.get(outputIndicator).keySet()}">
                                    <c:forEach var="outputIndicatorValues" items="${sessionScope.outputsReport.get(outputIndicator).get(cummulativeIndicatorValues)}">
                                        <td class="editable">${outputIndicatorValues.expectedValue}</td>
                                        <td>${outputIndicatorValues.actualValue}</td>
                                        <td><c:if test="${not empty outputIndicatorValues.ratio}">${outputIndicatorValues.ratio}%</c:if></td>
                                    </c:forEach>
                                    <td id="appraisal-target-${cummulativeIndicatorValues.id}" class="editable pencil" onclick="setAppraisalTarget('${cummulativeIndicatorValues.id}', '${cummulativeIndicatorValues.actualValue}', '${cummulativeIndicatorValues.expectedValue}', '${outputIndicator.description}')">${cummulativeIndicatorValues.expectedValue}</td>
                                    <td>${cummulativeIndicatorValues.actualValue}</td>
                                    <td id="output-ratio-${cummulativeIndicatorValues.id}"><c:if test="${not empty cummulativeIndicatorValues.ratio}">${cummulativeIndicatorValues.ratio}%</c:if></td>
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