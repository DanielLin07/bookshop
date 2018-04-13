package com.daniel.service.impl;

import com.daniel.dao.BookImageDAO;
import com.daniel.pojo.Book;
import com.daniel.pojo.BookImage;
import com.daniel.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookImageServiceImpl implements BookImageService{

    @Autowired
    private BookImageDAO bookImageDAO;

    @Override
    public BookImage getByBookId(int bid) {
        return bookImageDAO.getByBookId(bid);
    }

    @Override
    public void add(BookImage bookImage) {
        bookImageDAO.add(bookImage);
    }

    @Override
    public void deleteByBid(Book book) {
        bookImageDAO.deleteByBid(book);
    }
}
