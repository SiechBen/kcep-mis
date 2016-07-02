<%-- 
    Document   : region_programmes
    Created on : Jun 22, 2016, 5:07:55 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view programmes </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of programmes
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addProgramme')">Add</button></th>
                                        <th>Component</th>
                                        <th>Sub-component</th>
                                        <th>Implementing partner</th>
                                        <th>Activity</th>
                                        <th>Start period</th>
                                        <th>End period</th>
                                        <th>Unit</th>
                                        <th>AWPB target</th>
                                        <th>Programme target</th>
                                        <th>Value achieved</th>
                                        <th>Requested budget</th>
                                        <th>Actual expenditure</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of programmes</td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="programme" items="${sessionScope.programmes}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                            <td>${index.count}</td>
                                            <td>${programme.component.component}</td>
                                            <td>${programme.subComponent.subComponent}</td>
                                            <td>${programme.implementingPartner.personRole.personRole}</td>
                                            <td>${programme.activity.description}</td>
                                            <td>${programme.startPeriod}</td>
                                            <td>${programme.endPeriod}</td>
                                            <td>${programme.measurementUnit.unit}</td>
                                            <td>${programme.awpbTarget}</td>
                                            <td>${programme.programmeTarget}</td>
                                            <td>${programme.valueAchieved}</td>
                                            <td>${programme.requestedBudget}</td>
                                            <td>${programme.actualExpenditure}</td>
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
</kcep:region>
