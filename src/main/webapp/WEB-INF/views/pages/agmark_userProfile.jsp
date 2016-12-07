<%--
    Document   : agmark_userProfile
    Created on : Oct 9, 2016, 5:40:57 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - user profile </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/userProfile.jsp"/>

    </jsp:attribute>
</kcep:agmark>