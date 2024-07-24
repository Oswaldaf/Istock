package com.app.briefi.controllers;

import com.app.briefi.HelloApplication;
import com.app.briefi.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AuthController {
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField usernameTextField;

    @FXML
    private void onRegister() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("register.fxml")));
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void onSignIn() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("hello-view.fxml")));
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    private void onSubmitRegister() throws IOException, SQLException {
        String username = usernameTextField.getText().trim();
        String password = passwordPasswordField.getText().trim();

        if (!username.isEmpty() && !password.isEmpty()) {

            User users = new User();
            users.setUsername(username);
            users.setPassword(password);

            users.register(users);

            Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("hello-view.fxml")));

            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void onSubmitSignIn() throws IOException, SQLException {
        String username = usernameTextField.getText().trim();
        String password = passwordPasswordField.getText().trim();
        if (!username.isEmpty() && !password.isEmpty()) {
            User users = new User();
            users.setUsername(username);
            users.setPassword(password);
            if (users.signIn(users)) {
                Stage stage = (Stage) usernameTextField.getScene().getWindow();

                Stage newStage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("home.fxml")));
                Image icon = new
                        Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/image/groupe.png")));

                newStage.setTitle("Team Codeurs");
                newStage.getIcons().add(icon);
                newStage.setScene(new Scene(root));
                newStage.show();

                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Echec d'authentification");
                alert.setHeaderText("Nom d'utilisateur ou mot de passe incorrect (s)");
                alert.setContentText("Nous ne parvenons pas à vous connecter à votre session avec ces identifians. vérifiez-les puis réessayez.");
                alert.show();
            }
        }
    }
}
