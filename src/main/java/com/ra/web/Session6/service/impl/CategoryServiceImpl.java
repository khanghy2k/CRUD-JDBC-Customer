package com.ra.web.Session6.service.impl;

import com.ra.web.Session6.model.Category;
import com.ra.web.Session6.service.CategoryService;
import com.ra.web.Session6.util.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAll() {
        List<Category> data = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // mở kết nối
            connection = MySqlConnection.open();
            // tạo câu truy vấn
            preparedStatement = connection.prepareStatement("SELECT * from categories");
            // thực thi
            ResultSet resultSet = preparedStatement.executeQuery();
            // Xử lý lấy dữ liệu trong result set
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getString("id"));
                category.setName(resultSet.getString("name"));
                category.setParentId(resultSet.getString("parentId"));
                category.setStatus(resultSet.getBoolean("status"));
                data.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // đóng kết nối
            try {
                preparedStatement.close();
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
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE name LIKE ?")) {

            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        PreparedStatement preparedStatement = null;
        try {
            // mở kết nối
            connection = MySqlConnection.open();
            // tạo câu truy vấn
            assert connection != null;
            preparedStatement = connection.prepareStatement("INSERT INTO categories VALUES (?,?,?,?)");
            //truyền tham số
            preparedStatement.setString(1, category.getId());
            preparedStatement.setString(2,category.getName());
            preparedStatement.setString(3, category.getParentId());
            preparedStatement.setBoolean(4, category.isStatus());
            // thực thi
            int result = preparedStatement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // đóng kết nối
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Category findId(String id) {
        return null;
    }

    @Override
    public void edit(Category category) {

    }

    @Override
    public void delete(String id) {

    }
}



