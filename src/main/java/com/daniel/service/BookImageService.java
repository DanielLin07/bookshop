package com.daniel.service;

import com.daniel.pojo.Book;
import com.daniel.pojo.BookImage;

public interface BookImageService {

    BookImage getByBookId(int bid);
    void add(BookImage bookImage);
    void update(BookImage bookImage);
    void deleteByBookId(int bid);
}
