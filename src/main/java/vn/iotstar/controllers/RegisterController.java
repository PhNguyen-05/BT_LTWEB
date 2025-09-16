package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/views/register.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String sdt = req.getParameter("sdt");  // Thay phone bằng sdt

        String alertMsg = "";

        // Handle null
        if (username == null) username = "";
        if (password == null) password = "";
        if (email == null) email = "";
        if (fullname == null) fullname = "";
        if (sdt == null) sdt = "";

        // Kiểm tra dữ liệu rỗng
        if (username.trim().isEmpty() || password.trim().isEmpty() || email.trim().isEmpty() || fullname.trim().isEmpty()) {
            alertMsg = "Vui lòng nhập đầy đủ thông tin bắt buộc";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();

        // Kiểm tra trùng lặp
        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (!sdt.trim().isEmpty() && service.checkExistSdt(sdt)) {
            alertMsg = "Số điện thoại đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        String confirmPassword = req.getParameter("confirmPassword");
        if (confirmPassword == null) confirmPassword = "";
        if (!password.equals(confirmPassword)) {
            alertMsg = "Mật khẩu không khớp!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Tạo UserModel
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setSdt(sdt);  // Thay phone bằng sdt
        user.setPassword(password);

        boolean success = service.register(user);
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Đăng ký thất bại! Vui lòng thử lại.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
