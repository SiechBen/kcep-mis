<%-- 
    Document   : region_performance_indicators
    Created on : Jul 3, 2016, 3:50:15 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - key performance indicators </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/performance_indicators.jsp"/>

    </jsp:attribute>
</kcep:region>