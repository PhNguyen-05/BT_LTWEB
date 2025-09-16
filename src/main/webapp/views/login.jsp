<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng nhập</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	background-image: url('path/to/background-image.jpg');
	/* Thay bằng hình nền nếu có */
	background-size: cover;
	background-position: center;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.login-form {
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
	border-radius: 0;
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
				<form class="login-form"
					action="${pageContext.request.contextPath}/login" method="post">
					<h3 class="text-center mb-4">Đăng nhập</h3>
					<c:if test="${alert != null}">
						<div class="alert alert-danger">${alert}</div>
					</c:if>

					<div class="form-group">
						<span class="input-group-text"><i class="bi bi-person"></i></span>
						<input type="text" class="form-control" name="username"
							placeholder="Tài khoản" required value="${param.username}">
					</div>

					<div class="form-group">
						<span class="input-group-text"><i class="bi bi-lock"></i></span> <input
							type="password" class="form-control" name="password"
							placeholder="Mật khẩu" required>
					</div>

					<div class="form-check mb-3">
						<input type="checkbox" class="form-check-input" id="remember"
							name="remember"> <label class="form-check-label"
							for="remember">Nhớ mật khẩu</label>
					</div>

					<button type="submit" class="btn btn-primary">Đăng nhập</button>

					<!-- Thêm link quên mật khẩu ở đây (sau button submit) -->
					<p class="text-center mt-3">
						<a href="${pageContext.request.contextPath}/forgot-password">Quên
							mật khẩu?</a>
					</p>

					<p class="text-center mt-3">
						Nếu chưa có tài khoản? <a
							href="${pageContext.request.contextPath}/register">Đăng ký</a>
					</p>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>