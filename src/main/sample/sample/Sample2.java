package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Sample2 implements Initializable {

    @FXML
    Tab tab1;

    @FXML
    Tab tab2;

    @FXML
    Tab tab3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FXMLLoader loader = new FXMLLoader();
        try {
            AnchorPane anch1 = loader.load(getClass().getResource("tab1.fxml"));
            tab1.setContent(anch1);
        } catch (IOException iex) {
            System.out.println("File not found");
        }
        loader = new FXMLLoader();
        try {
            AnchorPane anch2 = loader.load(getClass().getResource("tab2.fxml"));
            tab2.setContent(anch2);
        } catch (IOException iex) {
            System.out.println("File not found");
        }
        loader = new FXMLLoader();
        try {
            AnchorPane anch3 = loader.load(getClass().getResource("Tab3.fxml"));
            tab3.setContent(anch3);
        } catch (IOException iex) {
            System.out.println("File not found");
        }
    }
}


