<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: khang
  Date: 9/11/2023
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm mới danh mục</title>
</head>
<body>
<h1>Thêm mới danh mục</h1>
<form method="post">
    <input type="hidden" name="action" value="create">
    <p><b>Tên danh mục</b><input type="text" name="txtName"></p>
    <p><b>Danh mục cha
        <select name="txtParentId">
            <option value="">Danh mục gốc</option>
            <c:forEach var="c" items="${data}">
                <option value="${c.id}">${c.name}</option>
            </c:forEach>
        </select>
    </b></p>
    <p><b>Trạng thái</b>
        <input type="radio" name="txtStatus" value="true">Hoạt động</p>
    <input type="radio" name="txtStatus" value="false"> Không Hoạt động</p>

    <input type="submit">
</form>
</body>
</html>
