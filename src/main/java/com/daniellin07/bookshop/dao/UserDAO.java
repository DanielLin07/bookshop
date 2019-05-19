package com.daniellin07.bookshop.dao;

import com.daniellin07.bookshop.entity.User;

public interface UserDAO {

    User get(int id);

    void update(User user);

    int checkPassword(User user);

    User getByStudentid(String studentid);

}
