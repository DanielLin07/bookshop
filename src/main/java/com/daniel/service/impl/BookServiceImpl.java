package com.daniel.service.impl;

import com.daniel.dao.BookDAO;
import com.daniel.dao.BookImageDAO;
import com.daniel.dao.CategoryDAO;
import com.daniel.pojo.Book;
import com.daniel.pojo.Category;
import com.daniel.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private BookImageDAO bookImageDAO;

    @Override
    public Map<Category,List<Book>> listBookByCategory() {
        List<Category> categories = categoryDAO.list();
        Map<Category,List<Book>> booksMap = new LinkedHashMap<>();
        for (Category category : categories) {
            List<Book> books = bookDAO.getListByCategoryId(0,5,1,category.getId());
            if (books.size() > 0) {
                for (Book book : books) {
                    book.setBookImage(bookImageDAO.getByBookId(book.getId()));
                }
            }
            booksMap.put(category,books);
        }
        return booksMap;
    }

    @Override
    public List<Book> listBookByUserId(int uid,int bookType) {
        List<Book> books = bookDAO.getListByUserId(uid,bookType);
        for (Book book : books) {
            book.setBookImage(bookImageDAO.getByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public List<Book> listBookByCategoryId(int bookType,int cid) {
        List<Book> books = bookDAO.getListByCategoryId(-1,-1,bookType,cid);
        for (Book book : books) {
            book.setBookImage(bookImageDAO.getByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public Book get(int id) {
        return bookDAO.get(id);
    }

    @Override
    public int getUserId(int id) {
        return bookDAO.getUserId(id);
    }

    @Override
    public void add(Book book) {
        bookDAO.add(book);
    }

    @Override
    public int count() {
        return bookDAO.count();
    }

    @Override
    public List<Book> list() {
        List<Book> books = bookDAO.list();
        for (Book book : books) {
            book.setBookImage(bookImageDAO.getByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public List<Book> listByBookType(int bookType) {
        List<Book> books = bookDAO.listByBookType(bookType);
        for (Book book : books) {
            book.setBookImage(bookImageDAO.getByBookId(book.getId()));
        }
        return books;
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

}
