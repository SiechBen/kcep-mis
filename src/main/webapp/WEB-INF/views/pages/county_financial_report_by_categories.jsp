<%--
    Document   : county_financial_report_by_categories
    Created on : Oct 17, 2016, 1:21:10 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - financial reports by categories</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/financial_report_by_categories.jsp"/>

    </jsp:attribute>
</kcep:county>