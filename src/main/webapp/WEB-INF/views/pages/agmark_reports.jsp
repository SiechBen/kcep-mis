<%--
    Document   : agmark_reports
    Created on : Feb 9, 2017, 6:21:52 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - reports </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/reports.jsp"/>

    </jsp:attribute>
</kcep:agmark>
