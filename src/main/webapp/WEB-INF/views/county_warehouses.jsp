<%-- 
    Document   : county_warehouses
    Created on : Jun 25, 2016, 11:36:17 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouses </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of warehouses
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table" id="warehouse-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addWarehouse')">Add</button></th>
                                        <th>Name</th>
                                        <th>Capacity</th>
                                        <th>Offers WRS services</th>
                                        <th>Certification</th>
                                        <th>Subcounty, County</th>
                                        <th>Latitude, Longitude</th>
                                        <th>Operator</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of warehouses </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="warehouse" items="${sessionScope.warehouses}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if> onclick="loadEquimentWindow(${warehouse.id})">
                                            <td>${index.count}</td>
                                            <td>${warehouse.name}</td>
                                            <td>${warehouse.capacity} ${warehouse.units.unit}</td>
                                            <td>${warehouse.offersWrs}</td>
                                            <td>${warehouse.certified}</td>
                                            <td>${warehouse.location.subCounty.name}, ${warehouse.location.county.name}</td>
                                            <td>${warehouse.location.latitude}, ${warehouse.location.longitude}</td>
                                            <td>${warehouse.warehouseOperator.name}</td>
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