<%-- Document : home-page Created on : Jan 13, 2021, 2:16:53 AM Author : MONS --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Links -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <!-- Left -->
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link waves-effect" href="DispatcherServlet?searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}">Home
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link waves-effect"
                               href="DispatcherServlet?btAction=loadItems&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}">Shopping</a>
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
                                   class="nav-link border border-light rounded waves-effect">
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


        <!--Carousel Wrapper-->
        <div id="carousel-example-1z" class="carousel slide carousel-fade pt-4" data-ride="carousel">

            <!--Indicators-->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-1z" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-1z" data-slide-to="1"></li>
                <li data-target="#carousel-example-1z" data-slide-to="2"></li>
            </ol>
            <!--/.Indicators-->

            <!--Slides-->
            <div class="carousel-inner" role="listbox">

                <!--First slide-->
                <div class="carousel-item active">
                    <div class="view" style="background-image: url('https://images.unsplash.com/photo-1606787366850-de6330128bfc?ixid=MXwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80'); background-repeat: no-repeat; background-size: cover;">

                        <!-- Mask & flexbox options-->
                        <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                            <!-- Content -->
                            <div class="text-center white-text mx-5 wow fadeIn">
                                <h1 class="mb-4">
                                    <strong>Food And Drink With Hana Shop</strong>
                                </h1>

                                <p>
                                    <strong>Best Choice For Your Meal !!!</strong>
                                </p>

                                <p class="mb-4 d-none d-md-block">

                                </p>

                                <a   href="DispatcherServlet?btAction=loadItems&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}"
                                     class="btn btn-outline-white btn-lg">Start
                                    Shopping
                                    <i class=" fas fa-shopping-basket ml-2 "></i> 
                                </a>
                            </div>
                            <!-- Content -->

                        </div>
                        <!-- Mask & flexbox options-->

                    </div>
                </div>
                <!--/First slide-->

                <!--Second slide-->
                <div class="carousel-item">
                    <div class="view" style="background-image: url('https://images.unsplash.com/photo-1495195134817-aeb325a55b65?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1055&q=80'); background-repeat: no-repeat; background-size: cover;">

                        <!-- Mask & flexbox options-->
                        <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                            <!-- Content -->
                            <div class="text-center white-text mx-5 wow fadeIn">
                                <h1 class="mb-4">
                                    <strong>Food And Drink With Hana Shop</strong>
                                </h1>

                                <p>
                                    <strong>Best Choice For Your Meal !!!</strong>
                                </p>

                                <p class="mb-4 d-none d-md-block">

                                </p>

                                <a   href="DispatcherServlet?btAction=loadItems&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}"
                                     class="btn btn-outline-white btn-lg">Start
                                    Shopping
                                    <i class=" fas fa-shopping-basket ml-2 "></i> 
                                </a>
                            </div>
                            <!-- Content -->

                        </div>
                        <!-- Mask & flexbox options-->

                    </div>
                </div>
                <!--/Second slide-->

                <!--Third slide-->
                <div class="carousel-item">
                    <div class="view" style="background-image: url('https://images.unsplash.com/photo-1505935428862-770b6f24f629?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1047&q=80'); background-repeat: no-repeat; background-size: cover;">

                        <!-- Mask & flexbox options-->
                        <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                            <!-- Content -->
                            <div class="text-center white-text mx-5 wow fadeIn">
                                <h1 class="mb-4">
                                    <strong>Food And Drink With Hana Shop</strong>
                                </h1>

                                <p>
                                    <strong>Best Choice For Your Meal !!!</strong>
                                </p>

                                <p class="mb-4 d-none d-md-block">

                                </p>

                                <a   href="DispatcherServlet?btAction=loadItems&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}"
                                     class="btn btn-outline-white btn-lg">Start
                                    Shopping
                                    <i class=" fas fa-shopping-basket ml-2 "></i> 
                                </a>
                            </div>
                            <!-- Content -->

                        </div>
                        <!-- Mask & flexbox options-->

                    </div>
                </div>
                <!--/Third slide-->

            </div>
            <!--/.Slides-->

            <!--Controls-->
            <a class="carousel-control-prev" href="#carousel-example-1z" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carousel-example-1z" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
            <!--/.Controls-->

        </div>
        <!--/.Carousel Wrapper-->

        <!-- trending -->
        <div class="trending">
            <div class="container-fluid">
                <div class="row titlte">
                    <div class="col-md-8 offset-md-2">
                        <div class="title">
                            <h2>Trending <strong class="blackk">Categories</strong></h2>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 margitop">
                        <div class="trending-box">
                            <figure><img src="https://images.pexels.com/photos/3434523/pexels-photo-3434523.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" /></figure>

                            <a href="DispatcherServlet?btAction=searchByCategory&categorySearchValue=Food" class="badge badge-light"><h3>Food</h3></a>

                        </div>
                    </div>
                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12">
                        <div class="trending-box">
                            <figure><img src="https://images.pexels.com/photos/605408/pexels-photo-605408.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" /></figure>

                            <a href="DispatcherServlet?btAction=searchByCategory&categorySearchValue=Drink" class="badge badge-light"><h3>Drink</h3></a>
                        </div>
                    </div>
                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 margitop">
                        <div class="trending-box">
                            <figure><img src="https://images.pexels.com/photos/2199190/pexels-photo-2199190.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" /></figure>

                            <a href="DispatcherServlet?btAction=loadItems&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}" class="badge badge-light"><h3>See More</h3></a>

                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- end trending -->

        <!--Footer-->
        <footer class="page-footer text-center font-small mt-4 wow fadeIn">
            <!--Copyright-->
            <div class="footer-copyright py-3">
                © 2019 Copyright:
                <a href="https://mdbootstrap.com/education/bootstrap/"> Tks
                    MDBootstrap.com !!! </a>
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