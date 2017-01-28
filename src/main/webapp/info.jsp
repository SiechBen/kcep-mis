<%--
    Document   : info
    Created on : Jan 24, 2017, 11:04:21 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>KCEP-MIS - welcome</title>

        <link href="static/plugins/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
        <link href="static/plugins/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
        <link href="static/plugins/datatables/media/css/dataTables.bootstrap.css" rel="stylesheet">
        <link href="static/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
        <link href="static/plugins/jquery-ui/jquery-ui.css" rel="stylesheet">
        <link href="static/css/timeline.css" rel="stylesheet">
        <link href="static/css/kcep-mis.css" rel="stylesheet">
        <link href="static/plugins/morrisjs/morris.css" rel="stylesheet">
        <link href="static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="static/css/index.css" rel="stylesheet"/>

    </head>
    <body>

    <fmt:setBundle basename="text"/>
    <fmt:setLocale value="en"/>

    <div id="wrapper">

        <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-brand" onclick="loadPreviousWindow()">KCEP-MIS</a>
            </div>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <a class="text-center" href="<fmt:message key='home_link'/>">
                        <i class="fa fa-home"></i>
                    </a>
                </li>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-messages">
                    <c:forEach var="feedback" items="${applicationScope.latestFeedbackList}">
                        <li>
                            <a onclick="loadAjaxWindow('feedback')">
                                <div>
                                    <strong>${feedback.farmer.name}</strong>
                                    <span class="pill-right text-muted">
                                        <em>${feedback.timePosted}</em>
                                    </span>
                                </div>
                                <div>${feedback.shortMessage}</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                    </c:forEach>
                    <li>
                        <a class="text-center" onclick="loadAjaxWindow('feedback'')">
                            <strong>Read All Messages</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="userProfile"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a onclick="loadAjaxWindow('loginpage')"><i class="fa fa-sign-in fa-fw"></i> Login </a>
                        <li><a onclick="loadAjaxWindow('logout')"><i class="fa fa-sign-out fa-fw"></i> Logout </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>

        <div class="info-container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Kenya Cereal Enhancement Programme - Management Information System (KCEP-MIS)
                        </div>
                        <div class="panel-body">
                            <div>
                                <table class="table table-striped table-responsive">
                                    <tr>
                                        <th>Programme Title</th>
                                        <td>Kenya Cereal Enhancement Programme</td>
                                    </tr>
                                    <tr>
                                        <th>Executing Agency</th>
                                        <td>Ministry of Agriculture, Livestock & Fisheries</td>
                                    </tr>
                                    <tr>
                                        <th>Programme Objectives:</th>
                                        <td>The overall development objectives of the KCEP are to<br>
                                            <ol>
                                                <li class="indented">
                                                    contribute to national food security by increasing production of cereal staples (maize, sorghum, millet and associated <br>
                                                    pulses), and increase income of smallholders in medium and high potential production areas of targeted crops
                                                </li>
                                                <li class="indented">
                                                    support smallholder farmers in graduating from subsistence to commercial agriculture.
                                                </li>
                                            </ol>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Programme Components</th>
                                        <td>
                                            <ul>
                                                <li class="indented">
                                                    Component 1 – Cereal Productivity Enhancement
                                                </li>
                                                <li class="indented">
                                                    Component 2 - Post-Harvest Management and Market Linkages
                                                </li>
                                                <li class="indented">
                                                    Component 3 – Financial Inclusion
                                                </li>
                                            </ul>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Duration</th>
                                        <td>
                                            4 years
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Key Implementing Partners</th>
                                        <td>
                                            <p>
                                                The County government through County Agriculture offices will facilitate Programme implementation at the <br>
                                                County and Sub County levels.
                                            </p>
                                            <p>
                                                The various value chain actors (agrodealers, millers etc) are tasked with the role of service delivery to <br>
                                                primary Programme beneficiaries.<br>
                                            </p>
                                            <p>
                                                Farmers are central stakeholders in KCEP implementation. The programme’s strategy and activities are <br>
                                                geared towards ensuring that, by the end of the programme, they have graduated from subsistence farming to <br>
                                                commercial agriculture. The strengthening of Farmer Organisations capacities will be one of the main <br>
                                                responsibilities of KCEP partners and service providers.
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Programme Targeting</th>
                                        <td>
                                            CoutniesBungoma, Kakamega, Nakuru, Nandi and Trans Nzoia, Embu, Kitui and Tharaka Nithi
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Beneficiaries</th>
                                        <td>
                                            The KCEP targets 100,000 smallholder farmers whose livelihoods revolve around maize, sorghum, millet and other pulses. <br>
                                            The targeted smallholders include:

                                            <ol>
                                                <li class="indented">
                                                    i). 40,000 subsistence farmers who will be supported to graduate to commercial agriculture of which 20,000 are maize, <br>
                                                    13,200 sorghum and 6,800 millet producers
                                                </li>
                                                <li class="indented">
                                                    ii). 60,000 farmers who are already engaged in farming as a business and apply appropriate agricultural inputs and<br>
                                                    improved agricultural services
                                                </li>
                                            </ol>

                                            <p>
                                                A secondary target group will be stakeholders in the three value chains who will facilitate smallholders‟ access to enhanced <br>
                                                agricultural services, namely agro-dealers, private extension services, buyers, processors, and leading farmers providing <br>
                                                support services to smallholders.
                                            </p>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="fixed-bottom">
                <button class="btn btn-primary btn-default fa fa-arrow-left" onclick="loadPreviousWindow()"> Back</button>
            </div>
        </div>
        <div class="dialog" id="message-dialog">
            <p id="message"></p>
        </div>

    </div>

    <script src="static/plugins/index/cufon-yui.js"></script>
    <script src="static/plugins/index/Bebas_400.font.js"></script>
    <script src="static/plugins/index/Bell_Gothic_Std_300.font.js"></script>
    <script src="static/plugins/jquery/jquery.js"></script>
    <script src="static/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="static/plugins/i18n/jquery.i18n.properties.min.js"></script>
    <script src="static/plugins/metisMenu/dist/metisMenu.min.js"></script>
    <script src="static/plugins/vfs-fonts/vfs_fonts.js"></script>
    <script src="static/plugins/jquery-ui/jquery-ui.js"></script>
    <script src="static/plugins/jquery-form/jquery.form.js"></script>
    <script src="static/plugins/jquery-awesome-cursor/js/jquery.awesome-cursor.min.js"></script>
    <script src="static/js/index.js"></script>
</body>
</html>
