<%--
    Document   : indexx
    Created on : Feb 23, 2017, 5:53:37 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>easyWizard :: Easiest way to make wizard :: powered by jQuery</title>
        <link href="static/plugins/easy-wizard/demo/css/bootstrap.min.css" rel="stylesheet">
        <link href="static/plugins/easy-wizard/demo/css/jquery.snippet.min.css" rel="stylesheet">
        <link href="static/css/styles.css" rel="stylesheet">
    </head>
    <body data-spy="scroll" data-target=".navbar" data-offset="150">

        <div class="row" id="sample-dialog">
            <div class="col-lg-12">
                <div class="panel-default">
                    <div class="panel-body">
                        <div id="myWizard3">
                            <section class="step" data-step-title="page">
                                <div class="hero-unit">
                                    <h1>Hey !</h1>
                                    <p>Discover my awesome product</p>
                                    <p>
                                        <a class="btn btn-primary btn-large">
                                            Read more
                                        </a>
                                    </p>
                                </div>
                            </section>
                            <section class="step" data-step-title="page">
                                <div class="hero-unit">
                                    <h1>Here it is</h1>
                                    <p>Soon this product for you (and only you)</p>
                                </div>
                            </section>
                            <section class="step" data-step-title="page">
                                <div class="hero-unit">
                                    <h1>Awesome !</h1>
                                    <p>This product is now ready to use</p>
                                    <p>
                                        <a class="btn btn-primary btn-large">
                                            Get it !
                                        </a>
                                    </p>
                                </div>
                            </section>
                        </div>
                        <div id="myWizard3Pager" class="pagination pagination-centered">
                            <ul>
                                <li class="previous"><a href="#">&larr; Previous</a></li>
                                <li class="page"><a href="#" rel="1">1</a></li>
                                <li class="page"><a href="#" rel="2">2</a></li>
                                <li class="page"><a href="#" rel="3">3</a></li>
                                <li class="next"><a href="#">Next &rarr;</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="static/plugins/jquery/jquery.min.js"></script>
        <script src="static/plugins/easy-wizard/demo/js/bootstrap.min.js"></script>
        <script src="static/plugins/easy-wizard/demo/js/jquery.snippet.min.js"></script>
        <script src="static/plugins/easy-wizard/jquery.easyWizard.js"></script>
        <script src="static/plugins/easy-wizard/demo/js/scripts.js"></script>
    </body>
</html>