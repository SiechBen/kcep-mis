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
                <table class="table table-striped table-bordered table-hover" id="indicator-report-table">
                    <thead>
                        <tr>
                            <th>&nbsp;</th>
                            <th>Country</th>
                            <th colspan="2"><input id="country-name"></th>
                            <th>Report date</th>
                            <th colspan="3"><input id="report-date" class="datefield"></th>
                        </tr>
                        <tr>
                            <th>&nbsp;</th>
                            <th>Project</th>
                            <th colspan="2"><input id="project-name"></th>
                            <th>Project year</th>
                            <th colspan="3">
                                <select id="project-year">
                                    <c:forEach var="projectYear" items="${sessionScope.projectYears}" varStatus="index">
                                        <option value="${projectYear}">${projectYear}</option>
                                    </c:forEach>
                                </select>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="7">Second level results</th>
                        </tr>
                        <tr>
                            <th>&nbsp;</th>
                            <th>Outcome</th>
                            <th>Outcome indicator</th>
                            <th>Unit</th>
                            <th>AWPB</th>
                            <th>Actual</th>
                            <th>% of AWPB</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th colspan="7">Report on second level results</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="outputIndicator" items="${sessionScope.indicatorsReport.keySet()}" varStatus="index">
                            <tr>
                                <td>${index.count}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${outputIndicator.resultHierarchy.description}">${outputIndicator.resultHierarchy.description}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${outputIndicator.description}">${outputIndicator.description}</td>
                                <td>${outputIndicator.performanceIndicatorType.category.name}</td>
                                <td class="editable" data-placement="auto bottom" title="${outputIndicator.description}"></td>
                                <td class="editable" data-placement="auto bottom" title="${outputIndicator.description}"></td>
                                <td class="editable" data-placement="auto bottom" title="${outputIndicator.description}"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>