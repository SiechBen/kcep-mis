<%--
    Document   : equity_financial_report_by_components
    Created on : Feb 9, 2017, 6:34:53 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:equity>
    <jsp:attribute name="pagetitle"> KCEP-MIS - financial reports by components </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/financial_report_by_components.jsp"/>

    </jsp:attribute>
</kcep:equity>