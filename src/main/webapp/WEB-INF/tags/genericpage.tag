<%--
    Document   : genericpage
    Created on : Jun 3, 2016, 3:19:52 PM
    Author     : siech
--%>

<%@tag description="This is the parent tag for java server pages used in kcep-mis" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<%-- The list of normal attributes:--%>
<%@attribute name="title" required="true" %>

<%--The list of links accessible by the user logged in:--%>
<%@attribute name="menuitems" required="true"%>

<%--The js scripts in to be used--%>
<%@attribute name="scripts" required="false" %>

<%-- The list of fragments:--%>
<%@attribute name="content" fragment="true"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title> ${title} </title>

        <link href="static/plugins/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
        <link href="static/plugins/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
        <link href="static/plugins/datatables/media/css/jquery.dataTables.min.css">
        <link href="static/plugins/datatables/media/css/buttons.dataTables.min.css" rel="stylesheet">
        <link href="static/plugins/datatables/media/css/select.dataTables.min.css" rel="stylesheet">
        <link href="static/plugins/datatables/media/css/dataTables.bootstrap.css" rel="stylesheet">
        <link href="static/plugins/datatables-responsive/css/responsive.dataTables.scss" rel="stylesheet">
        <link href="static/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
        <link href="static/plugins/jquery-ui/jquery-ui.css" rel="stylesheet">
        <link href="static/css/timeline.css" rel="stylesheet">
        <link href="static/css/kcep-mis.css" rel="stylesheet">
        <link href="static/plugins/morrisjs/morris.css" rel="stylesheet">
        <link href="static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    </head>

    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">KCEP-MIS V1.0</a>
                </div>
                <!-- /.navbar-header -->

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
                        <!-- /.dropdown-messages -->
                    </li>
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-tasks">
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 1</strong>
                                            <span class="pull-right text-muted">40% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                                <span class="sr-only">40% Complete (success)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 2</strong>
                                            <span class="pull-right text-muted">20% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 3</strong>
                                            <span class="pull-right text-muted">60% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                                <span class="sr-only">60% Complete (warning)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 4</strong>
                                            <span class="pull-right text-muted">80% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                                <span class="sr-only">80% Complete (danger)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Tasks</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                        <!-- /.dropdown-tasks -->
                    </li>
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-comment fa-fw"></i> New Comment
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                        <span class="pull-right text-muted small">12 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> Message Sent
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-tasks fa-fw"></i> New Task
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Alerts</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                        <!-- /.dropdown-alerts -->
                    </li>
                    <!-- /.dropdown -->
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
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li class="sidebar-search">
                                <div class="input-group custom-search-form">
                                    <input type="text" class="form-control" placeholder="Search...">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- /input-group -->
                            </li>
                            <li>
                                <a href="#" onclick="loadAjaxWindow('home')"><i class="fa fa-dashboard fa-fw"></i> Dashboard </a>
                            </li>
                            ${menuitems}

                        </ul>
                    </div>
                </div>
            </nav>

            <div>
                <jsp:invoke fragment="content" />
            </div>
            <div class="dialog" id="message-dialog">
                <p id="message"></p>
            </div>

        </div>

        <script src="static/plugins/jquery/jquery.min.js"></script>
        <script src="static/plugins/jquery/jquery.js"></script>
        <script src="static/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="static/plugins/i18n/jquery.i18n.properties.min.js"></script>
        <script src="static/plugins/metisMenu/dist/metisMenu.min.js"></script>
        <script src="static/plugins/datatables/media/js/jquery.dataTables.js"></script>
        <script src="static/plugins/datatables/media/js/dataTables.buttons.min.js"></script>
        <script src="static/plugins/datatables/media/js/dataTables.select.min.js"></script>
        <script src="static/plugins/datatables/media/js/dataTables.editor.js"></script>
        <script src="static/plugins/datatables/media/js/buttons.flash.min.js"></script>
        <script src="static/plugins/jszip/jszip.min.js"></script>
        <script src="static/plugins/pdfmake/pdfmake.min.js"></script>
        <script src="static/plugins/vfs-fonts/vfs_fonts.js"></script>
        <script src="static/plugins/datatables/media/js/buttons.html5.min.js"></script>
        <script src="static/plugins/datatables/media/js/buttons.print.min.js"></script>
        <script src="static/plugins/datatables/media/js/buttons.colVis.min.js"></script>
        <script src="static/plugins/datatables/media/js/dataTables.bootstrap.min.js"></script>
        <script src="static/plugins/jquery-ui/jquery-ui.js"></script>
        <script src="static/plugins/jquery-form/jquery.form.js"></script>
        <script src="static/plugins/jquery-awesome-cursor/js/jquery.awesome-cursor.min.js"></script>
        <script src="static/plugins/jstorage/jstorage.js"></script>
        ${scripts}
        <script src="static/js/actions.js"></script>

    </body>

</html>
