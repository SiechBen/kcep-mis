<%--
    Document   : indexx
    Created on : Oct 5, 2016, 2:09:55 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div id="wrapper">

            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-brand" href="home">KCEP-MIS ${sessionScope.userTitle}</a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
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

            <div class="main_container">
                <div class="header">
                    <a class="logo" href="#">--<span>--</span></a>
                </div>
                <div class="content">
                    <h1 class="login-link" onclick="loadAjaxWindow('loginpage')"><center>Welcome to KCEP-MIS</center></h1>
                </div>
                <div class="feedback">
                    <center>
                        <input type="text" class="field" id="feedback" name="feedback" onfocus="if (this.value === 'Leave a comment to KCEP officials')
                                    this.value = '';" onblur="if (this.value.trim() === '')
                                                this.value = 'Leave a comment to KCEP officials';" value="Leave a comment to KCEP officials" />
                        <input type="button" class="submit" onclick="saveFeedback()">
                    </center>
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
