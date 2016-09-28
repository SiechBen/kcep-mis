<%-- 
    Document   : farmer_userProfile
    Created on : Jun 27, 2016, 7:07:44 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:farmer>
    <jsp:attribute name="pagetitle"> KCEP-MIS - user profile </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/userProfile.jsp"/>

    </jsp:attribute>
</kcep:farmer>