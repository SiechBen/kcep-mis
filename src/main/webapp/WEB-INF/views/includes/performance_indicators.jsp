<%-- 
    Document   : performance_indicators
    Created on : Sep 7, 2016, 11:38:11 AM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of key performance indicators
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addPerformanceIndicator')">Add</button></th>
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
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="8"> List of key performance indicators</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="performanceIndicator" items="${sessionScope.performanceIndicators}" varStatus="index">
                                <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                    <td>${index.count}</td>
                                    <td>${performanceIndicator.performanceIndicatorType.type}</td>
                                    <td>${performanceIndicator.resultHierarchy.description}</td>
                                    <td>${performanceIndicator.description}</td>
                                    <td>${performanceIndicator.baselineDate}</td>
                                    <td>${performanceIndicator.baselineValue}</td>
                                    <td>${performanceIndicator.yearOfUse}</td>
                                    <td>${performanceIndicator.actualValue}</td>
                                    <td>${performanceIndicator.expectedValue}</td>
                                    <td>${performanceIndicator.ratio}</td>
                                    <td><button onclick="doeditPerfomance_indicators('${perfomanceIndicator.id}','${performanceIndicator.performanceIndicatorType.type}','${performanceIndicator.resultHierarchy.description}','${performanceIndicator.description}','${performanceIndicator.baselineDate}','${performanceIndicator.baselineValue}','${performanceIndicator.yearOfUse}','${performanceIndicator.actualValue}','${performanceIndicator.expectedValue}','${performanceIndicator.ratio}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="dodeletePerfomance_indicators(${perfomanceIndicator.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
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

                </form>
            </div>
        </div>
    </div>