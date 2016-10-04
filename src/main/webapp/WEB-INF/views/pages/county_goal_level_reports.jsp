<%--
    Document   : county_goal_level_reports
    Created on : Oct 4, 2016, 9:50:47 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - goal level reports</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/goal_level_reports.jsp"/>

    </jsp:attribute>
</kcep:county>
