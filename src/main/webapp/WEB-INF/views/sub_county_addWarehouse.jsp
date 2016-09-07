<%-- 
    Document   : sub_county_addWarehouse
    Created on : Jun 23, 2016, 7:47:53 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add warehouse</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/addWarehouse.jsp"/>

    </jsp:attribute>
</kcep:sub_county>