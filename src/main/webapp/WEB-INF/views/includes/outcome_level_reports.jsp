<%--
    Document   : outcome_level_reports
    Created on : Sep 29, 2016, 12:07:04 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Report for outcome level indicators
            </div>
            <div class="panel-body">
                <h4>Outcome level indicators report</h4>
                <table class="table table-striped table-bordered table-hover indicator-report-table" id="outcome-report-table">
                    <thead>
                        <tr>
                            <th colspan="2">Republic of Kenya</th>
                            <th colspan="7">
                                Report date: &nbsp;
                                <input id="report-date" class="datefield" type="date">
                            </th>
                        </tr>
                        <tr>
                            <th colspan="2">Kenya Cereal Enhancement Programme</th>
                            <th colspan="7">
                                Project year: &nbsp;
                                <select id="project-year" onchange="changeOutcomeReport()">
                                    <c:forEach var="projectYear" items="${sessionScope.projectYears}" varStatus="index">
                                        <option value="${projectYear}" <c:if test="${sessionScope.projectYear == projectYear}">selected</c:if>>${projectYear}</option>
                                    </c:forEach>
                                </select>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="9">Second level results</th>
                        </tr>
                        <tr>
                            <th>&nbsp;</th>
                            <th>Reference code</th>
                            <th>Outcome indicator</th>
                            <th>Unit</th>
                            <th>Target(end of project)</th>
                            <th>Actual accumulated at end of previous year</th>
                            <th>Target end of year</th>
                            <th>Actual end of year</th>
                            <th>% completion of yearly target</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th colspan="9">Report on second level results</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="outcome" items="${sessionScope.outcomesReport}" varStatus="index">
                            <tr>
                                <td>${index.count}</td>
                                <td>&nbsp;</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${outcome.performanceIndicator.description}">${outcome.performanceIndicator.description}</td>
                                <td>Rating</td>
                                <td id="appraisal-target-${outcome.performanceIndicator.id}" class="editable pencil" onclick="setOutcomeAppraisalTarget('${outcome.performanceIndicator.id}', '${outcome.performanceIndicator.appraisalTarget}', '${outcome.performanceIndicator.description}')">${outcome.performanceIndicator.appraisalTarget}</td>
                                <td>${outcome.performanceIndicator.accumulatedActual}</td>
                                <td id="expected-value-${outcome.id}" class="editable pencil" onclick="editOutcomeValue('${outcome.id}', '${outcome.actualValue}', '${outcome.expectedValue}', '${outcome.performanceIndicator.description}')">${outcome.expectedValue}</td>
                                <td id="actual-value-${outcome.id}" class="editable pencil" onclick="editOutcomeValue('${outcome.id}', '${outcome.actualValue}', '${outcome.expectedValue}', '${outcome.performanceIndicator.description}')">${outcome.actualValue}</td>
                                <td id="outcome-ratio-${outcome.id}"><c:if test="${not empty outcome.ratio}">${outcome.ratio}%</c:if></td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="outcome-report-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        End of year target value
                        <select id="expected-value" class="form-control">
                            <c:forEach var="ratingValue" items="${sessionScope.ratingValues}" varStatus="index">
                                <option value="${ratingValue.category.name}">${ratingValue.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        End of year actual value
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

<div class="row dialog" id="appraisal-target-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Appraisal target
                        <input type="number" step="0.01"  id="appraisal-target" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
