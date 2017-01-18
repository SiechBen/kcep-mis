<%--
    Document   : agmark_documents
    Created on : Jan 17, 2017, 3:43:00 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view KCEP common documents </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/documents.jsp"/>

    </jsp:attribute>
</kcep:agmark>