<%--
    Document   : equity_financial_years
    Created on : Feb 9, 2017, 6:33:55 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/"%>

<kcep:equity>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view financial years </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/financial_years.jsp"/>

    </jsp:attribute>
</kcep:equity>