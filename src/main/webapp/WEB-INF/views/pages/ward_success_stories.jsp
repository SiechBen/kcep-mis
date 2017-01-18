<%--
    Document   : ward_success_stories
    Created on : Oct 12, 2016, 10:00:15 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view success stories </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/success_stories.jsp"/>

    </jsp:attribute>
</kcep:ward>