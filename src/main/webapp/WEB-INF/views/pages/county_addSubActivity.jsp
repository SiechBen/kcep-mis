<%-- 
    Document   : county_addSubActivity
    Created on : Jul 15, 2016, 8:06:46 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add sub-activity </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addSubActivity.jsp"/>

    </jsp:attribute>
</kcep:county>