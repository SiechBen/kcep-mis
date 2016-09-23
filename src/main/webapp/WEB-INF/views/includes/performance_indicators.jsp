<%--
    Document   : performance_indicators
    Created on : Sep 7, 2016, 11:38:11 AM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of key performance indicators
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addPerformanceIndicator</label>
                    <table class="table table-striped table-bordered table-hover" id="performance-indicator-table">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Performance indicator type</th>
                                <th>Result hierarchy</th>
                                <th>Key Performance Indicator</th>
                                <th>Baseline date</th>
                                <th>Baseline value</th>
                                <th>Year</th>
                                <th>Actual value</th>
                                <th>Expected value</th>
                                <th>Ratio</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;
                                </th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="8"> List of key performance indicators</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="performanceIndicator" items="${sessionScope.performanceIndicators}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${performanceIndicator.performanceIndicatorType.category.name}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${performanceIndicator.resultHierarchy.description}">${performanceIndicator.resultHierarchy.description}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${performanceIndicator.description}">${performanceIndicator.description}</td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${performanceIndicator.baselineDate}"/></td>
                                    <td>${performanceIndicator.baselineValue}</td>
                                    <td>${performanceIndicator.yearOfUse}</td>
                                    <td>${performanceIndicator.actualValue}</td>
                                    <td>${performanceIndicator.expectedValue}</td>
                                    <td>${performanceIndicator.ratio}</td>
                                    <td><button onclick="editPerformanceIndicator(
                                                    '${performanceIndicator.id}',
                                                    '${performanceIndicator.performanceIndicatorType.id}',
                                                    '${performanceIndicator.resultHierarchy.id}',
                                                    '${performanceIndicator.description}',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${performanceIndicator.baselineDate}"/>',
                                                    '${performanceIndicator.baselineValue}',
                                                    '${performanceIndicator.yearOfUse}',
                                                    '${performanceIndicator.actualValue}',
                                                    '${performanceIndicator.expectedValue}',
                                                    '${performanceIndicator.ratio}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deletePerformanceIndicator(${performanceIndicator.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="performance-indicators-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Performance indicator type
                        <select id="performance-indicator-type" class="form-control">
                            <c:forEach var="performanceIndicatorType" items="${sessionScope.performanceIndicatorTypes}">
                                <option value="${performanceIndicatorType.id}">${performanceIndicatorType.category.name}</option>
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
                        <input id="ratio" class="form-control" readonly>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>