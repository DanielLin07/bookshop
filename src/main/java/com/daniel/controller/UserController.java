package com.daniel.controller;

import com.daniel.pojo.User;
import com.daniel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView login(HttpServletRequest request) {
        if (request.getSession().getAttribute("user")!=null){
            return new ForeController().goHome(request);
        }
        return new ModelAndView("login");
    }

    @ResponseBody
    /*@RequestMapping(value = "/user",method = RequestMethod.GET)*/
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
}
