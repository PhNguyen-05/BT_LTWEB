<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tạo Tài Khoản Mới</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
<style>
body {
	background-image: url('path/to/background-image.jpg');
	background-size: cover;
	background-position: center;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.register-form {
	background-color: rgba(255, 255, 255, 0.9);
	padding: 2rem;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	width: 100%;
}

.form-control {
	background-color: #e9f5ff;
	border: none;
	border-bottom: 2px solid #ccc;
	padding-left: 3rem;
}

.input-group-text {
	background: transparent;
	border: none;
	position: absolute;
	z-index: 2;
	padding: 0.375rem 0.75rem;
	color: #6c757d;
}

.form-group {
	position: relative;
	margin-bottom: 1.5rem;
}

.btn-primary {
	background-color: #0d6efd;
	border: none;
	width: 100%;
}

.alert {
	margin-bottom: 1rem;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<form class="register-form" action="${pageContext.request.contextPath}/register" method="post">
					<h3 class="text-center mb-4">Tạo tài khoản mới</h3>
					<c:if test="${alert != null}">
						<div class="alert alert-danger">${alert}</div>
					</c:if>

					<div class="form-group">
						<span class="input-group-text"><i class="bi bi-person"></i></span>  <!-- Fix truncation -->
						<input type="text" class="form-control" name="username" placeholder="Tên đăng nhập" required value="${param.username}">
					</div>

					<div class="form-group">
						<span class="input-group-text"><i class="bi bi-person-fill"></i></span>
						<input type="text" class="form-control" name="fullname" placeholder="Họ tên" required value="${param.fullname}">
					</div>

					<div class="form-group">
						<span class="input-group-text"><i class="bi bi-envelope"></i></span>
						<input type="email" class="form-control" name="email" placeholder="Nhập Email" required value="${param.email}">
					</div>

					<div class="form-group">
						<span class="input-group-text"><i class="bi bi-telephone"></i></span>
						<input type="tel" class="form-control" name="sdt" placeholder="Số điện thoại" value="${param.sdt}">
					</div>

					<div class="form-group">
						<span class="input-group-text"><i class="bi bi-lock"></i></span> <input type="password" class="form-control" name="password" placeholder="Mật khẩu" required>
					</div>

					<div class="form-group">
						<span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
						<input type="password" class="form-control" name="confirmPassword" placeholder="Nhập lại mật khẩu" required>
					</div>

					<button type="submit" class="btn btn-primary">Tạo tài khoản</button>
					<p class="text-center mt-3">
						Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
					</p>
				</form>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>