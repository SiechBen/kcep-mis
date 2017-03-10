<%--
    Document   : agmark_financial_report_by_categories
    Created on : Feb 9, 2017, 6:22:29 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - financial reports by categories</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/financial_report_by_categories.jsp"/>

    </jsp:attribute>
</kcep:agmark>