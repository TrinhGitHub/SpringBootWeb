<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post" action="/admin/saveofupdate" enctype="multipart/form-data">
    <div class="form-group">
        <label for="productid">ProductId</label>
        <input type="text" class="form-control" id="productid" name="productid" value="${product.productid}" aria-describedby="emailHelp" placeholder="Product id" readonly>
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
        <label for="productname">Product name</label>
        <input type="text" class="form-control" value="${product.productname}" id="productname" name="productname" placeholder="Category name">
    </div>
    <div class="form-group">
        <label for="description">Product description</label>
        <input type="text" class="form-control" value="${product.description}" id="description" name="description" placeholder="Category name">
    </div>
    <div class="form-group">
        <label for="price">Product price</label>
        <input type="text" class="form-control" value="${product.price}" id="price" name="price" placeholder="Category name">
    </div>
    <div class="form-group">
        <label for="quantity">Product quantity</label>
        <input type="text" class="form-control" value="${product.quantity}" id="quantity" name="quantity" placeholder="Category name">
    </div>
    <div class="form-group">
        <label for="categoryid">Category Id</label>
        <select class="form-control" name="categoryid" id="categoryid" >
            <c:forEach items="${categories}" var="item">
                <option value="${item.cateid}" selected="${item.cateid == product.categoryid? 'selected':''}">${item.catename}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="isSource">Chọn nơi lưu file</label>
        <select name="isSource" aria-label="isSource" id="isSource">
            <option ${product.isSource == true ? 'selected':''} value="true">Lưu Cloudinary</option>
            <option ${product.isSource == false ? 'selected':''} value="false">Lưu trên server</option>
        </select>
    </div>
    <div class="form-group">
        <script type="text/javascript">
            function chonseFile(fileinput){
                if(fileinput.files && fileinput.files[0]){
                    var reader = new FileReader();
                    reader.onload = function (e){
                        $('#images').attr('src',e.target.result);
                    }
                    reader.readAsDataURL(fileinput.files[0]);
                }
            }
        </script>
        <c:if test="${product.image != null}">
            <c:choose>
                <c:when test="${product.image.substring(0,4) == 'https'}">
                    <img id="images" src="${product.image}" width="60%">
                </c:when>
                <c:otherwise>
                    <img id="images" src="/admin/images/${product.image}" width="60%">
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${product.image == null}">
            <img id="images" src="/templates/images/noimage.png" width="60%">
        </c:if>
    </div>
    <div class="form-group">
        <label for="imageFile">images file</label>
        <input type="file" class="form-control-file" value="${product.imageFile}" id="imageFile" name="imageFile"
               aria-description="imageFile"
               onchange="chonseFile(this)" accept=".jpg, .png"
        >
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>