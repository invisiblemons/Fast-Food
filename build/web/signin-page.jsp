<%-- 
    Document   : signin-page
    Created on : Jan 13, 2021, 1:46:05 AM
    Author     : MONS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Hana Shop</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="css/mdb.min.css" rel="stylesheet">
        <!-- Your custom styles (optional) -->
        <link href="css/style.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <!-- font -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">

    </head>

    <body>

        <!-- Navbar -->
        <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
            <div class="container">

                <!-- Brand -->
                <a class="navbar-brand waves-effect" href="DispatcherServlet">
                    <strong class="blue-text">HANA</strong>
                </a>

                <!-- Collapse -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Links -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <!-- Left -->
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link waves-effect" href="DispatcherServlet?searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}">Home

                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link waves-effect" href="DispatcherServlet?btAction=loadItems&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}" >Shopping</a>
                        </li>

                    </ul>


                </div>

            </div>
        </nav>
        <!-- Navbar -->


        <!-- Main -->
        <div class="contactus">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8 offset-md-2">
                        <div class="title">
                            <h2>Sign In</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="about">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                        <div class="content">
                            <div class="container">
                                <div class="row">
                                    <div class="col contents">
                                        <div class="row justify-content-center">
                                            <div class="col-md-8">
                                                <div class="mb-4">
                                                </div>
                                                <form action="DispatcherServlet" method="post">
                                                    <div class="form-group first">
                                                        <label for="username">Username</label>
                                                        <input type="text" name="txtUsername" value="${param.txtUsername}" class="form-control" id="username">
                                                    </div>
                                                    <div class="form-group last mb-4">
                                                        <label for="password">Password</label>
                                                        <input type="password" name="txtPassword" class="form-control" id="password">

                                                    </div>
                                                    <h5 style="color:red;margin: 0;">${requestScope.INVALID}</h5>
                                                    <input type="submit" class="btn btn-primary" value="login" name="btAction" />

                                                    <span class="d-block text-left my-4 text-muted"> or sign in with</span>

                                                    <div class="social-login">
                                                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/HanaShop/DispatcherServlet?btAction=login-google&response_type=code
                                                           &client_id=1040468399169-4g9311k89t5q9r59kvf68j2hq08van7l.apps.googleusercontent.com&approval_prompt=force" class="google">
                                                            <i class="fab fa-google-plus-g fa-5x"></i>
                                                        </a>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- Main -->
        <!--Footer-->
        <footer class="page-footer text-center font-small mt-4 wow fadeIn">
            <hr class="my-4">
            <!--Copyright-->
            <div class="footer-copyright py-3">
                © 2019 Copyright:
                <a href="https://mdbootstrap.com/education/bootstrap/" target="_blank"> MDBootstrap.com </a>
            </div>
            <!--/.Copyright-->

        </footer>
        <!--/.Footer-->

        <!-- SCRIPTS -->
        <!-- JQuery -->
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Initializations -->
        <script type="text/javascript">
            // Animations initialization
            new WOW().init();

        </script>
    </body>

</html>
