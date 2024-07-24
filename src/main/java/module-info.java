module com.app.briefi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.app.briefi to javafx.fxml;
    exports com.app.briefi;


    opens com.app.briefi.models to javafx.base;
    exports com.app.briefi.models;

    opens com.app.briefi.controllers to javafx.fxml;
    exports com.app.briefi.controllers;
}