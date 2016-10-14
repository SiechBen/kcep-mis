<%--
    Document   : addWarehouse
    Created on : Sep 7, 2016, 9:32:29 AM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Warehouse details
            </div>
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
