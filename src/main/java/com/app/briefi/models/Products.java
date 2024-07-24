package com.app.briefi.models;

import com.app.briefi.dbconfig.IDBConfig;
import com.app.briefi.interfaces.ProductInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Products implements ProductInterface {

    private int id;
    private String name;

    private String description;
    private int quantity;
    private int thresholdQuantity;
    private Category category;

    private Connection connection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThresholdQuantity() {
        return thresholdQuantity;
    }

    public void setThresholdQuantity(int thresholdQuantity) {
        this.thresholdQuantity = thresholdQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public List<Products> list() throws SQLException {
        List<Products> products = new ArrayList<>();

        connection = IDBConfig.getConnection();
        if (connection != null) {
            String req = "SELECT * FROM products";
            PreparedStatement prepareStatement = this.connection.prepareStatement(req);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()){
                Products product = new Products();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));

                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setThresholdQuantity(resultSet.getInt("thresholdQuantity"));
                products.add(product);
            }
            prepareStatement.close();
            this.connection.close();
        }
        return products;
    }

    @Override
    public void create(Products products) throws SQLException {
        connection = IDBConfig.getConnection();
        if (connection != null) {

            String req = "{CALL createProduct(?, ?, ?, ?, ?)}";

            CallableStatement callableStatement = connection.prepareCall(req);
            callableStatement.setString(1, products.getName());
            callableStatement.setString(2, products.getDescription());
            callableStatement.setInt(3, products.getQuantity());
            callableStatement.setInt(4, products.getThresholdQuantity());
            callableStatement.setInt(5, products.getCategory().getId());

            callableStatement.execute();
            callableStatement.close();
            this.connection.close();
        }
    }

    @Override
    public void update(Products products) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
