package vn.iotstar.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/profile"})
public class ProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/profile.jsp");
		rd.forward(req, resp);
	}
}
//
//package vn.iotstar.admin;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.Part;
//import vn.iotstar.dao.UserDao;
//import vn.iotstar.dao.impl.UserDaoImpl;
//import vn.iotstar.models.UserModel;
//
//@WebServlet(urlPatterns = {"/admin/profile"})
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
//public class ProfileController extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//    private UserDao userDao = new UserDaoImpl();
//    private static final String UPLOAD_DIR = "/uploads";  // Thư mục upload (có thể dùng Constant.UPLOAD_DIRECTORY nếu cần)
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        UserModel userModel = (UserModel) session.getAttribute("account");
//        if (userModel == null) {
//            resp.sendRedirect(req.getContextPath() + "/login");
//            return;
//        }
//        req.setAttribute("account", userModel);
//        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/profile.jsp");
//        rd.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        UserModel userModel = (UserModel) session.getAttribute("account");
//        if (userModel == null) {
//            resp.sendRedirect(req.getContextPath() + "/login");
//            return;
//        }
//
//        // Cập nhật fullname và sdt (phone)
//        String fullname = req.getParameter("fullname");
//        String sdt = req.getParameter("sdt");  // Phone field
//        userModel.setFullname(fullname);
//        userModel.setSdt(sdt);
//
//        // Handle upload images nếu có file
//        Part filePart = req.getPart("images");
//        if (filePart != null && filePart.getSize() > 0) {
//            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//            String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) uploadDir.mkdir();
//
//            String fullPath = uploadPath + File.separator + fileName;
//            filePart.write(fullPath);
//            userModel.setImages(UPLOAD_DIR + "/" + fileName);  // Lưu path vào model
//        }
//
//        // Update DB qua DAO (JPA)
//        userDao.updateProfile(userModel);
//
//        // Update session
//        session.setAttribute("account", userModel);
//
//        // Redirect về home với message thành công
//        req.setAttribute("message", "Cập nhật profile thành công!");
//        resp.sendRedirect(req.getContextPath() + "/admin/home");
//    }
//}

//package vn.iotstar.admin;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.Part;
//import vn.iotstar.models.UserModel;
//import vn.iotstar.services.UserService;
//import vn.iotstar.services.impl.UserServiceImpl;
//
//@WebServlet(urlPatterns = {"/admin/profile"})
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
//public class ProfileController extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//    private UserService userService = new UserServiceImpl();
//    private static final String UPLOAD_DIR = "/uploads";  // Có thể dùng Constant.DIR nếu muốn absolute path
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        UserModel userModel = (UserModel) session.getAttribute("account");
//        if (userModel == null) {
//            resp.sendRedirect(req.getContextPath() + "/login");
//            return;
//        }
//        req.setAttribute("account", userModel);
//        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/profile.jsp");
//        rd.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        UserModel userModel = (UserModel) session.getAttribute("account");
//        if (userModel == null) {
//            resp.sendRedirect(req.getContextPath() + "/login");
//            return;
//        }
//
//        // Cập nhật fullname và sdt (phone)
//        String fullname = req.getParameter("fullname");
//        String sdt = req.getParameter("sdt");  // Phone field
//        userModel.setFullname(fullname);
//        userModel.setSdt(sdt);
//
//        // Handle upload images nếu có file
//        Part filePart = req.getPart("images");
//        if (filePart != null && filePart.getSize() > 0) {
//            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//            String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) uploadDir.mkdir();
//
//            String fullPath = uploadPath + File.separator + fileName;
//            filePart.write(fullPath);
//            userModel.setImages(UPLOAD_DIR + "/" + fileName);  // Lưu path vào model
//        }
//
//        // Update qua service (JPA)
//        userService.updateProfile(userModel);
//
//        // Update session
//        session.setAttribute("account", userModel);
//
//        // Redirect về home với message thành công
//        req.setAttribute("message", "Cập nhật profile thành công!");
//        resp.sendRedirect(req.getContextPath() + "/admin/home");
//    }
//}