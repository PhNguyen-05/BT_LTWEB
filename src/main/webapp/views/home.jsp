<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="row">
    <!-- Welcome -->
    <div class="col-12 text-center welcome-section">
        <c:if test="${not empty sessionScope.account}">
            <h2 class="text-danger">Chào mừng, ${sessionScope.account.fullname}!</h2>
            <p class="lead">Khám phá những bó hoa tuyệt đẹp tại StarShop. 🌸</p>
        </c:if>
        <c:if test="${empty sessionScope.account}">
            <h2 class="text-danger">Chào mừng đến với StarShop!</h2>
            <p class="lead">Nơi những bông hoa tỏa sáng như ngôi sao.</p>
        </c:if>
    </div>

    <!-- Carousel Banner hoa -->
    <div id="bannerStarShop" class="carousel slide col-12 mb-4" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/assets/images/banner-starshop1.jpg" class="d-block w-100" alt="Bó hoa pastel" style="height: 400px; object-fit: cover;" onerror="this.src='${pageContext.request.contextPath}/assets/images/default-banner.jpg';">
                <div class="carousel-caption d-none d-md-block bg-dark bg-opacity-50 rounded">
                    <h5>Bó hoa tinh tế</h5>
                    <p>Chỉ từ 250k!</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/assets/images/banner-starshop2.jpg" class="d-block w-100" alt="Giỏ hoa" style="height: 400px; object-fit: cover;" onerror="this.src='${pageContext.request.contextPath}/assets/images/default-banner.jpg';">
            </div>
            <%-- Thêm slide thứ 3 nếu có --%>
            <c:if test="${not empty banner3}">
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/assets/images/banner-starshop3.jpg" class="d-block w-100" alt="Bó hoa đặc biệt" style="height: 400px; object-fit: cover;" onerror="this.src='${pageContext.request.contextPath}/assets/images/default-banner.jpg';">
                </div>
            </c:if>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#bannerStarShop" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#bannerStarShop" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <!-- Cards Danh mục hoa -->
    <div class="col-12">
        <h3 class="text-center mb-4 text-danger">Danh mục hoa nổi bật</h3>
        <div class="row">
            <c:forEach var="cate" items="${categories}">
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <img src="${pageContext.request.contextPath}${cate.icon}" class="card-img-top" alt="${cate.categoryName}" style="height: 200px; object-fit: cover;" onerror="this.src='${pageContext.request.contextPath}/assets/images/default-icon.jpg';">
                        <div class="card-body text-center">
                            <h5 class="card-title text-danger">${cate.categoryName}</h5>
                            <p class="card-text">Bó hoa tươi, giao nhanh trong ngày.</p>
                            <a href="${pageContext.request.contextPath}/category/detail?id=${cate.categoryID}" class="btn btn-outline-danger">Xem thêm</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Section Sản phẩm nổi bật (chỉ hiển thị nếu có products) -->
    <c:if test="${not empty products}">
        <div class="col-12 mt-5">
            <h3 class="text-center mb-4 text-danger">Sản phẩm hoa nổi bật</h3>
            <div class="row">
                <c:forEach var="product" items="${products}" varStatus="status">
                    <c:if test="${status.index < 6}"> <%-- Giới hạn 6 sản phẩm --%>
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <img src="${pageContext.request.contextPath}/uploads/${product.imageLink}" class="card-img-top" alt="${product.productName}" style="height: 200px; object-fit: cover;" onerror="this.src='${pageContext.request.contextPath}/assets/images/default-product.jpg';">
                                <div class="card-body text-center">
                                    <h5 class="card-title text-danger">${product.productName}</h5>
                                    <p class="card-text">Giá: ${product.price} VND</p>
                                    <p class="card-text small text-muted">${product.description}</p> <%-- text-muted để font nhẹ hơn, kế thừa từ layout --%>
                                    <a href="${pageContext.request.contextPath}/product/detail?id=${product.productID}" class="btn btn-outline-danger">Xem chi tiết</a>
                                    <button class="btn btn-danger mt-2" onclick="addToCart(${product.productID})">Thêm Vào Giỏ</button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:if>
</div>

<script>
    // JS cho "Thêm Vào Giỏ" với AJAX (cải thiện: kiểm tra đăng nhập nếu cần)
    function addToCart(productId) {
        <c:if test="${empty sessionScope.account}">
            alert('Vui lòng đăng nhập để thêm vào giỏ hàng!');
            window.location.href = '${pageContext.request.contextPath}/login';
            return;
        </c:if>
        fetch('${pageContext.request.contextPath}/cart/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ productId: productId, quantity: 1 })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Đã thêm sản phẩm vào giỏ hàng!');
                // Cập nhật badge giỏ hàng nếu có (ví dụ: location.reload(); để refresh)
            } else {
                alert('Lỗi: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Có lỗi xảy ra khi thêm sản phẩm.');
        });
    }
</script>