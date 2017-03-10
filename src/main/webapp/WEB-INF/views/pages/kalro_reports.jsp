<%--
    Document   : kalro_reports
    Created on : Feb 9, 2017, 5:34:11 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - reports </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/reports.jsp"/>

    </jsp:attribute>
</kcep:kalro>