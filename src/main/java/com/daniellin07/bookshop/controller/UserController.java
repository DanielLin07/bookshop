package com.daniellin07.bookshop.controller;

import com.daniellin07.bookshop.common.Result;
import com.daniellin07.bookshop.common.ResultGenerator;
import com.daniellin07.bookshop.entity.User;
import com.daniellin07.bookshop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 日志文件
    private static final Logger log = Logger.getLogger(UserController.class);

    @RequestMapping("")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 验证登录
     *
     * @param user    用户输入的学号与密码封装成的User对象
     * @param request 登录成功时将user存入session当中
     * @return 登录成功后跳转至首页
     */
    @RequestMapping(value = "/sessions", method = RequestMethod.POST)
    @ResponseBody
    public Result checkLogin(@RequestBody User user, HttpServletRequest request) {
        // userService验证是否登录成功
        boolean flag = userService.checkUser(user);
        log.info("request: user/login , user: " + user.toString());
        if (flag) {
            Map data = new HashMap();
            data.put("currentUser", user);
            // 登录成功，将登录信息放入session
            request.getSession().setAttribute("user", userService.getByStudentid(user.getStudentid()));
            return ResultGenerator.genSuccessResult(data);
        } else {
            return ResultGenerator.genFailResult("学号或密码输入错误！");
        }
    }

    /**
     * 登出操作
     *
     * @param request 用于获取session中的User对象
     * @return 登出后跳转至登录界面
     */
    @RequestMapping(value = "/sessions", method = RequestMethod.DELETE)
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return ResultGenerator.genSuccessResult();
    }
}
