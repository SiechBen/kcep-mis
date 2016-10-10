<%--
    Document   : agmark_trainees
    Created on : Oct 9, 2016, 5:40:42 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view trainees </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/trainees.jsp"/>

    </jsp:attribute>
</kcep:agmark>