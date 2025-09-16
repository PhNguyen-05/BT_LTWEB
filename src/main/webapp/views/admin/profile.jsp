<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật Profile StarShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Cập nhật thông tin cá nhân</h2>
        <c:if test="${not empty alert}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/admin/profile" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="fullname" class="form-label">Họ và tên</label>
                <input type="text" class="form-control" id="fullname" name="fullname" value="${account.fullname}" required>
            </div>
            <div class="mb-3">
                <label for="sdt" class="form-label">Số điện thoại</label>
                <input type="tel" class="form-control" id="sdt" name="sdt" value="${account.sdt}" required>
            </div>
            <div class="mb-3">
                <label for="images" class="form-label">Ảnh đại diện</label>
                <input type="file" class="form-control" id="images" name="images" accept="image/*">
                <c:if test="${not empty account.images}">
                    <img src="${pageContext.request.contextPath}${account.images}" alt="Avatar" width="100" class="mt-2">
                </c:if>
            </div>
            <button type="submit" class="btn btn-primary">Cập nhật</button>
            <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary">Hủy</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>