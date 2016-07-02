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
            <a onclick="loadAjaxWindow('activities')"><i class="fa fa-edit fa-fw"></i> Activities </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('planning')"><i class="fa fa-edit fa-fw"></i> Planning </a>
        </li>

        <li>
            <a onclick="loadAjaxWindow('procurements')"><i class="fa fa-edit fa-fw"></i> Procurements </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('procurement_plans')"><i class="fa fa-edit fa-fw"></i> Procurement plan </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('procurement_plans_cs')"><i class="fa fa-edit fa-fw"></i> Procurement plan - cs </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('eVouchers')"><i class="fa fa-edit fa-fw"></i> E-vouchers </a>
        </li>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">PCU Project Coordinator</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div>         
                <jsp:invoke fragment="pagecontent" />
            </div>
        </div>
    </jsp:attribute>
</kcep:genericpage>
