<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Start Categories of The Month -->
<section class="container py-5">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col">

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col" class="h5">Shopping Bag</th>
                            <th scope="col">Format</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="item">
                            <tr>
                                <th scope="row">
                                    <div class="d-flex align-items-center">
                                        <c:if test="${item.image != null}">
                                            <c:choose>
                                                <c:when test="${item.image.substring(0,4) == 'http'}">
                                                    <img id="images" src="${item.image}" class="img-fluid rounded-3"
                                                         style="width: 120px;" alt="Book">
                                                </c:when>
                                                <c:otherwise>
                                                    <img id="images" src="/admin/images/${item.image}" class="img-fluid rounded-3"
                                                         style="width: 120px;" alt="Book">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                        <c:if test="${item.image == null}">
                                            <img id="images" src="/templates/img/feature_prod_01.jpg" class="img-fluid rounded-3"
                                                 style="width: 120px;" alt="Book">
                                        </c:if>
                                        <div class="flex-column ms-4">
                                            <p class="mb-2">${item.productname} ${item.productid}</p>
                                            <p class="mb-0">Daniel Kahneman</p>
                                        </div>
                                    </div>
                                </th>
                                <td class="align-middle">
                                    <p class="mb-0" style="font-weight: 500;">Digital</p>
                                </td>
                                <td class="align-middle">
                                    <div class="d-flex flex-row">
                                        <a class="btn btn-link px-2" href="/minustocart?cart_itemid=${item.cartitemid}">
                                            <i class="fas fa-minus"></i>
                                        </a>

                                        <input id="form1" min="0" name="quantity" value="${item.quantity}" type="number"
                                               class="form-control form-control-sm" style="width: 50px;" />

                                        <a class="btn btn-link px-2" href="/addtocart?productid=${item.productid}&quantity=${item.quantity}&cart_itemid=${item.cartid}">
                                            <i class="fas fa-plus"></i>
                                        </a>
                                    </div>
                                </td>
                                <td class="align-middle">
                                    <p class="mb-0" style="font-weight: 500;">$9.99</p>
                                </td>
                                <td class="align-middle">
                                    <a href="/cart/delete/${item.cartitemid}" style="color: #cecece;"><i class="fas fa-trash-alt"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="card shadow-2-strong mb-5 mb-lg-0" style="border-radius: 16px;">
                    <div class="card-body p-4">

                        <div class="row">
                            <div class="col-md-6 col-lg-4 col-xl-3 mb-4 mb-md-0">
                                <form>
                                    <div class="d-flex flex-row pb-3">
                                        <div class="d-flex align-items-center pe-2">
                                            <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel1v"
                                                   value="" aria-label="..." checked />
                                        </div>
                                        <div class="rounded border w-100 p-3">
                                            <p class="d-flex align-items-center mb-0">
                                                <i class="fab fa-cc-mastercard fa-2x text-dark pe-2"></i>Credit
                                                Card
                                            </p>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row pb-3">
                                        <div class="d-flex align-items-center pe-2">
                                            <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel2v"
                                                   value="" aria-label="..." />
                                        </div>
                                        <div class="rounded border w-100 p-3">
                                            <p class="d-flex align-items-center mb-0">
                                                <i class="fab fa-cc-visa fa-2x fa-lg text-dark pe-2"></i>Debit Card
                                            </p>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row">
                                        <div class="d-flex align-items-center pe-2">
                                            <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel3v"
                                                   value="" aria-label="..." />
                                        </div>
                                        <div class="rounded border w-100 p-3">
                                            <p class="d-flex align-items-center mb-0">
                                                <i class="fab fa-cc-paypal fa-2x fa-lg text-dark pe-2"></i>PayPal
                                            </p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-6">
                                <div class="row">
                                    <div class="col-12 col-xl-6">
                                        <div class="form-outline mb-4 mb-xl-5">
                                            <input type="text" id="typeName" class="form-control form-control-lg" siez="17"
                                                   placeholder="John Smith" />
                                            <label class="form-label" for="typeName">Name on card</label>
                                        </div>

                                        <div class="form-outline mb-4 mb-xl-5">
                                            <input type="text" id="typeExp" class="form-control form-control-lg" placeholder="MM/YY"
                                                   size="7" id="exp" minlength="7" maxlength="7" />
                                            <label class="form-label" for="typeExp">Expiration</label>
                                        </div>
                                    </div>
                                    <div class="col-12 col-xl-6">
                                        <div class="form-outline mb-4 mb-xl-5">
                                            <input type="text" id="typeText1" class="form-control form-control-lg" siez="17"
                                                   placeholder="1111 2222 3333 4444" minlength="19" maxlength="19" />
                                            <label class="form-label" for="typeText1">Card Number</label>
                                        </div>

                                        <div class="form-outline mb-4 mb-xl-5">
                                            <input type="password" id="typeText" class="form-control form-control-lg"
                                                   placeholder="&#9679;&#9679;&#9679;" size="1" minlength="3" maxlength="3" />
                                            <label class="form-label" for="typeText">Cvv</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-xl-3">
                                <div class="d-flex justify-content-between" style="font-weight: 500;">
                                    <p class="mb-2">Subtotal</p>
                                    <p class="mb-2">$23.49</p>
                                </div>

                                <div class="d-flex justify-content-between" style="font-weight: 500;">
                                    <p class="mb-0">Shipping</p>
                                    <p class="mb-0">$2.99</p>
                                </div>

                                <hr class="my-4">

                                <div class="d-flex justify-content-between mb-4" style="font-weight: 500;">
                                    <p class="mb-2">Total (tax included)</p>
                                    <p class="mb-2">$26.48</p>
                                </div>

                                <button type="button" class="btn btn-primary btn-block btn-lg">
                                    <div class="d-flex justify-content-between">
                                        <span>Checkout</span>
                                        <span>$26.48</span>
                                    </div>
                                </button>

                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
</section>
<!-- End Categories of The Month -->
