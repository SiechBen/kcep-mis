<%--
    Document   : agmark_farm
    Created on : Oct 9, 2016, 5:39:55 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - farm </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/farm.jsp"/>

    </jsp:attribute>
</kcep:kalro>