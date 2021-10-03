package sample;

import dictionary.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Tab1 implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField WordSearching;
    @FXML
    private TextField WordMeaning;


    public void Submit(ActionEvent actionEvent) {
        String wordSearching = WordSearching.getText();
        String wordMeaning = WordMeaning.getText();


        if (wordSearching.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập từ (Eng)!");
            alert.showAndWait();
            return;
        }
        if (!(DictionaryManagement.checklookup(wordSearching) != false)) {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Cảnh báo");
            alert2.setHeaderText(null);
            alert2.setContentText("Từ bạn nhập  có trong từ điển!");
            alert2.showAndWait();
            return;
        }
        if (wordMeaning.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập nghĩa (Vie)!");
            alert.showAndWait();
            return;
        }
        DictionaryManagement.addWord(wordSearching, wordMeaning);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cảnh báo");
        alert.setHeaderText(null);
        alert.setContentText("Thêm từ thành công!");
        alert.showAndWait();
    }


}

