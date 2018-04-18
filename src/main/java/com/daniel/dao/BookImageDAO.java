package com.daniel.dao;

import com.daniel.pojo.Book;
import com.daniel.pojo.BookImage;
import org.apache.ibatis.annotations.Param;

public interface BookImageDAO {

    /*BookImage getByBookId(@Param("start") int start, @Param("count") int count, @Param("cid") int cid);*/
    BookImage getByBookId(int bid);
    void add(BookImage bookImage);
    void update(BookImage bookImage);
    void deleteByBookId(int bid);
}
