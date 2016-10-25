<%--
    Document   : county_sub_activity_names
    Created on : Jul 15, 2016, 8:05:02 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view sub-activity names </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-countying">
                        List of sub-activity names
                    </div>
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table">
                                <tcounty>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addSubActivityName')">Add</button></th>
                                        <th>Sub-activity name</th>
                                    </tr>
                                </tcounty>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of sub-activity names </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="subActivityName" items="${sessionScope.subActivityNames}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                            <td>${index.count}</td>
                                            <td>${subActivityName.name}</td>
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