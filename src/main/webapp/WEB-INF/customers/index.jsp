<%--
  Created by IntelliJ IDEA.
  User: khang
  Date: 9/14/2023
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh sách khách hàng</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

</head>
<body>
<style>
    a {
        text-decoration: none;
        color: black;
    }
    h1 {
        font-size: 24px;
        color: #333;
        text-align: center;
        margin-bottom: 20px;
    }
    .custom-form {
        margin: 10px;

    }

    /* Style the input */
    .custom-form input[type="text"] {
        border-radius: 10px;
        margin: 10px;
        padding: 5px;
        border: 1px solid #ccc;
    }

    /* Style the button */
    .custom-form button {
        border-radius: 10px;
        background-color: white;
        margin: 10px;
        padding: 5px 10px;
        border: 1px solid #ccc;
        cursor: pointer;
    }

</style>
<h1>Danh sách khách hàng</h1>
<form action="/customers" method="GET" class="custom-form">
    <input type="text" name="search">
    <button type="submit">Tìm Kiếm</button>
</form>

<table border="1" width="100%" cellspacing="0" cellpadding="5">
    <tr>
        <th>Tên khách hàng</th>
        <th>Tuổi</th>
        <th>Ngày sinh</th>
        <th>Hình ảnh</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="c" items="${data}">
        <tr>
            <td>${c.name}</td>
            <td>${c.age}</td>
            <td><fmt:formatDate value="${c.birthday}" pattern="dd-MM-YYYY"/></td>
            <td><img src="${c.avatar}" alt="" width="60"></td>
            <td>
                <a href="?action=edit&id=<c:out value="${c.id}" />">
                    <i class="fas fa-edit"></i> Sửa
                </a> |
                <a href="?action=delete&id=<c:out value="${c.id}" />" onclick="return confirm('Bạn có chắc chắn muốn xóa khách hàng này?');">
                    <i class="fas fa-trash-alt"></i> Xóa
                </a>
                |
                <a href="?action=create">
                    <i class="fas fa-plus"></i>
                </a>

            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
