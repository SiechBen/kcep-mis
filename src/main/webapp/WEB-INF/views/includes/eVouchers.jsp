<%--
    Document   : eVouchers
    Created on : Sep 6, 2016, 12:16:47 PM
    Author     : qortez
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                e-Voucher information
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
                                    <th>Serial number</th>
                                    <th>Total count</th>
                                    <th>Status</th>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <td colspan="7"> List of e-vouchers </td>
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

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of e-Vouchers
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table id="e-voucher-table" class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addEVoucher')">Add</button></th>
                                <th>Amount</th>
                                <th>Input type</th>
                                <th>Person</th>
                                <th>Date redeemed</th>
                                <th>Attachment i.e scanned pages/ photos from the inputs logging book</th>
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
                            <c:forEach var="eVoucher" items="${sessionScope.eVouchers}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${eVoucher.amount}</td>
                                    <td>${eVoucher.inputType.type}</td>
                                    <td>${eVoucher.person.name}</td>
                                    <td>${eVoucher.dateRedeemed}</td>
                                    <td><a onclick="loadAjaxWindow('download?filePath=${eVoucher.inputsLogbookPage}')" target="_blank">${eVoucher.fileName}</a></td>
                                    <td><button onclick="editEVoucher('${eVoucher.id}', '${eVoucher.amount}', '${eVoucher.inputType.id}', '${eVoucher.person.id}', '${eVoucher.dateRedeemed}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deletEVoucher(${eVoucher.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="evoucher-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Amount
                        <input id="e-voucher-amount" name="amount" class="form-control">
                    </div>
                    <div class="form-group">
                        Input type
                        <select id="e-voucher-input-type" name="input-type" class="form-control">
                            <c:forEach var="inputType" items="${sessionScope.inputTypes}" varStatus="index">
                                <option value="${inputType.id}">${inputType.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Person
                        <select id="e-voucher-person" name="person" class="form-control">
                            <c:forEach var="person" items="${sessionScope.eVoucherPeople}" varStatus="index">
                                <option value="${person.id}">${person.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Date redeemed
                        <input id="date-redeemed" name="date-redeemed" class="form-control datefield">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
