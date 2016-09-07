<%-- 
    Document   : sub_county_warehouses
    Created on : Jun 23, 2016, 7:48:08 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouses </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/warehouse.jsp"/>

    </jsp:attribute>
</kcep:sub_county>