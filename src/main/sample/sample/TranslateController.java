package sample;

import main.TranslateAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TranslateController {
    String languageFrom = "";
    String languageTo = "vi";
    @FXML
    private TextArea area1;
    @FXML
    private TextField area2;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;

    @FXML
    void chinese(ActionEvent event) {
        languageFrom = "zh";
        text2.setText("Tiếng Trung");
    }

    @FXML
    void eng(ActionEvent event) {
        languageFrom = "en";
        text1.setText("Tiếng Anh");
    }

    @FXML
    void eng2(ActionEvent event) {
        text2.setText("Tiếng Anh");
        languageTo = "en";
    }

    @FXML
    void korea(ActionEvent event) {
        text1.setText("Tiếng Hàn");
        languageFrom = "ko";
    }

    @FXML
    void korea2(ActionEvent event) {

        text2.setText("Tiếng Hàn");
        languageTo = "ko";
    }

    @FXML
    void rus(ActionEvent event) {

        text2.setText("Tiếng Nga");
        languageTo = "ru";
    }

    @FXML
    void vie1(ActionEvent event) {
        text1.setText("Tiếng Việt");
        languageFrom = "vi";
    }

    @FXML
    void vie2(ActionEvent event) {
        text2.setText("Tiếng Việt");
        languageTo = "vi";
    }

    @FXML
    void translate(ActionEvent event) throws IOException {
        area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
    }

    @FXML
    void speak1(ActionEvent event) throws Exception {
        main.VoiceRSS.speakWord(area1.getText());
    }

    @FXML
    void speak2(ActionEvent event) throws Exception {
        main.VoiceRSS.speakWord(area2.getText());
    }

    public void detect(ActionEvent event) {
        languageFrom = "";
    }
}
