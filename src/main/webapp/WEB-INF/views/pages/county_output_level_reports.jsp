<%--
    Document   : county_output_level_reports
    Created on : Sep 28, 2016, 11:33:59 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - output level reports</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../../includes/output_level_reports.jsp"/>

    </jsp:attribute>
</kcep:county>