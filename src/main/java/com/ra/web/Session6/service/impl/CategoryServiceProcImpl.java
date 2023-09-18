package com.ra.web.Session6.service.impl;

import com.ra.web.Session6.model.Category;
import com.ra.web.Session6.service.CategoryService;
import com.ra.web.Session6.util.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceProcImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        List<Category> data = new ArrayList<>();
        Connection connection = null;
        CallableStatement cs = null;
        try {
            // mở kết nối
            connection = MySqlConnection.open();
            // tạo câu truy vấn
            cs = connection.prepareCall("CALL  sp_categories_select()");
            // thực thi
            ResultSet resultSet = cs.executeQuery();
            // Xử lý lấy dữ liệu trong result set
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getString("id"));
                category.setName(resultSet.getString("name"));
                category.setParentId(resultSet.getString("parentId"));
                category.setStatus(resultSet.getBoolean("status"));
                category.setParentCategory(resultSet.getString("parentCategory"));
                data.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // đóng kết nối
            try {
                cs.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return data;
    }

    @Override
    public List<Category> findbyName(String name) {
        List<Category> data = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            return data;
        }

        try (Connection connection = MySqlConnection.open();
             CallableStatement cs = connection.prepareCall("CALL sp_categories_select_byName(?)")) {

            cs.setString(1, "%" + name + "%");
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getString("id"));
                category.setName(resultSet.getString("name"));
                category.setParentId(resultSet.getString("parentId"));
                category.setStatus(resultSet.getBoolean("status"));
                data.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return data;
    }


    @Override
    public void add(Category category) {
        List<Category> data = new ArrayList<>();
        Connection connection = null;
        CallableStatement cs = null;
        try {
            // mở kết nối
            connection = MySqlConnection.open();
            // tạo câu truy vấn
            assert connection != null;
            cs = connection.prepareCall("CALL sp_categories_insert(?,?,?,?)");
            //truyền tham số
            cs.registerOutParameter(1,Types.VARCHAR,36);
            cs.setString(2, category.getName());
            cs.setString(3, category.getParentId());
            cs.setBoolean(4, category.isStatus());
            // thực thi
            int result = cs.executeUpdate();
            // lấy giá trị tham số out



        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // đóng kết nối
            try {
                cs.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Category findId(String id) {
        List<Category> data = new ArrayList<>();
        if (id == null || id.isEmpty()) {
            return null;
        }
        Category category = null;

        try (Connection connection = MySqlConnection.open();
             CallableStatement cs = connection.prepareCall("CALL sp_categories_select_byId(?)")) {
            cs.setString(1, id);
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getString("id"));
                category.setName(resultSet.getString("name"));
                category.setParentId(resultSet.getString("parentId"));
                category.setStatus(resultSet.getBoolean("status"));
                data.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return category;
    }

    @Override
    public void edit(Category category) {
        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = MySqlConnection.open();
            cs = connection.prepareCall("CALL sp_categories_update(?,?,?,?)");
            cs.setString(1, category.getId());
            cs.setString(2, category.getName());
            cs.setString(3, category.getParentId());
            cs.setBoolean(4, category.isStatus());

            int result = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id) {
        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = MySqlConnection.open();
            cs = connection.prepareCall("CALL sp_categories_delete(?)");
            cs.setString(1, id);
            int result = cs.executeUpdate();
            if (result > 0) {
                System.out.println(id + " deleted successfully.");
            } else {
                System.out.println(id + " not found or not deleted.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private boolean checkForChildRecords(String customerId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasChildRecords = false;

        try {
            connection = MySqlConnection.open();
            String sql = "SELECT COUNT(*) FROM orders WHERE customerId = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, customerId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int orderCount = rs.getInt(1);
                if (orderCount > 0) {
                    hasChildRecords = true; // Có bản ghi con liên quan
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hasChildRecords;
    }
}

