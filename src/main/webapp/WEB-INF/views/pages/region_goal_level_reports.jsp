<%--
    Document   : region_goal_level_reports
    Created on : Oct 4, 2016, 9:51:29 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - goal level reports</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/goal_level_reports.jsp"/>

    </jsp:attribute>
</kcep:region>
