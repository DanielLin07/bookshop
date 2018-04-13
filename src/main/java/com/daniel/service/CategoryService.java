package com.daniel.service;

import java.util.List;
import java.util.Map;

import com.daniel.pojo.Category;

public interface CategoryService {

    List<Category> list();
    Category get(int id);
    void update(Category category);
    void delete(int id);
    int count();
    Map<Integer,String> listByMap();

}
