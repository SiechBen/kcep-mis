<%--
    Document   : agmark_feedback
    Created on : Oct 9, 2016, 5:40:05 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - farmer feedback </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/feedback.jsp"/>

    </jsp:attribute>
</kcep:agmark>