<%--
    Document   : warehouses
    Created on : Sep 7, 2016, 9:59:22 AM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                                <th>Type</th>
                                <th>Capacity</th>
                                <th>Offers WRS services</th>
                                <th>Certification</th>
                                <th>Subcounty, County</th>
                                <th>Latitude, Longitude</th>
                                <th>Operator</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="9"> List of warehouses </td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="warehouse" items="${sessionScope.warehouses}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td class="pointable" onclick ="loadEquimentWindow(${warehouse.id})">${warehouse.name}</td>
                                    <td class="pointable" onclick ="loadEquimentWindow(${warehouse.id})">${warehouse.warehouseType.category.name}</td>
                                    <td class="pointable" onclick ="loadEquimentWindow(${warehouse.id})">${warehouse.capacity} ${warehouse.units.unit}</td>
                                    <td class="pointable" onclick ="loadEquimentWindow(${warehouse.id})">${warehouse.offersWrs}</td>
                                    <td class="pointable" onclick ="loadEquimentWindow(${warehouse.id})">${warehouse.certified}</td>
                                    <td class="pointable" onclick ="loadEquimentWindow(${warehouse.id})">${warehouse.location.subCounty.name}, ${warehouse.location.county.name}</td>
                                    <td class="pointable" onclick ="loadEquimentWindow(${warehouse.id})">${warehouse.location.latitude}, ${warehouse.location.longitude}</td>
                                    <td class="pointable" onclick ="loadEquimentWindow(${warehouse.id})">${warehouse.warehouseOperator.category.name}</td>
                                    <td><button onclick="editWarehouse('${warehouse.id}', '${warehouse.name}', '${warehouse.warehouseType.id}', '${warehouse.capacity}', '${warehouse.units.id}', '${warehouse.offersWrs}', '${warehouse.certified}', '${warehouse.location.id}', '${warehouse.location.subCounty.id}', '${warehouse.location.county.id}', '${warehouse.location.latitude}', '${warehouse.location.longitude}', '${warehouse.warehouseOperator.id}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deleteWarehouse(${warehouse.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row dialog" id="warehouse-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">

                    <div class="form-group">
                        Name
                        <input id="warehouse-name" class="form-control">
                    </div>
                    <div class="form-group">
                        Warehouse operator
                        <select id="warehouse-operator" class="form-control">
                            <c:forEach var="warehouseOperator" items="${sessionScope.warehouseOperators}" varStatus="index">
                                <option value="${warehouseOperator.id}">${warehouseOperator.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Warehouse type
                        <select id="warehouse-type" class="form-control">
                            <c:forEach var="warehouseType" items="${applicationScope.warehouseTypes}" varStatus="index">
                                <option value="${warehouseType.id}">${warehouseType.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Capacity
                        <input id="capacity" class="form-control">
                    </div>
                    <div class="form-group">
                        Units
                        <select id="capacity-units" class="form-control">
                            <c:forEach var="measurementUnit" items="${applicationScope.measurementUnits}" varStatus="index">
                                <option value="${measurementUnit.id}">${measurementUnit.unit}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Offers WRS services?
                        <select id="offers-wrs" class="form-control">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Certified?
                        <select id="certified" class="form-control">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Latitude
                        <input id="warehouse-latitude" class="form-control">
                    </div>
                    <div class="form-group">
                        Longitude
                        <input id="warehouse-longitude" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="hidden" id="warehouse-county" value="${sessionScope.person.location.county.id}">
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="warehouse-sub-county" class="form-control">
                            <c:forEach var="subCounty" items="${sessionScope.subCounties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Ward
                        <select id="warehouse-ward" class="form-control">
                            <c:forEach var="ward" items="${sessionScope.wards}" varStatus="index">
                                <option value="${ward.id}">${ward.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>