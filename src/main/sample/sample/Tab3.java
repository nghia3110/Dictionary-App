package sample;

import dictionary.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Tab3 {
    @FXML
    private TextField WordSearching;
    @FXML
    private TextField WordMeaning;

    public void Save(ActionEvent actionEvent) {
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

        if (DictionaryManagement.checklookup(wordSearching)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Từ bạn nhập chưa có trong từ điển!");
            alert.showAndWait();
            return;
        }

        Controller.DicM.modifyWord(wordSearching, wordMeaning);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Sửa từ thành công!");
        alert.showAndWait();
    }
}

