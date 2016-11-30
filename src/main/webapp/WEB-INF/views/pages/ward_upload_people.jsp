<%--
    Document   : ward_upload_people
    Created on : Nov 25, 2016, 3:51:49 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - upload people </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/uploadPeople.jsp" />

    </jsp:attribute>
</kcep:ward>