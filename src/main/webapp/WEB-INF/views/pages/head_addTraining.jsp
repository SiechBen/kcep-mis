<%-- 
    Document   : head_addTraining
    Created on : Jun 22, 2016, 3:32:39 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add training </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addTraining.jsp"/>

    </jsp:attribute>
</kcep:head>