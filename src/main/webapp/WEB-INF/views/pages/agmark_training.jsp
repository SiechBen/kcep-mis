<%--
    Document   : agmark_training
    Created on : Oct 9, 2016, 5:41:20 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view training </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/training.jsp"/>

    </jsp:attribute>
</kcep:agmark>