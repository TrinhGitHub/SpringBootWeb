<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-light shadow">
    <div class="container d-flex justify-content-between align-items-center">

        <a  class="navbar-brand text-success logo h1 align-self-center" href="/home">
            Zay

        </a>

        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="templatemo_main_nav">
            <div class="flex-fill">
                <ul class="nav navbar-nav justify-content-left mx-lg-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/shop/products">Shop</a>
                    </li>
                    <li>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Categories</a>
                            <div class="dropdown-menu m-0 rounded-0">
                                <c:forEach items="${categories}" var="cate">
                                    <a class="dropdown-item ${cateActive == cate.cateid ? "active" : ""}" href="/shop/productsWithCate?cateid=${cate.cateid}">
                                            ${cate.catename}
                                        <i class="pull-right fa fa-fw fa-chevron-circle-down mt-1"></i>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>
            <div class="navbar align-self-center d-flex">
                <div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id="inputMobileSearch" placeholder="Search ...">
                        <div class="input-group-text">
                            <i class="fa fa-fw fa-search"></i>
                        </div>
                    </div>
                </div>
                <a class="nav-icon position-relative text-decoration-none" href="/cart">
                    <i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i>
                    <span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">${totalproduct}</span>
                </a>
                <c:if test="${user != null}">
                    <div class="btn-group">
                        <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                ${user.name}
                            <i class="fa fa-fw fa-user text-white mr-3"></i>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" style="font-size: 15px !important;" href="/infor">Thông tin cá nhân</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" style="font-size: 15px !important;" href="/login/logout">Đăng Xuất</a></li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${user == null}">
                    <a href="/login">

                        <button type="button" class="btn btn-success">login
                            <i class="fa fa-fw fa-user text-white mr-3"></i>
                        </button>
                    </a>
                </c:if>
            </div>
        </div>

    </div>
</nav>
<!-- Close Header -->
