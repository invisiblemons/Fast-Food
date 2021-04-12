<%-- Document : shopping-page Created on : Jan 13, 2021, 3:21:28 AM Author : MONS --%>
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
                        <li class="nav-item">
                            <a class="nav-link waves-effect" href="DispatcherServlet?searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}">Home

                            </a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link waves-effect"
                               href="DispatcherServlet?btAction=loadItems&searchValueName=${requestScope.searchValueName}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&categorySearchValue=${requestScope.categorySearchValue}&ACTION=${requestScope.ACTION}">Shopping</a>
                        </li>

                    </ul>

                    <!-- middle -->
                    <form class="example" action="DispatcherServlet">
                        <input type="text" placeholder="Search Product By Name" name="searchValueName"
                               value="${param.searchValueName}" />
                        <button name="btAction" type="submit" value="searchByName">
                            <i class="fas fa-search"></i>
                        </button>
                    </form>

                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
                            data-target="#modelId">Advance</button>

                    <!-- Modal -->
                    <div class="modal fade" id="modelId" tabindex="-1" role="dialog"
                         aria-labelledby="modelTitleId" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Search Advance</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p><strong>Search By Price</strong></p>
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-sm-2"></div>
                                            <div class="col">
                                                <form class="examplePrice" action="DispatcherServlet">
                                                    <input type="number" class="eP1" min="0" step="0.01" name="minPrice"
                                                           value="${param.minPrice}" maxlength="12" size="12"
                                                           placeholder="Min Price" required/>
                                                    <span>-</span>
                                                    <input type="number" class="eP2" min="0" step="0.01" name="maxPrice"
                                                           value="${param.maxPrice}" maxlength="12" size="12"
                                                           placeholder="Max Price" required/>
                                                    <button name="btAction" type="submit" value="searchByPrice">
                                                        <i class="fas fa-search"></i>
                                                    </button>
                                                </form>
                                            </div>
                                            <div class="col-sm-2"></div>
                                        </div>
                                    </div>
                                    <p><strong>Search By Category</strong></p>
                                    <div class="container">
                                        <div class="row">
                                            <div class="col">
                                                <div class="dropdown show">
                                                    <a class="btn btn-secondary dropdown-toggle" href="#" role="button"
                                                       id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                                                       aria-expanded="false">
                                                        Categories
                                                    </a>
                                                    <c:set var="categoryList" value="${sessionScope.CATEGORYLIST}"></c:set>
                                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                                        <c:forEach var="category" items="${categoryList}">
                                                            <a class="dropdown-item" href="DispatcherServlet?btAction=searchByCategory&categorySearchValue=${category}">${category}</a>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

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
        <div class="contactus">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8 offset-md-2">
                        <div class="title">
                            <h2>Our Product</h2>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--show edit result-->

        <c:if test="${not empty param.productNew}">
            <h4>Create new ${param.productNew} Success!</h4>
        </c:if>

        <c:if test="${not empty requestScope.ERROR}">
            <h3 class="error" style="color: red">
                ${requestScope.ERROR}
            </h3>
        </c:if>



        <c:set var="listItems" value="${sessionScope.ITEMSLIST}" />
        <c:set var="bestList" value="${sessionScope.BESTLIST}" />
        <!--User Form Product-->
        <c:if test="${sessionScope.USER.isAdmin eq false || empty sessionScope.USER}">
            <c:if test="${not empty bestList}">
                <div class="container bestSell">
                    <div class="row">
                        <c:forEach var="dto" items="${bestList}" varStatus="counter">
                            <!-- product -->
                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 list-groupp">
                                <div class=" list-group-itemm">

                                    <div class="full product">
                                        <form action="DispatcherServlet">
                                            <div class="product_img">
                                                <div class="center"> 
                                                    <img src="${dto.productImage}" alt="#" />
                                                    <input type="hidden" name="image" value="${dto.productImage}" />
                                                    <c:if test="${not empty sessionScope.USER}">
                                                        <div class="overlay_hover"> <button type="submit" name="btAction" value="addProductInCart" class="add-bt" >+ Add to cart</button> </div>

                                                    </c:if>

                                                    <c:if test="${empty sessionScope.USER}">
                                                        <div class="overlay_hover"> <button type="submit" name="btAction" value="loginPage" class="add-bt" >+ Add to cart</button> </div>

                                                    </c:if>
                                                </div>
                                            </div>

                                            <!-- parameter -->
                                            <input type="hidden" name="userDelete" value="${currentUserID}" />
                                            <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                            <input type="hidden" name="searchValueName" value="${param.searchValueName}" />
                                            <input type="hidden" name="minPrice" value="${param.minPrice}" />
                                            <input type="hidden" name="maxPrice" value="${param.maxPrice}" />
                                            <input type="hidden" name="categorySearchValue" value="${param.categorySearchValue}" />


                                            <div class="product_detail text_align_center">
                                                <p class="product_price">${dto.productPrice} đ      <strong style="color: red"> Best Seller!!</strong></p>
                                                <input type="hidden" name="productPrice" value="${dto.productPrice}" />
                                                <p class="product_name"><strong>Name:    </strong>${dto.productName}</p>
                                                <input type="hidden" name="productName" value="${dto.productName}" />
                                                <p class="product_category"><strong>Category:    </strong>${dto.category}</p>
                                                <input type="hidden" name="productCategory" value="${dto.category}" />
                                                <input type="hidden" name="productID" value="${dto.productID}" />
                                                <input type="hidden" name="quantity" value="${dto.quantity}" />
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                            <!-- end product -->
                        </c:forEach>
                    </div>
                </div>

            </c:if>

            <!-- Paging -->
            <nav aria-label="Page navigation">
                <ul class="pagination">


                </ul>
            </nav>

            <!-- end paging -->

            <c:if test="${not empty listItems}">
                <div id="loopp">
                    <div class="container">
                        <div class="row">

                            <c:forEach var="dto" items="${listItems}" varStatus="counter">
                                <!-- product -->
                                <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 list-groupp">
                                    <div class=" list-group-itemm">

                                        <div class="full product">
                                            <form action="DispatcherServlet">
                                                <div class="product_img">
                                                    <div class="center"> 
                                                        <img src="${dto.productImage}" alt="#" />
                                                        <input type="hidden" name="image" value="${dto.productImage}" />
                                                        <c:if test="${not empty sessionScope.USER}">
                                                            <div class="overlay_hover"> <button type="submit" name="btAction" value="addProductInCart" class="add-bt" >+ Add to cart</button> </div>

                                                        </c:if>

                                                        <c:if test="${empty sessionScope.USER}">
                                                            <div class="overlay_hover"> <button type="submit" name="btAction" value="loginPage" class="add-bt" >+ Add to cart</button> </div>

                                                        </c:if>
                                                    </div>
                                                </div>

                                                <!-- parameter -->
                                                <input type="hidden" name="userDelete" value="${currentUserID}" />
                                                <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                                <input type="hidden" name="searchValueName" value="${param.searchValueName}" />
                                                <input type="hidden" name="minPrice" value="${param.minPrice}" />
                                                <input type="hidden" name="maxPrice" value="${param.maxPrice}" />
                                                <input type="hidden" name="categorySearchValue" value="${param.categorySearchValue}" />


                                                <div class="product_detail text_align_center">
                                                    <p class="product_price">${dto.productPrice} đ</p>
                                                    <input type="hidden" name="productPrice" value="${dto.productPrice}" />
                                                    <p class="product_name"><strong>Name:    </strong>${dto.productName}</p>
                                                    <input type="hidden" name="productName" value="${dto.productName}" />
                                                    <p class="product_category"><strong>Category:    </strong>${dto.category}</p>
                                                    <input type="hidden" name="productCategory" value="${dto.category}" />
                                                    <input type="hidden" name="productID" value="${dto.productID}" />
                                                    <input type="hidden" name="quantity" value="${dto.quantity}" />
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                </div>
                                <!-- end product -->
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty listItems && empty requestScope.ERROR}">
                <h1 class="error-list" style="color:red">List Products Are Empty!!!</h1>
            </c:if>
        </c:if>
        <!--admin-->
        <c:if test="${sessionScope.USER.isAdmin eq true}">

            <c:if test="${not empty listItems}">
                <div id="loopp">
                    <form action="DispatcherServlet">

                        <div class="container">
                            <div class="row">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                                    <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                    <input type="hidden" name="searchValueName" value="${param.searchValueName}" />
                                    <input type="hidden" name="minPrice" value="${param.minPrice}" />
                                    <input type="hidden" name="maxPrice" value="${param.maxPrice}" />
                                    <input type="hidden" name="categorySearchValue" value="${param.categorySearchValue}" />
                                    <button id="removeButton" type="submit" name="btAction" value="deleteProducts">
                                        Delete the selected items
                                    </button>
                                    <!-- Button trigger modal -->
                                    <button class="btn btn-primary btn-lg" type="button" data-toggle="modal" data-target="#createForm">
                                        Create New Product
                                    </button>
                                    <p style="color: red"> ${param.deleteError}</p>
                                </div>
                            </div>
                        </div>

                        <!-- Paging -->
                        <nav aria-label="Page navigation">
                            <ul class="pagination">


                            </ul>
                        </nav>

                        <!-- end paging -->
                        <div class="container">
                            <div class="row">
                                <c:forEach var="dto" items="${listItems}" varStatus="counter">
                                    <!-- product -->

                                    <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 list-groupp">
                                        <div class=" list-group-itemm">

                                            <div class="full product">
                                                <div class="product_img">
                                                    <div class="center"> 
                                                        <img src="${dto.productImage}" alt="#" />
                                                    </div>
                                                </div>

                                                <div class="product_detail text_align_center">
                                                    <p class="product_price">${dto.productPrice} đ</p>

                                                    <p class="product_name"><strong>Name:    </strong>${dto.productName}</p>

                                                    <p class="product_category"><strong>Category:    </strong>${dto.category}</p>

                                                    <p class="product_category"><strong>Status:    </strong>${dto.status}</p>

                                                    <!-- Button trigger modal UPDATE-->
                                                    <button
                                                        onclick="document.getElementById('${dto.productID}').style.display = 'block'"
                                                        type="button" class="btn btn-primary btn-lg"
                                                        data-toggle="modal" data-target="#${dto.productID}">
                                                        Update
                                                    </button>
                                                    <c:if test="${dto.status eq true}">
                                                        <input id="removeCheckox" type="checkbox" name="chkRemove"
                                                               value="${dto.productID}" />
                                                    </c:if>

                                                </div>

                                            </div>

                                        </div>
                                    </div>

                                    <!-- end product -->


                                </c:forEach>
                            </div>
                        </div>
                    </form>
                </div>

            </c:if>

            <c:if test="${empty listItems && empty requestScope.ERROR}">
                <h1 class="error-list" style="color:red">List Products Are Empty!!!</h1>
            </c:if>

        </c:if>

        <!--End Form Product-->

        <!-- modal Update -->
        <c:forEach var="dto" items="${listItems}" varStatus="counter">
            <div class="updateForm">
                <form action="DispatcherServlet">
                    <div class="modal fade" id="${dto.productID}" tabindex="-1" role="dialog"
                         aria-labelledby="modelTitleId" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Update Product Form</h5>
                                    <button type="button" class="close"
                                            data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="container updatee">
                                        <input type="hidden" name="UserUpdate" value="${currentUserID}" />
                                        <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                        <input type="hidden" name="searchValueName" value="${param.searchValueName}" />
                                        <input type="hidden" name="minPrice" value="${param.minPrice}" />
                                        <input type="hidden" name="maxPrice" value="${param.maxPrice}" />
                                        <input type="hidden" name="categorySearchValue" value="${param.categorySearchValue}" />


                                        <label for="ID"><b>Product ID</b></label>
                                        <input type="text" value="${dto.productID}"
                                               disabled />
                                        <input type="hidden" name="ID"
                                               value="${dto.productID}" />
                                        <label for="ID"><b>Product Name</b></label>
                                        <input type="text"
                                               placeholder="Enter Product Name..."
                                               name="Name" value="${dto.productName}" 
                                               required />
                                        <label for="Image"><b>Product Image</b></label>
                                        <input type="text"
                                               placeholder="Enter link Product Image..."
                                               name="Image" value="${dto.productImage}" 
                                               required />
                                        <label for="Description"><b>Product
                                                Description</b></label>
                                        <input type="text"
                                               placeholder="Enter Product Description..."
                                               name="Description"
                                               value="${dto.productDescription}" 
                                               required />
                                        <label for="Price"><b>Product
                                                Price</b></label>
                                        <input type="number" step="0.01" min="1"
                                               placeholder="Enter Product Price..."
                                               name="Price" value="${dto.productPrice}" 
                                               required />
                                        <label for="Quantity"><b>Product
                                                Quantity</b></label>
                                        <input type="number" min="0"
                                               placeholder="Enter Product Quantity..."
                                               name="Quantity" value="${dto.quantity}" 
                                               required />
                                        <!-- category -->
                                        <label for="Category"><b>Category</b></label>
                                        <select name="Category">
                                            <c:forEach var="category" items="${categoryList}">
                                                <c:if test="${dto.category eq category}">
                                                    <option value="${category}">${category}</option>
                                                </c:if>
                                            </c:forEach>
                                            <c:forEach var="category" items="${categoryList}">
                                                <c:if test="${dto.category != category}">
                                                    <option value="${category}">${category}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>

                                        <!-- status -->
                                        <label for="Status"><b>Status</b></label>
                                        <select name="Status">
                                            <c:if test="${dto.status eq true}">

                                                <option value="${dto.status}">Active</option>
                                                <option value="false">Inactive</option>

                                            </c:if>
                                            <c:if test="${dto.status eq false}">

                                                <option value="${dto.status}">Inactive</option>
                                                <option value="true">Active</option>

                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <c:if test="${not empty param.productName}">
                                        <h4>Update ${param.productName} Success!</h4>
                                    </c:if>
                                    <button type="submit" class="btn btn-primary"
                                            name="btAction"
                                            value="updateProduct">Update</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </c:forEach>
        <!-- modal Update -->
        <!-- Modal create new -->
        <div class="modal fade" id="createForm" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
            <form action="DispatcherServlet">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Create New Product Form</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body createFormm">
                            <div class="container ">
                                <input type="hidden" name="UserInsert" value="${currentUserID}" />
                                <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                <input type="hidden" name="searchValueName" value="${param.searchValueName}" />
                                <input type="hidden" name="minPrice" value="${param.minPrice}" />
                                <input type="hidden" name="maxPrice" value="${param.maxPrice}" />
                                <input type="hidden" name="categorySearchValue" value="${param.categorySearchValue}" />
                                <label for="ID"><b>Product ID</b></label>
                                <input type="text" name="ID" 
                                       placeholder="Enter Product ID..."
                                       value="${param.ID}"  required/>
                                <label for="Name"><b>Product Name</b></label>
                                <input type="text"
                                       placeholder="Enter Product Name..."
                                       name="Name" value="${param.Name}" 
                                       required />
                                <label for="Image"><b>Product Image</b></label>
                                <input type="text"
                                       placeholder="Enter link Product Image..."
                                       name="Image" value="${param.Image}" 
                                       required />
                                <label for="Description"><b>Product
                                        Description</b></label>
                                <input type="text"
                                       placeholder="Enter Product Description..."
                                       name="Description" 
                                       value="${param.Description}"
                                       required />
                                <label for="Price"><b>Product
                                        Price</b></label>
                                <input type="number" step="0.01" min="1"
                                       placeholder="Enter Product Price..."
                                       name="Price" value="${param.Price}" 
                                       required />
                                <label for="Quantity"><b>Product
                                        Quantity</b></label>
                                <input type="number" min="0"
                                       placeholder="Enter Product Quantity..."
                                       name="Quantity" value="${param.Quantity}" 
                                       required />
                                <!-- category -->
                                <label for="Category"><b>Category</b></label>
                                <select name="Category">
                                    <c:forEach var="category" items="${categoryList}">
                                        <c:if test="${param.Category == category}">
                                            <option value="${category}">${category}</option>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach var="category" items="${categoryList}">
                                        <c:if test="${param.Category != category}">
                                            <option value="${category}">${category}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>

                                <!-- status -->
                                <label for="Status"><b>Status</b></label>
                                <select name="Status">
                                    <c:if test="${not empty param.status}">
                                        <c:if test="${param.status=='true'}">

                                            <option value="${param.status}">Active</option>
                                            <option value="false">Inactive</option>

                                        </c:if>
                                        <c:if test="${param.status=='false'}">

                                            <option value="${param.status}">Inactive</option>
                                            <option value="true">Active</option>

                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty param.status}">
                                        <option value="true">Active</option>
                                        <option value="false">Inactive</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <c:if test="${not empty param.newError}">
                                <label style="color: red">${param.newError}</label>
                            </c:if>
                            <button type="submit" class="btn btn-primary"
                                    name="btAction"
                                    value="createNewPoduct">Create</button>

                        </div>
                    </div>
                </div>
            </form>
        </div> 
        <!--end modal create new-->






        <!--Footer-->
        <footer class="page-footer text-center font-small mt-4 wow fadeIn">
            <!--Copyright-->
            <div class="footer-copyright py-3">
                2019 Copyright:
                <a href="https://mdbootstrap.com/education/bootstrap/"> Tks MDBootstrap.com !!!
                </a>
            </div>
            <!--/.Copyright-->

        </footer>
        <!--/.Footer-->

        <!-- SCRIPTS -->
        <!-- JQuery -->
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
        <script src="js/style.js"></script>
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
        <c:if test="${not empty param.productID}">
            <script>
                $('#${param.productID}').modal('show');
            </script>
        </c:if>

        <c:if test="${not empty param.newError}">
            <script>
                $('#createForm').modal('show');
            </script>
        </c:if>
        <!--end Form JS Update-->
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    </body>

</html>