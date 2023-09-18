package com.ra.web.Session6.service;

import com.ra.web.Session6.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findbyName(String name);
    void add(Category category);
    Category findId(String id);
    void edit(Category category);
    void delete(String id);
}
