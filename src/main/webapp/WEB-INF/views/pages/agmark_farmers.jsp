<%--
    Document   : agmark_farmers
    Created on : Oct 19, 2016, 8:29:56 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view farmers</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/partner_farmers.jsp"/>

    </jsp:attribute>
</kcep:agmark>