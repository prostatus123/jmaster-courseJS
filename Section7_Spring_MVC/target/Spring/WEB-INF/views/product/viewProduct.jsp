<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2020
  Time: 8:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Product Name</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${allProducts}" var="product">
    <tr>
        <th scope="row">${product.id}</th>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
        <td><a href="<c:url value = '/product/detail/${product.id}'/>">Detail</a>|
            <a href="<c:url value = '/product/edit/${product.id}'/>">Edit</a>|
            <a href="<c:url value = '/product/delete/${product.id}'/>">Delete</a>  </td>
    </tr>
</c:forEach>
    </tbody>
</table>
<a href="<c:url value = '/product/add'/>">Add</a>
