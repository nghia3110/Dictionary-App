package main;

import base.TranslateAPI;import base.VoiceRSS;import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    String languageFrom = "";
    String languageTo = "vi";
    String nameFrom;
    String speakFrom;
    String nameTo;
    String speakTo;

    @FXML
    private TextArea area1;
    @FXML
    private TextField area2;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;

    @FXML
    private Button langFromFirst;
    @FXML
    private Button langFromSecond;
    @FXML
    private Button langFromThird;
    @FXML
    private Button langFromFourth;

    @FXML
    private Button langToFirst;
    @FXML
    private Button langToSecond;
    @FXML
    private Button langToThird;
    @FXML
    private Button langToFourth;
    @FXML
    private Button langToFifth;

    public void resetStyleLangFrom() {
        langFromFirst.getStyleClass().removeAll("active");
        langFromSecond.getStyleClass().removeAll("active");
        langFromThird.getStyleClass().removeAll("active");
        langFromFourth.getStyleClass().removeAll("active");
    }

    public void detect() {
        resetStyleLangFrom();
        langFromFirst.getStyleClass().add("active");
        languageFrom = "";
        text1.setText("Phát hiện n.ngữ");
        nameFrom = "Linda";
        speakFrom = "en-gb";
    }

    @FXML
    void eng() {
        resetStyleLangFrom();
        langFromSecond.getStyleClass().add("active");
        languageFrom = "en";
        text1.setText("Tiếng Anh");
        nameFrom = "Linda";
        speakFrom = "en-gb";
    }

    @FXML
    void vie1() {
        resetStyleLangFrom();
        langFromThird.getStyleClass().add("active");
        text1.setText("Tiếng Việt");
        languageFrom = "vi";
        nameFrom = "Chi";
        speakFrom = "vi-vn";
    }

    @FXML
    void korea() {
        resetStyleLangFrom();
        langFromFourth.getStyleClass().add("active");
        text1.setText("Tiếng Hàn");
        languageFrom = "ko";
        nameFrom = "Nari";
        speakFrom = "ko-kr";
    }

    public void resetStyleLangTo() {
        langToFirst.getStyleClass().removeAll("active");
        langToSecond.getStyleClass().removeAll("active");
        langToThird.getStyleClass().removeAll("active");
        langToFourth.getStyleClass().removeAll("active");
        langToFifth.getStyleClass().removeAll("active");
    }

    @FXML
    void vie2() throws IOException {
        resetStyleLangTo();
        langToFirst.getStyleClass().add("active");
        text2.setText("Tiếng Việt");
        languageTo = "vi";
        nameTo = "Chi";
        speakTo = "vi-vn";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
        }
    }

    @FXML
    void eng2() throws IOException {
        resetStyleLangTo();
        langToSecond.getStyleClass().add("active");
        text2.setText("Tiếng Anh");
        languageTo = "en";
        nameTo = "Linda";
        speakTo = "en-gb";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
        }
    }

    @FXML
    void korea2() throws IOException {
        resetStyleLangTo();
        langToThird.getStyleClass().add("active");
        text2.setText("Tiếng Hàn");
        languageTo = "ko";
        nameTo = "Nari";
        speakTo = "ko-kr";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
        }
    }

    @FXML
    void rus() throws IOException {
        resetStyleLangTo();
        langToFourth.getStyleClass().add("active");
        text2.setText("Tiếng Nga");
        languageTo = "ru";
        nameTo = "Marina";
        speakTo = "ru-ru";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
        }
    }

    @FXML
    void chinese() throws IOException {
        resetStyleLangTo();
        langToFifth.getStyleClass().add("active");
        languageTo = "zh";
        text2.setText("Tiếng Trung");
        nameTo = "Luli";
        speakTo = "zh-cn";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
        }
    }

    @FXML
    void translate() throws IOException {
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(languageFrom, languageTo, area1.getText()));
        }
    }

    @FXML
    void speak1() throws Exception {
        VoiceRSS.Name = nameFrom;
        VoiceRSS.language = speakFrom;
        if (!Objects.equals(area1.getText(), "")) {
            VoiceRSS.speakWord(area1.getText());
        }
    }

    @FXML
    void speak2() throws Exception {
        VoiceRSS.Name = nameTo;
        VoiceRSS.language = speakTo;
        if (!Objects.equals(area2.getText(), "")) {
            VoiceRSS.speakWord(area2.getText());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        langFromFirst.getStyleClass().add("active");
        langToFirst.getStyleClass().add("active");

        text1.setText("Phát hiện n.ngữ");
        area1.setText("");
        nameFrom = "Linda";
        speakFrom = "en-gb";
        languageFrom = "";

        text2.setText("Tiếng Việt");
        nameTo = "Chi";
        speakTo = "vi-vn";
        languageTo = "vi";
    }
}
