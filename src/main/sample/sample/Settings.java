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
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

public class Settings extends GeneralController implements Initializable {
    @FXML
    private TextField editTextEV;
    @FXML
    private TextField editTextVE;
    @FXML
    private TextField addText;
    @FXML
    private WebView web;
    @FXML
    private HTMLEditor edit;
    @FXML
    private HTMLEditor addEditor;
    @FXML
    private RadioButton editEV;
    @FXML
    private RadioButton editVE;
    @FXML
    private RadioButton addEV;
    @FXML
    private RadioButton addVE;
    @FXML
    private ToggleGroup data;
    @FXML
    private ToggleGroup data1;
    ArrayList<String> wordve = new ArrayList<>();
    ArrayList<String> wordev = new ArrayList<>();
    String[] voiceUS = {"Linda", "Amy", "Mary", "John", "Mike"};
    String[] voiceUK = {"Alice", "Nancy", "Lily", "Harry"};

    public void showDefinition() {
        String a;
        if (editEV.isSelected()) {
            a = editTextEV.getText();
        } else {
            a = editTextVE.getText();
        }
        int index = Collections.binarySearch(getDictionary().getVocab(), new Word(a, null));
        String b = getDictionary().getVocab().get(index).getMeaning();
        web.getEngine().loadContent(b, "text/html");

    }

    @FXML
    public void handleClickEditArrow(ActionEvent event) {
        String a;
        edit.setVisible(true);
        if (editEV.isSelected()) {
            a = editTextEV.getText();
        } else {
            a = editTextVE.getText();
        }
        int index = Collections.binarySearch(getDictionary().getVocab(), new Word(a, null));
        String b = getDictionary().getVocab().get(index).getMeaning();
        edit.setHtmlText(b);
    }

    public void addWordListEV() {
        for (int i = 0; i < evDic.getVocab().size(); i++) {
            wordev.add(evDic.getVocab().get(i).getSearching());
        }
    }

    public void addWordListVE() {
        for (int i = 0; i < veDic.getVocab().size(); i++) {
            wordve.add(veDic.getVocab().get(i).getSearching());
        }
    }

    @FXML
    public void save(ActionEvent event) {
        if(editTextEV.getText().equals("") && editTextVE.getText().equals("")){
            showWarningAlert();
            return;
        }
        if (editEV.isSelected()) {
            getDictionary().modifyWord(editTextEV.getText(), edit.getHtmlText().replace(" dir=\"ltr\"", ""));
        } else {
            getDictionary().modifyWord(editTextVE.getText(), edit.getHtmlText().replace(" dir=\"ltr\"", ""));
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Sửa từ thành công");
        alert.showAndWait();
        showDefinition();
    }

    @FXML
    public void remove(ActionEvent event) {
        if(editTextEV.getText().equals("") || editTextVE.getText().equals("")){
            showWarningAlert();
            return;
        }
        if (editEV.isSelected()) {
            ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == yes) {
                getDictionary().removeWord(editTextEV.getText(), getDictionary().getPATH(), getDictionary().getVocab());
                editTextEV.clear();
                edit.setHtmlText("");
            }
        } else {
            ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == yes) {
                getDictionary().removeWord(editTextVE.getText(), getDictionary().getPATH(), getDictionary().getVocab());
                editTextVE.clear();
                edit.setHtmlText("");
            }
        }
    }

    public void addDefault() {
        addText.clear();
        addEditor.setHtmlText("");
    }

    @FXML
    public void addReset() {
        addText.clear();
        addEditor.setHtmlText("<html><ul><li><b><i> loại từ: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul></li></ul></ul></li></ul><ul><li><b><i>loại từ khác: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font></li></ul></li></ul></html>");
    }

    @FXML
    public void handleClickArrow() {
        if (addText.getText().equals("")) {
            showWarningAlert();
            return;
        }
        addEditor.setHtmlText("<html>" + addText.getText() + " /" + addText.getText() + "/"
                + "<ul><li><b><i> loại từ: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul></li></ul></ul></li></ul><ul><li><b><i>loại từ khác: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font></li></ul></li></ul></html>");
    }

    @FXML
    void add() {
        String meaning = addEditor.getHtmlText().replace(" dir=\"ltr\"", "");
        if(addText.getText().equals("")){
            showWarningAlert();
            return;
        }
        if (getDictionaryToAdd().addWord(addText.getText(), meaning)) {
            addReset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Thêm từ thành công");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Từ bạn thêm đã tồn tại! Hãy chọn chức năng sửa đổi!");
            alert.showAndWait();
        }
    }

    private NewDictionary getDictionaryToAdd() {
        if (addEV.isSelected()) {
            return evDic;
        } else {
            return veDic;
        }
    }

    private NewDictionary getDictionary() {
        if (editEV.isSelected()) {
            return evDic;
        } else {
            return veDic;
        }
    }

    private static final String SLIDER_STYLE_FORMAT =
            "-slider-track-color: linear-gradient(to right, -slider-filled-track-color 0%%, "
                    + "-slider-filled-track-color %1$f%%, -fx-base %1$f%%, -fx-base 100%%);";

    @FXML
    void saveVoice(ActionEvent event) {
        VoiceRSS.speed = slider.getValue();
        VoiceRSS.voiceNameUS = choiceboxus.getValue();
        VoiceRSS.voiceNameUK = choiceboxuk.getValue();
    }

    @FXML
    void voice(ActionEvent event) throws Exception {
        VoiceRSS.speed = slider.getValue();
        VoiceRSS.speakWord("information");
    }

    @FXML
    protected void voiceuk(ActionEvent event) throws Exception {
        VoiceRSS.Name = choiceboxuk.getValue();
        VoiceRSS.language = "en-gb";
        VoiceRSS.speed = slider.getValue();
        VoiceRSS.speakWord("information");
    }

    @FXML
    protected void voiceus(ActionEvent event) throws Exception {
        VoiceRSS.Name = choiceboxus.getValue();
        VoiceRSS.language = "en-us";
        VoiceRSS.speed = slider.getValue();
        VoiceRSS.speakWord("information");
    }

    @FXML
    void handleClickEVButton(ActionEvent event) {
        editTextEV.setVisible(true);
        editTextVE.setVisible(false);
        TextFields.bindAutoCompletion(editTextEV, wordev);
    }

    @FXML
    void handleClickVEButton(ActionEvent event) {
        editTextEV.setVisible(false);
        editTextVE.setVisible(true);
        TextFields.bindAutoCompletion(editTextVE, wordve);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.styleProperty().bind(Bindings.createStringBinding(() -> {
            double percentage = (slider.getValue() - slider.getMin()) / (slider.getMax() - slider.getMin()) * 100.0;
            return String.format("-slider-track-color: linear-gradient(to right, -slider-filled-track-color 0%%, "
                            + "-slider-filled-track-color %f%%, -fx-base %f%%, -fx-base 100%%);",
                    percentage, percentage);
        }, slider.valueProperty(), slider.minProperty(), slider.maxProperty()));
        slider.setValue(VoiceRSS.speed);
        addWordListEV();
        addWordListVE();
        edit.setVisible(true);
        editEV.setSelected(true);
        addEV.setSelected(true);
        editTextEV.setVisible(true);
        editTextVE.setVisible(false);
        TextFields.bindAutoCompletion(editTextEV, wordev);
        choiceboxuk.getItems().addAll(voiceUK);
        choiceboxus.getItems().addAll(voiceUS);
        choiceboxuk.setValue(VoiceRSS.voiceNameUK);
        choiceboxus.setValue(VoiceRSS.voiceNameUS);
        addDefault();
    }
}