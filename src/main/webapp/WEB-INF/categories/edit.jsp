<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cập nhật sản phẩm</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="txtId" value="${c.id}">
    <p><b>Tên danh mục</b><input type="text" name="txtName" value="<c:out value="${c.name}"/>"></p>
    <p><b>Danh mục cha</b>
        <select name="txtParentId">
            <option value="">Danh mục gốc</option>
            <c:forEach var="category" items="${data}">
                <c:choose>
                    <c:when test="${category.id == c.parentId}">
                        <option value="${category.id}" selected>${category.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${category.id}">${category.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </p>
    <p><b>Trạng thái</b>
        <c:if test="${c.status == true}">
            <input type="radio" name="txtStatus" value="true" checked> Hoạt động
            <input type="radio" name="txtStatus" value="false"> Không động
        </c:if>
        <c:if test="${c.status == false}">
            <input type="radio" name="txtStatus" value="true"> Hoạt động
            <input type="radio" name="txtStatus" value="false" checked> Không hoạt động
        </c:if>
    </p>
    <input type="submit" value="Lưu">
</form>
</body>
</html>
