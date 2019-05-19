package com.daniellin07.bookshop.dao;

import java.util.List;

import com.daniellin07.bookshop.entity.Category;

public interface CategoryDAO {

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);

    // 获取所有Category
    List<Category> list();

    int count();

}
