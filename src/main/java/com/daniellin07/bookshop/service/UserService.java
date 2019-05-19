package com.daniellin07.bookshop.service;

import com.daniellin07.bookshop.entity.User;

public interface UserService {

    boolean checkUser(User user);
    User get(int id);
    User getByStudentid(String studnetid);

}
