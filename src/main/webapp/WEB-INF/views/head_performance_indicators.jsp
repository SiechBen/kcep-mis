<%-- 
    Document   : head_performance_indicators
    Created on : Jul 3, 2016, 3:50:15 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - key performance indicators </jsp:attribute>
    <jsp:attribute name="pagecontent">

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
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>