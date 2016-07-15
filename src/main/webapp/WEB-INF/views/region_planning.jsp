<%-- 
    Document   : region_planning
    Created on : Jun 22, 2016, 5:07:55 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view planning </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of planning
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addPlanning')">Add</button></th>
                                        <th>Component</th>
                                        <th>Sub-component</th>
                                        <th>Implementing partner</th>
                                        <th>Annual Workplan Reference Code</th>
                                        <th>Key Performance Indicator</th>
                                        <th>AWPB target</th>
                                        <th>Programme target</th>
                                        <th>Value achieved</th>
                                        <th>Allocated budget</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of planning</td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="planning" items="${sessionScope.planning}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd pointable"</c:if> <c:if test="${index.count % 2 != 0}">class="pointable"</c:if> onclick="loadSubActivitiesWindow('${planning.id}')">
                                            <td>${index.count}</td>
                                            <td>${planning.component.component}</td>
                                            <td>${planning.subComponent.subComponent}</td>
                                            <td>${planning.implementingPartner.personRole.personRole}</td>
                                            <td>${planning.annualWorkplanReferenceCode}</td>
                                            <td>${planning.performanceIndicator.description}</td>
                                            <td>${planning.awpbTarget}</td>
                                            <td>${planning.programmeTarget}</td>
                                            <td>${planning.valueAchieved}</td>
                                            <td>${planning.allocatedBudget}</td>
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
