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
                                    <td><button onclick="doeditEquipment('${equipment.id}', '${equipment.type}', '${equipment.totalCount}', '${equipment.status}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="dodeleteEquipment(${equipment.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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