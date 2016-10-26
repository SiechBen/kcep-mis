<%--
    Document   : region_financial_years
    Created on : Oct 26, 2016, 3:53:47 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view financial years </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/financial_years.jsp"/>

    </jsp:attribute>
</kcep:region>