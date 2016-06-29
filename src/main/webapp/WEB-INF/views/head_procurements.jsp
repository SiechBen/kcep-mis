<%-- 
    Document   : head_procurements
    Created on : Jun 22, 2016, 3:34:29 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view procurements </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Ward Extension Officer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            List of procurements
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover data-table">
                                    <thead>
                                        <tr>
                                            <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addProcurement')">Add</button></th>
                                            <th>Item</th>
                                            <th>Cost[KES]</th>
                                            <th>Date purchased</th>
                                            <th>Serial/plate number</th>
                                            <th>Description</th>
                                            <th>Target office</th>
                                            <th>County</th>
                                            <th>Sub-county</th>
                                            <th>LPO number</th>
                                            <th>Invoice or receipt</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <td colspan="8"> List of procurements</td>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach var="procurement" items="${sessionScope.procurements}" varStatus="index">
                                            <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                                <td>${index.count}</td>
                                                <td>${procurement.item}</td>
                                                <td>${procurement.cost}</td>
                                                <td>${procurement.datePurchased}</td>
                                                <td>${procurement.serialNumber}</td>
                                                <td>${procurement.description}</td>
                                                <td>${procurement.targetOffice}</td>
                                                <td>${procurement.county.name}</td>
                                                <td>${procurement.subCounty}</td>
                                                <td>${procurement.lpoNumber}</td>
                                                <td><a onclick="loadAjaxWindow('download?filePath=${procurement.invoiceOrReceipt}')" target="_blank">${procurement.fileName}</a></td>
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
</kcep:head>
