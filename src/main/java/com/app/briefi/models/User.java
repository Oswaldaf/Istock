package com.app.briefi.models;

import com.app.briefi.dbconfig.IDBConfig;
import com.app.briefi.interfaces.AuthInterface;
import com.app.briefi.interfaces.UserInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements AuthInterface, UserInterface {

    private int id;
    private String username;

    private String password;

    private Connection connection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<User> list() throws SQLException {
        List<User> users = new ArrayList<>();
        connection = IDBConfig.getConnection();
        if (connection != null) {
            String req = "SELECT * FROM users";
            PreparedStatement prepareStatement = this.connection.prepareStatement(req);

            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }

            prepareStatement.close();
            this.connection.close();
        }
        return users;
    }
    @Override
    public void register(User users) throws SQLException {
        connection = IDBConfig.getConnection();
        if (connection != null) {
            String req = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(req);
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getPassword());

            int row = preparedStatement.executeUpdate();

            System.out.printf(String.valueOf(row));

            preparedStatement.close();
            this.connection.close();
        }
    }

    @Override
    public boolean signIn(User users) throws SQLException {
        connection = IDBConfig.getConnection();
        int rows = 0;
        if (connection != null) {
            String req = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(req);
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                rows++;
            }

            preparedStatement.close();
            this.connection.close();
        }

        return rows > 0;
    }

}
