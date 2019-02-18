<%--
  Created by IntelliJ IDEA.
  User: weiwei
  Date: 2019/1/14
  Time: 下午5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<h1>Welcome to BookStoreAPI</h1>
<p>Because this is an independent API, there is no front-end web page</p>
<p>But you still can try to use some functions</p>
<a href="http://101.132.77.215:8080/bookstore/rest/ProductCatalog/getProductList">Get the lisft of all books</a>
<br><br>
<a href="http://101.132.77.215:8080/bookstore/rest/ProductCatalog/getCategoryList">Get the list of all categories</a>
<br><br>
<a href="http://101.132.77.215:8080/bookstore/rest/ProductCatalog/getProductInfo/1118008189">Check a specific book's detailed info(by its id)</a>
<br><br>
<a href="http://101.132.77.215:8080/bookstore/rest/ProductCatalog/getProductList/1">Check books which belong to a specific category (categoryid:1,2,3...)</a>
<br><br>
<a href="http://101.132.77.215:8080/bookstore/rest/OrderProcess/getUserinfo/1">Get someone's personal info (userid:1,2,3....21)</a>
<br><br>
<a href="http://101.132.77.215:8080/bookstore/rest/OrderProcess/getAddressinfo/1">Check someone's address detailed info (userid:1,2,3....21)</a>
<br><br>
<a href="http://101.132.77.215:8080/bookstore/rest/OrderProcess/DisplayMyOrder/1">Display someone's orders (userid:1,2,3....21)</a>
<br><br>
<p>For the other functions and operations, be sure to check my Github Repository:<p>
<a href="https://github.com/SevenWG">https://github.com/SevenWG</a>
<br><br>
    <p>To clone this project:</p>
    <a href="https://github.com/SevenWG/BookStoreAPI-v2.0.git">https://github.com/SevenWG/BookStoreAPI-v2.0.git</a>
<a></a>
</body>
</html>
