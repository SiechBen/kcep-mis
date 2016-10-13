<%--
    Document   : ward
    Created on : Jun 22, 2016, 8:52:24 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - ward officer </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/home.jsp"/>

    </jsp:attribute>
</kcep:ward>