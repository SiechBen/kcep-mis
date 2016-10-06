<%--
    Document   : eVouchers
    Created on : Sep 6, 2016, 12:16:47 PM
    Author     : qortez
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of e-vouchers
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addEVoucher</label>
                    <table id="e-voucher-table" class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
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
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${eVoucher.dateRedeemed}"/></td>
                                    <td><a onclick="loadAjaxWindow('download?filePath=${eVoucher.inputsLogbookPage}')" target="_blank">${eVoucher.fileName}</a></td>
                                    <td><button onclick="editEVoucher('${eVoucher.id}', '${eVoucher.amount}', '${eVoucher.inputType.id}', '${eVoucher.person.id}', '<fmt:formatDate pattern="MM/dd/yyyy" value="${eVoucher.dateRedeemed}"/>')"><span class="glyphicon glyphicon-pencil"></span></button></td>
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