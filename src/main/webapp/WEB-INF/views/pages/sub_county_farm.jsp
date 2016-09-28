<%-- 
    Document   : sub_county_farm
    Created on : Jul 22, 2016, 10:29:09 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - national officer </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/farm.jsp"/>

    </jsp:attribute>
</kcep:sub_county>