<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/10/2020
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>List User</h3>
</hr>
<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Username</th>
        <th scope="col">Password</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${allUsers}" var="user">
        <tr>
            <th scope="row">${user.id}</th>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><a href="<c:url value = '/user/detail/${user.id}'/>">Detail</a>|

                <a href="<c:url value = '/user/edit/${user.id}'/>">Edit</a>|
                <a href="<c:url value = '/user/delete/${user.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<c:url value = '/user/add'/>">Add</a>|
