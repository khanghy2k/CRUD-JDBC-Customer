package com.ra.web.Session6.service;

import com.ra.web.Session6.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findId(String id);
    void add(Customer c);
    void edit(Customer c);
    void delete(String id);

}
