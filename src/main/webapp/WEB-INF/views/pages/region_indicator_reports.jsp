<%--
    Document   : region_indicator_reports
    Created on : Sep 28, 2016, 11:34:15 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - output level reports</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../../includes/indicator_reports.jsp"/>

    </jsp:attribute>
</kcep:region>