<%--
    Document   : region_outcome_level_reports
    Created on : Sep 29, 2016, 12:16:02 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - outcome level reports</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/outcome_level_reports.jsp"/>

    </jsp:attribute>
</kcep:region>