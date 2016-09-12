<%-- 
    Document   : equipment
    Created on : Sep 7, 2016, 12:26:40 PM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of equipment in ${sessionScope.warehouse.name} warehouse
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
                        <table id="equipment-table" class="table table-striped table-bordered table-hover data-table">                         
                            <thead>
                                <tr>
                                    <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addEquipment')">Add</button></th>
                                    <th>Equipment type</th>
                                    <th>Total count</th>
                                    <th>Status</th>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
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
                                        <td><button onclick="editEquipment('${equipment.id}', '${equipment.warehouse.id}', '${equipment.type}', '${equipment.totalCount}', '${equipment.status}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deleteEquipment('${equipment.id}', '${equipment.warehouse.id}', )"><span class="glyphicon glyphicon-trash"></span></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane fade" id="warehouse-operations">
                        <h4>Warehouse operations</h4>
                        <table id="warehouse-operations-table" class="table table-striped table-bordered table-hover data-table">                         
                            <thead>
                                <tr>
                                    <th><button type="button" class="btn btn-outline btn-primary" onclick="addWarehouseOperation(); return false;">Add</button></th>
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
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="warehouseOperation" items="${sessionScope.warehouseOperations}" varStatus="index">
                                    <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                        <td>${index.count}</td>
                                        <td>${warehouseOperation.quantityBrought}</td>
                                        <td>${warehouseOperation.produceTypeBrought}</td>
                                        <td>${warehouseOperation.sellingDate}</td>
                                        <td>${warehouseOperation.quantitySold}</td>
                                        <td>${warehouseOperation.produceTypeSold}</td>
                                        <td>${warehouseOperation.sellingPrice}</td>
                                        <td>${warehouseOperation.buyer}</td>
                                        <td><button onclick="editWarehouseOperation('${warehouseOperation.id}', '${warehouseOperation.quantityBrought}', '${warehouseOperation.produceTypeBrought}', '${warehouseOperation.sellingDate}', '${warehouseOperation.quantitySold}', '${warehouseOperation.produceTypeSold}', '${warehouseOperation.sellingPrice}', '${warehouseOperation.buyer}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deleteWarehouseOperation('${warehouseOperation.id}')"><span class="glyphicon glyphicon-trash"></span></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="equipment-form-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Equipment type
                        <input id="equipment-type" name="equipment-type" class="form-control">
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
                      <th> Quantity(bags) </th>
                                    <th> Produce type </th>
                                    <th> Selling date </th>
                                    <th> Quantity(bags) </th>
                                    <th> Produce type </th>
                                    <th> Selling price per bag </th>
                                    <th> Sold to who? </th>
                              
                                    <div class="form-group">
                        Quantity(bags)
                        <input id="warehouse-operation" class="form-control" required="true">
                    </div>
                              
                                    <div class="form-group">
                        Produce type
                        <input id="warehouse-operation" class="form-control" required="true">
                    </div>
                    <div class="form-group">
                        Selling date
                        <input type="date" id="farm-activity-date" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Quantity (bags)
                        <input type="number" step="0.01" id="quantity-harvested" class="form-control">
                    </div>
                    <div class="form-group">
                        Family consumption
                        <input type="number" step="0.01" id="family-consumption" class="form-control">
                    </div>
                    <div class="form-group">
                        Quantity sold/taken to warehouse
                        <input type="number" step="0.01" id="quantity-sold" class="form-control">
                    </div>
                    <div class="form-group">
                        Post-harvest loss
                        <input type="number"  id="post-harvest-loss" step="0.01" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        Average selling price
                        <input type="number" id="average-selling-price" step="0.01" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

