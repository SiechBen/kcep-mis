<%-- 
    Document   : head
    Created on : Jun 22, 2016, 3:36:37 PM
    Author     : siech
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<%-- The list of fragments:--%>
<%@attribute name="pagecontent" fragment="true"%>
<%@attribute name="pagetitle" required="true" %>

<kcep:genericpage>
    <jsp:attribute name="title"> ${pagetitle} </jsp:attribute>
    <jsp:attribute name="menuitems">
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
            <a onclick="loadAjaxWindow('activity_names')"><i class="fa fa-edit fa-fw"></i> Activity names </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('financial_years')"><i class="fa fa-edit fa-fw"></i> Financial years </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('sub_activities')"><i class="fa fa-edit fa-fw"></i> Activity planning </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('procurements')"><i class="fa fa-edit fa-fw"></i> Procurements </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('procurement_plans')"><i class="fa fa-edit fa-fw"></i> Procurement plans - ncs </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('procurement_plans_cs')"><i class="fa fa-edit fa-fw"></i> Procurement plans - cs </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('performance_indicators')"><i class="fa fa-edit fa-fw"></i> Performance indicators </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('eVouchers')"><i class="fa fa-edit fa-fw"></i> E-vouchers </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('reports')"><i class="fa fa-edit fa-fw"></i> Reports </a>
        </li>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">PCU Project Coordinator</h1>
                </div>
            </div>
            <div>         
                <jsp:invoke fragment="pagecontent" />
            </div>
        </div>
    </jsp:attribute>
</kcep:genericpage>
