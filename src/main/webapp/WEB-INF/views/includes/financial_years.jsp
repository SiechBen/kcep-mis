<%--
    Document   : financial_years
    Created on : Oct 26, 2016, 10:17:48 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of financial years
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addFinancialYear</label>
                    <table class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Financial year</th>
                                <th>Current year</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="3"> List of financial years </td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="financialYear" items="${sessionScope.financialYears}" varStatus="index">
                                <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                    <td>${index.count}</td>
                                    <td>${financialYear.financialYear}</td>
                                    <td><c:if test="${financialYear.currentYear}">Current year</c:if></td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>