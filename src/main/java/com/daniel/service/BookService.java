package com.daniel.service;

import com.daniel.pojo.Book;
import com.daniel.pojo.Category;

import java.util.List;
import java.util.Map;

public interface BookService {

    Map<Category,List<Book>> listBookByCategory();
    Book get(int id);
    int getUserId(int id);
    void add(Book book);
    int count();
    void delete(int id);
    List<Book> list();
    List<Book> listBookByUserId(int uid,int bookType);
    List<Book> listBookByCategoryId(int bookType,int cid);
    List<Book> listByBookType(int bookType);
    void update(Book book);
}
