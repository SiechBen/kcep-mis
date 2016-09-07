<%-- 
    Document   : region_addPerformanceIndicator
    Created on : Jul 3, 2016, 3:50:27 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add planning </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/addPerformanceIndicator.jsp"/>

    </jsp:attribute>
</kcep:region>