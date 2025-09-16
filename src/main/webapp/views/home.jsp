<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row justify-content-center">
    <!-- Welcome message cá nhân hóa -->
    <div class="col-12 text-center welcome-section mb-4">
        <c:if test="${not empty sessionScope.account}">
            <h2 class="text-danger">Chào mừng quay lại, ${sessionScope.account.fullname}!</h2>
            <p class="lead">Hãy chọn bó hoa yêu thích hôm nay nhé! 🌸</p>
        </c:if>
        <c:if test="${empty sessionScope.account}">
            <h2 class="text-danger">Chào mừng đến với StartShop!</h2>
            <p class="lead">Nơi những bông hoa tươi đẹp chờ bạn.</p>
        </c:if>
    </div>

    <!-- Carousel Banner với hình ảnh hoa (slide tự động) -->
    <div id="bannerHoa" class="carousel slide col-lg-10 mb-5" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#bannerHoa" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#bannerHoa" data-bs-slide-to="1" aria-label="Slide 2"></button>
        </div>
        <div class="carousel-inner rounded shadow">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/assets/images/bohoa.jpg" class="d-block w-100" alt="Bó hoa hồng đẹp" style="height: 450px; object-fit: cover;">
                <div class="carousel-caption d-none d-md-block bg-dark bg-opacity-50 rounded p-3">
                    <h5 class="text-white">Hoa Hồng Tươi Mới</h5>
                    <p class="text-light">Giá chỉ từ 200k - Giao hàng nhanh!</p>
                    <a href="${pageContext.request.contextPath}/product/list" class="btn btn-outline-light">Mua ngay</a>
                </div>
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/assets/images/banner-hoa2.jpg" class="d-block w-100" alt="Giỏ hoa hỗn hợp" style="height: 450px; object-fit: cover;">
                <div class="carousel-caption d-none d-md-block bg-dark bg-opacity-50 rounded p-3">
                    <h5 class="text-white">Hoa Tươi Cho Mọi Dịp</h5>
                    <p class="text-light">Sinh nhật, kỷ niệm - Đa dạng lựa chọn!</p>
                    <a href="${pageContext.request.contextPath}/product/list" class="btn btn-outline-light">Xem thêm</a>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#bannerHoa" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#bannerHoa" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <!-- Cards Danh mục hoa với hình ảnh icon -->
    <div class="col-12">
        <h3 class="text-center mb-4 text-danger">Danh mục hoa nổi bật</h3>
        <div class="row justify-content-center">
            <c:forEach var="cate" items="${categories}">  <!-- Giả sử categories từ HomeController -->
                <div class="col-md-3 mb-4">
                    <div class="card h-100 shadow rounded">
                        <img src="${cate.icon}" class="card-img-top rounded-top" alt="${cate.categoryName}" style="height: 200px; object-fit: cover;">  <!-- Icon là ảnh hoa từ DB -->
                        <div class="card-body text-center">
                            <h5 class="card-title text-danger">${cate.categoryName}</h5>  <!-- Ví dụ: Hoa Hồng, Hoa Lan -->
                            <p class="card-text text-muted">Bó hoa tươi. Giao hàng tận nơi.</p>
                            <a href="${pageContext.request.contextPath}/category/detail?id=${cate.categoryID}" class="btn btn-outline-danger">Khám phá</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>