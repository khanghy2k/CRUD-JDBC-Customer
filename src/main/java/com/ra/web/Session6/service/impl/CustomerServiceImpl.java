package com.ra.web.Session6.service.impl;

import com.ra.web.Session6.model.Customer;
import com.ra.web.Session6.service.CustomerService;
import com.ra.web.Session6.util.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> findAll() {
        List<Customer> data = new ArrayList<>();
        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = MySqlConnection.open();
            assert connection != null;
            cs = connection.prepareCall("CALL sp_customers_select()");
            ResultSet resultSet = cs.executeQuery();
            while (resultSet.next()) {
                Customer c = new Customer();
                c.setId(resultSet.getString("id"));
                c.setName(resultSet.getString("name"));
                c.setAge(resultSet.getInt("age"));
                c.setBirthday(resultSet.getTimestamp("birthday"));
                c.setAvatar(resultSet.getString("avatar"));
                data.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                assert cs != null;
                cs.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public Customer findId(String id) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnection.open();
            cs = conn.prepareCall("CALL sp_customers_select_byId(?)");
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setBirthday(rs.getTimestamp("birthday"));
                c.setAvatar(rs.getString("avatar"));
                return c;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Customer c) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnection.open();
            cs = conn.prepareCall("CALL sp_customers_select_insert(?,?,?,?,?)");
            cs.registerOutParameter(1, Types.VARCHAR, 36);
            cs.setString(2, c.getName());
            cs.setInt(3, c.getAge());
            cs.setTimestamp(4, c.getBirthday());
            cs.setString(5, c.getAvatar());
            int result = cs.executeUpdate();
            System.out.println("customerId: " + cs.getString(1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void edit(Customer c) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnection.open();
            cs = conn.prepareCall("CALL sp_customers_select_update(?,?,?,?,?)");
            cs.setString(1, c.getId());
            cs.setString(2, c.getName());
            cs.setInt(3, c.getAge());
            cs.setTimestamp(4, c.getBirthday());
            cs.setString(5, c.getAvatar());
            int result = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void delete(String id) {
        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = MySqlConnection.open();
            cs = connection.prepareCall("CALL  sp_customers_select_delete(?)");
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

}
