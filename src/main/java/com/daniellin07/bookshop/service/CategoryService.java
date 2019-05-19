package com.daniellin07.bookshop.service;

import com.daniellin07.bookshop.entity.Category;

import java.util.List;
import java.util.Map;


public interface CategoryService {

    List<Category> list();
    Category get(int id);
    void update(Category category);
    void delete(int id);
    int count();

    //获取一个Key为CategoryId，Value为CategoryName的Map
    Map<Integer,String> listByMap();

}
