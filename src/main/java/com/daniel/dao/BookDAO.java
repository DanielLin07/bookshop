package com.daniel.dao;

import com.daniel.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDAO {

    List<Book> getListByCategoryId(@Param("start") int start, @Param("count") int count, @Param("bookType") int bookType, @Param("cid") int cid);

    int getUserId(int id);

    Book get(int id);

    void add(Book book);

    void update(Book book);

    int count();

    void delete(int id);

    List<Book> list();

    List<Book> listByBookType(int bookType);

    List<Book> getListByUserId(@Param("uid") int uid, @Param("bookType") int bookType);
}
