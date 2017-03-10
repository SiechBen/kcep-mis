<%--
    Document   : genericpage
    Created on : Jun 3, 2016, 3:19:52 PM
    Author     : siech
--%>

<%@tag description="This is the parent tag for java server pages used in kcep-mis" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<%-- The list of normal attributes:--%>
<%@attribute name="title" required="true" %>

<%--The list of links accessible by the user logged in:--%>
<%@attribute name="menuitems" required="true"%>

<%--The js scripts in to be used--%>
<%@attribute name="scripts" required="false" %>

<%@attribute name="styles" required="false" %>
<%-- The list of fragments:--%>
<%@attribute name="content" fragment="true"%>

<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <meta name="description" content="The tag file from which all jsps inherit from">
        <meta name="author" content="Ben Siech">
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

        <title> ${title} </title>

        <link href="static/plugins/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
        <link href="static/plugins/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
        <link href="static/plugins/datatables/media/css/jquery.dataTables.min.css">
        <link href="static/plugins/datatables/media/css/buttons.dataTables.min.css" rel="stylesheet">
        <link href="static/plugins/datatables/media/css/select.dataTables.min.css" rel="stylesheet">
        <link href="static/plugins/datatables/media/css/dataTables.bootstrap.css" rel="stylesheet">
        <link href="static/plugins/datatables-responsive/css/responsive.dataTables.scss">
        <link href="static/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
        <link href="static/plugins/jquery-ui/jquery-ui.css" rel="stylesheet">
        ${styles}
        <link href="static/css/timeline.css" rel="stylesheet">
        <link href="static/css/kcep-mis.css" rel="stylesheet">
        <link href="static/plugins/morrisjs/morris.css" rel="stylesheet">
        <link href="static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    </head>

    <body>

        <fmt:setBundle basename="text"/>
        <fmt:setLocale value="en"/>

        <div id="wrapper">

            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <strong><a class="navbar-brand" <c:if test="${sessionScope.userTitle}">href="home"</c:if>>KCEP-MIS <span id="user-title">: ${sessionScope.person.name} - ${sessionScope.userTitle}</span></a></strong>
                    </div>
                    <img class="center-logo" src="static/images/tri_logo_small.png">
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <a class="text-center" href="<fmt:message key='home_link'/>">
                            <i class="fa fa-home"></i>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <c:forEach var="feedback" items="${applicationScope.latestFeedbackList}">
                                <li>
                                    <a onclick="loadAjaxWindow('feedback')">
                                        <div>
                                            <strong>${feedback.submitter.name}</strong>
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
                                <a class="text-center" onclick="loadAjaxWindow('feedback')">
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
                            <li><a href="user_account"><i class="fa fa-gear fa-fw"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li><a onclick="loadAjaxWindow('loginpage')"><i class="fa fa-sign-in fa-fw"></i> Login </a>
                            <li><a onclick="loadAjaxWindow('logout')"><i class="fa fa-sign-out fa-fw"></i> Logout </a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse collapse">
                        <ul class="nav" id="side-menu">
                            <!--                            <li class="sidebar-search">
                                                            <div class="input-group custom-search-form">
                                                                <input type="text" class="form-control" placeholder="Search...">
                                                                <span class="input-group-btn">
                                                                    <button class="btn btn-default" type="button">
                                                                        <i class="fa fa-search"></i>
                                                                    </button>
                                                                </span>
                                                            </div>
                                                        </li>-->
                            <!--                            <li>
                                                            <a href="#" onclick="loadAjaxWindow('home')"><i class="fa fa-dashboard fa-fw"></i> Dashboard </a>
                                                        </li>-->
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

            <div class="loader"></div>

            <!--            <footer>
                            <div class="row">
                                <div class="col-lg-12">
                                    <p>Copyright &copy; Your Website 2014</p>
                                </div>
                            </div>
                        </footer>-->

        </div>

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
        <script src="static/plugins/isLoading/jquery.isloading.js"></script>
        <script src="static/plugins/jstorage/jstorage.js"></script>
        <script src="static/plugins/modernizr/modernizr.js"></script>
        <script src="static/plugins/visualization/d3js/d3.min.js"></script>
        ${scripts}
        <script src="static/js/actions.js"></script>

    </body>

</html>
