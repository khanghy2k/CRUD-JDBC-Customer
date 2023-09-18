<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Category</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="action" value="delete">
    <input type="hidden" name="deleteID" value="${d.id}">

    <h1>Xóa sản phẩm</h1>
    <p><b>Tên danh mục:</b> ${d.name}</p>

    <p><b>Danh mục cha:</b>
        <select name="txtParentId" disabled>
            <option value="">Danh mục gốc</option>
            <c:forEach var="category" items="${data}">
                <c:choose>
                    <c:when test="${category.id == d.parentId}">
                        <option value="${category.id}" selected>${category.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${category.id}">${category.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </p>

    <p><b>Trạng thái:</b>
        <c:choose>
            <c:when test="${d.status == true}">
                <input type="radio" name="txtStatus" value="true" checked> Hoạt động
                <input type="radio" name="txtStatus" value="false"> Không hoạt động
            </c:when>
            <c:otherwise>
                <input type="radio" name="txtStatus" value="true"> Hoạt động
                <input type="radio" name="txtStatus" value="false" checked> Không hoạt động
            </c:otherwise>
        </c:choose>
    </p>
    <input type="submit" value="Xóa">
</form>
</body>
</html>
