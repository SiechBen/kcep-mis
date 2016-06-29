<%-- 
    Document   : agro_dealer_eVouchers
    Created on : Jun 22, 2016, 4:40:26 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agro_dealer>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view e-vouchers </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">KCEP agro-dealer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            List of e-Vouchers
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover data-table">
                                    <thead>
                                        <tr>
                                            <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addEVoucher')">Add</button></th>
                                            <th>Amount</th>
                                            <th>Input type</th>
                                            <th>Person</th>
                                            <th>Date redeemed</th>
                                            <th>Photo/scanned logbook page</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <td colspan="8"> List of e-vouchers </td>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach var="eVoucher" items="${sessionScope.eVouchers}" varStatus="index">
                                            <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                                <td>${index.count}</td>
                                                <td>${eVoucher.amount}</td>
                                                <td>${eVoucher.inputType.type}</td>
                                                <td>${eVoucher.person.name}</td>
                                                <td>${eVoucher.dateRedeemed}</td>
                                                <td><a onclick="loadAjaxWindow('download?filePath=${eVoucher.inputsLogbookPage}')" target="_blank">${eVoucher.fileName}</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

        </div>
        <!-- /#page-wrapper -->

    </jsp:attribute>
</kcep:agro_dealer>