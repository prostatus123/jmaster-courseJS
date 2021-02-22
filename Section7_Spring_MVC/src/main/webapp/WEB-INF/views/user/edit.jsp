<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/3/2020
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h3>Form nguoi dung</h3>
<c:url value="/user/edit" var = "url"/>
<form:form modelAttribute="user" method="post" action="${url}" enctype="multipart/form-data">
    <form:hidden path="id"/>
    <p>Enter username:</p><form:input path="username"/>
    <form:errors path="username"/>
    <p>Enter password:</p><form:password path="password"/>
    <form:errors path="password"/>
    <p>Choose a role:</p>
    <form:radiobutton path="role" value="user" label="User" checked="checked"/>
    <form:radiobutton path="role" value="admin" label="Admin"/>
    <p>Upload user's avatar:</p><form:input path="avatar" type="file"/>
    <br>
    <button type="submit">Edit user</button>
</form:form>