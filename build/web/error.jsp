<%-- 
    Document   : error
    Created on : Jan 15, 2021, 5:53:48 AM
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
                <a class="navbar-brand waves-effect" href="DispatcherServlet" >
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
                        <li class="nav-item active">
                            <a class="nav-link waves-effect" href="DispatcherServlet?btAction=loadItems&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}" >Shopping</a>
                        </li>

                    </ul>

                    <!-- Right -->
                    <ul class="navbar-nav nav-flex-icons">
                        <c:if test="${sessionScope.USER.isAdmin eq false}">
                            <li class="nav-item">
                                <a href="DispatcherServlet?btAction=viewCart&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}" class="nav-link waves-effect">
                                    <i class="fas fa-shopping-cart"></i>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${empty sessionScope.USER}">
                            <li class="nav-item">
                                <a href="DispatcherServlet?btAction=loginPage"
                                   class="nav-link border border-light rounded waves-effect" >
                                    <i class="fas fa-user    "></i> Sign In
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.USER}">
                            <c:set var="currentUserID" value="${sessionScope.USER.username}" />
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="dropdownId" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false"> <i class="fas fa-user    "></i>
                                    Welcome, ${sessionScope.USER.fullName}</a>

                                <div class="dropdown-menu" aria-labelledby="dropdownId">
                                    <c:if test="${sessionScope.USER.isAdmin eq false}">
                                        <a class="dropdown-item"
                                           href="DispatcherServlet?btAction=orderHistory">Order History</a>
                                        </c:if>
                                    <a class="dropdown-item" href="DispatcherServlet?btAction=logout">Sign Out</a>
                                </div>
                            </li>
                        </c:if>

                    </ul>

                </div>

            </div>
        </nav>
        <!-- Navbar -->
        <!--main-->
        <div class="error">
        <div class="container">
            <div class="row">
                <div class="col">
                    
        <h1 style="color:red"><strong>Error!!!</strong></h1>
                </div>
            </div>
        </div>
    </div>
        <!--main-->
        <!--Footer-->
        <footer class="page-footer text-center font-small mt-4 wow fadeIn">
            <!--Copyright-->
            <div class="footer-copyright py-3">
                2019 Copyright:
                <a href="https://mdbootstrap.com/education/bootstrap/" target="_blank"> Tks MDBootstrap.com !!! </a>
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
