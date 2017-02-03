<%--
    Document   : region_farmers
    Created on : Feb 3, 2017, 10:50:39 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view farmers</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/farmers.jsp"/>

    </jsp:attribute>
</kcep:region>