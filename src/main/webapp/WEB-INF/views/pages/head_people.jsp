<%--
    Document   : head_people
    Created on : Jun 22, 2016, 3:34:17 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view people </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/people.jsp" />

    </jsp:attribute>
</kcep:head>