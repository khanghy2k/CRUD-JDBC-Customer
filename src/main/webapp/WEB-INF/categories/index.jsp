<%--
  Created by IntelliJ IDEA.
  User: khang
  Date: 9/11/2023
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách danh mục</title>
</head>
<h1>Danh sách danh mục</h1>
<p><a href="?action=create">Thêm mới danh mục</a></p>
<form action="/categories" method="GET">
    <input type="text" name="search" style="border-radius: 10px; margin: 10px">
    <button style="border-radius: 5px; background-color: whitesmoke; margin: 10px">Tìm Kiếm</button>
</form>
<table border="1" cellpadding="5" cellspacing="0" width="100%">
    <tr>
        <th>Tên danh mục</th>
        <th>Danh mục cha</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    <c:forEach items="${data}" var="c">
        <tr>
            <td>${c.name}</td>
            <td>${c.parentCategory}</td>
            <td>
                <c:if test="${c.status == true}">
                    <c:out value="Hoạt động"/>
                </c:if>
                <c:if test="${c.status == false}">
                    <c:out value="Không Hoạt động"/>
                </c:if>
            </td>
            <td>
                <a href="?action=edit&id=${c.id}">Sửa</a>
                <a href="?action=delete&id=${c.id}">Xóa</a>

            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
