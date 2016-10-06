<%--
    Document   : e_vouchers_item
    Created on : Sep 14, 2016, 9:16:16 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<li>
    <a href="#"><i class="fa fa-file-text fa-fw"></i> E-voucher information <span class="fa arrow"></span></a>
    <ul class="nav nav-second-level">
        <li>
            <a href="#" onclick="loadAjaxWindow('eVouchers');
                    return false;"><i class="fa fa-file-text fa-fw"></i> E-vouchers </a>
        </li>
        <li>
            <a href="#" onclick="loadAjaxWindow('farmers');
                    return false;"><i class="fa fa-male fa-fw"></i> Beneficiaries </a>
        </li>
        <li>
            <a href="#" onclick="loadAjaxWindow('agroDealers');
                    return false;"><i class="fa fa-male fa-fw"></i> Agro-dealers </a>
        </li>
    </ul>
</li>