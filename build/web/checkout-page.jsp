<%-- 
    Document   : checkout-page
    Created on : Feb 13, 2021, 4:01:36 AM
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
        <div class="mainbody">
            <!--Main layout-->
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                        <!-- Shopping cart table -->
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="p-2 px-3 text-uppercase">Product</div>
                                        </th>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Price</div>
                                        </th>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Quantity</div>
                                        </th>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Edit</div>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="list" value="${sessionScope.CARTCUSTOMER.getCartProduct()}" />
                                    <c:set var="total" value="0"/>

                                    <c:if test="${not empty list}">
                                        <c:forEach var="dto" items="${list}" >

                                            <tr>
                                                <th scope="row" class="border-0">
                                                    <div class="p-2">

                                                        <img src="${dto.value.productImage}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                        <div class="ml-3 d-inline-block align-middle">
                                                            <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block align-middle">${dto.value.productName}</a></h5><span class="text-muted font-weight-normal font-italic d-block">${dto.value.category}</span>
                                                        </div>
                                                    </div>
                                                </th>

                                        <form action="DispatcherServlet"> 
                                            <td class="border-0 align-middle"><strong>${dto.value.productPrice} đ</strong></td>
                                            <c:set var="total" value="${dto.value.productPrice*dto.value.quancart+total}"/>
                                            <td class="border-0 align-middle"><strong> <input onkeydown="return false" type="number" min="0"  max="${dto.value.quantity}" name="quanCart" value="${dto.value.quancart}" /> </strong>
                                                <c:if test="${not empty requestScope.updateError}">
                                                    <strong style="color: red">Out of Stock</strong>
                                                </c:if>
                                            </td>

                                            <td class="border-0 align-middle"> 
                                                <input type="hidden" name="productID" value="${dto.value.productID}" />
                                                <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                                <input type="hidden" name="searchValueName" value="${requestScope.searchValueName}" />
                                                <input type="hidden" name="minPrice" value="${requestScope.minPrice}" />
                                                <input type="hidden" name="maxPrice" value="${requestScope.maxPrice}" />
                                                <input type="hidden" name="categorySearchValue" value="${requestScope.categorySearchValue}" />
                                                <button id="updateCart" type="submit" name="btAction" value="updateProductInCart" class="text-dark"><i class="fa fa-edit"></i></button> 
                                        </form>
                                        <form action="DispatcherServlet" onsubmit="return confirm('Are you sure to delete?')">
                                            <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                            <input type="hidden" name="searchValueName" value="${requestScope.searchValueName}" />
                                            <input type="hidden" name="minPrice" value="${requestScope.minPrice}" />
                                            <input type="hidden" name="maxPrice" value="${requestScope.maxPrice}" />
                                            <input type="hidden" name="categorySearchValue" value="${requestScope.categorySearchValue}" />
                                            <input type="hidden" name="productID" value="${dto.value.productID}" />
                                            <button type="submit" name="btAction" value="deleteProductInCart" class="text-dark"><i class="fa fa-trash"></i></button>
                                        </form></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>

                                </tbody>

                            </table>
                            <c:if test="${empty list}">
                                <strong>Cart is empty!!!</strong>
                            </c:if>
                        </div>
                        <!-- End -->
                    </div>
                </div>

                <div class="row py-5 p-4 bg-white rounded shadow-sm">
                    <div class="col-lg-12">
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order summary </div>
                        <div class="p-4">
                            <form action="DispatcherServlet">
                                <ul class="list-unstyled mb-4">
                                    <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                                        <input type="hidden" name="totalPrice" value="${total}" />
                                        <h5 class="font-weight-bold">${total} đ</h5>
                                    </li>
                                    <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Payment Method</strong>
                                        <input type="hidden" name="paymentMethod" value="Cash payment upon delivery" />
                                        <h5 class="font-weight-bold">Cash payment upon delivery</h5>
                                    </li>
                                </ul>
                                <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                <input type="hidden" name="searchValueName" value="${requestScope.searchValueName}" />
                                <input type="hidden" name="minPrice" value="${requestScope.minPrice}" />
                                <input type="hidden" name="maxPrice" value="${requestScope.maxPrice}" />
                                <input type="hidden" name="categorySearchValue" value="${requestScope.categorySearchValue}" />
                                <strong>${requestScope.SUCCESSCHECKOUT}</strong><button type="submit" name="btAction" value="checkout" class="btn btn-dark rounded-pill py-2 btn-block">Proceed to checkout</button>
                            </form>
                        </div>
                    </div>
                </div>

            </div>

            <!--Main layout-->
        </div>

        <!--Footer-->
        <footer class="page-footer text-center font-small mt-4 wow fadeIn">
            <!--Copyright-->
            <div class="footer-copyright py-3">
                © 2019 Copyright:
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
