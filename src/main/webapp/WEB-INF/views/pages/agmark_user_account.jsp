<%--
    Document   : agmark_user_account
    Created on : Nov 30, 2016, 6:28:10 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - user account </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/user_account.jsp"/>

    </jsp:attribute>
</kcep:agmark>
