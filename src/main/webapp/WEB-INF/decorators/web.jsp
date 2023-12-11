<%--
  Created by IntelliJ IDEA.
  User: T2011
  Date: 12/5/2023
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Zay Shop eCommerce HTML CSS Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="/templates/img/apple-icon.png">
    <link rel="shortcut icon" type="image/x-icon" href="/templates/img/favicon.ico">

    <link rel="stylesheet" href="/templates/css/bootstrap.min.css">
    <link rel="stylesheet" href="/templates/css/templatemo.css">
    <link rel="stylesheet" href="/templates/css/custom.css">

    <!-- Load fonts style after rendering the layout styles -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="/templates/css/fontawesome.min.css">
</head>
<body>
<div id="header">
    <%@include file="/common/web/Header.jsp"%>
</div>
<div id="content">
    <sitemesh:write property="body" />
</div>
<div id="footer">
    <%@include file="/common/web/Footer.jsp"%>
</div>
<!-- Start Script -->
<script src="/templates/js/jquery-1.11.0.min.js"></script>
<script src="/templates/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/templates/js/bootstrap.bundle.min.js"></script>
<script src="/templates/js/templatemo.js"></script>
<script src="/templates/js/custom.js"></script>
</body>
</html>
