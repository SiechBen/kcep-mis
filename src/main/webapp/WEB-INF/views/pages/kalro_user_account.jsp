<%--
    Document   : kalro_user_account
    Created on : Nov 30, 2016, 6:29:57 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - user account </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/user_account.jsp"/>

    </jsp:attribute>
</kcep:kalro>
