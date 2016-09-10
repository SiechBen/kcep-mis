<%-- 
    Document   : county_warehouses
    Created on : Jun 25, 2016, 11:36:17 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouses </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/warehouses.jsp" />

    </jsp:attribute>
</kcep:county>