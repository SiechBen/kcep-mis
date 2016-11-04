<%--
    Document   : head_procurements_item
    Created on : Sep 14, 2016, 9:13:43 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<li>
    <a href="#"><i class="fa fa-money fa-fw"></i> Procurements <span class="fa arrow"></span></a>
    <ul class="nav nav-second-level">

        <li>
            <a href="#" onclick="loadAjaxWindow('procurement_plans_goods');
                    return false;"><i class="fa fa-money fa-fw"></i> Procurement plans - goods </a>
        </li>
        <li>
            <a href="#" onclick="loadAjaxWindow('procurement_plans_ncs');
                    return false;"><i class="fa fa-money fa-fw"></i> Procurement plans - ncs </a>
        </li>
        <li>
            <a href="#" onclick="loadAjaxWindow('procurement_plans_cs');
                    return false;"><i class="fa fa-money fa-fw"></i> Procurement plans - cs </a>
        </li>
        <li>
            <a href="#" onclick="loadAjaxWindow('procurements');
                    return false;"><i class="fa fa-money fa-fw"></i> PCU Inventory </a>
        </li>
    </ul>
</li>
