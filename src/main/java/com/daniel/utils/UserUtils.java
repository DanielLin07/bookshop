package com.daniel.utils;

import com.daniel.controller.LoginController;
import com.daniel.pojo.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    public static ModelAndView setUser(HttpServletRequest request, String viewName){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return new LoginController().login(request);
        }else {
            ModelAndView mav = new ModelAndView(viewName);
            mav.addObject("user",user);
            return mav;
        }
    }
}
