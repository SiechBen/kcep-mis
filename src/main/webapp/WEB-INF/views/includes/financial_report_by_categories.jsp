<%--
    Document   : financial_report_by_categories
    Created on : Oct 26, 2016, 4:01:51 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Financial report for this AWPB
            </div>
            <div class="panel-body">
                <h4>Financial report by categories</h4>
                <table id="financial-report-by-category-table" class="table table-striped table-bordered table-hover reports-table">
                    <thead>
                        <tr>
                            <th colspan="2">Description</th>
                            <th colspan="18">Sources of Funds</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr><td colspan="20">Financial report by categories for this AWPB</td></tr>
                    </tfoot>
                    <tbody>
                        <tr>
                            <th rowspan="2" colspan="2"> Exenditure category</th>
                            <th colspan="2">GOK</th>
                            <th colspan="2">Beneficiaries</th>
                            <th colspan="2">IFAD Loan</th>
                            <th colspan="2">EU</th>
                            <th colspan="2">IFAD Grant</th>
                            <th colspan="2">Financial Institution</th>
                            <th colspan="2">Total</th>
                            <th colspan="2">Total Initial Allocation</th>
                            <th colspan="2">Balance</th>
                        </tr>
                        <tr>
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
                            <th>%</th>
                            <th>USD '000</th>
                        </tr>
                        <c:forEach var="financialPlanTotals" items="${sessionScope.financialPlanByCategoryMap.keySet()}">
                            <c:forEach var="expenditureCategory" items="${sessionScope.financialPlanByCategoryMap.get(financialPlanTotals).keySet()}" varStatus="index">
                                <tr>
                                    <th>${index.count}</th>
                                    <th>${expenditureCategory.category.name}</th>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).gokPercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).gokValue}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).beneficiariesPercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).beneficiariesValue}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).ifadLoanPercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).ifadLoanValue}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).euPercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).euValue}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).ifadGrantPercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).ifadGrantValue}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).financialInstitutionPercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).financialInstitutionValue}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).totalsPercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).totalsValue}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).totalInitialAllocationPercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).totalInitialAllocationValue}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).balancePercentage}</td>
                                    <td>${financialPlanByCategoryMap.get(financialPlanTotals).get(expenditureCategory).balanceValue}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td>&nbsp;</td>
                                <td><strong>Total</strong></td>
                                <td>${financialPlanTotals.gokPercentage}</td>
                                <td>${financialPlanTotals.gokValue}</td>
                                <td>${financialPlanTotals.beneficiariesPercentage}</td>
                                <td>${financialPlanTotals.beneficiariesValue}</td>
                                <td>${financialPlanTotals.ifadLoanPercentage}</td>
                                <td>${financialPlanTotals.ifadLoanValue}</td>
                                <td>${financialPlanTotals.euPercentage}</td>
                                <td>${financialPlanTotals.euValue}</td>
                                <td>${financialPlanTotals.ifadGrantPercentage}</td>
                                <td>${financialPlanTotals.ifadGrantValue}</td>
                                <td>${financialPlanTotals.financialInstitutionPercentage}</td>
                                <td>${financialPlanTotals.financialInstitutionValue}</td>
                                <td>${financialPlanTotals.totalsPercentage}</td>
                                <td>${financialPlanTotals.totalsValue}</td>
                                <td>${financialPlanTotals.totalInitialAllocationPercentage}</td>
                                <td>${financialPlanTotals.totalInitialAllocationValue}</td>
                                <td>${financialPlanTotals.balancePercentage}</td>
                                <td>${financialPlanTotals.balanceValue}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
