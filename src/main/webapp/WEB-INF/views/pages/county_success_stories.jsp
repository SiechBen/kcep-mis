<%--
    Document   : county_success_stories
    Created on : Oct 12, 2016, 9:59:01 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view success stories </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/success_stories.jsp"/>

    </jsp:attribute>
</kcep:county>