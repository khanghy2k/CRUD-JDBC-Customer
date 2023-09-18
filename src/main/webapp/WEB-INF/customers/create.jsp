<%--
  Created by IntelliJ IDEA.
  User: khang
  Date: 9/14/2023
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Thêm mới khách hàng</h1>
<form method="POST" enctype="multipart/form-data">
    <input type="hidden" name="action" value="create">
    <p><b>Tên khách hàng: </b><input type="text" name="txtName"></p>
    <p><b>Tuổi: </b><input type="number" name="txtAge"></p>
    <p><b>Ngày sinh: </b><input type="date" name="txtBirthday"></p>
    <p><b>Hình ảnh: </b><input type="file" name="txtAvatar"></p>
    <button type="submit">Thêm</button>
</form>
</body>
</html>
