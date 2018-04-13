package com.daniel.controller;

import com.alibaba.fastjson.JSONObject;
import com.daniel.pojo.*;
import com.daniel.service.BookImageService;
import com.daniel.service.BookService;
import com.daniel.service.CategoryService;
import com.daniel.service.UserService;
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

    @RequestMapping("login.do")
    public ModelAndView login(HttpServletRequest request) {
        if (request.getSession().getAttribute("user")!=null){
            return goHome(request);
        }
        return new ModelAndView("login");
    }

    @ResponseBody
    @RequestMapping("checkLogin.do")
    public String checkLogin(@RequestBody User user, HttpServletRequest request) {
        boolean flag = userService.checkUser(user);
        String str;
        if (flag) {
            str = "0";
            request.getSession().setAttribute("user",userService.getByStudentid(user.getStudentid()));
        }else {
            str = "1";
        }
        return str;
    }

    @RequestMapping("logout.do")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new ModelAndView("login");
    }

    @RequestMapping("home.do")
    public ModelAndView goHome(HttpServletRequest request) {
        ModelAndView mav = setUser(request,"home");
        if (mav.getViewName().equals("login")) return mav;
        Map<Integer, String> categories = categoryService.listByMap();
        Map<Category,List<Book>> booksMap = bookService.listBookByCategory();
        mav.addObject("categories",categories);
        mav.addObject("booksMap",booksMap);
        return mav;
    }

    @RequestMapping("bookDetail.do")
    public ModelAndView getBookDetail(Book book,HttpServletRequest request) {
        ModelAndView mav = setUser(request,"bookDetail");
        if (mav.getViewName().equals("login")) return mav;
        Book curBook = bookService.get(book.getId());
        curBook.setBookImage(bookImageService.getByBookId(book.getId()));
        curBook.setUser(userService.get(bookService.getUserId(book.getId())));
        mav.addObject("book",curBook);
        return mav;
    }

    @RequestMapping("myBookShelf.do")
    public ModelAndView goMyBookShelf(HttpServletRequest request) {
        ModelAndView mav = setUser(request,"myBookShelf");
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
        ModelAndView mav = setUser(request,path);
        if (mav.getViewName().equals("login")) return mav;
        Map<Integer,String> categories = categoryService.listByMap();
        mav.addObject("categories",categories);
        return mav;
    }


    @RequestMapping("upload.do")
    public void uploadSell(HttpServletRequest request, HttpServletResponse response, Book book,
                           @RequestParam(value = "image" , required = false) MultipartFile file){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("bookType:"+book.getBookType());
        JSONObject obj = new JSONObject();
        PrintWriter out = null;
        String status;
        try {
            out = response.getWriter();
            if(file != null && book != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long time = System.currentTimeMillis();
                String timeStr= sdf.format(time);
                book.setDate(timeStr);
                book.setUser(user);
                bookService.add(book);
                BookImage bookImage = new BookImage();
                bookImage.setBook(book);
                bookImageService.add(bookImage);
                String imageName = bookImage.getId()+".jpg";
                String imagePath = request.getServletContext().getRealPath("/img/book-list/article/");
                File filePath = new File(imagePath,imageName);
                if (!filePath.getParentFile().exists()){
                    filePath.getParentFile().mkdir();
                }
                file.transferTo(new File(imagePath + File.separator + imageName));
                status = "1";
                obj.put("msg",status);
            }else {
                status = "0";
                obj.put("msg",status);
            }
            out.print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @RequestMapping("goEditBook.do")
    public ModelAndView goEditBook(HttpServletRequest request,Book book){
        ModelAndView mav = setUser(request,"editBook");
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

    @RequestMapping("getCategory.do")
    public void getCategory(HttpServletResponse response, @RequestBody Book book) throws IOException {
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        Category category = bookService.get(book.getId()).getCategory();
        System.out.println("categoryId:"+category.getId()+"\t"+"categoryName:"+category.getName());
        obj.put("msg",category.getId());
        out.print(obj);
    }

    @RequestMapping("editBook.do")
    public void editBook(HttpServletRequest request, HttpServletResponse response, Book book,
                         @RequestParam(value = "image" , required = false) MultipartFile file){
        PrintWriter out = null;
        try {
            out = response.getWriter();
            JSONObject obj = new JSONObject();
            bookService.update(book);
            BookImage bookImage = new BookImage();
            bookImage.setBook(book);
            bookImageService.deleteByBid(book);
            bookImageService.add(bookImage);
            String imageName = bookImage.getId()+".jpg";
            String imagePath = request.getServletContext().getRealPath("/img/book-list/article/");
            File filePath = new File(imagePath,imageName);
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdir();
            }
            file.transferTo(new File(imagePath + File.separator + imageName));
            obj.put("msg","1");
            out.print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("deleteBook.do")
    public void deleteBook(HttpServletResponse response,Book book){

    }

    @RequestMapping("goBookStore.do")
    public ModelAndView goBookStore(HttpServletRequest request,Page page,Category category){
        ModelAndView mav = setUser(request,"bookStore");
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
        ModelAndView mav = setUser(request,"askBookStore");
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

    private ModelAndView setUser(HttpServletRequest request,String viewName){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return login(request);
        }else {
            ModelAndView mav = new ModelAndView(viewName);
            mav.addObject("user",user);
            return mav;
        }
    }

}
