<%-- 
    Document   : head_equipment
    Created on : Jun 22, 2016, 3:33:46 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouse equipment </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/equipment.jsp"/>

    </jsp:attribute>
</kcep:head>