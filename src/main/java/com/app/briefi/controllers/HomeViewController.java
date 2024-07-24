package com.app.briefi.controllers;

import com.app.briefi.models.Category;
import com.app.briefi.models.Products;
import com.app.briefi.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    @FXML
    private AreaChart<String, Number> purchasingAreaChart;
    @FXML
    private PieChart bankPieChart;
    @FXML
    private PieChart categoriesPieChart;
    @FXML
    private Label chartCaptionLabel;
    @FXML
    private Text categoriesCountText;
    @FXML
    private Text productsCountText;
    @FXML
    private Text usersCounText;

    private void initChart () {
        XYChart.Series<String, Number> series1 = new
                XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>("Jan", 23));
        series1.getData().add(new XYChart.Data<>("Fév", 14));
        series1.getData().add(new XYChart.Data<>("Mar", 15));
        series1.getData().add(new XYChart.Data<>("Avr", 24));
        series1.getData().add(new XYChart.Data<>("Mai", 34));
        series1.getData().add(new XYChart.Data<>("Juin", 36));
        series1.getData().add(new XYChart.Data<>("Juil", 23));
        series1.getData().add(new XYChart.Data<>("Aout", 14));
        series1.getData().add(new XYChart.Data<>("Sept", 15));
        series1.getData().add(new XYChart.Data<>("Oct", 24));
        series1.getData().add(new XYChart.Data<>("Nov", 34));
        series1.getData().add(new XYChart.Data<>("Déc", 36));

        purchasingAreaChart.getData().add(series1);
        ObservableList<PieChart.Data> bankPieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10));

        bankPieChart.getData().addAll(bankPieChartData);

        bankPieChart.setLabelsVisible(true);

        chartCaptionLabel.setTextFill(Color.WHITE);
        chartCaptionLabel.setStyle("-fx-font: 8 arial;");

        for (final PieChart.Data data :
                categoriesPieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    e -> {
                        chartCaptionLabel.setTranslateX(e.getSceneX());
                        chartCaptionLabel.setTranslateY(e.getSceneY());
                        chartCaptionLabel.setText(data.getPieValue() + "%");

                    }
            );
        }

        for (final PieChart.Data data : bankPieChart.getData()) {

            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    e -> {

                        chartCaptionLabel.setTranslateX(e.getSceneX());
                        chartCaptionLabel.setTranslateY(e.getSceneY());
                        chartCaptionLabel.setText(data.getPieValue() + "%");

                    });

        }
    }

    private void initCardStats() throws SQLException, IOException {
        Category category = new Category();
        categoriesCountText.setText(String.valueOf(category.list().size()));

        User users = new User();
        usersCounText.setText(String.valueOf(users.list().size()));

        Products products = new Products();

        productsCountText.setText(String.valueOf(products.list().size()));

    }
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            initCardStats();
        }
        catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        initChart();
    }
}
