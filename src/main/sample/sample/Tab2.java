package sample;

import dictionary.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Tab2 implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField WordSearching;

    @FXML
    void remove(ActionEvent event) {


        String wordSearching = WordSearching.getText();

        if (wordSearching.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập từ!");
            alert.showAndWait();
            return;
        }

        if (DictionaryManagement.checklookup(wordSearching)) {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Cảnh báo");
            alert2.setHeaderText(null);
            alert2.setContentText("Từ bạn nhập chưa có trong từ điển!");
            alert2.showAndWait();
            return;
        }
        DictionaryManagement.removeWord(wordSearching);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Xóa từ thành công");
        alert.showAndWait();
    }
}