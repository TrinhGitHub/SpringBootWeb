<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Product</h1>
    <c:if test="${REF_cartitem_product == true}">
        <p id="alertDiv" class="mb-4">Lỗi Khóa Ngoại.</p>
    </c:if>
    <c:if test="${REF_cartitem_product != true}">
        <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
            For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>
    </c:if>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <div class="row">
                <div class="col-3 text-center">
                    <h5 class="m-0 font-weight-bold text-primary">DataTables Example</h5>
                </div>
                <div class="col-6 m-0 dropdown mb-2">
                    <button class="btn bg-white dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Categories
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="/admin/products">All</a>
                        <c:forEach items="${categories}" var="cate">
                            <a class="dropdown-item" href="/admin/products?categoryid=${cate.cateid}">${cate.catename}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-3">
                    <a class="btn btn-primary" href="/admin/add" role="button">Add Product</a>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Category</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Category</th>
                        <th>Action</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach items="${listproducts}" var="pro">
                    <tr>
                        <td>${pro.productid}</td>
                        <td>${pro.productname}</td>
                        <td>${pro.description}</td>
                        <td>
                            <c:if test="${pro.image != null}">
                                <c:choose>
                                    <c:when test="${pro.image.substring(0,4) == 'http'}">
                                        <img id="images" src="${pro.image}" width="60%">
                                    </c:when>
                                    <c:otherwise>
                                        <img id="images" src="/admin/images/${pro.image}" width="60%">
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <c:if test="${pro.image == null}">
                                <img id="images" src="/templates/img/feature_prod_01.jpg" width="60%">
                            </c:if>
                        </td>
                        <td>$${pro.price}</td>
                        <td>${pro.quantity}</td>
                        <td>${pro.category.catename}</td>
                        <td>
                            <div class="row">
                                <div class="col-3">
                                    <a href="/admin/edit?productid=${pro.productid}" style="font-size: 20px !important;">
                                        <i class="fas fa-solid fa-pen-nib fa-sm fa-fw mr-2 text-success"></i>
                                    </a>
                                </div>
                                <div class="col-3">
                                    <a href="/admin/delete?productid=${pro.productid}" style="font-size: 20px !important;" data-target="#logoutModal">
                                        <i class="fas fa-sharp fa-solid fa-trash fa-sm fa-fw mr-2 text-danger"></i>
                                    </a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<!-- Logout Modal-->
<script type="text/javascript">
    // Chờ 5 giây sau khi trang đã tải
    document.addEventListener("DOMContentLoaded", function () {
        // Lấy đối tượng div thông báo
        var alertDiv = document.getElementById("alertDiv");

        // Nếu tồn tại đối tượng, ẩn nó sau 5 giây
        if (alertDiv) {
            setTimeout(function () {
                alertDiv.style.display = 'none';
            }, 5000); // 5 giây
        }
    });
</script>