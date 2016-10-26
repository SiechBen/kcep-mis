<%--
    Document   : head_financial_report_by_categories
    Created on : Jul 19, 2016, 8:44:01 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - financial reports by categories</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/financial_report_by_categories.jsp"/>

    </jsp:attribute>
</kcep:head>