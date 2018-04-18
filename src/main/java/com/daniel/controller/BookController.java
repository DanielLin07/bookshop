package com.daniel.controller;

import com.alibaba.fastjson.JSONObject;
import com.daniel.pojo.Book;
import com.daniel.pojo.BookImage;
import com.daniel.pojo.Category;
import com.daniel.pojo.User;
import com.daniel.service.BookImageService;
import com.daniel.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookImageService bookImageService;

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
            if (file != null) {
                BookImage bookImage = bookImageService.getByBookId(book.getId());
                bookImage.setBook(book);
                bookImageService.update(bookImage);
                String imageName = bookImage.getId() + ".jpg";
                String imagePath = request.getServletContext().getRealPath("/img/book-list/article/");
                File filePath = new File(imagePath, imageName);
                if (!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdir();
                }else if (filePath.exists()){
                    filePath.delete();
                }
                file.transferTo(new File(imagePath + File.separator + imageName));
                obj.put("img",bookImage.getId());
            }
            obj.put("msg","1");
            out.print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("deleteBook.do")
    public void deleteBook(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "bookIds") String[] bookIds){
        for (String bookId : bookIds) {
            int id = Integer.parseInt(bookId);

            String imageName = bookImageService.getByBookId(id).getId() + ".jpg";
            String imagePath = request.getServletContext().getRealPath("/img/book-list/article/");
            File filePath = new File(imagePath, imageName);
            if (filePath.exists()){
                filePath.delete();
            }

            bookImageService.deleteByBookId(id);
            bookService.delete(id);

        }

    }
}
