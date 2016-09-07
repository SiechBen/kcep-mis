<%-- 
    Document   : head_sub_activities
    Created on : Jun 28, 2016, 9:04:59 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view sub-activities </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/sub_activities.jsp"/>

    </jsp:attribute>
</kcep:head>