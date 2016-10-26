<%--
    Document   : county_financial_report_by_components
    Created on : Oct 17, 2016, 1:21:29 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - financial reports by components </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Financial report for this AWPB
                    </div>
                    <div class="panel-body">
                        <h4>Financial report by components</h4>
                        <table id="financial-report-by-component-table" class="table table-striped table-bordered table-hover reports-table">
                            <thead>
                                <tr>
                                    <th colspan="2">Description</th>
                                    <th colspan="18">Sources of Funds</th>
                                </tr>
                                <tr>
                                    <th rowspan="2" colspan="2">Component &nbsp;</th>
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
                            </thead>
                            <tfoot>
                                <tr><td colspan="18">Financial report by categories for this AWPB</td></tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="financialPlanTotals" items="${sessionScope.financialPlanByComponentMap.keySet()}">
                                    <c:forEach var="component" items="${sessionScope.financialPlanByComponentMap.get(financialPlanTotals).keySet()}" varStatus="index">
                                        <tr>
                                            <th>${index.count}</th>
                                            <th>${component.component}</th>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).gokPercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).gokValue}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).beneficiariesPercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).beneficiariesValue}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).ifadLoanPercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).ifadLoanValue}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).euPercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).euValue}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).ifadGrantPercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).ifadGrantValue}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).financialInstitutionPercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).financialInstitutionValue}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).totalsPercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).totalsValue}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).totalInitialAllocationPercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).totalInitialAllocationValue}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).balancePercentage}</td>
                                            <td>${financialPlanByComponentMap.get(financialPlanTotals).get(component).balanceValue}</td>
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

    </jsp:attribute>
</kcep:county>