<%--
    Document   : county_outcome_level_reports
    Created on : Sep 29, 2016, 12:15:12 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - outcome level reports</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/outcome_level_reports.jsp"/>

    </jsp:attribute>
</kcep:county>