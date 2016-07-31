<%-- 
    Document   : head_financial_plan_by_components
    Created on : Jul 31, 2016, 5:00:11 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - financial reports by components </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Financial plan for this AWPB
                    </div>
                    <div class="panel-body">
                        <h4>Financial plan by components</h4>
                        <table id="financial-plan-by-component-table" class="table table-striped table-bordered table-hover reports-table">
                            <thead>
                                <tr>
                                    <th colspan="2">Description</th>
                                    <th colspan="16">Sources of Funds</th>
                                </tr>
                                <tr>
                                    <th rowspan="2" colspan="2">Component &nbsp;</th>
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
                                <c:forEach var="component" items="${sessionScope.financialPlanByComponentMap.keySet()}" varStatus="index">
                                    <tr>
                                        <th>${index.count}</th>
                                        <th>${component.component}</th>
                                        <td>${financialPlanByComponentMap.get(component).gokPercentage}</td>
                                        <td>${financialPlanByComponentMap.get(component).gokValue}</td>
                                        <td>${financialPlanByComponentMap.get(component).beneficiariesPercentage}</td>
                                        <td>${financialPlanByComponentMap.get(component).beneficiariesValue}</td>
                                        <td>${financialPlanByComponentMap.get(component).ifadLoanPercentage}</td>
                                        <td>${financialPlanByComponentMap.get(component).ifadLoanValue}</td>
                                        <td>${financialPlanByComponentMap.get(component).ifadGrantPercentage}</td>
                                        <td>${financialPlanByComponentMap.get(component).ifadGrantValue}</td>
                                        <td>${financialPlanByComponentMap.get(component).financialInstitutionPercentage}</td>
                                        <td>${financialPlanByComponentMap.get(component).financialInstitutionValue}</td>
                                        <td>${financialPlanByComponentMap.get(component).totalsPercentage}</td>
                                        <td>${financialPlanByComponentMap.get(component).totalsValue}</td>
                                        <td>${financialPlanByComponentMap.get(component).totalInitialAllocationPercentage}</td>
                                        <td>${financialPlanByComponentMap.get(component).totalInitialAllocationValue}</td>
                                        <td>${financialPlanByComponentMap.get(component).balancePercentage}</td>
                                        <td>${financialPlanByComponentMap.get(component).balanceValue}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>