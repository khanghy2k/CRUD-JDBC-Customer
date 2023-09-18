package com.ra.web.Session6.controller;

import com.ra.web.Session6.model.Customer;
import com.ra.web.Session6.service.CustomerService;
import com.ra.web.Session6.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "CustomersController",value = "/customers")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50
)
public class CustomersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CustomerService service = new CustomerServiceImpl();
        if (action == null || action.equals("index")) {
            List<Customer> data = service.findAll();
            req.setAttribute("data", data);
            req.getRequestDispatcher("WEB-INF/customers/index.jsp").forward(req, resp);
        }
        if (action != null && action.equals("create")) {
            req.getRequestDispatcher("WEB-INF/customers/create.jsp").forward(req, resp);
        }
        if (action != null && action.equals("edit")) {
            String id = req.getParameter("id");
            Customer customer = service.findId(id);
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("WEB-INF/customers/edit.jsp").forward(req, resp);
        }
        if (action != null && action.equals("delete")){
            String id = req.getParameter("id");
            service.delete(id);
            resp.sendRedirect("/customers");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        // Lấy thông tin từ form
        String txtName = req.getParameter("txtName");
        int txtAge = Integer.parseInt(req.getParameter("txtAge"));
        // Xử lý ngày tháng
        Timestamp txtBirthday = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            txtBirthday = new Timestamp(format.parse(req.getParameter("txtBirthday")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Xử lý ảnh
        String txtAvatar = null; // Initialize to null by default

// Lấy file trong request gửi lên
        Part file = req.getPart("txtAvatar");

        if (file != null && file.getSize() > 0) {
            // Bóc tách tên file để lưu trữ
            String fileName = extractFileName(file);

            // Tạo đường dẫn thư mục lưu file
            String uploadsDir = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDirectory = new File(uploadsDir);

            // Đảm bảo thư mục lưu file tồn tại
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }
            // Đường dẫn đến file lưu trữ trên server
            String filePath = uploadsDir + File.separator + fileName;
            // Lưu file vào thư mục
            try (InputStream input = file.getInputStream();
                 OutputStream output = new FileOutputStream(filePath)) {
                int bytesRead;
                final byte[] BUFFER = new byte[8192];
                while ((bytesRead = input.read(BUFFER)) != -1) {
                    output.write(BUFFER, 0, bytesRead);
                }
                txtAvatar = "uploads" + File.separator + fileName; // Relative path to the saved file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Tạo đối tượng
        Customer customer = new Customer();
        customer.setName(txtName);
        customer.setAge(txtAge);
        customer.setBirthday(txtBirthday);
        customer.setAvatar(txtAvatar);
        // Xủ lý dữ liệu
        CustomerService service = new CustomerServiceImpl();
        if (action != null && action.equals("edit")) {
            String txtId = req.getParameter("txtId");
            customer.setId(txtId);
            service.edit(customer);
        }
        if (action != null && action.equals("create")) {
            service.add(customer);
        }
        if (action !=null && action.equals("delete")){
            String txtId = req.getParameter("id");
            service.delete(txtId);
            resp.sendRedirect("/customers");
        }
        resp.sendRedirect("/customers");
    }

    private String extractFileName(Part part) {
        String content = part.getHeader("content-disposition");
        String[] items = content.split(";");
        for (String s : items) {
            //filename="abc.jpg"
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}


