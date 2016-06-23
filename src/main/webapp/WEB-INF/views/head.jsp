<%-- 
    Document   : head
    Created on : Jun 22, 2016, 8:52:02 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:genericpage>
    <jsp:attribute name="title"> KCEP-MIS - view purchase </jsp:attribute>
    <jsp:attribute name="menuitems">
        <li>
            <a onclick="loadAjaxWindow('ward')"><i class="fa fa-table fa-fw"></i> WAO </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('kalro')"><i class="fa fa-table fa-fw"></i> KALRO </a>
        </li>       
        <li>
            <a onclick="loadAjaxWindow('people')"><i class="fa fa-edit fa-fw"></i> People </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('training')"><i class="fa fa-edit fa-fw"></i> Training </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('warehouses')"><i class="fa fa-edit fa-fw"></i> Warehouses </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('programmes')"><i class="fa fa-edit fa-fw"></i> Programmes </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('procurements')"><i class="fa fa-edit fa-fw"></i> Procurements </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('eVouchers')"><i class="fa fa-edit fa-fw"></i> E-vouchers </a>
        </li>
    </jsp:attribute>
    <jsp:attribute name="content">

    </jsp:attribute>
</kcep:genericpage>
