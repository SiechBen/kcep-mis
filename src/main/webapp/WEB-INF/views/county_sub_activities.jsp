<%-- 
    Document   : county_sub_activities
    Created on : Jul 15, 2016, 8:05:02 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view sub-activities </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of sub-activites
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addSubActivity')">Add</button></th>
                                        <th>Sub-activity description</th>
                                        <th>Measurement unit</th>
                                        <th>Start date</th>
                                        <th>End date</th>
                                        <th>Actual expenditure</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of sub-activities </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="subActivity" items="${sessionScope.subActivities}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                            <td>${index.count}</td>
                                            <td>${subActivity.description}</td>
                                            <td>${subActivity.measurementUnit.unit}(${subActivity.measurementUnit.symbol})</td>
                                            <td>${subActivity.startDate})</td>
                                            <td>${subActivity.endDate})</td>
                                            <td>${subActivity.actualExpenditure})</td>
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
</kcep:county>