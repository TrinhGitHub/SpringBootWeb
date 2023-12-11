<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post" action="/admin/category/saveofupdate" enctype="multipart/form-data">
    <div class="form-group">
        <label for="cateid">Categoryid</label>
        <input type="text" class="form-control" id="cateid" name="cateid" value="${category.cateid}" aria-describedby="emailHelp" placeholder="Product id" readonly>
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
        <label for="catename">Product name</label>
        <input type="text" class="form-control" value="${category.catename}" id="catename" name="catename" placeholder="Category name">
    </div>
    <div>
        <label for="isSource">Chọn nơi lưu file</label>
        <select name="isSource" aria-label="isSource" id="isSource">
            <option ${category.isSource == true ? 'selected':''} value="true">Lưu Cloudinary</option>
            <option ${category.isSource == false ? 'selected':''} value="false">Lưu trên server</option>
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
        <c:if test="${category.image != null}">
            <c:choose>
                <c:when test="${category.image.substring(0,4) == 'https'}">
                    <img id="images" src="${category.image}" width="60%">
                </c:when>
                <c:otherwise>
                    <img id="images" src="/admin/images/${category.image}" width="60%">
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${category.image == null}">
            <img id="images" src="/templates/images/noimage.png" width="60%">
        </c:if>
    </div>
    <div class="form-group">
        <label for="imageFile">images file</label>
        <input type="file" class="form-control-file" value="${category.imageFile}" id="imageFile" name="imageFile"
               aria-description="imageFile"
               onchange="chonseFile(this)" accept=".jpg, .png"
        >
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>