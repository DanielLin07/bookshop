package com.daniel.controller;

import com.alibaba.fastjson.JSONObject;
import com.daniel.common.Result;
import com.daniel.common.ResultGenerator;
import com.daniel.pojo.Book;
import com.daniel.pojo.BookImage;
import com.daniel.pojo.Category;
import com.daniel.pojo.User;
import com.daniel.service.BookImageService;
import com.daniel.service.BookService;
import com.daniel.service.CategoryService;
import com.daniel.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookImageService bookImageService;
    @Autowired
    private CategoryService categoryService;

    // 日志文件
    private static final Logger log = Logger.getLogger(BookController.class);

    /**
     * 书本详情页
     * @param id 图书ID
     * @return 该ID图书的详情页
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView getBookDetail(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("bookDetail");
        int intId = Integer.parseInt(id);
        Book curBook = bookService.get(intId);
        curBook.setBookImage(bookImageService.getByBookId(intId));
        curBook.setUser(userService.get(bookService.getUserId(intId)));
        mav.addObject("book",curBook);
        return mav;
    }

    /**
     * 上传图书
     * @param request 用于获取当前用户信息
     * @param book 图书实体类
     * @param file 图片文件
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result uploadSell(HttpServletRequest request,Book book,
                           @RequestParam(value = "image" , required = false) MultipartFile file){
        // 获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        try {
            if(file != null && book != null){
                // 写入图书信息
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
                log.info("request: book/upload , book: " + book.toString());
                return ResultGenerator.genSuccessResult();
            }else {
                return ResultGenerator.genFailResult("信息填写不完整或未上传图片！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("上传失败");
        }
    }

    /**
     * 更新图书的视图
     * @param id 图书的ID
     * @return 该ID的图书的更新界面
     */
    @RequestMapping(value = "/renewal/{id}",method = RequestMethod.GET)
    public ModelAndView goEditBook(@PathVariable String id){
        ModelAndView  mav = new ModelAndView("editBook");
        int bookId = Integer.parseInt(id);
        Book curBook = bookService.get(bookId);
        log.info("request: book/update , book: " + curBook.toString());
        if (curBook != null){
            curBook.setBookImage(bookImageService.getByBookId(bookId));
        }
        mav.addObject("book",curBook);
        Map<Integer,String> categories = categoryService.listByMap();
        mav.addObject("categories",categories);
        return mav;
    }

    /**
     * 根据ID获取书的Category
     * @param book
     * 应该使用GET的，但是会产生不合法URI异常，待解决
     */
    @RequestMapping(value = "/categories")
    public Result getCategory(@RequestBody Book book){
        JSONObject data = new JSONObject();
        Category category = bookService.get(book.getId()).getCategory();
        log.info("request: book/category/get , bookId: " + book.getId()+" , category:"+category.toString());
        if (category.getId() != 0){
            data.put("categoryId",category.getId());
            return ResultGenerator.genSuccessResult(data);
        }else {
            return ResultGenerator.genFailResult("无效的Category！");
        }
    }

    /**
     * 更新图书内容
     * @param request 用于获取路径
     * @param book 除图片外其他的图书信息
     * @param file 图片
     * @return
     * 应该使用PUT，可是需要上传图片，表单提交无法用PUT，待解决
     */
    @RequestMapping(value = "/renewal",method = RequestMethod.POST)
    public Result editBook(HttpServletRequest request, Book book,
                           @RequestParam(value = "image" , required = false) MultipartFile file){
        try {
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
            }
            log.info("request: book/update , book: " + book.toString());
            return ResultGenerator.genSuccessResult();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("修改失败！");
        }
    }

    /**
     * 删除一本或多本图书
     * @param request 用于获取路径，删除图片
     * @param bookIds 要删除的图书ID数组
     */
    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public Result deleteBook(HttpServletRequest request, @RequestParam(value = "bookIds", required = false) String[] bookIds){

        if (bookIds != null) {
            // 遍历每个ID
            for (String bookId : bookIds) {
                int id = Integer.parseInt(bookId);

                // 获取当前图书的图片名称与存放路径
                String imageName = bookImageService.getByBookId(id).getId() + ".jpg";
                String imagePath = request.getServletContext().getRealPath("/img/book-list/article/");
                File filePath = new File(imagePath, imageName);

                // 删除图片
                if (filePath.exists()){
                    filePath.delete();
                }

                // 删除数据库中的图书
                bookImageService.deleteByBookId(id);
                bookService.delete(id);
            }
            log.info("request: book/delete , bookIds: " + Arrays.toString(bookIds));
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("删除失败！未选中图书");
        }
    }
}
