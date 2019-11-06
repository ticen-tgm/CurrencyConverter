package controller;

import data.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Arrays;

public class Controller extends Application {
    @FXML
    TextField amountTextField;
    @FXML
    TextField currencyTextField;
    @FXML
    TextField targetCurrencyTextField;
    @FXML
    CheckBox liveDataCheckBox;
    @FXML
    Button convertButton;
    @FXML
    Button resetButton;
    @FXML
    Button exitButton;
    @FXML
    WebView webView;
    WebEngine webEngine;
    Data strategy = new OnlineData();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/sample.fxml"));
        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

   @FXML
   public void initialize(){
        webEngine=webView.getEngine();
   }

    public void convert(ActionEvent actionEvent) {
        webEngine.loadContent(
                strategy.get_rates(
                        amountTextField.getText(),
                        currencyTextField.getText().toUpperCase(),
                        Arrays.asList(targetCurrencyTextField.getText().toUpperCase().split(","))
                )
        );
    }

    public void liveDataChanger(ActionEvent actionEvent) {
        if(liveDataCheckBox.isSelected()) strategy= new OnlineData();
        //else if(!liveDataCheckBox.isSelected()) strategy = new OfflineData();
    }

    public void exit(ActionEvent actionEvent) {
    }

    public void reset(ActionEvent actionEvent) {
        currencyTextField.clear();
        targetCurrencyTextField.clear();
        amountTextField.clear();
        webEngine.load("about:blank");
    }
}
