package vn.iotstar.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/admin/home"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
//		rd.forward(req, resp);
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // Load categories từ DAO/Service
	    CategoryService categoryService = new CategoryServiceImpl();  // Giả sử bạn có service
	    req.setAttribute("categories", categoryService.findAll());  // Truyền cho JSP
	    
	    RequestDispatcher rd = req.getRequestDispatcher("/views/home.jsp");
	    rd.forward(req, resp);
	}
}

