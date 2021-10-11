package sample;

import main.NewDictionary;
import main.VoiceRSS;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import main.Word;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class Settings extends GeneralController implements Initializable {
    @FXML
    private Button b1;
    @FXML
    private TextField text1;
    @FXML
    private TextField addText;
    @FXML
    private Button save;
    @FXML
    private WebView web;
    @FXML
    private HTMLEditor edit;
    @FXML
    private HTMLEditor addEditor;
    @FXML
    private RadioButton av;
    @FXML
    private RadioButton va;
    @FXML
    private RadioButton addEV;
    @FXML
    private RadioButton addVE;
    @FXML
    private ToggleGroup data;
    @FXML
    private ToggleGroup data1;
    String[] voiceUS = {"Linda", "Amy", "Mary", "John", "Mike"};
    String[] voiceUK = {"Alice", "Nancy", "Lily", "Harry"};
    void showDefinition() {
        edit.setVisible(false);
        String a = text1.getText();
        int index = Collections.binarySearch(getDictionary().getVocab(), new Word(a,null));
        String b = getDictionary().getVocab().get(index).getMeaning();
        web.getEngine().loadContent(b, "text/html");

    }
    @FXML
    void xemtruoc(ActionEvent event) {
        showDefinition();
    }

    @FXML
    void edit(ActionEvent event) {
        edit.setVisible(true);
        String a = text1.getText();
        int index = Collections.binarySearch(getDictionary().getVocab(), new Word(a,null));
        String b = getDictionary().getVocab().get(index).getMeaning();
        edit.setHtmlText(b);

    }
    @FXML
    void save(ActionEvent event) {
        getDictionary().modifyWord(text1.getText(), edit.getHtmlText().replace(" dir=\"ltr\"", ""));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Sửa từ thành công");
        alert.showAndWait();
        showDefinition();
    }

    @FXML
    void remove(ActionEvent event) {
        getDictionary().removeWord(text1.getText(), getDictionary().getPATH(), getDictionary().getVocab());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Xoá từ thành công");
        alert.showAndWait();
    }

    void addDefault(){
        addText.clear();
        addText.setPromptText("Nhập từ bạn muốn thêm");
        addEditor.setHtmlText("<html><ul><li><b><i> loại từ: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul></li></ul></ul></li></ul><ul><li><b><i>loại từ khác: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font></li></ul></li></ul></html>");
    }

    @FXML
    void add(){
        String meaning = addEditor.getHtmlText().replace(" dir=\"ltr\"", "");
        getDictionaryToAdd().addWord(addText.getText(), meaning);
        addDefault();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Thêm từ thành công");
        alert.showAndWait();
    }

    private NewDictionary getDictionaryToAdd(){
        if(addEV.isSelected()){
            return evDic;
        }
        else{
            return veDic;
        }
    }

    private NewDictionary getDictionary(){
        if(av.isSelected()){
            return evDic;
        }
        else{
            return veDic;
        }
    }

    private static final String SLIDER_STYLE_FORMAT =
            "-slider-track-color: linear-gradient(to right, -slider-filled-track-color 0%%, "
                    + "-slider-filled-track-color %1$f%%, -fx-base %1$f%%, -fx-base 100%%);";

    @FXML
    void voice(ActionEvent event) throws Exception {
        VoiceRSS.speed = (int) slider.getValue();
        VoiceRSS.speakWord("information");
    }

    @FXML
    protected void voiceuk(ActionEvent event) throws Exception {
        VoiceRSS.Name = choiceboxuk.getValue();
        VoiceRSS.language = "en-gb";
        VoiceRSS.speed = (int) slider.getValue();
        VoiceRSS.speakWord("information");
    }
    @FXML
    protected void voiceus(ActionEvent event) throws Exception {
        VoiceRSS.Name = choiceboxus.getValue();
        VoiceRSS.language="en-us";
        VoiceRSS.speed = (int) slider.getValue();
        VoiceRSS.speakWord("information");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.styleProperty().bind(Bindings.createStringBinding(() -> {
            double percentage = (slider.getValue() - slider.getMin()) / (slider.getMax() - slider.getMin()) * 100.0;
            return String.format("-slider-track-color: linear-gradient(to right, -slider-filled-track-color 0%%, "
                            + "-slider-filled-track-color %f%%, -fx-base %f%%, -fx-base 100%%);",
                    percentage, percentage);
        }, slider.valueProperty(), slider.minProperty(), slider.maxProperty()));

        edit.setVisible(false);
        av.setSelected(true);
        addEV.setSelected(true);
        //TextFields.bindAutoCompletion(text1, word);
        choiceboxuk.getItems().addAll(voiceUK);
        choiceboxus.getItems().addAll(voiceUS);
        choiceboxuk.setValue("Alice");
        choiceboxus.setValue("Linda");
        addDefault();
    }
}
