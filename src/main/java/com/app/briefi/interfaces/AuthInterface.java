package com.app.briefi.interfaces;

import com.app.briefi.models.User;

import java.sql.SQLException;

public interface AuthInterface{
    void register(User users) throws SQLException;
    boolean signIn(User users) throws SQLException;
}
