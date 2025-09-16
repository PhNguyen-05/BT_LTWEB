<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><sitemesh:write property='title'/> StarShop</title>
    
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Google Fonts: Lora (cho tiêu đề mềm mại) và Roboto (fallback) -->
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400&family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
    <!-- Custom CSS cho theme hồng pastel -->
    <style>
        body {
            font-family: 'Roboto', 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Fallback sans-serif cho body */
            background: linear-gradient(to bottom, #fff1f5, #fce4ec); /* Hồng pastel nhạt */
            padding-top: 70px; /* Điều chỉnh padding cho navbar fixed */
            color: #555;
        }
        /* Áp dụng font Lora cho tiêu đề và lead để nhất quán */
        .welcome-section h2, .welcome-section p.lead, h3, h5.card-title, .carousel-caption h5, .carousel-caption p {
            font-family: 'Lora', serif !important; /* !important để override Bootstrap */
        }
        .navbar {
            background: linear-gradient(135deg, #ffd1dc, #ffebef); /* Navbar hồng pastel */
            box-shadow: 0 2px 10px rgba(255, 182, 193, 0.3); /* Shadow nhẹ */
        }
        .navbar-brand img {
            border-radius: 50%;
            transition: transform 0.3s;
        }
        .navbar-brand:hover img {
            transform: scale(1.1);
        }
        .nav-link, .navbar-brand {
            color: #ff69b4 !important; /* Chữ hồng đậm hơn trên nền pastel */
            font-family: 'Roboto', sans-serif; /* Giữ sans-serif cho nav */
        }
        .nav-link:hover {
            color: #ff1493 !important;
        }
        .card {
            transition: transform 0.3s, box-shadow 0.3s;
            border: none;
            background: #fffaf9; /* Nền card pastel */
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(255, 182, 193, 0.4); /* Shadow hồng pastel */
        }
        .card-body p {
            font-family: 'Roboto', sans-serif; /* Font đơn giản cho text thường */
        }
        footer {
            background: linear-gradient(135deg, #ffd1dc, #ffebef); /* Footer hồng pastel */
            color: #333;
            padding: 2rem 0;
            font-family: 'Roboto', sans-serif;
        }
        footer a {
            color: #ff69b4;
            transition: color 0.3s;
        }
        footer a:hover {
            color: #ff1493;
        }
        .carousel-item img {
            height: 500px;
            object-fit: cover;
        }
        .welcome-section {
            background: rgba(255, 209, 220, 0.3); /* Hồng pastel nhạt cho welcome */
            border-radius: 15px;
            padding: 2rem;
            margin-bottom: 2rem;
            border: 1px solid rgba(255, 182, 193, 0.5); /* Viền nhẹ */
        }
        .btn-outline-danger {
            color: #ff69b4;
            border-color: #ff69b4;
            font-family: 'Roboto', sans-serif;
        }
        .btn-outline-danger:hover {
            background-color: #ff69b4;
            color: #fff;
        }
        /* Responsive carousel height (di chuyển từ home.jsp để thống nhất) */
        @media (max-width: 768px) {
            .carousel-item img {
                height: 200px; /* Giảm height trên mobile */
            }
            body {
                font-size: 14px; /* Giảm font size trên mobile nếu cần */
            }
        }
    </style>
    <sitemesh:head />
</head>
<body>
    <!-- Navbar: Hồng pastel, dính top -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/assets/images/logo.jpg" alt="StarShop" width="40" height="40" class="me-2">
                <span class="fw-bold" style="color: #ff69b4;">StarShop</span>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home"><i class="bi bi-house me-1"></i> Trang chủ</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/category/list"><i class="bi bi-flower1 me-1"></i> Danh mục hoa</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/product/list"><i class="bi bi-basket me-1"></i> Sản phẩm hoa</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/contact"><i class="bi bi-telephone me-1"></i> Liên hệ</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/about"><i class="bi bi-heart me-1"></i> Giới thiệu</a></li>
                </ul>
                <ul class="navbar-nav">
                    <c:if test="${not empty sessionScope.account}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: #ff69b4;">
                                <i class="bi bi-person-circle me-1"></i> ${sessionScope.account.fullname}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile"><i class="bi bi-person me-2"></i> Hồ sơ</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logout"><i class="bi bi-box-arrow-right me-2"></i> Đăng xuất</a></li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${empty sessionScope.account}">
                        <li class="nav-item"><a class="nav-link btn btn-outline-danger ms-2" href="${pageContext.request.contextPath}/login"><i class="bi bi-box-arrow-in-right me-1"></i> Đăng nhập</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Nội dung chính: Thêm padding và container bo tròn -->
    <div class="container-fluid py-4">
        <div class="container bg-white rounded-3 shadow-sm p-4">
            <sitemesh:write property="body" />
        </div>
    </div>

    <!-- Footer: Hồng pastel, gọn gàng hơn -->
    <footer class="text-center">
        <div class="container py-3">
            <div class="row">
                <div class="col-12 col-md-4 mb-3">
                    <h5 class="text-muted mb-3">StarShop</h5>
                    <p class="text-muted">Cửa hàng hoa tươi đẹp, mang đến những bó hoa tinh tế cho mọi dịp đặc biệt. 🌸</p>
                    <img src="${pageContext.request.contextPath}/assets/images/logo-starshop-white.png" alt="Logo" width="100" class="mb-2">
                </div>
                <div class="col-12 col-md-4 mb-3">
                    <h5 class="text-muted mb-3">Liên hệ</h5>
                    <ul class="list-unstyled">
                        <li class="mb-2"><i class="bi bi-geo-alt text-muted me-2"></i> 123 Đường Hoa, TP.HCM</li>
                        <li class="mb-2"><i class="bi bi-telephone text-muted me-2"></i> 0908 123 456</li>
                        <li class="mb-2"><i class="bi bi-envelope text-muted me-2"></i> info@starshop.com</li>
                    </ul>
                </div>
                <div class="col-12 col-md-4 mb-3">
                    <h5 class="text-muted mb-3">Theo dõi</h5>
                    <div class="d-flex justify-content-center">
                        <a href="#" class="me-3"><i class="bi bi-facebook fs-4 text-muted"></i></a>
                        <a href="#" class="me-3"><i class="bi bi-instagram fs-4 text-muted"></i></a>
                        <a href="#" class="me-3"><i class="bi bi-pinterest fs-4 text-muted"></i></a>
                    </div>
                </div>
            </div>
            <hr class="bg-muted">
            <p class="text-muted mb-0">&copy; 2025 StarShop. All rights reserved.</p>
        </div>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();
                const target = document.querySelector(this.getAttribute('href'));
                if (target) {
                    target.scrollIntoView({ behavior: 'smooth' });
                }
            });
        });
    </script>
</body>
</html>