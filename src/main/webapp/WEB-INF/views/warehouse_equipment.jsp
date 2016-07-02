<%-- 
    Document   : warehouse_equipment
    Created on : Jun 22, 2016, 4:51:41 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:genericpage>
    <jsp:attribute name="title"> KCEP-MIS - view warehouse equipment </jsp:attribute>
    <jsp:attribute name="menuitems"></jsp:attribute>
    <jsp:attribute name="content">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of equipment in ${sessionScope.warehouse.name} warehouse
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addEquipment')">Add</button></th>
                                        <th>Equipment type</th>
                                        <th>Total count</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of e-vouchers </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="equipment" items="${sessionScope.equipment}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                            <td>${index.count}</td>
                                            <td>${equipment.type}</td>
                                            <td>${equipment.totalCount}</td>
                                            <td>${equipment.status}</td>
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
</kcep:genericpage>