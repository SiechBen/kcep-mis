<%--
    Document   : head_trainees
    Created on : Jun 29, 2016, 2:55:04 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view trainees </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/trainees.jsp"/>

    </jsp:attribute>
</kcep:head>