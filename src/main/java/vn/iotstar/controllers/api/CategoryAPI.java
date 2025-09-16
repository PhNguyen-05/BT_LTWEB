package vn.iotstar.controllers.api;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.iotstar.entity.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.util.Constant;
import vn.iotstar.util.HttpUtil;
import vn.iotstar.util.UploadUtils;

@MultipartConfig
@WebServlet(urlPatterns = {"/api-admin-category"})
public class CategoryAPI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoryService categoryService = new CategoryServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), categoryService.findAll());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Category category = new Category();
        try {
            BeanUtils.populate(category, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        String fileName = "" + System.currentTimeMillis();
        category.setIcon(UploadUtils.processUpload("icon", request, Constant.DIR + "\\category\\", fileName));
        categoryService.insert(category);
        mapper.writeValue(response.getOutputStream(), category);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Category category = new Category();
        try {
            BeanUtils.populate(category, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        Category oldCate = categoryService.findById(category.getCategoryID());
        if (request.getPart("icon").getSize() == 0) {
            category.setIcon(oldCate.getIcon());
        } else {
            if (oldCate.getIcon() != null) {
                String fileName = oldCate.getIcon();
                File file = new File(Constant.DIR + "\\category\\" + fileName);
                if (file.delete()) {
                    System.out.println("Đã xóa thành công");
                } else {
                    System.out.println(Constant.DIR + "\\category\\" + fileName);
                }
            }
            String fileName = "" + System.currentTimeMillis();
            category.setIcon(UploadUtils.processUpload("icon", request, Constant.DIR + "\\category\\", fileName));
        }
        categoryService.update(category);
        mapper.writeValue(response.getOutputStream(), category);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Category cateModel = HttpUtil.of(request.getReader()).toModel(Category.class);
        try {
            categoryService.delete(cateModel.getCategoryID());
            mapper.writeValue(response.getOutputStream(), "{\"message\": \"Đã xóa thành công\"}");
        } catch (Exception e) {
            e.printStackTrace();
            mapper.writeValue(response.getOutputStream(), "{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}