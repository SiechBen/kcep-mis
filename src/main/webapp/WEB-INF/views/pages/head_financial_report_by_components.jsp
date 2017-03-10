<%--
    Document   : head_financial_report_by_components
    Created on : Jul 31, 2016, 5:00:11 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - financial reports by components </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/financial_report_by_components.jsp"/>

    </jsp:attribute>
</kcep:head>