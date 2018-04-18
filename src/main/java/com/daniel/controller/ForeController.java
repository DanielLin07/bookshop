package com.daniel.controller;

import com.alibaba.fastjson.JSONObject;
import com.daniel.pojo.*;
import com.daniel.service.BookImageService;
import com.daniel.service.BookService;
import com.daniel.service.CategoryService;
import com.daniel.service.UserService;
import com.daniel.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class ForeController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookImageService bookImageService;



    @RequestMapping("home.do")
    public ModelAndView goHome(HttpServletRequest request) {
        ModelAndView mav = UserUtils.setUser(request,"home");
        if (mav.getViewName().equals("login")) return mav;
        Map<Integer, String> categories = categoryService.listByMap();
        Map<Category,List<Book>> booksMap = bookService.listBookByCategory();
        mav.addObject("categories",categories);
        mav.addObject("booksMap",booksMap);
        return mav;
    }

    @RequestMapping(value = "/book/{id}",method = RequestMethod.GET)
    public ModelAndView getBookDetail(Book book,HttpServletRequest request) {
        ModelAndView mav = UserUtils.setUser(request,"bookDetail");
        if (mav.getViewName().equals("login")) return mav;
        Book curBook = bookService.get(book.getId());
        curBook.setBookImage(bookImageService.getByBookId(book.getId()));
        curBook.setUser(userService.get(bookService.getUserId(book.getId())));
        mav.addObject("book",curBook);
        return mav;
    }

    @RequestMapping("myBookshelf.do")
    public ModelAndView goMyBookshelf(HttpServletRequest request) {
        ModelAndView mav = UserUtils.setUser(request,"myBookshelf");
        User user = (User) request.getSession().getAttribute("user");
        if (mav.getViewName().equals("login")) return mav;
        List<Book> books = bookService.listBookByUserId(user.getId(),1);
        List<Book> askBooks = bookService.listBookByUserId(user.getId(),0);
        mav.addObject("books",books);
        mav.addObject("askBooks",askBooks);
        return mav;
    }

    @RequestMapping("goUpload.do")
    public ModelAndView upload(HttpServletRequest request,Book book){
        String path = book.getBookType()==1?"uploadSell":"uploadAsk";
        ModelAndView mav = UserUtils.setUser(request,path);
        if (mav.getViewName().equals("login")) return mav;
        Map<Integer,String> categories = categoryService.listByMap();
        mav.addObject("categories",categories);
        return mav;
    }

    @RequestMapping("goEditBook.do")
    public ModelAndView goEditBook(HttpServletRequest request,Book book){
        ModelAndView mav = UserUtils.setUser(request,"editBook");
        if (mav.getViewName().equals("login")) return mav;
        Book curBook = bookService.get(book.getId());
        if (curBook != null){
            curBook.setBookImage(bookImageService.getByBookId(book.getId()));
        }
        mav.addObject("book",curBook);
        Map<Integer,String> categories = categoryService.listByMap();
        mav.addObject("categories",categories);
        return mav;
    }

    @RequestMapping("goBookStore.do")
    public ModelAndView goBookStore(HttpServletRequest request,Page page,Category category){
        ModelAndView mav = UserUtils.setUser(request,"bookStore");
        Map<Integer, String> categories = categoryService.listByMap();
        Category curCategory = category.getId() !=0?categoryService.get(category.getId()):new Category();
        String categoryName = curCategory.getName() == null?"所有二手书":curCategory.getName();
        if (mav.getViewName().equals("login")) return mav;
        int total = bookService.count();
        page.calculateEnd(total);
        if (page.getStart() < 0) {
            page.setStart(0);
        }else if (page.getStart() > total){
            page.setEnd(page.getEnd());
        }
        PageHelper.offsetPage(page.getStart(),16);
        List<Book> books = curCategory.getId() == 0?bookService.listByBookType(1):bookService.listBookByCategoryId(1,curCategory.getId());
        mav.addObject("categoryName",categoryName);
        mav.addObject("books",books);
        mav.addObject("categories",categories);
        return mav;
    }

    @RequestMapping("goAskBookStore.do")
    public ModelAndView goAskBookStore(HttpServletRequest request,Page page){
        ModelAndView mav = UserUtils.setUser(request,"askBookStore");
        if (mav.getViewName().equals("login")) return mav;
        int total = bookService.count();
        page.calculateEnd(total);
        if (page.getStart() < 0) {
            page.setStart(0);
        }else if (page.getStart() > total){
            page.setEnd(page.getEnd());
        }
        PageHelper.offsetPage(page.getStart(),16);
        List<Book> books = bookService.listByBookType(0);
        mav.addObject("books",books);
        return mav;
    }

}
