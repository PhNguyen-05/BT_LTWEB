package vn.iotstar.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category; 
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/", "/home", "/trangchu" })
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // Load categories từ service/DAO
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> entityList = categoryService.findAll();

        if (entityList == null) {
            entityList = new ArrayList<>();
        }

        // Chuyển đổi entity sang list (nếu muốn xử lý thêm)
        List<Category> categories = entityList.stream()
            .map(c -> {
                Category model = new Category();
                model.setCategoryID(c.getCategoryID());
                model.setCategoryName(c.getCategoryName());
                model.setIcon(c.getIcon());
                return model;
            }).collect(Collectors.toList());

        // Nếu DB rỗng thì thêm dữ liệu test
        if (categories.isEmpty()) {
            Category testCate = new Category();
            testCate.setCategoryID(1);
            testCate.setCategoryName("Hoa Hồng");
            testCate.setIcon(req.getContextPath() + "/assets/images/bohoa.jpg"); 
            categories.add(testCate);
        }

        // Gửi sang JSP
        req.setAttribute("categories", categories);

        RequestDispatcher rd = req.getRequestDispatcher("/views/home.jsp");
        rd.forward(req, resp);
    }
}
