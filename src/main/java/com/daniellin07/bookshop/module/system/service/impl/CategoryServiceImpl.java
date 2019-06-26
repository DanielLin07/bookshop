//package com.daniellin07.bookshop.module.system.service.impl;
//
//import com.daniellin07.bookshop.module.system.dao.CategoryDAO;
//import com.daniellin07.bookshop.module.system.domain.Category;
//import com.daniellin07.bookshop.module.system.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class CategoryServiceImpl implements CategoryService{
//
//    @Autowired
//    CategoryDAO categoryDAO;
//
//    @Override
//    public List<Category> list() {
//        return categoryDAO.list();
//    }
//
//    @Override
//    public Category get(int id) {
//        return categoryDAO.get(id);
//    }
//
//    @Override
//    public void update(Category category) {
//        categoryDAO.update(category);
//    }
//
//    @Override
//    public void delete(int id) {
//        categoryDAO.delete(id);
//    }
//
//    @Override
//    public int count() {
//        return categoryDAO.count();
//    }
//
//    @Override
//    public Map<Integer, String> listByMap() {
//        List<Category> categories = categoryDAO.list();
//        Map<Integer, String> categoriesMap = new HashMap<>();
//        for (Category category : categories) {
//            categoriesMap.put(category.getId(),category.getName());
//        }
//        return categoriesMap;
//    }
//}
