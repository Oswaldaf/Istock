package com.app.briefi.interfaces;

import com.app.briefi.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserInterface {
    List<User> list() throws SQLException;
}
