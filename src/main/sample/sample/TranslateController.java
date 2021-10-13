package sample;

import main.TranslateAPI;
import main.VoiceRSS;
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
    String nameFrom;
    String speakFrom;
    String nameTo;
    String speakTo;

    @FXML
    void chinese(ActionEvent event) throws IOException {
        languageTo = "zh";
        text2.setText("Tiếng Trung");
        nameTo = "Luli";
        speakTo = "zh-cn";
        area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
    }

    @FXML
    void eng(ActionEvent event) {
        languageFrom = "en";
        text1.setText("Tiếng Anh");
        nameFrom = "Linda";
        speakFrom = "en-gb";
    }

    @FXML
    void eng2(ActionEvent event) throws IOException {
        text2.setText("Tiếng Anh");
        languageTo = "en";
        nameTo = "Linda";
        speakTo = "en-gb";
        area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
    }

    @FXML
    void korea(ActionEvent event) throws IOException {
        text1.setText("Tiếng Hàn");
        languageFrom = "ko";
        nameFrom = "Nari";
        speakFrom = "ko-kr";

    }

    @FXML
    void korea2(ActionEvent event) throws IOException {
        text2.setText("Tiếng Hàn");
        languageTo = "ko";
        nameTo = "Nari";
        speakTo = "ko-kr";
        area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
    }

    @FXML
    void rus(ActionEvent event) throws IOException {
        text2.setText("Tiếng Nga");
        languageTo = "ru";
        nameTo = "Marina";
        speakTo = "ru-ru";
        area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
    }

    @FXML
    void vie1(ActionEvent event) {
        text1.setText("Tiếng Việt");
        languageFrom = "vi";
        nameFrom = "Chi";
        speakFrom = "vi-vn";
    }

    @FXML
    void vie2(ActionEvent event) throws IOException {
        text2.setText("Tiếng Việt");
        languageTo = "vi";
        nameTo = "Chi";
        speakTo = "vi-vn";
        area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
    }

    @FXML
    void translate(ActionEvent event) throws IOException {
        area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));

    }

    @FXML
    void speak1(ActionEvent event) throws Exception {
        VoiceRSS.Name = nameFrom;
        VoiceRSS.language = speakFrom;
        VoiceRSS.speakWord(area1.getText());

    }

    @FXML
    void speak2(ActionEvent event) throws Exception {

        VoiceRSS.Name = nameTo;
        VoiceRSS.language = speakTo;
        VoiceRSS.speakWord(area2.getText());
    }

    public void detect(ActionEvent event) {
        languageFrom = "";
    }



}
