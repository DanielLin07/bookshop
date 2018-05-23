package com.daniel.dao;

import java.util.List;
import java.util.Map;

import com.daniel.pojo.Category;

public interface CategoryDAO {

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);

    // 获取所有Category
    List<Category> list();

    int count();

}
