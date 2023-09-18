package com.ra.web.Session6.controller;

import com.ra.web.Session6.model.Category;
import com.ra.web.Session6.service.CategoryService;
import com.ra.web.Session6.service.impl.CategoryServiceImpl;
import com.ra.web.Session6.service.impl.CategoryServiceProcImpl;
import com.ra.web.Session6.util.MySqlConnection;

import java.io.*;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CategoryService categoryService = new CategoryServiceProcImpl();
        List<Category> data = null;
        Category newCategory = new Category();
        newCategory.setId("UO01");
        newCategory.setName("Hy");
        newCategory.setParentId("UO02");
        newCategory.setStatus(true);
        categoryService.add(newCategory);






        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h1>Danh Sách sản phẩm</h1>");
        out.println("<ul>");
            for (Category category : data){
                out.println("<li>"+category.getName()+"</h1>");
            }
        out.println("</ul>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}