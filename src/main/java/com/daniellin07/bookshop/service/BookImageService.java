package com.daniellin07.bookshop.service;

import com.daniellin07.bookshop.entity.BookImage;

public interface BookImageService {

    BookImage getByBookId(int bid);
    void add(BookImage bookImage);
    void update(BookImage bookImage);
    void deleteByBookId(int bid);
}
