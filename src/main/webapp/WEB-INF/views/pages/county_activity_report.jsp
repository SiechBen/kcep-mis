<%--
    Document   : county_activity_report
    Created on : Nov 4, 2016, 8:16:10 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - activity progress report </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/activity_report.jsp"/>

    </jsp:attribute>
</kcep:county>