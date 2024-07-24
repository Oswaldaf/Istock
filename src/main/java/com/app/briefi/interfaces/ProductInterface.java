package com.app.briefi.interfaces;

import com.app.briefi.models.Products;

import java.sql.SQLException;
import java.util.List;

public interface ProductInterface {
    List<Products> list ()  throws SQLException;
    void create (Products products) throws SQLException;
    void update(Products products) throws SQLException;
    void delete(int id) throws SQLException;
}
