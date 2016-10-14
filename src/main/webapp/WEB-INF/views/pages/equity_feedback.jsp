<%--
    Document   : equity_feedback
    Created on : Oct 13, 2016, 12:49:13 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:equity>
    <jsp:attribute name="pagetitle"> KCEP-MIS - farmer feedback </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/feedback.jsp"/>

    </jsp:attribute>
</kcep:equity>