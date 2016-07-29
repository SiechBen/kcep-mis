<%-- 
    Document   : head_reports
    Created on : Jul 19, 2016, 8:44:01 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - reports </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Financial plan by categories
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table id="financial-plan-table" class="table table-striped table-bordered table-hover reports-table">
                                <thead>
                                    <tr>
                                        <th colspan="2">Description</th>
                                        <th colspan="2">GOK</th>
                                        <th colspan="2">Beneficiaries</th>
                                        <th colspan="2">IFAD Loan</th>
                                        <th colspan="2">IFAD Grant</th>
                                        <th colspan="2">Financial Institution</th>
                                        <th colspan="2">Total</th>
                                        <th colspan="2">Total Initial Allocation</th>
                                        <th colspan="2">Balance</th>
                                    </tr>
                                    <tr>
                                        <th>&nbsp;</th>
                                        <th>Exenditure category</th>
                                        <th>%</th>
                                        <th>USD '000</th>
                                        <th>%</th>
                                        <th>USD '000</th>
                                        <th>%</th>
                                        <th>USD '000</th>
                                        <th>%</th>
                                        <th>USD '000</th>
                                        <th>%</th>
                                        <th>USD '000</th>
                                        <th>%</th>
                                        <th>USD '000</th>
                                        <th>%</th>
                                        <th>USD '000</th>
                                        <th>%</th>
                                        <th>USD '000</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>&nbsp;</th>
                                        <th><strong>Total</strong></th>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                        <td><strong></strong></td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="expenditureCategory" items="${sessionScope.financialPlanMap.keySet()}" varStatus="index">
                                        <tr>
                                            <th>${index.count}</th>
                                            <th>${expenditureCategory.name}</th>
                                            <td>${financialPlanMap.get(expenditureCategory).gokPercentage}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).gokValue}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).beneficiariesPercentage}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).beneficiariesValue}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).ifadLoanPercentage}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).ifadLoanValue}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).ifadGrantPercentage}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).ifadGrantValue}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).financialInstitutionPercentage}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).financialInstitutionValue}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).totalsPercentage}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).totalsValue}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).totalInitialAllocationPercentage}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).totalInitialAllocationValue}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).balancePercentage}</td>
                                            <td>${financialPlanMap.get(expenditureCategory).balanceValue}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>