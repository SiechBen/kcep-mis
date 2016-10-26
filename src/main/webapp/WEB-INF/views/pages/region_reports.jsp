<%--
    Document   : region_reports
    Created on : Oct 26, 2016, 3:57:06 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - reports </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/reports.jsp"/>

    </jsp:attribute>
</kcep:region>