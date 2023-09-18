<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Cập nhật khách hàng</title>
</head>
<body>
<h1>Cập nhật khách hàng</h1>
<form method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="txtId" value="${customer.id}">
    <input type="hidden" name="txtAvatar" value="${customer.avatar}">
    <p><b>Tên khách hàng: </b><input type="text" name="txtName" value="${customer.name}"></p>
    <p><b>Tuổi: </b><input type="number" name="txtAge" value="${customer.age}"></p>
    <p><b>Ngày sinh: </b><input type="date" name="txtBirthday" value="<fmt:formatDate value='${customer.birthday}' pattern='yyyy-MM-dd' />" /></p>

    <p><b>Hình ảnh: </b><input type="file" name="avatar"></p>
    <button type="submit">Lưu</button>
</form>
</body>
</html>