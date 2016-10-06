<%--
    Document   : head_farmers
    Created on : Oct 5, 2016, 11:31:15 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view beneficiaries</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/farmers.jsp"/>

    </jsp:attribute>
</kcep:head>
