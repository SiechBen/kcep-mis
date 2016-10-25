<%--
    Document   : activity_planning_item
    Created on : Sep 14, 2016, 9:19:19 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<li>
    <a href="#"><i class="fa fa-calendar fa-fw"></i> Activity planning <span class="fa arrow"></span></a>
    <ul class="nav nav-second-level">
        <li>
            <a href="#" onclick="loadAjaxWindow('activity_names');
                            return false;"><i class="fa fa-edit fa-fw"></i> Activity names </a>
        </li>
        <li>
            <a href="#" onclick="loadAjaxWindow('financial_years');
                    return false;"><i class="fa fa-edit fa-fw"></i> Financial years </a>
        </li>
        <li>
            <a href="#" onclick="loadAjaxWindow('sub_activities');
                    return false;"><i class="fa fa-table fa-fw"></i> Annual Workplan Budget </a>
        </li>
    </ul>
</li>
