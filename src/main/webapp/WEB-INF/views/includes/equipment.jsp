<%--
    Document   : equipment
    Created on : Sep 7, 2016, 12:26:40 PM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Equipment and warehouse operations in ${sessionScope.warehouse.name} warehouse
            </div>
            <div class="panel-body">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#equipment" data-toggle="tab">Equipment</a>
                    </li>
                    <li>
                        <a href="#warehouse-operations" data-toggle="tab">Warehouse operations</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="equipment">
                        <h4>Equipment Details</h4>
                        <label hidden id="add-label">addEquipment</label>
                        <table id="equipment-table" class="table table-striped table-bordered table-hover data-table">
                            <thead>
                                <tr>
                                    <th>&nbsp;</th>
                                    <th>Equipment type</th>
                                    <th>Serial number</th>
                                    <th>Total count</th>
                                    <th>Status</th>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <td colspan="7"> List of equipment </td>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="equipment" items="${sessionScope.equipment}" varStatus="index">
                                    <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                        <td>${index.count}</td>
                                        <td>${equipment.type}</td>
                                        <td>${equipment.serialNumber}</td>
                                        <td>${equipment.totalCount}</td>
                                        <td>${equipment.status}</td>
                                        <td><button onclick="editEquipment('${equipment.id}', '${equipment.warehouse.id}', '${equipment.type}', '${equipment.serialNumber}', '${equipment.totalCount}', '${equipment.status}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deleteEquipment('${equipment.id}', '${equipment.warehouse.id}')"><span class="glyphicon glyphicon-trash"></span></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane fade" id="warehouse-operations">
                        <h4>Warehouse operations</h4>
                        <table id="warehouse-operations-table" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th><button type="button" class="btn btn-outline btn-primary" onclick="addWarehouseOperation(${sessionScope.warehouse.id}); return false;">Add</button></th>
                                    <th colspan="2"> Produce brought in(bags) </th>
                                    <th colspan="5"> Produce sold </th>
                                    <th colspan="2"> &nbsp; </th>
                                </tr>
                                <tr>
                                    <th> &nbsp; </th>
                                    <th> Quantity(bags) </th>
                                    <th> Produce type </th>
                                    <th> Selling date </th>
                                    <th> Quantity(bags) </th>
                                    <th> Produce type </th>
                                    <th> Selling price per bag </th>
                                    <th> Sold to who? </th>
                                    <th> &nbsp; </th>
                                    <th> &nbsp; </th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <td colspan="10"> List of warehouse operations </td>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="warehouseOperation" items="${sessionScope.warehouseOperations}" varStatus="index">
                                    <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                        <td>${index.count}</td>
                                        <td>${warehouseOperation.quantityBrought}</td>
                                        <td>${warehouseOperation.produceTypeBrought.name}</td>
                                        <td><fmt:formatDate pattern="MM/dd/yyyy" value="${warehouseOperation.sellingDate}"/></td>
                                        <td>${warehouseOperation.quantitySold}</td>
                                        <td>${warehouseOperation.produceTypeSold.name}</td>
                                        <td>${warehouseOperation.sellingPrice}</td>
                                        <td>${warehouseOperation.buyer}</td>
                                        <td><button onclick="editWarehouseOperation('${warehouseOperation.id}', '${warehouseOperation.warehouse.id}', '${warehouseOperation.quantityBrought}', '${warehouseOperation.produceTypeBrought.id}', '${warehouseOperation.quantitySold}', '${warehouseOperation.produceTypeSold.id}', '<fmt:formatDate pattern="MM/dd/yyyy" value="${warehouseOperation.sellingDate}"/>', '${warehouseOperation.sellingPrice}', '${warehouseOperation.buyer}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deleteWarehouseOperation('${warehouseOperation.id}', '${warehouseOperation.warehouse.id}')"><span class="glyphicon glyphicon-trash"></span></button></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="3">
                                        <select id="counter" onchange="updateProduceCounts(${sessionScope.warehouse.id})">
                                            <option disabled selected>Select produce type</option>
                                            <c:forEach var="produceType" items="${sessionScope.produceTypes}" varStatus="index">
                                                <option value="${produceType.id}">${produceType.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td colspan="2"> <strong>Total bags brought in</strong> </td>
                                    <td colspan="2"> <strong>Total bags sold</strong> </td>
                                    <td colspan="3"> <strong>Total bags in the warehouse</strong> </td>
                                </tr>
                                <tr id="produce-summary">
                                    <td colspan="3"> &nbsp; </td>
                                    <td colspan="2"> ${sessionScope.totalBagsBroughtIn} </td>
                                    <td colspan="2"> ${sessionScope.totalBagsSold} </td>
                                    <td colspan="3"> ${sessionScope.totalBagsIn} </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="equipment-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Equipment type
                        <input id="equipment-type" name="equipment-type" class="form-control">
                    </div>
                    <div class="form-group">
                        Serial number
                        <input id="serial-number" name="serial-number" class="form-control">
                    </div>
                    <div class="form-group">
                        Total Count
                        <input type="number" id="equipment-total-count" name="equipment-total-count" class="form-control">
                    </div>
                    <div class="form-group">
                        Equipment status
                        <input id="equipment-status" name="equipment-status" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="warehouse-operation-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Quantity brought(bags)
                        <input id="quantity-brought" class="form-control">
                    </div>
                    <div class="form-group">
                        Produce type brought
                        <select id="produce-type-brought" class="form-control">
                            <option disabled selected>Select produce type</option>
                            <c:forEach var="produceType" items="${sessionScope.produceTypes}" varStatus="index">
                                <option value="${produceType.id}">${produceType.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Selling date
                        <input type="date" id="selling-date" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Quantity sold(bags)
                        <input type="number" id="warehouse-operation-quantity-sold" class="form-control">
                    </div>
                    <div class="form-group">
                        Produce type sold
                        <select id="produce-type-sold" class="form-control">
                            <option disabled selected>Select produce type</option>
                            <c:forEach var="produceType" items="${sessionScope.produceTypes}" varStatus="index">
                                <option value="${produceType.id}">${produceType.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Selling price per bag
                        <input type="number" step="0.01" id="selling-price" class="form-control">
                    </div>
                    <div class="form-group">
                        Sold to who?
                        <input id="buyer" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

