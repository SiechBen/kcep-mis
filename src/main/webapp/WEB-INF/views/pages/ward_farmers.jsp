<%--
    Document   : ward_farmers
    Created on : Feb 3, 2017, 10:48:20 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view farmers</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/farmers.jsp"/>

    </jsp:attribute>
</kcep:ward>