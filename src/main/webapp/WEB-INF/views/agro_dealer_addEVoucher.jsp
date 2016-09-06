<%-- 
    Document   : agro_dealer_addEVoucher
    Created on : Jun 22, 2016, 4:35:41 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agro_dealer>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add e-voucher </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/addEVoucher.jsp"/>

    </jsp:attribute>
</kcep:agro_dealer>
