package com.ra.web.Session6.controller;

import com.ra.web.Session6.model.Category;
import com.ra.web.Session6.service.CategoryService;
import com.ra.web.Session6.service.impl.CategoryServiceProcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "CategoriesController", value = "/categories")
public class CategoriesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String search = request.getParameter("search");
        CategoryService service = new CategoryServiceProcImpl();

        if (action == null || action.equals("index")) {
            List<Category> data;
            if (search != null && !search.isEmpty()) {
                data = service.findbyName(search);
            } else {
                data = service.findAll();
            }
            request.setAttribute("data", data);
            request.getRequestDispatcher("/WEB-INF/categories/index.jsp").forward(request, response);
        }
        if (action != null && action.equals("create")) {
            List<Category> data = service.findAll();
            request.setAttribute("data", data);
            request.getRequestDispatcher("/WEB-INF/categories/create.jsp").forward(request, response);
        }
        if (action != null && action.equals("edit")) {
            String id = request.getParameter("id");
            Category c = service.findId(id);
            List<Category> data = service.findAll();
            request.setAttribute("data", data);
            request.setAttribute("c", c);
            request.getRequestDispatcher("/WEB-INF/categories/edit.jsp").forward(request, response);
        }
        if (action != null && action.equals("delete")) {
            String id = request.getParameter("id");
            Category d = service.findId(id);
            List<Category> data = service.findAll();
            request.setAttribute("data", data);
            request.setAttribute("d", d);
            request.getRequestDispatcher("/WEB-INF/categories/delete.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        // lấy thông tin từ form
        String txtName = request.getParameter("txtName");
        String txtParentId = request.getParameter("txtParentId");
        boolean txtStatus = Boolean.parseBoolean(request.getParameter("txtStatus"));

        // tạo đối tượng
        Category category = new Category();
        category.setName(txtName);
        category.setParentId(txtParentId);
        category.setStatus(txtStatus);
        // xử lý dữ liệu
        CategoryService categoryService = new CategoryServiceProcImpl();
        if (action != null && action.equals("edit")) {
            String txtId = request.getParameter("txtId");
            category.setId(txtId);
            categoryService.edit(category);
        }
        if (action != null && action.equals("create")) {
            category.setId(UUID.randomUUID().toString());
            categoryService.add(category);
        }
        if (action != null && action.equals("delete")) {
            String deleteID = request.getParameter("deleteID");
            category.setId(deleteID);
            categoryService.delete(deleteID);

        }
        response.sendRedirect("/categories");
    }
}
