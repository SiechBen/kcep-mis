<%--
    Document   : warehouse_count
    Created on : Nov 2, 2016, 10:06:25 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-body warehouse-scroll">
                <table id="warehouse-count-table" class="table table-bordered table-hover table-responsive">
                    <thead>
                        <tr>
                            <th> County: </th>
                            <td colspan="14">
                                <select id="counter" onchange="updateWarehouseCounts()">
                                    <option disabled selected>Select county</option>
                                    <c:forEach var="county" items="${sessionScope.counties}">
                                        <option value="${county.id}">${county.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="7"> <strong>Warehouses</strong> </td>
                            <td colspan="7"> <strong>Collection centres</strong> </td>
                            <td rowspan="2"> <strong>Total</strong> </td>
                        </tr>
                        <tr>
                            <td> <strong>Certified</strong> </td>
                            <td> <strong>Not certified</strong> </td>
                            <td> <strong>Offering wrs services</strong> </td>
                            <td> <strong>Not offering wrs services</strong> </td>
                            <td> <strong>Farmer owned</strong> </td>
                            <td> <strong>Privately owned</strong> </td>
                            <td> <strong>Total</strong> </td>
                            <td> <strong>Certified</strong> </td>
                            <td> <strong>Not certified</strong> </td>
                            <td> <strong>Offering wrs services</strong> </td>
                            <td> <strong>Not offering wrs services</strong> </td>
                            <td> <strong>Farmer owned</strong> </td>
                            <td> <strong>Privately owned</strong> </td>
                            <td> <strong>Total</strong> </td>
                        </tr>
                        <tr id="warehouse-summary">
                            <td> ${sessionScope.certifiedWarehouses} </td>
                            <td> ${sessionScope.unCertifiedWarehouses} </td>
                            <td> ${sessionScope.warehousesOfferingWrs} </td>
                            <td> ${sessionScope.warehousesNotOfferingWrs} </td>
                            <td> ${sessionScope.farmerOwnedWarehouses} </td>
                            <td> ${sessionScope.privatelyOwnedWarehouses} </td>
                            <td> ${sessionScope.totalWarehouses} </td>
                            <td> ${sessionScope.certifiedCollectionCentres} </td>
                            <td> ${sessionScope.unCertifiedCollectionCentres} </td>
                            <td> ${sessionScope.collectionCentresOfferingWrs} </td>
                            <td> ${sessionScope.collectionCentresNotOfferingWrs} </td>
                            <td> ${sessionScope.farmerOwnedCollectionCentres} </td>
                            <td> ${sessionScope.privatelyOwnedCollectionCentres} </td>
                            <td> ${sessionScope.totalCollectionCentres} </td>
                            <td> ${sessionScope.total} </td>
                        </tr>
                        <tr>
                            <td colspan="15" class="divider"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>