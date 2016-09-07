<%-- 
    Document   : head_userProfile
    Created on : Jun 22, 2016, 3:35:26 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - user profile </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/userProfile.jsp"/>

    </jsp:attribute>
</kcep:head>