<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Categories</h1>
    <c:if test="${REF_category_product == true}">
        <p id="alertDiv" class="mb-4">Lỗi Khóa Ngoại.</p>
    </c:if>
    <c:if test="${REF_category_product != true}">
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
                <div class="col-3">
                    <a class="btn btn-primary" href="/admin/category/add" role="button">Add category</a>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>CategoryId</th>
                        <th>CategoryName</th>
                        <th>Image</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>CategoryId</th>
                        <th>CategoryName</th>
                        <th>Image</th>
                        <th>Action</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach items="${listcategory}" var="cate">
                    <tr>
                        <td>${cate.cateid}</td>
                        <td>${cate.catename}</td>
                        <td>
                            <c:if test="${cate.image != null}">
                                <c:choose>
                                    <c:when test="${cate.image.substring(0,4) == 'http'}">
                                        <img id="images" src="${cate.image}" width="60%">
                                    </c:when>
                                    <c:otherwise>
                                        <img id="images" src="/admin/images/${cate.image}" width="60%">
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <c:if test="${cate.image == null}">
                                <img id="images" src="/templates/img/feature_prod_01.jpg" width="60%">
                            </c:if>
                        </td>
                        <td>
                            <div class="row">
                                <div class="col-3">
                                    <a href="/admin/category/edit?cateid=${cate.cateid}" style="font-size: 20px !important;">
                                        <i class="fas fa-solid fa-pen-nib fa-sm fa-fw mr-2 text-success"></i>
                                    </a>
                                </div>
                                <div class="col-3">
                                    <a href="/admin/category/delete?cateid=${cate.cateid}" style="font-size: 20px !important;" data-target="#logoutModal">
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