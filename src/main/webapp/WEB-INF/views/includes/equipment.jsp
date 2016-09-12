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
                                    <th>Crop</th>
                                    <th>Date</th>
                                    <th>Quantity harvested</th>
                                    <th>Family consumption</th>
                                    <th>Quantity sold</th>
                                    <th>Post harvest loss</th>
                                    <th>Average selling price</th>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="farmActivity" items="${sessionScope.farmActivities}" varStatus="index">
                                    <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                        <td>${index.count}</td>
                                        <td>${farmActivity.name}</td>
                                        <td>${farmActivity.yield}</td>
                                        <td>${farmActivity.dateDone}</td>
                                        <td>${farmActivity.quantityHarvested}</td>
                                        <td>${farmActivity.familyConsumption}</td>
                                        <td>${farmActivity.quantitySold}</td>
                                        <td>${farmActivity.postHarvestLoss}</td>
                                        <td>${farmActivity.averageSellingPrice}</td>
                                        <td><button onclick="editFarmActivity('${farmActivity.id}', '${farmActivity.quantityHarvested}', '${farmActivity.familyConsumption}', '${farmActivity.quantitySold}', '${farmActivity.postHarvestLoss}', '${farmActivity.yield}', '${farmActivity.dateDone}', '${farmActivity.name}', '${farmActivity.averageSellingPrice}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deleteFarmActivity('${farmActivity.id}')"><span class="glyphicon glyphicon-trash"></span></button></td>
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
<div class="row dialog" id="account-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Account Number
                        <input class="form-control" id="account-number" >
                    </div>
                    <div class="form-group">
                        Ebl Branch
                        <select  id="ebl-branch" class="form-control"> 
                            <c:forEach var="eblBranch" items="${sessionScope.eblBranches}" varStatus="index">
                                <option value="${eblBranch.id}">${eblBranch.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sol id
                        <input class="form-control" id="sol-id" >
                    </div>
                    <div class="form-group">
                        Deposit(+) or withdrawal(-)
                        <input type="number" step="0.01" class="form-control" id="change">
                    </div>
                    Savings
                    <div class="form-group">
                        <input type="number" step="0.01" class="form-control" id="savings"  readonly>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

